package com.example.dell.quizbox;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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


    RadioButton rb11;
    RadioButton rb12;
    RadioButton rb13;
    RadioButton rb14;
    RadioButton rb21;
    RadioButton rb22;
    RadioButton rb23;
    RadioButton rb24;

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
        timerbar.setMax(20000);

        rb11 = (RadioButton) findViewById(R.id.Option11);
        rb12 = (RadioButton) findViewById(R.id.Option12);
        rb13 = (RadioButton) findViewById(R.id.Option13);
        rb14 = (RadioButton) findViewById(R.id.Option14);

        rb21 = (RadioButton) findViewById(R.id.Option21);
        rb22 = (RadioButton) findViewById(R.id.Option22);
        rb23 = (RadioButton) findViewById(R.id.Option23);
        rb24 = (RadioButton) findViewById(R.id.Option24);



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

        rb11.setEnabled(true);
        rb12.setEnabled(true);
        rb13.setEnabled(true);
        rb14.setEnabled(true);
        rb21.setEnabled(true);
        rb22.setEnabled(true);
        rb23.setEnabled(true);
        rb24.setEnabled(true);

        timerbar.setProgress(20000);

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
        timer=new CountDownTimer(20 * 1000+100, 1) {
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
        rl1.setVisibility(View.GONE);
        rl2.setVisibility(View.GONE);
        timerbar.setVisibility(View.GONE);
        RelativeLayout rl3=(RelativeLayout)findViewById(R.id.relativeLayout3);
        RelativeLayout rl4=(RelativeLayout)findViewById(R.id.relativeLayout4);
        ImageView congrats=(ImageView)findViewById(R.id.congrats);

        if(score[0]>score[1])
        {
            //rl3.setVisibility(View.VISIBLE);
            congrats.setVisibility(View.VISIBLE);
            congrats.setRotation(180.0f);
        }
        else if(score[1]>score[0])
        {
            //rl4.setVisibility(View.VISIBLE);
            congrats.setVisibility(View.VISIBLE);
        }
        else
        {
           /* rl3.setVisibility(View.VISIBLE);
            rl4.setVisibility(View.VISIBLE);
            TextView tv1=(TextView)findViewById(R.id.tv1);
            TextView tv2=(TextView)findViewById(R.id.tv2);
            tv1.setText("Draw");
            tv2.setText("Draw");*/
           congrats.setImageResource(R.drawable.congratulation_draw);
            congrats.setVisibility(View.VISIBLE);
        }

    }
    }
    public void Check(View view)
    {
        timer.cancel();
        rb11.setEnabled(false);
        rb12.setEnabled(false);
        rb13.setEnabled(false);
        rb14.setEnabled(false);
        rb21.setEnabled(false);
        rb22.setEnabled(false);
        rb23.setEnabled(false);
        rb24.setEnabled(false);

        final RadioButton rb=(RadioButton)findViewById(view.getId());
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
        final Drawable d = rb.getBackground();

        if(Ans.equals(answer))
        {
            score[player-1]++;
            Score[player-1].setText(String.valueOf(score[player-1]));
            new CountDownTimer(2 * 1000 + 100, 500) {
                @Override
                public void onTick(long millisUntilFinished) {
                    rb.setBackgroundColor(getResources().getColor(R.color.material_lime_a700));


                }

                @Override
                public void onFinish() {

                    rb.setBackground(d);
                    rb.setChecked(false);

                    setQuestion(++questionNo);


                }
            }.start();
        }
        else
        {
            new CountDownTimer(2 * 1000 + 100, 500) {
                @Override
                public void onTick(long millisUntilFinished) {
                    rb.setBackgroundColor(getResources().getColor(R.color.red));


                }

                @Override
                public void onFinish() {

                    rb.setBackground(d);
                    rb.setChecked(false);

                    setQuestion(++questionNo);


                }
            }.start();
        }


    }


}
