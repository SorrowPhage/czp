package com.sorrowphage.czp.mapper;

import com.sorrowphage.czp.entity.Audit;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sorrowphage.czp.entity.UserGroup;
import com.sorrowphage.czp.entity.vo.AuditVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author SorrowPhage
 * @since 2023-11-07
 */
@Mapper
public interface AuditMapper extends BaseMapper<Audit> {

    //从中间表中找出id用户相关的审计数据
    List<AuditVO> auditList(String id);

    /**
     * 审核历史
     * @param id
     * @return
     */
    List<AuditVO> auditListHistory(String id);

    /**
     * 将审核结果放入历史表
     * @param audit
     * @return
     */
    int insertToHistory(Audit audit);
}
