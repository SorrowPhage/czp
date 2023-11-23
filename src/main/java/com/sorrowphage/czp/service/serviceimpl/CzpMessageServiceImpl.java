package com.sorrowphage.czp.service.serviceimpl;

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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.chrono.IsoChronology;
import java.util.List;

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


    @Override
    public ResultMessage getChatList(String id) {
        List<CzpUser> dataList = czpMessageMapper.getChatList(id);
        return ResultMessage.success(dataList);
    }

    @Override
    public ResultMessage getMessageList(String u1, String u2) {
        //TODO 将用户查询的信息中的未读修改为已读

        List<MessageVO> dataList = czpMessageMapper.getMessageList(u1, u2);

        UserVo user1 = czpUserMapper.userInfo(u1);
        UserVo user2 = czpUserMapper.userInfo(u2);

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
        //判断用户是否在线
        boolean online = socketServer.isOnline(message.getToId());
        if (online) {
            //将消息进行推送
            socketServer.appointSending(message.getToId(), message.getContent());
        }

        return null;
    }
}
