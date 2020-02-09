package com.mfypay.pay3.a;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mfypay.pay3.R;
import com.mfypay.pay3.m.CoinModel;
import com.mfypay.pay3.util.ImgUtil;

import java.util.List;

public class HorizontalListViewAdapter extends BaseAdapter {
    private List<CoinModel> mList;
    private Context mCtx;

    public HorizontalListViewAdapter(List<CoinModel> mList, Context mCtx) {
        this.mList = mList;
        this.mCtx = mCtx;
    }

    @Override
    public int getCount() {
        return mList.isEmpty() ? 0 : mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holde holde;
        if (convertView == null) {
            convertView = LayoutInflater.from(mCtx).inflate(R.layout.item_coin, parent, false);
            holde = new Holde();
            holde.imageView = convertView.findViewById(R.id.iv);
            holde.textView = convertView.findViewById(R.id.tv);

            convertView.setTag(holde);
        } else {
            holde = (Holde) convertView.getTag();
        }
        ImgUtil.loadImage(mCtx,holde.imageView,mList.get(position).getUrl());
        holde.textView.setText(mList.get(position).getName());

        return convertView;
    }

    class Holde {
        ImageView imageView;
        TextView textView;

    }
}
