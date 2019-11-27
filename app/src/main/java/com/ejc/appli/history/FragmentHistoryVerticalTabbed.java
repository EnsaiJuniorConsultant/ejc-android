package com.ejc.appli.history;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ejc.appli.OnFragmentInteractionListener;
import com.ejc.appli.test.R;
import com.ejc.appli.tools.CacheThis;
import com.ejc.appli.tools.ViewPagerAdapter;
import com.ejc.appli.tools.VerticalViewPager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
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
    public static FragmentHistoryVerticalTabbed newInstance() {
        return new FragmentHistoryVerticalTabbed();
    }

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

        // Load years from cache
        try {
            String importHistory =  CacheThis.readObject(viewPager.getContext(), "jhistory").toString();
            JSONArray mJasonArray = new JSONArray(importHistory);
            for (int i=0; i < mJasonArray.length(); i++) {
                JSONObject oneObject = mJasonArray.getJSONObject(i);
                JSONArray factsAsJSON = oneObject.getJSONArray("facts");
                StringBuilder facts = new StringBuilder();
                for(int j = 0; j < factsAsJSON.length(); j++){
                    facts.append("- ").append(factsAsJSON.getString(j)).append("\n");
                }
                adapter.addFragment(
                        An20XXFragment.newInstance(
                                oneObject.getInt("year"),
                                oneObject.getString("title"),
                                facts.toString(),
                                oneObject.getString("image")),
                        Integer.toString(oneObject.getInt("year")));
            }
        } catch (JSONException | IOException | ClassNotFoundException e) {
            Log.e("history_from_cache", e.toString());
            e.printStackTrace();
        }

        viewPager.setAdapter(adapter);
    }

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

