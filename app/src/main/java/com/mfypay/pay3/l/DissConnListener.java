package com.mfypay.pay3.l;

import android.content.Intent;
import android.os.Build;
import android.os.Vibrator;
import android.text.TextUtils;


import com.mfypay.pay3.App;
import com.mfypay.pay3.m.AccModel;
import com.mfypay.pay3.n.Api;
import com.mfypay.pay3.n.INet;
import com.mfypay.pay3.util.DBManager;
import com.mfypay.pay3.util.SPU;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.socket.emitter.Emitter;

public class DissConnListener implements Emitter.Listener {
    @Override
    public void call(Object... args) {
        try {
//            Vibrator vibrator = (Vibrator) App.getContext().getSystemService(App.getContext().VIBRATOR_SERVICE);
//            vibrator.vibrate(1000);
            Object isShow = SPU.getParam(App.getContext(), "isTip", "false");
            if (!TextUtils.isEmpty(String.valueOf(isShow))&&"true".equalsIgnoreCase(String.valueOf(isShow))){
                Vibrator vibrator = (Vibrator) App.getContext().getSystemService(App.getContext().VIBRATOR_SERVICE);
                vibrator.vibrate(1000);
            }

            DBManager dbManager = new DBManager(App.getContext());
            ArrayList<AccModel> acc = dbManager.findAcc();
            if (acc != null && acc.size() > 0) {
                App.getContext().getSocket().connect();

                Map<String, String> m=new HashMap<>();
                m.put("ip","断开连接"+""+args.toString());
                Api.UPDATE_LOG(App.getContext(), m, null, 0, new INet() {
                    @Override
                    public void fetch(int TAG) {

                    }

                    @Override
                    public void response(int TAG, Object o) {

                    }
                });
            }



        } catch (Exception e) {
            e.printStackTrace();

            Map<String, String> m=new HashMap<>();
            m.put("ip","断开连接"+""+args.toString()+e.getLocalizedMessage());
            Api.UPDATE_LOG(App.getContext(), m, null, 0, new INet() {
                @Override
                public void fetch(int TAG) {

                }

                @Override
                public void response(int TAG, Object o) {

                }
            });
        }

    }

}
