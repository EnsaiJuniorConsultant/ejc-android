package com.ejc.appli.history;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ejc.appli.OnFragmentInteractionListener;
import com.ejc.appli.test.R;
import com.ejc.appli.tools.ViewPagerAdapter;
import com.ejc.appli.tools.VerticalViewPager;

import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentHistoryVerticalTabbed#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentHistoryVerticalTabbed extends Fragment {

    private OnFragmentInteractionListener mListener;

    public FragmentHistoryVerticalTabbed() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment FragmentAboutUs.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentHistoryVerticalTabbed newInstance() {
        return new FragmentHistoryVerticalTabbed();
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
        View vue = inflater.inflate(R.layout.content_history, container, false);

        VerticalViewPager viewPager = vue.findViewById(R.id.viewpagerHistory);
        setViewPager(viewPager);

        TabLayout tabLayout = vue.findViewById(R.id.tabsHistory);
        tabLayout.setupWithViewPager(viewPager);

        return vue;
    }

    private void setViewPager(VerticalViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(Objects.requireNonNull(getActivity()).getSupportFragmentManager());
        adapter.addFragment(An20XXFragment.newInstance(1996,"Création d'EjC","",R.mipmap.ecusson_ejc1), "1996");
        /*adapter.addFragment(An20XXFragment.newInstance(1997, "",""),"1997");*/
        adapter.addFragment(An20XXFragment.newInstance(1998, "EjC devient une\nJunior-Entreprise","",R.mipmap.ecusson_ejc1),"1998");
        /*adapter.addFragment(An20XXFragment.newInstance(1999, "",""),"1999");*/
        adapter.addFragment(An20XXFragment.newInstance(2000, "EjC dans le Top 10\ndes meilleurs JE","",R.mipmap.ecusson_ejc1),"2000");
        /*adapter.addFragment(An20XXFragment.newInstance(2001, "",""),"2001");
        adapter.addFragment(An20XXFragment.newInstance(2002, "",""),"2002");
        adapter.addFragment(An20XXFragment.newInstance(2003, "",""),"2003");*/
        adapter.addFragment(An20XXFragment.newInstance(2004, "Organisation du CRAO","- Nouveau logo",R.mipmap.logo_ejc_2),"2004");
        /*adapter.addFragment(An20XXFragment.newInstance(2005, "",""),"2005");
        adapter.addFragment(An20XXFragment.newInstance(2006, "",""),"2006");
        adapter.addFragment(An20XXFragment.newInstance(2007, "",""),"2007");
        adapter.addFragment(An20XXFragment.newInstance(2008, "",""),"2008");
        adapter.addFragment(An20XXFragment.newInstance(2009, "",""),"2009");
        adapter.addFragment(An20XXFragment.newInstance(2010, "",""),"2010");
        adapter.addFragment(An20XXFragment.newInstance(2011, "",""),"2011");
        adapter.addFragment(An20XXFragment.newInstance(2012, "",""),"2012");
        adapter.addFragment(An20XXFragment.newInstance(2013, "",""),"2013");*/
        adapter.addFragment(An20XXFragment.newInstance(2014,"","- Réalisation du palmarès des écoles de commerce pour l'Etudiant",R.mipmap.logo_etudiant),"2014");
        adapter.addFragment(An20XXFragment.newInstance(2015,"EjC dans la L30","- Nouveau logo\n- Création des JER\n- Réalisation du palmarès des écoles de commerce pour l'Etudiant"), "2015");
        adapter.addFragment(An20XXFragment.newInstance(2016,"Organisation du CRAO","- Premier auditeur conseil\n- Première présence d'un stand EjC au salon Entreprendre dans l'Ouest\n- Nouveau site internet\n- Un article dans Ouest France\n- Réalisation du palmarès des écoles de commerce pour l'Etudiant",R.mipmap.logo20ans), "2016");
        adapter.addFragment(An20XXFragment.newInstance(2017,"Partenariat avec Alumneye","- Première conférence JE\n- Lancement du blog\n- Apparition des Wanted publics\n- Présence d'un stand au salon Entreprendre dans l'Ouest\n- Première journée identité d'EjC avec EjC Alumni\n- Réalisation du palmarès des écoles de commerce pour l'Etudiant"), "2017");
        adapter.addFragment(An20XXFragment.newInstance(2018,"Partenariat avec EY","- Première auditrice conseil\n- Organisation de deux Data Thursday\n- Présence d'un stand au salon Entreprendre dans l'Ouest\n- Création de l'application Android\n- Mention dans le Monde Diplomatique",R.mipmap.ey_logo), "2018");
        adapter.addFragment(An20XXFragment.newInstance(2019,"A suivre",""), "2019");
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

