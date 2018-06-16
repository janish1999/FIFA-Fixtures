package com.example.user.fifa_fixtures;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Add_Fixture extends AppCompatActivity implements Dialogoption.EditNameDialogListener {
    EditText country1,country2,venue,time,date;
    Button icon1click,icon2click,add;
    ImageView icon1,icon2;
    Uri image1,image2;
    String ic1,ic2;
    int i;
    int o=0;
    Bitmap A,B;


    @Override
    public void onFinishEditDialog(String image) {
        if (i == 0) {
            image1 = Uri.parse(image);
            icon1.setImageURI(Uri.parse(image));
        }
        if (i == 1) {
            icon2.setImageURI(Uri.parse(image));
            image2 =Uri.parse(image);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__fixture);
        country1=(EditText)findViewById(R.id.country1);
        country2=(EditText)findViewById(R.id.country2);
        venue=(EditText)findViewById(R.id.venue);
        time=(EditText)findViewById(R.id.time);
        date=(EditText)findViewById(R.id.date);
        icon1click=(Button)findViewById(R.id.icon1click);
        icon2click=(Button)findViewById(R.id.icon2click);
        icon1=(ImageView)findViewById(R.id.icon1);
        icon2=(ImageView)findViewById(R.id.icon2);
        add=(Button)findViewById(R.id.add);
        icon1click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 i=0;
                Dialogoption dialogoption=new Dialogoption();
                dialogoption.show(getFragmentManager(),"dialogoption");
            }
        });
        icon2click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 i=1;
                Dialogoption dialogoption=new Dialogoption();
                dialogoption.show(getFragmentManager(),"dialogoption");
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(image1== null) {
                    ic1="NULL";
                    //Toast.makeText(getApplicationContext(), "Add team icon to proceed", Toast.LENGTH_SHORT).show();
                }else {
                    try {
                        A=getBitmapFromUri(image1); } catch (IOException e){
                        Log.e("error","ERROR");
                    }
                    Uri path1=bitmapToUriConverter(A);
                    ic1=path1.toString();
                }
                if(image2==null){
                    ic2="NULL";
                }else{
                    try {
                        B=getBitmapFromUri(image2);} catch (IOException e){
                        Log.e("error","ERROR");
                    }
                    Uri path2=bitmapToUriConverter(B);
                    ic2=path2.toString();
                }
                    String count1=country1.getText().toString();
                    String count2=country2.getText().toString();
                    String datestr=date.getText().toString();
                    String timestr=time.getText().toString();
                    String venuestr=venue.getText().toString();
                    Time24hFormatValidat time24hFormatValidat=new Time24hFormatValidat();
                if(isThisDateValid(datestr,"dd/MM/yyyy") && time24hFormatValidat.validate(timestr)){
                    DataManager data=new DataManager();
                    data.setCountry1(count1);
                    data.setCountry2(count2);
                    data.setDate(datestr);
                    data.setTime(timestr);
                    data.setVenue(venuestr);
                    data.setIcon1(ic1);
                    data.setIcon2(ic2);

                    MainActivity.database.dataAccessObject().insertfixture(data);

                    gotomain();}
                    else
                        Toast.makeText(getApplicationContext(),"Enter Valid Date and Time",Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void gotomain(){
        Intent intent=new Intent();
        intent.putExtra("Country1",country1.getText().toString());
        intent.putExtra("Country2",country2.getText().toString());
        intent.putExtra("venue",venue.getText().toString());
        intent.putExtra("time",time.getText().toString());
        intent.putExtra("date",date.getText().toString());
        intent.putExtra("uri1",ic1);
        intent.putExtra("uri2",ic2);
        setResult(RESULT_OK,intent);
        finish();
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
    public boolean isThisDateValid(String dateToValidate, String dateFromat){

        if(dateToValidate == null){
            return false;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
        sdf.setLenient(false);

        try {

            //if not valid, it will throw ParseException
            Date date = sdf.parse(dateToValidate);
            System.out.println(date);

        } catch (ParseException e) {

            e.printStackTrace();
            return false;
        }

        return true;
    }


}

