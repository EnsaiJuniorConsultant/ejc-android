package com.ejc.appli;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ejc.appli.test.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentDashboard#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentDashboard extends Fragment {
    private OnFragmentInteractionListener mListener;

    public FragmentDashboard() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment FragmentAboutUs.
     */
    public static FragmentDashboard newInstance() {
        return new FragmentDashboard();
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
        View vue = inflater.inflate(R.layout.content_dashboard, container, false);

        GraphView graph = vue.findViewById(R.id.graphCA);
        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[] {
                new DataPoint(1, 2000),
                new DataPoint(3, 8000),
                new DataPoint(5, 20000),
                new DataPoint(7, 32000)
        });
        graph.addSeries(series);
        // styling
        series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
            @Override
            public int get(DataPoint data) {
                return Color.rgb((int) data.getX()*255/4, (int) Math.abs(data.getY()*255/6), 100);
            }
        });
        series.setSpacing(50);
        // draw values on top
        series.setDrawValuesOnTop(true);
        series.setValuesOnTopColor(Color.RED);
        //series.setValuesOnTopSize(50);


        GraphView graphEtude = vue.findViewById(R.id.graphNbEtude);
        BarGraphSeries<DataPoint> seriesEtude = new BarGraphSeries<>(new DataPoint[] {
                new DataPoint(1, 20),
                new DataPoint(3, 5),
                new DataPoint(5, 10),
                new DataPoint(7, 7)
        });

        graphEtude.addSeries(seriesEtude);
        // styling
        seriesEtude.setValueDependentColor(new ValueDependentColor<DataPoint>() {
            @Override
            public int get(DataPoint data) {

                return Color.rgb((int) data.getX()*255/4, (int) Math.abs(data.getY()*255/6), 100);
            }
        });
        seriesEtude.setSpacing(50);
        // draw values on top
        seriesEtude.setDrawValuesOnTop(true);
        seriesEtude.setValuesOnTopColor(Color.RED);
        //series.setValuesOnTopSize(50);

        GraphView graphPP = vue.findViewById(R.id.graphNbPiouPious);
        LineGraphSeries<DataPoint> seriesPP = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(1, 50),
                new DataPoint(2, 45),
                new DataPoint(3, 30),
                new DataPoint(4, 29),
                new DataPoint(5, 25),
                new DataPoint(6, 25),
                new DataPoint(7, 24)
        });
        graphPP.addSeries(seriesPP);


        return vue;
    }

    /* TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }*/

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

