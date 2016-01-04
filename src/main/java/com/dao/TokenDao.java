package com.dao;

import com.model.TokenDO;
import org.springframework.stereotype.Repository;

/**
 * Created by htt on 2015/12/28.
 */
@Repository
public interface TokenDao {
    int addToken(TokenDO tokenDO);
    TokenDO findTokenById(String tokenId);
}
