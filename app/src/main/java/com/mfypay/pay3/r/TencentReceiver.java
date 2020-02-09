package com.mfypay.pay3.r;//package com.shoudaqian.apppay.r;
//
//import android.content.Context;
//import android.content.Intent;
//import android.text.TextUtils;
//
//import com.google.gson.Gson;
//import com.shoudaqian.apppay.m.MsgModel;
//import com.shoudaqian.apppay.u.LGU;
//import com.shoudaqian.apppay.u.Md5;
//import com.shoudaqian.apppay.u.SPU;
//import com.tencent.android.tpush.XGPushBaseReceiver;
//import com.tencent.android.tpush.XGPushClickedResult;
//import com.tencent.android.tpush.XGPushRegisterResult;
//import com.tencent.android.tpush.XGPushShowedResult;
//import com.tencent.android.tpush.XGPushTextMessage;
//
//public class TencentReceiver extends XGPushBaseReceiver {
//    @Override
//    public void onRegisterResult(Context context, int i, XGPushRegisterResult xgPushRegisterResult) {
//
//    }
//
//    @Override
//    public void onUnregisterResult(Context context, int i) {
//
//    }
//
//    @Override
//    public void onSetTagResult(Context context, int i, String s) {
//
//    }
//
//    @Override
//    public void onDeleteTagResult(Context context, int i, String s) {
//
//    }
//
//    @Override
//    public void onTextMessage(Context context, XGPushTextMessage message) {
//
//        LGU.D(message.toString()+"=====");
//        // 获取自定义key-value
//        String content = message.getContent();
//
//
//        if (TextUtils.isEmpty(content)) {
//            return;
//        } else {
//            try {
//                LGU.D(content);
//                MsgModel msgModel = new Gson().fromJson(content, MsgModel.class);
//
//
//                Object o = SPU.getParam(context, "pre", "");
//
//
//                if (o != null && !"".equalsIgnoreCase(String.valueOf(o)) && String.valueOf(o).equals(msgModel.getTrade_no())) {
//                    SPU.setParam(context, "pre", msgModel.getTrade_no());
//                    return;
//                }
//
//                SPU.setParam(context, "pre", msgModel.getTrade_no());
//
//
//                String keyStr = msgModel.getTrade_no() + msgModel.getIstype() + msgModel.getPrice() + msgModel.getTimestamp()+
//                msgModel.getUid() + msgModel.getAccount_id() + SPU.getParam(context,"token", "");
//
//                if (!Md5.md5(keyStr).equals(msgModel.getKey())) {
//
//                    return;
//
//                }
//
//
//                Object param = SPU.getParam(context, "token", "");
//                if (param == null || TextUtils.isEmpty(param.toString())) {
//
//                    return;
//
//                }
//
//
//                if (msgModel != null) {
//                    Intent intent = new Intent();
//
//                    if (msgModel.getIstype() == 1) {
//
//                        intent.setAction("store.imea1.alipay.start");
//                    } else if (msgModel.getIstype() == 2) {
//                        intent.setAction("store.imea1.wechat.start");
//                    } else {
//                        intent.setAction("store.imea1.alipay.start");
//                    }
//
//                    LGU.D(content);
//                    intent.putExtra("mark", msgModel.getTrade_no());
//                    intent.putExtra("money", Double.parseDouble(msgModel.getPrice()));
//                    context.sendBroadcast(intent);
//                }
//
//
//            } catch (Exception e) {
//                LGU.D(e.getLocalizedMessage());
//                e.printStackTrace();
//            }
//
//
//        }
//
//
//    }
//
//    @Override
//    public void onNotifactionClickedResult(Context context, XGPushClickedResult xgPushClickedResult) {
//
//    }
//
//    @Override
//    public void onNotifactionShowedResult(Context context, XGPushShowedResult xgPushShowedResult) {
//
//    }
//}