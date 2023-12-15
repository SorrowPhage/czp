package com.sorrowphage.czp.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author SorrowPhage
 * @since 2023-12-07
 */
@Getter
@Setter
@TableName("czp_user_chat_list")
@NoArgsConstructor
@AllArgsConstructor
public class CzpUserChatList implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("own_id")
    private String ownId;

    @TableField("other_id")
    private String otherId;

    /**
     * 1表示还在，0表示隐藏
     */
    @TableField("status")
    private Integer status;

    @TableField("create_time")
    private String createTime;

    @TableField("update_time")
    private String updateTime;


}
