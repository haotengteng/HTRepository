package com.dao;

import com.model.UserBaseDO;
import org.springframework.stereotype.Repository;

/**
 * Created by htt on 2015/12/23.
 */
public interface UserBaseDao {
    int addUser(UserBaseDO userBaseDO);
    UserBaseDO findByPhone(String phone);
}
