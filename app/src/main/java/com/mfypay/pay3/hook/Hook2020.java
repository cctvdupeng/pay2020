package com.mfypay.pay3.hook;

import android.app.Activity;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.UserHandle;

import com.mfypay.pay3.invocation.WangxinHook2;
import com.mfypay.pay3.util.LGU;

import org.json.JSONException;
import org.json.JSONObject;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by ruo on 2020/2/5.
 */

public class Hook2020 implements IXposedHookLoadPackage {


    public static final String name = "com.alibaba.mobileim";

    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam paramLoadPackageParam) {
        if (paramLoadPackageParam.packageName.equals("com.alibaba.mobileim") &&
                paramLoadPackageParam.processName.equals("com.alibaba.mobileim"))
            XposedHelpers.findAndHookMethod("com.alibaba.mobileim.MessengerApplication",
                    paramLoadPackageParam.classLoader, "onCreate", new Object[]{new XC_MethodHook() {
                        protected void afterHookedMethod(XC_MethodHook.MethodHookParam param1MethodHookParam) throws Throwable {
                            super.afterHookedMethod(param1MethodHookParam);
                            LGU.D("init=========================");
                            init((Context) param1MethodHookParam.thisObject);
                        }

                        public void init(final Context context) {
                            WangxinHelper.mHandler.postDelayed(new Runnable() {
                                public void run() {
                                    try {

                                        final WXUserInfo userInfo = new WXUserInfo();
                                        userInfo.cid = "";
                                        userInfo.name = WangxinHelper.getShowName(context);


                                        WXClass.hook(context, userInfo, context.getClassLoader());


                                        //  HookUtil.HOOKONE("",context.getClassLoader(),"init");

                                        String str = null;

                                        str = WangxinHelper.getShowName(context);


                                        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
                                            @Override
                                            public void onReceive(Context mCtx, Intent intent) {

                                                String type = intent.getStringExtra("rType");
                                                WXUserInfo userInfo = new WXUserInfo();
                                                LGU.D(type);

                                                userInfo.cid = "cntaobao饭小饭1691";
                                                userInfo.sender = "ruoshuisixue";

                                                if (type.equals("link")) {//hoq lmj

                                                    try {
                                                        LGU.D("开始hook");
                                                        WangxinHelper.sendRedPacket(context, userInfo, 100);
                                                    } catch (Throwable throwable) {
                                                        throwable.printStackTrace();
                                                        LGU.D("-----------");
                                                    }
                                                }


                                                if (type.equals("returns")) {//
                                                    JSONObject jsonObject = new JSONObject();
                                                    try {
                                                        jsonObject.put("id", "12621186_350_1581128178");
                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                    }
                                                    WangxinHelper.pushRedPacket(context, userInfo, jsonObject);
                                                }


                                                if (type.equals("open")) {
                                                    JSONObject jsonObject = new JSONObject();
                                                    try {
                                                        jsonObject.put("id", "12621186_350_1581128178");
                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                    }
                                                    try {
                                                        WangxinHelper.openRedPacket(context, userInfo, jsonObject);
                                                    } catch (Throwable throwable) {
                                                        throwable.printStackTrace();
                                                    }


                                                }


                                            }
                                        };
                                        IntentFilter intentFilter = new IntentFilter();
                                        intentFilter.addAction("com.mfypay.wangwang");
                                        context.registerReceiver(broadcastReceiver, intentFilter);


                                    } catch (Throwable throwable) {
                                        throwable.printStackTrace();
                                    } finally {
                                        Exception exception = null;

                                    }
                                }
                            }, 3000L);
                        }
                    }});


    }
}