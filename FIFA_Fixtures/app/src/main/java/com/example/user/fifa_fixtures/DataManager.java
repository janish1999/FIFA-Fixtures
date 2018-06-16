package com.example.user.fifa_fixtures;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

@Entity(tableName = "Fixture")
public class DataManager {
    @PrimaryKey(autoGenerate = true)
    int id;

    @ColumnInfo(name = "country1")
    private String country1;
    @ColumnInfo(name = "country2")
    private String country2;
    @ColumnInfo(name = "time")
    private String time;
    @ColumnInfo(name = "date")
    private String date;
    @ColumnInfo(name = "venue")
    private String venue;
    @ColumnInfo(name = "icon1")
    private  String icon1;
    @ColumnInfo(name = "icon2")
    private String icon2;

    public String getIcon1() {
        return icon1;
    }

    public void setIcon1(String icon1) {
        this.icon1 = icon1;
    }

    public String getIcon2() {
        return icon2;
    }

    public void setIcon2(String icon2) {
        this.icon2 = icon2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry1() {
        return country1;
    }

    public void setCountry1(String country1) {
        this.country1 = country1;
    }

    public String getCountry2() {
        return country2;
    }

    public void setCountry2(String country2) {
        this.country2 = country2;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }
}
   /* private SQLiteDatabase db;
    public static final String TABLE_ROW_ID="_id";
    public static final String TABLE_ROW_COUNTRY1="country1";
    public static final String TABLE_ROW_COUNTRY2="country2";
    public static final String TABLE_ROW_TIME="time";
    public static final String TABLE_ROW_VENUE="venue";
    public static final String TABLE_ROW_DATE="date";
    public static final String DB_NAME="Match_Fixture_Db";
    public static final int DB_VERSION=1;
    public static final String TABLE_MATCH="match_fixed";


    public DataManager(Context context){
        CustomSQLiteOpenHelper helper=new CustomSQLiteOpenHelper(context);
        db=helper.getWritableDatabase();
    }
    public void insert(String country1,String country2,String time,String date,String venue){
        String query= "INSERT INTO" + TABLE_MATCH + "(" + TABLE_ROW_COUNTRY1 + "," + TABLE_ROW_COUNTRY2 + "," + TABLE_ROW_TIME + "," + TABLE_ROW_DATE + "," + TABLE_ROW_VENUE +")" + "VALUES("+
                      "'" + country1 + "'" + "," + "'" + country2 + "'" + "," +"'" + time+"'"+","+"'"+date+"'"+","+"'"+venue+"'"+");";
        Log.i("insert()=",query);
        db.execSQL(query);

    }
    public Cursor selectAll(){
        //Cursor c=db.rawQuery()
       // return c;
    }
    public class CustomSQLiteOpenHelper extends SQLiteOpenHelper{

        public CustomSQLiteOpenHelper(Context context) {
            super(context,DB_NAME,null,DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

        }
    }


}*/
