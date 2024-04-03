package com.sorrowphage.czp.entity.vo;

import lombok.Data;

/**
 * @author: SorrowPhage
 * @date: 2023/11/22
 */
@Data
public class MessageVO {

    private String id;

    private String fromId;

    private String toId;

    private String type;

    private String content;

    private String sendTime;

    private Integer status;

    private String createTime;

    private UserVo user;

    private String avatar;

    private String name;


}
