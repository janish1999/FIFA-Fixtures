package com.example.user.fifa_fixtures;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
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

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.viewHolder> {
    Dialogedit dialogedit= new Dialogedit();
    ArrayList<fixture> match;
    private Context context;
    Context mcontext;
    Activity activity;
    String country1;
    String country2;
    String time;
    String date;
    String venue;
    String uri1 ;
    Uri icon1;
    String uri2;
    Uri icon2 ;
    View view;
    int ic=0;
    int k;
    private static int position1;



    public RecyclerAdapter(ArrayList<fixture> match, Context context,Activity activity)  {
        this.activity=activity;
        this.match = match;
        this.context = context;
    }

    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mcontext=context;//parent.getContext();
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new viewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final viewHolder holder,  final int position) {
        mcontext=context;

        holder.country1.setText(match.get(position).getCountry1());
        holder.country2.setText(match.get(position).getCountry2());
        holder.time.setText(match.get(position).getTime());
        holder.date.setText(match.get(position).getDate());
        holder.venue.setText(match.get(position).getVenue());
        holder.icon1.setImageURI(match.get(position).getIcon1());
        holder.icon2.setImageURI(match.get(position).getIcon2());
        final fixture data=match.get(position);
        Intent intent = new Intent(context,Dialogedit.class);
        country1=match.get(position).getCountry1();
        country2=match.get(position).getCountry2();
        time=match.get(position).getTime();
        date=match.get(position).getDate();
        venue=match.get(position).getVenue();
        icon1=match.get(position).getIcon1();
        icon2=match.get(position).getIcon2();
        final fixture fixture=new fixture(country1,country2,date,time,venue,icon1,icon2);
        holder.date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.parent.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Bundle args=new Bundle();
                args.putInt("position",position);
                args.putSerializable("fixture",fixture);

                FragmentManager manager = ((Activity) context).getFragmentManager();
                Dialog_edit_delete dialog_edit_delete = new Dialog_edit_delete();
                dialog_edit_delete.setArguments(args);
                dialog_edit_delete.show(manager,"edit_delete");
                return false;
            }
        });
        holder.icon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ic=0;
                Intent intent=new Intent(context,iconFixtures.class);
                intent.putExtra("country1",match.get(position).getCountry1());
                intent.putExtra("flag",ic);
                context.startActivity(intent);
            }
        });
        holder.icon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ic=1;
                Intent intent=new Intent(context,iconFixtures.class);
                intent.putExtra("country2",match.get(position).getCountry2());
                intent.putExtra("flag",ic);
                context.startActivity(intent);
            }
        });


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
    public int returnposition(){
        return position1;
    }


}