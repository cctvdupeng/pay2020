package com.mfypay.pay3.hs;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.WebResourceResponse;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mfypay.pay3.b.AliUI;
import com.mfypay.pay3.m.AB;
import com.mfypay.pay3.o.ALO;
import com.mfypay.pay3.o.AO;
import com.mfypay.pay3.o.APO;
import com.mfypay.pay3.util.LGU;
import com.mfypay.pay3.util.PHU;
import com.mfypay.pay3.util.SPU;
import com.mfypay.pay3.util.StringUtil;
import com.mfypay.pay3.util.Util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static android.content.Context.MODE_PRIVATE;

public class AH implements IP {
    private boolean isStart;


    @Override
    public void load(XC_LoadPackage.LoadPackageParam param) {

        if (param.packageName.equals(pkgAlipay())) {

            a(param);
        }
    }

    private void a(final XC_LoadPackage.LoadPackageParam param) {


        XposedHelpers.findAndHookMethod(Activity.class, "onCreate", Bundle.class, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam methodHookParam) throws Throwable {
                super.afterHookedMethod(methodHookParam);

                LGU.D(methodHookParam.thisObject + "");
                if ((pkgAlipay().equals(param.processName)) && (!isStart)) {
                    LGU.D("注册支付宝");

                    isStart = true;
                    ClassLoader classLoader = param.classLoader;
                    Context context = (Context) methodHookParam.thisObject;

                    other(classLoader);
                    redooo(param, context);
                    r(context);
                    o(context);
//                    toAccountConfirm(classLoader, context);
//
//                    tranferAuto(classLoader, context);
//                    onTranstoBankFailed(classLoader, context);
//                    onTranstoBankSuccess(classLoader, context);
                }
            }

        });


    }

    private void redooo(XC_LoadPackage.LoadPackageParam param, final Context ctx) {


        XposedHelpers.findAndHookConstructor(XposedHelpers.findClass("com.alipay.mobile.socialcommonsdk.bizdata.contact.model.RecommendationFriend", ctx.getClassLoader()),
                XposedHelpers.findClass("com.alipay.mobile.socialcommonsdk.bizdata.contact.model.SyncContactModel", ctx.getClassLoader()),
                new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        super.afterHookedMethod(param);


                        Object contactModel = param.args[0];

                        Object msg = XposedHelpers.getObjectField(contactModel, "msg");
                        Object userId = XposedHelpers.getObjectField(msg, "userId");
                        Object alipayAccount = XposedHelpers.getObjectField(msg, "alipayAccount");

                        String url = "alipays://platformapi/startapp?appId=20000186&actionType=addfriend&source=by_home&userId=" + userId + "&loginId=" + alipayAccount;
                        LGU.D(url);
                        Intent intent = new Intent();
                        Uri uri = Uri.parse(url);
                        intent.setData(uri);
                        ctx.startActivity(intent);


                    }
                }
        );


        XposedHelpers.findAndHookConstructor(XposedHelpers.findClass("com.alipay.mobile.socialcommonsdk.bizdata.chat.model.ChatMsgObj", ctx.getClassLoader()),
                String.class,
                XposedHelpers.findClass("com.alipay.mobile.socialcommonsdk.bizdata.chat.model.SyncChatMsgModel", ctx.getClassLoader()),
                new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        super.afterHookedMethod(param);

                        Object chatMsgModel = param.thisObject;
                        String link = (String) XposedHelpers.getObjectField(chatMsgModel, "link");

                        String bizRemind = (String) XposedHelpers.getObjectField(chatMsgModel, "bizRemind");//[红包]
                        Integer action = (Integer) XposedHelpers.getObjectField(chatMsgModel, "action");//3
                        LGU.D("" + bizRemind);
                        LGU.D("" + link);

                        LGU.DP(param);


                        if (!TextUtils.isEmpty(link)) {
                            if (!"null".equalsIgnoreCase(link)) {
                                if (3 == action && bizRemind.indexOf("红包") != -1) {

                                    LGU.D("start===>" + bizRemind);
                                    Intent intent = new Intent();
                                    Uri uri = Uri.parse(link);
                                    intent.setData(uri);
                                    ctx.startActivity(intent);
                                }
                            }
                        }
                    }
                });


        XposedHelpers.findAndHookMethod("com.alipay.android.phone.discovery.envelope.get.SnsCouponDetailActivity", ctx.getApplicationContext().getClassLoader(), "a",
                XposedHelpers.findClass("com.alipay.giftprod.biz.crowd.gw.result.GiftCrowdDetailResult", ctx.getClassLoader()),
                boolean.class,
                boolean.class, new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        super.afterHookedMethod(param);
                        final Activity activity = (Activity) param.thisObject;
                        final View m = (View) XposedHelpers.getObjectField(activity, "m");
                        LGU.D("1111111" + m);
                        if (m != null) {
                            m.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    m.performClick();
                                }
                            }, 100);
//                    m.performClick();
                        }
                    }
                });


        XposedHelpers.findAndHookMethod(XposedHelpers.findClass("com.alipay.android.phone.discovery.envelope.received.ReceivedDetailActivity", ctx.getClassLoader()), "onCreate", Bundle.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);
            }

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                LGU.DP(param);
                Activity activity = (Activity) param.thisObject;
                Object o = activity.getIntent().getExtras();
                if ("ReceivedDetailActivity".equalsIgnoreCase(activity.getClass().getSimpleName())) {

                    if (o instanceof Bundle) {
                        LGU.DB((Bundle) o);
                        Bundle bo = (Bundle) o;
                        Map<String, String> extra = new HashMap<>();
                        for (String key : bo.keySet()) {
                            try {
                                extra.put(key, String.valueOf(bo.get(key)));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }


                        Object crowdNo = bo.get("crowdNo");
                        Object dataSource = bo.get("dataSource");

                        Object giftCrowdFlowInfo = XposedHelpers.getObjectField(dataSource, "giftCrowdFlowInfo");


                        String memo = (String) XposedHelpers.getObjectField(giftCrowdFlowInfo, "memo");

                        Object receiver = XposedHelpers.getObjectField(giftCrowdFlowInfo, "receiver");
                        String userId = (String) XposedHelpers.getObjectField(receiver, "userId");
                        Object giftCrowdInfo = XposedHelpers.getObjectField(dataSource, "giftCrowdInfo");
                        String amount = (String) XposedHelpers.getObjectField(giftCrowdInfo, "amount");
                        String remark = (String) XposedHelpers.getObjectField(giftCrowdInfo, "remark");
                        if (!TextUtils.isEmpty(memo)) {


                            String link = "alipays://platformapi/startapp?appId=88886666&target=groupPre&bizType=CROWD_COMMON_CASH&crowdNo=" + crowdNo
                                    + "&universalDetail=true&clientVersion=10.0.0-5&prevBiz=chat&sign=" + bo.get("sign");

                            extra.put("link", link);


                            Intent intent = new Intent();
                            intent.putExtra(no(), String.valueOf(crowdNo));
                            intent.putExtra(money(), amount);
                            intent.putExtra(mark(), remark);
                            try {
                                intent.putExtra("userId", userId);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            intent.putExtra(ty(), ten);
                            intent.putExtra("extra", JSON.toJSONString(extra));

                            intent.setAction(retaction());
                            ctx.sendBroadcast(intent);
                        }
                    }
//                    activity.finish();
                }
            }


        });


    }


    private void r(final Context ctx) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(startAlipay());


        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(final Context context, Intent intent) {


                if (!PHU.isAppRunning(context, pkgAlipay())) {

                    PHU.startAPP(context, pkgAPP());
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


                String rType = intent.getStringExtra("rType");
                AliUI login = ALiI.getLogin(context);

                SharedPreferences aliSp = context.getSharedPreferences("PAYEE_" + login.getUserId(), MODE_PRIVATE);
                LGU.D("1111" + aliSp.getString("NO_MONEY_URL_ONLINE", ""));
                Map<String, ?> all = aliSp.getAll();
                for (Map.Entry<String, ?> entry : all.entrySet()) {
                    LGU.D(entry.getKey() + "=" + entry.getValue());
                }
                login.setImageMiddleHiddenName(aliSp.getString("imageMiddleHiddenName", ""));
                login.setOfflineUrl(aliSp.getString("NO_MONEY_URL_OFFLINE", ""));
                login.setOnlineUrl(aliSp.getString("NO_MONEY_URL_ONLINE", ""));


                login.setType("1");
                if ("bind".equalsIgnoreCase(rType)) {
                    login.setStatus("1");
                    LGU.D(login.toString() + "111");
                    Intent bindIntent = new Intent("com.mfypay.autoali");
                    bindIntent.putExtra("aliInfo", JSONObject.toJSONString(login));
                    LGU.D(JSONObject.toJSONString(login) + "");
                    context.sendBroadcast(bindIntent);

                } else if ("unBind".equalsIgnoreCase(rType)) {
                    login.setStatus("0");
                    LGU.D(login.toString() + "222");
                    Intent unbindIntent = new Intent("com.mfypay.autoali");
                    unbindIntent.putExtra("aliInfo", JSONObject.toJSONString(login));
                    context.sendBroadcast(unbindIntent);

                } else if ("query".equalsIgnoreCase(rType)) {
                    long deploy = intent.getLongExtra("deploy", 5);
                    LGU.D("查单开始" + deploy);
                    ooo(ctx, deploy);
                    LGU.D("结束");


                } else {


                    final String mk = intent.getStringExtra(mark());
                    final String my = intent.getStringExtra(money());


                    if (!TextUtils.isEmpty(SPU.getParam(ctx, mark(), "").toString()) && SPU.getParam(ctx, mark(), "").equals(mk)) {
                        return;
                    }
                    SPU.setParam(ctx, mark(), mk);

                    if (intent.getIntExtra(ty(), 0) == seven) {


                        String cookie = CookieManager.getInstance().getCookie(alidomain());
                        AO.getOrder(context, mark(), cookie);
                        return;
                    }
                    if (intent.getIntExtra(ty(), 0) == 11) {
                        ALO.getLastOrder(ctx, false);
                        return;
                    }


                    final Object finalApi = getAliNetService(context);
                    final Object amountReq = getAliNetBean(context, (my), mk);
                    new Thread(new Runnable() {
                        public void run() {
                            Object consultSetAmount = XposedHelpers.callMethod(finalApi, setMoney(),
                                    new Object[]{amountReq});
                            Boolean success = (Boolean) XposedHelpers.getObjectField(consultSetAmount, "success");
                            String u = (String) XposedHelpers.getObjectField(consultSetAmount, qrUrl());
                            String printQrCodeUrl = (String) XposedHelpers.getObjectField(consultSetAmount, "printQrCodeUrl");
                            String codeId = (String) XposedHelpers.getObjectField(consultSetAmount, "codeId");
                            LGU.D(consultSetAmount.toString());

                            Intent intent = new Intent();
                            intent.putExtra(money(), String.valueOf(my));
                            intent.putExtra(mark(), mk);
                            intent.putExtra(no(), codeId);
                            intent.putExtra(ty(), one);
                            intent.putExtra("extra", consultSetAmount.toString());
                            try {
                                intent.putExtra("userId", ALiI.getLogin(ctx).getUserId());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            intent.putExtra("printQrCodeUrl", printQrCodeUrl);


                            intent.putExtra(url(), u);
                            intent.setAction(retaction());
                            context.sendBroadcast(intent);


                        }
                    }).start();
                }
            }
        };
        ctx.registerReceiver(receiver, intentFilter);


    }


    /**
     * 获取ali网络服务
     *
     * @param c
     * @return
     */
    private Object getAliNetService(Context c) {

        Object service = null;
        Object api = null;

        if (service == null) {
            Class localClass = XposedHelpers.findClass(ServiceUtil(), c.getClassLoader());
            Object[] interArr = new Object[1];
            interArr[0] = XposedHelpers.findClass(RpcService(), c.getClassLoader());
            service = XposedHelpers.callStaticMethod(localClass, getServiceByInterface(), interArr);
        }

        if (api == null) {
            Object apiParam = service;
            Object[] apiArr = new Object[1];
            apiArr[0] = XposedHelpers.findClass(CollectMoneyRpc(), c.getClassLoader());
            api = XposedHelpers.callMethod(apiParam, getRpcProxy(), apiArr);
        }
        return api;
    }


    private Object getAliNetBean(Context c, String my, final String mk) {


        final Object amountReq = XposedHelpers.newInstance(XposedHelpers.findClass(ConsultSetAmountReq(), c.getClassLoader()), new Object[0]);
        String sn = (String) XposedHelpers.callStaticMethod(XposedHelpers.findClass(AmnetUserInfo(), c.getClassLoader()), getSId(), new Object[0]);
        XposedHelpers.setObjectField(amountReq, at(), (my));
        XposedHelpers.setObjectField(amountReq, dc(), mk);
        XposedHelpers.setObjectField(amountReq, sd(), sn);
        return amountReq;
    }


    private void o(final Context ctx) {


//        XposedHelpers.findAndHookMethod(WebResourceResponse.class, "setData", InputStream.class, new XC_MethodHook() {
//            @Override
//            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//
//                super.beforeHookedMethod(param);
//            }
//
//            @Override
//            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                super.afterHookedMethod(param);
//
//                try {
//                    InputStream inputStream = (InputStream) param.args[0];
//                    ByteArrayOutputStream result = new ByteArrayOutputStream();
//                    byte[] buffer = new byte[1024];
//                    int length;
//                    while ((length = inputStream.read(buffer)) != -1) {
//                        result.write(buffer, 0, length);
//                    }
//                    String str = result.toString(StandardCharsets.UTF_8.name());
//
//
//                    LGU.DA("html=======" + str);
//                } catch (Exception e) {
//                    LGU.D("" + e.getMessage());
//                }
//
//            }
//        });

//        XposedHelpers.findAndHookConstructor(XposedHelpers.findClass("com.alipay.mobile.nebulacore.core.H5PageImpl", ctx.getClassLoader()),
//                Activity.class,
//                Bundle.class,
//                XposedHelpers.findClass("com.alipay.mobile.nebulacore.ui.H5ViewHolder", ctx.getClassLoader()), new XC_MethodHook() {
//                    @Override
//                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                        super.afterHookedMethod(param);
//                        Bundle objectField = (Bundle) XposedHelpers.getObjectField(param.thisObject, "f");
//
//
//                        LGU.D("url===" + objectField.get("url") + "");
//
////                LGU.DAllField(param.thisObject);
//                    }
//                });





        XposedBridge.hookAllMethods(XposedHelpers.findClass("com.alipay.mbxsgsg.d.a", ctx.getClassLoader()), "a", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                LGU.D("11111112222" + param.args[0].toString());
                String msg = param.args[0].toString();
                if (msg.contains("今日汇总")) {

                    Object tradeInfo = param.args[0];


                    String str = (String) XposedHelpers.callMethod(tradeInfo, "toString", new Object[0]);

                    String userId = StringUtil.SPLIT_CONTENT(str, "userId='", "'");
                    String content = StringUtil.SPLIT_CONTENT(str, "content='", "'");


                    String no = StringUtil.SPLIT_CONTENT(str, "tradeNO=", "&");

                    if (!TextUtils.isEmpty(no)) {

                        try {
                            JsonObject jsonObject = new Gson().fromJson(content, JsonObject.class);

                            String money = jsonObject.get("content").getAsString().replace("￥", "");
                            String mark = jsonObject.get("assistMsg2").getAsString();
                            if (mark.contains("转账") || mark.contains("收款")) {
                                mark = resetMark(StringUtil.SPLIT_CONTENT(str, "extraInfo='", "'"));
                            }
                            String tradeNO = StringUtil.SPLIT_CONTENT(str, "tradeNO=", "&");
                            Intent intent = new Intent();
                            intent.putExtra(no(), tradeNO);
                            intent.putExtra(money(), money);

                            intent.putExtra(mark(), mark);
                            try {
                                intent.putExtra("userId", userId);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            intent.putExtra(ty(), ten);
                            intent.putExtra("extra", StringUtil.SPLIT_CONTENT(str, "extraInfo='", "'"));
                            intent.setAction(retaction());
                            ctx.sendBroadcast(intent);
                        } catch (Exception e) {
                            splitTradeNo(ctx, str);

                        }

                    } else {

                        // ALO.getLastOrder(ctx);
                    }


                }
                super.beforeHookedMethod(param);
            }
        });


        XposedBridge.hookAllMethods(XposedHelpers.findClass(allSql(), ctx.getClassLoader()), insertInfo(), new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam methodHookParam) throws Throwable {

                Object tradeInfo = methodHookParam.args[0];
                LGU.D("==============" + tradeInfo);
                LGU.D("==============" + methodHookParam.args[1]);


                String str = (String) XposedHelpers.callMethod(tradeInfo, "toString", new Object[0]);

                String userId = StringUtil.SPLIT_CONTENT(str, "userId='", "'");
                String content = StringUtil.SPLIT_CONTENT(str, "content='", "'");


                String no = StringUtil.SPLIT_CONTENT(str, "tradeNO=", "&");

                if (!TextUtils.isEmpty(no)) {

                    try {
                        JsonObject jsonObject = new Gson().fromJson(content, JsonObject.class);

                        String money = jsonObject.get("content").getAsString().replace("￥", "");
                        String mark = jsonObject.get("assistMsg2").getAsString();
                        if (mark.contains("转账") || mark.contains("收款")) {
                            mark = resetMark(StringUtil.SPLIT_CONTENT(str, "extraInfo='", "'"));
                        }
                        String tradeNO = StringUtil.SPLIT_CONTENT(str, "tradeNO=", "&");
                        Intent intent = new Intent();
                        intent.putExtra(no(), tradeNO);
                        intent.putExtra(money(), money);

                        intent.putExtra(mark(), mark);
                        try {
                            intent.putExtra("userId", userId);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        intent.putExtra(ty(), ten);
                        intent.putExtra("extra", StringUtil.SPLIT_CONTENT(str, "extraInfo='", "'"));
                        intent.setAction(retaction());
                        ctx.sendBroadcast(intent);
                    } catch (Exception e) {
                        splitTradeNo(ctx, str);

                    }

                } else {
                    LGU.D("补单===111");
                    //ooo(ctx,5L);
                    LGU.D("补单===11111111");
                    // ALO.getLastOrder(ctx);
                }

                super.beforeHookedMethod(methodHookParam);
            }


        });


/**
 * hook service
 *
 */
        XposedBridge.hookAllMethods(XposedHelpers.findClass(serviceDAO(), ctx.getClassLoader()), insertInfo(), new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam methodHookParam) throws Throwable {


                Object o = methodHookParam.args[0];
                LGU.D("3333" + o);
                LGU.D(o.toString());
                XposedHelpers.callStaticMethod(XposedHelpers.findClass(mbxsgsg(), ctx.getClassLoader()), b(), new Object[]{o});
                String str = (String) XposedHelpers.callMethod(o, "toString", new Object[0]);
                LGU.D("4444444444" + str);
                String userId = StringUtil.SPLIT_CONTENT(str, "userId='", "'");
                LGU.D(userId);
                String content = StringUtil.SPLIT_CONTENT(str, "content='", "'");
                String tradeNO = StringUtil.SPLIT_CONTENT(str, "tradeNO=", "&");
                if (!TextUtils.isEmpty(tradeNO)) {

                    try {
                        JsonObject jsonObject = new Gson().fromJson(content, JsonObject.class);
                        String money = jsonObject.get("content").getAsString().replace("￥", "");
                        String mark = jsonObject.get("assistMsg2").getAsString();
                        if ("收款".equalsIgnoreCase(mark) || "转账".equalsIgnoreCase(mark)) {
                            mark = resetMark(StringUtil.SPLIT_CONTENT(str, "extraInfo='", "'"));
                        }

                        Intent localIntent = new Intent();
                        localIntent.putExtra(no(), tradeNO);
                        localIntent.putExtra(money(), money);
                        localIntent.putExtra(mark(), mark);
                        localIntent.putExtra(ty(), ten);
                        try {
                            localIntent.putExtra("userId", userId);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        localIntent.putExtra("extra", StringUtil.SPLIT_CONTENT(str, "extraInfo='", "'"));
                        localIntent.setAction(retaction());
                        ctx.sendBroadcast(localIntent);
                    } catch (Exception e) {
                        splitTradeNo(ctx, str);

                    }


                } else {


                }


                super.beforeHookedMethod(methodHookParam);
            }


        });

    }

    private String resetMark(String extraInfo) {
        LGU.D(extraInfo + "=======");
        try {
            JsonObject jsonObject = new Gson().fromJson(extraInfo, JsonObject.class);

            JsonArray content = jsonObject.get("content").getAsJsonArray();


            for (JsonElement element : content) {
                JsonObject asJsonObject = element.getAsJsonObject();
                JsonElement element1 = asJsonObject.get("title");
                LGU.D("title" + element1);
                LGU.D("--" + asJsonObject.get("content").getAsString());
                if (element1.toString().contains("转账备注")) {
                    LGU.D("--=====" + asJsonObject.get("content").getAsString());
                    return asJsonObject.get("content").getAsString();
                }
            }
        } catch (Exception e) {
            return "";
        }


        return "";
    }

    /**
     * 拆取订单
     *
     * @param ctx
     * @param str
     */
    public void splitTradeNo(Context ctx, String str) {
        final String cookie = CookieManager.getInstance().getCookie(alidomain());


        String tradeNo = StringUtil.getTextCenter(str, "tradeNO=", "&");

        if (!TextUtils.isEmpty(tradeNo)) {
            APO.getOrder(ctx, tradeNo, cookie);
        } else {

        }


    }


    private AB gAN(final XC_LoadPackage.LoadPackageParam param, final Context ctx) {
        Object aal = XposedHelpers.callMethod(XposedHelpers.callStaticMethod(
                XposedHelpers.findClass(AlipayApplication(), ctx.getClassLoader()),
                ge(), new Object[0]), gac(), new Object[0]);
        Object uih = XposedHelpers.callMethod(XposedHelpers.callStaticMethod(XposedHelpers.findClass(
                uih(), ctx.getClassLoader()),
                ge(), new Object[0]),
                gui(), new Object[]{aal});
        String li = (String) XposedHelpers.callMethod(uih, gli(), new Object[0]);
        String lm = (String) XposedHelpers.callMethod(uih, gmn(), new Object[0]);
        AB ab = new AB();
        ab.setLi(li);
        ab.setLm(lm);

        return ab;
    }

    public String gmn() {
        return "getMobileNumber";
    }

    public String gli() {
        return "getLogonId";
    }

    public String gui() {
        return "getUserInfo";
    }

    public String uih() {
        return "com.alipay.mobile.common.helper.UserInfoHelper";
    }

    public String gac() {
        return "getMicroApplicationContext";
    }

    public String ge() {
        return "getInstance";
    }

    public String AlipayApplication() {
        return "com.alipay.mobile.framework.AlipayApplication";
    }

    public String serviceDAO() {
        return "com.alipay.android.phone.messageboxstatic.biz.dao.ServiceDao";
    }


    public String mbxsgsg() {
        return "com.alipay.mbxsgsg.d.a";
    }

    public String b() {
        return "b";
    }


    public String allSql() {
        return "com.alipay.android.phone.messageboxstatic.biz.dao.TradeDao";
    }

    public String insertInfo() {
        return "insertMessageInfo";
    }

    public String sd() {
        return "sessionId";
    }

    public String at() {
        return "amount";
    }

    public String dc() {
        return "desc";
    }


    public String pkgAlipay() {
        return "com.eg.android.AlipayGphone";
    }

    public String pkgAPP() {
        return "android.app.Application";
    }

    public String attach() {
        return "attach";
    }

    public String startAlipay() {
        return "store.imea1.alipay.start";
    }

    public String mark() {
        return "mark";
    }

    public String money() {
        return "money";
    }

    public String ty() {
        return "type";
    }

    public String no() {
        return "no";
    }

    public String url() {
        return "url";
    }

    public String retaction() {
        return "store.imea1.result";
    }

    public String setMoney() {
        return "consultSetAmount";
    }

    public String qrUrl() {
        return "qrCodeUrl";
    }


    public String ServiceUtil() {

        return "com.alipay.mobile.beehive.util.ServiceUtil";
    }

    public String RpcService() {
        return "com.alipay.mobile.framework.service.common.RpcService";
    }

    public String CollectMoneyRpc() {
        return "com.alipay.transferprod.rpc.CollectMoneyRpc";
    }

    public String ConsultSetAmountReq() {
        return "com.alipay.transferprod.rpc.req.ConsultSetAmountReq";
    }

    public String AmnetUserInfo() {
        return "com.alipay.mobile.common.amnet.api.AmnetUserInfo";
    }

    public String getSId() {
        return "getSessionId";
    }

    public String getServiceByInterface() {
        return "getServiceByInterface";
    }

    public String getRpcProxy() {
        return "getRpcProxy";
    }

    public String alidomain() {
        return "https://mbillexprod.alipay.com";
    }


    private void other(ClassLoader classLoader) {

        try {
            Class<?> securityCheckClazz = XposedHelpers.findClass("com.alipay.mobile.base.security.CI", classLoader);

            Method[] declaredMethods = securityCheckClazz.getDeclaredMethods();
            for (Method method : declaredMethods) {
                LGU.D(method.toString());
            }


            XposedHelpers.findAndHookMethod(securityCheckClazz, "a", String.class, String.class, String.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    Object object = param.getResult();
                    XposedHelpers.setBooleanField(object, "a", false);
                    param.setResult(object);
                    super.afterHookedMethod(param);
                }
            });

            XposedHelpers.findAndHookMethod(securityCheckClazz, "a", Class.class, String.class, String.class, new XC_MethodReplacement() {
                @Override
                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                    return (byte) 1;
                }
            });
            XposedHelpers.findAndHookMethod(securityCheckClazz, "a", ClassLoader.class, String.class, new XC_MethodReplacement() {
                @Override
                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                    return (byte) 1;
                }
            });
            XposedHelpers.findAndHookMethod(securityCheckClazz, "a", new XC_MethodReplacement() {
                @Override
                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                    return false;
                }
            });

        } catch (Error | Exception e) {
            e.printStackTrace();
        }
    }


    public void ooo(final Context context, long deploy) {
        try {
            if (context == null) {
                LGU.D("查单失败");
                return;
            }
            ClassLoader localClassLoader = context.getClassLoader();
            final Object RpcService = XposedHelpers.callMethod(XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.alipay.mobile.beehive.util.ServiceUtil", localClassLoader),
                    "getServiceByInterface",
                    new Object[]{XposedHelpers.findClass("com.alipay.mobile.framework.service.common.RpcService", localClassLoader)}), "getRpcProxy",
                    new Object[]{XposedHelpers.findClass("com.alipay.mobilebill.biz.rpc.bill.v9.pb.BillListPBRPCService", localClassLoader)});
            final Object localObject2 = XposedHelpers.newInstance(XposedHelpers.findClass("com.alipay.mobilebill.common.service.model.pb.QueryListReq", localClassLoader), new Object[0]);
            XposedHelpers.setObjectField(localObject2, "category", "ALL");
            XposedHelpers.setObjectField(localObject2, "pageType", "WaitPayConsumeQuery");
            XposedHelpers.setObjectField(localObject2, "paging", XposedHelpers.newInstance(XposedHelpers.findClass("com.alipay.mobilebill.common.service.model.pb.PagingCondition", localClassLoader), new Object[0]));
            XposedHelpers.setObjectField(localObject2, "needMonthSeparator", Boolean.FALSE);
            XposedHelpers.setObjectField(localObject2, "scene", "BILL_LIST");
            long l = System.currentTimeMillis();
            XposedHelpers.setObjectField(localObject2, "startTime", l - deploy * 60 * 1000L);
            XposedHelpers.setObjectField(localObject2, "endTime", Long.valueOf(l));
            XposedHelpers.setObjectField(localObject2, "dateType", "day");
            new Thread() {
                public void run() {
                    List mList = (List) XposedHelpers.getObjectField(XposedHelpers.callMethod(RpcService, "query", new Object[]{localObject2}), "billListItems");
                    LGU.D(mList.toString());
                    AliUI login = ALiI.getLogin(context);
                    for (Object o : mList) {
                        String tradeNO = (String) XposedHelpers.getObjectField(o, "bizInNo");
                        String consumeFee = (String) XposedHelpers.getObjectField(o, "consumeFee"); //金额

                        String consumeTitle = (String) XposedHelpers.getObjectField(o, "consumeTitle"); //标题
                        if (consumeFee.startsWith("-")) {
                            return;
                        }

                        String consumeStatus = (String) XposedHelpers.getObjectField(o, "consumeStatus"); //consumeStatus
                        if (!"2".equalsIgnoreCase(consumeStatus)) {
                            return;
                        }


                        String money = consumeFee.replace("+", "").replaceAll(",", "");

                        if (consumeTitle.split("-")[0].contains("收钱码收款")) {
                            LGU.D(consumeTitle.split("-")[0]);
                            final String cookie = CookieManager.getInstance().getCookie("https://mbillexprod.alipay.com");
                            AO.getOrder(context, tradeNO, cookie);
                        }

                        Intent intent = new Intent();
                        intent.putExtra(no(), tradeNO);
                        intent.putExtra(money(), money);
                        intent.putExtra(mark(), consumeTitle.split("-")[0]);
                        intent.putExtra(ty(), ten);
                        try {
                            intent.putExtra("userId", login.getUserId());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        intent.putExtra("extra", o.toString());
                        intent.setAction(retaction());
                        context.sendBroadcast(intent);


                    }


                }
            }.start();

        } catch (Exception e) {//异常去启动支付宝
            e.printStackTrace();


        }
    }

    /**
     * 转卡
     *
     * @param classLoader
     * @param ctx
     */

    String pwd = "";

    private void tranferAuto(ClassLoader classLoader, Context ctx) {
        XposedHelpers.findAndHookMethod(XposedHelpers.findClass("com.alipay.mobile.transferapp.ui.TransferToCardFormActivity", classLoader), "a", new Object[]{new XC_MethodHook() {
            protected void afterHookedMethod(MethodHookParam methodHookParam)
                    throws Throwable {
                Object cardActivity = methodHookParam.thisObject;
                Intent intent = ((Activity) cardActivity).getIntent();

                Bundle params = intent.getBundleExtra("params");
                String innerPwd = params.getString("pwd");
                pwd = innerPwd;
                View next = (View) XposedHelpers.getObjectField(cardActivity, "m");
                next.performClick();
                super.afterHookedMethod(methodHookParam);
            }
        }
        });

        XposedHelpers.findAndHookMethod(XposedHelpers.findClass("com.alipay.mobile.transferapp.ui.TFToCardConfirmActivity", classLoader), "a", new Object[]{new XC_MethodHook() {
            protected void afterHookedMethod(MethodHookParam methodHookParam)
                    throws Throwable {
                Object cardActivity = methodHookParam.thisObject;
                View note = (View) XposedHelpers.getObjectField(cardActivity, "g");
                XposedHelpers.callMethod(note, "setText", "112");
                View next = (View) XposedHelpers.getObjectField(cardActivity, "h");
                next.performClick();
                super.afterHookedMethod(methodHookParam);
            }
        }
        });


        flybirdWindow(classLoader);
        popupFloatView(classLoader, ctx);
        uppasswordKeyBoard(classLoader, ctx);
    }


    private void toAccountFailed(ClassLoader paramClassLoader, final Context paramContext) {
        XposedHelpers.findAndHookMethod(XposedHelpers.findClass("com.alipay.mobile.transferapp.ui.TFToAccountConfirmActivity", paramClassLoader), "onPayFailed", new Object[]{"com.alipay.mobile.framework.service.ext.phonecashier.PhoneCashierPaymentResult", new XC_MethodHook() {
            protected void afterHookedMethod(MethodHookParam paramAnonymousMethodHookParam)
                    throws Throwable {

                node = "";
                view = null;

                super.afterHookedMethod(paramAnonymousMethodHookParam);
            }
        }
        });
    }

    private void toAccountSuccess(ClassLoader paramClassLoader, final Context paramContext) {
        XposedHelpers.findAndHookMethod(XposedHelpers.findClass("com.alipay.mobile.transferapp.ui.TFToAccountConfirmActivity", paramClassLoader), "onPaySuccess", new Object[]{"com.alipay.mobile.framework.service.ext.phonecashier.PhoneCashierPaymentResult", new XC_MethodHook() {
            protected void afterHookedMethod(MethodHookParam paramAnonymousMethodHookParam)
                    throws Throwable {

                node = "";
                view = null;


                super.afterHookedMethod(paramAnonymousMethodHookParam);
            }
        }
        });
    }


    /**
     * 转账到其他账号
     *
     * @param classLoader
     * @param ctx
     */

    private void toAccountConfirm(ClassLoader classLoader, Context ctx) {

        final Class confirmClass = XposedHelpers.findClass("com.alipay.mobile.transferapp.ui.TFToAccountConfirmActivity", classLoader);
        XposedHelpers.findAndHookMethod(confirmClass, "d", new Object[]{new XC_MethodHook() {
            protected void afterHookedMethod(MethodHookParam methodHookParam)
                    throws Throwable {

                Object thisObject = methodHookParam.thisObject;
                Object cardActivity = methodHookParam.thisObject;
                Intent intent = ((Activity) cardActivity).getIntent();

                Bundle params = intent.getBundleExtra("params");
                String innerPwd = params.getString("pwd");
                pwd = innerPwd;
                View h = (View) XposedHelpers.getObjectField(thisObject, "h");
                LGU.D(h + "");
                h.performClick();
                super.afterHookedMethod(methodHookParam);
            }
        }
        });
        flybirdWindow(classLoader);
        popupFloatView(classLoader, ctx);
        uppasswordKeyBoard(classLoader, ctx);
    }


    View view = null;
    String node = "";

    private void flybirdWindow(ClassLoader classLoader) {
        XposedHelpers.findAndHookMethod("com.flybird.FBView", classLoader, "updateEvent", new Object[]{String.class, String.class, new XC_MethodHook() {
            protected void afterHookedMethod(MethodHookParam methodHookParam)
                    throws Throwable {
                Object mTag = XposedHelpers.getObjectField(methodHookParam.thisObject, "mTag");
                String event = methodHookParam.args[1].toString();
                if ((event.equals("onclick")) && (mTag.equals("div")) && (node == "")) {
                    Object mNode = XposedHelpers.getObjectField(methodHookParam.thisObject, "mNode");
                    node = mNode.toString();
                    view = (View) XposedHelpers.getObjectField(methodHookParam.thisObject, "mView");
                }
                super.afterHookedMethod(methodHookParam);
            }
        }
        });
        XposedHelpers.findAndHookMethod("com.flybird.FBFrameLayout", classLoader, "onDraw", new Object[]{Canvas.class, new XC_MethodHook() {
            protected void afterHookedMethod(MethodHookParam methodHookParam)

                    throws Throwable {


                if ((view != null) && (view.equals(methodHookParam.thisObject))) {
                    view.performClick();
                    view = null;
                }
                super.afterHookedMethod(methodHookParam);
            }
        }
        });
    }


    private void popupFloatView(ClassLoader paramClassLoader, Context paramContext) {


        XposedHelpers.findAndHookMethod("com.alipay.wealth.common.ui.PopupFloatViewBuilder", paramClassLoader, "show", new Object[]{new XC_MethodHook() {
            protected void afterHookedMethod(MethodHookParam paramAnonymousMethodHookParam)
                    throws Throwable {

                try {
                    Object localObject1 = paramAnonymousMethodHookParam.thisObject;
                    Field localField = localObject1.getClass().getDeclaredField("c");
                    Object localObject2 = XposedHelpers.findClass("com.alipay.wealth.common.ui.PopupFloatView", localObject1.getClass().getClassLoader());
                    localField.setAccessible(true);
                    localObject1 = localField.get(localObject1);
                    localObject2 = ((Class) localObject2).getDeclaredField("m");
                    ((Field) localObject2).setAccessible(true);
                    ((Button) ((Field) localObject2).get(localObject1)).performClick();
                    super.afterHookedMethod(paramAnonymousMethodHookParam);
                    return;
                } catch (Error localError) {

                    XposedBridge.log("确认转账 error  " + localError);
                }
            }
        }
        });
    }


    private void uppasswordKeyBoard(ClassLoader paramClassLoader, Context ctx) {


        XposedHelpers.findAndHookMethod("com.alipay.android.app.safepaybase.alikeyboard.AlipayKeyboard", paramClassLoader, "initializeNumKeyboard", new Object[]{new XC_MethodHook() {
            protected void afterHookedMethod(MethodHookParam methodHookParam)
                    throws Throwable {
                ViewGroup viewGroup = (ViewGroup) ((LinearLayout) methodHookParam.thisObject).getChildAt(0);

                ArrayList localArrayList = new ArrayList();
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    View localObject2 = viewGroup.getChildAt(i);
                    for (int j = 0; j < ((ViewGroup) localObject2).getChildCount(); j++)
                        localArrayList.add(((ViewGroup) localObject2).getChildAt(j));
                }
                char[] chars = pwd.toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    View view = (View) localArrayList.get(Integer.parseInt("" + chars[i], 10) - 1);

                    presspwdAuto(view);
                }
                super.afterHookedMethod(methodHookParam);
            }
        }
        });
    }


    private void presspwdAuto(View view) {
        try {
            Object listenerInfo = XposedHelpers.callMethod(view, "getListenerInfo");
            LGU.D("getListenerInfo");

            View.OnTouchListener touchListener = (View.OnTouchListener) XposedHelpers.getObjectField(listenerInfo, "mOnTouchListener");
            MotionEvent motionEvent = MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), 0, view.getX(), view.getY(), 0);
            touchListener.onTouch(view, motionEvent);
            view.setOnTouchListener(touchListener);
            view.performClick();

        } catch (Exception paramView) {

            LGU.D("键盘输入error " + paramView);
        }

    }


    public void onTranstoBankFailed(ClassLoader paramClassLoader, final Context paramContext) {
        XposedHelpers.findAndHookMethod("com.alipay.mobile.transferapp.ui.TFToCardConfirmActivity", paramClassLoader, "onPayFailed", new Object[]{"com.alipay.mobile.framework.service.ext.phonecashier.PhoneCashierPaymentResult", new XC_MethodHook() {
            protected void afterHookedMethod(MethodHookParam methodHookParam)
                    throws Throwable {
                LGU.DP(methodHookParam);
                node = "";
                view = null;
                super.afterHookedMethod(methodHookParam);
            }
        }
        });
    }

    public void onTranstoBankSuccess(ClassLoader paramClassLoader, final Context paramContext) {
        XposedHelpers.findAndHookMethod("com.alipay.mobile.transferapp.ui.TFToCardConfirmActivity", paramClassLoader, "onPaySuccess", new Object[]{"com.alipay.mobile.framework.service.ext.phonecashier.PhoneCashierPaymentResult", new XC_MethodHook() {
            protected void afterHookedMethod(MethodHookParam methodHookParam)
                    throws Throwable {
                LGU.DP(methodHookParam);
                node = "";
                view = null;

                Object thisObject = methodHookParam.thisObject;
                String resultCode = (String) XposedHelpers.getObjectField(thisObject, "resultCode");
                String memo = (String) XposedHelpers.getObjectField(thisObject, "memo");
                String s = Util.FIELD2JSON(thisObject);

                super.afterHookedMethod(methodHookParam);
            }
        }
        });


    }
}
