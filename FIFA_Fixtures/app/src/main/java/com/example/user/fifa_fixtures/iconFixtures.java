package com.example.user.fifa_fixtures;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class iconFixtures extends AppCompatActivity {
    ArrayList<fixture> match = new ArrayList<>();
    recycleradapter2 adapter;
    Activity activity;
    String count1;
    String count2;
    int ic;
    public static myDatabase database;
    List<DataManager> dataManagerList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.iconfixtures);
        ic=getIntent().getIntExtra("flag",-1);
        database= Room.databaseBuilder(getApplicationContext(),myDatabase.class,"Fixture").allowMainThreadQueries().build();
        if(ic==0) {
            count1=getIntent().getStringExtra("country1");
            dataManagerList = database.dataAccessObject().fix(count1);
        }
         if(ic==1)
         {count2=getIntent().getStringExtra("country2");
            dataManagerList=database.dataAccessObject().fix(count2);}
        for(DataManager dataManager : dataManagerList){
            String country1=dataManager.getCountry1();
            String country2=dataManager.getCountry2();
            String Time=dataManager.getTime();
            String Venue=dataManager.getVenue();
            String Date=dataManager.getDate();
            String icon1=dataManager.getIcon1();
            String icon2=dataManager.getIcon2();
            Uri img1;
            Uri img2;
            if(icon1.equals("NULL"))
                img1=null;
            else
                img1=Uri.parse(icon1);
            if(icon2.equals("NULL"))
                img2=null;
            else
                img2=Uri.parse(icon2);
            match.add(new fixture(country1,country2,Date,Time,Venue,img1,img2));
            RecyclerView recyclerView=findViewById(R.id.recylce);
            RecyclerAdapter adapter= new RecyclerAdapter(match,this,activity);
            recyclerView.setAdapter(adapter);

        }
        RecyclerView recyclerView=findViewById(R.id.recylce);
        RecyclerAdapter adapter= new RecyclerAdapter(match,this,activity);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
