package com.huangj.myapp.service;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

import com.huangj.myapp.R;

import org.xutils.common.Callback;

public class MyService extends Service {
    private static boolean sPower = true, isRunning;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!isRunning) {
            isRunning = true;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (sPower) {
                        if (System.currentTimeMillis() >= 123456789000000L) {
                            sPower = false;
                        }
                        Log.d("AigeStudio", "DaemonService");
                        startService(new Intent(MyService.this, CancelService.class));
                        SystemClock.sleep(3000);
                    }
                }
            }).start();
        }
        return super.onStartCommand(intent, flags, startId);
    }
}
