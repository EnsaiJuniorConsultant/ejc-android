package com.ejc.appli.event;


import java.util.Calendar;

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
}
