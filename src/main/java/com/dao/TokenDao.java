package com.dao;

import com.model.TokenDO;

/**
 * Created by htt on 2015/12/28.
 */
public interface TokenDao {
    int addToken(TokenDO tokenDO);
    TokenDO findTokenById(String tokenId);
}
