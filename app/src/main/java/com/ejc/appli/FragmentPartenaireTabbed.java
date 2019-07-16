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
import com.ejc.appli.partenaire.PartenaireFragment;
import com.ejc.appli.tools.ViewPagerAdapter;

import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentPartenaireTabbed#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentPartenaireTabbed extends Fragment {

    private OnFragmentInteractionListener mListener;

    public FragmentPartenaireTabbed() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment FragmentAboutUs.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentPartenaireTabbed newInstance() {
        return new FragmentPartenaireTabbed();
    }

    /*
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }*/

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View vue = inflater.inflate(R.layout.content_partenaire, container, false);

        ViewPager viewPager = vue.findViewById(R.id.viewpagerPartenaire);
        setViewPager(viewPager);

        TabLayout tabLayout = vue.findViewById(R.id.tabsPartenaire);
        tabLayout.setupWithViewPager(viewPager);

        return vue;
    }

    private void setViewPager(ViewPager viewPager) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(Objects.requireNonNull(getActivity()).getSupportFragmentManager());
        adapter.addFragment(PartenaireFragment.PartenaireFragmentFille("Ensai"), "Ensai");
        adapter.addFragment(PartenaireFragment.PartenaireFragmentFille("BNP Paribas"),"BNP Paribas");
        adapter.addFragment(PartenaireFragment.PartenaireFragmentFille("Breizh Data Club"), "Breizh Data Club");
        adapter.addFragment(PartenaireFragment.PartenaireFragmentFille("EJR"), "EJR");
        adapter.addFragment(PartenaireFragment.PartenaireFragmentFille("Forum"), "Forum");
        adapter.addFragment(PartenaireFragment.PartenaireFragmentFille("EY"), "EY");
        adapter.addFragment(PartenaireFragment.PartenaireFragmentFille("Engie"), "Engie");
        adapter.addFragment(PartenaireFragment.PartenaireFragmentFille("Alten"), "Alten");
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

