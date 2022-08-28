package com.lfs.test.java;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * CodeDemo
 *
 * @author fushenliao
 * @version 1.0.0
 * @project demo
 * @create 2021/6/24
 * @modify 2021/6/24
 */
public class CodeDemo {

    public static void setConfVal(String fieldName, int newValue) {
        try {
            //System.out.println("Before modifying : "+RcCreditConfig.KEY_EXIT);//1024

            //获取属性
            Field field = CodeDemo.class.getDeclaredField(fieldName);

            //忽略final修饰符【注释一】
            Field modifiers = Field.class.getDeclaredField("modifiers");
            modifiers.setAccessible(true);
            modifiers.setInt(field, field.getModifiers() & ~Modifier.FINAL);
            //设置新的值
            field.set(null, newValue);

        } catch (Throwable t) {
            //  LOGGER.error("修改RcCreditConfig配置值失败, fieldName:{},newValue:{}", fieldName, newValue, t);
        }
    }

    public static void main(String[] args) {

    }
}
