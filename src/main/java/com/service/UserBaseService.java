package com.service;

import com.model.UserBaseDO;

/**
 * Created by htt on 2015/12/23.
 */
public interface  UserBaseService {
    Boolean regeist(UserBaseDO userBaseDO);
    UserBaseDO findByPhone(String phone);
}
