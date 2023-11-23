package com.sorrowphage.czp.filter;


import com.sorrowphage.czp.entity.LoginUser;
import com.sorrowphage.czp.utils.JwtUtil;
import com.sorrowphage.czp.utils.RedisCache;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;


/**
 * 认证过滤器
 * @author: SorrowPhage
 * @date: 2023/10/27
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private final RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        //获取token
        String token = request.getHeader("token");
        token = !StringUtils.hasText(token) ? request.getHeader("Sec-WebSocket-Protocol") : token;
        //TODO 这样不是长久之计
        if (!StringUtils.hasText(token) || "/czpUser/login".equals(requestURI) || "/czpUser/sendcode".equals(requestURI) || "/czpUser/register".equals(requestURI)) {
            //放行
            filterChain.doFilter(request, response);
            return;
        }
        //解析token
        String userid;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userid = claims.getSubject();
        } catch (Exception e) {
            throw new RuntimeException("token非法");
        }
        //从redis中获取用户信息
        String redisKey = userid;
        LoginUser loginUser = redisCache.getCacheObject(RedisCache.TOKEN_KEY + redisKey);
        if (Objects.isNull(loginUser)) {
            throw new RuntimeException("用户未登录");
        }
        //存入SecurityContextHolder  ，三个参数会执行super.setAuthenticated(true);
        //获取权限信息封装到Authentication中
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //放行
        filterChain.doFilter(request, response);
    }
}
