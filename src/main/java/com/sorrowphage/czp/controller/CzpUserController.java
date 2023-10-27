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

    private final CzpUserService czpUserService;


    @PostMapping("/login")
    public ResultMessage login(@RequestBody CzpUser czpUser) {
        return czpUserService.login(czpUser);
    }

    @PostMapping("/logout")
    public ResultMessage logout() {
        return czpUserService.logout();
    }

    @PostMapping("/register")
    public ResultMessage register(@RequestBody CzpUser czpUser) {
        return czpUserService.register(czpUser);
    }

    @PostMapping("/sendcode")
    public ResultMessage sendCode(@RequestBody Email email) {
        return czpUserService.sendCode(email);
    }

    @PostMapping("/test")
    public ResultMessage test() {
        return ResultMessage.success("2");
    }

}
