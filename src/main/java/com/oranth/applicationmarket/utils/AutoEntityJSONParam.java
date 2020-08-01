package com.oranth.applicationmarket.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

public class AutoEntityJSONParam {

    public static Object setParam(Class<?> cla,String json) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        JSONObject jsonObj =  JSON.parseObject(json);
        Set<String> set = jsonObj.keySet();
        Object obj = cla.newInstance();

        Field[] fields = cla.getFields();
        Method[] methods = cla.getDeclaredMethods();
        String methodName = "";
        for(String fieldName : set){
            Object val = jsonObj.get(fieldName);
            methodName = "set"+fieldName;
//            Method method =
            for(Method method : methods){
                if(methodName.equalsIgnoreCase(method.getName())){
                    Class paremCla = method.getParameterTypes()[0];
                    method.invoke(obj,formatPatam(paremCla,val));
                    break;
                }
            }
        }

        return obj;
    }

    private static String upperCase(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
    private static Object formatPatam(Class cla,Object obj){
        String className = cla.getName();
        String str = obj.toString();
        switch (className){
            case "java.lang.String" :
                return str;
            case "java.lang.Integer" :
                return Integer.valueOf(str);
            default:
                return null;
        }


    }
}
