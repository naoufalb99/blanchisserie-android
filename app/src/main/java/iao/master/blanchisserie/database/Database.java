package iao.master.blanchisserie.database;

import androidx.room.RoomDatabase;

import iao.master.blanchisserie.daos.SettingsDao;
import iao.master.blanchisserie.models.Settings;


@androidx.room.Database(entities = {Settings.class}, version = 1)
public abstract class Database extends RoomDatabase {

    public abstract SettingsDao settingsDao();

}
