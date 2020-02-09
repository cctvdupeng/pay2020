package com.mfypay.pay3.a;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mfypay.pay3.R;
import com.mfypay.pay3.m.CoinModel;

import java.util.List;

public class DetailAdapter extends BaseAdapter {
    private Context mCtx;
    private List<CoinModel> mList;

    public DetailAdapter(Context mCtx, List<CoinModel> mList) {
        this.mCtx = mCtx;
        this.mList = mList;
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
            convertView = LayoutInflater.from(mCtx).inflate(R.layout.item_list, parent, false);
            holde = new Holde();
            holde.tv = convertView.findViewById(R.id.tv);
            holde.tv2 = convertView.findViewById(R.id.tvright);
            convertView.setTag(holde);
        } else {
            holde = (Holde) convertView.getTag();
        }

        holde.tv.setText(mList.get(position).getAmount());
        holde.tv2.setText(mList.get(position).getInfo());
        return convertView;
    }

    class Holde {
        TextView tv;
        TextView tv2;

    }
}
