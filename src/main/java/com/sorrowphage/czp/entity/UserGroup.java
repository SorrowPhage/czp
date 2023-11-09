package com.sorrowphage.czp.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author SorrowPhage
 * @since 2023-11-08
 */
@Getter
@Setter
@TableName("user_group")
public class UserGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("user_id")
    private String userId;

    @TableField("group_id")
    private String groupId;

    @TableField("parent_id")
    private String parentId;

    @TableField("create_time")
    private String createTime;

    @TableField("update_time")
    private String updateTime;


}
