package com.sorrowphage.czp.mapper;

import com.sorrowphage.czp.entity.CzpMessage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sorrowphage.czp.entity.CzpUser;
import com.sorrowphage.czp.entity.vo.MessageVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author SorrowPhage
 * @since 2023-11-22
 */
@Mapper
public interface CzpMessageMapper extends BaseMapper<CzpMessage> {

    /**
     * 聊天列表
     * @param id
     * @return
     */
    List<CzpUser> getChatList(String id);

    /**
     * 从信息表中构建聊天列表
     * @param id
     * @return
     */
    List<CzpUser> getChatListFromMessage(String id);

    /**
     * 信息列表
     * @param u1
     * @param u2
     * @return
     */
    List<MessageVO> getMessageList(@Param("fromId") String u1,@Param("toId") String u2);

    /**
     * 修改消息状态为已读
     *
     * @param u1 读取消息用户
     * @param u2 发送方
     * @return 处理数量
     */
    int updateMessageStatusToRead(String u1, String u2);


    List<MessageVO> getGangHsienMessageList(@Param(("toId")) String toId);
}
