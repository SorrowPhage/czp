package com.sorrowphage.czp.mapper;

import com.sorrowphage.czp.entity.CzpUserChatList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author SorrowPhage
 * @since 2023-12-07
 */
@Mapper
public interface CzpUserChatListMapper extends BaseMapper<CzpUserChatList> {

    List<CzpUserChatList> selectBetweenTheTwoUserList(String fromId, String toId);

}
