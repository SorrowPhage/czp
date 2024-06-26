package com.sorrowphage.czp.service.serviceimpl;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sorrowphage.czp.entity.*;
import com.sorrowphage.czp.entity.vo.MessageVO;
import com.sorrowphage.czp.entity.vo.UserVo;
import com.sorrowphage.czp.mapper.ChatListMapper;
import com.sorrowphage.czp.mapper.CzpMessageMapper;
import com.sorrowphage.czp.mapper.CzpUserMapper;
import com.sorrowphage.czp.service.CzpMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sorrowphage.czp.socket.MsgChatWebSocketServer;
import com.sorrowphage.czp.utils.DateUtil;
import freemarker.template.utility.CollectionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 *
 * 水调歌头·中秋
 * 明月几时有，把酒问青天，不知天上宫阙，今夕是何年，我欲乘风归去，又恐琼楼玉宇，高处不胜寒，起舞弄清影，何似在人间。转朱阁，低绮户，照无眠。不应有恨，何事长向别时圆？人有悲欢离合，月有阴晴圆缺，此事古难全。但愿人长久，千里共婵娟。
 * </p>
 *
 * @author SorrowPhage
 * @since 2023-11-22
 */
@Service
@RequiredArgsConstructor
public class CzpMessageServiceImpl extends ServiceImpl<CzpMessageMapper, CzpMessage> implements CzpMessageService {

    private final CzpMessageMapper czpMessageMapper;

    private final CzpUserMapper czpUserMapper;


    private final MsgChatWebSocketServer socketServer;

    private final ChatListMapper chatListMapper;


    /**
     * 获取聊天列表
     * @param id 用户id
     * @return 查询数据
     */
    @Override
    public ResultMessage getChatList(String id) {
        // List<CzpUser> dataList = czpMessageMapper.getChatList(id);
        List<CzpUser> dataList = czpMessageMapper.getChatListFromMessage(id);
        return ResultMessage.success(dataList);
    }

    @Override
    public ResultMessage getMessageList(String u1, String u2) {
        //判断u2是否存在
        // UserVo user1 = czpUserMapper.userInfo(u1);
        // UserVo user2 = czpUserMapper.userInfo(u2);
        // if (Objects.isNull(user2)) {
        //     return ResultMessage.failure(u2 + "用户不存在");
        // }
        //将用户查询的信息中的未读修改为已读
        czpMessageMapper.updateMessageStatusToRead(u1,u2);
        List<MessageVO> dataList = czpMessageMapper.getMessageList(u1, u2);

        // for (MessageVO messageVO : dataList) {
        //     if (messageVO.getFromId().equals(u1)) {
        //         messageVO.setUser(user1);
        //     } else {
        //         messageVO.setUser(user2);
        //     }
        // }

        return ResultMessage.success(dataList);
    }

    @Override
    public ResultMessage sendMessage(CzpMessage message) {
        String currentTime = DateUtil.getSystemDateTimeString();
        //判断两人之间是否有消息列表没有就加入
        List<ChatList> chatLists = chatListMapper.selectChatRelationship(message.getFromId(), message.getToId());
        if (CollectionUtil.isEmpty(chatLists)) {
            ChatList chatList = new ChatList();
            chatList.setOwnId(message.getFromId());
            chatList.setOtherId(message.getToId());
            chatListMapper.insert(chatList);
        }
        List<ChatList> chatLists2 = chatListMapper.selectChatRelationship(message.getToId(), message.getFromId());
        if (CollectionUtil.isEmpty(chatLists2)) {
            ChatList chatList = new ChatList();
            chatList.setOwnId(message.getToId());
            chatList.setOtherId(message.getFromId());
            chatListMapper.insert(chatList);
        }
        message.setSendTime(currentTime);
        message.setCreateTime(currentTime);
        UserVo userVo = czpUserMapper.userInfo(message.getFromId());
        message.setAvatar(userVo.getAvatar());
        message.setName(userVo.getName());
        //判断用户是否在线，若用户在线则消息状态修改为已读(1),不在线为未读(0)   但是因为前端的websocket在头部，通过全局事件总线进行消息的转发，
        //其实接收方不一定在聊天页面，所以这样的判断是有一点瑕疵的，但是接收方用户是否在线是确定的，所以前端在接收到消息时，需判断接收用户是否在聊天页面和发送方进行聊天
        //若不在聊天页面，则需要将该消息使用Message组件进行展示，以此达到让用户读过的感觉。
        //本来是使用以上的方式，但是上面的方式有一个bug:当用户在聊天时，所以消息都会被推送带目前的聊天页面，前端需要进行判断再判断消息是否需要push
        boolean online = socketServer.isOnline(message.getToId());
        // if (online) {
        //     message.setStatus(1);
        // }
        //先保存到数据库中再进行推送，这样可以拿到id，v-for展示时有唯一id
        this.save(message);

        //如果是发送方和接收方一样，那么不需要进行推送
        boolean mySelf = !message.getFromId().equals(message.getToId());

        if (online && mySelf) {
            //将消息进行推送
            JSONObject o = (JSONObject) JSONObject.toJSON(message);
            socketServer.appointSending(message.getToId(), o.toJSONString());
        }
        return ResultMessage.success(message);
    }

    @Override
    public ResultMessage getGangHsienMessageList(String toId) {
        List<MessageVO> list = czpMessageMapper.getGangHsienMessageList(toId);
        return ResultMessage.success(list);
    }

    @Override
    public ResultMessage sendGangHsienMessage(CzpMessage message) {
        String currentTime = DateUtil.getSystemDateTimeString();
        message.setSendTime(currentTime);
        message.setCreateTime(currentTime);
        UserVo userVo = czpUserMapper.userInfo(message.getFromId());
        message.setAvatar(userVo.getAvatar());
        message.setName(userVo.getName());
        this.save(message);
        JSONObject o = (JSONObject) JSONObject.toJSON(message);
        socketServer.GroupSending(o.toJSONString());
        return ResultMessage.success(message);
    }

    @Override
    public ResultMessage closeChat(ChatList chatList) {
        QueryWrapper<ChatList> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(ChatList::getOwnId, chatList.getOwnId()).eq(ChatList::getOtherId, chatList.getOtherId());
        int delete = chatListMapper.delete(wrapper);
        if (delete > 0) {
            return ResultMessage.success("删除成功");
        }
        return ResultMessage.failure("没有数据");
    }
}
