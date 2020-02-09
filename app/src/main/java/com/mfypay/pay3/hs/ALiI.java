package com.mfypay.pay3.hs;


import android.content.Context;

import com.mfypay.pay3.b.AliUI;
import com.mfypay.pay3.util.LGU;

import de.robv.android.xposed.XposedHelpers;

/**
 * smali 插值
 */
public class ALiI {
    /**
     * private  String        realNamed;//getRealNamed
     * private  String nick;//getNick
     * private  String loginTime ;//getLoginTime
     * private  String userId ;//  getUserId
     *
     * @param ctx
     * @return
     */
    public static AliUI getLogin(Context ctx) {

        Object o = XposedHelpers.callMethod(XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.alipay.mobile.framework.AlipayApplication", ctx.getClassLoader()),
                "getInstance", new Object[0]), "getMicroApplicationContext", new Object[0]);
        Object localObject2 = XposedHelpers.callMethod(XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.alipay.mobile.common.helper.UserInfoHelper",
                ctx.getClassLoader()), "getInstance", new Object[0]), "getUserInfo", new Object[]{o});
        LGU.D(localObject2.toString());
        String loginId = (String) XposedHelpers.callMethod(localObject2, "getLogonId", new Object[0]);
        String mobileNumber = (String) XposedHelpers.callMethod(localObject2, "getMobileNumber", new Object[0]);
        String nick = (String) XposedHelpers.callMethod(localObject2, "getNick", new Object[0]);
        String loginTime = (String) XposedHelpers.callMethod(localObject2, "getLoginTime", new Object[0]);
        String userId = (String) XposedHelpers.callMethod(localObject2, "getUserId", new Object[0]);
        String realName = (String) XposedHelpers.callMethod(localObject2, "getRealName", new Object[0]);
        String showName = (String) XposedHelpers.callMethod(localObject2, "getShowName", new Object[0]);
        String userName = (String) XposedHelpers.callMethod(localObject2, "getUserName", new Object[0]);
        String userAvatar = (String) XposedHelpers.callMethod(localObject2, "getUserAvatar", new Object[0]);

        AliUI aliUI = new AliUI();
        aliUI.setLogonId(loginId);
        aliUI.setMobileNumber(mobileNumber);
        aliUI.setNick(nick);
        aliUI.setRealNamed(realName);
        aliUI.setUserId(userId);
        aliUI.setLoginTime(loginTime);
        aliUI.setUserName(userName);
        aliUI.setShowName(showName);
        aliUI.setUserAvatar(userAvatar);

        return aliUI;
    }


}
