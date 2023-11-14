package com.sorrowphage.czp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sorrowphage.czp.entity.CzpUser;
import com.sorrowphage.czp.entity.Group;
import com.sorrowphage.czp.entity.vo.GroupVO;
import com.sorrowphage.czp.entity.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;

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
}
