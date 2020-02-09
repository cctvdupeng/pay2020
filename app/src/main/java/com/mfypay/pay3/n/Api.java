package com.mfypay.pay3.n;

import android.content.Context;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.mfypay.pay3.m.AM;
import com.mfypay.pay3.m.CoinList;
import com.mfypay.pay3.m.CoinModel;
import com.mfypay.pay3.m.LM;
import com.mfypay.pay3.m.UA;
import com.mfypay.pay3.util.LGU;
import com.mfypay.pay3.util.SPU;
import com.mfypay.pay3.util.StringUtil;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 小饭 on 2018/7/4.
 */

public class Api extends BaseApi {

    /**
     * 上传url
     *
     * @param context
     * @param param
     * @param clazz
     * @param TAG
     */
    public static void PUSH_URL(final Context context, Map<String, String> param, final Class clazz, final int TAG, final INet iNet) {


        param.put("sign", "1");
        param.put("appSign", StringUtil.getSingInfo(context, context.getPackageName()));
        param.put("token", SPU.getParam(context, "token", "").toString());

        LGU.D(SPU.getParam(context, "token", "").toString());
        BaseApi.newInstance().pu(param).enqueue(new Callback<LM>() {
            @Override
            public void onResponse(Call<LM> call, Response<LM> response) {

                try {
                    LM body = response.body();
                    LGU.D(body.toString());

                    if (body.getCode() == 1) {
                        iNet.response(TAG, body.getData());
                    } else {
                        Toast.makeText(context, body.getMsg(), Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<LM> call, Throwable t) {
                LGU.D(t.getLocalizedMessage());
            }
        });

    }

    /**
     * 绑定设备
     *
     * @param context
     * @param param
     * @param clazz
     * @param TAG
     */
    public static void BIND_DEVICE(final Context context, Map<String, String> param, final Class clazz, final int TAG, final INet iNet) {

        param.put("sign", "1");
        param.put("appSign", StringUtil.getSingInfo(context, context.getPackageName()));
        param.put("token", SPU.getParam(context, "token", "").toString());

        LGU.D(SPU.getParam(context, "token", "").toString());
        BaseApi.newInstance().bd(param).enqueue(new Callback<LM>() {
            @Override
            public void onResponse(Call<LM> call, Response<LM> response) {

                try {
                    LM body = response.body();
                    LGU.D(body.toString());

                    if (body.getCode() == 1) {
                        iNet.response(TAG, body.getData());
                    } else {
                        Toast.makeText(context, body.getMsg(), Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<LM> call, Throwable t) {
                LGU.D(t.getLocalizedMessage());
            }
        });


    }


    /**
     * 上传支付结果
     *
     * @param context
     * @param param
     * @param clazz
     * @param TAG
     */
    public static void PUSH_ORDER_RESULT(final Context context, Map<String, String> param, final Class clazz, final int TAG, final INet iNet) {
        param.put("sign", "1");
        param.put("appSign", StringUtil.getSingInfo(context, context.getPackageName()));
        param.put("token", SPU.getParam(context, "token", "").toString());

        LGU.D(SPU.getParam(context, "token", "").toString());
        BaseApi.newInstance().n(param).enqueue(new Callback<LM>() {
            @Override
            public void onResponse(Call<LM> call, Response<LM> response) {
                try {
                    LM body = response.body();

                    LGU.D(body.toString());


                    if (body.getCode() == 1) {
                        iNet.response(TAG, body.getData());
                    } else {
                        Toast.makeText(context, body.getMsg(), Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<LM> call, Throwable t) {
                LGU.D(t.getLocalizedMessage());
            }
        });

    }

    public static void PUSH_ORDER_UNION_RESULT(final Context context, Map<String, String> param, final Class clazz, final int TAG, final INet iNet) {
        param.put("sign", "1");
        param.put("appSign", StringUtil.getSingInfo(context, context.getPackageName()));
        param.put("token", SPU.getParam(context, "token", "").toString());

        LGU.D(SPU.getParam(context, "token", "").toString());
        BaseApi.newInstance().nl(param).enqueue(new Callback<LM>() {
            @Override
            public void onResponse(Call<LM> call, Response<LM> response) {

                try {
                    LM body = response.body();
                    LGU.D(body.toString());

                    if (body.getCode() == 1) {
                        iNet.response(TAG, body.getData());
                    } else {
                        Toast.makeText(context, body.getMsg(), Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<LM> call, Throwable t) {
                LGU.D(t.getLocalizedMessage());
            }
        });

    }

    public static void LOGIN(final Context context, Map<String, String> param, final Class clazz, final int TAG, final INet iNet) {

        BaseApi.newInstance().login(param).enqueue(new Callback<LM>() {
            @Override
            public void onResponse(Call<LM> call, Response<LM> response) {
                try {
                    LM body = response.body();
                    LGU.D(SPU.getParam(context, "token", "").toString());
                    LGU.D(body.getData());
                    if (1 == body.getCode()) {
                        iNet.response(TAG, body.getData());
                    } else {
                        Toast.makeText(context, body.getMsg(), Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<LM> call, Throwable t) {
                LGU.D(t.getLocalizedMessage());
            }
        });


    }


    public static void AUTO_BIND(final Context context, Map<String, String> param, final Class clazz, final int TAG, final INet iNet) {
        param.put("sign", "1");
        param.put("appSign", StringUtil.getSingInfo(context, context.getPackageName()));
        param.put("token", SPU.getParam(context, "token", "").toString());
        LGU.D("===" + JSON.toJSONString(param));
        BaseApi.newInstance().ab(param).enqueue(new Callback<LM>() {
            @Override
            public void onResponse(Call<LM> call, Response<LM> response) {
                try {
                    LM body = response.body();
                    LGU.D(SPU.getParam(context, "token", "").toString());
                    LGU.D(body.getData());
                    if (1 == body.getCode()) {
                        iNet.response(TAG, body.getData());
                    } else {
                        Toast.makeText(context, body.getMsg(), Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<LM> call, Throwable t) {
                LGU.D(t.getLocalizedMessage());
            }
        });


    }


    public static void LOAD_ACCOUNT(final Context context, Map<String, String> param, final Class clazz, final int TAG, final INet iNet) {


        param.put("sign", "1");
        param.put("appSign", StringUtil.getSingInfo(context, context.getPackageName()));
        param.put("token", SPU.getParam(context, "token", "").toString());

        BaseApi.newInstance().gl(param).enqueue(new Callback<AM>() {
            @Override
            public void onResponse(Call<AM> call, Response<AM> response) {

                try {
                    AM body = response.body();


                    if (body.getCode() == 1) {
                        iNet.response(TAG, body.getData());
                    } else {
                        Toast.makeText(context, body.getMsg(), Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<AM> call, Throwable t) {
                LGU.D(t.getLocalizedMessage());
            }
        });
    }


    public static void UPDATE_LOG(final Context context, Map<String, String> param, final Class clazz, final int TAG, final INet iNet) {


        param.put("sign", "1");
        param.put("appSign", StringUtil.getSingInfo(context, context.getPackageName()));
        param.put("token", SPU.getParam(context, "token", "").toString());

        BaseApi.newInstance().upl(param).enqueue(new Callback<AM>() {
            @Override
            public void onResponse(Call<AM> call, Response<AM> response) {

                try {
                    AM body = response.body();


                    if (body.getCode() == 1) {
                        iNet.response(TAG, body.getData());
                    } else {
                        Toast.makeText(context, body.getMsg(), Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<AM> call, Throwable t) {
                LGU.D(t.getLocalizedMessage());
            }
        });
    }


    public static void PUSH_ORDER_STRATEGY_RESULT(final Context context, Map<String, String> param, final Class clazz, final int TAG, final INet iNet) {
        param.put("sign", "1");
        param.put("appSign", StringUtil.getSingInfo(context, context.getPackageName()));
        param.put("token", SPU.getParam(context, "token", "").toString());

        LGU.D(SPU.getParam(context, "token", "").toString());
        BaseApi.newInstance().sn(param).enqueue(new Callback<LM>() {
            @Override
            public void onResponse(Call<LM> call, Response<LM> response) {
                try {
                    LM body = response.body();

                    LGU.D(body.toString());


                    if (body.getCode() == 1) {
                        iNet.response(TAG, body.getData());
                    } else {
                        Toast.makeText(context, body.getMsg(), Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<LM> call, Throwable t) {
                LGU.D(t.getLocalizedMessage());
            }
        });

    }


    public static void REGEDIT(final Context context, Map<String, String> param, final Class clazz, final int TAG, final INet iNet) {

        BaseApi.newInstance().regedit(param).enqueue(new Callback<LM>() {
            @Override
            public void onResponse(Call<LM> call, Response<LM> response) {
                try {
                    LGU.D("----------"+ response);
                    LM body = response.body();
                    LGU.D(SPU.getParam(context, "token", "").toString());
                    LGU.D(body.getData());
                    if (1 == body.getCode()) {
                        iNet.response(TAG, body.getData());
                    } else {
                        Toast.makeText(context, body.getMsg(), Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<LM> call, Throwable t) {
                LGU.D(t.getLocalizedMessage());
            }
        });


    }


    public static void LOADCOIN(final Context context, Map<String, String> param, final Class clazz, final int TAG, final INet iNet) {
        if (param == null)
            param = new HashMap<>();
        param.put("sign", "1");
        param.put("appSign", StringUtil.getSingInfo(context, context.getPackageName()));
        param.put("token", SPU.getParam(context, "token", "").toString());


        BaseApi.newInstance().loadcoin(param).enqueue(new Callback<CoinList>() {
            @Override
            public void onResponse(Call<CoinList> call, Response<CoinList> response) {

                try {
                    LGU.D("----------"+ response);
                    CoinList body = response.body();
                    LGU.D("----------"+body);

                    if (body.getCode() == 1) {
                        iNet.response(TAG, body.getData());
                    } else {
                        Toast.makeText(context, body.getMsg(), Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    LGU.D("----------"+e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<CoinList> call, Throwable t) {
                LGU.D(t.getLocalizedMessage());
            }
        });

    }

    public static void PAYLIST(final Context context, Map<String, String> param, final Class clazz, final int TAG, final INet iNet) {
        if (param == null)
            param = new HashMap<>();
        param.put("sign", "1");
        param.put("appSign", StringUtil.getSingInfo(context, context.getPackageName()));
        param.put("token", SPU.getParam(context, "token", "").toString());


        BaseApi.newInstance().paylist(param).enqueue(new Callback<CoinList>() {
            @Override
            public void onResponse(Call<CoinList> call, Response<CoinList> response) {

                try {
                    LGU.D("----------"+ response);
                    CoinList body = response.body();
                    LGU.D("----------"+body);

                    if (body.getCode() == 1) {
                        iNet.response(TAG, body.getData());
                    } else {
                        Toast.makeText(context, body.getMsg(), Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    LGU.D("----------"+e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<CoinList> call, Throwable t) {
                LGU.D(t.getLocalizedMessage());
            }
        });

    }






    public static void MODIFYPWD(final Context context, Map<String, String> param, final Class clazz, final int TAG, final INet iNet) {
        if (param == null)
            param = new HashMap<>();
        param.put("sign", "1");
        param.put("appSign", StringUtil.getSingInfo(context, context.getPackageName()));
        param.put("token", SPU.getParam(context, "token", "").toString());


        BaseApi.newInstance().modifypwd(param).enqueue(new Callback<LM>() {
            @Override
            public void onResponse(Call<LM> call, Response<LM> response) {

                try {
                    LGU.D("----------"+ response);
                    LM body = response.body();
                    LGU.D("----------"+body);

                    if (body.getCode() == 1) {
                        iNet.response(TAG, body.getData());
                    } else {
                        Toast.makeText(context, body.getMsg(), Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    LGU.D("----------"+e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<LM> call, Throwable t) {
                LGU.D(t.getLocalizedMessage());
            }
        });

    }




    public static void MODIFYPAYPWD(final Context context, Map<String, String> param, final Class clazz, final int TAG, final INet iNet) {
        if (param == null)
            param = new HashMap<>();
        param.put("sign", "1");
        param.put("appSign", StringUtil.getSingInfo(context, context.getPackageName()));
        param.put("token", SPU.getParam(context, "token", "").toString());


        BaseApi.newInstance().modifyPaypwd(param).enqueue(new Callback<LM>() {
            @Override
            public void onResponse(Call<LM> call, Response<LM> response) {

                try {
                    LGU.D("----------"+ response);
                    LM body = response.body();
                    LGU.D("----------"+body);

                    if (body.getCode() == 1) {
                        iNet.response(TAG, body.getData());
                    } else {
                        Toast.makeText(context, body.getMsg(), Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    LGU.D("----------"+e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<LM> call, Throwable t) {
                LGU.D(t.getLocalizedMessage());
            }
        });

    }

















    public static void INCOMING(final Context context, Map<String, String> param, final Class clazz, final int TAG, final INet iNet) {
        if (param == null)
            param = new HashMap<>();
        param.put("sign", "1");
        param.put("appSign", StringUtil.getSingInfo(context, context.getPackageName()));
        param.put("token", SPU.getParam(context, "token", "").toString());


        BaseApi.newInstance().incoming(param).enqueue(new Callback<CoinList>() {
            @Override
            public void onResponse(Call<CoinList> call, Response<CoinList> response) {

                try {
                    LGU.D("----------"+ response);
                    CoinList body = response.body();
                    LGU.D("----------"+body);

                    if (body.getCode() == 1) {
                        iNet.response(TAG, body.getData());
                    } else {
                        Toast.makeText(context, body.getMsg(), Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    LGU.D("----------"+e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<CoinList> call, Throwable t) {
                LGU.D(t.getLocalizedMessage());
            }
        });

    }


    public static void DETAIL(final Context context, Map<String, String> param, final Class clazz, final int TAG, final INet iNet) {
        if (param == null)
            param = new HashMap<>();
        param.put("sign", "1");
        param.put("appSign", StringUtil.getSingInfo(context, context.getPackageName()));
        param.put("token", SPU.getParam(context, "token", "").toString());


        BaseApi.newInstance().detail(param).enqueue(new Callback<CoinList>() {
            @Override
            public void onResponse(Call<CoinList> call, Response<CoinList> response) {

                try {
                    LGU.D("----------"+ response);
                    CoinList body = response.body();
                    LGU.D("----------"+body);

                    if (body.getCode() == 1) {
                        iNet.response(TAG, body.getData());
                    } else {
                        Toast.makeText(context, body.getMsg(), Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    LGU.D("----------"+e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<CoinList> call, Throwable t) {
                LGU.D(t.getLocalizedMessage());
            }
        });

    }




    public static void GETINCOMING(final Context context, Map<String, String> param, final Class clazz, final int TAG, final INet iNet) {
        if (param == null)
            param = new HashMap<>();
        param.put("sign", "1");
        param.put("appSign", StringUtil.getSingInfo(context, context.getPackageName()));
        param.put("token", SPU.getParam(context, "token", "").toString());


        BaseApi.newInstance().getIncoming(param).enqueue(new Callback<LM>() {
            @Override
            public void onResponse(Call<LM> call, Response<LM> response) {

                try {
                    LGU.D("----------"+ response);
                    LM body = response.body();
                    LGU.D("----------"+body);

                    if (body.getCode() == 1) {
                        iNet.response(TAG, body.getData());
                    } else {
                        Toast.makeText(context, body.getMsg(), Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    LGU.D("----------"+e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<LM> call, Throwable t) {
                LGU.D(t.getLocalizedMessage());
            }
        });

    }










    public static void USERACCOUNT(final Context context, Map<String, String> param, final Class clazz, final int TAG, final INet iNet) {
        if (param == null)
            param = new HashMap<>();
        param.put("sign", "1");
        param.put("appSign", StringUtil.getSingInfo(context, context.getPackageName()));
        param.put("token", SPU.getParam(context, "token", "").toString());


        BaseApi.newInstance().userAccount(param).enqueue(new Callback<UA>() {
            @Override
            public void onResponse(Call<UA> call, Response<UA> response) {

                try {
                    LGU.D("----------"+ response);
                    UA body = response.body();
                    LGU.D("----------"+body);

                    if (body.getCode() == 1) {
                        iNet.response(TAG, body.getData());
                    } else {
                        Toast.makeText(context, body.getMsg(), Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    LGU.D("----------"+e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<UA> call, Throwable t) {
                LGU.D(t.getLocalizedMessage());
            }
        });

    }




    public static void GETMONEY(final Context context, Map<String, String> param, final Class clazz, final int TAG, final INet iNet) {
        if (param == null)
            param = new HashMap<>();
        param.put("sign", "1");
        param.put("appSign", StringUtil.getSingInfo(context, context.getPackageName()));
        param.put("token", SPU.getParam(context, "token", "").toString());


        BaseApi.newInstance().getMoney(param).enqueue(new Callback<UA>() {
            @Override
            public void onResponse(Call<UA> call, Response<UA> response) {

                try {
                    LGU.D("----------"+ response);
                    UA body = response.body();
                    LGU.D("----------"+body);

                    if (body.getCode() == 1) {
                        iNet.response(TAG, body.getData());
                    } else {
                        Toast.makeText(context, body.getMsg(), Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    LGU.D("----------"+e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<UA> call, Throwable t) {
                LGU.D(t.getLocalizedMessage());
            }
        });

    }


}
