package com.sorrowphage.czp.entity.vo;

import com.sorrowphage.czp.entity.CzpUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: SorrowPhage
 * @date: 2023/11/1
 */
@Data
public class UserVo extends CzpUser {

    private List<String> permissions;

    private List<UserVo> children = new ArrayList<>();

    //父级id
    private String parentId;

}
