package com.ejc.appli;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ejc.appli.test.R;
import com.ejc.appli.article.Article;
import com.ejc.appli.article.ArticleAdapter;
import com.ejc.appli.event.EventAdapter;
import com.ejc.appli.tools.CustomNetwork;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentMain#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentMain extends Fragment {
    private OnFragmentInteractionListener mListener;

    public FragmentMain() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment FragmentMain.
     */
    public static FragmentMain newInstance() {
        FragmentMain fragment = new FragmentMain();
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

        View vue = inflater.inflate(R.layout.content_main, container, false);

        ArrayList<Article> arrayOfInterviews = new ArrayList<>();
        boolean trouve;
        int j;
        for (Article a : MainActivity.arrayOfArticles){
            trouve = false;
            j=0;
            while(!trouve & j < MainActivity.arrayOfInterview.size()){
                if(MainActivity.arrayOfInterview.get(j).equals(a.idArticle)){
                    trouve = true;
                    arrayOfInterviews.add(a);
                }else{
                    j++;
                }
            }
        }


        // Create the adapter to convert the array to views
        ArticleAdapter adapterArticle = new ArticleAdapter(getContext(), new ArrayList<>(MainActivity.arrayOfArticles.subList(0,Math.min(3,MainActivity.arrayOfArticles.size()))));
        // Attach the adapter to a ListView
        final ListView listViewArticle = vue.findViewById(R.id.listeViewArticle);
        listViewArticle.setAdapter(adapterArticle);

        listViewArticle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long id) {

                if(CustomNetwork.isNetworkAvailable(listViewArticle.getContext())) {
                    Article art = (Article) adapter.getItemAtPosition(position);

                    Intent openArticle = new Intent(listViewArticle.getContext(),WebviewActivity.class);
                    WebviewActivity.url = art.url;
                    Toast.makeText(listViewArticle.getContext(),"Chargement ...", Toast.LENGTH_SHORT).show();
                    startActivity(openArticle);

                }else{
                    Toast.makeText(listViewArticle.getContext(),"Connectez-vous à internet",Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Create the adapter to convert the array to views
        ArticleAdapter adapterInterviews = new ArticleAdapter(getContext(), new ArrayList<>(arrayOfInterviews.subList(0,Math.min(3,arrayOfInterviews.size()))));
        // Attach the adapter to a ListView
        final ListView listViewInterview = vue.findViewById(R.id.listeViewInterview);
        listViewInterview.setAdapter(adapterInterviews);

        listViewInterview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long id) {

                if(CustomNetwork.isNetworkAvailable(listViewInterview.getContext())) {
                    Article art = (Article) adapter.getItemAtPosition(position);

                    Intent openArticle = new Intent(listViewInterview.getContext(),WebviewActivity.class);
                    WebviewActivity.url = art.url;
                    Toast.makeText(listViewInterview.getContext(),"Chargement ...", Toast.LENGTH_SHORT).show();
                    startActivity(openArticle);

                }else{
                    Toast.makeText(listViewInterview.getContext(),"Connectez-vous à internet",Toast.LENGTH_SHORT).show();
                }
            }
        });



        // Create the adapter to convert the array to views
        EventAdapter adapterEvents = new EventAdapter(getContext(), new ArrayList<>(MainActivity.arrayOfEvents.subList(0,Math.min(5,MainActivity.arrayOfEvents.size()))));

        // Attach the adapter to a ListView
        final ListView listViewEvent = vue.findViewById(R.id.listeViewEvent);
        listViewEvent.setAdapter(adapterEvents);
        TextView titreEvent = vue.findViewById(R.id.titleEvent);



        /*listViewEvent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long id) {

            Toast.makeText(listViewEvent.getContext(),"")
            }
        });
        */
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

