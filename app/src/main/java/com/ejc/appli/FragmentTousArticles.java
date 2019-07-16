package com.ejc.appli;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.ejc.appli.test.R;
import com.ejc.appli.article.Article;
import com.ejc.appli.article.ArticleAdapter;
import com.ejc.appli.tools.CustomNetwork;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentTousArticles#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentTousArticles extends Fragment {


    private OnFragmentInteractionListener mListener;

    public FragmentTousArticles() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment FragmentMain.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentTousArticles newInstance() {
        FragmentTousArticles fragment = new FragmentTousArticles();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    /*
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }*/

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vue = inflater.inflate(R.layout.content_allarticles, container, false);



        // Create the adapter to convert the array to views
        // Attach the adapter to a ListView
        final ListView listView = vue.findViewById(R.id.listeViewArticle);
        final ImageView zero = vue.findViewById(R.id.nothinA);


        if(MainActivity.arrayOfArticles.size() >0) {
            ArticleAdapter adapter = new ArticleAdapter(getContext(), MainActivity.arrayOfArticles);
            listView.setAdapter(adapter);
            listView.setVisibility(View.VISIBLE);
            zero.setVisibility(View.GONE);
            listView.setAdapter(adapter);
        }else{
            listView.setVisibility(View.GONE);
            zero.setVisibility(View.VISIBLE);
        }


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long id) {

                if(CustomNetwork.isNetworkAvailable(listView.getContext())) {
                    Article art = (Article) adapter.getItemAtPosition(position);

                    Intent openArticle = new Intent(listView.getContext(),WebviewActivity.class);
                    WebviewActivity.url = art.url;
                    Toast.makeText(listView.getContext(),"Chargement ...", Toast.LENGTH_SHORT).show();
                    startActivity(openArticle);

                }else{
                    Toast.makeText(listView.getContext(),"Connectez-vous à internet",Toast.LENGTH_SHORT).show();
                }
            }
        });

        return vue;
    }

    /*
    @Override
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

