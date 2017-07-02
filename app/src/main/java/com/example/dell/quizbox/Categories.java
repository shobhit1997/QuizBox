package com.example.dell.quizbox;

import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by DELL on 6/20/2017.
 */

public class Categories extends Fragment {

    View myView;
    ArrayList<Integer> icon1=new ArrayList<>();
    ArrayList<String> name1=new ArrayList<>();
    ArrayList<String> apis=new ArrayList<>();
    static ArrayList<String> answers=new ArrayList<>();
    static ArrayList<String> questions=new ArrayList<>();
    static ArrayList<String[]> options=new ArrayList<>();
    private ProgressBar loader;
    String selecteditem;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView=inflater.inflate(R.layout.categories,container,false);


        return myView;
    }

    @Override
    public void onAttach(final Context context) {
        super.onAttach(context);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loader=(ProgressBar)getActivity().findViewById(R.id.progressBar1);
        loader.setVisibility(View.INVISIBLE);
        icon1.add(R.drawable.film);
        icon1.add(R.drawable.cartoons);
        icon1.add(R.drawable.comics);
        icon1.add(R.drawable.book);
        icon1.add(R.drawable.celebrities);
        icon1.add(R.drawable.music);
        icon1.add(R.drawable.science);
        icon1.add(R.drawable.gadgets);
        icon1.add(R.drawable.sports);
        icon1.add(R.drawable.vehicles);
        name1.add("Films");
        name1.add("Cartoons");
        name1.add("Comics");
        name1.add("Books");
        name1.add("Celebrities");
        name1.add("Music");
        name1.add("Science");
        name1.add("Gadgets");
        name1.add("Sports");
        name1.add("Vehicles");
        apis.add(" https://opentdb.com/api.php?amount=10&category=11&type=multiple&encode=url3986");
        apis.add(" https://opentdb.com/api.php?amount=10&category=32&type=multiple&encode=url3986");
        apis.add(" https://opentdb.com/api.php?amount=10&category=29&type=multiple&encode=url3986");
        apis.add(" https://opentdb.com/api.php?amount=10&category=10&type=multiple&encode=url3986");
        apis.add(" https://opentdb.com/api.php?amount=10&category=26&type=multiple&encode=url3986");
        apis.add(" https://opentdb.com/api.php?amount=10&category=12&type=multiple&encode=url3986");
        apis.add(" https://opentdb.com/api.php?amount=10&category=17&type=multiple&encode=url3986");
        apis.add(" https://opentdb.com/api.php?amount=10&category=30&type=multiple&encode=url3986");
        apis.add(" https://opentdb.com/api.php?amount=10&category=21&type=multiple&encode=url3986");
        apis.add(" https://opentdb.com/api.php?amount=10&category=28&type=multiple&encode=url3986");


        CustomListAdapter adapter=new CustomListAdapter(getActivity(),name1,icon1);


        final ListView category=(ListView)getView().findViewById(R.id.categoriesList);
        category.setVisibility(View.VISIBLE);
        category.setAdapter(adapter);

        category.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                selecteditem= name1.get(position);
                questions.clear();
                answers.clear();
                options.clear();
                category.setVisibility(View.GONE);
                loader.setVisibility(View.VISIBLE);
                DownloadTask downloadTask=new DownloadTask();
                downloadTask.execute(apis.get(position));




                Toast.makeText(getActivity().getApplicationContext(), selecteditem, Toast.LENGTH_SHORT).show();

            }
        });
    }
    public class DownloadTask extends AsyncTask<String,Void,String>
    {


        @Override
        protected String doInBackground(String... urls) {


            String result=null;
            URL url;
            HttpURLConnection urlConnection=null;
            try {
                url=new URL(urls[0]);
                urlConnection=(HttpURLConnection) url.openConnection();
                InputStream in=urlConnection.getInputStream();
                InputStreamReader isr=new InputStreamReader(in);
                int data=isr.read();
                result="";
                while(data!=-1)
                {
                    char current=(char)data;
                    result+=current;
                    data=isr.read();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            try {
                JSONObject jsonObject=new JSONObject(result);
                JSONArray jsonArray=jsonObject.getJSONArray("results");
                for(int index=0;index<=9;index++) {
                    JSONObject obj = jsonArray.getJSONObject(index);
                    String ques = obj.getString("question");
                    String ans = obj.getString("correct_answer");
                    JSONArray wrngAns = obj.getJSONArray("incorrect_answers");
                    String WrongAns[] = {wrngAns.getString(0), wrngAns.getString(1), wrngAns.getString(2)};

                    ques = java.net.URLDecoder.decode(ques, "UTF-8");
                    ans = java.net.URLDecoder.decode(ans, "UTF-8");
                    WrongAns[0] = java.net.URLDecoder.decode(WrongAns[0], "UTF-8");
                    WrongAns[1] = java.net.URLDecoder.decode(WrongAns[1], "UTF-8");
                    WrongAns[2] = java.net.URLDecoder.decode(WrongAns[2], "UTF-8");
                    Random rand = new Random();
                    int ansId = rand.nextInt(4);
                    String opt[] = new String[4];
                    opt[ansId] = ans;
                    int i = 0;
                    for (int x = 0; x <= 3; x++) {
                        if (x == ansId) continue;
                        opt[x] = WrongAns[i++];
                    }
                    questions.add(ques);
                    answers.add(ans);
                    options.add(opt);
                    Log.i("Question", questions.get(index));
                    Log.i("Ans", answers.get(index));
                    Log.i("Option 1", options.get(index)[0]);
                    Log.i("Option 2", options.get(index)[1]);
                    Log.i("Option 3", options.get(index)[2]);
                    Log.i("Option 4", options.get(index)[3]);

                    loader=(ProgressBar)getActivity().findViewById(R.id.progressBar1);
                    loader.setVisibility(View.GONE);

                    Intent intent=new Intent(getActivity().getApplicationContext(),MCQ3.class);
                    intent.putExtra("category",selecteditem);
                    startActivity(intent);

                    ListView category=(ListView)getView().findViewById(R.id.categoriesList);
                    category.setVisibility(View.VISIBLE);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Log.i("Error","JSON");

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                Log.i("Error","Error1");


            }catch (Exception e)
            {
                e.printStackTrace();
                Log.i("Error","Error1");
            }
            //Log.i("Content",result);
        }
    }
}
