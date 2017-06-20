package com.example.dell.quizbox;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by DELL on 6/20/2017.
 */

public class MakeMyQuiz extends Fragment {

    View myView;
    ArrayList<Integer> icon1=new ArrayList<>();
    ArrayList<String> name1=new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView=inflater.inflate(R.layout.make_my_quiz,container,false);


        return myView;
    }

    @Override
    public void onAttach(final Context context) {
        super.onAttach(context);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        TextView save=(TextView) getActivity().findViewById(R.id.save);
        TextView saveNadd=(TextView) getActivity().findViewById(R.id.saveNadd);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(), "Save", Toast.LENGTH_SHORT).show();

            }
        });

        saveNadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(), "Save And Add", Toast.LENGTH_SHORT).show();

            }
        });


    }
}
