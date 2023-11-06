package com.sorrowphage.czp.entity.vo;

import com.sorrowphage.czp.entity.CzpUser;
import lombok.Data;

import java.util.List;

/**
 * @author: SorrowPhage
 * @date: 2023/11/1
 */
@Data
public class UserVo extends CzpUser {

    private List<String> permissions;

}
