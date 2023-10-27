package com.sorrowphage.czp.handler;


import com.alibaba.fastjson.JSON;
import com.sorrowphage.czp.entity.ResultMessage;
import com.sorrowphage.czp.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//认证失败
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        String json = JSON.toJSONString(ResultMessage.failure(HttpStatus.UNAUTHORIZED.value(), "认证失败请重新登录"));
        WebUtils.renderString(response,json);
    }
}

