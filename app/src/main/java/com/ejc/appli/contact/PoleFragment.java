package com.ejc.appli.contact;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.ejc.appli.test.R;
import com.ejc.appli.tools.CacheThis;
import com.ejc.appli.tools.CustomDisplay;
import com.ejc.appli.wanted.Wanted;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PoleFragment extends Fragment {

    private String pole = null;
    private int iNom = 0;
    private int iDsc = 0;
    private int iStat1Value = 0;
    private int iStat2Value = 0;
    private int iStat1Label = 0;
    private int iStat2Label = 0;

    public PoleFragment() {
    }


    public static PoleFragment newInstance(String pole,int[] values) {
        PoleFragment fragment = new PoleFragment();
        Bundle args = new Bundle();
        args.putCharSequence("pole",pole);
        args.putIntArray("values",values);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.pole = getArguments().getString("pole");
            this.iNom = Objects.requireNonNull(getArguments().getIntArray("values"))[0];
            this.iDsc = Objects.requireNonNull(getArguments().getIntArray("values"))[1];
            this.iStat1Value = Objects.requireNonNull(getArguments().getIntArray("values"))[2];
            this.iStat1Label = Objects.requireNonNull(getArguments().getIntArray("values"))[3];
            this.iStat2Value = Objects.requireNonNull(getArguments().getIntArray("values"))[4];
            this.iStat2Label = Objects.requireNonNull(getArguments().getIntArray("values"))[5];
        }
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View vue = inflater.inflate(R.layout.content_contact_pole, container, false);

        if(CustomDisplay.getScreenOrientation(Objects.requireNonNull(getActivity())) == Configuration.ORIENTATION_LANDSCAPE){
            vue = inflater.inflate(R.layout.content_contact_pole_paysage, container, false);
        }

        // Construct the data source
        ArrayList<MailContact> arrayOfUsers = new ArrayList<>();

        TextView nomPole = vue.findViewById(R.id.tvName);
        TextView desc = vue.findViewById(R.id.tvDescription);
        TextView valeurStat1 = vue.findViewById(R.id.tvStat1chiffre);
        TextView labelStat1 = vue.findViewById(R.id.tvStat1label);
        TextView valeurStat2 = vue.findViewById(R.id.tvStat2chiffre);
        TextView labelStat2 = vue.findViewById(R.id.tvStat2label);

        try {
            // Retrieve json from cache
            String jsonTeam =  CacheThis.readObject(vue.getContext(), "jteam").toString();

            // Decode json array from the right pole
            JSONObject oneObject = new JSONObject(jsonTeam);
            JSONArray listOfMails = oneObject.getJSONArray(pole);
            for (int i=0; i < listOfMails.length(); i++) {
                JSONObject jsonMail = listOfMails.getJSONObject(i);
                arrayOfUsers.add(MailContact.ParseJSON(jsonMail));
            }
        } catch (JSONException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        nomPole.setText(iNom);
        desc.setText(iDsc);
        valeurStat1.setText(iStat1Value);
        labelStat1.setText(iStat1Label);
        valeurStat2.setText(iStat2Value);
        labelStat2.setText(iStat2Label);

        // Create the adapter to convert the array to views
        MailAdapter adapter = new MailAdapter(getContext(), arrayOfUsers);
        // Attach the adapter to a ListView
        final ListView listView = vue.findViewById(R.id.listeViewMail);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long id) {
                MailContact mail = (MailContact) adapter.getItemAtPosition(position);
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/html");
                intent.putExtra(Intent.EXTRA_EMAIL,new String[] { mail.adresse });
                intent.putExtra(Intent.EXTRA_SUBJECT, "Contact depuis l'appli Android");
                intent.putExtra(Intent.EXTRA_TEXT, "Entrez votre contenu.");
                startActivity(Intent.createChooser(intent, "Envoyer un mail"));
            }
        });

        return vue;
    }

}