package com.mfypay.pay3.a;

import android.content.Context;
import android.content.Intent;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.snackbar.Snackbar;
import com.mfypay.pay3.App;
import com.mfypay.pay3.R;
import com.mfypay.pay3.m.AD;
import com.mfypay.pay3.m.AccModel;
import com.mfypay.pay3.m.SbM;
import com.mfypay.pay3.n.Api;
import com.mfypay.pay3.n.INet;
import com.mfypay.pay3.util.DBManager;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by 小饭 on 2018/8/22.
 */

public class AccountAdapter extends BaseAdapter implements INet {

    private Context ctx;
    private List<AD> list;
    private int id;
    private String device_id;
    private int type = 1;

    private SbM sbM = new SbM();

    public List<AD> getList() {
        return list;
    }

    public void setList(List<AD> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public AccountAdapter(Context ctx, List<AD> list) {
        this.ctx = ctx;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {

            convertView = LayoutInflater.from(ctx).inflate(R.layout.item_account, parent, false);

            viewHolder = new ViewHolder();

            viewHolder.itemTvId = convertView.findViewById(R.id.item_tv_id);
            viewHolder.itemTvName = convertView.findViewById(R.id.item_tv_name);
            viewHolder.itemTvBind = convertView.findViewById(R.id.item_tv_bind);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();

        }


        if (App.getContext().getSocket().connected() && !TextUtils.isEmpty(App.getContext().getSocket().id())) {
            final String bind = list.get(position).getSocket_id();
            if (!TextUtils.isEmpty(bind)) {
                viewHolder.itemTvBind.setText(App.getContext().getSocket().id().equals(bind) ? "已绑定" : "未绑定");
            }
        } else {
            viewHolder.itemTvBind.setText("未绑定");
        }


        viewHolder.itemTvId.setText(String.valueOf(list.get(position).getId()));
        viewHolder.itemTvName.setText(list.get(position).getUsername());


        viewHolder.itemTvBind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolder.itemTvBind.setClickable(false);

                id = list.get(position).getId();

                if (((TextView) v).getText().toString().equals("已绑定")) {


                    sbM.setaId(String.valueOf(id));
                    sbM.setDataType(2);


                    DBManager dbManager = new DBManager(App.getContext());
                    dbManager.delAccByAccId(String.valueOf(list.get(position).getId()));
                } else {
                    sbM.setaId(String.valueOf(id));
                    sbM.setDataType(1);

                    if (!App.getContext().getSocket().connected()) {
//                    ctx.startService(new Intent(ctx, PayService.class));
//                    App.getContext().getmScocket().connect();
                        Snackbar.make(v, "未连接,请先连接或断开连接重新尝试连接", Snackbar.LENGTH_SHORT).show();
                        return;
                    }


                    DBManager dbManager = new DBManager(App.getContext());
                    AccModel accModel = new AccModel();
                    accModel.setAcc_id(String.valueOf(list.get(position).getId()));
                    accModel.setType(String.valueOf(list.get(position).getIstype()));
                    accModel.setSocket_id(App.getContext().getSocket().id());
                    dbManager.addAccount(accModel);
                }

                sbM.setType(list.get(position).getIstype());


                App.getContext().getSocket().emit("service", com.alibaba.fastjson.JSONObject.toJSONString(sbM));
                fetch(2);
                viewHolder.itemTvBind.setClickable(true);





            }
        });


        return convertView;
    }


    @Override
    public void fetch(int TAG) {

        if (TAG == 1) {

            Map<String, String> param = new HashMap<>();
            param.put("id", String.valueOf(id));
            param.put("device_id", device_id);
            Api.BIND_DEVICE(ctx, param, null, TAG, this);

        } else if (TAG == 2) {

            Map<String, String> param = new HashMap<>();
            param.put("id", String.valueOf(id));
            param.put("type", String.valueOf(type));
            Api.LOAD_ACCOUNT(ctx, param, null, TAG, this);
        }
    }

    @Override
    public void response(int TAG, Object o) {


        if (o == null)
            return;


        if (TAG == 1) {
            fetch(2);

        } else if (TAG == 2) {
            list = (List<AD>) o;
            setList(list);
            notifyDataSetInvalidated();


        }


    }

    private class ViewHolder {
        private TextView itemTvName;
        private TextView itemTvId;
        private Button itemTvBind;


    }
}
