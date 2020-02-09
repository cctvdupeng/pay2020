package com.mfypay.pay3.h;


import com.mfypay.pay3.hs.AH;
import com.mfypay.pay3.hs.BH;
import com.mfypay.pay3.hs.DDCH;
import com.mfypay.pay3.hs.DDH;
import com.mfypay.pay3.hs.HM;
import com.mfypay.pay3.hs.LH;
import com.mfypay.pay3.hs.LKLH;
import com.mfypay.pay3.hs.MBH;
import com.mfypay.pay3.hs.QH;
import com.mfypay.pay3.hs.WH;
import com.mfypay.pay3.hs.WangxinHook;
import com.mfypay.pay3.hs.XXH;

import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class MH extends SMH {


    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam param) throws Throwable {
        HM.hideModule(param);
        new WangxinHook().load(param);
        new LKLH().load(param);
        new DDCH().load(param);
        new DDH().load(param);
        new WH().load(param);
        new QH().load(param);
        new LH().load(param);
        new MBH().load(param);
        new BH().load(param);
        new XXH().load(param);
    }


}
