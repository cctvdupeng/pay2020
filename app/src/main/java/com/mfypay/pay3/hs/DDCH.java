package com.mfypay.pay3.hs;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.mfypay.pay3.b.AliUI;
import com.mfypay.pay3.util.LGU;
import com.mfypay.pay3.util.SPU;
import com.mfypay.pay3.util.StringUtil;
import com.mfypay.pay3.util.Util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.List;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.callMethod;
import static de.robv.android.xposed.XposedHelpers.callStaticMethod;
import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import static de.robv.android.xposed.XposedHelpers.findClass;
import static de.robv.android.xposed.XposedHelpers.findConstructorBestMatch;
import static de.robv.android.xposed.XposedHelpers.getObjectField;

public class DDCH implements IP {
    private boolean isStart;


    @Override
    public void load(XC_LoadPackage.LoadPackageParam param) {
LGU.D(param.packageName);
        if (param.packageName.equals(pkgAlipay())) {

            a(param);
        }
    }

    private void a(final XC_LoadPackage.LoadPackageParam param) {


        findAndHookMethod(Activity.class, "onCreate", Bundle.class, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam methodHookParam) throws Throwable {
                super.afterHookedMethod(methodHookParam);
                LGU.D(methodHookParam.thisObject + "====================");
                if ((pkgAlipay().equals(param.processName)) && (!isStart)) {


                    isStart = true;
                    ClassLoader classLoader = param.classLoader;
                    Activity activity = (Activity) methodHookParam.thisObject;
                    redooo(param, activity.getBaseContext());
                    r(activity);
                    //o(param, activity);
                }
            }

        });


    }

    private void redooo(XC_LoadPackage.LoadPackageParam param, final Context ctx) {
//

        HU.HOOK("com.alibaba.android.dingtalkbase.widgets.appcompatdialog.DDAppCompatAlertDialog", ctx.getClassLoader());
//
//        HU.HOOK("cev", ctx.getClassLoader());
//        HU.HOOK("cte", ctx.getClassLoader());
//        HU.HOOK("java.net.URL", ctx.getClassLoader());
//        HU.HOOK("com.alibaba.alimei.restfulapi.spi.okhttp.OKHttpServiceImpl", ctx.getClassLoader());

/**
 * public static void cqb.a(java.lang.String,int,int,int,int)=>start
 */
//
//        XposedBridge.hookAllMethods(XposedHelpers.findClass("com.alibaba.android.dingtalk.redpackets.models.RedPacketsClusterObject", ctx.getClassLoader()), "fromIDL", new XC_MethodHook() {
//            @Override
//            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                super.afterHookedMethod(param);
//
//
//                Object o = XposedHelpers.newInstance(XposedHelpers.findClass("com.alibaba.android.dingtalk.redpackets.fragments.SendNormalRedPacketsFragment", ctx.getClassLoader()));
//
//
//                XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.alibaba.android.dingtalk.redpackets.fragments.SendNormalRedPacketsFragment", ctx.getClassLoader()), "a",
//                        new Class[]{XposedHelpers.findClass("com.alibaba.android.dingtalk.redpackets.fragments.SendNormalRedPacketsFragment", ctx.getClassLoader()),
//                                XposedHelpers.findClass("com.alibaba.android.dingtalk.redpackets.models.RedPacketsClusterObject", ctx.getClassLoader())
//                        }, o, param.thisObject);
//
//
//            }
//        });


        XposedBridge.hookAllMethods(XposedHelpers.findClass("cqb", ctx.getClassLoader()), "a", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                LGU.DP(param);
            }
        });


//        HU.HOOK("com.alibaba.android.dingtalk.redpackets.RedPacketsInterfaceImpl", ctx.getClassLoader());


        XposedBridge.hookAllMethods(XposedHelpers.findClass("cpq", ctx.getClassLoader()), "caught", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                LGU.DP(param);

            }
        });


        XposedBridge.hookAllMethods(XposedHelpers.findClass("cpq", ctx.getClassLoader()), "onSuccess", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                LGU.DP(param);


                if (param.args != null) {
                    if (param.args.length > 0) {

                        if (param.args[0] != null) {
                            Object ccm = param.args[0];
                            if ("cdw".equalsIgnoreCase(ccm.getClass().getSimpleName())) {

                                LGU.DAllField(ccm);


                                String url = (String) getObjectField(ccm, "r");
                                String mark = (String) getObjectField(ccm, "n");
                                String amount = (String) getObjectField(ccm, "f");


                                String s = Util.FIELD2JSON(ccm);
                                LGU.D(s);
                                LGU.D(url);
                                Intent intent = new Intent();
                                intent.putExtra(money(), String.valueOf(amount));
                                intent.putExtra(mark(), mark);
                                intent.putExtra(no(), StringUtil.getTextCenter(url, "master_order_no=\"", "\"&"));
                                intent.putExtra(ty(), 12);
                                intent.putExtra("extra", s);
                                intent.putExtra("userId", String.valueOf(getObjectField(ccm, "c")));
                                intent.putExtra(url(), url);
                                intent.setAction(retaction());
                                ctx.sendBroadcast(intent);

                            }
                        }
                    }
                }


            }
        });


//        XposedBridge.hookAllMethods(XposedHelpers.findClass("cpq", ctx.getClassLoader()), "onException", new XC_MethodHook() {
//            @Override
//            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                super.afterHookedMethod(param);
//                LGU.DP(param);
//            }
//
//            @Override
//            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                LGU.DP(param);
//
//                super.beforeHookedMethod(param);
//            }
//        });

//        XposedBridge.hookAllMethods(XposedHelpers.findClass("cdw", ctx.getClassLoader()), "decode", new XC_MethodHook() {
//            @Override
//            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//
//                LGU.DP(param);
//
//
//                if (param.args != null) {
//                    if (param.args.length > 0) {
//
//                        if (param.args[0] != null) {
//                            Object ccm = param.args[0];
//                            if ("cdw".equalsIgnoreCase(ccm.getClass().getSimpleName())) {
//
//                                LGU.DAllField(ccm);
//
//
//                                String url = (String) getObjectField(ccm, "r");
//                                String mark = (String) getObjectField(ccm, "n");
//                                String amount = (String) getObjectField(ccm, "f");
//
//
//                                String s = Util.FIELD2JSON(ccm);
//                                LGU.D(s);
//                                LGU.D(url);
//                                Intent intent = new Intent();
//                                intent.putExtra(money(), String.valueOf(amount));
//                                intent.putExtra(mark(), mark);
//                                intent.putExtra(no(), StringUtil.getTextCenter(url, "master_order_no=\"", "\"&"));
//                                intent.putExtra(ty(), 12);
//                                intent.putExtra("extra", s);
//                                intent.putExtra("userId", String.valueOf(getObjectField(ccm, "c")));
//                                intent.putExtra(url(), url);
//                                intent.setAction(retaction());
//                                ctx.sendBroadcast(intent);
//
//                            } else if ("ccl".equalsIgnoreCase(ccm.getClass().getSimpleName())) {
//
//
//                                Object ccm2 = XposedHelpers.getObjectField(ccm, "a");
//                                Object ccq = XposedHelpers.getObjectField(ccm, "c");
//
//                                if (ccq == null)
//                                    return;
//
//
//                                String mark = (String) XposedHelpers.getObjectField(ccm2, "n");
//                                String money = (String) XposedHelpers.getObjectField(ccm2, "f");
//                                String tradeNO = (String) XposedHelpers.getObjectField(ccm2, "d");
//
//                                String userId = String.valueOf(XposedHelpers.getObjectField(ccq, "g"));
//
//                                Intent intent = new Intent();
//                                intent.putExtra(no(), tradeNO);
//                                intent.putExtra(money(), money);
//
//                                intent.putExtra(mark(), mark);
//
//                                intent.putExtra("userId", userId);
//
//                                intent.putExtra(ty(), 22);
//                                intent.putExtra("extra", Util.FIELD2JSON(ccm) + Util.FIELD2JSON(ccm2));
//                                intent.setAction(retaction());
//
//                                LGU.DAllField(ccm);
//                                LGU.DAllField(XposedHelpers.getObjectField(ccm, "a"));
//                                LGU.DAllField(XposedHelpers.getObjectField(ccm, "c"));
//
//
//                                ctx.sendBroadcast(intent);
//
////public java.lang.Integer ccq.h<------->h<---->2
//                                //   public java.lang.Integer ccq.e<------->e<---->0
//                            } else if ("cco".equalsIgnoreCase(ccm.getClass().getSimpleName())) {
//
//
//                                Object a = XposedHelpers.getObjectField(ccm, "a");
//                                Object b = XposedHelpers.getObjectField(ccm, "b");
//                                Object c = XposedHelpers.getObjectField(ccm, "c");
//                                List d = (List) XposedHelpers.getObjectField(ccm, "d");
//
//                                LGU.D(a + "==a");
//                                LGU.D(b + "==b");
//                                LGU.D(c + "==c");
//
//
//                                for (Object object : d) {
//
//                                    LGU.DAllField(object);
//
//                                    LGU.D(XposedHelpers.getObjectField(object, "l") + "-----");  //3 失效  1 失败 2成功
//                                }
//
//
//                            }else if ("ccf".equalsIgnoreCase(ccm.getClass().getSimpleName())) {
//
//
//                                Object a = XposedHelpers.getObjectField(ccm, "a");
//                                Object b = XposedHelpers.getObjectField(ccm, "b");
//                                Object c = XposedHelpers.getObjectField(ccm, "c");
//
//
//                                LGU.D(a + "==a");
//                                LGU.D(b + "==b");
//                                LGU.D(c + "==c");
//
//
//
//
//                            }
//
//
//                        }
//
//
//                    }
//                }
//
//                super.beforeHookedMethod(param);
//            }
//
//            @Override
//            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                super.afterHookedMethod(param);
//                LGU.DP(param);
//            }
//        });


    }


    private void r(final Activity activity) {
        try {
            Toast.makeText(activity, "广播注册中", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(startAlipay());
        LGU.D("ok");

        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(final Context context, Intent intent) {


                String rType = intent.getStringExtra("rType");
                AliUI login = new AliUI();

                if (TextUtils.isEmpty(rType)) {
                    rType = "";
                }
                if (rType.contains("bind")) {
                    Toast.makeText(context, "获取钉钉信息开始", Toast.LENGTH_LONG).show();
//AuthService.getInstance().latestAuthInfo()
                    Object afi = XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.alibaba.wukong.auth.AuthService", activity.getClassLoader()), "getInstance");


                    Object card = XposedHelpers.callMethod(afi, "latestAuthInfo");

                    String accountName = (String) XposedHelpers.callMethod(card, "getNickname");
                    String mobile = (String) XposedHelpers.callMethod(card, "getMobile");


                    Object o = XposedHelpers.callStaticMethod(XposedHelpers.findClass("cft", activity.getClassLoader()), "a");
                    Object c = XposedHelpers.callMethod(o, "c");


                    String nickName = (String) XposedHelpers.callMethod(o, "d");
                    login.setMobileNumber(String.valueOf(mobile).replace("+86-", ""));
                    login.setLogonId(mobile);
                    login.setShowName(nickName);
                    login.setUserId(String.valueOf(c));
                    login.setUserName(accountName);
                    login.setRealNamed(accountName);
                    login.setNick(accountName);

                    login.setLoginTime(String.valueOf(System.currentTimeMillis() / 1000));
                    login.setExtra(Util.FIELD2JSON(card));
                    login.setType("12");
                    Toast.makeText(context, "获取钉钉信息完成", Toast.LENGTH_LONG).show();
                }
                if ("bind".equalsIgnoreCase(rType)) {
                    Toast.makeText(context, "绑定!!!!", Toast.LENGTH_LONG).show();
                    login.setStatus("1");
                    LGU.D(login.toString() + "111");
                    Intent bindIntent = new Intent("com.mfypay.autoali");
                    bindIntent.putExtra("aliInfo", JSONObject.toJSONString(login));
                    LGU.D(JSONObject.toJSONString(login) + "========");
                    context.sendBroadcast(bindIntent);

                } else if ("unBind".equalsIgnoreCase(rType)) {
                    login.setStatus("0");
                    LGU.D(login.toString() + "222");
                    Intent unbindIntent = new Intent("com.mfypay.autoali");
                    unbindIntent.putExtra("aliInfo", JSONObject.toJSONString(login));
                    context.sendBroadcast(unbindIntent);

                } else if ("query".equalsIgnoreCase(rType)) {
                    long deploy = intent.getLongExtra("deploy", 5);

                } else if ("reSend".equalsIgnoreCase(rType)) {
                    final String toUser = intent.getStringExtra("toUser");
                    reSend(activity, toUser);

                } else {
                    final String mk = intent.getStringExtra(mark());
                    final String my = intent.getStringExtra(money());
                    final String toUser = intent.getStringExtra("toUser");
                    if (!TextUtils.isEmpty(SPU.getParam(activity, mark(), "").toString()) && SPU.getParam(activity, mark(), "").equals(mk)) {
                        return;
                    }
                    SPU.setParam(activity, mark(), mk);
                    requestCode(activity, (my), mk, toUser);
                }
            }
        };
        activity.getApplication().registerReceiver(receiver, intentFilter);
        try {
            Toast.makeText(activity, "广播注册成功", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    //toUser"9361879:111633825"

    private void reSend(Activity ctx, String toUser) {


        Object imService = XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.alibaba.wukong.im.IMEngine", ctx.getClassLoader()), "getIMService", XposedHelpers.findClass("com.alibaba.wukong.im.ConversationService", ctx.getClassLoader()));

        Object a = XposedHelpers.callStaticMethod(XposedHelpers.findClass("cft", ctx.getClassLoader()), "a");
        Object userId = XposedHelpers.callMethod(a, "c");

        XposedHelpers.callMethod(imService, "getConversation", new Class[]{XposedHelpers.findClass("com.alibaba.wukong.Callback", ctx.getClassLoader()), String.class, boolean.class}, null, toUser + ":" + String.valueOf(userId), true);


    }

    /**
     * @param ctx
     * @param amount
     * @param mark
     */
    private void requestCode(Activity ctx, String amount, String mark, String toUser) {


        Object cdi = XposedHelpers.callStaticMethod(XposedHelpers.findClass("ces", ctx.getClassLoader()), "a");

        Object o2 = XposedHelpers.callStaticMethod(XposedHelpers.findClass("cer", ctx.getClassLoader()), "a");
        Object b = XposedHelpers.callMethod(o2, "b");

        Object a = XposedHelpers.callStaticMethod(XposedHelpers.findClass("cft", ctx.getClassLoader()), "a");
        Object userId = XposedHelpers.callMethod(a, "c");


        XposedHelpers.callMethod(cdi, "a",
                new Class[]{Long.class, String.class, String.class, Integer.class, List.class, Integer.class, String.class, String.class,
                        long.class, XposedHelpers.findClass("ced", ctx.getClassLoader()), long.class, String.class, int.class, XposedHelpers.findClass("cpi", ctx.getClassLoader())},
                Long.parseLong(String.valueOf(userId)), String.valueOf(b), amount, 1, null, 1, toUser + ":" + String.valueOf(userId), mark, 0, null, 0, null, 0, null
        );


    }

    private static final String MAP_CLASS_NAME = "com.alibaba.android.rimet.biz.im.notification.MessageNotificationManager";
    private static final String MAP_FUNCTION_NAME = "a";

    private void o(final XC_LoadPackage.LoadPackageParam lpparam, final Context ctx) {

        final Class<?> message = findClass("com.alibaba.wukong.im.Message", lpparam.classLoader);

        findAndHookMethod(MAP_CLASS_NAME, lpparam.classLoader, MAP_FUNCTION_NAME, int.class, message, new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {

                        if (null != param.args[1]) {
                            Field messageContentFileld = param.args[1].getClass().getSuperclass().getSuperclass().getDeclaredField("mMessageContent");
                            String messageContent = messageContentFileld.get(param.args[1]).toString();
                            if (messageContent.startsWith("{\"tp\":902")) {
                                JSONObject jsonObject = JSONObject.parseObject(messageContent);
                                String ext = jsonObject.getJSONArray("multi").getJSONObject(0).getString("ext");
                                ext = ext.replace("\\", "").replace("\"{", "{").replace("}\"", "}");
                                jsonObject = JSONObject.parseObject(ext);
                                Long sender = jsonObject.getLong("sid");
                                String clusterId = jsonObject.getString("clusterid");

                                Object RedPacketsRpc = callStaticMethod(findClass("wl", lpparam.classLoader), "a");
                                Constructor constructor = findConstructorBestMatch(findClass("wl$9", lpparam.classLoader), RedPacketsRpc.getClass(), findClass("aae", lpparam.classLoader));

                                callMethod(callStaticMethod(findClass("cdk", lpparam.classLoader), "a", findClass("com.alibaba.android.dingtalk.redpackets.idl.service.RedEnvelopPickIService", lpparam.classLoader)), "pickRedEnvelopCluster", sender, clusterId, constructor.newInstance(RedPacketsRpc, null));
                            }
                        }
                    }
                }
        );


        findAndHookMethod("com.alibaba.android.dingtalk.redpackets.activities.PickRedPacketsActivity", ctx.getClassLoader(), MAP_FUNCTION_NAME, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                View mView = (View) getObjectField(param.thisObject, "h");
                mView.callOnClick();
                mView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        return false;
                    }
                });
            }
        });

        findAndHookMethod("com.alibaba.android.dingtalk.redpackets.activities.RedPacketsDetailActivity", ctx.getClassLoader(), "onCreate", Bundle.class, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                Object a = getObjectField(param.thisObject, "a");
                Object b = getObjectField(param.thisObject, "b");
                Object c = getObjectField(param.thisObject, "c");
                Object e = getObjectField(param.thisObject, "e");
                Object f = getObjectField(param.thisObject, "f");

                LGU.DAllField(a);
                LGU.DAllField(b);
                LGU.DAllField(c);
                LGU.DAllField(e);
                LGU.DAllField(f);
            }
        });
    }


    public String b() {
        return "b";
    }


    public String pkgAlipay() {
        return "com.alibaba.android.babylon";
    }


    public String startAlipay() {
        return "store.imea1.ddc.start";
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


}
