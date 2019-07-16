package com.ejc.appli;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import com.ejc.appli.notif.Alarm;
import com.ejc.appli.tools.UpdateBDDAsync;
import com.ejc.appli.test.R;


public class SplashScreen extends Activity {

   protected void onCreate(Bundle savedInstanceState){
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_splash);

       UpdateBDDAsync mUpdate = new UpdateBDDAsync(this.getApplicationContext());
       String[] json = new String[4];
       json[0]="jwanted";
       json[1]="jevent";
       json[2]="jarticle";
       json[3]="jinterview";
       mUpdate.execute(json);

       createNotificationChannel();

        Alarm mAlarm = new Alarm();
        mAlarm.setAlarm(this.getBaseContext());

       new Handler().postDelayed(new Runnable() {
           @Override
           public void run(){
               Intent i = new Intent(SplashScreen.this,MainActivity.class);
               startActivity(i);
               finish();
           }
       },3000);


   }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Essai";
            String description = "DescEssai";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("EjC35170", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

}
