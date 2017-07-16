package com.example.dell.quizbox;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    CountDownTimer timer;
    float a=1-1/5000.0f;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView)findViewById(R.id.welcome);

        a=1.0f;




       timer=new CountDownTimer(5 * 1000+100, 500) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.i("Time Left",millisUntilFinished+"");
                imageView.animate().alpha(a).setDuration(500);
                a=(a==0.5?1.0f:0.5f);



            }

            @Override
            public void onFinish() {

                Intent i=new Intent(getApplicationContext(),Welcome.class);

                startActivity(i);


            }
        }.start();
    }


    public void next(View view)
    {
        timer.cancel();
        Intent i=new Intent(getApplicationContext(),Welcome.class);

        startActivity(i);



    }
}
