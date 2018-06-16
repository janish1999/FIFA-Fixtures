package com.example.user.fifa_fixtures;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface DataAccessObject {

    @Insert
    public void insertfixture(DataManager dataManager);

    @Query("select * from Fixture")
    public List<DataManager> getFixture();

    @Query("delete from Fixture where country1 =:count1 and country2 =:count2 and date=:date")
    public void delete(String count1,String count2,String date);

    @Query("UPDATE Fixture SET country1 = :count1 , country2=:count2 , date=:date , time=:time,venue=:venue,icon1=:icon1,icon2=:icon2 WHERE date=:original1 and country1 =:original2 and country2 =:original3")
    public void update(String count1,String count2,String date,String time,String venue,String icon1,String icon2,String original1,String original2,String original3);


    @Query("select * from Fixture where country1=:count1 or country2=:count1")
    public List<DataManager> fix(String count1);

    @Query("select icon1 from Fixture where country1 = :count1 and country2=:count2 and date=:date and time=:time and venue=:venue")
    public String getIcon1(String count1,String count2,String date,String time,String venue);

    @Query("select icon2 from Fixture where country1 = :count1 and country2=:count2 and date=:date and time=:time and venue=:venue")
    public String getIcon2(String count1,String count2,String date,String time,String venue);
}
