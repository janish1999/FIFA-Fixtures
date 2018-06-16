package com.example.user.fifa_fixtures;

import android.Manifest;
import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import static android.app.Activity.RESULT_OK;

public class Dialog_edit_delete extends DialogFragment {
    int position;
    fixture fixture;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Bundle Args=getArguments();
        position=Args.getInt("position");
        fixture= (com.example.user.fifa_fixtures.fixture) Args.getSerializable("fixture");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.dialogedit_delete,container,false);
        TextView edit=(TextView)view.findViewById(R.id.Edit);
        TextView delete=(TextView)view.findViewById(R.id.Delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Position",String.valueOf(position));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    ((MainActivity)getContext()).delete(position);
                }
                getDialog().dismiss();
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Log.d("coutnry1 ", fixture.getCountry1());
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    Intent intent = new Intent(getContext(), Dialogedit.class);
                    intent.putExtra("match", fixture);
                    startActivityForResult(intent, 101);
                }

            }
        });

        getDialog().dismiss();
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            if(requestCode ==101 && resultCode == RESULT_OK) {
        String country1 = data.getStringExtra("Country1");
        String country2 = data.getStringExtra("Country2");
        String time = data.getStringExtra("time");
        String date = data.getStringExtra("date");
        String venue = data.getStringExtra("venue");
        String uri1 = data.getStringExtra("uri1");
                Uri icon1;
                Uri icon2;
        if(uri1.equals("NULL"))
            icon1=null;
        else
            icon1 = Uri.parse(uri1);
        String uri2 = data.getStringExtra("uri2");
        if(uri2.equals("NULL"))
            icon2=null;
        else
            icon2 = Uri.parse(uri2);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                ((MainActivity)getContext()).editfixture(country1,country2,time,date,venue,icon1,icon2,position);
                getDialog().dismiss();
    }}}

}
