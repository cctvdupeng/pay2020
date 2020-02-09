package com.mfypay.pay3.hs;

import com.mfypay.pay3.util.LGU;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;

public class HU {
    public static void HOOK(String clazzName, ClassLoader clazzLoader) {
        clazzName=clazzName.trim();

        Class<?> aClass = XposedHelpers.findClass(clazzName, clazzLoader);


        XposedBridge.hookAllConstructors(aClass, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
           //     LGU.DP(param);
                super.beforeHookedMethod(param);
            }

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
              //  LGU.DP(param);
                super.afterHookedMethod(param);
                LGU.DP(param);
            }
        });





        Method[] methods = aClass.getDeclaredMethods();
        if (methods != null && methods.length > 0) {


            for (Method m : methods) {
                LGU.D("开始hook====>" +clazzName+"."+ m.toString());
                HOOKONE(clazzName, clazzLoader, m.getName());

            }

        }


    }

    public static void HOOKONE(String clazzName, ClassLoader clazzLoader, String methodName) {
        LGU.D("开始hook====>" +clazzName+"."+ methodName);
        Class<?> aClass = XposedHelpers.findClass(clazzName, clazzLoader);
//        LGU.D("开始hook====>findClass" +aClass );
        XposedBridge.hookAllMethods(aClass, methodName, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {

                super.beforeHookedMethod(param);
               // LGU.DP(param);
            }

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {

//                LGU.DP(param);
                super.afterHookedMethod(param);
                LGU.DP(param);


                Object thisObject = param.thisObject;
                if (thisObject==null)
                    return;
                if (thisObject.getClass()==null)
                    return;

             try {
                 Field[] declaredFields = thisObject.getClass().getDeclaredFields();

                 for (Field field:declaredFields){
                     LGU.D(thisObject.toString()+"=="+field.toString()+"=="+field.getDeclaringClass()+"<------------------->"+field.getName()+"<----->"+XposedHelpers.getObjectField(thisObject,field.getName()));
                 }

             }catch (Exception e){

                 e.printStackTrace();
             }

            }
        });


    }
}
