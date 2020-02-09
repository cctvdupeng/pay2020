package com.mfypay.pay3;

import android.app.Application;
import android.content.Context;


import com.mfypay.pay3.listerner.ConnListener;
import com.mfypay.pay3.listerner.DissConnListener;
import com.mfypay.pay3.listerner.ErrListerner;
import com.mfypay.pay3.listerner.NewMsgListener;
import com.mfypay.pay3.util.CrashHandler;

import io.socket.client.IO;
import io.socket.client.Socket;

/**
 * Created by ruo on 2020/2/5.
 */

public class App extends Application {

    private Socket mSocket;

    {
        try {
            mSocket = IO.socket("http://meal.zaqsap.cn:5946");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Socket getSocket() {
        return mSocket;
    }

    private static App app;

    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler.getInstance().init(this);
        app = this;
        initCloudChannel(this);

    }


    public void initCloudChannel(Context context) {
        Socket socket = getSocket();
        socket.on(Socket.EVENT_CONNECT_ERROR, new ErrListerner());
        socket.on(Socket.EVENT_CONNECT_TIMEOUT, new ErrListerner());
        socket.on(Socket.EVENT_CONNECT, new ConnListener());
        socket.on("client", new NewMsgListener());
        socket.on(Socket.EVENT_DISCONNECT, new DissConnListener());
        socket.connect();
    }


    public static App getContext() {
        return app;
    }
}
