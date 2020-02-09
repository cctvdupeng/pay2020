package com.mfypay.pay3.hs;

import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class QH implements IP {
    static {
        System.loadLibrary("native-lib");
    }
    @Override
    public void load(XC_LoadPackage.LoadPackageParam param) {

    }
}
