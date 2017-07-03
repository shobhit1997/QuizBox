package com.example.dell.quizbox;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.share.widget.ShareButton;
import com.facebook.share.widget.ShareDialog;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class BuzzerMcq extends AppCompatActivity {

    ArrayList<String> answers;
    ArrayList<String> questions;
    ArrayList<String[]> options;
    String category;
    String answer="";

    int questionNo=0;
    ShareButton shareButton;
    ProgressBar timerbar;
    CallbackManager callbackManager;
    ShareDialog shareDialog;
    CountDownTimer timer;
    TextView Score[]=new TextView[2];
    int n=0,height,width ,flag=1,player=0;
    int score[]=new int[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buzzer_mcq);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        Intent i=getIntent();
        category=i.getStringExtra("category");
        timerbar=(ProgressBar)findViewById(R.id.timer);




        questions=BuzzerActivity.questions;
        answers=BuzzerActivity.answers;
        options=BuzzerActivity.options;
        setQuestion(questionNo);


       /* LinearLayout myLayout =
                (LinearLayout)findViewById(R.id.Buzz);

        myLayout.setOnTouchListener(
                new LinearLayout.OnTouchListener() {
                    public boolean onTouch(View v,
                                           MotionEvent m) {
                        if(flag==1)
                            handleTouch(m);
                        return true;
                    }
                }
        );*/




    }

    void handleTouch(MotionEvent m)
    {

        //int pointerCount = m.getPointerCount();
        int i=m.getActionIndex();
        size();
        int x = (int) m.getX(i);
        int y = (int) m.getY(i);
        // TextView ques = (TextView) findViewById(R.id.Ques);
        // ques. setText(width+","+height+","+x+","+y);
        if(y<=height/2)
        {

            flag=0;
            player=1;


        }
        else
        {
            flag=0;
            player=2;

        }


    }



    public void size()
    {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;
    }

    public void setQuestion(int n)
    {   if(n<10) {

        timerbar.setProgress(10000);

        TextView ques1= (TextView) findViewById(R.id.question1);
        TextView ques2= (TextView) findViewById(R.id.question2);
        RadioButton O11 = (RadioButton) findViewById(R.id.Option11);
        RadioButton O12 = (RadioButton) findViewById(R.id.Option12);
        RadioButton O13 = (RadioButton) findViewById(R.id.Option13);
        RadioButton O14 = (RadioButton) findViewById(R.id.Option14);
        RadioButton O21 = (RadioButton) findViewById(R.id.Option21);
        RadioButton O22 = (RadioButton) findViewById(R.id.Option22);
        RadioButton O23 = (RadioButton) findViewById(R.id.Option23);
        RadioButton O24 = (RadioButton) findViewById(R.id.Option24);
        answer=answers.get(n);
        ques1.setText(questions.get(n));
        ques2.setText(questions.get(n));
        O11.setText(options.get(n)[0]);
        O12.setText(options.get(n)[1]);
        O13.setText(options.get(n)[2]);
        O14.setText(options.get(n)[3]);
        O21.setText(options.get(n)[0]);
        O22.setText(options.get(n)[1]);
        O23.setText(options.get(n)[2]);
        O24.setText(options.get(n)[3]);
        timer=new CountDownTimer(10 * 1000+100, 1) {
            @Override
            public void onTick(long millisUntilFinished) {
               // Log.i("Time Left",millisUntilFinished+"");
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

       /* LinearLayout lo=(LinearLayout)findViewById(R.id.relativeLayout2);
        lo.setVisibility(View.GONE);
        TextView sco=(TextView)findViewById(R.id.score);
        sco.setVisibility(View.VISIBLE);
        sco.setText("Score : "+Score);
        shareButton.setVisibility(View.VISIBLE);
        shareButton.setEnabled(true);
        Log.i("Score",Score+"");*/

        RelativeLayout rl1=(RelativeLayout)findViewById(R.id.relativeLayout1);
        RelativeLayout rl2=(RelativeLayout)findViewById(R.id.relativeLayout2);
        RelativeLayout rl3=(RelativeLayout)findViewById(R.id.relativeLayout3);
        RelativeLayout rl4=(RelativeLayout)findViewById(R.id.relativeLayout4);

        rl1.setVisibility(View.GONE);
        rl2.setVisibility(View.GONE);
        timerbar.setVisibility(View.GONE);
        if(score[0]>score[1])
        {
            rl3.setVisibility(View.VISIBLE);
        }
        else if(score[1]>score[0])
        {
            rl4.setVisibility(View.VISIBLE);
        }
        else
        {
            rl3.setVisibility(View.VISIBLE);
            rl4.setVisibility(View.VISIBLE);
            TextView tv1=(TextView)findViewById(R.id.tv1);
            TextView tv2=(TextView)findViewById(R.id.tv2);
            tv1.setText("Draw");
            tv2.setText("Draw");
        }

    }
    }
    public void Check(View view)
    {
        timer.cancel();
        RadioButton rb=(RadioButton)findViewById(view.getId());
        int id=view.getId();
        if(id==R.id.Option11||id==R.id.Option12||id==R.id.Option13||id==R.id.Option14)
        {
            player =1;
        }
        else
        {
            player=2;
        }

        String Ans=rb.getText().toString();

        Score[0]=(TextView)findViewById(R.id.score1);
        Score[1]=(TextView)findViewById(R.id.score2);
        Log.i("Ans",answer);
        Log.i("Selected",Ans);

        if(Ans.equals(answer))
        {
            score[player-1]++;
            Score[player-1].setText(String.valueOf(score[player-1]));
        }
        rb.setChecked(false);

        setQuestion(++questionNo);


    }


}
