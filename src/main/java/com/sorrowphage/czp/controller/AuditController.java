package com.sorrowphage.czp.controller;


import com.sorrowphage.czp.entity.Audit;
import com.sorrowphage.czp.entity.ResultMessage;
import com.sorrowphage.czp.service.AuditService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author SorrowPhage
 * @since 2023-11-07
 */
@RestController
@RequestMapping("/audit")
@RequiredArgsConstructor
public class AuditController {

    private final AuditService auditService;

    /**
     * 申请加入族群
     * @param audit
     * @return
     */
    @PostMapping("/apply")
    public ResultMessage applyJoinGroup(@RequestBody Audit audit) {
        return auditService.applyJoinGroup(audit);
    }

    /**
     * 审核结果
     * @param audit
     * @return
     */
    @PostMapping("/result")
    public ResultMessage auditResult(@RequestBody Audit audit) {
        return auditService.auditResult(audit);
    }

    /**
     * 未审核列表
     * @param id
     * @return
     */
    @GetMapping("/list")
    public ResultMessage auditList(@RequestParam("id") String id) {
        return auditService.auditList(id);
    }

    /**
     * 审核历史
     * @param id
     * @return
     */
    @GetMapping("/history")
    public ResultMessage auditHistoryList(@RequestParam("id") String id) {
        return auditService.auditHistoryList(id);
    }
}
