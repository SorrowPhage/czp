package com.sorrowphage.czp.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * @author: SorrowPhage
 * @date: 2023/11/27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RelationGraphNode {
    private String id;
    private String text;
    private Object data;
    public RelationGraphNode(String id, String text) {
        this.id = id;
        this.text = text;
    }
}
