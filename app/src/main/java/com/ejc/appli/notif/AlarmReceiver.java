package com.ejc.appli.notif;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.ejc.appli.tools.UpdateBDDAsync;


public class AlarmReceiver extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction() != null) {

            UpdateBDDAsync mUpdate = new UpdateBDDAsync(context);
            String[] json = new String[4];
            json[0] = "jwanted";
            json[1] = "jevent";
            json[2] = "jarticle";
            json[3] = "jinterview";

            switch (intent.getAction()) {
                case Intent.ACTION_DATE_CHANGED:
                    mUpdate.execute(json);
                    break;
                case Intent.ACTION_BOOT_COMPLETED:
                    mUpdate.execute(json);
                    break;
            }
        }
    }
}