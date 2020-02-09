package com.mfypay.pay3.listerner;



import android.os.Vibrator;
import android.text.TextUtils;


import com.mfypay.pay3.App;
import com.mfypay.pay3.util.SPU;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.socket.emitter.Emitter;

public class ConnListener implements Emitter.Listener {
    @Override
    public void call(Object... args) {

//
//        try {
//
//            Object isShow = SPU.getParam(App.getContext(), "isTip", "false");
//
//            DBManager dbManager = new DBManager(App.getContext());
//            ArrayList<AccModel> acc = dbManager.findAcc();
//
//            if (acc != null && acc.size() > 0) {
//
//                for (AccModel accModel : acc) {
//                    SbM sbM = new SbM();
//                    sbM.setDataType(1);
//                    sbM.setaId( (accModel.getAcc_id()));
//                    App.getContext().getSocket().emit("service", com.alibaba.fastjson.JSONObject.toJSONString(sbM));
//                }
//            }
//
//
//            Map<String, String> m=new HashMap<>();
//            m.put("ip","已连接"+""+args.toString());
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
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            Map<String, String> m=new HashMap<>();
//            m.put("ip","连接"+""+e.getLocalizedMessage());
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
//        }

    }
}
