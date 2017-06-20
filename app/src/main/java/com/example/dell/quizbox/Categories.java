package com.example.dell.quizbox;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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

import java.util.ArrayList;

/**
 * Created by DELL on 6/20/2017.
 */

public class Categories extends Fragment {

    View myView;
    ArrayList<Integer> icon1=new ArrayList<>();
    ArrayList<String> name1=new ArrayList<>();


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

        CustomListAdapter adapter=new CustomListAdapter(getActivity(),name1,icon1);


        ListView category=(ListView)getView().findViewById(R.id.categoriesList);
        category.setAdapter(adapter);

        category.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                String Selecteditem= name1.get(position);

                Toast.makeText(getActivity(), Selecteditem, Toast.LENGTH_SHORT).show();

            }
        });
    }
}
