package com.mfypay.pay3.util;

import com.alibaba.fastjson.JSON;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import de.robv.android.xposed.XposedHelpers;

public class Util {
    public static String FIELD2JSON(Object o) {

        if (o == null)
            return "";

        Field[] declaredFields = o.getClass().getDeclaredFields();

        Map<String, String> map = new HashMap<>();
        for (Field field : declaredFields) {
            map.put(field.getName(), String.valueOf(XposedHelpers.getObjectField(o, field.getName())));
        }

        return JSON.toJSONString(map);
    }
}
