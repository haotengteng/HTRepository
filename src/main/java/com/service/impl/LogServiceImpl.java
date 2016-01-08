package com.service.impl;

import com.dao.LogDao;
import com.model.LogDo;
import com.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2016/1/4.
 */
@Service
public class LogServiceImpl implements LogService{
    @Autowired
    LogDao logDao;
    public boolean insertLog(LogDo logDo) {
        if (logDo==null||logDo.getUserId()==null){
            return  false;
        }
        logDo.setLogId(UUID.randomUUID().toString());
        return logDao.insertLog(logDo)>0;
    }

    public List<LogDo> selectLog(String userId) {
        return null;
    }
}
