package com.ejc.appli.tools;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.ejc.appli.test.R;
import com.ejc.appli.SplashScreen;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@SuppressLint("StaticFieldLeak")
public class UpdateBDDAsync extends AsyncTask<String, Integer, String> {

    private final Context mContext;
    private String dateEvent1 = "a";
    private String dateArticle1 = "a";
    private String dateWanted1 = "a";
    private String dateEvent2 = "a";
    private String dateArticle2 = "a";
    private String dateWanted2 = "a";

    public UpdateBDDAsync(Context context) {
        mContext = context;
    }

    protected String doInBackground(String... strings) {
        String result = "coucou" ;
        for (String string : strings) {
            try {
                OkHttpClient client = new OkHttpClient();
                URL url = new URL("http://ejc.fr/android/" + string + ".html");
                Request request = new Request.Builder()
                        .url(url)
                        .build();
                Response response = client.newCall(request).execute();
                try {
                    CacheThis.writeObject(mContext, string, Objects.requireNonNull(response.body()).string());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        try {
            OkHttpClient client = new OkHttpClient();
            URL url = new URL("http://ejc.fr/android/jdate.html");
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Response response = client.newCall(request).execute();
            try {
                result=Objects.requireNonNull(response.body()).string();
                CacheThis.writeObject(mContext, "jdate", result);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    protected void onPreExecute() {
        try {
            String importDate = CacheThis.readObject(mContext, "jdate").toString();
            JSONArray mJasonArray = new JSONArray(importDate);
            for (int i = 0; i < mJasonArray.length(); i++) {
                JSONObject oneObject = mJasonArray.getJSONObject(i);
                switch (oneObject.getString("nom")) {
                    case "article":
                        dateArticle1 = oneObject.getString("date");
                        break;
                    case "event":
                        dateEvent1 = oneObject.getString("date");
                        break;
                    case "wanted":
                        dateWanted1 = oneObject.getString("date");
                        break;
                }
            }
        } catch (IOException | JSONException | ClassNotFoundException e) {
            e.printStackTrace();

        }
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        if (!s.equals("coucou")) {

            try {
                JSONArray mJasonArray = new JSONArray(s);
                for (int j = 0; j < mJasonArray.length(); j++) {
                    JSONObject oneObject = mJasonArray.getJSONObject(j);

                    switch (oneObject.getString("nom")) {
                        case "article":
                            dateArticle2 = oneObject.getString("date");
                            break;
                        case "event":
                            dateEvent2 = oneObject.getString("date");
                            break;
                        case "wanted":
                            dateWanted2 = oneObject.getString("date");
                            break;
                    }

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            Intent intent = new Intent(mContext, SplashScreen.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0, intent, 0);


            NotificationCompat.Builder mBuilderA = new NotificationCompat.Builder(mContext, "EjC35170")
                    .setSmallIcon(R.mipmap.ic_launcher_round)
                    .setContentTitle("Nouvel article disponible")
                    .setContentIntent(pendingIntent)
                    .setStyle(new NotificationCompat.BigTextStyle()
                            .bigText("Un nouvel article a été publié sur notre blog. Viens le découvrir."))
                    .setContentText("Un nouvel article a été publié sur notre blog. Viens le découvrir.")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setAutoCancel(true);

            NotificationCompat.Builder mBuilderE = new NotificationCompat.Builder(mContext, "EjC35170")
                    .setSmallIcon(R.mipmap.ic_launcher_round)
                    .setContentTitle("De nouveaux événements")
                    .setContentIntent(pendingIntent)
                    .setContentText("EjC vient d'ajouter de nouveaux événements.")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setAutoCancel(true);

            NotificationCompat.Builder mBuilderW = new NotificationCompat.Builder(mContext, "EjC35170")
                    .setSmallIcon(R.mipmap.ic_launcher_round)
                    .setContentTitle("Nouvelle étude")
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .setStyle(new NotificationCompat.BigTextStyle()
                            .bigText("Une étude vient d'être publié ! Tu es consultant à EjC et motivé ? Viens vite postuler !"))
                    .setContentText("Une étude vient d'être publié ! Tu es consultant à EjC et motivé ? Viens vite postuler !")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(mContext);

            if (!dateArticle1.equals("a") && !dateArticle2.equals("a") && !dateArticle1.equals(dateArticle2)) {
                notificationManager.notify(1, mBuilderA.build());
            }

            if (!dateEvent1.equals("a") && !dateEvent2.equals("a") && !dateEvent1.equals(dateEvent2)) {
                notificationManager.notify(2, mBuilderE.build());
            }

            if (!dateWanted1.equals("a") && !dateWanted2.equals("a") && !dateWanted1.equals(dateWanted2)) {
                notificationManager.notify(3, mBuilderW.build());
            }
        }

    }

}

