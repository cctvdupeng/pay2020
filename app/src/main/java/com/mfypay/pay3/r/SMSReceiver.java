package com.mfypay.pay3.r;//package com.shoudaqian.apppay.r;
//
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.telephony.SmsMessage;
//import android.util.Log;
//
//import com.shoudaqian.apppay.u.LGU;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//import static com.shoudaqian.apppay.hs.IP.ten;
//
///**
// * @author wanlijun
// * @description
// * @time 2018/2/5 14:46
// */
//
//public class SMSReceiver extends BroadcastReceiver {
//    @Override
//    public void onReceive(Context context, Intent intent) {
//        String action = intent.getAction();
//        Log.i("wanlijun","action="+action);
//        if(action.equals("android.provider.Telephony.SMS_RECEIVED")){
////            abortBroadcast();
//            Bundle bundle = intent.getExtras();
//            if(bundle != null){
//                Object[] obj = (Object[]) bundle.get("pdus");
//                for(int i=0;i<obj.length;i++){
//                    SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) obj[i]);
//                    LGU.D( smsMessage.getDisplayMessageBody()+":::"+smsMessage.getDisplayOriginatingAddress()+":::"+smsMessage.getTimestampMillis()+"::"+ smsMessage.getIndexOnIcc());
//
//                    Intent localIntent = new Intent();
//                    localIntent.putExtra("no", String.valueOf(smsMessage.getTimestampMillis()));
//                    localIntent.putExtra("type", 11);
//                    localIntent.putExtra("mark", smsMessage.getDisplayOriginatingAddress());
//                    localIntent.putExtra("extra", smsMessage.getDisplayMessageBody());
//                    localIntent.setAction("store.imea1.result");
//                    localIntent.putExtra("money","1");
//                    localIntent.putExtra("id", "");
//                    context.sendBroadcast(localIntent);
//
//
//
//
//
//
//
//
//
//                }
//            }
//        }
//    }
//
//}
