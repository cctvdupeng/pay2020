package com.mfypay.pay3.ac;

import android.os.Bundle;
import android.widget.ListView;


import androidx.appcompat.app.AppCompatActivity;

import com.mfypay.pay3.R;
import com.mfypay.pay3.a.DetailAdapter;
import com.mfypay.pay3.m.CoinModel;
import com.mfypay.pay3.n.Api;
import com.mfypay.pay3.n.INet;

import java.util.List;

public class DetailActivity extends AppCompatActivity implements INet {
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        lv = findViewById(R.id.lv);
        fetch(1);
    }

    @Override
    public void fetch(int TAG) {
        if (TAG == 1) {
            Api.DETAIL(this, null, CoinModel.class, TAG, this);
        }

    }

    @Override
    public void response(int TAG, Object o) {
        if (o == null)
            return;
        if (TAG == 1) {
            DetailAdapter adapter = new DetailAdapter(this, (List<CoinModel>) o);

            lv.setAdapter(adapter);
        }
    }
}
