package com.mfypay.pay3.ac;

import android.os.Bundle;
import android.widget.ListView;


import androidx.appcompat.app.AppCompatActivity;

import com.mfypay.pay3.R;
import com.mfypay.pay3.a.ListsAdapter;
import com.mfypay.pay3.m.CoinModel;
import com.mfypay.pay3.n.Api;
import com.mfypay.pay3.n.INet;

import java.util.List;

public class IncomingActivity extends AppCompatActivity implements INet {
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incoming);
        lv = findViewById(R.id.lv);
        fetch(1);
    }

    @Override
    public void fetch(int TAG) {
        if (TAG == 1) {
            Api.INCOMING(this, null, CoinModel.class, TAG, this);
        }

    }

    @Override
    public void response(int TAG, Object o) {
        if (o == null)
            return;
        if (TAG == 1) {
            ListsAdapter adapter = new ListsAdapter(this, (List<CoinModel>) o);

            lv.setAdapter(adapter);
        }
    }
}
