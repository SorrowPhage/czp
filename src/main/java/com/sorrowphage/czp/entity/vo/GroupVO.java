package com.sorrowphage.czp.entity.vo;

import com.sorrowphage.czp.entity.CzpUser;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: SorrowPhage
 * @date: 2023/11/7
 */
@Data
public class GroupVO {
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
    private CzpUser clanElderUser;
    private String createUser;
    //用户
    private List<UserVo> children = new ArrayList<>();
    //族群人数
    private Integer peopleTotal;
    private Integer livingPeopleTotal;
    //父级族群名称
    private String parentName;


}
