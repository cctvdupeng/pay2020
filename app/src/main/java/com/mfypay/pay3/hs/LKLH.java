package com.mfypay.pay3.hs;

import android.app.Activity;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mfypay.pay3.b.AliUI;
import com.mfypay.pay3.util.LGU;
import com.mfypay.pay3.util.PHU;
import com.mfypay.pay3.util.StringUtil;
import com.mfypay.pay3.util.Util;

import java.lang.reflect.Field;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class LKLH implements IP {
    private int isStart = 0;
    private String remark = "";

    @Override
    public void load(final XC_LoadPackage.LoadPackageParam param) {
        XposedHelpers.findAndHookMethod(Application.class, "attach", new Object[]{Context.class, new XC_MethodHook() {
            protected void afterHookedMethod(MethodHookParam paramAnonymousMethodHookParam)
                    throws Throwable {
                super.afterHookedMethod(paramAnonymousMethodHookParam);


                Context localContext = (Context) paramAnonymousMethodHookParam.args[0];
                ClassLoader clazzLoader = localContext.getClassLoader();
                if (isStart == 0&&param.packageName.equals("com.lakala.cloudpos.qcodeapp")) {
                    isStart = 1;
                    hook(paramAnonymousMethodHookParam, localContext);
                }
            }
        }});
    }

    private void hook(XC_MethodHook.MethodHookParam param, Context ctx) {
        final Class app = XposedHelpers.findClass("com.stub.StubApp", ctx.getClassLoader());
        XposedHelpers.findAndHookMethod(app, "onCreate", new Object[]{new XC_MethodHook() {
            protected void afterHookedMethod(MethodHookParam methodHookParam)
                    throws Throwable {
                super.afterHookedMethod(methodHookParam);
                Object paramClassLoader = methodHookParam.thisObject;
                Object localObject = XposedHelpers.findField(app, "ᵢˎ");

                ((Field) localObject).setAccessible(true);
                localObject = ((Field) localObject).get(methodHookParam.thisObject);
                Field localField = XposedHelpers.findField(app, "ᵢˑ");
                localField.setAccessible(true);
                Object ctx = localField.get(methodHookParam.thisObject);

                hook_real(localObject.getClass().getClassLoader(), (Context) ctx);
            }
        }});

    }

    private static final String LKL_QRCODE = "store.imea1.lkl.start";

    private void hook_real(ClassLoader classLoader, final Context ctx) {

        request(ctx);


        XposedHelpers.findAndHookMethod("com.lakala.qcodeapp.module.trade.activity.GatherCodeActivity", classLoader, "ny", new Object[]

                {
                        new XC_MethodHook() {
                            protected void beforeHookedMethod(MethodHookParam methodHookParam)
                                    throws Throwable {
                                Intent localIntent = ((Activity) methodHookParam.thisObject).getIntent();

                                String mark = localIntent.getStringExtra("mark");
                                if ((remark == null) || (!remark.equals(mark))) {
                                    remark = mark;
                                }
                                super.beforeHookedMethod(methodHookParam);
                            }
                        }
                });


//{code='null', codeImage='null', expiresIn='null', tradeNo='737410DA35BB4B938AB7E260807D869E', tradeTime='20190322234242', orderId='4d3601e416f94fa1b3d072500976e21a', amount='000000000001', payAmt='000000000001', payMode='ALIPAY', payTime='20190322234257', payNo='2019032222001494980546871676', batchNo='', seqNo='', srefno='032223317229', weOrderNo='052019032222001494980546871676', lklOrderNo='19032223317229', retAmt='null', retTime='null', needQuery='null'}
        XposedHelpers.findAndHookMethod("com.lakala.qcodeapp.module.trade.model.Imp.LKLCodePaySvrResponse", ctx.getClassLoader(), "getRetData", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                Object ret = param.thisObject;
                Object retMsg = XposedHelpers.getObjectField(ret, "retMsg");
                Object retCode = XposedHelpers.getObjectField(ret, "retCode");
                if (retCode != null && TextUtils.isEmpty(retCode.toString()) && "000000".equalsIgnoreCase(retCode.toString())) {
                    if (retMsg != null && TextUtils.isEmpty(retMsg.toString()) && "交易成功".equalsIgnoreCase(retMsg.toString())) {

                        String tradeNo = (String) XposedHelpers.getObjectField(ret, "tradeNo");
                        String amount = (String) XposedHelpers.getObjectField(ret, "amount");
                        String orderId = (String) XposedHelpers.getObjectField(ret, "orderId");

                        Intent intent = new Intent();
                        intent.putExtra("no", tradeNo);
                        intent.putExtra("money", String.valueOf(Integer.parseInt(amount) * 0.01));

                        intent.putExtra("mark", "null");

                        intent.putExtra("queueChannel", orderId);
                        String userId = (String) XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.lakala.qcodeapp.util.DataBaseOperateUtil", ctx.getClassLoader()), "getLoginName", ctx);

                        intent.putExtra("userId", userId);
                        intent.putExtra("type", 23);
                        intent.putExtra("strategy", false);
                        intent.putExtra("extra", Util.FIELD2JSON(param.getResult()));
                        intent.setAction("store.imea1.result");

                        ctx.sendBroadcast(intent);
                    }


                }


                LGU.DP(param);
            }
        });


        XposedHelpers.findAndHookMethod("com.lakala.qcodeapp.common.BaseActivity", classLoader, "onCreate", new Object[]

                {
                        Bundle.class, new XC_MethodHook() {
                    protected void afterHookedMethod(MethodHookParam paramAnonymousMethodHookParam)
                            throws Throwable {
                        super.afterHookedMethod(paramAnonymousMethodHookParam);
                        try {
                            if ((paramAnonymousMethodHookParam.thisObject.getClass().getName().equals("com.lakala.qcodeapp.module.signinfo.shopinfo.ShopInfoActivity")) ||
                                    (paramAnonymousMethodHookParam.thisObject.getClass().getName().equals("com.lakala.qcodeapp.module.trade.activity.GatherSuccessActivity"))) {
                                XposedHelpers.callMethod(paramAnonymousMethodHookParam.thisObject, "finish", new Object[0]);
//                                PHU.StartMe();
                            }
                            return;
                        } catch (Exception e) {

                            e.printStackTrace();

                        }
                    }
                }
                });
        XposedHelpers.findAndHookMethod("com.lakala.qcodeapp.common.BaseActivity", classLoader, "onPause", new Object[]

                {
                        new XC_MethodHook() {
                            protected void afterHookedMethod(MethodHookParam paramAnonymousMethodHookParam)
                                    throws Throwable {
                                super.afterHookedMethod(paramAnonymousMethodHookParam);
                                try {

                                    if (paramAnonymousMethodHookParam.thisObject.getClass().getName().equals("com.lakala.qcodeapp.module.trade.activity.GatherCodeActivity")) {
                                        XposedHelpers.callMethod(paramAnonymousMethodHookParam.thisObject, "finish", new Object[0]);
                                    }
                                    return;
                                } catch (Exception e) {

                                    e.printStackTrace();

                                }
                            }
                        }
                });
        XposedHelpers.findAndHookMethod("com.lakala.qcodeapp.module.trade.b.b$3", classLoader, "onSuccess", new Object[]

                {
                        "com.lakala.qcodeapp.module.trade.model.ILKLTransCodeResult", new XC_MethodHook() {
                    protected void afterHookedMethod(MethodHookParam methodHookParam)
                            throws Throwable {

                        String args = methodHookParam.args[0].toString();
                        String url = StringUtil.getTextCenter(args, "code='", "'");


                        Uri uri = Uri.parse(url);
                        String param = uri.getQueryParameter("p");


                        JSONObject paramJson = JSON.parseObject(new String(Base64.decode(param, Base64.DEFAULT)));

                        String amount = String.valueOf(paramJson.getIntValue("amount") * 0.01);
                        Intent intent = new Intent();
                        intent.putExtra("money", String.valueOf(amount));
                        intent.putExtra("mark", remark);
                        intent.putExtra("no", StringUtil.getTextCenter(args, "orderId='", "',"));
                        intent.putExtra("type", 13);
                        intent.putExtra("extra", args);
                        String userId = (String) XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.lakala.qcodeapp.util.DataBaseOperateUtil", ctx.getClassLoader()), "getLoginName", ctx);

                        intent.putExtra("userId", userId);
                        intent.putExtra("url", url);
                        intent.setAction(retaction());
                        ctx.sendBroadcast(intent);


                        super.afterHookedMethod(methodHookParam);
                    }
                }
                });
        XposedHelpers.findAndHookMethod("com.lakala.qcodeapp.module.trade.b.b$3", classLoader, "onFail", new Object[]

                {
                        String.class, String.class, Integer.TYPE, new XC_MethodHook() {
                    protected void afterHookedMethod(MethodHookParam paramAnonymousMethodHookParam)
                            throws Throwable {
                        // XposedBridge.log("com.lakala.qcodeapp.module.trade.b.b$3 ==== 结果 ==" + paramAnonymousMethodHookParam.args[0]);
                        super.afterHookedMethod(paramAnonymousMethodHookParam);
                    }
                }
                });
        XposedHelpers.findAndHookMethod("com.avos.avoscloud.AVPushConnectionManager", classLoader, "processPushMessage", new Object[]

                {
                        String.class, String.class, String.class, new XC_MethodHook() {
                    protected void afterHookedMethod(MethodHookParam methodHookParam)
                            throws Throwable {
                        super.afterHookedMethod(methodHookParam);
                        //{"action":"com.lakala.qcode.action","alert":"支付宝成功收款0.01元。","badge":"Increment","id":"6cf474477ee5431d83dca9cd925fdc4b","sound":"default","title":"{\"voiceAlert\":\"拉卡拉成功收款0.01元\",\"logNo\":\"19032320632179\",\"batNo\":\"500000\",\"tradeNo\":\"812019032322001494980550209549\",\"orderId\":\"6651a22ea4754b5b94f3addbb069da98\",\"icon\":\"http://download.lakala.com.cn/qcode/icon/tradeQuery/payType/alipay.png\",\"tradeDesc\":\"\",\"tradeStatusMsg\":\"成功收款\",\"merchantName\":\"福州市闽侯县贤仪钟表店\",\"payTypeName\":\"收款码\",\"tradeAmount\":\"0.01\",\"termId\":\"44007973\",\"sRefNo\":\"032320632179\",\"tradeTime\":\"20190323102808\",\"tradeTypeName\":\"支付宝\",\"payType\":\"2\",\"tradeStatus\":\"1\",\"shopNo\":\"822391076310201\",\"billNo\":\"500041\",\"tradeType\":\"1\"}"}=========

                        Object ret = methodHookParam.args[1];

                        JSONObject jsonObject = JSONObject.parseObject(ret.toString());

                        LGU.D("123"+"com.lakala.qcode.action".equalsIgnoreCase(jsonObject.getString("action")));
                        if ("com.lakala.qcode.action".equalsIgnoreCase(jsonObject.getString("action"))) {

                            JSONObject title = jsonObject.getJSONObject("title");

                            LGU.D("123"+title.toJSONString());

                            if ("成功收款".equalsIgnoreCase(title.getString("tradeStatusMsg")) && "1".equalsIgnoreCase(title.getString("tradeStatus"))) {
                                Intent intent = new Intent();
                                intent.putExtra("no", title.getString("tradeNo"));
                                intent.putExtra("money", String.valueOf(Double.parseDouble(title.getString("tradeAmount"))));

                                intent.putExtra("mark", "null");
                                intent.putExtra("queueChannel", title.getString("orderId"));

                                String userId = (String) XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.lakala.qcodeapp.util.DataBaseOperateUtil", ctx.getClassLoader()), "getLoginName", ctx);

                                intent.putExtra("userId", userId);
                                intent.putExtra("type", 23);
                                intent.putExtra("strategy", false);
                                intent.putExtra("extra", String.valueOf(ret));
                                intent.setAction("store.imea1.result");

                                ctx.sendBroadcast(intent);

                            }


                        }


                    }
                }
                });


    }

    private void request(final Context ctx) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(LKL_QRCODE);

        ctx.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {


                String rType = intent.getStringExtra("rType");
                AliUI login = new AliUI();

                if (TextUtils.isEmpty(rType)) {
                    rType = "";
                }
                if (rType.contains("bind")) {

                    String loginName = (String) XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.lakala.qcodeapp.util.DataBaseOperateUtil", ctx.getClassLoader()), "getLoginName", ctx);
                    String token = (String) XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.lakala.qcodeapp.util.DataBaseOperateUtil", ctx.getClassLoader()), "getToken", ctx);

                    login.setExtra(token);
                    login.setMobileNumber(String.valueOf(loginName).replace("+86-", ""));
                    login.setLogonId(loginName);
                    login.setShowName(loginName);
                    login.setUserId(String.valueOf(loginName));
                    login.setUserName(loginName);
                    login.setRealNamed(loginName);
                    login.setNick(loginName);

                    login.setLoginTime(String.valueOf(System.currentTimeMillis() / 1000));

                    login.setType("13");

                }
                if ("bind".equalsIgnoreCase(rType)) {
                    Toast.makeText(context, "绑定!!!!", Toast.LENGTH_LONG).show();
                    login.setStatus("1");
                    LGU.D(login.toString() + "111");
                    Intent bindIntent = new Intent("com.mfypay.autoali");
                    bindIntent.putExtra("aliInfo", JSONObject.toJSONString(login));
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


                } else {
                    try {
                        Intent localIntent = new Intent();
                        localIntent.setClass(ctx, XposedHelpers.findClass("com.lakala.qcodeapp.module.trade.activity.GatherCodeActivity", ctx.getClassLoader()));
                        localIntent.putExtra("mark", intent.getStringExtra("mark"));
                        localIntent.putExtra("sum", intent.getStringExtra("money"));
                        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//268435456
                        ctx.startActivity(localIntent);
                        return;
                    } catch (Exception e) {
                        LGU.D(e.getLocalizedMessage());
                    }
                }


            }

        }, intentFilter);
    }

    public String retaction() {
        return "store.imea1.result";
    }
}
