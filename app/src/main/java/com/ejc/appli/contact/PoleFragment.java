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
import com.ejc.appli.tools.CustomDisplay;

import java.util.ArrayList;
import java.util.Objects;

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

        switch (pole) {
            case "cdp":

                arrayOfUsers.clear();
                arrayOfUsers.add(new MailContact("contact@ejc.fr", "EjC"));

                break;
            case "ci":

                arrayOfUsers.clear();
                arrayOfUsers.add(new MailContact("controle-interne@ejc.fr", "EjC"));
                arrayOfUsers.add(new MailContact("isaura.laurent@ensai.fr", "Administrateur"));
                arrayOfUsers.add(new MailContact("solene.venezia@ensai.fr", "Administrateur"));

                break;
            case "comm":

                arrayOfUsers.clear();
                arrayOfUsers.add(new MailContact("communication@ejc.fr", "EjC"));
                arrayOfUsers.add(new MailContact("marie.ract@ensai.fr", "Administrateur"));
                arrayOfUsers.add(new MailContact("remy.chevalier@ensai.fr", "Administrateur"));
                arrayOfUsers.add(new MailContact("juliette.meyer@ensai.fr", "Administrateur"));
                arrayOfUsers.add(new MailContact("louis.le-clainche@ensai.fr", "Administrateur"));

                break;
            case "devco":

                arrayOfUsers.clear();
                arrayOfUsers.add(new MailContact("developpement-commercial@ejc.fr", "EjC"));
                arrayOfUsers.add(new MailContact("betty.bonnet@ensai.fr", "Administrateur"));
                arrayOfUsers.add(new MailContact("lea.fortat@ensai.fr", "Administrateur"));
                arrayOfUsers.add(new MailContact("mahalia.stepanoff@ensai.fr", "Administrateur"));
                arrayOfUsers.add(new MailContact("dylan.decrulle@ensai.fr", "Administrateur"));
                arrayOfUsers.add(new MailContact("laurine.bonin@ensai.fr", "Administrateur"));
                arrayOfUsers.add(new MailContact("nicolas.bertin@ensai.fr", "Administrateur"));

                break;
            case "info":

                arrayOfUsers.clear();
                arrayOfUsers.add(new MailContact("info@ejc.fr", "EjC"));
                arrayOfUsers.add(new MailContact("samuel.goutin@ensai.fr", "Administrateur"));
                arrayOfUsers.add(new MailContact("paul.imbault@ensai.fr", "Administrateur"));

                break;
            case "prez":

                arrayOfUsers.clear();
                arrayOfUsers.add(new MailContact("presidence@ejc.fr", "EjC"));
                arrayOfUsers.add(new MailContact("margot.havet@ensai.fr", "Présidente"));
                arrayOfUsers.add(new MailContact("julie.mandard@ensai.fr", "Vice-présidente"));
                arrayOfUsers.add(new MailContact("derek.aubert@ensai.fr", "Vice-président"));

                break;
            case "secgen":

                arrayOfUsers.clear();
                arrayOfUsers.add(new MailContact("secretaire@ejc.fr", "EjC"));
                arrayOfUsers.add(new MailContact("clement.soulignac@ensai.fr", "Administrateur"));

                break;
            case "treso":

                arrayOfUsers.clear();
                arrayOfUsers.add(new MailContact("tresorerie@ejc.fr", "EjC"));
                arrayOfUsers.add(new MailContact("alois.de-oliveira@ensai.fr", "Trésorier"));
                arrayOfUsers.add(new MailContact("melanie.baconnais@ensai.fr", "Vice-trésorier"));
                arrayOfUsers.add(new MailContact("albane.cosse@ensai.fr", "Comptable"));

                break;
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