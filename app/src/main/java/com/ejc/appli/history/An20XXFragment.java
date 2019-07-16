package com.ejc.appli.history;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ejc.appli.test.R;

public class An20XXFragment extends Fragment {

    private String titre = "Nouveau logo";
    private String listeAction = "";
    private int img = -1 ;

    public An20XXFragment(){}


    public static An20XXFragment newInstance(int annee,String titre,String listeAction){
        An20XXFragment fragment = new An20XXFragment();
        Bundle args = new Bundle();
        args.putInt("annee", annee);
        args.putString("titre", titre);
        args.putString("listeAction", listeAction);
        fragment.setArguments(args);
        return fragment;
    }

    public static An20XXFragment newInstance(int annee,String titre,String listeAction,int img){
        An20XXFragment fragment = new An20XXFragment();
        Bundle args = new Bundle();
        args.putInt("annee", annee);
        args.putString("titre", titre);
        args.putString("listeAction", listeAction);
        args.putInt("img", img);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.titre = getArguments().getString("titre");
            this.listeAction = getArguments().getString("listeAction","");
            this.img = getArguments().getInt("img",-1);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vue = inflater.inflate(R.layout.content_history_an20xx, container, false);
        TextView titrePage = vue.findViewById(R.id.historyTitre);
        titrePage.setText(titre);

        TextView mListeAction = vue.findViewById(R.id.listActions);
        TextView mMaisAussi = vue.findViewById(R.id.butalso);
        ImageView mImageView = vue.findViewById(R.id.historyImage);

        if(img != -1){
            mImageView.setImageResource(img);
        }

        if(listeAction.equals("")){
            mMaisAussi.setVisibility(View.INVISIBLE);
            mListeAction.setVisibility(View.INVISIBLE);
        }else {
            mMaisAussi.setVisibility(View.VISIBLE);
            mListeAction.setVisibility(View.VISIBLE);
            mListeAction.setText(listeAction);
        }
        return vue;
    }
}