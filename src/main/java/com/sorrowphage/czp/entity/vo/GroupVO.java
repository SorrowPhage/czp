package com.sorrowphage.czp.entity.vo;

import com.sorrowphage.czp.entity.Group;
import lombok.Data;

/**
 * @author: SorrowPhage
 * @date: 2023/11/7
 */
@Data
public class GroupVO extends Group {

    private String name;
    private String avatar;
    private String parentName;

}
