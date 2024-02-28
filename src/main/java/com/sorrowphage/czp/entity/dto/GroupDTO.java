package com.sorrowphage.czp.entity.dto;

import lombok.Data;

/**
 * @author: SorrowPhage
 * @date: 2024/2/22
 */
@Data
public class GroupDTO {
    private String groupName;
    private Integer pageNum = 1;
    private Integer pageSize = 10;
}
