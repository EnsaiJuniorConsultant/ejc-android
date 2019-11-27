package com.ejc.appli.event;


import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.TimeZone;

public class Event {
    public final Calendar date = Calendar.getInstance();
    public final String label;
    public final String organisateur;
    public final String commentaire;
    public final String localisation;


    public Event (int jour, int mois, int annee, String label, String organisateur, String commentaire, String localisation){
        date.set(annee,mois-1,jour);
        this.label = label;
        this.organisateur = organisateur;
        this.commentaire = commentaire;
        this.localisation = localisation;
    }

    public static ArrayList<Event> ParseJSONArray(JSONArray mJSONArray){

        ArrayList<Event> arrayOfEvents = new ArrayList<>();

        try {
            for (int i=0; i < mJSONArray.length(); i++) {
                JSONObject oneObject = mJSONArray.getJSONObject(i);
                Event intentEvent = new Event(
                        oneObject.getInt("jour"),
                        oneObject.getInt("mois"),
                        oneObject.getInt("annee"),
                        oneObject.getString("label"),
                        oneObject.getString("organisateur"),
                        oneObject.getString("commentaire"),
                        oneObject.getString("localisation"));
                Log.i("bdd",intentEvent.date.toString());
                if (intentEvent.date.compareTo(Calendar.getInstance(TimeZone.getDefault())) > 0){
                    arrayOfEvents.add(intentEvent);
                }
            }

            Collections.sort(arrayOfEvents, new Comparator<Event>() {
                @Override
                public int compare(Event ev2, Event ev1)
                {
                    return -ev1.date.compareTo(ev2.date);
                }
            });

        } catch (JSONException e) {
            Log.e("events_from_cache", e.toString());
            e.printStackTrace();
        }

        return arrayOfEvents;
    }

}
