package com.ejc.appli;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.ejc.appli.wanted.WantedAdapter;
import com.ejc.appli.test.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentTousWanted#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentTousWanted extends Fragment {


    private OnFragmentInteractionListener mListener;

    public FragmentTousWanted() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment FragmentTousWanted.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentTousWanted newInstance() {
        FragmentTousWanted fragment = new FragmentTousWanted();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    /*@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }*/

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vue = inflater.inflate(R.layout.content_allwanted, container, false);
        final ListView listView = vue.findViewById(R.id.listeViewWanted);
        final ImageView zero = vue.findViewById(R.id.nothinW);


        if(MainActivity.arrayOfWanteds.size() >0) {
            // Create the adapter to convert the array to views
            WantedAdapter adapter = new WantedAdapter(getContext(), MainActivity.arrayOfWanteds);
            // Attach the adapter to a ListView
            listView.setVisibility(View.VISIBLE);
            zero.setVisibility(View.GONE);
            listView.setAdapter(adapter);
        }else{
            listView.setVisibility(View.GONE);
            zero.setVisibility(View.VISIBLE);
        }

        return vue;
        }


    /*@Override
    public void onResume(){
        super.onResume();
    }*/

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

