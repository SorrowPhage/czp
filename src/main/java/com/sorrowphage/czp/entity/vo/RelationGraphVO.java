package com.sorrowphage.czp.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: SorrowPhage
 * @date: 2023/11/27
 * RelationGraph组件需要的数据
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RelationGraphVO {
    private String rootId;
    private List<RelationGraphNode> nodes;
    private List<RelationGraphLine> lines;

    public RelationGraphVO(List<RelationGraphNode> nodes, List<RelationGraphLine> lines) {
        this.nodes = nodes;
        this.lines = lines;
    }
}
