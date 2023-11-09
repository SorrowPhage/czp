package com.sorrowphage.czp.service.serviceimpl;

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
        return ResultMessage.success(list);
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



}
