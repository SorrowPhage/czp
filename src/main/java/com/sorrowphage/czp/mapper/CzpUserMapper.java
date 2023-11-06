package com.sorrowphage.czp.mapper;

import com.sorrowphage.czp.entity.CzpUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sorrowphage.czp.entity.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author SorrowPhage
 * @since 2023-09-11
 */
@Mapper
public interface CzpUserMapper extends BaseMapper<CzpUser> {

    /**
     * TODO 这里会扩展
     * @param id
     * @return
     */
    UserVo userInfo(String id);
}
