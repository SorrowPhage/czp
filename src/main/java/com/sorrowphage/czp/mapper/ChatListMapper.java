package com.sorrowphage.czp.mapper;

import com.sorrowphage.czp.entity.ChatList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author SorrowPhage
 * @since 2024-04-03
 */
@Mapper
public interface ChatListMapper extends BaseMapper<ChatList> {

    List<ChatList> selectChatRelationship(@Param("ownId") String ownId,@Param("otherId") String otherId);

}
