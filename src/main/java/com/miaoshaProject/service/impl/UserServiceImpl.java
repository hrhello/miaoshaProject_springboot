package com.miaoshaProject.service.impl;

import com.miaoshaProject.dao.UserDOMapper;
import com.miaoshaProject.dao.UserPassWordDOMapper;
import com.miaoshaProject.dataobject.UserDO;
import com.miaoshaProject.dataobject.UserPassWordDO;
import com.miaoshaProject.service.UserService;
import com.miaoshaProject.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDOMapper userDOMapper;

    @Autowired
    private UserPassWordDOMapper userPassWordDOMapper;
    @Override
    public UserModel userById(Integer id) {
       UserDO userDO = userDOMapper.selectByPrimaryKey(id);
       if (userDO == null){
           return null;
       }
       UserPassWordDO userPassWordDO = userPassWordDOMapper.selectByUserId(userDO.getId());
       return convertFromUserDO(userDO,userPassWordDO);
    }

    private UserModel convertFromUserDO(UserDO userDO, UserPassWordDO userPassWordDO){
        if(userDO == null){
            return null;
        }
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDO,userModel);
        if (userPassWordDO != null){
            userModel.setEncrptPassword(userPassWordDO.getEncrptPassword());
        }
        return userModel;

    }
}
