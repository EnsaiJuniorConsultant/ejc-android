package com.ejc.appli.tools;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@SuppressLint("StaticFieldLeak")
public class UpdateBDDAsync extends AsyncTask<String, Integer, String> {

    private final Context mContext;

    public UpdateBDDAsync(Context context) {
        mContext = context;
    }

    protected String doInBackground(String... databases) {

        // Databases is an array of string
        // Each string is a name of a database in json format
        // Those json files are saved at https://ejc.fr/android/
        // and at https://tintinmar1995.github.io/ejc/android/
        // The first link is used in priority

        for (String database : databases) {

            // Create objects needed to perform http request
            OkHttpClient client = new OkHttpClient();
            Response response = null;
            Request request;
            URL url;

            // try to reach ejc.fr
            try {
                url = new URL("https://ejc.fr/android/" + database + ".html");
                request = new Request.Builder()
                        .url(url)
                        .build();
                response = client.newCall(request).execute();
                Log.i("import_databases", database + " imported from ejc.fr");
            } catch (IOException err) {
                // try to reach tintinmar1995.github.io
                try {
                    url = new URL("https://tintinmar1995.github.io/ejc/android/" + database + ".html");
                    request = new Request.Builder()
                            .url(url)
                            .build();
                    response = client.newCall(request).execute();
                    Log.i("import_databases", database + " imported from github.io");
                } catch (IOException err2) {
                    // If we could not reach either ejc nor github.io
                    // We print stacktrace... data could be loaded
                    err.printStackTrace();
                    err2.printStackTrace();
                }
            }


            // If we have been able to reach ejc.fr or tintinmar1995.github.io
            // we save databases in Android cache. Thanks to that data can be retrieved afterward
            if (!(response == null)) {
                try {
                    CacheThis.writeObject(mContext, database, Objects.requireNonNull(response.body()).string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        return "";
    }

}

