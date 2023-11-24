package com.sorrowphage.czp.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import com.sorrowphage.czp.entity.vo.UserVo;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *
 * </p>
 *
 * @author SorrowPhage
 * @since 2023-11-22
 */
@Getter
@Setter
@TableName("czp_message")
public class CzpMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private String id;

    @TableField("from_id")
    private String fromId;

    @TableField("to_id")
    private String toId;

    @TableField("type")
    private String type;

    @TableField("content")
    private String content;

    @TableField("send_time")
    private String sendTime;

    @TableField("status")
    private Integer status;

    @TableField("create_time")
    private String createTime;

    @TableField(exist = false)
    private UserVo user;
}
