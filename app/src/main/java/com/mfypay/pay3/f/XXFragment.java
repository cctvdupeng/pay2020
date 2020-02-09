package com.mfypay.pay3.f;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mfypay.pay3.R;
import com.mfypay.pay3.a.AAdapter;
import com.mfypay.pay3.b.OrderBean;
import com.mfypay.pay3.util.LGU;
import com.mfypay.pay3.util.SPU;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 */
public class XXFragment extends BF implements View.OnClickListener {

    private Button btnBind, btnUnbind, btnQuery, btnTestAlipay;
    private RecyclerView rvAli;
    private AAdapter aAdapter;
    private EditText etQuery;
    private List<OrderBean> mList = new ArrayList<>();
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String no = intent.getStringExtra("no");
            String money = intent.getStringExtra("money");
            String mark = intent.getStringExtra("mark");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
            String format = simpleDateFormat.format(new Date());

            resetAdapter("<font color='black' size='24'>订单号:</font>" + "<span color='red'>" + no + "<span>-----金额:" + "<span color='red'>" + money + "</span>" + "<span>-----备注:" + "<span color='red'>"
                    + mark + "</span>" + "-----获取时间:" + format, no);

        }
    };
    private LinearLayoutManager manager;

    public XXFragment() {

    }


    public static XXFragment newInstance() {

        XXFragment fragment = new XXFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_xx, container, false);
        init(inflate);
        return inflate;
    }

    private void init(View view) {
        initView(view);
        initListen();
        r();


    }

    private void r() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.mfypay.xxorder");
        getActivity().registerReceiver(broadcastReceiver, intentFilter);

    }

    @Override
    public void onDestroy() {
        getActivity().unregisterReceiver(broadcastReceiver);
        super.onDestroy();
    }

    private void initListen() {
        btnUnbind.setOnClickListener(this);
        btnBind.setOnClickListener(this);
        btnQuery.setOnClickListener(this);
        btnTestAlipay.setOnClickListener(this);
        etQuery.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                SPU.setParam(getActivity(), "deploy", etQuery.getText().toString());
            }
        });


    }

    private void initView(View view) {
        btnBind = view.findViewById(R.id.btn_bind);
        btnUnbind = view.findViewById(R.id.btn_unbind);
        btnQuery = view.findViewById(R.id.btn_query);
        rvAli = view.findViewById(R.id.rv_ali);
        etQuery = view.findViewById(R.id.et_query);
        btnTestAlipay = view.findViewById(R.id.btn_test_alipay);
        view.findViewById(R.id.btn_resend).setOnClickListener(this);
        manager = new LinearLayoutManager(getContext());
        manager.setSmoothScrollbarEnabled(true);
        manager.setOrientation(RecyclerView.VERTICAL);
        rvAli.setLayoutManager(manager);
        rvAli.setNestedScrollingEnabled(false);
    }

    @Override
    public String toString() {
        return "学习";
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_bind:
                b();
                break;
            case R.id.btn_unbind:
                ub();
                break;
            case R.id.btn_query:
                query();
                break;
            case R.id.btn_test_alipay:
                testAlipay();
                break;
            case R.id.btn_resend:
                reSend();
                break;
            default:
                break;

        }
    }

    private void reSend() {
        Intent intent = new Intent();
        intent.setAction("store.imea1.xx.start");
        intent.putExtra("rType", "reSend");
        intent.putExtra("toUser","9361879");
        intent.putExtra("extra","{\"a\":\"1553655226000\",\"b\":\"1553655226000\",\"c\":\"111633825\",\"d\":\"3hpkzTv7_111633825100\",\"e\":\"3hpkzTv7\",\"f\":\"0.01\",\"g\":\"1\",\"h\":\"null\",\"i\":\"1\",\"j\":\"9361879:111633825\",\"k\":\"0\",\"l\":\"1\",\"m\":\"null\",\"n\":\"26659b60\",\"o\":\"null\",\"p\":\"null\",\"q\":\"null\",\"r\":\"service=\\\"alipay.fund.stdtrustee.order.create.pay\\\"&partner=\\\"2088801132166875\\\"&_input_charset=\\\"utf-8\\\"&notify_url=\\\"https://repay.dingtalk.com/RENotify/alipay_fund_stdtrustee_order_create_pay\\\"&out_order_no=\\\"3hpkzTv7_111633825100\\\"&out_request_no=\\\"3hpkzTv7_111633825100_s\\\"&product_code=\\\"SOCIAL_RED_PACKETS\\\"&scene_code=\\\"MERCHANT_COUPON\\\"&amount=\\\"0.01\\\"&pay_strategy=\\\"CASHIER_PAYMENT\\\"&receipt_strategy=\\\"INNER_ACCOUNT_RECEIPTS\\\"&platform=\\\"DEFAULT\\\"&channel=\\\"APP\\\"&order_title=\\\"发送学习强国红包\\\"&master_order_no=\\\"2019032710002001470296022383\\\"&order_type=\\\"DEDUCT_ORDER\\\"&extra_param=\\\"{\\\"payeeShowName\\\":\\\"学习强国红包\\\"}\\\"&pay_timeout=\\\"30m\\\"&order_expired_time=\\\"60d\\\"&out_context=\\\"{\\\"dingtalk_biz_tag\\\":\\\"red_envelop\\\"}\\\"&sign=\\\"hEG91Dc%2FGt2XDgK9lDbpqiXBr0veWuff04luZ8GROmHH7SbHCjFcwWClEvnLmDh9sOI1Eaa98lxfoOC55%2F1KCqWRYI%2F01tJ8lAyy634lM6qQPMnODPC7kBIRHCj%2BD5stD0tsROauvpTr6Oalpy57byaBT347E4K9OzSsnP03k0NJ7Xqeqf51Yc81MsnxoQXrWXaxsVDRZJtm7tclRkm206neYeD9MWljYMc%2BldTJgQLYhFmSYzjJSB1tf4%2FkupxQpw2h3y5Uz3FmBz9iM%2FA6hoM2hw68KBzUwzX6lk68fG0yb0eGxAM3ZrYsTouyALwvNcvDEG%2BThBQv8NmlUFYh8g%3D%3D\\\"&sign_type=\\\"RSA\\\"\",\"s\":\"null\",\"t\":\"{\\\"ua\\\":\\\"XueXi\\\"}\",\"u\":\"0\",\"v\":\"null\"}");
        getActivity().sendBroadcast(intent);
    }

    private void testAlipay() {
        Intent intent = new Intent();
        intent.setAction("store.imea1.xx.start");
        intent.putExtra("mark", UUID.randomUUID().toString().substring(0, 8));
        intent.putExtra("money", "0.01");
        intent.putExtra("toUser","9361879");
        getActivity().sendBroadcast(intent);
    }

    private void query() {
        long deploy = 5;
        if (TextUtils.isEmpty(etQuery.getText().toString())) {
            deploy = 5;
        }

        try {
            deploy = Long.parseLong(etQuery.getText().toString());
        } catch (Exception e) {

            deploy = 5;
        }

        Intent intent = new Intent("store.imea1.xx.start");
        intent.putExtra("rType", "query");
        intent.putExtra("deploy", deploy);
        getActivity().sendBroadcast(intent);
    }

    private void b() {

        Intent intent = new Intent("store.imea1.xx.start");
        intent.putExtra("rType", "bind");
        getActivity().sendBroadcast(intent);
    }

    private void ub() {
        Intent intent = new Intent("store.imea1.xx.start");
        intent.putExtra("rType", "unbind");


        getActivity().sendBroadcast(intent);
    }


    private void resetAdapter(String msg, String no) {
        LGU.D("xx==========");

        if (mList == null) {
            mList = new ArrayList<>();
        }
        if (mList != null && mList.size() > 2000) {
            mList = null;
            mList = new ArrayList<>();
        }
        OrderBean ob = new OrderBean();
        ob.setNo(no);
        ob.setShow(msg);

        if (!mList.contains(ob)) {
            mList.add(0, ob);

            LGU.D(mList.toString());
            if (aAdapter != null) {
                aAdapter.notifyDataSetChanged();
            } else {
                aAdapter = new AAdapter(mList, getActivity());
                rvAli.setAdapter(aAdapter);
            }


        }


    }
}