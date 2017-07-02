package com.example.dell.quizbox;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;

import com.facebook.CallbackManager;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareHashtag;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;
import com.facebook.share.widget.ShareDialog;

import java.util.ArrayList;

public class mcq extends AppCompatActivity {

     ArrayList<String> answers;
    ArrayList<String> questions;
    ArrayList<String[]> options;
    String category;
    String answer;
    int Score;
    int questionNo=0;
    ShareButton shareButton;

    CallbackManager callbackManager;
    ShareDialog shareDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mcq);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent i=getIntent();
        category=i.getStringExtra("category");

        shareButton = (ShareButton)findViewById(R.id.shareButton);

        TextView cat=(TextView)findViewById(R.id.cat);
        cat.setText(category);

        questions=SInglePlayerActivity.questions;
        answers=SInglePlayerActivity.answers;
        options=SInglePlayerActivity.options;
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

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }



    public void setQuestion(int n)
    {   if(n<10) {

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

        LinearLayout lo=(LinearLayout)findViewById(R.id.linearLayout2);
        lo.setVisibility(View.GONE);
        TextView sco=(TextView)findViewById(R.id.score);
        sco.setVisibility(View.VISIBLE);
        sco.setText("Score : "+Score);
        shareButton.setVisibility(View.VISIBLE);
        shareButton.setEnabled(true);
        Log.i("Score",Score+"");



    }
    }
    public void Check(View view)
    {
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
