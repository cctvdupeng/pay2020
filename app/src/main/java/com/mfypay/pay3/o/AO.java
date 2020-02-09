package com.mfypay.pay3.o;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.telecom.Call;
import android.text.TextUtils;
import android.webkit.WebSettings;


import com.mfypay.pay3.hs.ALiI;
import com.mfypay.pay3.hs.IP;
import com.mfypay.pay3.util.LGU;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AO extends O {

    /**
     * 通过交易号和cookie 查单
     * jsoup 解析
     *
     * @param tradeNo
     * @param cookie
     * @return
     */
    public static void getOrder(final Context context, final String tradeNo, final String cookie) {


        if (!TextUtils.isEmpty(tradeNo) && !TextUtils.isEmpty(cookie)) {

            String url = "https://tradeeportlet.alipay.com/wireless/tradeDetail.htm?tradeNo=" +
                    tradeNo +
                    "&source=channel&_from_url=https%3A%2F%2Frender.alipay.com%2Fp%2Fz%2Fmerchant-mgnt%2Fsimple-order._h_t_m_l_%3Fsource%3Dmdb_card";

            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis()));

            try {
                Document doc = Jsoup.connect(url).
                        cookie("Cookie", cookie).
                        referrer("https://render.alipay.com/p/z/merchant-mgnt/simple-order.html?beginTime=" + date + "&endTime=" + date + "&fromBill=true&channelType=ALL").
                        header("User-Agent", getUserAgent(context)).get();


                if (doc.text().contains("登录超时")){
                    Intent intent = new Intent();
                    LGU.D("111111111111111"+doc.text());
                    intent.putExtra("extra", "登录超时");

                    intent.setAction("store.imea1.result");
                    context.sendBroadcast(intent);
                    return;

                }

                Elements elements = doc.getElementsByClass("trade-info-value");

                if (elements.size() >= 5) {
                    String money = elements.get(2).text();
                    String mark = elements.get(3).text();
                    String extra = "";
                    try {
                        extra =  doc.text();
                    } catch (Exception e) {


                    }

                    LGU.D(extra);
                    Intent localIntent = new Intent();
                    localIntent.putExtra("no", tradeNo);
                    localIntent.putExtra("money", money);
                    localIntent.putExtra("mark", mark);
                    localIntent.putExtra("type", IP.ten);
                    localIntent.putExtra("extra", extra);
                    try {
                        localIntent.putExtra("userId", ALiI.getLogin(context).getUserId());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    localIntent.setAction("store.imea1.result");
                    context.sendBroadcast(localIntent);

                } else {
                    APO.getOrder(context, tradeNo, cookie);
                }


            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }
}

