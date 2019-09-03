package com.ejc.appli;

import android.content.Intent;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

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

       new Handler().postDelayed(new Runnable() {
           @Override
           public void run(){
               Intent i = new Intent(SplashScreen.this,MainActivity.class);
               startActivity(i);
               finish();
           }
       },3000);


   }

}
