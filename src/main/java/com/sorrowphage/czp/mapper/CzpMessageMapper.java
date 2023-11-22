package com.sorrowphage.czp.mapper;

import com.sorrowphage.czp.entity.CzpMessage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sorrowphage.czp.entity.CzpUser;
import com.sorrowphage.czp.entity.vo.MessageVO;
import org.apache.ibatis.annotations.Mapper;

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

    List<CzpUser> getChatList(String id);

    List<MessageVO> getMessageList(String u1, String u2);
}
