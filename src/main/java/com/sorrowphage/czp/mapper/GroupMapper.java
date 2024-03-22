package com.sorrowphage.czp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sorrowphage.czp.entity.CzpUser;
import com.sorrowphage.czp.entity.Group;
import com.sorrowphage.czp.entity.UserGroup;
import com.sorrowphage.czp.entity.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author SorrowPhage
 * @since 2023-11-06
 */
@Mapper
public interface GroupMapper extends BaseMapper<Group> {

    void insertDataToUserGroup(Map<String, String> param);

    /**
     *获取用户族群信息列表
     * @param userId 用户id
     * @return 族群信息列表
     */
    List<GroupVO> selectGroupList(String userId);

    /**
     * 查询id族群的第一级用户
     * @param id
     * @return
     */
    List<UserVo> createList(String id);

    /**
     * 搜索族群，
     * @param id 族群id或族群名称（模糊查询）
     * @return
     */
    List<GroupVO> searchGroupList(String id);

    List<CzpUser> getUserList(String id);


    /**
     * 获取用户列表
     * @param id 族群id
     * @return
     */
    List<UserVo> groupUserList(String id);

    List<String> groupCreaterList(String id);

    /**
     * 根据id获取族群
     * @param id
     * @return
     */
    GroupVO getGroupById(String id);

    /**
     * 获取s族群的子级族群
     * @param s
     * @return
     */
    List<String> selectSonGroup(String s);

    /**
     * 获取族群用户，不包含创建者
     * @param s
     * @return
     */
    List<UserVo> getUserListEliminateCreate(String s);

    List<GroupVO> selectAllGroupId();


    Integer groupUserNums(String id);

    List<GroupVO> recommendGroup();

    List<UserVo> getClanUser(String id);


    List<UserGroup> selectUserHasGroup(String id);

    /**
     * 和上面的方法差不多，返回的值不同
     * @param id
     * @return
     */
    List<Group> selectUserGroup(String id);

    GroupVO readGroupInfo(String id);


    /**
     * 查询族群信息--详细信息版
     * @param id 族群id
     * @return CzpGroupVo
     */
    CzpGroupVO czpGroupInfo(@Param("id") String id);

    List<GroupVO> selectGroupListByName(String groupName);

    /**
     * 统计各个族群的存活人数与总人数
     * @return
     */
    List<GroupPeopleNumer> selectAllGroup();

    /**
     * 统计族群数量
     * @return
     */
    Integer selectGroupNum();

    /**
     * 统计族群总人数
     * @return
     */
    Integer selectGroupPeopleNum();

}
