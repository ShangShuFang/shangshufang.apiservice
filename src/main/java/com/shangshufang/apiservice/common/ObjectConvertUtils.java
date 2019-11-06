package com.shangshufang.apiservice.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ObjectConvertUtils {
    private Logger logger = LogManager.getLogger(ObjectConvertUtils.class);

    /**
     * @param filterBean 用于赋值的实体类
     * @param targetBean 需要待赋值的实体类
     * 描述：反射实体类赋值target
     */
    public static void toBean(Object filterBean,Object targetBean) throws Exception{
        Class filterClass = Class.forName(filterBean.getClass().getName());
        Class targetClass = Class.forName(targetBean.getClass().getName());

        List<Field> fields1 = new ArrayList<>() ;
        List<Field> fields2 = new ArrayList<>() ;

        while (filterClass != null && !filterClass.getName().toLowerCase().equals("java.lang.object")) {//当父类为null的时候说明到达了最上层的父类(Object类).
            fields1.addAll(Arrays.asList(filterClass.getDeclaredFields()));
            filterClass = filterClass.getSuperclass(); //得到父类,然后赋给自己
        }
        while (targetClass != null && !targetClass.getName().toLowerCase().equals("java.lang.object")) {//当父类为null的时候说明到达了最上层的父类(Object类).
            fields2.addAll(Arrays.asList(targetClass.getDeclaredFields()));
            targetClass = targetClass.getSuperclass(); //得到父类,然后赋给自己
        }

        ObjectConvertUtils commonUtils = new ObjectConvertUtils();
        for (Field f1 : fields1) {
            Object value = commonUtils.invokeGetMethod(filterBean ,f1.getName(),null);
            for (Field f2 : fields2) {
                if(f1.getName().equals(f2.getName())){
                    Object[] obj = new Object[1];
                    obj[0] = value;
                    commonUtils.invokeSetMethod(targetBean, f2, f2.getName(), obj);
                    break;
                }
            }
        }

    }

    /**
     * 执行某个Field的getField方法
     * @param filterBean 类
     * @param fieldName 类的属性名称
     * @param args 参数，默认为null
     * @return
     */
    private Object invokeGetMethod(Object filterBean, String fieldName, Object[] args)
    {
        String methodName = fieldName.substring(0, 1).toUpperCase()+ fieldName.substring(1);
        Method method = null;
        try
        {
            method = Class.forName(filterBean.getClass().getName()).getDeclaredMethod("get" + methodName);
            return method.invoke(filterBean);
        }
        catch (Exception ex)
        {
            logger.error(ex.toString());
            return "";
        }
    }

    /**
     * 执行某个Field的setField方法
     * @param targetBean 类
     * @param fieldName 类的属性名称
     * @param args 参数，默认为null
     * @return
     */
    private Object invokeSetMethod(Object targetBean, Field targetField, String fieldName, Object[] args) {
        String methodName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        Method method = null;
        try {
            Class[] parameterTypes = new Class[1];
            Class targetClass = Class.forName(targetBean.getClass().getName());
            parameterTypes[0] = targetField.getType();
            method = targetClass.getMethod("set" + methodName, parameterTypes);
            return method.invoke(targetBean, args);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return "";
        }
    }
}
