package com.sorrowphage.czp.entity.vo;

import lombok.Data;

import java.util.List;

/**
 * @author: SorrowPhage
 * @date: 2024/3/22
 */
@Data
public class LargeGraphVO {
    private List<GroupPeopleNumer> groupPeopleNumerList;
    private Integer groupNum;
    private Integer groupPeopleNum;
}
