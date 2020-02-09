package com.mfypay.pay3;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.mfypay.pay3.a.HA;
import com.mfypay.pay3.a.HorizontalListViewAdapter;
import com.mfypay.pay3.ac.PayListActivity;
import com.mfypay.pay3.f.BF;
import com.mfypay.pay3.f.WangxinFragment;
import com.mfypay.pay3.m.CoinModel;
import com.mfypay.pay3.n.Api;
import com.mfypay.pay3.n.INet;
import com.mfypay.pay3.r.AlarmReceiver;
import com.mfypay.pay3.r.DaemonService;
import com.mfypay.pay3.util.LGU;
import com.mfypay.pay3.util.SPU;
import com.mfypay.pay3.view.HorizontalListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements INet {
    private ViewPager vpHome;
    private HA ha;
    private TabLayout tlHome;
    private CheckBox cbShow;
    private CheckBox cbTip;
    private CheckBox cbQuery;

    private HorizontalListView mHorizontalListView;
    private HorizontalListViewAdapter mHorizontalListViewAdapter;

    private AlarmReceiver alarmReceiver = new AlarmReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.mfypay.run");
        intentFilter.addAction("com.mfypay.autoali");
        intentFilter.addAction("store.imea1.result");
        registerReceiver(alarmReceiver, intentFilter);
        init();


    }


    private void init() {


        startService(new Intent(this, DaemonService.class));

        mHorizontalListView = findViewById(R.id.horizontal_lv);
//        tlHome = findViewById(R.id.tl_home);
//        tlHome.setTabGravity(TabLayout.GRAVITY_FILL);
//        tlHome.setTabMode(TabLayout.MODE_FIXED);
        vpHome = findViewById(R.id.vp_home);
        cbTip = findViewById(R.id.cb_tip);
        cbShow = findViewById(R.id.cb_show);
        cbQuery = findViewById(R.id.cb_query);
        cbShow.setChecked("true".equalsIgnoreCase(String.valueOf(SPU.getParam(MainActivity.this, "isShow", "false"))));
        cbQuery.setChecked("true".equalsIgnoreCase(String.valueOf(SPU.getParam(MainActivity.this, "isQuery", "false"))));
        cbTip.setChecked("true".equalsIgnoreCase(String.valueOf(SPU.getParam(MainActivity.this, "isTip", "false"))));
        cbShow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SPU.setParam(MainActivity.this, "isShow", String.valueOf(isChecked));
            }
        });
        cbTip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SPU.setParam(MainActivity.this, "isTip", String.valueOf(isChecked));
            }
        });
        cbQuery.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SPU.setParam(MainActivity.this, "isQuery", String.valueOf(isChecked));
            }
        });
        if (ha == null) {
            List<BF> bfs = new ArrayList<>();
            bfs.add(WangxinFragment.newInstance());
//            bfs.add(MBF.newInstance());
//            bfs.add(WFragment.newInstance());
//            bfs.add(ChatFragment.newInstance());
//            bfs.add(DDFragment.newInstance());
//            bfs.add(LKLFragment.newInstance());
//            bfs.add(XXFragment.newInstance());
            ha = new HA(getSupportFragmentManager(), bfs);
            vpHome.setAdapter(ha);
        } else {
            ha.notifyDataSetChanged();
        }
//        tlHome.setupWithViewPager(vpHome, true);
        mHorizontalListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LGU.D("===============");
                CoinModel itemAtPosition = (CoinModel) parent.getItemAtPosition(position);

                startActivity(new Intent(getBaseContext(), PayListActivity.class).putExtra("coin", itemAtPosition));

            }
        });

//        fetch(1);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
      //  getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(alarmReceiver);
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
           // case R.id.quick:
//                SPU.setParam(MainActivity.this, "token", "");
//                SPU.setParam(App.getContext(), "isTip", String.valueOf(false));
//                SPU.setParam(App.getContext(), "isShow", String.valueOf(false));
//                SPU.setParam(App.getContext(), "isQuery", String.valueOf(false));
//                DBManager dbManager = new DBManager(App.getContext());
//                dbManager.delAccount();
//                startActivity(new Intent(this, LoginActivity.class));
//                finish();
//                break;
          //  case R.id.me:
              //  startActivity(new Intent(MainActivity.this, UserActivity.class));
              //  break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(MainActivity.this, "请按home键退出", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void fetch(int TAG) {
        if (TAG == 1) {
            LGU.D("=====================");
            Api.LOADCOIN(this, null, CoinModel.class, TAG, this);

        }
    }

    @Override
    public void response(int TAG, Object o) {
        if (o == null)
            return;
        if (TAG == 1) {

            mHorizontalListViewAdapter = new HorizontalListViewAdapter((List<CoinModel>) o, this);


            mHorizontalListView.setAdapter(mHorizontalListViewAdapter);


        }
    }
}
