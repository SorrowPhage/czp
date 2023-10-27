package com.sorrowphage.czp.service.serviceimpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sorrowphage.czp.entity.CzpUser;
import com.sorrowphage.czp.entity.Email;
import com.sorrowphage.czp.entity.LoginUser;
import com.sorrowphage.czp.entity.ResultMessage;
import com.sorrowphage.czp.mapper.CzpUserMapper;
import com.sorrowphage.czp.service.CzpUserService;
import com.sorrowphage.czp.utils.DigitGeneratorUtils;
import com.sorrowphage.czp.utils.JwtUtil;
import com.sorrowphage.czp.utils.RedisCache;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author SorrowPhage
 * @since 2023-09-11
 */
@Service
@RequiredArgsConstructor
public class CzpUserServiceImpl extends ServiceImpl<CzpUserMapper, CzpUser> implements CzpUserService {

    private final AuthenticationManager authenticationManager;

    private final RedisCache redisCache;

    private final JavaMailSender javaMailSender;

    private final PasswordEncoder passwordEncoder;

    @Value("${spring.mail.username}")
    private String username;

    @Override
    public ResultMessage login(CzpUser user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getId(), user.getPassword());
        Authentication authenticate;

        try {
            authenticate = authenticationManager.authenticate(authenticationToken);
        } catch (BadCredentialsException e) {
            return ResultMessage.failure("账号或密码不对");
        }

        if (Objects.isNull(authenticate)) {
            return ResultMessage.failure("账号或密码不对");
        }
        //使用id生成token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        if (StringUtils.hasText(loginUser.getCzpUser().getStatus()) && "1".equals(loginUser.getCzpUser().getStatus())) {
            String userId = loginUser.getCzpUser().getId();
            String jwt = JwtUtil.createJWT(userId);
            //authenticate存入redis
            redisCache.setCacheObject(RedisCache.TOKEN_KEY + userId, loginUser);
            //token返回前端
            HashMap<String, String> map = new HashMap<>();
            map.put("token", jwt);
            return ResultMessage.success("登录成功", map);
        } else {
            return ResultMessage.failure("该账号已禁用");
        }
    }

    @Override
    public ResultMessage logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        redisCache.deleteObject("login" + loginUser.getCzpUser().getId());
        return ResultMessage.success("退出成功");
    }

    /**
     * 注册账号
     * @param czpUser 用户信息（应该有：name,verCode,password,email）
     * @return 注册结果
     */
    @Override
    public ResultMessage register(CzpUser czpUser) {
        //从 redis 中取出验证码
        String verCode = redisCache.getCacheObject(RedisCache.VER_CODE_KEY + czpUser.getEmail());
        if (!czpUser.getVerCode().equals(verCode)) {
            return ResultMessage.failure("验证码不一致");
        } else {
            //生成账号
            String id = DigitGeneratorUtils.generateAccount();
            while (this.getById(id) != null) {
                id = DigitGeneratorUtils.generateAccount();
            }
            czpUser.setId(id);
            //密码加密
            czpUser.setPassword(passwordEncoder.encode(czpUser.getPassword()));
            boolean save = this.save(czpUser);
            if (save) {
                return ResultMessage.success("注册成功", id);
            } else {
                return ResultMessage.failure("注册失败，未知原因，亲联系后台");
            }
        }
    }

    @Override
    public ResultMessage sendCode(Email email) {
        //生成验证码
        String verCode = DigitGeneratorUtils.generateVerCode();
        //设置邮件发送内容
        email.setContent("验证码是:" + verCode);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(username);
        message.setTo(email.getTos());
        message.setSubject(email.getSubject());
        message.setText(email.getContent());
        javaMailSender.send(message);

        //将验证码存到 redis 中，并设置5分钟的过期时间
        redisCache.setCacheObject(RedisCache.VER_CODE_KEY + email.getTos(), verCode, 5, TimeUnit.MINUTES);
        return ResultMessage.success("验证码发送成功");
    }

}
