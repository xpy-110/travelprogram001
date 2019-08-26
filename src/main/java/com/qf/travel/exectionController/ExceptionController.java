package com.qf.travel.exectionController;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

public class ExceptionController {
    @ExceptionHandler(value = UnauthorizedException.class)//处理访问方法时权限不足问题
    public String handlerException(HttpServletRequest request, Exception ex){

        return "unauth";
    }
}
