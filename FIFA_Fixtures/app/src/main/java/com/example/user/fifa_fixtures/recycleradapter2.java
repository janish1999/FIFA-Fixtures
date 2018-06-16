package com.example.user.fifa_fixtures;


import android.app.Activity;
import android.arch.persistence.room.ColumnInfo;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class recycleradapter2 extends RecyclerView.Adapter<recycleradapter2.viewHolder> {


    ArrayList<fixture> match;
    private Context context;
    Activity activity;

    public recycleradapter2(ArrayList<fixture> match, Context context,Activity activity) {
        this.match = match;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       // mcontext=context;//parent.getContext();
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.fixture2,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        holder.country1.setText(match.get(position).getCountry1());
        holder.country2.setText(match.get(position).getCountry2());
        holder.time.setText(match.get(position).getTime());
        holder.date.setText(match.get(position).getDate());
        holder.venue.setText(match.get(position).getVenue());
        holder.icon1.setImageURI(match.get(position).getIcon1());
        holder.icon2.setImageURI(match.get(position).getIcon2());
    }

    @Override
    public int getItemCount() {
        return match.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        TextView country1,country2,date,venue,time;
        ImageView icon1,icon2;
        RelativeLayout parent;

        public viewHolder(View itemView) {
            super(itemView);
            country1=itemView.findViewById(R.id.country1);
            country2=itemView.findViewById(R.id.country2);
            date=itemView.findViewById(R.id.date);
            venue=itemView.findViewById(R.id.venue);
            time=itemView.findViewById(R.id.time);
            icon1=itemView.findViewById(R.id.icon1click);
            icon2=itemView.findViewById(R.id.icon2);
            parent=itemView.findViewById(R.id.parent);
        }
    }
}

