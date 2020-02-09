package com.mfypay.pay3.util;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Base64;


import com.mfypay.pay3.App;
import com.mfypay.pay3.MainActivity;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class PHU {











    /*
     * 启动一个app
     */
    public static void startAPP() {
        try {
            Intent intent = new Intent(App.getContext().getApplicationContext(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            App.getContext().getApplicationContext().startActivity(intent);
        } catch (Exception e) {
        }
    }

    /**
     * 将图片转换成Base64编码的字符串
     *
     * @param path
     * @return base64编码的字符串
     */
    public static String imageToBase64(String path) {
        if (TextUtils.isEmpty(path)) {
            return null;
        }
        InputStream is = null;
        byte[] data = null;
        String result = null;
        try {
            is = new FileInputStream(path);
            // 创建一个字符流大小的数组。
            data = new byte[is.available()];
            // 写入数组
            is.read(data);
            // 用默认的编码格式进行编码
            result = Base64.encodeToString(data, Base64.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        result = "\"data:image/gif;base64," + result + "\"";
        return result;
    }


    /**
     * 方法描述：判断某一应用是否正在运行
     *
     * @param context     上下文
     * @param packageName 应用的包名
     * @return true 表示正在运行，false表示没有运行
     */
    public static boolean isAppRunning(Context context, String packageName) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(100);
        if (list.size() <= 0) {
            return false;
        }
        for (ActivityManager.RunningTaskInfo info : list) {
            if (info.baseActivity.getPackageName().equals(packageName)) {
                return true;
            }
        }
        return false;
    }

    /*
     * 启动一个app
     */
    public static void startAPP(Context context, String appPackageName) {
        try {
            Intent intent = context.getPackageManager().getLaunchIntentForPackage(appPackageName);
            context.startActivity(intent);
        } catch (Exception e) {
        }
    }






//    public static void StartMe() {
//
//      try {
//          if (!isAppRunning(App.getContext(), "com.mfypay.pay3")) {
//              Object isShow = SPU.getParam(App.getContext(), "isShow", "false");
//              Object token = SPU.getParam(App.getContext(), "token", "token");
//              if (!TextUtils.isEmpty(String.valueOf(isShow))&&"true".equalsIgnoreCase(String.valueOf(isShow))&&!TextUtils.isEmpty(String.valueOf(token))){
//                  //startAPP();
//              }
//
//          }
//      }catch (Exception e){
//
//      }
//
//    }
//
//    public static void StartMe(String pkgName) {
//        if (!isAppRunning(App.getContext(),pkgName)) {
//            Object isShow = SPU.getParam(App.getContext(), "isShow", "false");
//            if (!TextUtils.isEmpty(String.valueOf(isShow))&&"true".equalsIgnoreCase(String.valueOf(isShow))){
//                Intent intent = App.getContext().getPackageManager().getLaunchIntentForPackage(pkgName);
//                App.getContext().startActivity(intent);
//            }
//
//        }


//    }
}
