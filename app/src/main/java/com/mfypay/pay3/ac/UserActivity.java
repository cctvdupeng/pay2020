package com.mfypay.pay3.ac;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.system.Os;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.mfypay.pay3.R;


public class UserActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout llTuiJian, LLVersion, llDetail, llPwd, llPayPwd, llIncoming,llGetCash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        setTitle("我");
        llTuiJian = findViewById(R.id.ll_tuijian);
        LLVersion = findViewById(R.id.ll_version);
        llDetail = findViewById(R.id.ll_detail);

        llPwd = findViewById(R.id.ll_pwd);
        llPayPwd = findViewById(R.id.ll_pay_pwd);
        llIncoming = findViewById(R.id.ll_incoming);
        llGetCash=findViewById(R.id.ll_get_cash);
        llGetCash.setOnClickListener(this);

        llPayPwd.setOnClickListener(this);
        llPwd.setOnClickListener(this);

        llIncoming.setOnClickListener(this);
        llDetail.setOnClickListener(this);
        llTuiJian.setOnClickListener(this);
        LLVersion.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.ll_tuijian:
                startActivity(new Intent(this, IncomingActivity.class));
                break;

            case R.id.ll_version:
                startActivity(new Intent(this, WebViewActivity.class).putExtra("url", "http://meal.zaqsap.cn/mobao.html?" + "code=" + packageCode(this)));
                break;
            case R.id.ll_detail:
                startActivity(new Intent(this, DetailActivity.class));
                break;
            case R.id.ll_pay_pwd:
                startActivity(new Intent(this, ModifyPayPwdActivity.class));
                break;
            case R.id.ll_pwd:
                startActivity(new Intent(this, ModifyPwdActivity.class));
                break;
            case R.id.ll_incoming:
                startActivity(new Intent(this, ShowIncomingActivity.class));
                break;
            case  R.id.ll_get_cash:
                startActivity(new Intent(this, GetCashActivity.class));
                break;

            default:
                break;
        }
    }


    public static int packageCode(Context context) {
        PackageManager manager = context.getPackageManager();
        int code = 0;
        try {
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            code = info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return code;
    }
}