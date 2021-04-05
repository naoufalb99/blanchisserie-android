package iao.master.blanchisserie.database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import iao.master.blanchisserie.daos.BlanchisserieDao;
import iao.master.blanchisserie.models.ArticleCommand;
import iao.master.blanchisserie.models.Articles;
import iao.master.blanchisserie.models.ClientWithCommands;
import iao.master.blanchisserie.models.Clients;
import iao.master.blanchisserie.models.CommandWithArticles;
import iao.master.blanchisserie.models.Commands;
import iao.master.blanchisserie.models.Settings;


@androidx.room.Database(entities = {Settings.class, ArticleCommand.class, Articles.class, Clients.class, Commands.class}, version = 4,exportSchema = false)
public abstract class Database extends RoomDatabase {
    private static Database db;

    public static synchronized Database getInstance(Context context) {
        if (db == null) {
            db = Room.databaseBuilder(context,
                    Database.class, "Blanchisserie")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return db;
    }

    public abstract BlanchisserieDao settingsDao();


}
