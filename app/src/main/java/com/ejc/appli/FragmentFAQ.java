package com.ejc.appli;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.ejc.appli.test.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentFAQ#newInstance} factory method to
 * create an instance of this fragment.
 */
@SuppressWarnings("ALL")
public class FragmentFAQ extends Fragment {

    private OnFragmentInteractionListener mListener;

    @SuppressWarnings("WeakerAccess")
    public FragmentFAQ() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment FragmentAboutUs.
     */
    public static FragmentFAQ newInstance() {
        return new FragmentFAQ();
    }

    /*
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    */

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View vue = inflater.inflate(R.layout.content_faq, container, false);

        final WebView myWebView = vue.findViewById(R.id.webviewFaq);
        myWebView.getSettings().setJavaScriptEnabled(true);

        myWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url)
            {
                // hide element by class name
                myWebView.loadUrl("javascript:(function() { " +
                        "document.getElementsByClassName('light sticky-nav navigation')[0].style.display='none'; })()");
                myWebView.loadUrl("javascript:(function() { " +
                        "document.getElementsByClassName('copyright')[0].style.display='none'; })()");
            }
        });

        String adresse = "https://www.ejc.fr/FAQ.php";
        myWebView.loadUrl(adresse);
        Toast.makeText(myWebView.getContext(),"Chargement ...", Toast.LENGTH_SHORT).show();

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

