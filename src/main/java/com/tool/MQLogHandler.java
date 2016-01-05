package com.tool;

import com.model.LogDo;
import com.service.LogService;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2016/1/5.
 */
public class MQLogHandler {
    Logger logger = Logger.getLogger(MQLogHandler.class);
    @Autowired
    LogService logService;

//    public void listen(String object) {
//        JSONObject json = new JSONObject(object);
//        LogDo logDo = new LogDo();
//        logDo.setUri(json.get("uri").toString());
//        logDo.setUserId(json.get("userId").toString());
////        if (object instanceof LogDo) {
////            try {
////                LogDo logDo = (LogDo) object;
//                logService.insertLog(logDo);
////            }catch (Exception e){
////             logger.error("MQ消息处理异常："+e);
////            }
////        }else {
////            logger.error("MQ消息处理异常："+ new JSONObject(object).toString());
////        }
//    }

    public void listen(Object object) {
        if (object instanceof LogDo) {
            try {
                LogDo logDo = (LogDo) object;
        logService.insertLog(logDo);
            }catch (Exception e){
             logger.error("MQ消息处理异常："+e);
            }
        }else {
            logger.error("MQ消息处理异常："+ new JSONObject(object).toString());
        }
    }
}
