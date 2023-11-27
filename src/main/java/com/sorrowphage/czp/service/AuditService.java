package com.sorrowphage.czp.service;

import com.sorrowphage.czp.entity.Audit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sorrowphage.czp.entity.ResultMessage;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author SorrowPhage
 * @since 2023-11-07
 */
public interface AuditService extends IService<Audit> {

    /**
     * 申请加入族群
     * @param audit
     * @return
     */
    ResultMessage applyJoinGroup(Audit audit);


    /**
     * 审核结果
     * @param audit
     * @return
     */
    ResultMessage auditResult(Audit audit);


    /**
     * 未审核列表
     * @param id
     * @return
     */
    ResultMessage auditList(String id);


    /**
     * 审核历史记录
     * @param id
     * @return
     */
    ResultMessage auditHistoryList(String id);

    ResultMessage auditResultFromHistory(String id);

}
