package com.ejc.appli.wanted;

import com.ejc.appli.user.User;

import java.util.List;

public class Wanted {

    private final List<String> phase;
    private final boolean[] annees;
    private final int nbreConsultant;
    private final List<String> competence;
    private final List<String> echeancier;
    private final int idEtude;
    private final User chefDeProjet ;
    private final User assCdP;

    public Wanted(int idEtude, List<String> phase, boolean[] annees, int nbreConsultant, List<String> competence, List<String> echeancier, User cdp, User acdp){
        this.phase=phase;
        this.annees=annees;
        this.assCdP=acdp;
        this.chefDeProjet=cdp;
        this.nbreConsultant=nbreConsultant;
        this.idEtude=idEtude;
        this.competence=competence;
        this.echeancier=echeancier;
    }


    public List<String> getPhase() {
        return phase;
    }

    public boolean[] getAnnees() {
        return annees;
    }

    public int getNbreConsultant() {
        return nbreConsultant;
    }

    public List<String> getCompetence() {
        return competence;
    }

    public List<String> getEcheancier() {
        return echeancier;
    }

    public int getIdEtude() {
        return idEtude;
    }

// --Commented out by Inspection START (12/23/18 1:15 AM):
//    public User getChefDeProjet() {
//        return chefDeProjet;
//    }
// --Commented out by Inspection STOP (12/23/18 1:15 AM)

    public User getCdP(){
        return chefDeProjet;
    }

    public User getAssCdP(){
        return assCdP;
    }
}