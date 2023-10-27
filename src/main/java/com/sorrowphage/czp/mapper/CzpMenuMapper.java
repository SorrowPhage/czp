package com.sorrowphage.czp.mapper;

import com.sorrowphage.czp.entity.CzpMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author SorrowPhage
 * @since 2023-10-27
 */
@Mapper
public interface CzpMenuMapper extends BaseMapper<CzpMenu> {

    List<String> menuListByUserId(String userId);

}
