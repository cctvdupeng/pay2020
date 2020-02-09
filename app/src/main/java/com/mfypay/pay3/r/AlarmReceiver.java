package com.mfypay.pay3.r;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Vibrator;
import android.text.TextUtils;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mfypay.pay3.App;
import com.mfypay.pay3.MainActivity;
import com.mfypay.pay3.b.AliUI;
import com.mfypay.pay3.f.AFragment;
import com.mfypay.pay3.hs.ALiI;
import com.mfypay.pay3.m.AccModel;
import com.mfypay.pay3.m.OB;
import com.mfypay.pay3.m.SbM;
import com.mfypay.pay3.n.Api;
import com.mfypay.pay3.n.INet;
import com.mfypay.pay3.util.DBManager;
import com.mfypay.pay3.util.LGU;
import com.mfypay.pay3.util.PHU;
import com.mfypay.pay3.util.SPU;
import com.mfypay.pay3.util.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class AlarmReceiver extends BroadcastReceiver implements INet {
    Map<String, String> map = new HashMap<>();

    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();
        LGU.D("action:" + action);
        if ("com.mfypay.run".equalsIgnoreCase(action)) {
            Toast.makeText(App.getContext(), "支付监控中", Toast.LENGTH_LONG).show();

//            SPU.setParam(App.getContext(), "isQuery", "false");
            Object isQuery = SPU.getParam(App.getContext(), "isQuery", "false");
            if (!TextUtils.isEmpty(String.valueOf(isQuery)) && "true".equalsIgnoreCase(String.valueOf(isQuery))) {
                long deploy = 30;
                try {
                    deploy = Long.parseLong(String.valueOf(SPU.getParam(context, "deploy", "30")));
                } catch (Exception e) {

                    deploy = 30;
                }


                Intent queryIntent = new Intent("store.imea1.alipay.start");
                queryIntent.putExtra("rType", "query");
                queryIntent.putExtra("deploy", deploy);
                context.sendBroadcast(queryIntent);
            }

            try {
                //  PHU.StartMe("com.eg.android.AlipayGphone");
//                PHU.StartMe();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                if (!App.getContext().getSocket().connected()) {
                    App.getContext().getSocket().connect();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if ("com.mfypay.autoali".equalsIgnoreCase(action)) {
            LGU.D(intent.getStringExtra("aliInfo"));
            map = JSON.parseObject(intent.getStringExtra("aliInfo"), Map.class);


            if (App.getContext().getSocket() != null && App.getContext().getSocket().id() != null) {
                map.put("socket_id", App.getContext().getSocket().id());

            }
            if ("0".equalsIgnoreCase(map.get("status"))) {
                SPU.setParam(App.getContext(), "isShow", String.valueOf(false));
                SPU.setParam(App.getContext(), "isTip", String.valueOf(false));
                DBManager dbManager = new DBManager(App.getContext());
                dbManager.delAccByAccId(map.get("userId"));
            } else {
                SPU.setParam(App.getContext(), "isTip", String.valueOf(true));
                SPU.setParam(App.getContext(), "isShow", String.valueOf(true));
                DBManager dbManager = new DBManager(App.getContext());
                AccModel accModel = new AccModel();
                accModel.setAcc_id(map.get("userId"));
                accModel.setType("1");
                accModel.setSocket_id(App.getContext().getSocket().id());
                dbManager.addAccount(accModel);
            }


            fetch(1);

        } else if ("store.imea1.result".equalsIgnoreCase(action)) {
            String money = intent.getStringExtra("money");
            boolean strategy = intent.getBooleanExtra("strategy", false);
            String mark = intent.getStringExtra("mark");
            String no = intent.getStringExtra("no");
            String extra = intent.getStringExtra("extra");
            String userId = intent.getStringExtra("userId");
            int type = intent.getIntExtra("type", 0);
            String url = intent.getStringExtra("url");
            String printQrCodeUrl = intent.getStringExtra("printQrCodeUrl");

            if (type == 1 || type == 11 || type == 12 || type == 14 || type == 13) {

                Intent intent1 = new Intent();
                intent1.putExtra("no", no + ":" + printQrCodeUrl);
                intent1.putExtra("money", money);
                intent1.putExtra("mark", mark + ":" + !TextUtils.isEmpty(url));
                if (type == 1) {
                    intent1.setAction("com.mfypay.aliorder");
                } else if (type == 11) {
                    intent1.setAction("com.mfypay.chatorder");
                } else if (type == 12) {
                    intent1.setAction("com.mfypay.ddorder");
                } else if (type == 14) {
                    intent1.setAction("com.mfypay.xxorder");
                } else if (type == 13) {
                    intent1.setAction("com.mfypay.lklorder");
                }
                context.sendBroadcast(intent1);

                SbM sbM = new SbM();
                sbM.setDataType(3);
                sbM.setUrl(url);
                sbM.setMark(mark);
                sbM.setMoney(money);
                sbM.setExtra(extra);
                sbM.setNo(no);
                if (!App.getContext().getSocket().connected()) {
                    App.getContext().getSocket().connect();
                }
                App.getContext().getSocket().emit("service", JSONObject.toJSONString(sbM));

                return;
            }

            if (extra.contains("登录超时")) {
                try {
                    Vibrator vibrator = (Vibrator) App.getContext().getSystemService(App.getContext().VIBRATOR_SERVICE);
                    vibrator.vibrate(1000);

                    Intent intent1 = new Intent();

                    intent1.putExtra("mark", "登录超时");
                    intent1.setAction("com.mfypay.aliorder");
                    context.sendBroadcast(intent1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return;

            }

            try {

                ArrayList<OB> obs = new DBManager(context).FindByNo(no);
                LGU.D(obs.toString() + "===");

                if (obs == null || obs.size() < 1) {
                    map = new HashMap<>();
                    map.put("money", money);
                    map.put("mark", mark);
                    map.put("no", no);
                    map.put("extra", extra);
                    map.put("userId", userId);
                    Intent intent1 = new Intent();
                    intent1.putExtra("no", no);
                    intent1.putExtra("money", money);
                    intent1.putExtra("mark", mark);
                    intent1.setAction("com.mfypay.aliorder");
                    context.sendBroadcast(intent1);


                    if (strategy) {
                        fetch(3);
                    } else {
                        fetch(2);
                    }
                } else {
                    Toast.makeText(context, "已回调", Toast.LENGTH_LONG).show();
                }


            } catch (Exception e) {
                e.printStackTrace();
            }

        }


    }


    @Override
    public void fetch(int TAG) {
        if (TAG == 1) {
            Api.AUTO_BIND(App.getContext(), map, null, TAG, this);
        } else if (TAG == 2) {
            Api.PUSH_ORDER_RESULT(App.getContext(), map, null, TAG, this);
        } else if (TAG == 3) {
            Api.PUSH_ORDER_STRATEGY_RESULT(App.getContext(), map, null, TAG, this);
        }
    }

    @Override
    public void response(int TAG, Object o) {
        if (o == null) {
            return;
        } else if (TAG == 1) {
            LGU.D(o.toString());
            Toast.makeText(App.getContext(), o.toString(), Toast.LENGTH_SHORT).show();

        } else if (TAG == 2) {
            Toast.makeText(App.getContext(), o.toString(), Toast.LENGTH_SHORT).show();
        } else if (TAG == 3) {
            Toast.makeText(App.getContext(), o.toString(), Toast.LENGTH_SHORT).show();

            OB ob = new OB();
            ob.setDt("" + o.toString());
            ob.setMark(o.toString());
            ob.setMoney("1");
            ob.setNo(o.toString());
            ob.setResult("ok");
            ob.setType("1");
            new DBManager(App.getContext()).addOrder(ob);
        }
    }
}
