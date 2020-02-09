package com.mfypay.pay3.hook;

import android.content.Context;
import android.widget.EditText;

import com.mfypay.pay3.util.LGU;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

public class WXClass {
    public static void hook(final Context context, final WXUserInfo userInfo, ClassLoader classLoader) {
        LGU.D("0000000000onResume");
        Class<?> aClass = XposedHelpers.findClass("com.alibaba.mobileim.kit.chat.ChattingFragment", classLoader);

        XposedHelpers.findAndHookMethod(aClass, "onResume", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                LGU.D("==============");
                final String cid = (String)
                        XposedHelpers.getObjectField(param.thisObject,
                                "cvsId");//field.get(param1MethodHookParam.thisObject).toString();
                LGU.D(cid);


                Object replyBar = XposedHelpers.getObjectField(param.thisObject, "chattingReplyBar");
                final EditText inputText = (EditText) XposedHelpers.getObjectField(replyBar, "mInputText");
                inputText.post(new Runnable() {
                    @Override
                    public void run() {

                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("name = ");
                        stringBuilder.append(userInfo.name);
                        stringBuilder.append("\ncid = ");
                        stringBuilder.append(cid);
                        LGU.D(stringBuilder.toString());
                        inputText.setText(stringBuilder.toString());
                    }
                });
            }
        });

        LGU.DAllField(aClass);
//        XposedHelpers.findAndHookMethod("com.alibaba.mobileim.kit.chat.ChattingFragment",
//                classLoader,
//                "init", new Object[] { View.class, new XC_MethodHook() {
//            protected void afterHookedMethod(XC_MethodHook.MethodHookParam param1MethodHookParam)  {
//                try {
//                    super.afterHookedMethod(param1MethodHookParam);
//                } catch (Throwable throwable) {
//                    throwable.printStackTrace();
//                }
//                LGU.DAllField(param1MethodHookParam.thisObject);
//                final String cid = (String)
//                        XposedHelpers.getObjectField(param1MethodHookParam.thisObject,
//                                "cvsId");//field.get(param1MethodHookParam.thisObject).toString();
//
//
//                   // Field field1 = param1MethodHookParam.thisObject.getClass().getDeclaredField("chattingReplyBar");
//                 //   field1.setAccessible(true);
////                    final Object editText = field1.get(param1MethodHookParam.thisObject);
//                   final EditText editText1  = (EditText)
//                           XposedHelpers.getObjectField(param1MethodHookParam.thisObject,"mInputText");
//                    //field1.setAccessible(true);
////                    editText1.post(new Runnable() {
////                        public void run() {
////                            EditText editText = editText1;
////                            StringBuilder stringBuilder = new StringBuilder();
////                            stringBuilder.append("name = ");
////                            stringBuilder.append(userInfo.name);
////                            stringBuilder.append("\ncid = ");
////                            stringBuilder.append(cid);
////                            editText.setText(stringBuilder.toString());
////                        }
////                    });
//
//                    if (userInfo.cid == null) {
//                       // Socket.connect(context, userInfo.setCid(str));
//                        return;
//                    }
////                    userInfo.setCid(str);
//
//            }
//        } });
    }
}
