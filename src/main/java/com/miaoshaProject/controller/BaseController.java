package com.miaoshaProject.controller;

import com.miaoshaProject.error.BusinessException;
import com.miaoshaProject.error.EmBusinessError;
import com.miaoshaProject.response.CommonReturnType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class BaseController {

    public static final String CONTENT_TYPE_FORMED="appliction/x-www-from-urlencoded";
    //定义exceptionHandler解决未被controller层吸收的exception
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object handlerException(HttpServletRequest request, Exception ex){
        Map<String,Object> reponseData = new HashMap<>();
        if(ex instanceof BusinessException){
            BusinessException businessException = (BusinessException)ex;
            reponseData.put("errCode",businessException.getErrCode());
            reponseData.put("errMsg",businessException.getErrMsg());
        }else {
            reponseData.put("errCode", EmBusinessError.UNKONW_ERROR.getErrCode());
            reponseData.put("errMsg",EmBusinessError.UNKONW_ERROR.getErrMsg());
        }
        return CommonReturnType.create(reponseData,"fail");
    }
}
