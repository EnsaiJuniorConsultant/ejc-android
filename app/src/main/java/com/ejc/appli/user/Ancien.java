package com.ejc.appli.user;

import com.ejc.appli.test.R;

public class Ancien extends User {

    public Ancien(String nom,String prenom, String mail){
        super(nom,prenom,mail);
        this.compte=2;
        menuAssocie.add(R.id.section_outils);
        menuAssocie.add(R.id.nav_dashboard);
    }

}
