package com.sorrowphage.czp.entity.vo;

import com.sorrowphage.czp.entity.Audit;
import lombok.Data;

/**
 * @author: SorrowPhage
 * @date: 2023/11/8
 */
@Data
public class AuditVO extends Audit {

    private String name;

    private String avatar;

    private String groupName;

    private String parentName;

    private String parentAvatar;

    private String auditUser;

}
