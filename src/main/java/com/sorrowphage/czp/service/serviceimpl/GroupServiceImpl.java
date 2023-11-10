package com.sorrowphage.czp.service.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sorrowphage.czp.entity.CzpUser;
import com.sorrowphage.czp.entity.Group;
import com.sorrowphage.czp.entity.ResultMessage;
import com.sorrowphage.czp.entity.UserGroup;
import com.sorrowphage.czp.entity.vo.GroupVO;
import com.sorrowphage.czp.entity.vo.UserVo;
import com.sorrowphage.czp.mapper.GroupMapper;
import com.sorrowphage.czp.mapper.UserGroupMapper;
import com.sorrowphage.czp.service.GroupService;
import com.sorrowphage.czp.utils.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author SorrowPhage
 * @since 2023-11-06
 */
@Service
@RequiredArgsConstructor
public class GroupServiceImpl extends ServiceImpl<GroupMapper, Group> implements GroupService {

    private final GroupMapper groupMapper;

    private final UserGroupMapper userGroupMapper;

    @Override
    public ResultMessage createGroup(Group group) {
        //判断该用户是否已创建过族群，若创建过族群则，不能新建族群
        // LambdaUpdateWrapper<Group> wrapper = new LambdaUpdateWrapper<>();
        // wrapper.eq(Group::getClanElder, group.getClanElder());
        // List<Group> list = this.list(wrapper);
        // if (list.size() > 2) {
        //     return ResultMessage.failure("创建失败，超过最大管理数量");
        // }
        //新建族群
        boolean save = this.save(group);
        if (save) {
            return ResultMessage.success("创建成功");
        } else {
            return ResultMessage.failure("创建失败");
        }
    }

    @Override
    public ResultMessage groupList(String userId) {
        List<GroupVO> list = groupMapper.selectGroupList(userId);
        return ResultMessage.success("请求成功", list);
    }

    @Override
    public ResultMessage createList(String id) {
        List<UserVo> list = groupMapper.createList(id);
        return ResultMessage.success(list);
    }

    @Override
    public ResultMessage searchGroup(String id) {
        List<GroupVO> list = groupMapper.searchGroupList(id);
        return ResultMessage.success(list);
    }

    /**
     * 根据族群id查询用户列表,需要判断用户是否已在该族群，若用户已在，则不查询
     *
     * @param id
     * @return
     */
    @Override
    public ResultMessage getUserList(String id, String userId) {
        UserGroup exist = userGroupMapper.isExist(id, userId);
        if (!Objects.isNull(exist)) {
            return ResultMessage.failure("已在该族群当中");
        }

        List<CzpUser> list = groupMapper.getUserList(id);
        return ResultMessage.success(list);
    }

    @Override
    public ResultMessage becomeFirst(Map<String, String> params) {
        params.put("createTime", DateUtil.getSystemDateTimeString());
        params.put("updateTime", DateUtil.getSystemDateTimeString());
        groupMapper.insertDataToUserGroup(params);
        return ResultMessage.success("加入成功");
    }

    @Override
    public ResultMessage getClanGroupList(String id) {
        LambdaUpdateWrapper<Group> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Group::getClanElder, id);
        List<Group> list = this.list(wrapper);
        return ResultMessage.success(list);
    }

    @Override
    public ResultMessage updateGroupInfo(Group group) {
        //TODO 判断父级id是否可以当作父级id,
        //判断父级族群是否存在,父级族群不能是自己的,父级族群不能在自己的子族群节点下面,族群当中需要有第一节点也在父级族群当中
        LambdaUpdateWrapper<Group> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Group::getId, group.getParentId());
        List<Group> list = this.list(wrapper);
        if (list == null || list.size() == 0) {
            return ResultMessage.failure("父级族群不存在，请确认");
        }
        boolean update = this.saveOrUpdate(group);
        if (update) {
            return ResultMessage.success("修改成功");
        } else {
            return ResultMessage.failure("修改失败");
        }
    }

    @Override
    public ResultMessage groupUserList(String id) {
        List<UserVo> list = groupMapper.groupUserList(id);
        return ResultMessage.success(list);
    }


    /**
     * 将用户列表转换为树型结构
     * @param userVoList 用户列表
     * @param creates 根节点
     * @return 树型结构
     */
    public List<UserVo> createGroupTree(List<UserVo> userVoList, List<String> creates) {
        //过滤根节点
        Map<String, List<UserVo>> userMap = userVoList.stream().filter(node -> !creates.contains(node.getId())).collect(Collectors.groupingBy(UserVo::getParentId));
        //为每个节点找到子节点
        userVoList.forEach(node -> node.setChildren(userMap.get(node.getId())));
        //只留下根节点
        return userVoList.stream().filter(node -> creates.contains(node.getId())).collect(Collectors.toList());
    }

    @Override
    public ResultMessage groupTree(String id) {
        //获取用户列表
        List<UserVo> list = groupMapper.groupUserList(id);
        //获取族群创建者
        List<String> creaters = groupMapper.groupCreaterList(id);
        //封装为树型结构
        List<UserVo> groupTree = this.createGroupTree(list, creaters);
        //再创始人不止一个时,树型结构的根节点也不止一个,需要在封装一个根源
        GroupVO resultData = new GroupVO();
        //TODO 这个名称是族群名称,
        resultData.setGroupName(groupMapper.searchGroupList(id).get(0).getName());
        resultData.setChildren(groupTree);
        return ResultMessage.success(resultData);
    }


}
