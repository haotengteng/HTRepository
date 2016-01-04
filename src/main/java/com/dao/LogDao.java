package com.dao;

import com.model.LogDo;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2016/1/4.
 */
@Repository
public interface LogDao {
    int insertLog (LogDo logDo);
}
