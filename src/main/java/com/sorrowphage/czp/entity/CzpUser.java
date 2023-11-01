package com.sorrowphage.czp.entity;

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
 * @since 2023-09-11
 */
@Getter
@Setter
@TableName("czp_user")
public class CzpUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private String id;

    @TableField("name")
    private String name;

    @TableField("sex")
    private String sex;

    @TableField("area_id")
    private String areaId;

    @TableField("parent_id")
    private String parentId;

    @TableField("root_id")
    private String rootId;

    @TableField("birthday")
    private String birthday;

    @TableField("mate_id")
    private String mateId;

    @TableField("small_group")
    private String smallGroup;

    @TableField("avatar")
    private String avatar;

    @TableField("password")
    private String password;

    @TableField("status")
    private String status;

    @TableField("email")
    private String email;

    @TableField("generation")
    private String generation;

    @TableField("generation_rank")
    private String generationRank;


    @TableField(exist = false)
    private String verCode;

    @TableField(exist = false)
    private String token;

}
