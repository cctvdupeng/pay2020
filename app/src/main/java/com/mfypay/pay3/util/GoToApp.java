package com.mfypay.pay3.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import com.alibaba.fastjson.JSONObject;
import com.mfypay.pay3.App;

public class GoToApp {

    public static String gotoApp(String content) {

        Context context = App.getContext();
        if (TextUtils.isEmpty(content)) {

            return "参数不能为空";
        } else {
//            try {
//
//                MsgModel msgModel = JSONObject.parseObject(content, MsgModel.class);
//                String keyStr = msgModel.getTrade_no() + msgModel.getType() + msgModel.getPrice() + msgModel.getTimestamp() +
//                        msgModel.getUid() + SPU.getParam(context, "token", "");
//                LGU.D(content + "md5" + keyStr);
//                if (!Md5.md5(keyStr).equals(msgModel.getKey())) {
//
//                    return "签名错误";
//                }
//
//                LGU.D(content);
//
//                Object param = SPU.getParam(context, "token", "");
//                if (param == null || TextUtils.isEmpty(param.toString())) {
//
//                    return "用户为空";
//
//                }
//
//
//                if (msgModel != null) {
//
//                    if (!TextUtils.isEmpty(msgModel.getAction())) {
//
//                        Intent intent1 = new Intent();
//                        intent1.setData(Uri.parse(msgModel.getAction()));
//                        context.startActivity(intent1);
//                    }
//
//
//                    Intent intent = new Intent();
//
//                    if (msgModel.getType() == 1) {
//
//                        intent.setAction("store.imea1.alipay.start");
//                    } else if (msgModel.getType() == 2) {
//                        intent.setAction("store.imea1.wechat.start");
//                    } else if (msgModel.getType() == 11) {
//                        intent.setAction("store.imea1.chat.start");
//                    } else if (msgModel.getType() == 12) {
//                        intent.setAction("store.imea1.dingding.start");
//                    } else {
//                        intent.setAction("store.imea1.alipay.start");
//                    }
//
//                    intent.putExtra("mark", msgModel.getTrade_no());
//                    intent.putExtra("money", (msgModel.getPrice()));
//                    intent.putExtra("toUser", (msgModel.getToUserId()));
//                    intent.putExtra("extra", msgModel.getExtra());
//                    if (msgModel.getIsReSend() == 1) {
//                        intent.putExtra("rType", "reSend");
//
//                    }
//
//
//                    context.sendBroadcast(intent);
//                }
//
//
//            } catch (Exception e) {
//                LGU.D(e.getLocalizedMessage());
//
//                e.printStackTrace();
//                return e.getLocalizedMessage();
//            }
            return "";
        }
    }
}
