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
 * @since 2024-04-03
 */
@Getter
@Setter
@TableName("chat_list")
public class ChatList implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("own_id")
    private String ownId;

    @TableField("other_id")
    private String otherId;


}
