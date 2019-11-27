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
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        OnFragmentInteractionListener {

    private static MenuItem menuPrec = null;
    public  static boolean upDateMenu = false;
    public static User mUser = null;
    private boolean isMainFragment = true;

    public static ArrayList<Article> arrayOfArticles = new ArrayList<>();
    public static ArrayList<Wanted> arrayOfWanteds = new ArrayList<>();
    public static ArrayList<Event> arrayOfEvents = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Initialize BottomNavigationBar
        BottomNavigationView mBottomNavigationView = findViewById(R.id.bottom_navigation);
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) mBottomNavigationView.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationViewBehavior());
        mBottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.action_home:
                                selectedFragment = FragmentTousArticles.newInstance();
                                break;
                            case R.id.action_1:
                                selectedFragment = FragmentTousEvent.newInstance();
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

        // Check if one user is connected or not
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

        // This part fix an issue if user changes orientation, or leave standby mode
        if (savedInstanceState == null) {
            Fragment fragment = FragmentTousArticles.newInstance();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragment.setArguments(getIntent().getExtras());
            fragmentManager.beginTransaction().replace(R.id.flContent, fragment,"fragment_tag_main").commit();
        }else{
            Fragment fragment = getSupportFragmentManager().findFragmentByTag("fragment_tag_main");
        }

        // The first time, the app is run. Data are not loaded. That why we reload the fragment.
        // On the second loading, data are loaded is arrays
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run(){
                Fragment fragment = FragmentTousArticles.newInstance();
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
            Fragment fragment = FragmentTousArticles.newInstance();
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

    // onStart provoques a loading of data from cache
    @Override
    public void onStart(){
        super.onStart();
        JSONArray mJSONArray = null;


        // Read cache file jarticle/jevent/jwanted, created during the splash screen
        // Parse the file to generate an array a articles/events/wanteds


        // Load wanteds
        try {
            mJSONArray = new JSONArray(CacheThis.readObject(this.getBaseContext(), "jwanted").toString());
            arrayOfWanteds = Wanted.ParseJSONArray(mJSONArray);
        } catch (JSONException | IOException | ClassNotFoundException e) {
            Log.e("wanteds_from_cache", e.toString());
            e.printStackTrace();
        }

        // Load events
        try {
            mJSONArray = new JSONArray(CacheThis.readObject(this.getBaseContext(), "jevent").toString());
            arrayOfEvents = Event.ParseJSONArray(mJSONArray);
        } catch (JSONException | IOException | ClassNotFoundException e) {
            Log.e("events_from_cache", e.toString());
            e.printStackTrace();
        }

        // Load articles
        try {
            mJSONArray = new JSONArray(CacheThis.readObject(this.getBaseContext(), "jarticle").toString());
            arrayOfArticles = Article.ParseJSONArray(mJSONArray);
        } catch (JSONException | IOException | ClassNotFoundException e) {
            Log.e("articles_from_cache", e.toString());
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
