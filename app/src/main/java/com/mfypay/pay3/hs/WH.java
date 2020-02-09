package com.mfypay.pay3.hs;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;


import com.mfypay.pay3.util.LGU;
import com.mfypay.pay3.util.V;
import com.mfypay.pay3.util.XmlToJson;

import org.json.JSONObject;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class WH implements IP {
    private boolean isStart;

    static {
        System.loadLibrary("native-lib");
    }

    @Override
    public void load(XC_LoadPackage.LoadPackageParam param) {
        if (param.packageName.contains(pkgWechat()) && !isStart) {
            isStart = true;
            a(param);
        }
    }

    private void a(final XC_LoadPackage.LoadPackageParam param) {
        XposedHelpers.findAndHookMethod(Application.class, "onCreate", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam methodHookParam) throws Throwable {

                Context ctx = (Context) methodHookParam.thisObject;
                r(methodHookParam);
                pr(param, ctx);
                phr(param, ctx);

            }
        });


    }

    private void phr(XC_LoadPackage.LoadPackageParam param, final Context ctx) {



        XposedHelpers.findAndHookMethod("com.tencent.mm.ui.chatting.o",
                param.classLoader,
                "EM",
                String.class,
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        super.beforeHookedMethod(param);
                        String arg = (String) param.args[0];

                      LGU.D("arg:"+arg);

                        if (arg.equals("Test")){
                            param.setResult((true));
                        }

                    }
                }
        );



















        Object[] arrayOfObject1 = new Object[4];
        arrayOfObject1[0] = String.class;
        arrayOfObject1[1] = String.class;
        arrayOfObject1[2] = ContentValues.class;
        arrayOfObject1[3] = new XC_MethodHook() {
            protected void afterHookedMethod(MethodHookParam methodHookParam) {
            }

            protected void beforeHookedMethod(MethodHookParam methodHookParam) {
                LGU.D("-----------------");
                try {
                    ContentValues localContentValues = (ContentValues) methodHookParam.args[2];
                    String str1 = (String) methodHookParam.args[0];
                    LGU.D(str1 + "-----------------");
                    LGU.D(methodHookParam.args[0] + "0-----------------");
                    LGU.D(methodHookParam.args[1] + "1-----------------");
                    LGU.D(methodHookParam.args[2] + "2-----------------");
                    LGU.D(methodHookParam.args[0] + "1-----------------");
                    if (!TextUtils.isEmpty(str1)) {
                        if (!str1.equals("message"))
                            return;
                        Integer localInteger = localContentValues.getAsInteger("type");
                        if ((localInteger != null) && (localInteger.intValue() == 318767153)) {
                            String content = localContentValues.getAsString("content");
                            LGU.D(content + "0-----------------");

                            JSONObject msg = new XmlToJson.Builder(localContentValues.getAsString("content")).build().getJSONObject("msg");

                            String money = msg.getJSONObject("appmsg").getJSONObject("mmreader").getJSONObject("template_detail").getJSONObject("line_content").getJSONObject("topline").getJSONObject("value").getString("word");
                            String mark = msg.getJSONObject("appmsg").getJSONObject("mmreader").getJSONObject("template_detail").getJSONObject("line_content").getJSONObject("lines").getJSONArray("line").getJSONObject(0).getJSONObject("value").getString("word");
                            String no = msg.getJSONObject("appmsg").getString("template_id");
                            LGU.D("收到微信支付订单：" + no + "==" + money + "==" + mark);


                            Intent localIntent = new Intent();
                            localIntent.putExtra(no(), no);
                            localIntent.putExtra(money(), money);
                            localIntent.putExtra(mark(), mark);
                            localIntent.putExtra(ty(), IP.ten);
                            localIntent.putExtra("extra", content);
                            localIntent.putExtra("userId", content);
                            localIntent.setAction(retaction());
                            ctx.sendBroadcast(localIntent);


                        }
                    }
                } catch (Exception localException) {
                    localException.printStackTrace();

                }
            }
        };
        XposedHelpers.findAndHookMethod("com.tencent.wcdb.database.SQLiteDatabase", param.classLoader, "insert", arrayOfObject1);


    }

    private void pr(XC_LoadPackage.LoadPackageParam param, final Context ctx) {

        final ClassLoader classLoader = param.classLoader;
        XposedBridge.hookAllMethods(XposedHelpers.findClass(bs(), classLoader), a(), new XC_MethodHook() {
            protected void afterHookedMethod(MethodHookParam methodHookParam)
                    throws Throwable {
                String version = V.getVersion(ctx);
                double m;
                String mk;
                String u;
                Object thisObject = methodHookParam.thisObject;
                Class<?> aClass = thisObject.getClass();
                if (V667().equalsIgnoreCase(version)) {
                    m = ((Double) XposedHelpers.findField(aClass, hUL()).get(thisObject)).doubleValue();
                    mk = (String) XposedHelpers.findField(aClass, dc()).get(thisObject);
                    u = (String) XposedHelpers.findField(aClass, hUK()).get(thisObject);
                } else if (V672().equalsIgnoreCase(version)) {
                    m = ((Double) XposedHelpers.findField(aClass, iqu()).get(thisObject)).doubleValue();
                    mk = (String) XposedHelpers.findField(aClass, dc()).get(thisObject);
                    u = (String) XposedHelpers.findField(aClass, iqt()).get(thisObject);

                } else if (V673().equalsIgnoreCase(version)) {
                    m = ((Double) XposedHelpers.findField(aClass, iHP()).get(thisObject)).doubleValue();
                    mk = (String) XposedHelpers.findField(aClass, dc()).get(thisObject);
                    u = (String) XposedHelpers.findField(aClass, iHO()).get(thisObject);

                } else {
                    m = ((Double) XposedHelpers.findField(aClass, iHP()).get(thisObject)).doubleValue();
                    mk = (String) XposedHelpers.findField(aClass, dc()).get(thisObject);
                    u = (String) XposedHelpers.findField(aClass, iHO()).get(thisObject);
                }
                Intent intent = new Intent();
                intent.putExtra(money(), String.valueOf(m));
                intent.putExtra(mark(), mk);
                intent.putExtra(ty(), two);
                intent.putExtra(no(), mk);
                intent.putExtra(url(), u);
                intent.setAction(retaction());
                ctx.sendBroadcast(intent);
            }
        });


    }

    private void r(XC_MethodHook.MethodHookParam param) {
        IntentFilter intentFilter = new IntentFilter(startWechat());


        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {


                ClassLoader classLoader = context.getClassLoader();
                Object df = null;
                String version = V.getVersion(context);
                if (df == null) {
                    if (V667().equalsIgnoreCase(version)) {
                        df = XposedHelpers.callStaticMethod(XposedHelpers.findClass(au(), classLoader), df(), new Object[0]);

                    } else if (V672().equalsIgnoreCase(version)) {
                        df = XposedHelpers.callStaticMethod(XposedHelpers.findClass(g(), classLoader), cb(), new Object[0]);
                    } else if (V673().equalsIgnoreCase(version)) {
                        df = XposedHelpers.callStaticMethod(XposedHelpers.findClass(g(), classLoader), dkcb(), new Object[0]);
                    } else {
                        df = XposedHelpers.callStaticMethod(XposedHelpers.findClass(g(), classLoader), dkcb(), new Object[0]);

                    }
                }

                Class<?> clazz = null;
                if (clazz == null)
                    clazz = XposedHelpers.findClass(bs(), classLoader);


                double money = Double.parseDouble(intent.getStringExtra(money()));
                String mark = intent.getStringExtra(mark());

                XposedHelpers.callMethod(df, a(), new Object[]{
                        XposedHelpers.newInstance(
                                clazz, new Object[]{money, "1", mark}
                        ),
                        Integer.valueOf(0)});


            }
        };

        ((Context) param.thisObject).registerReceiver(receiver, intentFilter);
    }


    public native String pkgWechat();// = ;

    public native String startWechat();// = ;

    public native String V672();// = ;

    public native String V667();// =

    public native String V673();// =

    public native String dc();// =

    public native String bs();// =

    public native String a();// =

    public native String mark();

    public native String money();

    public native String ty();

    public native String no();

    public native String url();

    public native String retaction();

    public native String dkcb();

    public native String cb();

    public native String g();

    public native String df();

    public native String au();

    public native String iqt();

    public native String iqu();

    public native String hUK();

    public native String hUL();

    public native String iHP();

    public native String iHO();
}
