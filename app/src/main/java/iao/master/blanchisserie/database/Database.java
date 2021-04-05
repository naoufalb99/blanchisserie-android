package iao.master.blanchisserie.database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import iao.master.blanchisserie.daos.SettingsDao;
import iao.master.blanchisserie.models.Settings;


@androidx.room.Database(entities = {Settings.class}, version = 1)
public abstract class Database extends RoomDatabase {
    private static Database db;

    public static synchronized Database getInstance(Context context){
        if(db == null){
            db = Room.databaseBuilder(context,
                    Database.class, "Blanchisserie").allowMainThreadQueries().build();
        }
        return db;
    }

    public abstract SettingsDao settingsDao();


}
