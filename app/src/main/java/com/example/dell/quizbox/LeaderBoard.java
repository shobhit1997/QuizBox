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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by DELL on 7/2/2017.
 */

public class LeaderBoard extends Fragment {

    View myView;

    private FirebaseDatabase myDatabase;
    private DatabaseReference Users;
    private ChildEventListener myChildListener;

    ArrayList<User> users=new ArrayList<>();
    ArrayList<String> name1=new ArrayList<>();
    ArrayList<Integer>  score=new ArrayList<>();

    ListView category;
    private ProgressBar loader;

    CustomListAdapter1 adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView=inflater.inflate(R.layout.leaderboard,container,false);

        Log.i("Hello","Hii");




        return myView;
    }

    @Override
    public void onAttach(final Context context) {
        super.onAttach(context);

        Log.i("Hello","Hii");

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("Hello","Hii");
        myDatabase=FirebaseDatabase.getInstance();

        loader=(ProgressBar)getActivity().findViewById(R.id.progressBar1);
        loader.setVisibility(View.VISIBLE);
        Log.i("Hello","Hii");
        Users=myDatabase.getReference().child("Users");
        Log.i("Hello","Hii");

        myChildListener=new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                category.setVisibility(View.GONE);
                loader.setVisibility(View.VISIBLE);

                //Log.i("Hello","Hii");
                try {
                    User user= dataSnapshot.getValue(User.class);
                    //Log.i("Hello","Hii");
                    users.add(user);
                    //Log.i("Hello","Hii");
                    Log.i("UserName",user.getUsername());
                    Log.i("Score",user.getScore()+"");
                    //Log.i("Hello","Hii");

                    name1.add(user.getUsername());
                    //Log.i("Hello","Hii");
                    score.add(user.getScore());
                    //Log.i("Hello","Hii");
                    loader=(ProgressBar)getActivity().findViewById(R.id.progressBar1);
                    loader.setVisibility(View.GONE);
                    //adapter.notifyDataSetChanged();
                    //ListView category=(ListView)getView().findViewById(R.id.categoriesList);
                    sort();
                    category.setVisibility(View.VISIBLE);

                }catch (Exception e)
                {

                    System.out.print(e);
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
        Users.addChildEventListener(myChildListener);

        Log.i("Hello","Hii");









        adapter=new CustomListAdapter1(getActivity(),name1,score);


        category=(ListView)getView().findViewById(R.id.categoriesList);
        category.setAdapter(adapter);

        category.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                try {


                    String Selecteditem = name1.get(position);

                    /*ques = myQuiz.get(position).getQuestions();
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
                    Log.i("Done 13","Successful");*/

                    Toast.makeText(getActivity(), Selecteditem, Toast.LENGTH_SHORT).show();
                }catch (Exception e)
                {
                    e.printStackTrace();
                }


            }
        });
    }

    public void sort()
    {
        boolean sort=false;
        do {
            sort=true;
            for(int x=0;x<score.size()-1;x++)
            {
                if(score.get(x+1)>score.get(x))
                {
                    int temp=score.get(x+1);
                    score.set(x+1,score.get(x));
                    score.set(x,temp);

                    String temp1=name1.get(x+1);
                    name1.set(x+1,name1.get(x));
                    name1.set(x,temp1);
                    sort=false;
                }
            }




        }while(!sort);

        adapter.notifyDataSetChanged();
    }
}
