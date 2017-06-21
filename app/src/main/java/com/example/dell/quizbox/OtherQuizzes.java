package com.example.dell.quizbox;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Random;

import static android.content.ContentValues.TAG;

/**
 * Created by DELL on 6/20/2017.
 */

public class OtherQuizzes extends Fragment {

    View myView;
    ArrayList<Integer> icon1=new ArrayList<>();
    ArrayList<String> name1=new ArrayList<>();
    private FirebaseDatabase myDatabase;
    private DatabaseReference other_quizzes;
    private ChildEventListener myChildListener;
    static ArrayList<Question> ques;
    static ArrayList<String> answers=new ArrayList<>();
    static ArrayList<String> questions=new ArrayList<>();
    static ArrayList<String[]> options=new ArrayList<>();
    ArrayList<String> quizName=new ArrayList<>();
    ArrayList<MyQuiz> myQuiz=new ArrayList<>();
    ListView category;

    CustomListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView=inflater.inflate(R.layout.other_quizzes,container,false);




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

        myChildListener=new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {



                Log.i("Hello","Hii");
                try {
                    MyQuiz myQuiz1= dataSnapshot.getValue(MyQuiz.class);
                    myQuiz.add(myQuiz1);
                    Log.i("Quiz Name",myQuiz1.getQuizName());
                    icon1.add(R.drawable.film);
                    name1.add(myQuiz1.getQuizName());
                    adapter.notifyDataSetChanged();

                }catch (Exception e)
                {
                    e.printStackTrace();
                }





            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        other_quizzes.addChildEventListener(myChildListener);









         adapter=new CustomListAdapter(getActivity(),name1,icon1);


        category=(ListView)getView().findViewById(R.id.categoriesList);
        category.setAdapter(adapter);

        category.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                try {


                    String Selecteditem = name1.get(position);

                    ques = myQuiz.get(position).getQuestions();
                    int size = ques.size();
                    Log.i("Size",size+"");
                    for (int x = 0; x < size; x++) {
                        String WrongAns[] = new String[3];
                        Log.i("Done1","Successful"+x);
                        WrongAns[0] = ques.get(x).getWrong_ans1();
                        Log.i("Done2","Successful"+x);
                        WrongAns[1] = ques.get(x).getWrong_ans2();
                        Log.i("Done3","Successful"+x);
                        WrongAns[2] = ques.get(x).getWrong_ans3();
                        Log.i("Done4","Successful"+x);
                        Random rand = new Random();
                        int ansId = rand.nextInt(4);
                        String opt[] = new String[4];
                        opt[ansId] = ques.get(x).getAnswer();
                        Log.i("Done5","Successful"+x);
                        int i = 0;
                        for (int y = 0; y <= 3; y++) {
                            if (y == ansId) continue;
                            opt[y] = WrongAns[i++];
                        }
                        Log.i("Done6","Successful"+x);
                        questions.add(ques.get(x).getQuestion());
                        Log.i("Done7","Successful"+x);
                        answers.add(ques.get(x).getAnswer());
                        Log.i("Done8","Successful"+x);
                        options.add(opt);
                        Log.i("Done9","Successful"+x);
                    }
                    Intent intent = new Intent(getActivity().getApplicationContext(), MCQ2.class);
                    Log.i("Done 10","Successful");
                    intent.putExtra("category", Selecteditem);
                    Log.i("Done 11","Successful");
                    intent.putExtra("size", size);
                    Log.i("Done 12","Successful");
                    startActivity(intent);
                    Log.i("Done 13","Successful");

                    Toast.makeText(getActivity(), Selecteditem, Toast.LENGTH_SHORT).show();
                }catch (Exception e)
                {
                    e.printStackTrace();
                }


            }
        });
    }
}
