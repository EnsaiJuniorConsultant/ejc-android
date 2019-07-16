package com.ejc.appli.jer;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ejc.appli.test.R;

public class JERFragmentAdapter extends Fragment {

    private String nom;
    private String description;
    private int logo;
    private int crea;
    private int marque;
    private String domComp;

    public JERFragmentAdapter(){}


    public static JERFragmentAdapter newInstance(String nom, String description, int logo,String domComp,int crea,int marque){
        JERFragmentAdapter fragment = new JERFragmentAdapter();
        Bundle args = new Bundle();
        args.putInt("logo", logo);
        args.putInt("marque", marque);
        args.putInt("crea", crea);
        args.putString("nom",nom);
        args.putString("description", description);
        args.putString("domComp", domComp);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.description= getArguments().getString("description");
            this.logo = getArguments().getInt("logo",-1);
            this.nom = getArguments().getString("nom","");
            this.domComp= getArguments().getString("domComp","");
            this.crea= getArguments().getInt("crea",-1);
            this.marque= getArguments().getInt("marque",-1);
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vue = inflater.inflate(R.layout.content_jer_each, container, false);

        TextView mNom = vue.findViewById(R.id.nomJer);
        TextView mDesc = vue.findViewById(R.id.descJer);
        ImageView mLogo = vue.findViewById(R.id.logoJers);
        TextView mComp = vue.findViewById(R.id.compJer);
        TextView mDateCrea = vue.findViewById(R.id.JERdateCrea);
        TextView mDateMarque = vue.findViewById(R.id.JERdatePM);

        mNom.setText(nom);
        mDesc.setText(description);
        mComp.setText(domComp);
        mLogo.setImageResource(logo);
        mDateCrea.setText("Date de création : "+crea);

        if(marque != -1){
            mDateMarque.setText("Date de passage de marque : "+marque);
        }else{
            mDateMarque.setText("");
        }

        return vue;
    }
}