package com.sorrowphage.czp.service.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sorrowphage.czp.entity.Audit;
import com.sorrowphage.czp.entity.Group;
import com.sorrowphage.czp.entity.ResultMessage;
import com.sorrowphage.czp.entity.UserGroup;
import com.sorrowphage.czp.entity.dto.GraphDTO;
import com.sorrowphage.czp.entity.dto.GroupDTO;
import com.sorrowphage.czp.entity.vo.*;
import com.sorrowphage.czp.mapper.AuditMapper;
import com.sorrowphage.czp.mapper.CzpUserMapper;
import com.sorrowphage.czp.mapper.GroupMapper;
import com.sorrowphage.czp.mapper.UserGroupMapper;
import com.sorrowphage.czp.service.GroupService;
import com.sorrowphage.czp.utils.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
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

    private final CzpUserMapper czpUserMapper;

    private final AuditMapper auditMapper;


    /**
     * 新建族群
     * @param group 族群数据
     * @return 创建结果
     */
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

    /**
     * 获取用户族群信息列表
     * @param userId
     * @return
     */
    @Override
    public ResultMessage groupList(String userId) {
        List<GroupVO> list = groupMapper.selectGroupList(userId);
        if (list.size() == 0) {
            return ResultMessage.failure("用户没有加入族群");
        }
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
     * @param id 族群id
     * @param userId 用户id
     * @return 族群树结构数据
     */
    @Override
    public ResultMessage getUserList(String id, String userId) {
        UserGroup exist = userGroupMapper.isExist(id, userId);
        if (!Objects.isNull(exist)) {
            return ResultMessage.failure("已在该族群当中");
        }
        // List<CzpUser> list = groupMapper.getUserList(id);
        return this.groupTree(id);
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


    /**
     * 获取族群树结构数据
     * @param id 族群id
     * @return 族群树
     */
    @Override
    public ResultMessage groupTree(String id) {
        //获取用户列表
        List<UserVo> list = groupMapper.groupUserList(id);
        //获取族群创建者
        List<String> creaters = groupMapper.groupCreaterList(id);
        //封装为树型结构
        List<UserVo> groupTree = this.createGroupTree(list, creaters);
        //当创始人不止一个时,树型结构的根节点也不止一个,需要在封装一个根源,这里将源头封装为族群不是用户
        GroupVO resultData = groupMapper.getGroupById(id);
        resultData.setChildren(groupTree);
        return ResultMessage.success(resultData);
    }


    /**
     * 递归查询所有的子级数据
     * @param id 族群id
     * @return 族群树（包含所以子级族群）
     */
    @Override
    public ResultMessage groupTreeHasSon(String id) {
        //获取族群创建者
        List<String> creaters = groupMapper.groupCreaterList(id);
        //获取用户列表
        List<UserVo> list = groupMapper.groupUserList(id);
        //获取子级族群
        List<String> sonGroups = groupMapper.selectSonGroup(id);
        //获取子级族群用户数据
        list.addAll(subgroupList(sonGroups));
        //封装为树型结构
        List<UserVo> groupTree = this.createGroupTree(list, creaters);
        GroupVO resultData = groupMapper.getGroupById(id);
        resultData.setChildren(groupTree);
        return ResultMessage.success(resultData);
    }

    @Override
    public ResultMessage groupTreeHasOld(String id) {
        List<String> createrList = groupMapper.groupCreaterList(id);
        List<UserVo> list = groupMapper.groupUserList(id);
        //根据子级族群获取到父级族群的信息
        QueryWrapper<Group> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(Group::getId, id).select(Group::getParentId);
        List<Group> oldGroup = groupMapper.selectList(wrapper);
        //根据族群的创建者来获取父级的信息
        //递归找出父级的信息
        list.addAll(subgroupListOld(oldGroup.get(0).getParentId(),createrList));
        //组装成一个族群
        return null;
    }

    /**
     * 递归查询包含子级节点的用户树，但是数据库设计的时候，子级族群的创建者在当前族群和子级族群都有数据，
     * 查询时，应该直接剔除掉族群的创建者用户：使用这种方法最高级族群的创建者也会被剔除，所以最高级族群不参与递归
     * @param subgroup 子级族群节点
     * @return 所有的子级族群用户
     */
    public List<UserVo> subgroupList(List<String> subgroup) {
        List<UserVo> resultList = new ArrayList<>();
        if (subgroup.size() > 0) {
            for (String s : subgroup) {
                //查询s族群子级族群
                List<String> sonGroups = groupMapper.selectSonGroup(s);
                if (sonGroups != null && sonGroups.size() > 0) {
                    //有子级族群，先查询子级节点，并递归
                    resultList.addAll(subgroupList(sonGroups));
                }
                //到达这里时，已经没用子级节点，递归结束，查询s族群的用户
                List<UserVo> userList = groupMapper.getUserListEliminateCreate(s);
                resultList.addAll(userList);
            }
        } else {
            return null;
        }
        return resultList;
    }

    //获取父级族群的数据
    public List<UserVo> subgroupListOld(String oldGroup, List<String> createrList) {

        return null;
    }

    @Override
    public ResultMessage userInGroups(String id) {
        //查出该用户所在族群
        List<GroupVO> dataList = groupMapper.selectAllGroupId();
        return ResultMessage.success(dataList);
    }

    @Override
    public ResultMessage groupUserNums(String id) {
        Integer resultData = groupMapper.groupUserNums(id);
        return ResultMessage.success(resultData);
    }

    @Override
    public ResultMessage recommendGroup(String id) {
        List<GroupVO> dataList = groupMapper.recommendGroup();
        return ResultMessage.success(dataList);
    }

    @Override
    public ResultMessage getClanUser(String id) {
        List<UserVo> data = groupMapper.getClanUser(id);
        return ResultMessage.success(data);
    }


    /**
     * 这个方法，是将用户所在的两个具有父子关系的族群数据拼接到一起返回
     * @param id 用户id
     * @return RelationGraph数据
     */
    @Override
    public ResultMessage groupHomeData(String id) {
        //查询出用户所在的族群（按照设计思路来说，一个人最多只会存在两个族群当中，并且有一个族群是另一个的父级族群，最多只需要将这两个的族群数据拼接成一个临时的族群树）
        List<UserGroup> groups = groupMapper.selectUserHasGroup(id);
        List<String> parents = groups.stream().map(UserGroup::getParentId).collect(Collectors.toList());
        Optional<String> optional = groups.stream().map(UserGroup::getGroupId).filter(parents::contains).findFirst();
        Optional<String> optional2 = groups.stream().map(UserGroup::getGroupId).filter(groupId -> !parents.contains(groupId)).findFirst();
        String parentGroup = null;
        String sonGroup = null;
        if (optional.isPresent()) {
            parentGroup = optional.get();
        }
        if (optional2.isPresent()) {
            sonGroup = optional2.get();
        }
        List<String> createrList = groupMapper.groupCreaterList(parentGroup);
        List<UserVo> list = groupMapper.groupUserList(parentGroup);
        list.addAll(groupMapper.getUserListEliminateCreate(sonGroup));
        List<UserVo> groupTree = this.createGroupTree(list, createrList);
        GroupVO resultData = groupMapper.getGroupById(parentGroup);
        resultData.setChildren(groupTree);
        //转换
        RelationGraphVO relationGraphVO = treeCoversionRelationGraphData(resultData);
        relationGraphVO.setRootId(id);
        return ResultMessage.success(relationGraphVO);
    }

    /**
     * 将族群树转换成RelationGraph数据
     * @param groupVO 族群树数据
     * @return  RelationGraphVO
     */
    public RelationGraphVO treeCoversionRelationGraphData(GroupVO groupVO) {
        List<RelationGraphLine> lines = new ArrayList<>();
        List<RelationGraphNode> nodes = new ArrayList<>();
        nodes.add(new RelationGraphNode(groupVO.getId(), groupVO.getGroupName()));
        if (!Objects.isNull(groupVO.getChildren())) {
            for (UserVo child : groupVO.getChildren()) {
                if (!Objects.isNull(child.getChildren())) {
                    lines.addAll(recursionRelationLine(child, child.getChildren()));
                    nodes.addAll(recursionRelationNode(child.getChildren()));
                }
                RelationGraphLine relationGraphLine = new RelationGraphLine(groupVO.getId(), child.getId());
                lines.add(relationGraphLine);
                RelationGraphNode relationGraphNode = new RelationGraphNode(child.getId(), child.getName(),child);
                nodes.add(relationGraphNode);
            }
        }
        return new RelationGraphVO(nodes,lines);
    }

    /**
     * 拼接Line数据
     * @param userVo 当前用户
     * @param userVoList 当前用户的子级数据
     * @return List<RelationGraphLine>
     */
    public List<RelationGraphLine> recursionRelationLine(UserVo userVo, List<UserVo> userVoList) {
        List<RelationGraphLine> lines = new ArrayList<>();
        for (UserVo child : userVoList) {
            if (!Objects.isNull(child.getChildren())) {
                lines.addAll(recursionRelationLine(child, child.getChildren()));
            }
            RelationGraphLine relationGraphLine = new RelationGraphLine(userVo.getId(), child.getId());
            lines.add(relationGraphLine);
        }
        return lines;
    }

    /**
     * 拼接node数据
     * @param userVoList 用户数据
     * @return List<RelationGraphNode>
     */
    public List<RelationGraphNode> recursionRelationNode(List<UserVo> userVoList) {
        List<RelationGraphNode> nodes = new ArrayList<>();
        for (UserVo child : userVoList) {
            if (!Objects.isNull(child.getChildren())) {
                nodes.addAll(recursionRelationNode(child.getChildren()));
            }
            RelationGraphNode relationGraphNode = new RelationGraphNode(child.getId(), child.getName(),child);
            nodes.add(relationGraphNode);
        }
        return nodes;
    }


    /**
     * 返回用户所拥有的族群数据
     * @param id 用户id
     * @return  List<Group>
     */
    @Override
    public ResultMessage userGroups(String id) {
        List<Group> groups = groupMapper.selectUserGroup(id);
        return ResultMessage.success(groups);
    }

    /**
     * 将族群树数据转换成RelationGraph数据返回（
     * @param id 族群id
     * @return RelationGraphVO
     */
    @Override
    public ResultMessage groupRG(String id) {
        //获取用户列表
        List<UserVo> list = groupMapper.groupUserList(id);
        //获取族群创建者
        List<String> creaters = groupMapper.groupCreaterList(id);
        //封装为树型结构
        List<UserVo> groupTree = this.createGroupTree(list, creaters);
        //当创始人不止一个时,树型结构的根节点也不止一个,需要在封装一个根源,这里将源头封装为族群不是用户
        GroupVO resultData = groupMapper.getGroupById(id);
        resultData.setChildren(groupTree);
        //转换
        RelationGraphVO relationGraphVO = treeCoversionRelationGraphData(resultData);
        relationGraphVO.setRootId(id);
        return ResultMessage.success(relationGraphVO);
    }

    @Override
    public ResultMessage obscureSearchGroup(String q,String pageIndex,String pageSize) {
        List<GroupVO> list = groupMapper.searchGroupList(q);
        PageHelper.startPage(Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
        return ResultMessage.success(new PageInfo<>(list));
    }

    @Override
    public ResultMessage readGroupInfo(String id) {
        GroupVO group = groupMapper.readGroupInfo(id);
        return ResultMessage.success(group);
    }

    @Override
    public ResultMessage groupListPage(GroupDTO groupDTO) {
        PageHelper.startPage(groupDTO.getPageNum(), groupDTO.getPageSize());
        List<GroupVO> dataList = groupMapper.selectGroupListByName(groupDTO.getGroupName());
        return ResultMessage.success(new PageInfo<>(dataList));
    }


    @Override
    public ResultMessage groupGraphData(GraphDTO graphDTO) {
        GraphVO graphVO = new GraphVO();
        List<AGePieCharts> ageList = czpUserMapper.selectAgePieChatrsList(graphDTO);
        List<BirthYear> birthYearList = czpUserMapper.selectBirthAndDeathYearChartsList(graphDTO);
        List<AGePieCharts> maleToFemale = czpUserMapper.selectMaleFemale(graphDTO);
        graphVO.setAgePieChartsList(ageList);
        graphVO.setBirthAndDeathYearList(birthYearList);
        graphVO.setMaleToFemaleList(maleToFemale);
        return ResultMessage.success(graphVO);
    }

    @Override
    public ResultMessage deleteGroup(Group group) {
        //删除族群，删除加入族群的用户，删除申请记录
        QueryWrapper<Group> groupQueryWrapper = new QueryWrapper<>();
        groupQueryWrapper.lambda().eq(Group::getId, group.getId());
        groupMapper.delete(groupQueryWrapper);

        QueryWrapper<UserGroup> userGroupQueryWrapper = new QueryWrapper<>();
        userGroupQueryWrapper.lambda().eq(UserGroup::getGroupId, group.getId());
        userGroupMapper.delete(userGroupQueryWrapper);

        QueryWrapper<Audit> auditQueryWrapper = new QueryWrapper<>();
        auditQueryWrapper.lambda().eq(Audit::getGroupId, group.getId());
        auditMapper.delete(auditQueryWrapper);

        return ResultMessage.success("删除成功");
    }

    @Override
    public ResultMessage groupLargeGraphData(GraphDTO graphDTO) {
        LargeGraphVO largeGraphVO = new LargeGraphVO();
        List<GroupPeopleNumer> groupPeopleNumers = groupMapper.selectAllGroup();
        Integer groupNum = groupMapper.selectGroupNum();
        Integer groupPeopleNum = groupMapper.selectGroupPeopleNum();
        largeGraphVO.setGroupPeopleNumerList(groupPeopleNumers);
        largeGraphVO.setGroupNum(groupNum);
        largeGraphVO.setGroupPeopleNum(groupPeopleNum);
        return ResultMessage.success(largeGraphVO);
    }


}
