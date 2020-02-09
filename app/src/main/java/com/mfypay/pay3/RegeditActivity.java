package com.mfypay.pay3;

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
import java.util.UUID;

public class RegeditActivity extends AppCompatActivity implements INet {
    private EditText etPwd, etName,etInvitation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regedit);
        etName = findViewById(R.id.et_user);
        etPwd = findViewById(R.id.et_pwd);
        etInvitation=findViewById(R.id.et_invitation);
        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegeditActivity.this,LoginActivity.class));
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
        if (TextUtils.isEmpty(etName.getText().toString())) {

            Toast.makeText(this, "用户名不能为空", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(etPwd.getText().toString())) {

            Toast.makeText(this, "密码不能为空", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(etInvitation.getText().toString())) {

            Toast.makeText(this, "邀请码不能为空", Toast.LENGTH_LONG).show();
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
            param.put("invitation",etInvitation.getText().toString());
            param.put("appSign", StringUtil.getSingInfo(this, this.getPackageName()));
            param.put("access_token", UUID.randomUUID().toString());
            param.put("token", UUID.randomUUID().toString());
            LGU.D(StringUtil.getSingInfo(this, this.getPackageName()) + "---");
            Api.REGEDIT(this, param, null, TAG, this);
        }
    }

    @Override
    public void response(int TAG, Object o) {
        if (o == null)
            return;
        if (TAG == 1) {


            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            startActivity(new Intent(RegeditActivity.this, LoginActivity.class));
            finish();
        }
    }
}
