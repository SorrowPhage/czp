package com.sorrowphage.czp.entity.vo;

import lombok.Data;

/**
 * @author: SorrowPhage
 * @date: 2023/12/18
 */
@Data
public class CzpGroupVO {
    //族群id
    private String id;
    //名称
    private String groupName;
    //描述
    private String des;
    //地区
    private String area;
    //父级族群id
    private String parentId;
    //创建时间
    private String createTime;
    //族群管理员id
    private String clanElder;
    //父级族群名称
    private String parentName;
    //管理员名称
    private String name;
    //管理员头像
    private String avatar;

    private String num;

}
