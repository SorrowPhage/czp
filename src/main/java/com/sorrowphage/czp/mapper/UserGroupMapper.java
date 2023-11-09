package com.sorrowphage.czp.mapper;

import com.sorrowphage.czp.entity.UserGroup;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author SorrowPhage
 * @since 2023-11-08
 */
@Mapper
public interface UserGroupMapper extends BaseMapper<UserGroup> {

    /**
     * 该用户是否在该族群当中
     * @param id
     * @param userId
     * @return
     */
    UserGroup isExist(String id, String userId);


}
