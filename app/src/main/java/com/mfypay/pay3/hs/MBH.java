package com.mfypay.pay3.hs;

import android.annotation.TargetApi;
import android.app.Application;
import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mfypay.pay3.b.AliUI;
import com.mfypay.pay3.util.LGU;
import com.mfypay.pay3.util.PHU;
import com.mfypay.pay3.util.StringUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static android.content.Context.MODE_PRIVATE;

public class MBH implements IP {
    //com.mybank.android.phone
    private boolean isStart;


    @Override
    public void load(XC_LoadPackage.LoadPackageParam param) {

        if (param.packageName.equals("com.mybank.android.phone")) {

            a(param);
        }
    }

    private void a(final XC_LoadPackage.LoadPackageParam param) {


        XposedHelpers.findAndHookMethod("com.mybank.android.phone.MYApplication", param.classLoader, "onCreate", new Object[]{new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam methodHookParam) {
                if (("com.mybank.android.phone".equals(param.processName)) && (!isStart)) {
                    isStart = true;
                    LGU.D("注册网商成功1=========" + param.packageName);
                    r((Context) methodHookParam.thisObject);
                    LGU.D("注册网商成功5" + param.packageName);
                    o(param, (Context) methodHookParam.thisObject);

                }
            }
        }
        });


    }

    private void o(XC_LoadPackage.LoadPackageParam param, final Context context) {
        XposedBridge.hookAllMethods(XposedHelpers.findClass("com.mybank.android.phone.bill.ui.PaymentListActivity", context.getClassLoader()), "saveDataFromCache", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);
            }

            @Override
            protected void afterHookedMethod(final MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                LGU.D(param.args[0] + "result");
                Object thisObject = param.thisObject;


                Method[] declaredMethods = thisObject.getClass().getDeclaredMethods();


                for (Method method:declaredMethods){
                    LGU.D(method.toString()+"===============");
                }





                List mPayments = (List) XposedHelpers.getObjectField(param.thisObject, "mPayments");

                for (Object o : mPayments) {
                    String mBalance = String.valueOf(XposedHelpers.getObjectField(o, "mBalance"));
                    String mCardNumber = String.valueOf(XposedHelpers.getObjectField(o, "mCardNumber"));

                    String mCardRemark = String.valueOf(XposedHelpers.getObjectField(o, "mCardRemark"));

                    String mSerialNo = String.valueOf(XposedHelpers.getObjectField(o, "mSerialNo"));

                    final String mTransAmount = String.valueOf(XposedHelpers.getObjectField(o, "mTransAmount"));

                    String mTransAmountStr = String.valueOf(XposedHelpers.getObjectField(o, "mTransAmountStr"));
                    final String mTransDate = String.valueOf(XposedHelpers.getObjectField(o, "mTransDate"));

                    String mTransName = String.valueOf(XposedHelpers.getObjectField(o, "mTransName"));

                    String mTransRemark = String.valueOf(XposedHelpers.getObjectField(o, "mTransRemark"));

                    final String mTransTime = String.valueOf(XposedHelpers.getObjectField(o, "mTransTime"));
                    String mTransType = String.valueOf(XposedHelpers.getObjectField(o, "mTransType"));

                    final Map<String, String> map = new HashMap<>();
                    map.put("mBalance", mBalance);
                    map.put("mCardNumber", mCardNumber);
                    map.put("mCardRemark", mCardRemark);
                    map.put("mSerialNo", mSerialNo);
                    map.put("mTransAmount", mTransAmount);
                    map.put("mTransAmountStr", mTransAmountStr);
                    map.put("mTransDate", mTransDate);
                    map.put("mTransName", mTransName);
                    map.put("mTransRemark", mTransRemark);

                    map.put("mTransTime", mTransTime);
                    map.put("mTransType", mTransType);

                    if (mTransAmountStr.startsWith("-")) {
                        continue;
                    }

                    if (!mTransName.equalsIgnoreCase("支付宝转入")) {
                        continue;
                    }

                    String trandeTime = mTransDate + " " + mTransTime;
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
                    Date date = sdf.parse(trandeTime);
                    trandeTime = String.valueOf(date.getTime() / 1000);
                    map.put("trandeTime", trandeTime);

                    Class<?> aClass = XposedHelpers.findClass("com.alipay.mobile.common.logging.ContextInfo", context.getClassLoader());

                    Object info = XposedHelpers.newInstance(aClass, new Class[]{Context.class}, context);

                    String userId = String.valueOf(XposedHelpers.getObjectField(info, "userId"));
                    map.put("userId", userId);


                    Intent intent = new Intent();
                    intent.putExtra("no", mSerialNo);
                    intent.putExtra("money", mTransAmount);

                    intent.putExtra("mark", trandeTime);

                    intent.putExtra("userId", userId);
                    intent.putExtra("type", ten);
                    intent.putExtra("strategy", true);
                    intent.putExtra("extra", JSON.toJSONString(map));
                    intent.setAction("store.imea1.result");

                    context.sendBroadcast(intent);

                    LGU.D(JSON.toJSONString(map));

                    Toast.makeText(context, JSON.toJSONString(map), Toast.LENGTH_SHORT).show();


                }


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        XposedHelpers.setIntField(param.thisObject, "mRequestIndex", 0);
                        XposedHelpers.callMethod(param.thisObject, "onLoadData", new Class[]{boolean.class}, false);
                    }
                }, 5000);


            }
        });

    }

    private void r(final Context ctx) {
        LGU.D("注册网商成功3");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.mfypay.mbk");
        LGU.D("注册网商成功1");


        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(final Context context, Intent intent) {
                LGU.D("注册网商成功2");

                final String rType = intent.getStringExtra("rType");


                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final Class serviceManager1 = XposedHelpers.findClass("com.mybank.android.phone.common.service.api.ServiceManager", context.getClassLoader());


                        Object rpcService = XposedHelpers.callStaticMethod(serviceManager1, "findServiceByInterface", "com.mybank.android.phone.common.service.api.RpcService");

                        Object userInfoFacade = XposedHelpers.callMethod(rpcService, "getRpcProxy", new Class[]{Class.class}, XposedHelpers.findClass("com.mybank.bkmycfg.common.service.gw.UserInfoFacade", context.getClassLoader()));

                        Object userInfo = XposedHelpers.callMethod(userInfoFacade, "getUserInfo");


                        Class<?> aClass = XposedHelpers.findClass("com.alipay.mobile.common.logging.ContextInfo", ctx.getClassLoader());

                        Object info = XposedHelpers.newInstance(aClass, new Class[]{Context.class}, ctx);


                        AliUI login = new AliUI();

                        login.setUserId(String.valueOf(XposedHelpers.getObjectField(info, "userId")));
                        login.setLogonId(String.valueOf(XposedHelpers.getObjectField(info, "clientId")));

                        login.setUserName(String.valueOf(XposedHelpers.getObjectField(userInfo, "realName")));
                        login.setShowName(String.valueOf(XposedHelpers.getObjectField(userInfo, "realName")));
                        login.setNick(String.valueOf(XposedHelpers.getObjectField(userInfo, "realName")));
                        login.setUserAvatar(String.valueOf(XposedHelpers.getObjectField(userInfo, "headImageUrl")));
                        login.setImageMiddleHiddenName(String.valueOf(XposedHelpers.getObjectField(userInfo, "headImageUrl")));

                        login.setLoginTime(String.valueOf(XposedHelpers.getObjectField(userInfo, "birthDay")));
                        login.setRealNamed(String.valueOf(XposedHelpers.getObjectField(userInfo, "realName")));
                        login.setMobileNumber(String.valueOf(XposedHelpers.getObjectField(userInfo, "mobile")));

                        login.setBankId("12");
                        login.setIdCard(String.valueOf(XposedHelpers.getObjectField(userInfo, "certNo")));
                        login.setBankNum(String.valueOf(XposedHelpers.getObjectField(userInfo, "cardNo")).trim().replaceAll(" ","" ));

                        login.setType("10");
                        if ("bind".equalsIgnoreCase(rType)) {
                            login.setStatus("1");
                            if (!"Normal".equalsIgnoreCase(String.valueOf(XposedHelpers.getObjectField(userInfo, "certNoStatus")))) {
                                login.setStatus("0");
                            }

                          //  LGU.D(String.valueOf(XposedHelpers.getObjectField(userInfo, "certNoStatus")));

                            LGU.D(login.toString() + "111");
                            Intent bindIntent = new Intent("com.mfypay.autoali");
                            bindIntent.putExtra("aliInfo", JSONObject.toJSONString(login));


                            context.sendBroadcast(bindIntent);

                        } else if ("unBind".equalsIgnoreCase(rType)) {
                            login.setStatus("0");

                            Intent unbindIntent = new Intent("com.mfypay.autoali");
                            unbindIntent.putExtra("aliInfo", JSONObject.toJSONString(login));
                            context.sendBroadcast(unbindIntent);

                        }else if ("query".equalsIgnoreCase(rType)){

                            Intent intent1=new Intent();
                            intent1.setClassName("com.mybank.android.phone","com.mybank.android.phone.bill.ui.PaymentListActivity");
                            context.startActivity(intent1);



                        }


                    }
                }).start();
            }
        };

        ctx.registerReceiver(receiver, intentFilter);
    }
}
