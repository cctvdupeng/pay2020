package com.mfypay.pay3.ac;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import androidx.appcompat.app.AppCompatActivity;

import com.mfypay.pay3.R;
import com.mfypay.pay3.a.HorizontalListViewAdapter;
import com.mfypay.pay3.m.CoinModel;
import com.mfypay.pay3.n.Api;
import com.mfypay.pay3.n.INet;
import com.mfypay.pay3.util.LGU;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PayListActivity extends AppCompatActivity implements INet {
    private CoinModel coinModel;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_list);
        coinModel = (CoinModel) getIntent().getSerializableExtra("coin");
        LGU.D(coinModel + "");
        setTitle("充值");
        lv = findViewById(R.id.lv);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CoinModel itemAtPosition = (CoinModel) parent.getItemAtPosition(position);

                startActivity(new Intent(getBaseContext(), WebViewActivity.class).putExtra("url", itemAtPosition.getUrl()));
            }
        });
        fetch(1);


    }

    @Override
    public void fetch(int TAG) {
        if (1 == TAG) {
            LGU.D("=====================");
            Map<String,String> map=new HashMap<>();
            map.put("amount",coinModel.getAmount());
            map.put("coinId",coinModel.getId());

            Api.PAYLIST(this, map, CoinModel.class, TAG, this);
        }
    }

    @Override
    public void response(int TAG, Object o) {
        if (o == null)
            return;
        if (TAG == 1) {

            HorizontalListViewAdapter mHorizontalListViewAdapter = new HorizontalListViewAdapter((List<CoinModel>) o, this);

            lv.setAdapter(mHorizontalListViewAdapter);
        }

    }
}
