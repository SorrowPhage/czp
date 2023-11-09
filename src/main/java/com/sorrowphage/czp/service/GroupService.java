package com.sorrowphage.czp.service;

import com.sorrowphage.czp.entity.Group;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sorrowphage.czp.entity.ResultMessage;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author SorrowPhage
 * @since 2023-11-06
 */
public interface GroupService extends IService<Group> {

    ResultMessage createGroup(Group group);

    ResultMessage groupList(String userId);

    ResultMessage createList(String id);

    ResultMessage searchGroup(String id);

    ResultMessage getUserList(String id,String userId);

    ResultMessage becomeFirst(Map<String, String> params);

}
