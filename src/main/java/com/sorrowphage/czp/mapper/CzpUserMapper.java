package com.sorrowphage.czp.mapper;

import com.sorrowphage.czp.entity.CzpUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sorrowphage.czp.entity.dto.GraphDTO;
import com.sorrowphage.czp.entity.vo.AGePieCharts;
import com.sorrowphage.czp.entity.vo.BirthYear;
import com.sorrowphage.czp.entity.vo.GraphVO;
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

    CzpUser selectUserInfo(String id);

    List<AGePieCharts> selectAgePieChatrsList(GraphDTO graphDTO);

    List<BirthYear> selectBirthAndDeathYearChartsList(GraphDTO graphDTO);

    /**
     * 因为实体和AGePieCharts类似，所以返回改实体
     * @param graphDTO
     * @return
     */
    List<AGePieCharts> selectMaleFemale(GraphDTO graphDTO);
}
