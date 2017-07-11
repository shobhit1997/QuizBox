package com.example.dell.quizbox;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareHashtag;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;
import com.facebook.share.widget.ShareDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MCQ3 extends AppCompatActivity {

    ArrayList<String> answers;
    ArrayList<String> questions;
    ArrayList<String[]> options;
    String category;
    String answer;
    int Score;
    int questionNo=0;
    CountDownTimer timer;
    ProgressBar timerbar;

    ShareButton shareButton;

    CallbackManager callbackManager;
    ShareDialog shareDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mcq3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        timerbar=(ProgressBar)findViewById(R.id.timer);
        shareButton = (ShareButton)findViewById(R.id.shareButton);


        Intent i=getIntent();
        category=i.getStringExtra("category");

        TextView cat=(TextView)findViewById(R.id.cat);
        cat.setText(category);

        questions=Categories.questions;
        answers=Categories.answers;
        options=Categories.options;
        setQuestion(questionNo);

        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);
        shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
            @Override
            public void onSuccess(Sharer.Result result) {
                Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();

            }

            @Override
            public void onCancel() {

                Toast.makeText(getApplicationContext(),"Cancelled",Toast.LENGTH_LONG).show();

            }

            @Override
            public void onError(FacebookException error) {

            } });

    }

    public void setQuestion(int n)
    {   if(n<10) {
        timerbar.setProgress(10000);
        TextView ques = (TextView) findViewById(R.id.question);
        RadioButton O1 = (RadioButton) findViewById(R.id.Option1);
        RadioButton O2 = (RadioButton) findViewById(R.id.Option2);
        RadioButton O3 = (RadioButton) findViewById(R.id.Option3);
        RadioButton O4 = (RadioButton) findViewById(R.id.Option4);
        answer=answers.get(n);
        ques.setText(questions.get(n));
        O1.setText(options.get(n)[0]);
        O2.setText(options.get(n)[1]);
        O3.setText(options.get(n)[2]);
        O4.setText(options.get(n)[3]);

        timer=new CountDownTimer(10 * 1000+100, 1) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.i("Time Left",millisUntilFinished+"");
                timerbar.setProgress((int)millisUntilFinished);
            }

            @Override
            public void onFinish() {

                setQuestion(++questionNo);

            }
        }.start();
    }
    else
    {
        /*setContentView(R.layout.score);
        TextView lb = (TextView) findViewById(R.id.Score);
        lb.setText(""+Score);
        ImageView imv=(ImageView) findViewById(R.id.amaze);
        if(Score>5) {


            imv.setImageResource(R.drawable.awesum);
        }
        else
        {
            imv.setImageResource(R.drawable.sad);
        }*/

        LinearLayout lo=(LinearLayout)findViewById(R.id.relativeLayout2);
        lo.setVisibility(View.GONE);
        TextView sco=(TextView)findViewById(R.id.score);
        TextView wrngsco=(TextView)findViewById(R.id.wrongScore);
        ImageView imageview=(ImageView) findViewById(R.id.imageView8);
        imageview.setVisibility(View.VISIBLE);
        ImageView imageview1=(ImageView) findViewById(R.id.imageView9);
        imageview1.setVisibility(View.VISIBLE);
        sco.setVisibility(View.VISIBLE);
        wrngsco.setVisibility(View.VISIBLE);
        sco.setText(String.valueOf(Score));
        wrngsco.setText(String.valueOf(10-Score));
        shareButton.setVisibility(View.VISIBLE);
        shareButton.setEnabled(true);
        Log.i("Score",Score+"");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseDatabase myDatabase=FirebaseDatabase.getInstance();
        DatabaseReference users=myDatabase.getReference().child("Users");


        SharedPreferences sharedPreferences=this.getSharedPreferences("com.example.dell.quizbox",MODE_PRIVATE);
        int score=sharedPreferences.getInt(user.getUid(),0);

        sharedPreferences.edit().putInt(user.getUid(),(score+Score)).apply();


        User user1=new User(user.getUid(),user.getDisplayName(),score+Score);
        users.child(user.getUid()).setValue(user1);
        int score1=sharedPreferences.getInt(user.getUid(),0);
        Log.i("Score::",score1+"");
        shareButton.setVisibility(View.VISIBLE);
        shareButton.setEnabled(true);
        Log.i("Score",Score+"");


    }
    }
    public void Check(View view)
    {
        timer.cancel();
        RadioButton rb=(RadioButton)findViewById(view.getId());

        String Ans=rb.getText().toString();

        if(Ans.equals(answer))
        {
            Score++;
        }
        rb.setChecked(false);

        setQuestion(++questionNo);


    }
    public void next(View view)
    {
        setQuestion(++questionNo);


    }

    public void share(View view)
    {




        if (ShareDialog.canShow(ShareLinkContent.class)) {

            String sh1="#Score:"+Score;
           ShareHashtag sh=new ShareHashtag.Builder()
                   .setHashtag(sh1)
                   .build();

           /* ShareLinkContent linkContent = new ShareLinkContent.Builder()

                    .setShareHashtag(sh)
                    .setQuote("Score:"+Score)
             .build();*/
            ShareLinkContent linkContent = new ShareLinkContent.Builder()
                    .setContentUrl(Uri.parse("https://developers.facebook.com"))
                    .setQuote(sh1)
                    .build();




            shareButton.setShareContent(linkContent);
        }




    }


}
