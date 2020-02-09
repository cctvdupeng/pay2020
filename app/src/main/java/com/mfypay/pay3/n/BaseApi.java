package com.mfypay.pay3.n;

import android.content.Context;


import com.mfypay.pay3.App;
import com.mfypay.pay3.util.LGU;
import com.mfypay.pay3.util.StringUtil;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by 小饭 on 2018/7/4.
 */

public class BaseApi {


    public static ApiService newInstance() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://meal.zaqsap.cn/pay/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(ApiService.class);
    }


}
