package com.ejc.appli.contact;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ejc.appli.test.R;

import java.util.ArrayList;
import java.util.Objects;

class MailAdapter  extends ArrayAdapter<MailContact> {
        public MailAdapter(Context context, ArrayList<MailContact> users) {
            super(context, 0, users);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, @NonNull ViewGroup parent) {
            // Get the data item for this position
            MailContact user = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.content_contact_listviewmail, parent, false);
            }
            // Lookup view for data population
            TextView tvName = convertView.findViewById(R.id.tvMailAdresse);
            TextView tvHome = convertView.findViewById(R.id.tvMailTypeAdresse);
            // Populate the data into the template view using the data object
            tvName.setText(Objects.requireNonNull(user).adresse);
            tvHome.setText(user.typeAdresse);
            // Return the completed view to render on screen
            return convertView;
        }
}


