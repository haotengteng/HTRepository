package com.service;

import com.model.LogDo;

import java.util.List;

/**
 * Created by Administrator on 2016/1/4.
 */
public interface LogService {
    boolean insertLog(LogDo logDo);
    List<LogDo> selectLog(String userId);
}
