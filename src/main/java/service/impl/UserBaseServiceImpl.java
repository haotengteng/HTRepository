package service.impl;

import dao.UserBase;
import model.UserBaseDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import service.UserBaseService;

/**
 * Created by htt on 2015/12/23.
 */
@Service
public class UserBaseServiceImpl implements UserBaseService{
    @Autowired
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
