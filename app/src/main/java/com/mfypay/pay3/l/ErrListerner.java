package com.mfypay.pay3.l;

import android.content.Intent;
import android.os.Vibrator;
import android.text.TextUtils;


import com.mfypay.pay3.App;
import com.mfypay.pay3.n.Api;
import com.mfypay.pay3.n.INet;
import com.mfypay.pay3.util.SPU;

import java.util.HashMap;
import java.util.Map;

import io.socket.emitter.Emitter;

public class ErrListerner implements Emitter.Listener {
    @Override
    public void call(Object... args) {
        try {
            Object isShow = SPU.getParam(App.getContext(), "isTip", "false");
            if (!TextUtils.isEmpty(String.valueOf(isShow))&&"true".equalsIgnoreCase(String.valueOf(isShow))){
//                Vibrator vibrator = (Vibrator) App.getContext().getSystemService(App.getContext().VIBRATOR_SERVICE);
//                vibrator.vibrate(1000);
            }

            Map<String, String> m=new HashMap<>();
//            m.put("ip","连接错误"+""+args.toString());
//            Api.UPDATE_LOG(App.getContext(), m, null, 0, new INet() {
//                @Override
//                public void fetch(int TAG) {
//
//                }
//
//                @Override
//                public void response(int TAG, Object o) {
//
//                }
//            });

//            Vibrator vibrator = (Vibrator)App.getContext().getSystemService(App.getContext().VIBRATOR_SERVICE);
//            vibrator.vibrate(1000);


        } catch (Exception e) {
            e.printStackTrace();
            Map<String, String> m=new HashMap<>();
            m.put("ip","错误连接"+""+e.getLocalizedMessage());
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
