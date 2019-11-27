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

       // json is the name of the databases we need to retrieve from ejc.fr
       String[] json = new String[5];
       json[0]="jwanted";
       json[1]="jevent";
       json[2]="jarticle";
       json[3]="jteam";
       json[4]="jhistory";

       // Launch the async process to retrieve databases and save it to android cache
       UpdateBDDAsync mUpdate = new UpdateBDDAsync(this.getApplicationContext());
       mUpdate.execute(json);


       // After 3 sec, the MainActivity is launched even if UpdateBDDAsync is still running
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
