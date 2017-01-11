package com.huangj.myapp.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

import com.huangj.myapp.R;

public class CancelService extends Service {
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
                        SystemClock.sleep(1500);
                        Log.d("AigeStudio", "ProtectService");
                        startService(new Intent(CancelService.this, MyService.class));
                    }
                }
            }).start();
        }
        return super.onStartCommand(intent, flags, startId);
    }
}
