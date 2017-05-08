package com.charles.hijack;
  
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class HijackReceiver extends BroadcastReceiver {
  
    @Override  
    public void onReceive(Context context, Intent intent) {  
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {  
            Intent intent2 = new Intent(context, HijackService.class);
            context.startService(intent2);
        }
    }  
}  
