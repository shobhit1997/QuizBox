package com.example.dell.quizbox;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Welcome extends AppCompatActivity {
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        builder = new AlertDialog.Builder(this);
    }
    public void QuizType(View view)
    {
        int id=view.getId();
        if(id==R.id.single_player)
        {
            Intent i=new Intent(getApplicationContext(),SInglePlayerActivity.class);

            startActivity(i);


        }

        else if(id==R.id.buzzer_round)
        {
            Intent i=new Intent(getApplicationContext(),BuzzerActivity.class);

            startActivity(i);


        }
        else if(id==R.id.multiplayer)
        {

            ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
            boolean isConnected = activeNetwork != null &&
                    activeNetwork.isConnectedOrConnecting();
            if(isConnected)
            {
                Intent i=new Intent(getApplicationContext(),UserWindow.class);

                startActivity(i);

            }
            else
            {




                builder.setTitle("Alert")
                        .setMessage("NO INTERNET CONNECTION")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }

        }

    }

}
