package com.ejc.appli;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.ejc.appli.test.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentConsultant#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentConsultant extends Fragment {

    private OnFragmentInteractionListener mListener;
    private static int numImg = 1;

    public FragmentConsultant() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment FragmentAboutUs.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentConsultant newInstance() {
        return new FragmentConsultant();
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
        View vue = inflater.inflate(R.layout.content_consultant, container, false);

        ImageButton mImageButton = vue.findViewById(R.id.ConsultantVideo);

        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:SiMNtOz1ryM"));
                v.getContext().startActivity(appIntent);
            }
        });

        final int[] img = new int[6];
        img[0]=R.mipmap.comp1;
        img[1]=R.mipmap.comp2;
        img[2]=R.mipmap.comp3;
        img[3]=R.mipmap.comp4;
        img[4]=R.mipmap.comp5;
        img[5]=R.mipmap.comp6;

        ImageButton b1 = vue.findViewById(R.id.prev);
        ImageButton b2 = vue.findViewById(R.id.next);

        final ImageView mImageView = vue.findViewById(R.id.ConsFil3A);
        mImageView.setImageResource(R.mipmap.comp1);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FragmentConsultant.numImg == 0){
                    FragmentConsultant.numImg = 5;
                }else {
                    FragmentConsultant.numImg = (FragmentConsultant.numImg - 1) % 6;
                }
                mImageView.setImageResource(img[FragmentConsultant.numImg]);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FragmentConsultant.numImg == 5){
                    FragmentConsultant.numImg = 0;
                }else {
                    FragmentConsultant.numImg = (FragmentConsultant.numImg + 1) % 6;
                }
                mImageView.setImageResource(img[FragmentConsultant.numImg]);
            }
        });

        return vue;
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

