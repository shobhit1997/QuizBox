package com.example.dell.quizbox;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class mcq extends AppCompatActivity {

     ArrayList<String> answers;
    ArrayList<String> questions;
    ArrayList<String[]> options;
    String category;
    String answer;
    int Score;
    int questionNo=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mcq);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Intent i=getIntent();
        category=i.getStringExtra("category");

        TextView cat=(TextView)findViewById(R.id.cat);
        cat.setText(category);

        questions=SInglePlayerActivity.questions;
        answers=SInglePlayerActivity.answers;
        options=SInglePlayerActivity.options;
        setQuestion(questionNo);


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

        TextView ques=(TextView)findViewById(R.id.question);
        ques.setAlpha(0f);
        RadioGroup rg=(RadioGroup)findViewById(R.id.radiogroup);
        rg.setAlpha(0f);
        RelativeLayout rl=(RelativeLayout)findViewById(R.id.skipButton);
        rl.setAlpha(0f);
        TextView sco=(TextView)findViewById(R.id.score);
        sco.setAlpha(1f);
        sco.setText("Score : "+Score);
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

}
