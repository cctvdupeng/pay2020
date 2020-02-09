package com.mfypay.pay3.r;//package com.shoudaqian.apppay.r;


import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.os.SystemClock;

import com.mfypay.pay3.R;


/**
 * 前台Service，使用startForeground
 * 这个Service尽量要轻，不要占用过多的系统资源，否则
 * 系统在资源紧张时，照样会将其杀死
 */
public class DaemonService extends Service {
    public IBinder onBind(Intent paramIntent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT >= 18) {
            Notification.Builder builder = new Notification.Builder(this);
            builder.setSmallIcon(R.mipmap.ic_launcher);
            builder.setContentTitle(getResources().getString(R.string.app_name));
            builder.setContentText(getResources().getString(R.string.app_name));
            builder.setAutoCancel(false);
            builder.setOngoing(true);
//            startForeground(100, builder.build());
        } else {
            startForeground(100, new Notification());
        }
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent intent = new Intent("com.mfypay.run");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        (alarmManager).setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + 10000L, 120 * 1000L, pendingIntent);
    }

    public void onDestroy() {
        super.onDestroy();

        if (Build.VERSION.SDK_INT >= 18) {
            ((NotificationManager) getSystemService(NOTIFICATION_SERVICE)).cancel(100);
        }
        startService(new Intent(getApplicationContext(), DaemonService.class));
    }

    public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2) {
        return START_STICKY;
    }
}
