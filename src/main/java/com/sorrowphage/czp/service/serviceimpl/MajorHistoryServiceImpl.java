package com.sorrowphage.czp.service.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sorrowphage.czp.entity.MajorHistory;
import com.sorrowphage.czp.entity.ResultMessage;
import com.sorrowphage.czp.entity.dto.MajorDTO;
import com.sorrowphage.czp.mapper.MajorHistoryMapper;
import com.sorrowphage.czp.service.MajorHistoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author SorrowPhage
 * @since 2024-03-26
 */
@Service
public class MajorHistoryServiceImpl extends ServiceImpl<MajorHistoryMapper, MajorHistory> implements MajorHistoryService {

    @Override
    public ResultMessage createMajorHistory(MajorHistory majorHistory) {
        majorHistory.setCreateTime(LocalDateTime.now());
        majorHistory.setUpdateTime(LocalDateTime.now());
        this.save(majorHistory);
        return ResultMessage.success("新增成功");
    }

    @Override
    public ResultMessage getMajorHistoryList(MajorDTO pageDTO) {
        PageHelper.startPage(pageDTO.getPageNum(), pageDTO.getPageSize());
        QueryWrapper<MajorHistory> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(MajorHistory::getGroupId, pageDTO.getGroupId());
        List<MajorHistory> list = this.list(wrapper);
        return ResultMessage.success(new PageInfo<>(list));
    }

    @Override
    public ResultMessage updateMajorHistory(MajorHistory majorHistory) {
        MajorHistory major = this.getById(majorHistory.getId());
        major.setContent(majorHistory.getContent());
        major.setUpdateTime(LocalDateTime.now());
        major.setUserId(majorHistory.getUserId());
        return ResultMessage.success("修改成功");
    }


}
