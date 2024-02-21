package com.sorrowphage.czp.controller;


import com.sorrowphage.czp.entity.Group;
import com.sorrowphage.czp.entity.ResultMessage;
import com.sorrowphage.czp.service.GroupService;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("/listpage")
    public ResultMessage groupListPage(@RequestParam("userId") String userId) {
        return groupService.groupListPage(userId);
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
     * 成为groupId族群的第一级
     *
     * @param params
     * @return
     */
    @PostMapping("/bf")
    public ResultMessage becomeFirst(@RequestBody Map<String, String> params) {
        return groupService.becomeFirst(params);
    }

    /**
     * 获取用户管理的族群
     *
     * @param id
     * @return
     */
    @GetMapping("/clan")
    public ResultMessage getClanGroupList(@RequestParam("id") String id) {
        return groupService.getClanGroupList(id);
    }

    /**
     * 修改族群信息
     *
     * @param group
     * @return
     */
    @PostMapping("/update")
    public ResultMessage updateGroupInfo(@RequestBody Group group) {
        return groupService.updateGroupInfo(group);
    }

    /**
     * 获取id族群的所有用户信息需要将创建者标出
     *
     * @param id
     * @return
     */
    @GetMapping("/ul")
    public ResultMessage groupUserList(@RequestParam("id") String id) {
        return groupService.groupUserList(id);
    }


    /**
     * 查询id族群的族群树结构（不包含子级族群）
     *
     * @param id
     * @return
     */
    @GetMapping("/tree")
    public ResultMessage groupTree(@RequestParam("id") String id) {
        return groupService.groupTree(id);
    }

    /**
     * 查询id族群的族群树结构（包含子级族群）
     *
     * @param id
     * @return
     */
    @GetMapping("/hassontree")
    public ResultMessage groupTreeHasSon(@RequestParam("id") String id) {
        return groupService.groupTreeHasSon(id);
    }

    /**
     * 查询id族群的族群树结构(包含父级族群)
     * @param id
     * @return
     */
    @GetMapping("/hasoldtree")
    public ResultMessage groupTreeHasOld(@RequestParam("id") String id) {
        return groupService.groupTreeHasOld(id);
    }

    /**
     * 查询普通用户所能查看的所有族群（和子级族群有关联的族群都应该返回）
     *
     * @param id
     * @return
     */
    @GetMapping("/possess")
    public ResultMessage userInGroups(@RequestParam("id") String id) {
        return groupService.userInGroups(id);
    }

    /**
     * 获取族群人数
     *
     * @param id 族群id
     * @return
     */
    @GetMapping("/nums")
    public ResultMessage groupUserNums(@RequestParam("id") String id) {
        return groupService.groupUserNums(id);
    }

    /**
     * 根据用户推荐族群
     *
     * @param id 用户id
     * @return
     */
    @GetMapping("/recommend")
    public ResultMessage recommendGroup(@RequestParam("id") String id) {
        return groupService.recommendGroup(id);
    }

    /**
     * 获取clan用户信息
     *
     * @param id 族群id
     * @return
     */
    @GetMapping("/clanu")
    public ResultMessage getClanUser(@RequestParam("id") String id) {
        return groupService.getClanUser(id);
    }

    /**
     * 首页族群树需要的数据
     *
     * @param id 用户id
     * @return
     */
    @GetMapping("/home")
    public ResultMessage homeGroup(@RequestParam("id") String id) {
        return groupService.groupHomeData(id);
    }

    /**
     * 查询出用户所在的所以族群
     *
     * @param id 用户id
     * @return
     */
    @GetMapping("/userGroups")
    public ResultMessage getUserGroups(@RequestParam("id") String id) {
        return groupService.userGroups(id);
    }

    /**
     * 根据族群id获取RelationGraph数据
     *
     * @param id 族群id
     * @return
     */
    @GetMapping("/groupRG")
    public ResultMessage getGroupRG(@RequestParam("id") String id) {
        return groupService.groupRG(id);
    }

    /**
     * 模糊查询族群
     *
     * @param q
     * @return
     */
    @GetMapping("/obscure-search")
    public ResultMessage obscureSearchGroup(@RequestParam("q") String q, @RequestParam("pageIndex") String pageIndex, @RequestParam("pageSize") String pageSize) {
        return groupService.obscureSearchGroup(q, pageIndex, pageSize);
    }

    /**
     * 根据id获取族群信息
     * @param id 族群id
     * @return
     */
    @GetMapping("/groupInfo")
    public ResultMessage readGroupInfo(@RequestParam("id") String id) {
        return groupService.readGroupInfo(id);
    }
}
