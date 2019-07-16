package com.ejc.appli.wanted;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ejc.appli.MainActivity;
import com.ejc.appli.test.R;

import java.util.ArrayList;

public class WantedAdapter  extends ArrayAdapter<Wanted> {

    public WantedAdapter(Context context, ArrayList<Wanted> Wanteds) {
        super(context, 0, Wanteds);
    }

    @NonNull
    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        // Get the data item for this position
        final Wanted mWanted = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_wanted, parent, false);
        }
        // Lookup view for data population
        TextView titre = convertView.findViewById(R.id.titreWanted);
        TextView auteur = convertView.findViewById(R.id.auteurWanted);
        TextView intro = convertView.findViewById(R.id.wantedIntro);
        String intro2 = "Nous venons de décrocher une nouvelle étude et nous recherchons ";
        RelativeLayout bouton = convertView.findViewById(R.id.wantedBouton);
        StringBuilder phaseString = new StringBuilder();
        StringBuilder compString = new StringBuilder();
        StringBuilder echString = new StringBuilder();
        TextView phase = convertView.findViewById(R.id.wantedContContent);
        TextView comp = convertView.findViewById(R.id.wantedCompContent);
        TextView ech = convertView.findViewById(R.id.wantedEchaContent);


        assert mWanted != null;
        if(mWanted.getNbreConsultant() == 1){
            intro2+="1 "+"consultant.e"+" (";
        }else{
            intro2+=mWanted.getNbreConsultant()+" consultant.e.s."+" (";
        }
        if(mWanted.getAnnees()[0]){
            intro2+="1A";
        }
        if(mWanted.getAnnees()[1]){
            if(mWanted.getAnnees()[0]){
                    intro2+="/";
            }
            intro2+="2A";
        }
        if(mWanted.getAnnees()[2]){
            if(mWanted.getAnnees()[0] || mWanted.getAnnees()[1]){
                intro2+="/";
            }
            intro2+="3A";
        }

        intro2+=").";

        for(int i =0;i<mWanted.getPhase().size();i++){
            phaseString.append(mWanted.getPhase().get(i));
            if(i != mWanted.getPhase().size()-1){
                phaseString.append("\n");
            }
        }

        for(int i =0;i<mWanted.getCompetence().size();i++){
            compString.append(mWanted.getCompetence().get(i));
            if(i != mWanted.getCompetence().size()-1){
                compString.append("\n");
            }
        }

        for(int i =0;i<mWanted.getEcheancier().size();i++){
            echString.append(mWanted.getEcheancier().get(i));
            if(i != mWanted.getEcheancier().size()-1){
                echString.append("\n");
            }
        }



        // Populate the data into the template view using the data object
        titre.setText("Etude n°"+mWanted.getIdEtude());
        auteur.setText("Chefs de projet : "+mWanted.getCdP().getPrenom()+" "+mWanted.getCdP().getNom()+" & "+mWanted.getAssCdP().getPrenom()+" "+mWanted.getAssCdP().getNom());
        intro.setText(intro2);
        phase.setText(phaseString.toString());
        comp.setText(compString.toString());
        ech.setText(echString.toString());

                bouton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (MainActivity.mUser == null) {
                    Toast.makeText(v.getContext(), "Vous devez vous connecter pour postuler", Toast.LENGTH_SHORT).show();
                } else if (MainActivity.mUser != null & (MainActivity.mUser.getCompte() == 0 | MainActivity.mUser.getCompte() == 1)) {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/html");
                    intent.putExtra(Intent.EXTRA_EMAIL, new String[]{mWanted.getCdP().getMail(), mWanted.getAssCdP().getMail()});
                    intent.putExtra(Intent.EXTRA_SUBJECT, "[Wanted] Etude n°" + mWanted.getIdEtude());
                    intent.putExtra(Intent.EXTRA_TEXT, "Décrivez vos motivations");
                    getContext().startActivity(Intent.createChooser(intent, "Envoyer un mail"));
                } else if (MainActivity.mUser != null & MainActivity.mUser.getCompte() == 2){
                    Toast.makeText(v.getContext(), "Vous êtes trop vieux pour faire une étude :'(", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(v.getContext(), "Erreur", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Return the completed view to render on screen
        return convertView;
    }

}