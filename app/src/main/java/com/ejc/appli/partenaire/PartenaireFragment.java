package com.ejc.appli.partenaire;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ejc.appli.test.R;

@SuppressWarnings("unused")
public class PartenaireFragment extends Fragment {


   private String nom;

    public PartenaireFragment(){}

    public static PartenaireFragment PartenaireFragmentFille(String nom){
        PartenaireFragment frag = new PartenaireFragment();
        frag.nom = nom;
        return frag;
    }


    /*
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }*/

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.content_partenaire_partenaire, container, false);
    }
}