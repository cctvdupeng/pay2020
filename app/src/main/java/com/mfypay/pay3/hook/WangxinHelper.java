package com.mfypay.pay3.hook;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.JsonReader;

import com.mfypay.pay3.util.LGU;


import org.json.JSONObject;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;

public class WangxinHelper {
    public static Handler mHandler = new Handler(Looper.getMainLooper());

    private static ExecutorService threadPool = Executors.newCachedThreadPool();

    public static Object getAccount(Context paramContext) throws Throwable {
        Class<?> clazz = paramContext.getClassLoader().loadClass("com.alibaba.mobileim.gingko.WangXinApi");
        Object object = clazz.getDeclaredMethod("getInstance", new Class[0]).invoke(clazz, new Object[0]);
        return object.getClass().getDeclaredMethod("getAccount", new Class[0]).invoke(object, new Object[0]);
    }

    public static String getSender(Context paramContext) throws Throwable {
        Object object = getAccount(paramContext);
        object = object.getClass().getMethod("getUserId", new Class[0]).invoke(object, new Object[0]);
        Class<?> clazz = paramContext.getClassLoader().loadClass("com.alibaba.mobileim.channel.util.AccountUtils");
        return clazz.getDeclaredMethod("hupanIdToTbId", new Class[]{String.class}).invoke(clazz, new Object[]{object.toString()}).toString();
    }

    public static String getShowName(Context paramContext) throws Throwable {
        Object object = getAccount(paramContext);
        object = object.getClass().getMethod("getShowName", new Class[0]).invoke(object, new Object[0]);
        return (object == null) ? null : object.toString();
    }

    public static Object getWxAccount(Context mCtx) throws Throwable {
        Object object = getAccount(mCtx);
        object = object.getClass().getDeclaredMethod("getYWIMCore",
                new Class[0]).invoke(object, new Object[0]);
        return object.getClass().getSuperclass().getDeclaredMethod("getWxAccount", new Class[0]).invoke(object, new Object[0]);


    }

    /**
     * 打开红包
     *
     * @param context
     * @param userInfo
     * @param object
     * @throws Throwable
     */
    public static void openRedPacket(final Context context, final WXUserInfo userInfo, final JSONObject object)  {
        try {
            ClassLoader loader = context.getClassLoader();
            LGU.D("tryGetHongbao");
            Class<?> clazz1 = XposedHelpers.findClass("com.alibaba.mobileim.channel.EgoAccount", loader);
            Class<?> clazz2 = XposedHelpers.findClass("com.alibaba.mobileim.channel.event.IWxCallback", loader);
            Class<?> clazz3 = XposedHelpers.findClass("com.alibaba.mobileim.channel.SocketChannel", loader);
            Object object2 = clazz3.getDeclaredMethod("getInstance", new Class[0]).invoke(clazz3, new Object[0]);
            JSONObject jSONObject1 = new JSONObject();
            jSONObject1.put("method", "tryGetHongbao");
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("sender", userInfo.sender);
            jSONObject2.put("hongbao_id", object.optString("id"));
            jSONObject1.put("params", jSONObject2);
            jSONObject1.put("app_id", 2);
            jSONObject1.put("version", "4.6.0");
            jSONObject1.put("platform", "android");
            Object object3 = getWxAccount(context);
            object3 = XposedHelpers.callMethod(object3, "getWXContext");
            //object3.getClass().getDeclaredMethod("getWXContext", new Class[0]).invoke(object3, new Object[0]);

            InvocationHandler invocationHandler = new InvocationHandler() {
                public Object invoke(Object param1Object, Method param1Method, Object[] param1ArrayOfObject) throws Throwable {
                    LGU.D(param1Method.getName()+"=======");
                    LGU.D(param1Method.getName().equals("onSuccess")+"");

                    if (param1Method.getName().equals("onError")){
                        LGU.D(param1ArrayOfObject[0]+"");
                        LGU.D(param1ArrayOfObject[1]+"");
                    }

                    if (param1Method.getName().equals("onSuccess")) {

                        LGU.D( "---------ok");

                        Object[] os = (Object[]) param1ArrayOfObject[0];

                        LGU.D(os[0]+"ok");

                        Object rspData = XposedHelpers.callMethod(os[0], "getRspData");
                        LGU.D(rspData + "==ret");
                        JSONObject jsonObject = new JSONObject(rspData.toString());
                        if (jsonObject.optInt("code") == 0 && jsonObject.optString("msg").equals("success") &&
                                jsonObject.optJSONObject("result").optInt("status") == 2) {
                            LGU.D("打开红包接口成功");
                            LGU.D(jsonObject.toString());
                        }
//                      PayHelperUtils.sendmsg(context, ");
                        //     Socket.send(context, object.put("type", "open_red_packet_info").put("timeStamp", System.currentTimeMillis()).put("status", 1).put("name", userInfo.name).toString());
                        //   }
                    }
                    return null;
                }
            };
            Object object1 = Proxy.newProxyInstance(loader, new Class[]{clazz2}, invocationHandler);
            LGU.D("reqcascsiteapp");
            LGU.D(jSONObject1.toString());
            clazz3.getDeclaredMethod("reqCascSiteApp",
                    new Class[]{clazz1, clazz2, String.class, String.class, String.class, int.class}).
                    invoke(object2, new Object[]{object3, object1, jSONObject1.toString(), "hongbao", "hongbao", Integer.valueOf(10)});

        }catch (Throwable e){
            e.printStackTrace();
        }
    }

    public static void pushRedPacket(final Context context, final WXUserInfo userInfo, final JSONObject object) {
        threadPool.execute(new Runnable() {
            public void run() {
                for (int i = 0; i < 3; i++) {
                    try {
                        sendRedPacketNotify(context, userInfo, object);

                    } catch (Throwable throwable) {
                        throwable.printStackTrace();
                    } finally {
                        Exception exception = null;
                    }
                }
            }
        });
    }

    /**
     * 生成链接
     *
     * @param context
     * @param userInfo
     * @param amount
     * @throws Throwable
     */

    public static void sendRedPacket(final Context context, final WXUserInfo userInfo, final long amount) throws Throwable {
        ClassLoader loader = context.getClassLoader();
        Class<?> clazz4 = loader.loadClass("com.alibaba.mobileim.channel.util.WXUtil");
        Class<?> clazz1 = loader.loadClass("com.alibaba.mobileim.channel.event.IWxCallback");
        Class<?> clazz5 = loader.loadClass("com.alibaba.mobileim.lib.presenter.account.Account");
        Class<?> clazz2 = loader.loadClass("com.alibaba.mobileim.channel.EgoAccount");
        Class<?> clazz6 = loader.loadClass("com.alibaba.mobileim.lib.presenter.hongbao.HongbaoManager");
        Class<?> clazz3 = loader.loadClass("com.alibaba.mobileim.channel.SocketChannel");
        Object object2 = clazz3.getDeclaredMethod("getInstance", new Class[0]).invoke(clazz3, new Object[0]);
        Object object4 = getWxAccount(context);
        Object object3 = object4.getClass().getDeclaredMethod("getWXContext", new Class[0]).invoke(object4, new Object[0]);
        Object object5 = clazz6.getDeclaredMethod("getInstance", new Class[0]).invoke(clazz6, new Object[0]);
        final String redPacketId = object5.getClass().getDeclaredMethod("createHongbaoId", new Class[]{clazz5})
                .invoke(object5, new Object[]{object4}).toString();
        long l = ((Long) clazz4.getDeclaredMethod("getUUID", new Class[0]).invoke(clazz4, new Object[0])).longValue();

        JSONObject jSONObject = new JSONObject();
        jSONObject.put("method", "createHongbao");
        JSONObject ret = new JSONObject();
        ret.put("amount", amount);
        ret.put("type", 1);
        ret.put("size", 1);
        ret.put("note", "恭喜发财");
        ret.put("hongbao_id", redPacketId);
        ret.put("receiver", userInfo.cid);
        ret.put("message_id", l);
        ret.put("sub_type", 0);
        jSONObject.put("params", ret);
        jSONObject.put("app_id", 2);
        jSONObject.put("version", "4.6.0");
        jSONObject.put("platform", "android");
        ClassLoader classLoader = context.getClassLoader();
        InvocationHandler invocationHandler = new InvocationHandler() {
            public Object invoke(Object param1Object, Method param1Method, Object[] param1ArrayOfObject) {


                try {

                    if (param1Method.getName().equals("onSuccess")) {

                        Object[] objects = (Object[]) param1ArrayOfObject[0];

                        Object rspData = XposedHelpers.callMethod(objects[0], "getRspData");

                        JSONObject jSONObject = new JSONObject(rspData.toString());
                        if (jSONObject.optInt("code") == 0 && jSONObject.optString("msg").equals("success")) {
                            JSONObject ret = new JSONObject();
                            ret.put("name", userInfo.name);
                            ret.put("sender", userInfo.sender);
                            ret.put("amount", amount);
                            ret.put("cid", userInfo.cid);
                            ret.put("id", redPacketId);
                            ret.put("url", jSONObject.optJSONObject("result").optJSONObject("alipay_param").optString("url"));
                            ret.put("type", "new_red_packet_info");
                            ret.put("timeStamp", System.currentTimeMillis());
                            /**
                             * {"name":"ruoshuisixue","sender":"6269114296439078912L",
                             * "amount":100,"cid":"cntaobao桐君阁大药房旗舰店",
                             * "id":"12621186_7674_1581058596",
                             * "url":"service=\"alipay.fund.stdtrustee.order.create.pay\"&partner=\"2088401309894080\"&_input_charset=\"utf-8\"&notify_url=\"https:\/\/wwhongbao.taobao.com\/callback\/alipay\/notifyPaySuccess.do\"&out_order_no=\"12621186_7674_1581058596_75f04b83a91fd53617c4190a53525084_1\"&out_request_no=\"12621186_7674_1581058596_75f04b83a91fd53617c4190a53525084_1_p\"&product_code=\"SOCIAL_RED_PACKETS\"&scene_code=\"MERCHANT_COUPON\"&amount=\"1.00\"&pay_strategy=\"CASHIER_PAYMENT\"&receipt_strategy=\"INNER_ACCOUNT_RECEIPTS\"&platform=\"DEFAULT\"&channel=\"APP\"&order_title=\"淘宝现金红包\"&master_order_no=\"2020020710002001200504918627\"&order_type=\"DEDUCT_ORDER\"&extra_param=\"{\"payeeShowName\":\"淘宝现金红包\"}\"&pay_timeout=\"30m\"&order_expired_time=\"360d\"&sign=\"Ojw3583aVn%2FrvDl%2FN86%2BVmWKnYeXXBqv54uChpFDb4GsJFV9CfRiRmxBvAaUjHzQR8%2B7UNcfJypFgYmORCfn3heV9%2F9ATuJNmiS7ecHULipkkJUGL%2F74vmNpK6RBlWSQR2C2XUnIryFyrii9jwM4E6hYg0ie5UVnsVV1zqT2tDWr0tatQeNhKEk%2B%2BMo2uI%2B6bcaRBs0xmNiJCDClhqVzZ%2Bg6bqCVc2xzkHoK5LEfxlH3cBYsgWM1SIctQf3JK2FXONlMKfQXjBED%2FkxsNGObcxgtDBBwK31RzE8XQl7Fc7IQaDLmCL3eYnkd%2BtPdzTeRnAouZ7UcsGoWnKZt5kbosQ%3D%3D\"&sign_type=\"RSA\"",
                             * "type":"new_red_packet_info","timeStamp":1581058597251}
                             */


                            //  Socket.send(context, param1Object.toString());
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("xposed:");
                            stringBuilder.append(ret.toString());
                            LGU.D(stringBuilder.toString());
                            XposedBridge.log(stringBuilder.toString());
                        }
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                return null;
            }
        };
        Object object1 = Proxy.newProxyInstance(classLoader, new Class[]{clazz1}, invocationHandler);
        clazz3.getDeclaredMethod("reqCascSiteApp", new Class[]{clazz2, clazz1, String.class,
                String.class, String.class, int.class})
                .invoke(object2, new Object[]{object3, object1, jSONObject.toString(), "hongbao", "hongbao", Integer.valueOf(10)});
    }

    /**
     * 支付成功发送
     *
     * @param context
     * @param userInfo
     * @param object
     * @throws Throwable
     */

    private static void sendRedPacketNotify(final Context context, final WXUserInfo userInfo,
                                            final JSONObject object) throws Throwable {
        JSONObject jSONObject1 = new JSONObject();
        jSONObject1.put("method", "sendHongbaoNotify");
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("hongbao_id", object.optString("id"));
        jSONObject1.put("params", jSONObject2);
        jSONObject1.put("app_id", 2);
        jSONObject1.put("version", "4.6.0");
        jSONObject1.put("platform", "android");
        Class<?> clazz1 = context.getClassLoader().loadClass("com.alibaba.mobileim.channel.EgoAccount");
        Class<?> clazz2 = context.getClassLoader().loadClass("com.alibaba.mobileim.channel.event.IWxCallback");
        Class<?> clazz3 = context.getClassLoader().loadClass("com.alibaba.mobileim.channel.SocketChannel");
        Object object2 = clazz3.getDeclaredMethod("getInstance", new Class[0]).invoke(clazz3, new Object[0]);
        Object object3 = getWxAccount(context);
        object3 = object3.getClass().getDeclaredMethod("getWXContext", new Class[0]).invoke(object3, new Object[0]);
        ClassLoader classLoader = context.getClassLoader();
        InvocationHandler invocationHandler = new InvocationHandler() {
            public Object invoke(Object param1Object, Method param1Method, Object[] param1ArrayOfObject) throws Throwable {
                if (param1Method.getName().equals("onSuccess")) {
                    Object[] notifyObjs = (Object[]) param1ArrayOfObject[0];
                    String data = (String) XposedHelpers.callMethod(notifyObjs[0], "getRspData");
                    JSONObject json = new JSONObject(data);
                    //"msg":"success","code":0,"method":"sendHongbaoNotify","request_id":"0b1525e815811299255684568d05f0"}====
                    if (json.optInt("code") == 0 && json.optString("msg").equals("success")) {
                        LGU.D(json.toString() + "====");
                        WangxinHelper.mHandler.postDelayed(new Runnable() {
                            public void run() {
                                LGU.D("okok");
                            }
                        }, 1000L);
                    }

                }
                return null;
            }
        };
        Object object1 = Proxy.newProxyInstance(classLoader, new Class[]{clazz2}, invocationHandler);
        XposedHelpers.callMethod(object2, "reqCascSiteApp",
                new Class[]{clazz1, clazz2, String.class,
                        String.class, String.class, int.class},
                object3, object1, jSONObject1.toString(), "hongbao", "hongbao", Integer.valueOf(10)
        );

    }
}
