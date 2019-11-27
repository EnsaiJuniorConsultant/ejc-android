package com.ejc.appli.history;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ejc.appli.test.R;
import com.ejc.appli.tools.CustomNetwork;
import com.squareup.picasso.Picasso;

public class An20XXFragment extends Fragment {

    private String titre = "Nouveau logo";
    private String listeAction = "";
    private String img = "" ;

    public An20XXFragment(){}

    public static An20XXFragment newInstance(int annee,String titre,String listeAction,String img){
        An20XXFragment fragment = new An20XXFragment();
        Bundle args = new Bundle();
        args.putInt("annee", annee);
        args.putString("titre", titre);
        args.putString("listeAction", listeAction);
        args.putString("img", img);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.titre = getArguments().getString("titre");
            this.listeAction = getArguments().getString("listeAction","");
            this.img = getArguments().getString("img","");
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

        if(!img.equals("") && CustomNetwork.isNetworkAvailable(mImageView.getContext())) {
            Picasso.get()
                    .load("https://tintinmar1995.github.io/ejc/android/picture/" + img)
                    .placeholder(R.mipmap.logo_long)
                    .into(mImageView);
        }else{
            mImageView.setImageResource(R.mipmap.logo_long);
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