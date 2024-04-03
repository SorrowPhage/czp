package com.sorrowphage.czp.service;

import com.sorrowphage.czp.entity.CzpMessage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sorrowphage.czp.entity.ResultMessage;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author SorrowPhage
 * @since 2023-11-22
 */
public interface CzpMessageService extends IService<CzpMessage> {

    ResultMessage getChatList(String id);

    ResultMessage getMessageList(String u1, String u2);

    ResultMessage sendMessage(CzpMessage message);

    ResultMessage getGangHsienMessageList(String toId);

    ResultMessage sendGangHsienMessage(CzpMessage message);
}
