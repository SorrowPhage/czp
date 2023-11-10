package com.sorrowphage.czp.service.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sorrowphage.czp.entity.CzpUser;
import com.sorrowphage.czp.entity.LoginUser;
import com.sorrowphage.czp.mapper.CzpMenuMapper;
import com.sorrowphage.czp.mapper.CzpUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author: SorrowPhage
 * @date: 2023/10/18
 */
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final CzpUserMapper czpUserMapper;

    private final CzpMenuMapper czpMenuMapper;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        //根据id查出用户信息
        LambdaQueryWrapper<CzpUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CzpUser::getId, id);
        CzpUser czpUser = czpUserMapper.selectOne(wrapper);
        //如果没有查询到
        if (Objects.isNull(czpUser)) {
            // throw new RuntimeException("账号或密码错误");
            return null;
        }
        //查询用户权限
        List<String> permissions = czpMenuMapper.menuListByUserId(id);
        //封装成UserDetails返回
        return new LoginUser(czpUser, permissions);
    }


}
