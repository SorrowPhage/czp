package com.sorrowphage.czp.controller;


import com.sorrowphage.czp.entity.CzpUser;
import com.sorrowphage.czp.entity.Email;
import com.sorrowphage.czp.entity.LoginUser;
import com.sorrowphage.czp.entity.ResultMessage;
import com.sorrowphage.czp.service.CzpUserService;
import com.sorrowphage.czp.utils.JwtUtil;
import com.sorrowphage.czp.utils.RedisCache;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 前端控制器   用户
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

    private final RedisCache redisCache;


    /**
     * 登录接口 需要匿名访问
     *
     * @param czpUser 登录用户信息（id，password）
     * @return token
     */
    @PostMapping("/login")
    public ResultMessage login(@RequestBody CzpUser czpUser) {
        return czpUserService.login(czpUser);
    }

    /**
     * 退出 请求体携带token
     *
     * @return 退出结果
     */
    @PostMapping("/logout")
    public ResultMessage logout() {
        return czpUserService.logout();
    }

    /**
     * 注册账号
     *
     * @param czpUser 用户信息（应该有：name,verCode,password,email）
     * @return 注册结果
     */
    @PostMapping("/register")
    public ResultMessage register(@RequestBody CzpUser czpUser) {
        return czpUserService.register(czpUser);
    }

    /**
     * 发送验证码
     *
     * @param email 邮箱信息(tos,subject)
     * @return 发送结果
     */
    @PostMapping("/sendcode")
    public ResultMessage sendCode(@RequestBody Email email) {
        return czpUserService.sendCode(email);
    }

    /**
     * 获取用户信息
     *
     * @param id 用户id
     * @return
     */
    @GetMapping("/userinfo")
    public ResultMessage getUserInfo(@RequestParam("id") String id) {
        return czpUserService.userInfo(id);
    }

    /**
     * 修改头像
     *
     * @param file
     * @param id
     * @return
     */
    @PostMapping("/avatar")
    public ResultMessage setAvatar(@RequestParam("file") MultipartFile file, @RequestParam("id") String id) {
        return czpUserService.setAvatar(file, id);
    }


    /**
     * 根据token返回用户信息
     *
     * @param request
     * @return
     */
    @GetMapping("/token/userinfo")
    public ResultMessage getUserInfoByToken(HttpServletRequest request) {
        //获取token
        String token = request.getHeader("token");
        Claims claims = null;
        try {
            claims = JwtUtil.parseJWT(token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String userid = claims.getSubject();

        return czpUserService.userInfo(userid);
    }


    /**
     * 修改用户信息
     *
     * @param czpUser
     * @return
     */
    @PostMapping("/update")
    public ResultMessage updateUserInfo(@RequestBody CzpUser czpUser) {
        return czpUserService.updateUserInfo(czpUser);
    }

    /**
     * 修改密码
     *
     * @param params
     * @return
     */
    @PostMapping("/updatePassword")
    public ResultMessage updatePassword(@RequestBody Map<String, String> params) {
        return czpUserService.updatePassword(params);
    }

    /**
     * 搜索用户pageIndex: this.currentPage, pageSize: this.pageSize
     *
     * @param id
     * @return
     */
    @GetMapping("/search")
    public ResultMessage searchUserList(@RequestParam("id") String id, @RequestParam("pageIndex") String pageIndex, @RequestParam("pageSize") String pageSize) {
        return czpUserService.searchUser(id, pageIndex, pageSize);
    }

    /**
     * 获取用户信息
     * @param id
     * @return
     */
    @GetMapping("/su")
    public ResultMessage searchUser(@RequestParam("id") String id) {
        return czpUserService.getUser(id);
    }
}
