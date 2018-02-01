package com.net.app.Utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuxingxing on 2017/11/29.
 */

public class JsonUtils {

    public static Map<String,String> javaBeanToMap(Object javaBean){
        Map<String,String> resultMap = new HashMap<>();
        Method[] methods = javaBean.getClass().getDeclaredMethods();
        Field[] fields = javaBean.getClass().getFields();
        for (int i=0;i<methods.length;i++){
            if (methods[i].getName().startsWith("get")){
                String field =fields[i].getName();
                try {
                    Object value = methods[i].invoke(javaBean,(Object[])null);
                    resultMap.put(field,null!=value?value.toString():"");
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }

        return resultMap;
    }
}
