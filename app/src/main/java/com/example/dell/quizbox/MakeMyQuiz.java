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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by DELL on 6/20/2017.
 */

public class MakeMyQuiz extends Fragment {

    View myView;
    ArrayList<Integer> icon1=new ArrayList<>();
    ArrayList<String> name1=new ArrayList<>();
    private FirebaseDatabase myDatabase;
    private DatabaseReference other_quizzes;
    private MyQuiz myQuiz;
    private String USER_NAME=UserWindow.USER_NAME;
    ArrayList<Question> questionsArray=new ArrayList<>();

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



        myDatabase=FirebaseDatabase.getInstance();
        other_quizzes=myDatabase.getReference().child("other_quizzes");

















        TextView save=(TextView) getActivity().findViewById(R.id.save);
        TextView saveNadd=(TextView) getActivity().findViewById(R.id.saveNadd);







        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                EditText quizName=(EditText)getActivity().findViewById(R.id.quiz_name);
                EditText quesText=(EditText)getActivity().findViewById(R.id.ques);
                EditText ansText=(EditText)getActivity().findViewById(R.id.ans);
                EditText wrngans1=(EditText)getActivity().findViewById(R.id.wrngans1);
                EditText wrngans2=(EditText)getActivity().findViewById(R.id.wrngans2);
                EditText wrngans3=(EditText)getActivity().findViewById(R.id.wrngans3);
                String ques=quesText.getText().toString().trim();
                String ans=ansText.getText().toString().trim();
                String incans1=wrngans1.getText().toString().trim();
                String incans2=wrngans2.getText().toString().trim();
                String incans3=wrngans3.getText().toString().trim();
                if(ques.length()==0||ans.length()==0||incans1.length()==0||incans2.length()==0||incans3.length()==0)
                {
                    Toast.makeText(getActivity(), "Fill All Fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    Question question = new Question(quesText.getText().toString(), ansText.getText().toString(), wrngans1.getText().toString(), wrngans2.getText().toString(), wrngans3.getText().toString());
                    questionsArray.add(question);

                    quesText.setEnabled(false);
                    ansText.setEnabled(false);
                    wrngans1.setEnabled(false);
                    wrngans2.setEnabled(false);
                    wrngans3.setEnabled(false);
                    myQuiz = new MyQuiz(USER_NAME, quizName.getText().toString(), questionsArray);
                    other_quizzes.push().setValue(myQuiz);

                    Toast.makeText(getActivity(), "Saved", Toast.LENGTH_SHORT).show();
                }

            }
        });

        saveNadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText quizName=(EditText)getActivity().findViewById(R.id.quiz_name);


                if(quizName.isEnabled())
                {

                    quizName.setEnabled(false);
                }
                EditText quesText=(EditText)getActivity().findViewById(R.id.ques);
                EditText ansText=(EditText)getActivity().findViewById(R.id.ans);
                EditText wrngans1=(EditText)getActivity().findViewById(R.id.wrngans1);
                EditText wrngans2=(EditText)getActivity().findViewById(R.id.wrngans2);
                EditText wrngans3=(EditText)getActivity().findViewById(R.id.wrngans3);

                String ques=quesText.getText().toString().trim();
                String ans=ansText.getText().toString().trim();
                String incans1=wrngans1.getText().toString().trim();
                String incans2=wrngans2.getText().toString().trim();
                String incans3=wrngans3.getText().toString().trim();
                if(ques.length()==0||ans.length()==0||incans1.length()==0||incans2.length()==0||incans3.length()==0)
                {
                    Toast.makeText(getActivity(), "Fill All Fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    Question question = new Question(quesText.getText().toString(), ansText.getText().toString(), wrngans1.getText().toString(), wrngans2.getText().toString(), wrngans3.getText().toString());
                    questionsArray.add(question);
                    quesText.setText("");
                    ansText.setText("");
                    wrngans1.setText("");
                    wrngans2.setText("");
                    wrngans3.setText("");

                    Toast.makeText(getActivity(), "Saved", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
