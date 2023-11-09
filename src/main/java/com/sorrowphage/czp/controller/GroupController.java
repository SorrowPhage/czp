package com.sorrowphage.czp.controller;


import com.sorrowphage.czp.entity.Group;
import com.sorrowphage.czp.entity.ResultMessage;
import com.sorrowphage.czp.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author SorrowPhage
 * @since 2023-11-06
 */
@RestController
@RequestMapping("/group")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    /**
     * 创建族群
     *
     * @param group
     * @return
     */
    @PostMapping("/create")
    public ResultMessage createGroup(@RequestBody Group group) {
        return groupService.createGroup(group);
    }

    /**
     * 获取userId用户的所有家族列表
     *
     * @param userId
     * @return
     */
    @GetMapping("/list")
    public ResultMessage groupList(@RequestParam("userId") String userId) {
        return groupService.groupList(userId);
    }

    /**
     * 获取id族群的所有创建者的信息
     *
     * @param id
     * @return
     */
    @GetMapping("/createList")
    public ResultMessage groupCreateList(@RequestParam("id") String id) {
        return groupService.createList(id);
    }

    @GetMapping("/search")
    public ResultMessage getGroupList(@RequestParam("id") String id) {
        return groupService.searchGroup(id);
    }

    @GetMapping("/userList")
    public ResultMessage getUserList(@RequestParam("id") String id, @RequestParam("userId") String userId) {
        return groupService.getUserList(id, userId);
    }


    /**
     * TODO 成为groupId族群的第一级
     * @param params
     * @return
     */
    @PostMapping("/bf")
    public ResultMessage becomeFirst(@RequestBody Map<String, String> params) {
        return groupService.becomeFirst(params);
    }
}
