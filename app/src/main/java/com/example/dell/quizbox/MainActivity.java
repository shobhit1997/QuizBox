package com.example.dell.quizbox;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void QuizType(View view)
    {
        int id=view.getId();
        if(id==R.id.single_player)
        {
            Intent i=new Intent(getApplicationContext(),SInglePlayerActivity.class);

            startActivity(i);


        }

        if(id==R.id.buzzer_round)
        {
            Intent i=new Intent(getApplicationContext(),BuzzerActivity.class);

            startActivity(i);


        }
        else
        {
            Intent i=new Intent(getApplicationContext(),UserWindow.class);

            startActivity(i);
        }

    }
}
