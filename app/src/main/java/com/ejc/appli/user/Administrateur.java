package com.ejc.appli.user;

import com.ejc.appli.test.R;

public class Administrateur extends User {

    public Administrateur(String nom,String prenom, String mail){
        super(nom,prenom,mail);
        this.compte=1;
        //menuAssocie.add(R.id.section_outils);
        //menuAssocie.add(R.id.nav_dashboard);
    }

}