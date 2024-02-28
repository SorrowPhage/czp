package com.sorrowphage.czp.entity.vo;

import lombok.Data;

import java.util.List;

/**
 * @author: SorrowPhage
 * @date: 2024/2/26
 */
@Data
public class GraphVO {
    private List<AGePieCharts> agePieChartsList;
    private List<BirthYear> birthAndDeathYearList;
    private List<AGePieCharts> maleToFemaleList;
}
