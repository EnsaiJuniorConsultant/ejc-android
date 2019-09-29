package com.ejc.appli;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.ejc.appli.test.R;
import com.ejc.appli.article.Article;
import com.ejc.appli.event.Event;
import com.ejc.appli.history.FragmentHistoryVerticalTabbed;
import com.ejc.appli.jer.FragmentJERVerticalTabbed;
import com.ejc.appli.tools.BottomNavigationViewBehavior;
import com.ejc.appli.tools.CacheThis;
import com.ejc.appli.user.Administrateur;
import com.ejc.appli.user.Ancien;
import com.ejc.appli.user.ChefDeProjet;
import com.ejc.appli.user.Consultant;
import com.ejc.appli.user.User;
import com.ejc.appli.wanted.Wanted;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        OnFragmentInteractionListener {

    private static MenuItem menuPrec = null;
    public  static boolean upDateMenu = false;
    public static User mUser = null;
    private boolean isMainFragment = true;
    public static final ArrayList<Article> arrayOfArticles = new ArrayList<>();
    public static final ArrayList<Wanted> arrayOfWanteds = new ArrayList<>();
    public static final ArrayList<Event> arrayOfEvents = new ArrayList<>();
    public static final ArrayList<String> arrayOfInterview = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        BottomNavigationView mBottomNavigationView = findViewById(R.id.bottom_navigation);
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) mBottomNavigationView.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationViewBehavior());

        // Initialisation du menu du bas
        mBottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.action_home:
                                selectedFragment = FragmentMain.newInstance();
                                break;
                            case R.id.action_1:
                                selectedFragment = FragmentTousEvent.newInstance();
                                break;
                            case R.id.action_2:
                                selectedFragment = FragmentTousArticles.newInstance();
                                break;
                            case R.id.action_3:
                                selectedFragment = FragmentTousWanted.newInstance();
                                break;
                            case R.id.action_4:
                                selectedFragment = FragmentFAQ.newInstance();
                                break;
                        }
                        setTitle(item.getTitle());
                        isMainFragment=false;

                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.flContent, Objects.requireNonNull(selectedFragment));
                        transaction.commit();
                        return true;
                    }
                });

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean keepConnected = preferences.getBoolean("user_keep_connected",false);

        if(keepConnected) {
            if (preferences.getInt("user_compte", -1) == 0) {
                mUser = new Consultant(preferences.getString("user_nom", "nom"),
                        preferences.getString("user_prenom", "prenom"),
                        preferences.getString("user_mail", "mail"));
            } else if (preferences.getInt("user_compte", -1) == 1) {
                mUser = new Administrateur(preferences.getString("user_nom", "nom"),
                        preferences.getString("user_prenom", "prenom"),
                        preferences.getString("user_mail", "mail"));
            } else if (preferences.getInt("user_compte", -1) == 2) {
                mUser = new Ancien(preferences.getString("user_nom", "nom"),
                        preferences.getString("user_prenom", "prenom"),
                        preferences.getString("user_mail", "mail"));
            } else if (preferences.getInt("user_compte", -1) == 3) {
                mUser = new ChefDeProjet(preferences.getString("user_nom", "nom"),
                        preferences.getString("user_prenom", "prenom"),
                        preferences.getString("user_mail", "mail"));
            }
        }

        if(mUser!=null){updateMenu(false);}
        setTitle("Ensai junior Consultant");
        isMainFragment = true;

        // Le code ci-dessous est utile en cas de changement d'orientation ou de retour de veille
        if (savedInstanceState == null) {
            Fragment fragment = FragmentMain.newInstance();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragment.setArguments(getIntent().getExtras());
            fragmentManager.beginTransaction().replace(R.id.flContent, fragment,"fragment_tag_main").commit();
        }else{
            Fragment fragment = getSupportFragmentManager().findFragmentByTag("fragment_tag_main");
        }

        // Au premier lancement, les données ne sont pas chargées. On relance le fragment pour intégrer les données
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run(){
                Fragment fragment = FragmentMain.newInstance();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragment.setArguments(getIntent().getExtras());
                fragmentManager.beginTransaction().replace(R.id.flContent, fragment,"fragment_tag_main").commit();
            }
        },10);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (!isMainFragment){
            Fragment fragment = FragmentMain.newInstance();
            FragmentManager fragmentManager = getSupportFragmentManager();

            BottomNavigationView mBottomNavigationView = findViewById(R.id.bottom_navigation);
            mBottomNavigationView.setVisibility(View.VISIBLE);

            fragment.setArguments(getIntent().getExtras());
            fragmentManager.beginTransaction().replace(R.id.flContent, fragment,"fragment_tag_main").commit();
            setTitle("Ensai Junior Consultant");
            isMainFragment = true;
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        boolean isFragment = true;
        Fragment fragment = null;
        if (id == R.id.nav_contact) {
            fragment = FragmentContactTabbed.newInstance();
        } else if (id == R.id.nav_jer) {
            fragment = FragmentJERVerticalTabbed.newInstance();
        } else if (id == R.id.nav_aboutapp) {
            fragment = FragmentAboutApp.newInstance();
        } else if (id == R.id.nav_ejc_history) {
            fragment = FragmentHistoryVerticalTabbed.newInstance();
        } else if (id == R.id.nav_ejc_consultants) {
            fragment = FragmentConsultant.newInstance();
        } else if (id == R.id.nav_mvmt) {
            fragment = FragmentCnje.newInstance();
        } else if (id == R.id.nav_logout) {
            deconnexion();
            fragment = FragmentTousArticles.newInstance();
        } else if(id == R.id.nav_login){
            isFragment = false;
            Intent i = new Intent(getBaseContext(), LoginActivity.class);
            startActivity(i);
        }

        if(isFragment) {
            // Insert the fragment by replacing any existing fragment
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.flContent, Objects.requireNonNull(fragment)).commit();

            // Set action bar title
            setTitle(item.getTitle());
            isMainFragment = false;
        }

        // Highlight the selected item has been done by NavigationView
        if (menuPrec != null) {
            menuPrec.setChecked(false);
        }

        item.setChecked(true);
        menuPrec = item;

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        BottomNavigationView mBottomNavigationView = findViewById(R.id.bottom_navigation);
        mBottomNavigationView.setVisibility(View.GONE);

        return true;
    }

    /*
    @Override
    public void onFragmentInteraction(Uri uri) {

    }
    */

    @Override
    public void onResume(){
        super.onResume();
        if(upDateMenu && mUser!=null){updateMenu(true);}
        upDateMenu = false;
    }


    private void updateMenu(boolean printToast) {
        NavigationView navigationView = findViewById(R.id.nav_view);
        Menu menu = navigationView.getMenu();
        if (mUser != null) {
            for (int i = 0; i < mUser.getMenuAssocie().size(); i++) {
                menu.findItem(mUser.getMenuAssocie().get(i)).setVisible(true);
            }
            menu.findItem(R.id.nav_login).setVisible(false);
            menu.findItem(R.id.nav_logout).setVisible(true);
            if (printToast) {
                Toast.makeText(getBaseContext(), "Bonjour ", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // A chaque onStart, on réextrait les données des sauvegardes json
    @Override
    public void onStart(){
        super.onStart();
        arrayOfArticles.clear();
        arrayOfEvents.clear();
        arrayOfWanteds.clear();
        arrayOfInterview.clear();

        // Load wanted
        // TODO: Create a function ParseJSON in the Class Wanted
        try {
            String importWanted =  CacheThis.readObject(this.getBaseContext(), "jwanted").toString();
            JSONArray mJasonArray = new JSONArray(importWanted);
            Log.i("bdd","Ajout de "+mJasonArray.length()+" Wanted");
            for (int i=0; i < mJasonArray.length(); i++) {
                JSONObject oneObject = mJasonArray.getJSONObject(i);
                List<String> comp1 = new ArrayList<>();
                List<String> phase1 = new ArrayList<>();
                List<String> ech1 = new ArrayList<>();

                for(int a = 0 ; a < oneObject.getJSONArray("competence").length() ; a++) {
                    comp1.add(oneObject.getJSONArray("competence").getString(a));
                }
                for(int b = 0 ; b < oneObject.getJSONArray("phase").length() ; b++) {
                    phase1.add(oneObject.getJSONArray("phase").getString(b));
                }
                for(int c = 0 ; c < oneObject.getJSONArray("echeancier").length() ; c++) {
                    ech1.add(oneObject.getJSONArray("echeancier").getString(c));
                }

                JSONObject jCdP = oneObject.getJSONObject("chefDeProjet");
                JSONObject jAssCdP = oneObject.getJSONObject("assCdP");

                List<User> cdp = new ArrayList<>();

                if(jCdP.getInt("compte")==1){
                    cdp.add(new Administrateur(jCdP.getString("nom"),jCdP.getString("prenom"),jCdP.getString("mail")));
                }else if(jCdP.getInt("compte")==3) {
                    cdp.add(new ChefDeProjet(jCdP.getString("nom"), jCdP.getString("prenom"), jCdP.getString("mail")));
                }

                if(jAssCdP.getInt("compte")==1){
                    cdp.add(new Administrateur(jAssCdP.getString("nom"),jAssCdP.getString("prenom"),jAssCdP.getString("mail")));
                }else if(jAssCdP.getInt("compte")==3){
                    cdp.add(new ChefDeProjet(jAssCdP.getString("nom"),jAssCdP.getString("prenom"),jAssCdP.getString("mail")));
                }

                arrayOfWanteds.add(new Wanted(oneObject.getInt("idEtude"),
                        phase1,
                        new boolean[]{oneObject.getJSONArray("annees").getBoolean(0),oneObject.getJSONArray("annees").getBoolean(1),oneObject.getJSONArray("annees").getBoolean(2)},
                        oneObject.getInt("nbreConsultant"),
                        comp1,
                        ech1,
                        cdp.get(0),
                        cdp.get(1)));

                Collections.sort(arrayOfWanteds, new Comparator<Wanted>() {
                    @Override
                    public int compare(Wanted w2, Wanted w1)
                    {
                        return w2.getIdEtude()-w1.getIdEtude();
                    }
                });

            }
        } catch (JSONException | IOException | ClassNotFoundException e) {
            Log.e("wanteds_from_cache", e.toString());
            e.printStackTrace();
        }

        // Load events
        // TODO: Create a function ParseJSON in the Class Event
        try {
            String importEvent =  CacheThis.readObject(this.getBaseContext(), "jevent").toString();
            JSONArray mJasonArray = new JSONArray(importEvent);
            for (int i=0; i < mJasonArray.length(); i++) {
                JSONObject oneObject = mJasonArray.getJSONObject(i);
                Event candidat = new Event(
                        oneObject.getInt("jour"),
                        oneObject.getInt("mois"),
                        oneObject.getInt("annee"),
                        oneObject.getString("label"),
                        oneObject.getString("organisateur"),
                        oneObject.getString("commentaire"),
                        oneObject.getString("localisation"));
                Log.i("bdd",candidat.date.toString());
                if (candidat.date.compareTo(Calendar.getInstance(TimeZone.getDefault())) > 0){
                    arrayOfEvents.add(candidat);
                }
            }

            Collections.sort(arrayOfEvents, new Comparator<Event>() {
                @Override
                public int compare(Event ev2, Event ev1)
                {
                    return -ev1.date.compareTo(ev2.date);
                }
            });

        } catch (JSONException | IOException | ClassNotFoundException e) {
            Log.e("events_from_cache", e.toString());
            e.printStackTrace();
        }

        // Load articles
        // TODO : Import articles from the Internet
        try {
            // Read cache file jarticle, created during the splash screen
            // Parse the file to generate an array a articles
            String importArticle =  CacheThis.readObject(this.getBaseContext(), "jarticle").toString();
            JSONArray mJasonArray = new JSONArray(importArticle);
            Log.i("articles_from_cache","Ajout de "+mJasonArray.length()+" Articles");
            // TODO: Create a method ParseJSON in the Class Article
            for (int i=0; i < mJasonArray.length(); i++) {
                JSONObject oneObject = mJasonArray.getJSONObject(i);
                arrayOfArticles.add(new Article(
                        oneObject.getString("idArticle"),
                        oneObject.getString("titre"),
                        oneObject.getString("auteur"),
                        oneObject.getString("urlImage").replaceAll("http://", "https://"),
                        oneObject.getString("type")));
            }
            // TODO: Use date to sort article
            // Use idArticle to sort them
            Collections.sort(arrayOfArticles, new Comparator<Article>() {
                @Override
                public int compare(Article art2, Article art1)
                {
                    int id1 = Integer.parseInt(art1.idArticle.replace("_",""));
                    int id2 = Integer.parseInt(art2.idArticle.replace("_",""));
                    return  id1-id2;
                }
            });

        } catch (JSONException | IOException | ClassNotFoundException e) {
            Log.e("articles_from_cache", e.toString());
            e.printStackTrace();
        }

        // Load interviews
        try{
            String importInterview =  CacheThis.readObject(this.getBaseContext(), "jinterview").toString();
            JSONArray mJasonArray = new JSONArray(importInterview);
            for(int i = 0; i < mJasonArray.length();i++){
                arrayOfInterview.add(mJasonArray.getString(i));
            }

        }catch(JSONException | IOException | ClassNotFoundException e) {
            Log.e("interviews_from_cache", e.toString());
            e.printStackTrace();
        }

    }


    private void deconnexion(){
        NavigationView navigationView = findViewById(R.id.nav_view);
        Menu menu =navigationView.getMenu();
        menu.findItem(R.id.nav_logout).setVisible(false);
        for (int i = 0; i < mUser.getMenuAssocie().size(); i++) {
            menu.findItem(mUser.getMenuAssocie().get(i)).setVisible(false);
        }
        //menu.findItem(R.id.section_outils).setVisible(false);
        menu.findItem(R.id.nav_login).setVisible(true);
        Toast.makeText(getBaseContext(),"Aurevoir ",Toast.LENGTH_SHORT).show();

        SharedPreferences mySPrefs =PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = mySPrefs.edit();
        editor.remove("user_nom");
        editor.remove("user_prenom");
        editor.remove("user_compte");
        editor.remove("user_mail");
        editor.apply();

        mUser = null;

        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

}
