package com.service.impl;

import com.dao.TokenDao;
import com.model.TokenDO;
import com.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 * Created by htt on 2015/12/28.
 */
@Service
public class TokenServiceImpl implements TokenService{
    @Autowired
    TokenDao tokenDao;
    public String addTokenDO(TokenDO tokenDO) {
        if (tokenDO==null){
            tokenDO = new TokenDO();
        }
        String tokernId = UUID.randomUUID().toString();
       tokenDO.setTokenId(tokernId);
        tokenDO.setRegisterTime(new Date());
        tokenDao.addToken(tokenDO);
        return tokernId;
    }

    public TokenDO findTokenById(String token) {
        if (token==null){
            return null;
        }
        return tokenDao.findTokenById(token);
    }
}
