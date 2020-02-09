package com.mfypay.pay3.f;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.mfypay.pay3.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WFragment extends BF {


    public WFragment() {

    }


    public static WFragment newInstance() {
        WFragment fragment = new WFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_w, container, false);
    }

    @Override
    public String toString() {
        return "微信";
    }
}
