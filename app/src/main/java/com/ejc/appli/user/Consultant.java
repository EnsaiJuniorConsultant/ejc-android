package com.ejc.appli.user;

import com.ejc.appli.test.R;

public class Consultant extends User {

    public Consultant(String nom,String prenom, String mail){
        super(nom,prenom,mail);
        this.compte=0;
        //menuAssocie.add(R.id.section_outils);
    }

}
