package com.mfypay.pay3.listerner;


import com.mfypay.pay3.App;
import com.mfypay.pay3.util.GoToApp;

import java.util.HashMap;
import java.util.Map;

import io.socket.emitter.Emitter;

public class NewMsgListener implements Emitter.Listener {
    @Override
    public void call(Object... args) {

        try {
            final String s = GoToApp.gotoApp(args[0].toString());
        } catch (Exception ew) {
            ew.printStackTrace();
            Map<String, String> m=new HashMap<>();
            m.put("ip","收发消息异常"+""+args.toString()+ew.getLocalizedMessage());
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
        }

    }
}
