package com.ejc.appli.event;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ejc.appli.test.R;

import java.util.ArrayList;
import java.util.Calendar;

public class EventAdapter extends ArrayAdapter<Event> {

    public EventAdapter(Context context, ArrayList<Event> events) {
        super(context, 0, events);
    }

    @NonNull
    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        // Get the data item for this position
        Event mEvent = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_event, parent, false);
        }
        TextView jour = convertView.findViewById(R.id.day);
        TextView mois = convertView.findViewById(R.id.month);
        TextView title = convertView.findViewById(R.id.title);
        TextView ou = convertView.findViewById(R.id.location);
        TextView by = convertView.findViewById(R.id.by);
        TextView com = convertView.findViewById(R.id.commentaire);

        String[] tabmois = new String[]{"Janv.","Fevr.","Mars","Avril","Mai","Juin","Juil.","Aout","Sept.","Oct.","Nov.","Dec."};

        assert mEvent != null;
        jour.setText(Integer.toString(mEvent.date.get(Calendar.DATE)));
        mois.setText(tabmois[mEvent.date.get(Calendar.MONTH)]);


        title.setText(mEvent.label);
        String strby = "Organisé par ";
        by.setText(strby + mEvent.organisateur);
        com.setText(mEvent.commentaire);
        String at = "@ ";
        ou.setText(at+mEvent.localisation);

        // Return the completed view to render on screen
        return convertView;
    }

}