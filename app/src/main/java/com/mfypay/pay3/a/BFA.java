package com.mfypay.pay3.a;



import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.mfypay.pay3.f.BF;

import java.util.List;

public abstract class BFA extends FragmentPagerAdapter {
    private List<BF> mList;


    public BFA(FragmentManager fm, List<BF> mList) {
        super(fm);
        this.mList = mList;
    }
    @Override
    public Fragment getItem(int i) {
        return mList.isEmpty()?null:mList.get(i);
    }

    @Override
    public int getCount() {
        return mList.isEmpty()?0:mList.size();
    }
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mList.get(position).toString();
    }


}
