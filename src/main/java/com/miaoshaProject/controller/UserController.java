package com.miaoshaProject.controller;

import com.miaoshaProject.controller.viewObject.UserVO;
import com.miaoshaProject.error.BusinessException;
import com.miaoshaProject.error.EmBusinessError;
import com.miaoshaProject.response.CommonReturnType;
import com.miaoshaProject.service.UserService;
import com.miaoshaProject.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.support.HttpRequestHandlerServlet;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Handler;

@Controller("user")
@RequestMapping("/user")
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @RequestMapping(value = "/getOtp",method = {RequestMethod.POST},consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
   public CommonReturnType getOtp(@RequestParam(name = "telphone")String telphone){
       //随机生成Otp验证码
        Random random = new Random();
        int randomInt = random.nextInt(99999);
        randomInt += 10000;
        String otpCode = String.valueOf(randomInt);

        //将otp验证码同对应的手机号做关联，使用httpSession的方式
        httpServletRequest.getSession().setAttribute(telphone,otpCode);

        System.out.println("telphone :"+telphone+"otpCode:"+otpCode);
       return CommonReturnType.create(null);
   }

    @RequestMapping("/get")
    @ResponseBody
    public CommonReturnType getUser(@RequestParam(name = "id") Integer id) throws BusinessException {
       UserModel userModel = userService.userById(id);
       if(userModel == null){
           throw new BusinessException(EmBusinessError.USER_NOT_EXIST);
       }
       UserVO userVO = convestFromUserVO(userModel);
       return CommonReturnType.create(userVO);
    }

    private UserVO convestFromUserVO(UserModel userModel){
        if(userModel == null){
            return null;
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userModel,userVO);
        return userVO;
    }


}
