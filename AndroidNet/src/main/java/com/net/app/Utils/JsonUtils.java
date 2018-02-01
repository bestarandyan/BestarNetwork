package com.net.app.Utils;

import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuxingxing on 2017/11/29.
 */

public class JsonUtils {


    public static String getFieldName(String methodName){
        String fistName = methodName.substring(3,4).toLowerCase();
        return fistName + methodName.substring(4);
    }


    public static Map<String,String> javaBeanToMap(Object javaBean){
        Map<String,String> resultMap = new HashMap<>();
        StringBuffer requestBody = new StringBuffer();
        try {
            Method[] methods = javaBean.getClass().getDeclaredMethods();
            Method[] superMethods = javaBean.getClass().getSuperclass().getDeclaredMethods();

            ArrayList<Method> methodList = new ArrayList<>();
            methodList.addAll(getMethodList(methods));
            methodList.addAll(getMethodList(superMethods));

            for (int j=0;j<methodList.size();j++){
                Object value = methodList.get(j).invoke(javaBean, (Object[]) null);
                resultMap.put(getFieldName(methodList.get(j).getName()), null != value ? value.toString() : "");
                requestBody.append(getFieldName(methodList.get(j).getName()));
                requestBody.append(":");
                requestBody.append(value!=null?value.toString():""+",");
            }

            requestBody.append("}");
            Log.e("请求参数=========", requestBody.toString());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    public static ArrayList<Method> getMethodList(Method[] methods){
        ArrayList<Method> methodList = new ArrayList<>();
        for (int i=0 ;i< methods.length;i++) {
            Method method = methods[i];
            if (method.getName().startsWith("get")) {
                methodList.add(method);
            }
        }
        return methodList;
    }
}
