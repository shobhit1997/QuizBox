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

public class CustomListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    ArrayList<Integer> icon=new ArrayList<>();
    ArrayList<String> name=new ArrayList<>();
    public CustomListAdapter(Activity context, ArrayList<String> itemname, ArrayList<Integer> imgid) {
        super(context, R.layout.list_single, itemname);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.name=itemname;
        this.icon=imgid;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.list_single, null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.Itemname);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);


        txtTitle.setText(name.get(position));
        imageView.setImageResource(icon.get(position));

        return rowView;

    };
}
