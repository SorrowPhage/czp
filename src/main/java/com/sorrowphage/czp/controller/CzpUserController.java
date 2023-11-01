package com.sorrowphage.czp.controller;


import com.sorrowphage.czp.entity.CzpUser;
import com.sorrowphage.czp.entity.Email;
import com.sorrowphage.czp.entity.ResultMessage;
import com.sorrowphage.czp.service.CzpUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author SorrowPhage
 * @since 2023-09-11
 */
@RestController
@RequestMapping("/czpUser")
@RequiredArgsConstructor
public class CzpUserController {

    /**
     * 用户
     */
    private final CzpUserService czpUserService;


    /**
     * 登录接口 需要匿名访问
     * @param czpUser 登录用户信息（id，password）
     * @return token
     */
    @PostMapping("/login")
    public ResultMessage login(@RequestBody CzpUser czpUser) {
        return czpUserService.login(czpUser);
    }

    /**
     * 退出 请求体携带token
     * @return 退出结果
     */
    @PostMapping("/logout")
    public ResultMessage logout() {
        return czpUserService.logout();
    }

    /**
     * 注册账号
     * @param czpUser 用户信息（应该有：name,verCode,password,email）
     * @return 注册结果
     */
    @PostMapping("/register")
    public ResultMessage register(@RequestBody CzpUser czpUser) {
        return czpUserService.register(czpUser);
    }

    /**
     * 发送验证码
     * @param email 邮箱信息(tos,subject)
     * @return 发送结果
     */
    @PostMapping("/sendcode")
    public ResultMessage sendCode(@RequestBody Email email) {
        return czpUserService.sendCode(email);
    }

    @PostMapping("/test")
    public ResultMessage test() {
        return ResultMessage.success("2");
    }

}
