package com.example.user.fifa_fixtures;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Dialogedit extends AppCompatActivity implements Dialogoption.EditNameDialogListener{
    EditText country1,country2,venue,time,date;
    Button icon1click,icon2click,update;
    ImageView icon1,icon2;
    Uri image1=null,image2=null;
    Bitmap A,B,A1,B1;
    Uri i1,i2;
    String ic1,ic2;
    int i;
    @Nullable
    @Override
    public void onFinishEditDialog(String image) {
        if (i == 0) {
            image1 = Uri.parse(image);
              try{
                  A=getBitmapFromUri(image1);
                       }
                    catch (IOException e){
                        Log.e("error","ERROR");
                    }
                    image1=bitmapToUriConverter(A);
              icon1.setImageURI(image1);

        }
        if (i == 1) {
            image2 =Uri.parse(image);
              try{
                        B=getBitmapFromUri(image2);}
                    catch (IOException e){
                        Log.e("error","ERROR");
                    }
                    image2=bitmapToUriConverter(B);
              icon2.setImageURI(image2);
                    }
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialogedit);
        final fixture fixture=(fixture)getIntent().getSerializableExtra("match");
        country1=findViewById(R.id.country1);
        country2=findViewById(R.id.country2);
        venue=findViewById(R.id.venue);
        time=findViewById(R.id.time);
        date=findViewById(R.id.date);
        icon1click= findViewById(R.id.icon1click);
        icon2click= findViewById(R.id.icon2click);
        icon1=findViewById(R.id.icon1);
        icon2=findViewById(R.id.icon2);
        update= findViewById(R.id.update);
        final String count1=fixture.getCountry1();
        final String count2=fixture.getCountry2();
        final String datestr=fixture.getDate();
        String timestr=fixture.getTime();
        String venuestr=fixture.getVenue();
        country1.setText(count1,TextView.BufferType.EDITABLE);
        country2.setText(count2,TextView.BufferType.EDITABLE);
        venue.setText(venuestr,TextView.BufferType.EDITABLE);
        time.setText(timestr,TextView.BufferType.EDITABLE);
        date.setText(datestr,TextView.BufferType.EDITABLE);

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
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(image1== null) {
                    ic1="NULL";
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
                String checkdate=date.getText().toString();
                if(isThisDateValid(checkdate,"dd/MM/yyyy"))
                {MainActivity.database.dataAccessObject().update(country1.getText().toString(),country2.getText().toString(),date.getText().toString(),time.getText().toString(),venue.getText().toString(),ic1,ic2,datestr,count1,count2);
                    Intent intent=new Intent();
                    intent.putExtra("Country1",country1.getText().toString());
                    intent.putExtra("Country2",country2.getText().toString());
                    intent.putExtra("venue",venue.getText().toString());
                    intent.putExtra("time",time.getText().toString());
                    intent.putExtra("date",date.getText().toString());
                    intent.putExtra("uri1",ic1);
                    intent.putExtra("uri2",ic2);
                    setResult(RESULT_OK,intent);
                    finish();}
                    else
                        Toast.makeText(getApplicationContext(),"Enter a valid date",Toast.LENGTH_SHORT).show();
                }
        });
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
/* String ico1=MainActivity.database.dataAccessObject().getIcon1(count1,count2,datestr,timestr,venuestr);
       /* if(ico1.contentEquals("NULL")) {
            ;
        }else
            image1 = Uri.parse(ico1);
                    String ico2=MainActivity.database.dataAccessObject().getIcon2(count1,count2,datestr,timestr,venuestr);
        /*if(ico2.equals("NULL"))
            image2=null;
        else {
                    image2 = Uri.parse(ico2);

                    icon1.setImageURI(image1);
                    icon2.setImageURI(image2);*/
