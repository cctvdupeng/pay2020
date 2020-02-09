package com.mfypay.pay3.m;

import android.content.Context;

import com.mfypay.pay3.b.AliUI;
import com.mfypay.pay3.util.LGU;

import java.io.Serializable;
import java.lang.reflect.Method;

import de.robv.android.xposed.XposedHelpers;

public class LongM implements Serializable {
    public static AliUI getLongInfo(Context context) {
        AliUI aliUI=new AliUI();

       Object o= XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.ccb.framework.security.login.LoginUtils",context.getClassLoader()),"getLoginSetvarParams");

        Method[] methods = o.getClass().getMethods();

        for (Method m:methods){
            if (m.getName().startsWith("get")){
                LGU.D(XposedHelpers.callMethod(o,m.getName())+"=="+m.getName());
            }

            LGU.D(m.getName()+"======");
        }



        return aliUI;
    }
}
