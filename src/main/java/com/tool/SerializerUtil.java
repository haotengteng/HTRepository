package com.tool;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;

/**
 * Created by Administrator on 2016/1/6.
 */
public class SerializerUtil implements Serializable {
    public byte[] serialize(Object obj){
        if(obj == null){
            return null;
        }
        if (!(obj instanceof Serializable)) {
            throw new IllegalArgumentException("object of type [" + obj.getClass().getName() + "] is not serializable");
        }
        ByteArrayOutputStream os = null;
        Hessian2Output ho = null;
        try {
            os = new ByteArrayOutputStream();
            ho = new Hessian2Output(os);
            ho.writeObject(obj);
            ho.flush();
            return os.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if(null != ho){
                    ho.close();
                }
                if(null != os){
                    os.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    public Object deserialize(byte[] bytes) {
        if(bytes == null){
            return null;
        }
        ByteArrayInputStream is = null;
        Hessian2Input hi = null;
        try {
            is = new ByteArrayInputStream(bytes);
            hi = new Hessian2Input(is);
            return hi.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(null != hi){
                    hi.close();
                }
                if(null != is){
                    is.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
