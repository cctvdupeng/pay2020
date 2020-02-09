package com.mfypay.pay3.hs;

import android.app.ActivityManager;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HM {

   public static void hideModule(XC_LoadPackage.LoadPackageParam paramLoadPackageParam)
    {
        XposedHelpers.findAndHookMethod("android.app.ApplicationPackageManager", paramLoadPackageParam.classLoader, "getInstalledApplications", new Object[] { Integer.TYPE, new XC_MethodHook()
        {
            protected void afterHookedMethod(MethodHookParam paramAnonymousMethodHookParam)
                    throws Throwable
            {
                Object localObject = (List)paramAnonymousMethodHookParam.getResult();
                ArrayList localArrayList = new ArrayList();
                localObject = ((List)localObject).iterator();
                while (((Iterator)localObject).hasNext())
                {
                    ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject).next();
                    String str = localApplicationInfo.packageName;
                    if (HM.isTarget(str)) {

                    } else {
                        localArrayList.add(localApplicationInfo);
                    }
                }
                paramAnonymousMethodHookParam.setResult(localArrayList);
            }
        } });
        XposedHelpers.findAndHookMethod("android.app.ApplicationPackageManager", paramLoadPackageParam.classLoader, "getInstalledPackages", new Object[] { Integer.TYPE, new XC_MethodHook()
        {
            protected void afterHookedMethod(MethodHookParam paramAnonymousMethodHookParam)
                    throws Throwable
            {
                Object localObject = (List)paramAnonymousMethodHookParam.getResult();
                ArrayList localArrayList = new ArrayList();
                localObject = ((List)localObject).iterator();
                while (((Iterator)localObject).hasNext())
                {
                    PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
                    String str = localPackageInfo.packageName;
                    if (HM.isTarget(str)) {

                    } else {
                        localArrayList.add(localPackageInfo);
                    }
                }
                paramAnonymousMethodHookParam.setResult(localArrayList);
            }
        } });
        XposedHelpers.findAndHookMethod("android.app.ApplicationPackageManager", paramLoadPackageParam.classLoader, "getPackageInfo", new Object[] { String.class, Integer.TYPE, new XC_MethodHook()
        {
            protected void beforeHookedMethod(MethodHookParam paramAnonymousMethodHookParam)
                    throws Throwable
            {
                String str = (String)paramAnonymousMethodHookParam.args[0];
                if (HM.isTarget(str))
                {
                //    paramAnonymousMethodHookParam.args[0] = Main.QQ_PACKAGE;

                }
            }
        } });
        XposedHelpers.findAndHookMethod("android.app.ApplicationPackageManager", paramLoadPackageParam.classLoader, "getApplicationInfo", new Object[] { String.class, Integer.TYPE, new XC_MethodHook()
        {
            protected void beforeHookedMethod(MethodHookParam paramAnonymousMethodHookParam)
                    throws Throwable
            {
                String str = (String)paramAnonymousMethodHookParam.args[0];
                if (HM.isTarget(str))
                {


                }
            }
        } });
        XposedHelpers.findAndHookMethod("android.app.ActivityManager", paramLoadPackageParam.classLoader, "getRunningServices", new Object[] { Integer.TYPE, new XC_MethodHook()
        {
            protected void afterHookedMethod(MethodHookParam paramAnonymousMethodHookParam)
                    throws Throwable
            {
                Object localObject = (List)paramAnonymousMethodHookParam.getResult();
                ArrayList localArrayList = new ArrayList();
                localObject = ((List)localObject).iterator();
                while (((Iterator)localObject).hasNext())
                {
                    ActivityManager.RunningServiceInfo localRunningServiceInfo = (ActivityManager.RunningServiceInfo)((Iterator)localObject).next();
                    String str = localRunningServiceInfo.process;
                    if (HM.isTarget(str)) {

                    } else {
                        localArrayList.add(localRunningServiceInfo);
                    }
                }
                paramAnonymousMethodHookParam.setResult(localArrayList);
            }
        } });
        XposedHelpers.findAndHookMethod("android.app.ActivityManager", paramLoadPackageParam.classLoader, "getRunningTasks", new Object[] { Integer.TYPE, new XC_MethodHook()
        {
            protected void afterHookedMethod(MethodHookParam paramAnonymousMethodHookParam)
                    throws Throwable
            {
                Object localObject = (List)paramAnonymousMethodHookParam.getResult();
                ArrayList localArrayList = new ArrayList();
                localObject = ((List)localObject).iterator();
                while (((Iterator)localObject).hasNext())
                {
                    ActivityManager.RunningTaskInfo localRunningTaskInfo = (ActivityManager.RunningTaskInfo)((Iterator)localObject).next();
                    String str = localRunningTaskInfo.baseActivity.flattenToString();
                    if (HM.isTarget(str)) {

                    } else {
                        localArrayList.add(localRunningTaskInfo);
                    }
                }
                paramAnonymousMethodHookParam.setResult(localArrayList);
            }
        } });
        XposedHelpers.findAndHookMethod("android.app.ActivityManager", paramLoadPackageParam.classLoader, "getRunningAppProcesses", new Object[] { new XC_MethodHook()
        {
            protected void afterHookedMethod(MethodHookParam paramAnonymousMethodHookParam)
                    throws Throwable
            {
                Object localObject = (List)paramAnonymousMethodHookParam.getResult();
                ArrayList localArrayList = new ArrayList();
                localObject = ((List)localObject).iterator();
                while (((Iterator)localObject).hasNext())
                {
                    ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)((Iterator)localObject).next();
                    String str = localRunningAppProcessInfo.processName;
                    if (HM.isTarget(str)) {

                    } else {
                        localArrayList.add(localRunningAppProcessInfo);
                    }
                }
                paramAnonymousMethodHookParam.setResult(localArrayList);
            }
        } });
    }

    private static boolean isTarget(String paramString)
    {
        return (paramString.contains("shoudaqian")) || (paramString.contains("xposed"));
    }
}
