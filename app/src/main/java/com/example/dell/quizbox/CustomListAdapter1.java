package com.example.dell.quizbox;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by DELL on 6/20/2017.
 */

public class CustomListAdapter1 extends ArrayAdapter<String> {

    private final Activity context;
    ArrayList<Integer> score=new ArrayList<>();
    ArrayList<Integer> icon=new ArrayList<>();
    ArrayList<String> name=new ArrayList<>();
    public CustomListAdapter1(Activity context, ArrayList<String> username, ArrayList<Integer> score1, ArrayList<Integer> icon) {
        super(context, R.layout.list_single1, username);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.name=username;
        this.score=score1;
        this.icon=icon;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.list_single1, null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.Username);
        TextView scoreView = (TextView) rowView.findViewById(R.id.score);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);


        txtTitle.setText(name.get(position));
        scoreView.setText(score.get(position)+"");
        imageView.setImageResource(icon.get(position));

        return rowView;

    };
}
