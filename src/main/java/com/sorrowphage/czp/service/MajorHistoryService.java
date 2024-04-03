package com.sorrowphage.czp.service;

import com.sorrowphage.czp.entity.MajorHistory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sorrowphage.czp.entity.ResultMessage;
import com.sorrowphage.czp.entity.dto.MajorDTO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author SorrowPhage
 * @since 2024-03-26
 */
public interface MajorHistoryService extends IService<MajorHistory> {

    ResultMessage createMajorHistory(MajorHistory majorHistory);

    ResultMessage getMajorHistoryList(MajorDTO pageDTO);

    ResultMessage updateMajorHistory(MajorHistory majorHistory);
}
