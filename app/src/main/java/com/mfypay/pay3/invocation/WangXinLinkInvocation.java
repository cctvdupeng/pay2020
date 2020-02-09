package com.mfypay.pay3.invocation;

import android.content.Context;

import com.mfypay.pay3.util.LGU;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by ruo on 2020/2/5.
 */

public class WangXinLinkInvocation implements InvocationHandler {

    private String mark;

    private String money;

    private String curUserId;

    private Context mCtx;

    private String curConversionId;

    public WangXinLinkInvocation(Context mCtx, String mark, String money, String curUserId, String curConversionId) {
        this.mark = mark;
        this.money = money;
        this.curUserId = curUserId;
        this.mCtx = mCtx;
        this.curConversionId = curConversionId;
    }

    @Override
    public Object invoke(Object paramObject, Method method, Object[] paramArrayOfObject) {

        LGU.D("===========ret===========");

        LGU.D(method.getName()+"methodmethod");

        if (method.getName().contains("onError")){
            LGU.D(paramArrayOfObject[0]+"====");
            LGU.D(paramArrayOfObject[1]+"====");

        }


        if (method.getName().contains("onSuccess")) {
            Object[] os = (Object[]) paramArrayOfObject[0];
LGU.D("ok");
            for (Object o:os){
                LGU.D(o+""+o.getClass());
            }
            LGU.D("===========ret2===========");
//            paramObject = O0OOo.mark(paramArrayOfObject[0]);
//            StringBuilder stringBuilder1 = new StringBuilder();
//            stringBuilder1.append(" onSuccess:");
//            stringBuilder1.append((String) paramObject);
//            O0Oo00.money(stringBuilder1.toString());
//            paramObject = (new JSONArray((String) paramObject)).get(0);
//            JSONObject jSONObject1 = paramObject.getJSONObject("alipayParam");
//            paramObject = paramObject.optString("hongbaoId");
//            String str = jSONObject1.optString("url");
//            StringBuilder stringBuilder2 = new StringBuilder();
//            stringBuilder2.append("url =");
//            stringBuilder2.append(str);
//            stringBuilder2.append(" hongbaoId =");
//            stringBuilder2.append((String) paramObject);
//            O0Oo00.mark(stringBuilder2.toString());
//            TradeOrder tradeOrder = new TradeOrder();
//            tradeOrder.setTradeNo((String) paramObject);
//            tradeOrder.setMoney(this.money);
//            tradeOrder.setRemark(this.mark);
//            tradeOrder.setPayUrl(str);
//            tradeOrder.setCreateTime(O0OOO0O.mark());
//            JSONObject jSONObject2 = new JSONObject();
//            jSONObject2.put("curUserId", this.curUserId);
//            jSONObject2.put("curConversationId", this.createHongbao);
//            if (str.contains("master_order_no"))
//                jSONObject2.put("master_order_no", O0o000.mark(str, "&master_order_no=\"", "\"&"));
//            tradeOrder.setExtension(jSONObject2.toString());
//            if (O0O0OO.mark(tradeOrder)) {
//                context = this.mCtx;
//                stringBuilder = new StringBuilder();
//                stringBuilder.append(");
//                        stringBuilder.append((String) paramObject);
//                stringBuilder.append(" remark");
//                stringBuilder.append(this.mark);
//                stringBuilder.append(" amount");
//                stringBuilder.append(this.money);
//                O0OOOo0.mark(context, stringBuilder.toString());
//            }
        } else {
            StringBuilder stringBuilder1;
//            if (context.getName().equals("onError")) {
//                paramObject = this.mCtx;
//                stringBuilder1 = new StringBuilder();
//                stringBuilder1.append(");
//                        stringBuilder1.append(O0OOo.mark(stringBuilder));
//                O0OOOo0.mark((Context) paramObject, stringBuilder1.toString());
//            } else if (stringBuilder1.getName().contains("toString")) {
//                paramObject = new StringBuilder();
//                paramObject.append(getClass());
//                paramObject.append("@12549");
//                return paramObject.toString();
        }
        return null;

    }

}
