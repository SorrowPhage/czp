package com.sorrowphage.czp.controller;

import com.sorrowphage.czp.entity.MajorHistory;
import com.sorrowphage.czp.entity.ResultMessage;
import com.sorrowphage.czp.entity.dto.MajorDTO;
import com.sorrowphage.czp.service.MajorHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author: SorrowPhage
 * @date: 2024/3/27
 */
@RestController
@RequestMapping("/mh")
@RequiredArgsConstructor
public class MajorHistoryController {

    private final MajorHistoryService service;

    @PostMapping("/create")
    public ResultMessage createMajorHistory(@RequestBody MajorHistory majorHistory) {
        return service.createMajorHistory(majorHistory);
    }

    @PostMapping("/list")
    public ResultMessage getMajorHistoryList(@RequestBody MajorDTO pageDTO) {
        return service.getMajorHistoryList(pageDTO);
    }

    @PostMapping("/update")
    public ResultMessage updateMajorHistory(@RequestBody MajorHistory majorHistory) {
        return service.updateMajorHistory(majorHistory);
    }
}
