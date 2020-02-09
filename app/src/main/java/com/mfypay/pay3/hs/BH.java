package com.mfypay.pay3.hs;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.CookieManager;

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

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static android.content.Context.MODE_PRIVATE;

public class BH implements IP {
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
                if ((pkgAlipay().equals(param.processName)) && (!isStart)) {
                    LGU.D("注册支付宝");

                    isStart = true;
                    ClassLoader classLoader = param.classLoader;
                    Activity context = (Activity) methodHookParam.thisObject;

                    //  other(classLoader);
                    redooo(param, context);
                    r(context);
                    //o(context);
                }
            }

        });


    }


    private void redooo(XC_LoadPackage.LoadPackageParam param, final Context ctx) {
        HU.HOOK("com.bullet.messenger.uikit.business.alipay.a", ctx.getClassLoader());
//        HU.HOOK("com.bullet.f.a.b.a.h", ctx.getClassLoader());
        HU.HOOK("com.bullet.messenger.uikit.business.redpacket.c.j", ctx.getClassLoader());

        XposedHelpers.findAndHookMethod("com.bullet.messenger.uikit.business.alipay.a", ctx.getClassLoader(), "a",
                XposedHelpers.findClass("com.bullet.f.a.b.a.h", ctx.getClassLoader()),
                new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        super.afterHookedMethod(param);

                        Object arg = param.args[0];
                        LGU.DAllField(arg);
                        String order = (String) XposedHelpers.getObjectField(arg, "a");
                        String sign = (String) XposedHelpers.getObjectField(arg, "b");
                        String orderSign = (String) XposedHelpers.getObjectField(arg, "c");
                        String decodeOrder = URLDecoder.decode(orderSign, "utf-8");
                        LGU.D("---------------" + orderSign);
                        //"amount":300.0,"out_order_no":"20190227154255146869196915011","out_request_no":"20190227154255146869196915011","order_title":"聊天宝红包"}
                        JSONObject jsonObject = JSON.parseObject(StringUtil.getTextCenter(decodeOrder, "biz_content=", "&"));
                        double amount = jsonObject.getDouble("amount");
                        String outOrderNo = jsonObject.getString("out_order_no");
                        LGU.D(amount + "==========");


                        Intent intent = new Intent();
                        intent.putExtra(money(), String.valueOf(amount));
                        intent.putExtra(mark(), order);
                        intent.putExtra(no(), outOrderNo);
                        intent.putExtra(ty(), 11);
                        intent.putExtra("extra", decodeOrder);
                        try {
                            Object chatUserInfo = XposedHelpers.callStaticMethod(XposedHelpers.findClass("smartisan.cloud.im.f", ctx.getClassLoader()), "getInstance");
                            Object user = XposedHelpers.callMethod(chatUserInfo, "getLoginUser");
                            intent.putExtra("userId", String.valueOf(XposedHelpers.getObjectField(user, "lmid")));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        intent.putExtra(url(), orderSign);
                        intent.setAction(retaction());
                        ctx.sendBroadcast(intent);


                        param.setResult(null);
                    }
                }

        );


        XposedHelpers.findAndHookMethod("com.bullet.messenger.uikit.business.preference.RedPacketConfig", ctx.getClassLoader(), "getC2GOneAmountUpperLimit", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);
            }

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                param.setResult(10000);
            }
        });


    }


    private void r(final Activity ctx) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(startAlipay());
        LGU.D("注册成功");
        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(final Context context, Intent intent) {
                LGU.D("注册成功");

                String rType = intent.getStringExtra("rType");
                AliUI login = new AliUI();//ALiI.getLogin(context);


                Object chatUserInfo = XposedHelpers.callStaticMethod(XposedHelpers.findClass("smartisan.cloud.im.f", ctx.getClassLoader()), "getInstance");

                Object user = XposedHelpers.callMethod(chatUserInfo, "getLoginUser");
                String name = (String) XposedHelpers.getObjectField(user, "name");
                login.setRealNamed(name);
                login.setUserName(name);

                login.setUserId(String.valueOf(XposedHelpers.getObjectField(user, "lmid")));
                login.setLogonId(String.valueOf(XposedHelpers.getObjectField(user, "account")));
                login.setUserAvatar(String.valueOf(XposedHelpers.getObjectField(user, "avatars")));
                login.setImageMiddleHiddenName(String.valueOf(XposedHelpers.getObjectField(user, "name")));
                login.setMobileNumber(String.valueOf(XposedHelpers.getObjectField(user, "phone")));
                login.setNick(String.valueOf(XposedHelpers.getObjectField(user, "name")));
                login.setExtra(Util.FIELD2JSON(user));
                login.setLoginTime(StringUtil.getTextCenter(String.valueOf(XposedHelpers.getObjectField(user, "avatars")), "updateTime=", "}"));
                login.setShowName(String.valueOf(XposedHelpers.getObjectField(user, "name")));

                login.setType("11");
                if ("bind".equalsIgnoreCase(rType)) {
                    login.setStatus("1");
                    LGU.D(login.toString() + "111");
                    Intent bindIntent = new Intent("com.mfypay.autoali");
                    bindIntent.putExtra("aliInfo", JSONObject.toJSONString(login));
                    LGU.D(JSONObject.toJSONString(login) + "");
                    context.sendBroadcast(bindIntent);

                } else if ("unBind".equalsIgnoreCase(rType)) {
                    login.setStatus("0");
                    Intent unbindIntent = new Intent("com.mfypay.autoali");
                    unbindIntent.putExtra("aliInfo", JSONObject.toJSONString(login));
                    context.sendBroadcast(unbindIntent);

                } else if ("query".equalsIgnoreCase(rType)) {
                    long deploy = intent.getLongExtra("deploy", 5);
                    LGU.D("查单开始" + deploy);

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

                    requestCode(ctx, (my), mk);

                }
            }
        };
        ctx.registerReceiver(receiver, intentFilter);


    }


    private void requestCode(Activity ctx, String amount, String mark) {

//        Object team = XposedHelpers.getStaticObjectField(XposedHelpers.findClass("com.netease.nimlib.sdk.msg.constant.SessionTypeEnum", ctx.getClassLoader()), "Team");
//        Object o = XposedHelpers.newInstance(XposedHelpers.findClass("com.bullet.messenger.uikit.business.redpacket.c.j",
//                ctx.getClassLoader()), new Class[]{String.class, XposedHelpers.findClass("com.netease.nimlib.sdk.msg.constant.SessionTypeEnum", ctx.getClassLoader())},
//                "1608542936",
//                team
//        );
        Class<?> aClass = XposedHelpers.findClass("com.bullet.f.a.b.a.ag", ctx.getClassLoader());
        Object ag = XposedHelpers.callStaticMethod(aClass, "a", new Class[]{int.class}, 1);
        LGU.D("ag"+ag);
//
//        XposedHelpers.callMethod(o, "a", new Class[]{String.class, Integer.class, String.class, String.class, aClass},
//                amount, 10, mark, "李小凡、发达、江", ag);


        //  paramString1 = (com.bullet.f.a.b.a.f)com.bullet.f.a.b.a.f.a().c(paramString1).b(paramInteger.intValue()).d(paramString2).a(this.d).a(this.e.getValue()).a(paramag).b(paramString3).build();

        Object f = XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.bullet.f.a.b.a.f", ctx.getClassLoader()), "a");
        Object c = XposedHelpers.callMethod(f, "c", new Class[]{String.class}, "10");
        Object b = XposedHelpers.callMethod(c, "b", new Class[]{int.class}, 10);
        Object d = XposedHelpers.callMethod(b, "d", new Class[]{String.class}, "232");

        Object a = XposedHelpers.callMethod(d, "a", new Class[]{String.class}, "1608542936");
        Object a1 = XposedHelpers.callMethod(a, "a", new Class[]{String.class}, "Team");

        Object a2 = XposedHelpers.callMethod(a1, "a", new Class[]{aClass}, ag);
        Object a3 = XposedHelpers.callMethod(a2, "b", new Class[]{String.class}, "李小凡、发达、江");
        Object build = XposedHelpers.callMethod(a3, "build");
        LGU.D("build" + build);

        //new com.bullet.messenger.uikit.business.alipay.a().a(paramString1, (Activity)getActivity()).delay(100L, TimeUnit.MILLISECONDS);

        Object o = XposedHelpers.newInstance(XposedHelpers.findClass("com.bullet.messenger.uikit.business.alipay.a", ctx.getClassLoader()));
        LGU.D("000" + o);

        LGU.DAllMethod(o);


        Object a4 =  (XposedHelpers.callMethod(o, "a", new Class[]{  Activity.class},   ctx));
        LGU.D("a4" + a4);






        Object delay = XposedHelpers.callMethod(a4, "delay", new Class[]{long.class, TimeUnit.class}, 100L, TimeUnit.MILLISECONDS);
        LGU.D("delay" + delay);

        Object instance = XposedHelpers.callStaticMethod(XposedHelpers.findClass("smartisan.cloud.im.c", ctx.getClassLoader()), "getInstance");


        XposedHelpers.callStaticMethod(XposedHelpers.findClass("c.e.b.j", ctx.getClassLoader()), "a", instance, "GrpcClient.getInstance()");

// paramString1.subscribeOn(a.c.j.a.a((Executor)paramInteger.getExecutor())).observeOn(a.c.a.b.a.a()).subscribe((g)new h(this), (g)new i(this));

        Object executor = XposedHelpers.callMethod(instance, "getExecutor");
        XposedHelpers.callStaticMethod(XposedHelpers.findClass("a.c.j.a", ctx.getClassLoader()), "a", new Class[]{java.util.concurrent.Executor.class}, executor);

        LGU.DAllMethod(delay);

        Object subscribeOn = XposedHelpers.callMethod(delay, "subscribeOn", new Class[]{Object.class}, executor);

        Object o1 = XposedHelpers.callMethod(subscribeOn, "observeOn", new Class[]{XposedHelpers.findClass("a.c.x", ctx.getClassLoader())}, XposedHelpers.callStaticMethod(XposedHelpers.findClass("a.c.a.b.a", ctx.getClassLoader()), "a"));


        Object subscribe = XposedHelpers.callMethod(o1, "subscribe", new Class[]{XposedHelpers.findClass("", ctx.getClassLoader()), XposedHelpers.findClass("", ctx.getClassLoader())}, null, null);
        LGU.D("subscribe" + subscribe);


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


                    }


                } else {


                }


                super.beforeHookedMethod(methodHookParam);
            }


        });


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
        return "com.bullet.messenger";
    }

    public String pkgAPP() {
        return "android.app.Application";
    }

    public String attach() {
        return "attach";
    }

    public String startAlipay() {
        return "store.imea1.chat.start";
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


}
