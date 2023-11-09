package com.sorrowphage.czp.service.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sorrowphage.czp.entity.Audit;
import com.sorrowphage.czp.entity.ResultMessage;
import com.sorrowphage.czp.entity.UserGroup;
import com.sorrowphage.czp.entity.vo.AuditVO;
import com.sorrowphage.czp.mapper.AuditMapper;
import com.sorrowphage.czp.mapper.UserGroupMapper;
import com.sorrowphage.czp.service.AuditService;
import com.sorrowphage.czp.utils.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author SorrowPhage
 * @since 2023-11-07
 */
@Service
@RequiredArgsConstructor
public class AuditServiceImpl extends ServiceImpl<AuditMapper, Audit> implements AuditService {

    private final UserGroupMapper userGroupMapper;

    private final AuditMapper auditMapper;


    /**
     * 申请加入族群
     * @param audit
     * @return
     */
    @Override
    public ResultMessage applyJoinGroup(Audit audit) {
        audit.setCreateTime(DateUtil.getSystemDateTimeString());
        boolean save = this.save(audit);
        if (save) {
            return ResultMessage.success("提交成功");
        } else {
            return ResultMessage.failure("提交失败");
        }
    }


    /**
     * 审核结果
     * @param audit
     * @return
     */
    @Override
    public ResultMessage auditResult(Audit audit) {
        audit.setUpdateTime(DateUtil.getSystemDateTimeString());
        //若audit状态为1则，通过
        if ("1".equals(audit.getStatus())) {
            // this.saveOrUpdate(audit);
            //插入历史表
            auditMapper.insertToHistory(audit);

            //删除中间表中的数据
            LambdaUpdateWrapper<Audit> wrapper = new LambdaUpdateWrapper<>();
            wrapper.eq(Audit::getId, audit.getId());
            this.remove(wrapper);

            //将结果放入UserGroup表
            UserGroup userGroup = new UserGroup();
            userGroup.setUserId(audit.getUserId());
            userGroup.setGroupId(audit.getGroupId());
            userGroup.setParentId(audit.getParentId());
            userGroup.setCreateTime(DateUtil.getSystemDateTimeString());
            userGroup.setUpdateTime(DateUtil.getSystemDateTimeString());
            userGroupMapper.insert(userGroup);
            return ResultMessage.success("操作成功");
        } else {
            //反之则失败
            this.saveOrUpdate(audit);
            return ResultMessage.success("操作成功");
        }
    }


    /**
     * 未审核列表
     * @param id
     * @return
     */
    @Override
    public ResultMessage auditList(String id) {
        List<AuditVO> list = auditMapper.auditList(id);
        return ResultMessage.success(list);
    }


    /**
     * 审核历史记录
     * @param id
     * @return
     */
    @Override
    public ResultMessage auditHistoryList(String id) {
        List<AuditVO> list = auditMapper.auditListHistory(id);
        return ResultMessage.success(list);
    }
}
