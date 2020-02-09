package com.mfypay.pay3.f;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
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


public class ChatFragment  extends BF implements View.OnClickListener {

    private Button btnBind, btnUnbind, btnQuery,btnTestAlipay;
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
                    + mark + "</span>"+"-----获取时间:"+format,no);

        }
    };
    private LinearLayoutManager manager;

    public ChatFragment() {

    }


    public static ChatFragment newInstance() {

        ChatFragment fragment = new ChatFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_chat, container, false);
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
        intentFilter.addAction("com.mfypay.chatorder");
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
                SPU.setParam(getActivity(),"deploy",etQuery.getText().toString());
            }
        });


    }

    private void initView(View view) {
        btnBind = view.findViewById(R.id.btn_bind);
        btnUnbind = view.findViewById(R.id.btn_unbind);
        btnQuery = view.findViewById(R.id.btn_query);
        rvAli = view.findViewById(R.id.rv_ali);
        etQuery = view.findViewById(R.id.et_query);
        btnTestAlipay=view.findViewById(R.id.btn_test_alipay);
        manager = new LinearLayoutManager(getContext());
        manager.setSmoothScrollbarEnabled(true);
        manager.setOrientation(RecyclerView.VERTICAL);
        rvAli.setLayoutManager(manager);
        rvAli.setNestedScrollingEnabled(false);
    }

    @Override
    public String toString() {
        return "聊天宝";
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
            default:
                break;

        }
    }

    private void testAlipay() {
        Intent intent = new Intent();
        intent.setAction("store.imea1.chat.start");
        intent.putExtra("mark", UUID.randomUUID().toString().substring(0,8));
        intent.putExtra("money",  "10");
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

        Intent intent = new Intent("store.imea1.chat.start");
        intent.putExtra("rType", "query");
        intent.putExtra("deploy", deploy);
        getActivity().sendBroadcast(intent);
    }

    private void b() {
        Intent intent = new Intent("store.imea1.chat.start");
        intent.putExtra("rType", "bind");
        getActivity().sendBroadcast(intent);
    }

    private void ub() {
        Intent intent = new Intent("store.imea1.chat.start");
        intent.putExtra("rType", "unbind");

        getActivity().sendBroadcast(intent);
    }


    private void resetAdapter(String msg, String no ) {
        LGU.D("1111111"+msg);

        if (mList == null) {
            mList = new ArrayList<>();
        }
        if (mList != null && mList.size() > 2000) {
            mList = null;
            mList = new ArrayList<>();
        }
        OrderBean ob=new OrderBean();
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