package com.mfypay.pay3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mfypay.pay3.n.Api;
import com.mfypay.pay3.n.INet;
import com.mfypay.pay3.util.LGU;
import com.mfypay.pay3.util.SPU;
import com.mfypay.pay3.util.StringUtil;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by 小饭 on 2018/7/20.
 */

public class LoginActivity extends AppCompatActivity implements INet {
    private EditText etPwd, etName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        etName = findViewById(R.id.et_user);
        etPwd = findViewById(R.id.et_pwd);
        Object param = SPU.getParam(LoginActivity.this, "token", "");
        if (param != null && !TextUtils.isEmpty(param.toString())) {

            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();

        }


        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        findViewById(R.id.regedit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regedit();
            }
        });
    }

    private void regedit() {
        startActivity(new Intent(this,RegeditActivity.class));
    }

    private void login() {

        if (TextUtils.isEmpty(etName.getText().toString())) {

            Toast.makeText(this, "用户名不能为空", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(etPwd.getText().toString())) {

            Toast.makeText(this, "密码不能为空", Toast.LENGTH_LONG).show();
            return;
        }


        fetch(1);

    }


    @Override
    public void fetch(int TAG) {


        if (TAG == 1) {

            Map<String, String> param = new HashMap<>();

            param.put("password", etPwd.getText().toString());
            param.put("username", etName.getText().toString());
            param.put("sign", "1");
            param.put("appSign", StringUtil.getSingInfo(this, this.getPackageName()));
            LGU.D(StringUtil.getSingInfo(this, this.getPackageName()) + "---");


            Api.LOGIN(this, param, null, TAG, this);
        }

    }

    /**
     * @param TAG
     * @param o
     */
    @Override
    public void response(int TAG, Object o) {

        if (o == null)
            return;
        if (TAG == 1) {

            SPU.setParam(this, "token", o.toString());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }

    }
}
