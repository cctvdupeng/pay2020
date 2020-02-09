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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mfypay.pay3.b.AliUI;
import com.mfypay.pay3.util.LGU;
import com.mfypay.pay3.util.SPU;
import com.mfypay.pay3.util.StringUtil;
import com.mfypay.pay3.util.Util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

public class XXH implements IP {
    private boolean isStart;


    @Override
    public void load(XC_LoadPackage.LoadPackageParam param) {

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
                     o(param, activity);
                }
            }

        });


    }

    private void redooo(XC_LoadPackage.LoadPackageParam param, final Context ctx) {


        XposedBridge.hookAllMethods(XposedHelpers.findClass("ceb", ctx.getClassLoader()), "onLoadSuccess", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                LGU.DP(param);


                if (param.args != null) {
                    if (param.args.length > 0) {

                        if (param.args[0] != null) {
                            Object ccm = param.args[0];
                            LGU.DAllField(ccm);
                            if ("bvb".equalsIgnoreCase(ccm.getClass().getSimpleName())) {


                                String url = (String) getObjectField(ccm, "r");
                                String mark = (String) getObjectField(ccm, "n");
                                String amount = (String) getObjectField(ccm, "f");
                                String no = (String) getObjectField(ccm, "d");


                                String s = Util.FIELD2JSON(ccm);
                                LGU.D(s);
                                LGU.D(url);
                                Intent intent = new Intent();
                                intent.putExtra(money(), String.valueOf(amount));
                                intent.putExtra(mark(), mark);
                                intent.putExtra(no(), no);
                                intent.putExtra(ty(), 14);
                                intent.putExtra("extra", Util.FIELD2JSON(ccm));
                                intent.putExtra("userId", "xx" + String.valueOf(getObjectField(ccm, "c")));
                                intent.putExtra(url(), url);
                                intent.setAction(retaction());
                                ctx.sendBroadcast(intent);
                            }
                        }
                    }
                }


            }
        });


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


                    Object afi = XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.alibaba.wukong.auth.AuthService", activity.getClassLoader()), "getInstance");


                    Object card = XposedHelpers.callMethod(afi, "latestAuthInfo");

                    String accountName = (String) XposedHelpers.callMethod(card, "getNickname");
                    String mobile = (String) XposedHelpers.callMethod(card, "getMobile");


                    Object o = XposedHelpers.callStaticMethod(XposedHelpers.findClass("bwp", activity.getClassLoader()), "a");
                    Object c = XposedHelpers.callMethod(o, "c");


                    String nickName = (String) XposedHelpers.callMethod(o, "d");
                    login.setMobileNumber(String.valueOf(mobile).replace("+86-", ""));
                    login.setLogonId(mobile);
                    login.setShowName(nickName);
                    login.setUserId("xx" + String.valueOf(c));
                    login.setUserName(accountName);
                    login.setRealNamed(accountName);
                    login.setNick(accountName);

                    login.setLoginTime(String.valueOf(System.currentTimeMillis() / 1000));
                    login.setExtra(Util.FIELD2JSON(card));
                    login.setType("14");
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
                    final String extra = intent.getStringExtra("extra");
                    reSend(activity, toUser, extra);

                } else {
                    final String mk = intent.getStringExtra(mark());
                    final String my = intent.getStringExtra(money());
                    final String toUser = intent.getStringExtra("toUser");
                    if (!TextUtils.isEmpty(SPU.getParam(activity, mark(), "").toString()) && SPU.getParam(activity, mark(), "").equals(mk)) {
                        return;
                    }
                    SPU.setParam(activity, mark(), mk);
                    requestCode(activity, (my), mk, toUser.replace("xx", ""));
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


    /**
     * private com.alibaba.wukong.im.conversation.ConversationImpl idj.b(java.lang.String,java.lang.String,java.lang.Object)=>end
     * 9361879:111633825
     * desc
     * {"getStrangerSaftyMsgs":"1"}
     *
     * @param ctx
     * @param toUser
     */

    private void reSend(Activity ctx, String toUser, String extra) {

        JSONObject jsonObject = JSON.parseObject(extra);


        toUser = toUser.replace("xx", "");
        Map<String, String> map = new HashMap<>();
        map.put("getStrangerSaftyMsgs", "1");

        Object iMModule = XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.alibaba.wukong.im.context.IMModule", ctx.getClassLoader()), "getInstance");

        Object idj = XposedHelpers.callMethod(iMModule, "getConversationCache");

        Object conversation = XposedHelpers.callMethod(idj, "b", new Class[]{String.class, String.class, Object.class},
                toUser + ":" + getUserId(ctx), "desc", JSON.toJSONString(map)
        );


        Class<?> aClass = XposedHelpers.findClass("com.alibaba.android.dingtalk.redpackets.models.RedPacketsClusterObject", ctx.getClassLoader());

        Object redPacketsClusterObject = XposedHelpers.newInstance(aClass);

        XposedHelpers.setObjectField(redPacketsClusterObject, "alipayOrderString", jsonObject.getString("r"));
        XposedHelpers.setObjectField(redPacketsClusterObject, "alipayStatus", 0);
        XposedHelpers.setObjectField(redPacketsClusterObject, "amount", jsonObject.getString("f"));
        XposedHelpers.setObjectField(redPacketsClusterObject, "amountRange", null);
        XposedHelpers.setObjectField(redPacketsClusterObject, "businessId", jsonObject.getString("d"));
        XposedHelpers.setObjectField(redPacketsClusterObject, "cid", toUser + ":" + getUserId(ctx));
        XposedHelpers.setObjectField(redPacketsClusterObject, "clusterId", jsonObject.getString("e"));
        XposedHelpers.setObjectField(redPacketsClusterObject, "congratulations", jsonObject.getString("n"));
        XposedHelpers.setIntField(redPacketsClusterObject, "count", 0);

        XposedHelpers.setLongField(redPacketsClusterObject, "createTime", jsonObject.getLongValue("a"));
        XposedHelpers.setLongField(redPacketsClusterObject, "modifyTime", jsonObject.getLongValue("b"));

        XposedHelpers.setIntField(redPacketsClusterObject, "oid", 0);
        XposedHelpers.setIntField(redPacketsClusterObject, "pickDoneTime", 0);
        XposedHelpers.setIntField(redPacketsClusterObject, "pickPlanTime", 0);

        XposedHelpers.setIntField(redPacketsClusterObject, "pickTime", 0);
        XposedHelpers.setObjectField(redPacketsClusterObject, "receivers", null);
        XposedHelpers.setLongField(redPacketsClusterObject, "sender", Long.parseLong(getUserId(ctx)));
        XposedHelpers.setIntField(redPacketsClusterObject, "senderPayType", 0);

        XposedHelpers.setIntField(redPacketsClusterObject, "size", 1);
        XposedHelpers.setIntField(redPacketsClusterObject, "status", 1);
        XposedHelpers.setIntField(redPacketsClusterObject, "type", 1);

        Object redPacketsClusterObjectExt = XposedHelpers.newInstance(XposedHelpers.findClass("com.alibaba.android.dingtalk.redpackets.models.RedPacketsClusterObjectExt", ctx.getClassLoader()));
        XposedHelpers.setObjectField(redPacketsClusterObject, "receivers", null);

        XposedHelpers.setObjectField(redPacketsClusterObjectExt, "enterpriseB2CRedPacket", null);
        XposedHelpers.setObjectField(redPacketsClusterObjectExt, "festivalRedPacket", null);
        XposedHelpers.setObjectField(redPacketsClusterObjectExt, "redPacketBomb", null);

        XposedHelpers.setObjectField(redPacketsClusterObject, "ext", redPacketsClusterObjectExt);

        Object fragment = XposedHelpers.newInstance(XposedHelpers.findClass("com.alibaba.and roid.dingtalk.redpackets.fragments.SendNormalRedPacketsFragment", ctx.getClassLoader()));

        XposedHelpers.setIntField(fragment, "j", 0);

        XposedHelpers.setObjectField(fragment, "a", conversation);

        XposedHelpers.setBooleanField(fragment, "l", true);

        XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.alibaba.android.dingtalk.redpackets.fragments.SendFragment", ctx.getClassLoader()), "a",
                new Class[]{XposedHelpers.findClass("com.alibaba.android.dingtalk.redpackets.fragments.SendFragment", ctx.getClassLoader()), aClass},
                fragment
                , redPacketsClusterObject);

    }


    public String getUserId(Context ctx) {
        Object a = XposedHelpers.callStaticMethod(XposedHelpers.findClass("bwp", ctx.getClassLoader()), "a");
        Object userId = XposedHelpers.callMethod(a, "c");
        return String.valueOf(userId);
    }

    /**
     * @param ctx
     * @param amount
     * @param mark
     */
    private void requestCode(Activity ctx, String amount, String mark, String toUser) {
        toUser = toUser.replace("xx", "");

        Object cdi = XposedHelpers.callStaticMethod(XposedHelpers.findClass("bvt", ctx.getClassLoader()), "a");

        Object o2 = XposedHelpers.callStaticMethod(XposedHelpers.findClass("bvs", ctx.getClassLoader()), "a");
        Object b = XposedHelpers.callMethod(o2, "b");


        XposedHelpers.callMethod(cdi, "a",
                new Class[]{Long.class, String.class, String.class, Integer.class, List.class, Integer.class, String.class, String.class,
                        long.class, XposedHelpers.findClass("bvh", ctx.getClassLoader()), long.class, String.class, int.class, XposedHelpers.findClass("cdv", ctx.getClassLoader())},
                Long.parseLong((getUserId(ctx))), String.valueOf(b), amount, 1, null, 1, toUser + ":" + (getUserId(ctx)), mark, 0, null, 0, null, 0, null
        );


    }

    private static final String MAP_CLASS_NAME = "com.alibaba.android.rimet.biz.im.notification.MessageNotificationManager";
    private static final String MAP_FUNCTION_NAME = "a";

    private void o(final XC_LoadPackage.LoadPackageParam lpparam, final Context ctx) {

        final Class<?> message = findClass("com.alibaba.wukong.im.Message", lpparam.classLoader);

       XposedHelpers.findAndHookMethod(MAP_CLASS_NAME, lpparam.classLoader, MAP_FUNCTION_NAME, int.class, message, new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
LGU.DP(param);
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
        return "cn.xuexi.android";
    }


    public String startAlipay() {
        return "store.imea1.xx.start";
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
