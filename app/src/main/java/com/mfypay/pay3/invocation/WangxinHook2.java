package com.mfypay.pay3.invocation;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import com.alibaba.fastjson.JSONObject;
import com.mfypay.pay3.App;
import com.mfypay.pay3.util.LGU;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;

/**
 * Created by ruo on 2020/2/5.
 */

public class WangxinHook2 {
    public static String O000000o = "12621186";

    private static int O0000OoO = Math.abs((new Random()).nextInt());

//    private oO0Ooo00 O00000Oo;

    private ClassLoader classLoader;

    private Context mCtx;

    private String O00000oO;

    private boolean O00000oo = false;

    private String currentId;

    private String O0000OOo;

    private Class clazz;

    private String O0000Oo0;

    private String WangXinCreateInvocation(int paramInt) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("M");
        stringBuilder.append("_");
        stringBuilder.append(paramInt);
        stringBuilder.append("_");
        paramInt = O0000OoO;
        O0000OoO = paramInt + 1;
        stringBuilder.append(Math.abs(paramInt) % 10000);
        stringBuilder.append("_");
        stringBuilder.append(System.currentTimeMillis());
        return stringBuilder.toString();
    }

    private void WangXinCreateInvocation(Context paramContext, String paramString1, String paramString2, String paramString3) {
        Object object1 = XposedHelpers.callMethod(XposedHelpers.callMethod(XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.alibaba.mobileim.YWAPI", this.classLoader),
                "getIMKitInstance", new Object[]{this.O0000OOo, O000000o}), "getIMCore", new Object[0]),
                "getWxAccount", new Object[0]);
        Object object2 = XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.alibaba.mobileim.lib.presenter.hongbao.HongbaoManager", this.classLoader),
                "getInstance", new Object[0]);
        ClassLoader classLoader = this.classLoader;
        Class clazz = this.clazz;
        //  mCtx o00000o0 = new mCtx(paramContext, paramString2, O00000oo(), paramString1, paramString3);
        // O0o00.O000000o(new - $$Lambda$O00000Oo$zIZmVKHXSga3N_HjiNKHOmVWL0I(object2, object1, paramString2, paramString3, Proxy.newProxyInstance(classLoader, new Class[]{clazz}, o00000o0)));
    }

    private void createHongbao() {
        try {
            this.clazz = this.classLoader.loadClass("com.alibaba.mobileim.channel.event.IWxCallback");
            XposedBridge.hookAllMethods(XposedHelpers.findClass("com.alibaba.mobileim.lib.presenter.hongbao.HongbaoManager", this.classLoader),
                    "createHongbao",
                    new XC_MethodHook() {
                        protected void afterHookedMethod(XC_MethodHook.MethodHookParam param1MethodHookParam) {
                        }
                    });
            XposedBridge.hookAllMethods(XposedHelpers.findClass("com.alibaba.mobileim.YWAPI", this.classLoader),
                    "createIMCore", new XC_MethodHook() {
                        protected void beforeHookedMethod(XC_MethodHook.MethodHookParam param1MethodHookParam) {
                            try {
                                super.beforeHookedMethod(param1MethodHookParam);
                            } catch (Throwable throwable) {
                                throwable.printStackTrace();
                            }
                            LGU.D("====================");
//                    if (TextUtils.isEmpty(O00000Oo.O000000o(this.O000000o))) {
//                        O00000Oo.O000000o(this.O000000o, (String)param1MethodHookParam.args[0]);
//                        if (!O00000Oo.O000000o(this.O000000o).startsWith("cnhhupan") || !O00000Oo.O000000o(this.O000000o).startsWith("cntaobao")) {
//                            O00000Oo o00000Oo = this.O000000o;
//                            StringBuilder stringBuilder = new StringBuilder();
//                            stringBuilder.append("cntaobao");
//                            stringBuilder.append(O00000Oo.O000000o(this.O000000o));
//                            O00000Oo.O00000Oo(o00000Oo, stringBuilder.toString());
//                        }
//                    }
                        }
                    });
            XposedHelpers.findAndHookMethod(this.classLoader.loadClass("com.alibaba.mobileim.lib.presenter.message.MessageList"), "insertToDB", new Object[]
                    {this.classLoader.loadClass("com.alibaba.mobileim.lib.model.message.Message"),
                            new XC_MethodHook() {

                                protected void beforeHookedMethod(XC_MethodHook.MethodHookParam param1MethodHookParam) {
                                    try {
                                        super.beforeHookedMethod(param1MethodHookParam);
                                    } catch (Throwable throwable) {
                                        throwable.printStackTrace();
                                    }
//                    try {
//                        ImMessage imMessage = (ImMessage)O0OOo.O000000o(O0OOo.O000000o(param1MethodHookParam.args[0]), ImMessage.class);
//                        if (imMessage != null && imMessage.getmSubType() == 66 && !TextUtils.isEmpty(imMessage.getmContent()) && imMessage.getmContent().contains("你的红包已经领完") && imMessage.getmContent().contains("hongbaoSender")) {
//                            BodyContent bodyContent = (BodyContent)O0OOo.O000000o(imMessage.getmContent(), BodyContent.class);
//                            if (bodyContent != null && bodyContent.getHeader() != null && bodyContent.getInternal() != null) {
//                                String str = bodyContent.getInternal().getHongbao_id();
//                                if (!TextUtils.isEmpty(str)) {
//                                    O00000Oo o00000Oo = this.O000000o;
//                                    StringBuilder stringBuilder = new StringBuilder();
//                                    stringBuilder.append("insertToDB： ");
//                                    stringBuilder.append(str);
//                                    O00000Oo.mCtx(o00000Oo, stringBuilder.toString());
//                                    String str1 = O0O0OO.mCtx(str);
//                                    if (!TextUtils.isEmpty(str1)) {
//                                        JSONObject jSONObject2 = new JSONObject(str1);
//                                        PayOrder payOrder = new PayOrder();
//                                        payOrder.setTradeNo(str);
//                                        payOrder.setMoney(String.valueOf(jSONObject2.getLong("amount")));
//                                        payOrder.setRemark(jSONObject2.optString("remark"));
//                                        payOrder.setBizCreateTime(System.currentTimeMillis());
//                                        payOrder.setBizType("END");
//                                        JSONObject jSONObject1 = new JSONObject();
//                                        jSONObject1.put("senderId", bodyContent.getInternal().getHongbaoSender());
//                                        jSONObject1.put("curUserId", O00000Oo.O00000Oo(this.O000000o));
//                                        payOrder.setExtension(jSONObject1.toString());
//                                        if (O0O0OO.O000000o(payOrder)) {
//                                            OooOO.O000000o(O00000Oo.mCtx(this.O000000o), payOrder);
//                                            return;
//                                        }
//                                        StringBuilder stringBuilder1 = new StringBuilder();
//                                        stringBuilder1.append("我是插入的？");
//                                        stringBuilder1.append(payOrder);
//                                        O0Oo00.O000000o(stringBuilder1.toString());
//                                        return;
//                                    }
//                                }
//                            }
//                        }

                                }
                            }});

            XposedHelpers.findAndHookMethod("com.alibaba.mobileim.lib.presenter.message.MessageList", this.classLoader, "pushMessage", new Object[]{String.class, List.class, new WangxinHook2() {
                protected void afterHookedMethod(XC_MethodHook.MethodHookParam param1MethodHookParam) {
                    // O00000Oo o00000Oo = this.O000000o;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(" 收到消息 ");
                    stringBuilder.append(param1MethodHookParam.args[0]);
//                    O00000Oo.mCtx(o00000Oo, stringBuilder.toString());
//                    for (XC_MethodHook.MethodHookParam param1MethodHookParam : param1MethodHookParam.args[1]) {
//                        try {
//                            if (XposedHelpers.getIntField(param1MethodHookParam, "msgType") == 211) {
//                                String str1;
//                                XposedHelpers.getLongField(param1MethodHookParam, "msgId");
//                                JSONObject jSONObject1 = new JSONObject((String)XposedHelpers.getObjectField(param1MethodHookParam, "content"));
//                                JSONObject jSONObject2 = jSONObject1.optJSONObject("header");
//                                o00000Oo = null;
//                                O00000Oo o00000Oo1 = o00000Oo;
//                                if (jSONObject2 != null) {
//                                    o00000Oo1 = o00000Oo;
//                                    if (jSONObject2.has("summary")) {
//                                        String str = jSONObject2.getString("summary");
//                                        str1 = str;
//                                        if (str.startsWith("[红包]"))
//                                            str1 = str.substring(4);
//                                    }
//                                }
//                                String str3 = jSONObject1.optJSONObject("template").optJSONObject("data").optJSONObject("body").optJSONArray("ac").getString(0);
//                                O00000Oo o00000Oo4 = this.O000000o;
//                                StringBuilder stringBuilder3 = new StringBuilder();
//                                stringBuilder3.append("红包连接 ");
//                                stringBuilder3.append(str3);
//                                O00000Oo.mCtx(o00000Oo4, stringBuilder3.toString());
//                                String str5 = URLDecoder.decode(str3);
//                                O00000Oo o00000Oo2 = this.O000000o;
//                                StringBuilder stringBuilder1 = new StringBuilder();
//                                stringBuilder1.append("红包连接 decode: ");
//                                stringBuilder1.append(str5);
//                                O00000Oo.mCtx(o00000Oo2, stringBuilder1.toString());
//                                Uri uri = Uri.parse(str5);
//                                String str6 = uri.getQueryParameter("ActionExtraParam");
//                                String str4 = uri.getQueryParameter("sender");
//                                String str2 = uri.getQueryParameter("note");
//                                if (!TextUtils.isEmpty(str2))
//                                    str1 = str2;
//                                str6 = Uri.parse(str6.replace("wangwang", "http")).getQueryParameter("hongbaoId");
//                                str2 = str4;
//                                if (str4 == null) {
//                                    str2 = str5.substring(str5.indexOf("sender=") + 7);
//                                    O00000Oo o00000Oo5 = this.O000000o;
//                                    StringBuilder stringBuilder4 = new StringBuilder();
//                                    stringBuilder4.append("s = ");
//                                    stringBuilder4.append(str2);
//                                    O00000Oo.mCtx(o00000Oo5, stringBuilder4.toString());
//                                    str2 = str2.split("&")[0];
//                                }
//                                O00000Oo o00000Oo3 = this.O000000o;
//                                StringBuilder stringBuilder2 = new StringBuilder();
//                                stringBuilder2.append("红包信息 =");
//                                stringBuilder2.append(str6);
//                                stringBuilder2.append(" sender=");
//                                stringBuilder2.append(str2);
//                                stringBuilder2.append(" remark: ");
//                                stringBuilder2.append(str1);
//                                stringBuilder2.append(" isAutoGrab: ");
//                                stringBuilder2.append(O00000Oo.classLoader(this.O000000o));
//                                O00000Oo.mCtx(o00000Oo3, stringBuilder2.toString());
//                                if (O00000Oo.classLoader(this.O000000o))
//                                    O00000Oo.O000000o(this.O000000o, O00000Oo.mCtx(this.O000000o), str1, str2, str6);
//                            }
//                        } catch (Throwable throwable) {
//                            O0Oo00.O000000o(throwable);
//                        }
//                    }
                }
            }});
            XposedHelpers.findAndHookMethod(Activity.class, "onCreate", new Object[]{Bundle.class, new XC_MethodHook() {
                protected void afterHookedMethod(XC_MethodHook.MethodHookParam param1MethodHookParam) {
                    try {
                        super.afterHookedMethod(param1MethodHookParam);
                    } catch (Throwable throwable1) {
                        throwable1.printStackTrace();
                    }
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("SimpleName==> ");
                    stringBuilder.append(param1MethodHookParam.thisObject.getClass().getSimpleName());
                    //   O0Oo00.O000000o(stringBuilder.toString());
                    if (param1MethodHookParam.thisObject.getClass().getSimpleName().contains("MainTabActivity"))
                        try {
                            //   (new Handler()).postDelayed(new -$$Lambda$O00000Oo$7$nngLZzAD9s4ZApnn6cPjZa1Sc-g(this), 1000L);
                            return;
                        } catch (Throwable throwable) {
                            //   O0Oo00.O000000o(throwable);
                            return;
                        }
//                    if (((XC_MethodHook.MethodHookParam)throwable).thisObject.getClass().getSimpleName().equals("WxChattingActvity"))
//                        try {
//                            if (!TextUtils.isEmpty(O00000Oo.createHongbao(this.O000000o))) {
//                                String str1;
//                                Context context = O00000Oo.mCtx(this.O000000o);
//                                if (O00000Oo.classLoader(this.O000000o)) {
//                                    str1 = "已开启抢包";
//                                } else {
//                                    str1 = "未开启开包";
//                                }
//                                Toast.makeText(context, str1, 0).show();
//                                O00000Oo o00000Oo = this.O000000o;
//                                if (O00000Oo.classLoader(this.O000000o)) {
//                                    str1 = "已开启抢包";
//                                } else {
//                                    str1 = "未开启开包";
//                                }
//                                O00000Oo.mCtx(o00000Oo, str1);
//                            } else {
//                                O0Oo00.O000000o("未启动配置没有");
//                            }
//                            Activity activity = (Activity)((XC_MethodHook.MethodHookParam)throwable).thisObject;
//                            String str = activity.getIntent().getStringExtra("conversationId");
//                            StringBuilder stringBuilder1 = new StringBuilder();
//                            stringBuilder1.append("mConversationId:");
//                            stringBuilder1.append(str);
//                            O0Oo00.O000000o(stringBuilder1.toString());
//                            if (!TextUtils.isEmpty(str)) {
//                                if (str.startsWith("tribe")) {
//                                    O00000Oo.classLoader(this.O000000o, O0o000.O000000o(str, "tribe"));
//                                } else {
//                                    O00000Oo.classLoader(this.O000000o, str);
//                                }
//                                O00000Oo o00000Oo = this.O000000o;
//                                stringBuilder1 = new StringBuilder();
//                                stringBuilder1.append("当前对话ConversationId:");
//                                stringBuilder1.append(O00000Oo.O00000oo(this.O000000o));
//                                O00000Oo.mCtx(o00000Oo, stringBuilder1.toString());
//                                O0o0000.O000000o((Context)activity, "target_account_id", O00000Oo.O00000oo(this.O000000o));
//                                return;
//                            }
//                        } catch (Throwable throwable1) {
//                            O0Oo00.O000000o(throwable1);
//                        }
                }
            }});

        } catch (Throwable throwable) {
            //O0Oo00.O000000o(throwable);

        }
    }

    private String getCurrentId() {
        //  if (TextUtils.isEmpty(this.currentId))
        //  this.currentId = O0o0000.O00000Oo(this.mCtx, "current_account_id");
        return this.currentId;
    }

    private void O00000oo(String paramString) {
//        if (!TextUtils.isEmpty(paramString)) {
//            O0Oo00.O00000Oo(paramString);
//            oO0Ooo00 oO0Ooo001 = this.O00000Oo;
//            if (oO0Ooo001 != null)
//                oO0Ooo001.O000000o(paramString);
//        }
    }

    private void O0000O0o() {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            public void run() {
                try {
                    ArrayList arrayList = new ArrayList();//O0O0OO.O000000o(0);
                    int i = arrayList.size();
                    if (i > 0) {
                        List list;
                        if (i > 50) {
                            // O00000Oo o00000Oo = this.O00000Oo;
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("上报地址总数量为：");
                            stringBuilder.append(i);
                            // O00000Oo.mCtx(o00000Oo, stringBuilder.toString());
                            list = arrayList.subList(0, 50);
                            //  o00000Oo = this.O00000Oo;
                            stringBuilder = new StringBuilder();
                            stringBuilder.append("分批次上报当前数量为：");
                            stringBuilder.append(list.size());
                            //   O00000Oo.mCtx(o00000Oo, stringBuilder.toString());
                            // OooOO.O000000o(O00000Oo.O0000OOo(this.O00000Oo).O000000o(), list, true);
                        } else {
                            //    OooOO.O000000o(O00000Oo.O0000OOo(this.O00000Oo).O000000o(), list, true);
                        }
                    }
                } catch (Exception exception) {
                    //    O0Oo00.O000000o(exception);
                }
                // this.O000000o.postDelayed(this, 5000L);
            }
        }, 5000L);
        handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            public void run() {
                try {
                    //  O00000Oo.O0000Oo0(this.O00000Oo);
                } catch (Exception exception) {
                    //O0Oo00.O000000o(exception);
                }
                // this.O000000o.postDelayed(this, 15000L);
            }
        }, 15000L);
        //this.O00000oo = O0o0000.O00000Oo(this.O00000Oo.O000000o(), "key_switch_auto_grab", false);
    }

    private void O0000OOo() {
//        ArrayList arrayList = O0O0OO.O00000Oo(0);
//        if (!arrayList.isEmpty()) {
//            StringBuilder stringBuilder = new StringBuilder();
//            stringBuilder.append("当前未回调的支付完成订单数量为：");
//            stringBuilder.append(arrayList.size());
//            stringBuilder.append("  正在开启定时发送任务");
//            O00000oo(stringBuilder.toString());
//            OooOO.O000000o(this.O00000Oo.O000000o(), arrayList);
//        }
//        O0OO0Oo.O000000o().O00000oO();
    }

    public String O000000o(String paramString) {
        return (!TextUtils.isEmpty(paramString) && paramString.startsWith("cnhhupan")) ? paramString.replaceFirst("cnhhupan", "cntaobao") : paramString;
    }

    public void O000000o() {
        this.O00000oO = null;
        //    O0OO0Oo.O000000o().O00000o();
        // new Handler(Looper.getMainLooper()).postDelayed(new - $$Lambda$O00000Oo$xyxYNlmYFncqAYMLM2LBHwqdADM(this), 1000L);
    }

    public void O000000o(long paramLong, int paramInt, String paramString1, String paramString2) {
        //  O0o00.O000000o(new - $$Lambda$O00000Oo$tzkeW - JGbYhckq - b_aIdudaIaVI(this, paramString2, paramString1, paramLong, paramInt));
    }

    void WangxinCreateInvocation(Context context) {
        this.classLoader = context.getClassLoader();
        this.mCtx = context.getApplicationContext();
//        O0O0o00.O000000o(this.mCtx);
//        this.O00000Oo = oO0000o0.O000000o(this.mCtx, this);
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append("hook成功:");
//        stringBuilder.append(O0Oo.O000000o(paramContext));
//        O00000oo(stringBuilder.toString());


        createHongbao();
        O0000O0o();
    }

    public void O00000Oo() {
        //   this.O00000Oo.O000000o(O0OO0Oo.O000000o().O00000o0());
    }

    public void O00000Oo(String paramString) {
//        if (this.O00000Oo != null) {
//            StringBuilder stringBuilder = new StringBuilder();
//            stringBuilder.append("准备WS创建链接：");
//            stringBuilder.append(paramString);
//            O00000oo(stringBuilder.toString());
//            try {
//                JSONObject jSONObject = new JSONObject(paramString);
//                this.O00000oO = jSONObject.getString("appId");
//                this.O00000oo = jSONObject.getBoolean("canWork");
//            } catch (JSONException jSONException) {
//                stringBuilder = new StringBuilder();
//                stringBuilder.append("配置有误:");
//                stringBuilder.append(jSONException.getMessage());
//                O00000oo(stringBuilder.toString());
//            }
//            if (!TextUtils.isEmpty(this.O00000oO)) {
//                O0o0000.O000000o(this.O00000Oo.O000000o(), "current_appkey", this.O00000oO);
//                O0o0000.O000000o(this.O00000Oo.O000000o(), "key_switch_auto_grab", this.O00000oo);
//                O0OO0Oo.O000000o().O000000o(this.O00000Oo.O000000o(), "ws://122.112.245.93:13482/", this);
//                return;
//            }
//            return;
//        }
    }

    public void O00000o() {
        // O0o00.O000000o().execute(new - $$Lambda$O00000Oo$UHqOldaCqZA4kMdwMA6D85 - vhDM(this));
    }

    public void O00000o(String paramString) {
//        int j;
//        try {
//            if (TextUtils.isEmpty(paramString)) {
//                StringBuilder stringBuilder = new StringBuilder();
//                stringBuilder.append("批量配置有误:");
//                stringBuilder.append(paramString);
//                O00000oo(stringBuilder.toString());
//                return;
//            }
//            j = (new JSONObject(paramString)).getInt("money");
//            if (j > 500 || j < 0) {
//                StringBuilder stringBuilder = new StringBuilder();
//                stringBuilder.append("输入金额有误:");
//                stringBuilder.append(j);
//                stringBuilder.append(" 请输入100，200，300，400，500");
//                O00000oo(stringBuilder.toString());
//                return;
//            }
//            if (TextUtils.isEmpty(this.O0000Oo0)) {
//                O00000oo("请先进入群聊天界面");
//                return;
//            }
//        } catch (JSONException jSONException) {
//            StringBuilder stringBuilder = new StringBuilder();
//            stringBuilder.append("配置有误:");
//            stringBuilder.append(jSONException.getMessage());
//            O00000oo(stringBuilder.toString());
//            return;
//        }
//        int i;
//        for (i = 0; i < 400; i++)
//            O000000o((j * 100), 1, this.O0000Oo0, O000000o(i));
    }

    public void O00000o0() {
//        if (O0O0OO.O000000o()) {
//            O00000oo("数据清洗成功！！！！此功能谨慎使用");
//            return;
//        }
//        O00000oo("数据清洗【失败】！！！！此功能谨慎使用");
    }

    public void O00000o0(String paramString) {
//        try {
//            if (TextUtils.isEmpty(paramString)) {
//                StringBuilder stringBuilder1 = new StringBuilder();
//                stringBuilder1.append("配置有误:");
//                stringBuilder1.append(paramString);
//                O00000oo(stringBuilder1.toString());
//                return;
//            }
//            JSONObject jSONObject = new JSONObject(paramString);
//            this.O00000oO = jSONObject.optString("appId");
//            this.O00000oo = jSONObject.optBoolean("canWork");
//            if (!TextUtils.isEmpty(this.O00000oO))
//                O0o0000.O000000o(this.O00000Oo.O000000o(), "current_appkey", this.O00000oO);
//            O0o0000.O000000o(this.O00000Oo.O000000o(), "key_switch_auto_grab", this.O00000oo);
//            StringBuilder stringBuilder = new StringBuilder();
//            stringBuilder.append("更新配置：");
//            stringBuilder.append(paramString);
//            O00000oo(stringBuilder.toString());
//            return;
//        } catch (JSONException jSONException) {
//            StringBuilder stringBuilder = new StringBuilder();
//            stringBuilder.append("配置有误:");
//            stringBuilder.append(jSONException.getMessage());
//            O00000oo(stringBuilder.toString());
//            return;
//        }
    }

    public void createHongbao(String paramString) {
//        int i = 0;
//        while (i < 100) {
//            try {
//                O0Oo00.O000000o("createPayOrde。。。");
//                i++;
//            } catch (Exception exception) {
//                StringBuilder stringBuilder = new StringBuilder();
//                stringBuilder.append("JSON解析异常:");
//                stringBuilder.append(exception);
//                O00000oo(stringBuilder.toString());
//                break;
//            }
//        }
    }

    public void onClosed(int paramInt, String paramString) {
//        oO0Ooo00 oO0Ooo001 = this.O00000Oo;
//        if (oO0Ooo001 != null) {
//            oO0Ooo001.O000000o(O0OO0Oo.O000000o().O00000o0());
//            StringBuilder stringBuilder = new StringBuilder();
//            stringBuilder.append("WS连接已断开：");
//            stringBuilder.append(paramString);
//            O00000oo(stringBuilder.toString());
//        }
    }

    public void onMessage(int paramInt, JSONObject paramJSONObject) {
//        if (paramInt == 1004)
//            try {
//                String str1 = paramJSONObject.optString("remark", "");
//                String str2 = paramJSONObject.getString("money");
//                String str3 = paramJSONObject.getString("conversationId");
//                paramInt = paramJSONObject.getInt("count");
//                O000000o(Long.parseLong(str2), paramInt, str3, str1);
//                return;
//            } catch (Throwable throwable) {
//                StringBuilder stringBuilder = new StringBuilder();
//                stringBuilder.append("产码失败异常:");
//                stringBuilder.append(throwable.getMessage());
//                stringBuilder.append("message: ");
//                stringBuilder.append(paramJSONObject);
//                O00000oo(stringBuilder.toString());
//            }
    }

    public void onOpen(String paramString) {
        // O00000oo("WS连接成功------没必要停留在监控界面了，请停留在支付宝（监控日志只适用于看日志信息，不排查问题就不要看了）");
        // OooOO.O000000o(this.mCtx, paramString, this.currentId);
    }

    /**
     * 2621186_8999_1580956120=========
     * 02-06 10:28:40.622 4434-4434/? D/appapp: Thu Feb 06 10:28:40 GMT+08:00 2020<----------->100=========
     * 02-06 10:28:40.622 4434-4434/? D/appapp: Thu Feb 06 10:28:40 GMT+08:00 2020<----------->0=========
     * 02-06 10:28:40.622 4434-4434/? D/appapp: Thu Feb 06 10:28:40 GMT+08:00 2020<----------->1=========
     * 02-06 10:28:40.622 4434-4434/? D/appapp: Thu Feb 06 10:28:40 GMT+08:00 2020<----------->2=========
     * 02-06 10:28:40.622 4434-4434/? D/appapp: Thu Feb 06 10:28:40 GMT+08:00 2020<----------->cnhhupan桐君阁大药房旗舰店=========
     * 02-06 10:28:40.622 4434-4434/? D/appapp: Thu Feb 06 10:28:40 GMT+08:00 2020<----------->6269114290840010752=========
     */
    public void createHongBao(Context mCtx, ClassLoader classLoader) {
        /**
         *  Object object1 = XposedHelpers.callMethod(XposedHelpers.callMethod(XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.alibaba.mobileim.YWAPI", this.O00000o), "getIMKitInstance", new Object[] { this.O0000OOo, O000000o }), "getIMCore", new Object[0]), "getWxAccount", new Object[0]);
         Object object2 = XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.alibaba.mobileim.lib.presenter.hongbao.HongbaoManager", this.O00000o), "getInstance", new Object[0]);
         ClassLoader classLoader = this.O00000o;
         */
        // (YWIMKit)YWAPI.getIMKitInstance(this.mUserContext.getShortUserId(), this.mUserContext.getAppkey());

        String userId = "ruoshuisixue";


        Class<?> ywApi = XposedHelpers.findClass("com.alibaba.mobileim.YWAPI", classLoader);
        LGU.D("123---------------");
        LGU.D(ywApi.getSimpleName());
        LGU.DAllMethod(ywApi);
        LGU.D("456---------------");


        Object imKitInstance = XposedHelpers.callStaticMethod(
                ywApi,
                "getIMKitInstance", new Class[]{String.class, String.class},
                new Object[]{userId, "12621186"});

       // LGU.DAllMethod(imKitInstance);
        LGU.D("789-----------");


        Object imCore = XposedHelpers.callMethod(imKitInstance, "getIMCore", new Object[0]);

        Object account = XposedHelpers.callMethod(imCore, "getWxAccount", new Object[0]);

        LGU.D("==========");
        LGU.DAllMethod(account);
        LGU.DAllField(account);
        LGU.D("===========0000000000");
        Class<?> hongbaoClass = XposedHelpers.findClass
                ("com.alibaba.mobileim.lib.presenter.hongbao.HongbaoManager", classLoader);
        LGU.D("========================");
        Object hongbaoManager = XposedHelpers.callStaticMethod(hongbaoClass,
                "getInstance");
        LGU.D("ppppppppppppppppppppppppppppppp");
        WangXinLinkInvocation linkInvocation = new WangXinLinkInvocation(mCtx, "1", "1", "1", "1");


        Class notifyClass = XposedHelpers.findClass("com.alibaba.mobileim.channel.event.IWxCallback", classLoader);


        Object wangxinLinkInvocation = Proxy.newProxyInstance(classLoader,
                new Class[]{notifyClass},
                linkInvocation);
        LGU.D("-----------------------------");


        String hongbaoId = (String) XposedHelpers.callMethod(hongbaoManager, "createHongbaoId",
                account
        );

        LGU.D("hongbaoId:" + hongbaoId);
        /**
         * (Account paramAccount, String paramString1, long paramLong1,
         * int paramInt1, int paramInt2,String paramString2,
         * String paramString3, long paramLong2, IWxCallback paramIWxCallback


         createHonebao(com.alibaba.mobileim.lib.presenter.account.Account,java.lang.String,long,
         int,int,java.lang.String,
         java.lang.String,long,com.alibaba.mobileim.channel.event.IWxCallback,
         int)#bestmatch

         *
         */
        XposedHelpers.callMethod(hongbaoManager, "createHongbao", new Class[]{
                        XposedHelpers.findClass("com.alibaba.mobileim.lib.presenter.account.Account", classLoader),
                        String.class,

                        Long.TYPE,

                        Integer.TYPE,
                        Integer.TYPE,
                        String.class,

                        String.class,
                        Long.TYPE,
                        notifyClass ,

                        Integer.TYPE

                },//12621186_4271_1581041555
                account,
                hongbaoId,//红包id
                100L,//amount

                0,//类型
                1,//size
                "2",//备注

                "cnhhupan桐君阁大药房旗舰店",//接受者
                6269114296439078912L,// msgid
                wangxinLinkInvocation,
                0);


    }
}
