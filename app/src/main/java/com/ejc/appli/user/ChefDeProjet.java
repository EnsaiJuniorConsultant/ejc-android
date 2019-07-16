package com.ejc.appli.user;

import com.ejc.appli.test.R;

public class ChefDeProjet extends User {

    public ChefDeProjet(String nom, String prenom, String mail){
        super(nom,prenom,mail);
        this.compte=3;
        menuAssocie.add(R.id.section_outils);
    }

}