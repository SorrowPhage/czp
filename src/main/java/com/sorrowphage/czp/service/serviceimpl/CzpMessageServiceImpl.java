package com.sorrowphage.czp.service.serviceimpl;

import com.alibaba.fastjson.JSONObject;
import com.sorrowphage.czp.entity.CzpMessage;
import com.sorrowphage.czp.entity.CzpUser;
import com.sorrowphage.czp.entity.ResultMessage;
import com.sorrowphage.czp.entity.vo.MessageVO;
import com.sorrowphage.czp.entity.vo.UserVo;
import com.sorrowphage.czp.mapper.CzpMessageMapper;
import com.sorrowphage.czp.mapper.CzpUserMapper;
import com.sorrowphage.czp.service.CzpMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sorrowphage.czp.socket.MsgChatWebSocketServer;
import com.sorrowphage.czp.utils.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.chrono.IsoChronology;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 服务实现类
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
        UserVo user1 = czpUserMapper.userInfo(u1);
        UserVo user2 = czpUserMapper.userInfo(u2);
        if (Objects.isNull(user2)) {
            return ResultMessage.failure(u2 + "用户不存在");
        }
        //将用户查询的信息中的未读修改为已读
        czpMessageMapper.updateMessageStatusToRead(u1,u2);
        List<MessageVO> dataList = czpMessageMapper.getMessageList(u1, u2);

        for (MessageVO messageVO : dataList) {
            if (messageVO.getFromId().equals(u1)) {
                messageVO.setUser(user1);
            } else {
                messageVO.setUser(user2);
            }
        }

        return ResultMessage.success(dataList);
    }

    @Override
    public ResultMessage sendMessage(CzpMessage message) {
        //TODO 聊天列表
        //判断两个用户之间是否已创建过聊天,或者不用这种写法，只管消息列表中的是否有两人的消息，进行展示(目前使用的第二种,不需要进行操作,但是这种写法就无法删除)
        //若没用创建过聊天，则新增
        message.setSendTime(DateUtil.getSystemDateTimeString());
        message.setCreateTime(DateUtil.getSystemDateTimeString());
        message.setUser(czpUserMapper.userInfo(message.getFromId()));
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
        if (online) {
            //将消息进行推送
            JSONObject o = (JSONObject) JSONObject.toJSON(message);
            socketServer.appointSending(message.getToId(), o.toJSONString());
        }
        return ResultMessage.success(message);
    }

}
