package com.mfypay.pay3.util;


import android.os.Bundle;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

/**
 * https://qr.alipay.com/fkx04980j4sch8jm5tnyhd0?t=1544019741682
 * https://qr.alipay.com/fkx010571brb3lsliaigr11
 */
public class LGU {
    public static void D(String info) {
        Log.d("appapp", new Date() + "<----------->" + info + "");
    }

    public static void DB(Bundle bundle) {
        if (bundle == null)
            return;
        for (String key : bundle.keySet()) {
            D("Key=" + key + ", content=" + bundle.get(key));

        }

    }


    public static void DP(XC_MethodHook.MethodHookParam param) {


        if (param != null) {
            D("param<----------->" + param.thisObject + "==" + param);
            Object result = param.getResult();
            if (result != null) {
                DAllField(result);
                LGU.D("result:" + result);
            }


            Object[] args = param.args;

            D(param.method + "=>start");
            if (args != null && args.length > 0) {

                for (Object o : args) {

                    D(o + "=========");
                }

            }
            D(param.method + "=>end");
        }


    }


    public static void DAllField(Object o) {
        if (o == null)
            return;
        Class<?> aClass = o.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field field : declaredFields) {
            try {
                if (field.getName().indexOf("NFD") != -1 || aClass.getName().indexOf("Proxy") != -1)
                    continue;
                D(aClass.getName() + "<--->" + field.toString() + "<------->" + field.getName() + "<---->" + XposedHelpers.getObjectField(o, field.getName()));
            } catch (Error e) {
                e.printStackTrace();
            }
        }


    }

    public static void DSupAllField(Object o) {
//        if (o == null)
//            return;
        Class<?> superclass = o.getClass().getSuperclass();
        Field[] superclassDeclaredFields = superclass.getDeclaredFields();
        for (Field field : superclassDeclaredFields) {
            D("superclass=====>" + superclass + "<--->" + field.getName() + "<---->" + XposedHelpers.getObjectField(o, field.getName()));
        }
    }

    public static void DSupSupAllField(Object o) {

        Class<?> superclass = o.getClass().getSuperclass().getSuperclass();
        Field[] superclassDeclaredFields = superclass.getDeclaredFields();
        for (Field field : superclassDeclaredFields) {
            D("superclass=====>" + superclass + "<--->" + field.getName() + "<---->" + XposedHelpers.getObjectField(o, field.getName()));
        }
    }


    public static void DAllMethod(Object o) {

        Class<?> aClass = o.getClass();
        Method[] declaredMethods = aClass.getDeclaredMethods();
        for (Method field : declaredMethods) {
            D(aClass.getName() + "<--->" + field.toString() + "<------->" + field.getName() + "<---->" + field);
        }


    }





    public static void DA(  String msg) {  //信息太长,分段打印
        String tag="appapp";
        //因为String的length是字符数量不是字节数量所以为了防止中文字符过多，
        //  把4*1024的MAX字节打印长度改为2001字符数
        int max_str_length = 2001 - tag.length();
        //大于4000时
        while (msg.length() > max_str_length) {
            Log.i(tag, msg.substring(0, max_str_length));
            msg = msg.substring(max_str_length);
        }
        //剩余部分
        Log.i(tag, msg);
    }









}
