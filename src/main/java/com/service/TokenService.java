package com.service;

import com.commonHandler.defineException.CacheException;
import com.model.TokenDO;

/**
 * Created by htt on 2015/12/28.
 */
public interface TokenService {
    String addTokenDO(TokenDO tokenDO) throws CacheException;
    TokenDO findTokenById(String token);
}
