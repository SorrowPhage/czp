package com.sorrowphage.czp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sorrowphage.czp.entity.CzpUser;
import com.sorrowphage.czp.entity.Email;
import com.sorrowphage.czp.entity.ResultMessage;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author SorrowPhage
 * @since 2023-09-11
 */
public interface CzpUserService extends IService<CzpUser> {

    /**
     * 登录接口 需要匿名访问
     * @param user 登录用户信息（id，password）
     * @return token
     */
    ResultMessage login(CzpUser user);

    /**
     * 退出 请求体携带token
     * @return 退出结果
     */
    ResultMessage logout();

    /**
     * 注册账号
     * @param czpUser 用户信息（应该有：name,verCode,password,email）
     * @return 注册结果
     */
    ResultMessage register(CzpUser czpUser);

    /**
     * 发送验证码
     * @param email 邮箱信息(tos,subject)
     * @return 发送结果
     */
    ResultMessage sendCode(Email email);
}
