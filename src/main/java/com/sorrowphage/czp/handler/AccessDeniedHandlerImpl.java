package com.sorrowphage.czp.handler;

import com.alibaba.fastjson.JSON;
import com.sorrowphage.czp.entity.ResultMessage;
import com.sorrowphage.czp.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 鉴权失败
 * @author: SorrowPhage
 * @date: 2023/10/27
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        String json = JSON.toJSONString(ResultMessage.failure(HttpStatus.FORBIDDEN.value(), "权限不足"));
        WebUtils.renderString(response,json);

    }
}

