package com.sorrowphage.czp.entity.dto;

import lombok.Data;

/**
 * @author: SorrowPhage
 * @date: 2024/3/27
 */
@Data
public class MajorDTO {
    private String groupId;
    private Integer pageNum = 1;
    private Integer pageSize = 10;
}
