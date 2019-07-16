package com.ejc.appli;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.ejc.appli.test.R;
import com.ejc.appli.contact.PoleFragment;
import com.ejc.appli.tools.ViewPagerAdapter;

import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentContactTabbed#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentContactTabbed extends Fragment {


    private OnFragmentInteractionListener mListener;

    public FragmentContactTabbed() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment FragmentAboutUs.
     */
    public static FragmentContactTabbed newInstance() {
        FragmentContactTabbed fragment = new FragmentContactTabbed();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
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
        View vue = inflater.inflate(R.layout.content_contact, container, false);

        ViewPager viewPager = vue.findViewById(R.id.viewpager);
        setViewPager(viewPager);

        TabLayout tabLayout = vue.findViewById(R.id.mytabs);
        tabLayout.setupWithViewPager(viewPager);

        return vue;
    }

    private void setViewPager(ViewPager viewPager) {

        int[] valuesPrez = new int[6];
        valuesPrez[0] = R.string.presidence;
        valuesPrez[1] = R.string.descPresidence;
        valuesPrez[2] = R.string.PrezStat1;
        valuesPrez[3] = R.string.PrezStat1Label;
        valuesPrez[4] = R.string.PrezStat2;
        valuesPrez[5] = R.string.PrezStat2Label;

        int[] valuesCdP = new int[6];
        valuesCdP[0] = R.string.chefdeprojet;
        valuesCdP[1] = R.string.descCdP;
        valuesCdP[2] = R.string.CdPStat1;
        valuesCdP[3] = R.string.CdPStat1Label;
        valuesCdP[4] = R.string.CdPStat2;
        valuesCdP[5] = R.string.CdPStat2Label;

        int[] valuesDevCo = new int[6];
        valuesDevCo[0] = R.string.devco;
        valuesDevCo[1] = R.string.descDevco;
        valuesDevCo[2] = R.string.devcoStat1;
        valuesDevCo[3] = R.string.devcoStat1Label;
        valuesDevCo[4] = R.string.chiffreDevCoStat2;
        valuesDevCo[5] = R.string.descDevCoStat2;

        int[] valuesSecGen = new int[6];
        valuesSecGen[0] = R.string.secgen;
        valuesSecGen[1] = R.string.descSecGen;
        valuesSecGen[2] = R.string.StatSecGen;
        valuesSecGen[3] = R.string.secGenStat1Label;
        valuesSecGen[4] = R.string.stat2secgen;
        valuesSecGen[5] = R.string.stat2descSecGen;

        int[] valuesTreso = new int[6];
        valuesTreso[0] = R.string.treso;
        valuesTreso[1] = R.string.descTreso;
        valuesTreso[2] = R.string.devcoStat1;
        valuesTreso[3] = R.string.devcoStat1Label;
        valuesTreso[4] = R.string.tresoStat;
        valuesTreso[5] = R.string.tresoStatDesc;

        int[] valueCI = new int[6];
        valueCI[0] = R.string.CI;
        valueCI[1] = R.string.descCI;
        valueCI[2] = R.string.statCI;
        valueCI[3] = R.string.devcoStat1Label;
        valueCI[4] = R.string.nbreCIstat2;
        valueCI[5] = R.string.descCIstat2;

        int[] valueInfo = new int[6];
        valueInfo[0] = R.string.info;
        valueInfo[1] = R.string.descInfo;
        valueInfo[2] = R.string.statCI;
        valueInfo[3] = R.string.devcoStat1Label;
        valueInfo[4] = R.string.stat2info;
        valueInfo[5] = R.string.stat2descInfo;

        int[] valueComm = new int[6];
        valueComm[0] = R.string.comm;
        valueComm[1] = R.string.descComm;
        valueComm[2] = R.string.devcoStat1;
        valueComm[3] = R.string.devcoStat1Label;
        valueComm[4] = R.string.stat2com;
        valueComm[5] = R.string.stat2desccomm;

        ViewPagerAdapter adapter = new ViewPagerAdapter(Objects.requireNonNull(getActivity()).getSupportFragmentManager());
        adapter.addFragment(PoleFragment.newInstance("prez",valuesPrez), "Prez");
        adapter.addFragment(PoleFragment.newInstance("cdp",valuesCdP),"Chef de Projet");
        adapter.addFragment(PoleFragment.newInstance("devco",valuesDevCo), "DevCo");
        adapter.addFragment(PoleFragment.newInstance("secgen",valuesSecGen), "SecGen");
        adapter.addFragment(PoleFragment.newInstance("treso",valuesTreso), "Tréso");
        adapter.addFragment(PoleFragment.newInstance("ci",valueCI),"CI");
        adapter.addFragment(PoleFragment.newInstance("info",valueInfo), "Info");
        adapter.addFragment(PoleFragment.newInstance("comm",valueComm), "Comm'");
        viewPager.setAdapter(adapter);

    }

// --Commented out by Inspection START (12/23/18 1:15 AM):
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

