package com.mfypay.pay3.hs;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.text.TextUtils;
import android.widget.ArrayAdapter;

import com.alibaba.fastjson.JSON;
import com.mfypay.pay3.b.QrCodeBean;
import com.mfypay.pay3.util.LGU;
import com.mfypay.pay3.util.SPU;

import java.util.HashMap;
import java.util.Map;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class LH implements IP {
    boolean isStart;

    static {
        System.loadLibrary("native-lib");
    }

    @Override
    public void load(XC_LoadPackage.LoadPackageParam param) {


        if (param.packageName.contains("com.chinamworld.main") && !isStart) {
            isStart = true;
            a(param);
        }
    }


    private void a(final XC_LoadPackage.LoadPackageParam param) {

        XposedHelpers.findAndHookMethod(Application.class, "attach", new Object[]{Context.class, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam methodHookParam) {
                r((Context) methodHookParam.thisObject);
                o(param, (Context) methodHookParam.thisObject);

            }
        }
        });


    }


    private void o(XC_LoadPackage.LoadPackageParam param, final Context context) {


        XposedBridge.hookAllMethods(XposedHelpers.findClass("com.ccb.myaccount.view.DebitAccountDetailFragment", context.getClassLoader()), "access$1800", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);
            }

            @Override
            protected void afterHookedMethod(final MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                LGU.D(param.getResult() + "result");

                ArrayAdapter arrayAdapter = (ArrayAdapter) param.getResult();

//                LGU.D(arrayAdapter.getItem(0) + "1111111==" + arrayAdapter.getCount());


                /**
                 *  public String Amount;
                 *     public String Crdeit_Id;
                 *     public String CurCode;
                 *     public String De_Remark1;
                 *     public String De_Remark2;
                 *     public String KeepAmt;
                 *     public String Occur_Date;
                 *     public String PayName;
                 *     public String Rec_Account;
                 *     public String Remark;
                 *     public String Tax_Amount;
                 *     public String TransSign;
                 *     public String Transdate;
                 *     public String Transplace;
                 *     public String Transtime;
                 */

                for (int j = 0; j < arrayAdapter.getCount(); j++) {
                    Object item = arrayAdapter.getItem(j);
                    Object amount = XposedHelpers.getObjectField(item, "Amount");
                    Object Crdeit_Id = XposedHelpers.getObjectField(item, "Crdeit_Id");
                    Object CurCode = XposedHelpers.getObjectField(item, "CurCode");
                    Object De_Remark1 = XposedHelpers.getObjectField(item, "De_Remark1");
                    Object De_Remark2 = XposedHelpers.getObjectField(item, "De_Remark2");
                    Object KeepAmt = XposedHelpers.getObjectField(item, "KeepAmt");
                    Object Occur_Date = XposedHelpers.getObjectField(item, "Occur_Date");
                    Object PayName = XposedHelpers.getObjectField(item, "PayName");
                    Object Rec_Account = XposedHelpers.getObjectField(item, "Rec_Account");
                    Object Remark = XposedHelpers.getObjectField(item, "Remark");
                    Object Tax_Amount = XposedHelpers.getObjectField(item, "Tax_Amount");
                    Object TransSign = XposedHelpers.getObjectField(item, "TransSign");
                    Object Transdate = XposedHelpers.getObjectField(item, "Transdate");
                    Object Transplace = XposedHelpers.getObjectField(item, "Transplace");
                    Object Transtime = XposedHelpers.getObjectField(item, "Transtime");
                    LGU.D("amount" + amount);
//{"Crdeit_Id":"","CurCode":"01","De_Remark1":"","De_Remark2":"","KeepAmt":"21.03","Occur_Date":"20190107","PayName":"支付宝（中国）网络技术有限公司","Rec_Account":"215500690","Remark":"支付机构提现","Tax_Amount":"","TransSign":"1","Transdate":"20190106","Transplace":"","Transtime":"232304","amount":"0.01"}
//2019-01-07 16:28:13.079 18536-18536/? D/appapp: M

                    Map<String, String> map = new HashMap<>();
                    map.put("amount", String.valueOf(amount));
                    map.put("Crdeit_Id", String.valueOf(Crdeit_Id));
                    map.put("CurCode", String.valueOf(CurCode));
                    map.put("De_Remark1", String.valueOf(De_Remark1));
                    map.put("De_Remark2", String.valueOf(De_Remark2));
                    map.put("KeepAmt", String.valueOf(KeepAmt));
                    map.put("Occur_Date", String.valueOf(Occur_Date));
                    map.put("PayName", String.valueOf(PayName));
                    map.put("Rec_Account", String.valueOf(Rec_Account));
                    map.put("Remark", String.valueOf(Remark));
                    map.put("Tax_Amount", String.valueOf(Tax_Amount));
                    map.put("TransSign", String.valueOf(TransSign));
                    map.put("Transdate", String.valueOf(Transdate));
                    map.put("Transplace", String.valueOf(Transplace));
                    map.put("Transtime", String.valueOf(Transtime));

                    LGU.D(JSON.toJSONString(map));

                    Intent intent = new Intent();
                    intent.putExtra("money", String.valueOf(amount));
                    intent.putExtra("time", String.valueOf(Transtime));
                    intent.putExtra("extra", JSON.toJSONString(map));
                    intent.putExtra("type", 10);
                    intent.putExtra("userName","userName");
                    intent.putExtra("no", String.valueOf(Transtime));


                    Object setvarParams = XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.ccb.framework.security.login.LoginUtils", context.getClassLoader()), "getLoginSetvarParams");


                    Object getUSERNAME = XposedHelpers.callMethod(setvarParams, "getUSERNAME");


                    LGU.D(getUSERNAME+"=============");
                    intent.putExtra("mobilePhone","mobilePhone");
                    intent.putExtra("userIdcard",String.valueOf(getUSERNAME));
                    intent.putExtra("mark","mark");

                    intent.setAction("store.imea1.result");
                    context.sendBroadcast(intent);

                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Class clazz = XposedHelpers.findClass("com.ccb.myaccount.view.DebitAccountDetailFragment", context.getClassLoader());

                        Object access300 = XposedHelpers.callStaticMethod(clazz, "access$300", param.args[0]);
                        Object get = XposedHelpers.callMethod(access300, "get", 0);

                        XposedHelpers.callStaticMethod(clazz, "access$400", param.args[0], get);
                    }
                }, 5000);


            }
        });






    }


    private void r(final Context ctx) {

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.yun.san.fu");
        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {


                try {


                    String type = intent.getStringExtra("type");
                    if (!TextUtils.isEmpty(type) && "bind".equalsIgnoreCase(type)) {
//
//                        intent.putExtra("mobilePhone", userInfo.getMobilePhone());
//                        intent.putExtra("userName", userInfo.getUserName());
//                        intent.putExtra("userIdcard", userInfo.getUserIdcard());
//                        intent.putExtra("type", 100);
//                        intent.setAction("store.imea1.result");
//                        context.sendBroadcast(intent);
//                        return;
                    }


                    Intent intent1 = null;//new Intent(ctx, XposedHelpers.findClass(stm(), ctx.getClassLoader()));
                    intent1.putExtra("mark", intent.getStringExtra("mark"));
                    intent1.putExtra("money", intent.getStringExtra("money"));

                    final String mk = intent.getStringExtra("mark");


                    if (SPU.getParam(ctx, "mark", "").equals(mk)) {

                        return;
                    }


                    intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    ctx.startActivity(intent1);

                } catch (Exception e) {
                    LGU.D(e.getLocalizedMessage());
                    e.printStackTrace();

                }

            }
        };
        ctx.registerReceiver(receiver, intentFilter);
    }


}
