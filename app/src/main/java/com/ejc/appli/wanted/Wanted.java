package com.ejc.appli.wanted;

import android.content.Context;
import android.util.Log;

import com.ejc.appli.tools.CacheThis;
import com.ejc.appli.user.Administrateur;
import com.ejc.appli.user.ChefDeProjet;
import com.ejc.appli.user.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

    private static Wanted ParseJSON(JSONObject oneObject) {
            try {
                List<String> comp1 = new ArrayList<>();
                List<String> phase1 = new ArrayList<>();
                List<String> ech1 = new ArrayList<>();

                for (int a = 0; a < oneObject.getJSONArray("competence").length(); a++) {
                    comp1.add(oneObject.getJSONArray("competence").getString(a));
                }
                for (int b = 0; b < oneObject.getJSONArray("phase").length(); b++) {
                    phase1.add(oneObject.getJSONArray("phase").getString(b));
                }
                for (int c = 0; c < oneObject.getJSONArray("echeancier").length(); c++) {
                    ech1.add(oneObject.getJSONArray("echeancier").getString(c));
                }

                JSONObject jCdP = oneObject.getJSONObject("chefDeProjet");
                JSONObject jAssCdP = oneObject.getJSONObject("assCdP");

                List<User> cdp = new ArrayList<>();

                if (jCdP.getInt("compte") == 1) {
                    cdp.add(new Administrateur(jCdP.getString("nom"), jCdP.getString("prenom"), jCdP.getString("mail")));
                } else if (jCdP.getInt("compte") == 3) {
                    cdp.add(new ChefDeProjet(jCdP.getString("nom"), jCdP.getString("prenom"), jCdP.getString("mail")));
                }

                if (jAssCdP.getInt("compte") == 1) {
                    cdp.add(new Administrateur(jAssCdP.getString("nom"), jAssCdP.getString("prenom"), jAssCdP.getString("mail")));
                } else if (jAssCdP.getInt("compte") == 3) {
                    cdp.add(new ChefDeProjet(jAssCdP.getString("nom"), jAssCdP.getString("prenom"), jAssCdP.getString("mail")));
                }

                return new Wanted(oneObject.getInt("idEtude"),
                        phase1,
                        new boolean[]{oneObject.getJSONArray("annees").getBoolean(0), oneObject.getJSONArray("annees").getBoolean(1), oneObject.getJSONArray("annees").getBoolean(2)},
                        oneObject.getInt("nbreConsultant"),
                        comp1,
                        ech1,
                        cdp.get(0),
                        cdp.get(1));

            } catch (JSONException e) {
                return null;
            }
    }

    public static ArrayList<Wanted> ParseJSONArray(JSONArray mJasonArray) {

        ArrayList<Wanted> arrayOfWanteds = new ArrayList<>();
        try {
            for (int i=0; i < mJasonArray.length(); i++) {
                JSONObject oneObject = mJasonArray.getJSONObject(i);
                arrayOfWanteds.add(Wanted.ParseJSON(oneObject));
            }

            Collections.sort(arrayOfWanteds, new Comparator<Wanted>() {
                @Override
                public int compare(Wanted w2, Wanted w1)
                {
                    return w2.getIdEtude()-w1.getIdEtude();
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return  arrayOfWanteds;
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