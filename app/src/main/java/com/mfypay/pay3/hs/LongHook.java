package com.mfypay.pay3.hs;//package com.shoudaqian.apppay.hs;
//
//import android.app.Application;
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.content.IntentFilter;
//import android.text.TextUtils;
//
//import com.alibaba.fastjson.JSON;
//import com.mfypay.pay3.hs.IP;
//import com.mfypay.pay3.util.LGU;
//
//
//import java.lang.reflect.Field;
//import java.lang.reflect.Method;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.logging.Handler;
//
//import de.robv.android.xposed.XC_MethodHook;
//import de.robv.android.xposed.XposedBridge;
//import de.robv.android.xposed.XposedHelpers;
//import de.robv.android.xposed.callbacks.XC_LoadPackage;
//
//public class LongHook implements IP {
//    boolean isStart;
//
//    @Override
//    public void load(XC_LoadPackage.LoadPackageParam param) {
//        LGU.D("com.ccb.loongpay" + param.packageName);
//        if (param.packageName.contains("com.ccb.loongpay") && !isStart) {
//            isStart = true;
//            a(param);
//        }
//    }
//
//
//    private void a(final XC_LoadPackage.LoadPackageParam param) {
//
//        XposedHelpers.findAndHookMethod(Application.class, "attach", new Object[]{Context.class, new XC_MethodHook() {
//            @Override
//            protected void afterHookedMethod(MethodHookParam methodHookParam) {
//                r((Context) methodHookParam.thisObject);
//                oo((Context) methodHookParam.thisObject);
//
//            }
//        }
//        });
//
//
//    }
////com.ccb.common.sqlite.CcbSQLiteDatabase com.ccb.framework.tip.database.CcbTipDatabaseOpenHelper.getReadableDatabase()
//    private void oo(final Context ctx) {
//
//        XposedBridge.hookAllMethods(XposedHelpers.findClass("com.ccb.loongpay.exchange_record.LoongPayExchangeRecordAct", ctx.getClassLoader()), "initData", new XC_MethodHook() {
//            @Override
//            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                LGU.D(param.method + "");
//                super.beforeHookedMethod(param);
//            }
//
//            @Override
//            protected void afterHookedMethod(final MethodHookParam param) throws Throwable {
//
//                super.afterHookedMethod(param);
//
//                new android.os.Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        XposedHelpers.callMethod(param.thisObject,"initData");
//                    }
//                }, 5000);
//
//                /**
//                 * Ahn_TxnAmt 金额
//                 *ASS_ACCT_NO
//                 * ACCT_NO
//                 * ASS_ACCT_NAME
//                 * EVT_TRACE_ID_FIRST
//                 *
//                 * OnLn_Py_Txn_Ordr_ID
//                 *
//                 * Orig_TxnSrlNo
//                 *
//                 * TRD_TP_ECD
//                 *
//                 * Txn_Dt
//                 * Txn_Tm
//                 *
//                 *
//                 * Txn_Rmrk
//                 */
//
//
//
//
//
//                List dataList = (List) XposedHelpers.getObjectField(param.thisObject, "dataList");
//
//                for (Object o : dataList) {
//
//                    Object ahn_txnAmt = XposedHelpers.getObjectField(o, "Ahn_TxnAmt");
//                    Object ASS_ACCT_NO = XposedHelpers.getObjectField(o, "ASS_ACCT_NO");
//                    Object ACCT_NO = XposedHelpers.getObjectField(o, "ACCT_NO");
//                    Object ASS_ACCT_NAME = XposedHelpers.getObjectField(o, "ASS_ACCT_NAME");
//
//                    Object EVT_TRACE_ID_FIRST = XposedHelpers.getObjectField(o, "EVT_TRACE_ID_FIRST");
//
//                    Object OnLn_Py_Txn_Ordr_ID = XposedHelpers.getObjectField(o, "OnLn_Py_Txn_Ordr_ID");
//
//                    Object Orig_TxnSrlNo = XposedHelpers.getObjectField(o, "Orig_TxnSrlNo");
//
//
//                    Object TRD_TP_ECD = XposedHelpers.getObjectField(o, "TRD_TP_ECD");
//
//
//                    Object Txn_Dt = XposedHelpers.getObjectField(o, "Txn_Dt");
//
//                    Object Txn_Tm = XposedHelpers.getObjectField(o, "Txn_Tm");
//
//                    Object Txn_Rmrk = XposedHelpers.getObjectField(o, "Txn_Rmrk");
//
//
//                    Map<String,String> map=new HashMap<>();
//
//                    map.put("ahn_txnAmt",String.valueOf(ahn_txnAmt));
//                    map.put("ASS_ACCT_NO",String.valueOf(ASS_ACCT_NO));
//                    map.put("ACCT_NO",String.valueOf(ACCT_NO));
//                    map.put("ASS_ACCT_NAME",String.valueOf(ASS_ACCT_NAME));
//                    map.put("EVT_TRACE_ID_FIRST",String.valueOf(EVT_TRACE_ID_FIRST));
//                    map.put("OnLn_Py_Txn_Ordr_ID",String.valueOf(OnLn_Py_Txn_Ordr_ID));
//                    map.put("Orig_TxnSrlNo",String.valueOf(Orig_TxnSrlNo));
//                    map.put("TRD_TP_ECD",String.valueOf(TRD_TP_ECD));
//                    map.put("Txn_Dt",String.valueOf(Txn_Dt));
//                    map.put("Txn_Tm",String.valueOf(Txn_Tm));
//                    map.put("Txn_Rmrk",String.valueOf(Txn_Rmrk));
//
//
//
//                    Intent intent = new Intent();
//                    intent.putExtra("money", String.valueOf(ahn_txnAmt));
//                    intent.putExtra("time", String.valueOf(Txn_Dt)+String.valueOf(Txn_Tm));
//                    intent.putExtra("extra",JSON.toJSONString(map));
//                    intent.putExtra("no", String.valueOf(OnLn_Py_Txn_Ordr_ID));
//                    intent.putExtra("type", 10);
//                    intent.putExtra("mark","long");
//
//                    intent.setAction("store.imea1.result");
//
//                    ctx.sendBroadcast(intent);
//
//
//
//
//
//
//
//
//                }
//
//
//            }
//        });
//
//    }
//
//
//    private void r(final Context ctx) {
//
//        IntentFilter intentFilter = new IntentFilter();
//        intentFilter.addAction("com.yun.san.long");
//        LGU.D("注册成功");
//        BroadcastReceiver receiver = new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//
//
//                try {
//
//
////                    String type = intent.getStringExtra("type");
////                    if (!TextUtils.isEmpty(type) && "bind".equalsIgnoreCase(type)) {
////                        YUM userInfo = YUUtil.getYunUserInfo(context);
////                        intent.putExtra("mobilePhone", userInfo.getMobilePhone());
////                        intent.putExtra("userName", userInfo.getUserName());
////                        intent.putExtra("userIdcard", userInfo.getUserIdcard());
////                        intent.putExtra("type", 100);
////                        intent.setAction("store.imea1.result");
////                        context.sendBroadcast(intent);
////                        return;
////                    }
//                    LGU.D("0注册成功");
//
//                    Intent intent1 = new Intent(ctx, XposedHelpers.findClass("com.ccb.loongpay.exchange_record.LoongPayExchangeRecordAct", ctx.getClassLoader()));
//
//                    intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    ctx.startActivity(intent1);
//
//                } catch (Exception e) {
//                    LGU.D(e.getLocalizedMessage());
//                    e.printStackTrace();
//
//                }
//
//            }
//        };
//        ctx.registerReceiver(receiver, intentFilter);
//    }
//}
