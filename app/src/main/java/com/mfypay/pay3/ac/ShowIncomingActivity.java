package com.mfypay.pay3.ac;


import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mfypay.pay3.R;
import com.mfypay.pay3.m.CoinModel;
import com.mfypay.pay3.n.Api;
import com.mfypay.pay3.n.INet;

public class ShowIncomingActivity extends AppCompatActivity implements INet {
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_incoming);
        tv = findViewById(R.id.tv);
        fetch(1);
    }

    @Override
    public void fetch(int TAG) {
        if (TAG == 1) {
            Api.GETINCOMING(this, null, CoinModel.class, TAG, this);
        }
    }

    @Override
    public void response(int TAG, Object o) {
        if (o == null)
            return;
        if (TAG == 1) {
            tv.setText(String.valueOf(o));

        }
    }
}
