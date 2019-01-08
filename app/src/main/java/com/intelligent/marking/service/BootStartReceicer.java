package com.intelligent.marking.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.intelligent.marking.activity.LoginActivity;

public class BootStartReceicer extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            Intent intents = new Intent(context,LoginActivity.class);
            intents.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intents);
        }
    }
}
