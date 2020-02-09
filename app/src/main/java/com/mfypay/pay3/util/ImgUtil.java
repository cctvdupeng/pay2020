package com.mfypay.pay3.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mfypay.pay3.R;

public class ImgUtil {
    public static void loadImage(Context context, ImageView  view , String url) {

        Glide.with(context).load(url).placeholder(R.mipmap.ic_launcher).error(android.R.drawable.btn_default_small).into(view);
    }

}
