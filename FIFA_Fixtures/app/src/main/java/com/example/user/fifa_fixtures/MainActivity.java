package com.example.user.fifa_fixtures;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;
public class MainActivity extends AppCompatActivity {
    String country1;
    String country2;
    String time;
    String date;
    String venue;
    String uri1;
    String currntPhotopath;
    Uri icon1;
    String uri2;
    Uri icon2;
    Activity activity;
    Bitmap A;
    Bitmap B;


    public static myDatabase database;
    ArrayList<fixture> match = new ArrayList<>();
    FloatingActionButton floatingActionButton;
    RecyclerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       database= Room.databaseBuilder(getApplicationContext(),myDatabase.class,"Fixture").allowMainThreadQueries().build();
       if(doesDatabaseExist(this,"Fixture")){
           List<DataManager> dataManagerList=database.dataAccessObject().getFixture();
        for(DataManager dataManager : dataManagerList){
            String country1=dataManager.getCountry1();
            String country2=dataManager.getCountry2();
            String Time=dataManager.getTime();
            String Venue=dataManager.getVenue();
            String Date=dataManager.getDate();
            String icon1=dataManager.getIcon1();
            String icon2=dataManager.getIcon2();
            Uri img1,img2;
            if(icon1.equals("NULL"))
                img1=null;
            else
            img1=Uri.parse(icon1);
            if(icon2.equals("NULL"))
                img2=null;
            else
                img2=Uri.parse(icon2);
            match.add(new fixture(country1,country2,Date,Time,Venue,img1,img2));
            RecyclerView recyclerView=findViewById(R.id.view);
            RecyclerAdapter adapter= new RecyclerAdapter(match,this,activity);
            recyclerView.setAdapter(adapter);

        }
}else{
           add();
       }
        floatingActionButton=findViewById(R.id.floatbutton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getApplicationContext(), Add_Fixture.class),123);
            }
        });
        RecyclerView recyclerView=findViewById(R.id.view);
        adapter= new RecyclerAdapter(match,this,activity);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 123 && resultCode == RESULT_OK){
           String country1=data.getStringExtra("Country1");
            String country2=data.getStringExtra("Country2");
            String time=data.getStringExtra("time");
            String date=data.getStringExtra("date");
            String venue=data.getStringExtra("venue");
            String uri1 = data.getStringExtra("uri1");
            String uri2 = data.getStringExtra("uri2");
            if(uri1.equals("NULL"))
                icon1=null;
            else {
                icon1 = Uri.parse(uri1);
                try {
                    A = getBitmapFromUri(icon1);
                } catch (IOException e) {
                    Log.e("Error", "Error occured");
                }
                icon1= bitmapToUriConverter(A);
            }
            if(uri2.equals("NULL"))
                icon2=null;
            else {
                icon2 = Uri.parse(uri2);
                try {
                    B = getBitmapFromUri(icon2);
                } catch (IOException e) {
                    Log.e("Error", "Error occured");
                }
                 icon2=bitmapToUriConverter(B);
            }
           match.add(new fixture(country1,country2,date,time,venue,icon1,icon2));
           RecyclerView recyclerView=findViewById(R.id.view);
            RecyclerAdapter adapter= new RecyclerAdapter(match,this,activity);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();

        }

    }
    private Bitmap getBitmapFromUri(Uri uri) throws IOException {
        ParcelFileDescriptor parcelFileDescriptor =
                getContentResolver().openFileDescriptor(uri, "r");
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        parcelFileDescriptor.close();
        return image;
    }
    public Uri bitmapToUriConverter(Bitmap mBitmap) {
        Uri uri = null;
        try {
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = false;
            Bitmap newBitmap = Bitmap.createScaledBitmap(mBitmap, 200, 200,
                    true);
            File file = new File(getFilesDir(), "Image"
                    + new Random().nextInt() + ".jpeg");
            FileOutputStream out = openFileOutput(file.getName(),
                    Context.MODE_PRIVATE);
            newBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
            String realPath = file.getAbsolutePath();
            File f = new File(realPath);
            uri = Uri.fromFile(f);

        } catch (Exception e) {
            Log.e("Your Error Message", e.getMessage());
        }
        return uri;
    }
    public void add(){
        Uri a=null,b=null;
        match.clear();
        match.add(new fixture("Russia","Saudi Arabia","14/06/18","08:30pm","Luzhniki Stadium",a,b));
        match.add(new fixture(	"Egypt","Uruguay","15/06/18","05:30pm","Ekaterinburg",a,b));
        match.add(new fixture("Morocco","Iraq","15/06/18","08:30pm","St Petersburg",a,b));
        match.add(new fixture("Portugal","Spain","15/06/18","11:30pm","Sochi",a,b));
        match.add(new fixture("France","Australia","16/06/18","03:30pm","Kazan",a,b));
        match.add(new fixture("Argentina","Iceland	","16/06/18","06:30pm","partak Stadium",a,b));
        match.add(new fixture("Peru","Denmark","16/06/18","09:30pm","Saransk",a,b));
        match.add(new fixture("Croatia","Nigeria","17/06/18","12:30am","Kaliningrad",a,b));
         for(int i=0;i<8;i++){
          DataManager data=new DataManager();
                    data.setCountry1(match.get(i).getCountry1());
                    data.setCountry2(match.get(i).getCountry2());
                    data.setDate(match.get(i).getDate());
                    data.setTime(match.get(i).getTime());
                    data.setVenue(match.get(i).getVenue());
                    data.setIcon1("NULL");//match.get(i).getIcon1().toString());
                    data.setIcon2("NULL");//match.get(i).getIcon2().toString());
                    database.dataAccessObject().insertfixture(data);
    }}
    private static boolean doesDatabaseExist(Context context, String dbName) {
    File dbFile = context.getDatabasePath(dbName);
    return dbFile.exists();
}
public void delete(int position){
    String c1=match.get(position).getCountry1();
    String c2=match.get(position).getCountry2();
    String dat=match.get(position).getDate();
    database.dataAccessObject().delete(c1,c2,dat);
    match.remove(position);
    Toast.makeText(this,"Deleted",Toast.LENGTH_SHORT).show();
    //adapter.notifyDataSetChanged();
    RecyclerView recyclerView=findViewById(R.id.view);
    RecyclerAdapter adapter= new RecyclerAdapter(match,this,activity);
    recyclerView.setAdapter(adapter);
    }
public void editfixture(String country1, String country2, String date, String time, String venue, Uri icon1, Uri icon2,int position){
    match.get(position).setCountry1(country1);
    match.get(position).setCountry2(country2);
    match.get(position).setTime(time);
    match.get(position).setVenue(venue);
    match.get(position).setDate(date);
    match.get(position).setIcon1(icon1);
    match.get(position).setIcon2(icon2);
    RecyclerView recyclerView=findViewById(R.id.view);
    RecyclerAdapter adapter= new RecyclerAdapter(match,this,activity);
    recyclerView.setAdapter(adapter);
    adapter.notifyDataSetChanged();

}
}

