package com.mfypay.pay3.a;

import android.content.Context;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mfypay.pay3.R;
import com.mfypay.pay3.b.OrderBean;

import java.util.List;

public class AAdapter extends RecyclerView.Adapter<AAdapter.AliVH> {

    private List<OrderBean> mList;
    private Context mCtx;

    public AAdapter(List<OrderBean> mList, Context mCtx) {
        this.mList = mList;
        this.mCtx = mCtx;
    }

    @NonNull
    @Override
    public AliVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        View inflate = LayoutInflater.from(mCtx).inflate(R.layout.item_ali, viewGroup, false);


        return new AliVH(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull AliVH aliVH, int i) {

      try {
          aliVH.view.setText(Html.fromHtml(mList.get(i).getShow()));
      }catch (Exception e){
          e.printStackTrace();
      }
    }

    @Override
    public int getItemCount() {
        return mList.isEmpty() ? 0 : mList.size();
    }

    class AliVH extends RecyclerView.ViewHolder {
        TextView view;

        public AliVH(@NonNull View itemView) {
            super(itemView);
            view = itemView.findViewById(R.id.tv_ali);
        }


    }
}
