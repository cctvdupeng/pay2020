package com.mfypay.pay3.ac;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.mfypay.pay3.R;
import com.mfypay.pay3.m.LM;
import com.mfypay.pay3.n.Api;
import com.mfypay.pay3.n.INet;

import java.util.HashMap;
import java.util.Map;

public class ModifyPayPwdActivity extends AppCompatActivity implements INet {




    private EditText etOPwd;
    private EditText etPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_pay_pwd);
        etOPwd = findViewById(R.id.et_opwd);
        etPwd = findViewById(R.id.et_pwd);
        setTitle("修改支付密码");
        findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modify();
            }
        });
    }

    private void modify() {
        if (etOPwd.getText() == null || TextUtils.isEmpty(etOPwd.getText().toString())) {
            Toast.makeText(this, "原密码不能为空", Toast.LENGTH_LONG).show();
            return;
        }
        if (etPwd.getText() == null || TextUtils.isEmpty(etPwd.getText().toString())) {
            Toast.makeText(this, "原密码不能为空", Toast.LENGTH_LONG).show();
            return;
        }
        fetch(1);
    }

    @Override
    public void fetch(int TAG) {
        if (TAG == 1) {
            Map<String, String> map = new HashMap<>();
            map.put("pwd", etPwd.getText().toString());
            map.put("opwd", etOPwd.getText().toString());
            Api.MODIFYPAYPWD(this, map, LM.class, TAG, this);
        }

    }

    @Override
    public void response(int TAG, Object o) {
        if (o == null)
            return;
        if (TAG == 1) {
            Toast.makeText(this, o + "", Toast.LENGTH_LONG).show();

        }
    }
}
