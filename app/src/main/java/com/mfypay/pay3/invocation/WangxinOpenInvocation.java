package com.mfypay.pay3.invocation;

import android.content.Context;

import com.mfypay.pay3.util.LGU;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by ruo on 2020/2/6.
 */

public class WangxinOpenInvocation implements InvocationHandler {
    private Context mCtx;
    private String mark;

    private String money;

    private String curUserId;


    private String curConversionId;

    public WangxinOpenInvocation(Context mCtx, String mark, String money, String curUserId, String curConversionId) {
        this.mCtx = mCtx;
        this.mark = mark;
        this.money = money;
        this.curUserId = curUserId;
        this.curConversionId = curConversionId;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().contains("onSuccess")){
            LGU.D("------------------");
        }
        return null;
    }
}
