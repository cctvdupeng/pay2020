package com.mfypay.pay3.o;

import android.content.Context;
import android.text.TextUtils;
import android.webkit.CookieManager;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mfypay.pay3.util.LGU;


import org.json.JSONArray;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ALO extends O {

    /**
     * 获取订单
     *
     * @param ctx
     */
    public static void getLastOrder(final Context ctx) {
      //  getLastOrder(ctx, true);
    }

    public static void getLastOrder(final Context ctx, final boolean isOne) {
        final String cookie = CookieManager.getInstance().getCookie("https://mbillexprod.alipay.com");
        if (TextUtils.isEmpty(cookie)) {
            return;
        }
        long l = System.currentTimeMillis();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis()));
        String url = "https://mbillexprod.alipay.com/enterprise/walletTradeList.json?beginTime=" + (l - 864000000L) + "&limitTime=" + l + "&pageSize=20&pageNum=1&channelType=ALL";
        final Request request = new Request
                .Builder()
                .removeHeader("User-Agent")
                .addHeader("User-Agent", getUserAgent(ctx))
                .get()
                .url(url)
                .header("Cookie", cookie)
                .addHeader("Referer", "https://render.alipay.com/p/z/merchant-mgnt/simple-order.html?beginTime=" + date + "&endTime=" + (String) date + "&fromBill=true&channelType=ALL")
                .build();

        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.newCall(request).enqueue(new Callback() {
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            public void onResponse(Call call, Response response) {
                try {


                    String resp = response.body().string();

                    LGU.D(resp);
                    if (resp.contains("failed")) {
                        return;
                    }


                    JsonArray list = new Gson().fromJson(resp, JsonObject.class).getAsJsonObject("result").getAsJsonArray("list");


                    if ((list != null) && (list.size() > 0)) {
                        if (isOne) {
                            String tradeNo = list.get(0).getAsJsonObject().get("tradeNo").getAsString();
                            LGU.D(tradeNo);
                            AO.getOrder(ctx, tradeNo, cookie);

                        } else {

                            for (JsonElement je : list) {
                                String tradeNo = je.getAsJsonObject().get("tradeNo").getAsString();
                                AO.getOrder(ctx, tradeNo, cookie);
                            }
                        }


                    }

                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
        });
    }
}
