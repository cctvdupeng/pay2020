package com.mfypay.pay3.util;

import android.content.Context;
import android.content.pm.PackageManager;

public class V {
    /**
     *
     * @param paramContext
     * @return
     */
    public static String getVersion(Context paramContext)
    {
        try
        {
            String str = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
            return str;
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {

        }
        return "";
    }

}
