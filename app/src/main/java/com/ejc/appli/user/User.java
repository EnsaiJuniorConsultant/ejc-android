package com.ejc.appli.user;

import java.util.ArrayList;
import java.util.List;

public abstract class User {

    private final String nom;
    private final String prenom;
    int compte;
    private final String mail;
    final List<Integer> menuAssocie = new ArrayList<>();


    User(String nom, String prenom, String mail) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
    }

// --Commented out by Inspection START (12/23/18 1:15 AM):
//    User(int compte, String mail, List<Integer> menu, String nom, String prenom) {
//        this.nom = nom;
//        this.prenom = prenom;
//        this.mail = mail;
//        this.menuAssocie=menu;
//        this.compte=compte;
//    }
// --Commented out by Inspection STOP (12/23/18 1:15 AM)

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public int getCompte() {
        return compte;
    }

    public String getMail() {
        return mail;
    }

    public List<Integer> getMenuAssocie() {
        return menuAssocie;
    }


}
