package com.mfypay.pay3.ac;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSON;
import com.mfypay.pay3.R;
import com.mfypay.pay3.m.UserAccount;
import com.mfypay.pay3.n.Api;
import com.mfypay.pay3.n.INet;
import com.mfypay.pay3.util.LGU;


import java.util.HashMap;
import java.util.Map;

public class GetCashActivity extends AppCompatActivity implements INet {
    private TextView tvAccount, tvDeposit, tvFreeze;
    private EditText etMoney, etPhone, etAccount, etName,etInfo;
    private Button btnOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_cash);
        tvAccount = findViewById(R.id.tv_account);
        tvDeposit = findViewById(R.id.tv_deposit);
        tvFreeze = findViewById(R.id.tv_freeze);


        etPhone = findViewById(R.id.et_phone);
        etAccount = findViewById(R.id.et_accout);
        etName = findViewById(R.id.et_name);
        etInfo=findViewById(R.id.et_info);


        etMoney = findViewById(R.id.et_money);
        btnOk = findViewById(R.id.ok);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((etAccount.getText() == null)||TextUtils.isEmpty(etAccount.getText().toString())) {
                    Toast.makeText(GetCashActivity.this, "账号不能为空", Toast.LENGTH_LONG).show();

                    return;
                }



                if (etMoney.getText() != null && !TextUtils.isEmpty(etMoney.getText().toString())) {
                    if (account != null && Double.parseDouble(account.getCash()) >= Double.parseDouble(etMoney.getText().toString())) {

                        fetch(2);
                    }

                } else {
                    Toast.makeText(GetCashActivity.this, "金额不能为空", Toast.LENGTH_LONG).show();
                }
            }
        });
        fetch(1);
    }

    @Override
    public void fetch(int TAG) {
        if (TAG == 1) {
            Api.USERACCOUNT(this, null, UserAccount.class, TAG, this);
        } else if (TAG == 2) {

            Map<String, String> map = new HashMap<>();


            Map<String,String> bank=new HashMap<>();

            bank.put("bankCode","ALIPAY");
            bank.put("userName",TextUtils.isEmpty(etName.getText())?"无":etName.getText().toString());
            bank.put("phoneNum",TextUtils.isEmpty(etPhone.getText())?"无":etPhone.getText().toString());
            bank.put("bankNum",TextUtils.isEmpty(etAccount.getText())?"无":etAccount.getText().toString());
            bank.put("bankInfo",TextUtils.isEmpty(etInfo.getText())?"无":etInfo.getText().toString());
            map.put("bank", Base64.encodeToString(JSON.toJSONString(bank).getBytes(),Base64.DEFAULT));

            map.put("money", etMoney.getText().toString());
            Api.GETMONEY(this, map, UserAccount.class, TAG, this);

        }

    }

    UserAccount account;

    @Override
    public void response(int TAG, Object o) {
        if (o == null)
            return;
        if (TAG == 1) {
            account = (UserAccount) o;
            LGU.D(account.toString());
            tvFreeze.setText(account.getFreeze());
            tvDeposit.setText(account.getDeposit());
            tvAccount.setText(account.getCash());
        }
    }
}
