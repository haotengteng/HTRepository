package com.service.impl;

import com.dao.TokenDao;
import com.model.TokenDO;
import com.service.TokenService;
import com.tool.RedisCacheUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 * Created by htt on 2015/12/28.
 */
@Service
public class TokenServiceImpl implements TokenService {
    @Autowired
    TokenDao tokenDao;
    @Autowired
    RedisCacheUtil redisCacheUtil;

    Logger logger = Logger.getLogger(TokenServiceImpl.class);

    public String addTokenDO(TokenDO tokenDO) {
        if (tokenDO == null) {
            tokenDO = new TokenDO();
        }
        String tokernId = UUID.randomUUID().toString();
        tokenDO.setTokenId(tokernId);
        tokenDO.setRegisterTime(new Date());
        tokenDao.addToken(tokenDO);
        if (!redisCacheUtil.set(tokernId, tokenDO)) {
            logger.error("tokenID添加到缓存失败");
        }
        return tokernId;
    }

    public TokenDO findTokenById(String token) {
        if (token == null) {
            return null;
        }
        TokenDO tokenDO = redisCacheUtil.get(token, TokenDO.class);
//        if (tokenDO == null) {
//            tokenDO = tokenDao.findTokenById(token);
//            redisCacheUtil.set(token, tokenDO);
//        }
        return tokenDO;
    }
}
