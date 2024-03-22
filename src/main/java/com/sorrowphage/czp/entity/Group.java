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

    //管理员
    @TableField("clan_elder")
    private String clanElder;

    // //总人数
    // @TableField("people_total")
    // private Integer peopleTotal;
    // //现存人数
    // @TableField("living_people_total")
    // private Integer livingPeopleTotal;

    @TableField(exist = false)
    private String createUser;


}
