package com.example.dell.quizbox;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.*;
import com.firebase.ui.auth.BuildConfig;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class UserWindow extends AppCompatActivity implements
         NavigationView.OnNavigationItemSelectedListener {


    ArrayList<Integer> icon=new ArrayList<>();
    ArrayList<String> name=new ArrayList<>();

    private FirebaseDatabase myDatabase;
    private DatabaseReference users;
    private DatabaseReference usersId;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    private ChildEventListener myChildListener;

    public static final int RC_SIGN_IN = 1;
   static  String USER_NAME="ANONYMOUS";
    static String EMAIL_ID="@android.com";
    static String User_Id="";
    TextView name1;
    TextView id1;
    boolean flag=true;
    User user;

   // private ShareActionProvider mShareActionProvider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_window);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mFirebaseAuth = FirebaseAuth.getInstance();


        myDatabase=FirebaseDatabase.getInstance();
        users=myDatabase.getReference().child("Users");
        usersId=myDatabase.getReference().child("UserId");


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        name1=(TextView)navigationView.getHeaderView(0).findViewById(R.id.username1);
        id1=(TextView)navigationView.getHeaderView(0).findViewById(R.id.id1);

        mAuthStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user=firebaseAuth.getCurrentUser();

                if(user!=null)
                {
                    //if signed in
                    User_Id=user.getUid();
                    onSignedInInitialise(user.getDisplayName(),user.getEmail(),user.getUid());

                }
                else
                {
                    //if signed out
                    onSignedOutCleanUp();

                }

            }
        };





    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RC_SIGN_IN)
        {
            if(resultCode==RESULT_OK)
            {
                Toast.makeText(getApplicationContext(),"Signed In!",Toast.LENGTH_SHORT).show();
            }
            else if(resultCode==RESULT_CANCELED)
            {
                Toast.makeText(getApplicationContext(),"Sign In Canceled!",Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mAuthStateListener!=null)
            mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
    }




    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user_window, menu);
       /* MenuItem item = menu.findItem(R.id.nav_share);
        mShareActionProvider = (ShareActionProvider) item.getActionProvider();*/
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager = getFragmentManager();


        if (id == R.id.nav_category) {


            fragmentManager.beginTransaction()
                    .replace(R.id.userwindow, new Categories())
                    .commit();


            // Handle the camera action
        } else if (id == R.id.nav_UserQuizzes) {
            fragmentManager.beginTransaction()
                    .replace(R.id.userwindow, new OtherQuizzes())
                    .commit();


        } else if (id == R.id.nav_MyQuiz) {
            fragmentManager.beginTransaction()
                    .replace(R.id.userwindow, new MakeMyQuiz())
                    .commit();


        } else if (id == R.id.nav_leader) {

            fragmentManager.beginTransaction()
                    .replace(R.id.userwindow, new LeaderBoard())
                    .commit();


        } else if (id == R.id.nav_share) {

            SharedPreferences sharedPreferences=this.getSharedPreferences("com.example.dell.quizbox",MODE_PRIVATE);
            int score=sharedPreferences.getInt(user.getUserId(),0);
           Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "High Score:"+score);

            sendIntent.setType("text/plain");
            startActivity(Intent.createChooser(sendIntent,"Select the app to share!"));


        } else if (id == R.id.nav_signOut) {

            AuthUI.getInstance().signOut(this);
            finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    public void onSignedInInitialise(String username,String email,String uid)
    {
        Log.i("UserName",username);
        Log.i("Email",email);
        if(username!=null)
            USER_NAME=username;
        if(email!=null)
            EMAIL_ID=email;

        Log.i("UserName",USER_NAME);
        Log.i("Email",EMAIL_ID);


        if(name1!=null)
            name1.setText(USER_NAME);
        if(id1!=null)
            id1.setText(EMAIL_ID);

        user=new User(uid,username,0);
        if(users.child(uid)==null) {
            users.child(uid).setValue(user);
            SharedPreferences sharedPreferences=this.getSharedPreferences("com.example.dell.quizbox",MODE_PRIVATE);
            sharedPreferences.edit().putInt(uid,0).apply();
        }






    }
    public void onSignedOutCleanUp()
    {
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setIsSmartLockEnabled(!BuildConfig.DEBUG)
                        .setTheme(R.style.GreenTheme)
                        .setAvailableProviders(
                                Arrays.asList(new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
                                        new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build()))
                        .build(),
                RC_SIGN_IN);
    }







}
