package com.sorrowphage.czp.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author SorrowPhage
 * @since 2024-03-26
 */
@Getter
@Setter
@TableName("major_history")
public class MajorHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;

    @TableField("group_id")
    private String groupId;

    @TableField("content")
    private String content;

    @TableField("user_id")
    private String userId;

    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @TableField("create_time")
    private LocalDateTime createTime;

    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @TableField("update_time")
    private LocalDateTime updateTime;


}
