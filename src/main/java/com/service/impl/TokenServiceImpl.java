package com.service.impl;

import com.commonHandler.defineException.CacheException;
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
public class TokenServiceImpl implements TokenService {
    @Autowired
    TokenDao tokenDao;
    @Autowired
    RedisCacheUtil redisCacheUtil;

    Logger logger = Logger.getLogger(TokenServiceImpl.class);

    public String addTokenDO(TokenDO tokenDO) throws CacheException {
        if (tokenDO == null) {
            tokenDO = new TokenDO();
        }
        String tokernId = UUID.randomUUID().toString();
        tokenDO.setTokenId(tokernId);
        tokenDO.setRegisterTime(new Date());
        if (!redisCacheUtil.setEx(tokernId, 3600, tokenDO)) {
            logger.error("tokenID添加到缓存失败");
            throw new CacheException("tokenID添加到缓存失败");
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
