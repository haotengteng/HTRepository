package com.service.impl;

import com.dao.UserBaseDao;
import com.model.UserBaseDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import com.service.UserBaseService;
import redis.clients.util.SafeEncoder;

import javax.annotation.Resource;

/**
 * Created by htt on 2015/12/23.
 */
@Service
public class UserBaseServiceImpl implements UserBaseService{
    @Autowired
    UserBaseDao userBaseDao;
    @Transactional
    public Boolean regeist(UserBaseDO userBaseDO) {
        if (StringUtils.isEmpty(userBaseDO.getUserId())
                ||StringUtils.isEmpty(userBaseDO.getUserName())
                ||StringUtils.isEmpty(userBaseDO.getUserId())
                ||StringUtils.isEmpty(userBaseDO.getPassword())){
            return false;
        }
        userBaseDO.setPassword(SafeEncoder.encode(userBaseDO.getPassword()).toString());
        return  userBaseDao.addUser(userBaseDO)>0;
    }

    public UserBaseDO findByPhone(String phone) {
        if (phone==null){
            return null;
        }
        return userBaseDao.findByPhone(phone);
    }
}
