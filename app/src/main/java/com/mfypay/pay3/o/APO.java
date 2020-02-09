package com.mfypay.pay3.o;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;


import com.mfypay.pay3.hs.IP;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class APO extends  O{
    /**
     * 通过交易号和cookie 查单
     * jsoup 解析
     *
     * @param tradeNo
     * @param cookie
     * @return
     */
    public static void getOrder(final Context context, final String tradeNo, String cookie) {

//        if (!TextUtils.isEmpty(tradeNo) && !TextUtils.isEmpty(cookie)) {
//
//            String url = "https://shenghuo.alipay.com/send/queryTransferDetail.htm?tradeNo=" + tradeNo;
//            try {
//                Document doc = Jsoup.connect(url).
//                        cookie("Cookie", cookie).
//                        referrer("https://consumeprod.alipay.com/record/standard.htm").
//                        header("User-Agent", getUserAgent(context)).get();
//
//                Elements marks = doc.getElementsByClass("tb-right ");
//
//                Elements name = doc.getElementsByClass("name");
//
//
//                Elements explain = doc.getElementsByClass("explain");
//
//
//                Elements time = doc.getElementsByClass("time");
//
//                Elements amounts =doc.getElementsByClass("amount");
//
//                double money = 0;
//                for (Element amount : amounts) {
//                    if (amount != null && !TextUtils.isEmpty(amount.text())) {
//                        try {
//                            money = Double.parseDouble(amount.text());
//                        } catch (Exception e) {
//                            // e.printStackTrace();
//                        }
//                    }
//
//                }
//
//                String mark = "";
//
//                for (Element mark1 : marks) {
//                    if (mark1 != null && !TextUtils.isEmpty(mark1.text())) {
//                        try {
//                            mark = String.valueOf(Integer.parseInt(mark1.text()));
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//                }
//
//                String extra = "";
//                try {
//                    extra = explain.text() + "--"
//                            + time.get(0).text()
//                            + "-----" + time.get(1).text()
//
//                            + "-----收款单号" + "--" + tradeNo
//                            + "----商家订单号---" + tradeNo;
//                } catch (Exception e) {
//
//
//                }
//                Intent localIntent = new Intent();
//                localIntent.putExtra("no", tradeNo);
//                localIntent.putExtra("money", money);
//                localIntent.putExtra("mark", mark);
//                localIntent.putExtra("type", IP.ten);
//                localIntent.putExtra("extra", extra);
//                localIntent.setAction("store.imea1.result");
//                context.sendBroadcast(localIntent);
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }
    }



}
