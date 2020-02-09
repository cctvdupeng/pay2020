package com.mfypay.pay3.n;

/**
 * Created by 小饭 on 2018/7/4.
 */

public interface INet {
    void fetch(int TAG);
    void  response(int TAG, Object o);
}
