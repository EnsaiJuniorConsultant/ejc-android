package com.ejc.appli.jer;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ejc.appli.tools.ViewPagerAdapter;
import com.ejc.appli.OnFragmentInteractionListener;
import com.ejc.appli.test.R;

import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentJERVerticalTabbed#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentJERVerticalTabbed extends Fragment {

    private OnFragmentInteractionListener mListener;

    public FragmentJERVerticalTabbed() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment FragmentAboutUs.
     */
    public static FragmentJERVerticalTabbed newInstance() {
        return new FragmentJERVerticalTabbed();
    }

    /*
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    */

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View vue = inflater.inflate(R.layout.content_jer, container, false);

        ViewPager viewPager = vue.findViewById(R.id.viewpagerJER);
        setViewPager(viewPager);

        TabLayout tabLayout = vue.findViewById(R.id.tabsJER);
        tabLayout.setupWithViewPager(viewPager);

        return vue;
    }

    private void setViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(Objects.requireNonNull(getActivity()).getSupportFragmentManager());
        adapter.addFragment(FragmentJER.newInstance(),"Les JER");

        adapter.addFragment(JERFragmentAdapter.newInstance("Ouest INSA","Ouest INSA est la Junior-Entreprise de l’INSA Rennes, grande école post-bac. Cette école, plus grande école d’ingénieur de Bretagne est un membre fondateur du groupe INSA, reconnu pour ses compétences dans les sciences & technologies de l'information et de la communication et les matériaux, structures et mécanique.",R.mipmap.ouestinsa,"Informatique\n" +
                "Systèmes et réseaux de communication\n" +
                "Electronique et systèmes embarqués\n" +
                "Micro-optoélectronique\n" +
                "Mécanique et automatique\n" +
                "Génie civil\n" +
                "Mathématiques\n" +
                "Traductions techniques\n",
                1983,
                -1),"Ouest INSA");
        adapter.addFragment(JERFragmentAdapter.newInstance("EPINE","EPINE est la Junior-Entreprise d’Ecole Supérieur d'ingénieur de Rennes spécialisée dans les technologies de l’information et les matériaux.",R.mipmap.epine,"Informatique\n" +
                "Réseau et télécommunication\n" +
                "Imagerie numérique\n" +
                "Matériaux\n" +
                "Domotique\n" +
                "Ingénierie pour la santé\n",2014,2018),"Epine");
        adapter.addFragment(JERFragmentAdapter.newInstance("Science Po Rennes","Science Po Rennes Junior Conseil est la Junior-Entreprise de l’institut d’étude politique de Rennes, école pluridisciplinaire formant des étudiants aussi bien en histoire, qu’en science politique, en droit ou en économie. L’IEP Rennes est lié aux IEP de Aix, Lille, Lyon, Saint-Germain-en-Laye, Strasbourg et Toulouse avec lesquels elle partage, entre autre, un concours commun.",R.mipmap.spjc,"Affaires publiques et européennes \n" +
                "marketing et stratégie\n" +
                "médias et communication\n" +
                "ressources humaines, responsabilité sociale des entreprises et développement durable\n",2008,2013),"Science Po Rennes Junior Conseil");
        adapter.addFragment(JERFragmentAdapter.newInstance("Bretagne Conseil","Bretagne Conseil est la Junior-Entreprise de l’ESC Rennes School of Business. Bretagne Conseil propose des offres sur mesure dans les secteurs du marketing, de la communication, de la finance et de la stratégie d’entreprise. Anciennement appelé Entrep'Rennes, Bretagne Conseil prit son nom actuel en 2005.",R.mipmap.bretagnecons,"Marketing\nStratégie\nCommunication\nÉtude notoriété\n",1991,-1),"Bretagne Conseil");


        viewPager.setAdapter(adapter);
    }

// --Commented out by Inspection START (12/23/18 1:15 AM):
//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }
// --Commented out by Inspection STOP (12/23/18 1:15 AM)

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}

