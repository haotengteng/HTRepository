package com.service.impl;

import com.dao.UserBase;
import com.model.UserBaseDO;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.service.UserBaseService;

import javax.annotation.Resource;

/**
 * Created by htt on 2015/12/23.
 */
@Service
public class UserBaseServiceImpl implements UserBaseService{
    @Resource
    UserBase userBase;
    public Boolean regeist(UserBaseDO userBaseDO) {
        if (StringUtils.isEmpty(userBaseDO.getUserId())
                ||StringUtils.isEmpty(userBaseDO.getUserName())
                ||StringUtils.isEmpty(userBaseDO.getUserId())){
            return false;
        }
        return  userBase.addUser(userBaseDO)>0;
    }
}
