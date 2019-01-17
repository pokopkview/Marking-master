package com.intelligent.marking.service;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import com.intelligent.marking.activity.WelcomeInterfaceActivity;

public class BootStartReceicer extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            Intent intents = new Intent(context,WelcomeInterfaceActivity.class);
            intents.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intents);

//            Intent intents = new Intent(Intent.ACTION_MAIN);
//         intents.addCategory(Intent.CATEGORY_LAUNCHER);
//         ComponentName cn = new ComponentName("com.intelligent.marking", WelcomeInterfaceActivity.class.getName());
//         intents.setComponent(cn);
//         context.startActivity(intents);
        }
    }
}
