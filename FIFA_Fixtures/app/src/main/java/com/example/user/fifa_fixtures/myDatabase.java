package com.example.user.fifa_fixtures;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;


@Database(entities = {DataManager.class},version = 1  )
public abstract class myDatabase extends RoomDatabase {
    public abstract DataAccessObject dataAccessObject();
}
