package com.sorrowphage.czp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author SorrowPhage
 * @since 2023-11-06
 */
@Getter
@Setter
@TableName("czp_group")
public class Group implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private String id;

    @TableField("group_name")
    private String groupName;

    @TableField("des")
    private String des;

    @TableField("area")
    private String area;

    @TableField("parent_id")
    private String parentId;

    @TableField("create_time")
    private String createTime;

    @TableField("clan_elder")
    private String clanElder;

    @TableField(exist = false)
    private String createUser;


}
