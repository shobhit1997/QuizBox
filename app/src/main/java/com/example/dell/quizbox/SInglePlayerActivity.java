package com.example.dell.quizbox;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;


public class SInglePlayerActivity extends AppCompatActivity {

    ArrayList<Integer> icon=new ArrayList<>();
    ArrayList<String> name=new ArrayList<>();
    ArrayList<String> apis=new ArrayList<>();
   static ArrayList<String> answers=new ArrayList<>();
    static ArrayList<String> questions=new ArrayList<>();
    static ArrayList<String[]> options=new ArrayList<>();
    String selecteditem;
    private ProgressBar loader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_player);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        loader=(ProgressBar)findViewById(R.id.progressBar1);
        loader.setVisibility(View.GONE);
        Intent i=getIntent();

        icon.add(R.drawable.film);
        icon.add(R.drawable.cartoons);
        icon.add(R.drawable.comics);
        icon.add(R.drawable.book);
        icon.add(R.drawable.celebrities);
        icon.add(R.drawable.music);
        icon.add(R.drawable.science);
        icon.add(R.drawable.gadgets);
        icon.add(R.drawable.sports);
        icon.add(R.drawable.vehicles);
        name.add("Films");
        name.add("Cartoons");
        name.add("Comics");
        name.add("Books");
        name.add("Celebrities");
        name.add("Music");
        name.add("Science");
        name.add("Gadgets");
        name.add("Sports");
        name.add("Vehicles");
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

        CustomListAdapter adapter=new CustomListAdapter(this,name,icon);


        final ListView category=(ListView)findViewById(R.id.categories);
        category.setAdapter(adapter);

        category.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                selecteditem= name.get(position);
                questions.clear();
                answers.clear();
                options.clear();


                loader.setVisibility(View.VISIBLE);
                category.setVisibility(View.GONE);
                DownloadTask downloadTask=new DownloadTask();
                downloadTask.execute(apis.get(position));




                Toast.makeText(getApplicationContext(), selecteditem, Toast.LENGTH_SHORT).show();

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

                    loader=(ProgressBar)findViewById(R.id.progressBar1);
                    loader.setVisibility(View.GONE);
                    Intent intent=new Intent(getApplicationContext(),mcq.class);
                    intent.putExtra("category",selecteditem);
                    startActivity(intent);
                    ListView category=(ListView)findViewById(R.id.categories);
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
  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.score) {
            String string="";
            SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.dell.quizbox", MODE_PRIVATE);
            for(int x=0;x<name.size();x++) {

                int score = sharedPreferences.getInt(name.get(x), 0);
                string+=name.get(x)+"\t:\t"+score+"\n";
            }


            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle("High Scores")
                    .setMessage(string)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // continue with delete
                        }
                    })

                    .setIcon(R.drawable.leaderblack);
            AlertDialog alertDialog = builder.create();
            alertDialog.show();


            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
