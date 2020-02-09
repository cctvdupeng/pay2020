package com.mfypay.pay3.util;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;



public class SmsManager {


    public static void getSmsCode(Context ctx) {



        Cursor cursor = null;
        try {
            cursor = ctx.getContentResolver().query(
                    Uri.parse("content://sms"),
                    new String[]{"_id", "address", "body", "date"},
                    "type = 1", null, "date desc"); //
            if (cursor != null) {
                String body = "";
                String address = "";
                String date = "";
                String id = "";
                while (cursor.moveToNext()) {
                    body = cursor.getString(cursor.getColumnIndex("body"));// 在这里获取短信信息
                    address = cursor.getString(cursor.getColumnIndex("address"));// 在这里获取短信信息
                    date = String.valueOf(cursor.getLong(cursor.getColumnIndex("date")));// 在这里获取短信信息
                    id = cursor.getString(cursor.getColumnIndex("_id"));// 在这里获取短信信息
                    LGU.D(body+":::"+address+":::"+date+"::"+id);

                    Intent localIntent = new Intent();
                    localIntent.putExtra("no", date);
                    localIntent.putExtra("type", 11);
                    localIntent.putExtra("id", id);
                    localIntent.putExtra("mark", address);
                    localIntent.putExtra("extra", body);
                    localIntent.putExtra("money","1");
                    localIntent.setAction("store.imea1.result");
                    ctx.sendBroadcast(localIntent);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
}
