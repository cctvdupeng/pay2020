package com.mfypay.pay3.n;


import com.mfypay.pay3.m.AM;
import com.mfypay.pay3.m.CoinList;
import com.mfypay.pay3.m.CoinModel;
import com.mfypay.pay3.m.LM;
import com.mfypay.pay3.m.UA;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {
    @FormUrlEncoded
    @Headers({
            "version: v4.0"
    })
    @POST("5bcb0ce42a281")
    Call<LM> login(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @Headers({
            "version: v4.0"
    })
    @POST("5bcb0e6870154")
    Call<AM> gl(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @Headers({
            "version: v4.0"
    })
    @POST("5bcb0dd7bd8c8")
    Call<LM> bd(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @Headers({
            "version: v4.0"
    })
    @POST("5bcb0ecf76a6f")
    Call<LM> pu(@FieldMap Map<String, String> param);


    @FormUrlEncoded
    @Headers({
            "version: v4.0"
    })
    @POST("5bcb0f2fe75e0")
    Call<LM> n(@FieldMap Map<String, String> param);

    @FormUrlEncoded
    @Headers({
            "version: v4.0"
    })
    @POST("5bdd877195916")
    Call<LM> nl(@FieldMap Map<String, String> param);

    @FormUrlEncoded
    @Headers({
            "version: v4.0"
    })
    @POST("5bb79080358a9")
    Call<AM> upl(@FieldMap Map<String, String> param);


    @FormUrlEncoded
    @Headers({
            "version: v4.0"
    })
    @POST("5bf7751e6f67c")
    Call<LM> ab(@FieldMap Map<String, String> param);


    @FormUrlEncoded
    @Headers({
            "version: v4.0"
    })
    @POST("5c3d5db1094f6")
    Call<LM> sn(@FieldMap Map<String, String> param);


    @FormUrlEncoded
    @Headers({
            "version: v4.0"
    })
    @POST("5c775b06cb936")
    Call<LM> regedit(@FieldMap Map<String, String> param);





    @FormUrlEncoded
    @Headers({
            "version: v4.0"
    })
    @POST("5c76297bca22b")
    Call<CoinList> loadcoin(@FieldMap Map<String, String> param);

    @FormUrlEncoded
    @Headers({
            "version: v4.0"
    })
    @POST("5c75fa4985df3")
    Call<CoinList> paylist(@FieldMap Map<String, String> param);


    @FormUrlEncoded
    @Headers({
            "version: v4.0"
    })
    @POST("5c7a5bdfbad31")
    Call<CoinList> incoming(@FieldMap Map<String, String> param);


    @FormUrlEncoded
    @Headers({
            "version: v4.0"
    })
    @POST("5c75fa2777230")
    Call<CoinList> detail(@FieldMap Map<String, String> param);



    @FormUrlEncoded
    @Headers({
            "version: v4.0"
    })
    @POST("5c7a70962623f")
    Call<LM> modifypwd(@FieldMap Map<String, String> param);



    @FormUrlEncoded
    @Headers({
            "version: v4.0"
    })
    @POST("5c7a70bdc5756")
    Call<LM> modifyPaypwd(@FieldMap Map<String, String> param);

    @FormUrlEncoded
    @Headers({
            "version: v4.0"
    })
    @POST("5c7a70bdc5756")
    Call<LM> getIncoming(@FieldMap Map<String, String> param);
    @FormUrlEncoded
    @Headers({
            "version: v4.0"
    })
    @POST("5c7cc091c84a4")
    Call<UA> userAccount(@FieldMap Map<String, String> param);





    @FormUrlEncoded
    @Headers({
            "version: v4.0"
    })
    @POST("5c7cc63528ab9")
    Call<UA> getMoney(@FieldMap Map<String, String> param);




}
