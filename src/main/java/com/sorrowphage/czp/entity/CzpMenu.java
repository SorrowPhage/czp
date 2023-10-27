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
 * @since 2023-10-27
 */
@Getter
@Setter
@TableName("czp_menu")
public class CzpMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private String id;

    @TableField("menu_name")
    private String menuName;

    @TableField("path")
    private String path;

    @TableField("component")
    private String component;

    @TableField("visible")
    private String visible;

    @TableField("status")
    private String status;

    @TableField("perms")
    private String perms;

    @TableField("icon")
    private String icon;

    @TableField("create_time")
    private String createTime;

    @TableField("update_time")
    private String updateTime;


}
