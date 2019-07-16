package com.ejc.appli.notif;

import android.content.Context;
import android.content.Intent;
import android.content.BroadcastReceiver;


public class AutoStart extends BroadcastReceiver
{
    private final Alarm alarm = new Alarm();

    @Override
    public void onReceive(Context context, Intent intent)
    {
        if (intent.getAction() != null && intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED))
        {
            alarm.setAlarm(context);
        }
    }
}