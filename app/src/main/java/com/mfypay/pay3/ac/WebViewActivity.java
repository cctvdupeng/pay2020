package com.mfypay.pay3.ac;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;


import androidx.appcompat.app.AppCompatActivity;

import com.mfypay.pay3.R;
import com.mfypay.pay3.util.LGU;

import static android.view.KeyEvent.KEYCODE_BACK;

public class WebViewActivity extends AppCompatActivity {
    private WebView wb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        wb = findViewById(R.id.wb);
        wb.getSettings().setJavaScriptEnabled(true);

        String url = getIntent().getStringExtra("url");
        LGU.D(url);


        wb.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                if (!TextUtils.isEmpty(view.getTitle())){
                    setTitle(view.getTitle());
                }
                if (url.startsWith("http")) {
                    view.loadUrl(url);
                } else {
                    Intent intent = new Intent();
                    intent.setData(Uri.parse(url));
                    startActivity(intent);
                }

                return true;
            }
        });
        wb.loadUrl(url);


    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KEYCODE_BACK) && wb.canGoBack()) {
            wb.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
