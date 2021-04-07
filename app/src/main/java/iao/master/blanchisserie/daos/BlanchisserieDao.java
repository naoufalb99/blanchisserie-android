package iao.master.blanchisserie.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

import iao.master.blanchisserie.models.Articles;
import iao.master.blanchisserie.models.ClientWithCommands;
import iao.master.blanchisserie.models.Clients;
import iao.master.blanchisserie.models.CommandWithArticles;
import iao.master.blanchisserie.models.Commands;
import iao.master.blanchisserie.models.Settings;

@Dao
public interface BlanchisserieDao {

    //settings functions
    @Query("INSERT INTO settings (nomBlanchisserie, adresse, email, numeroTelephone) VALUES (:nom,:adresse,:email,:numero)")
    public void insertSetting(String nom, String adresse,String email, String numero);
    @Update
    public void updateSetting(Settings setting);
    @Query("DELETE FROM settings")
    public void deleteSettings();
    @Query("SELECT * FROM settings")
    public Settings[] getAllSettings();

    //clients functions
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertClient(Clients client);
    @Update
    public void updateClient(Clients client);
    @Delete
    public void deleteClient (Clients client);

    //commands functions
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertCommand(Commands command);
    @Update
    public void updateCommand(Commands command);
    @Delete
    public void deleteCommand (Commands command);

    //articles functions
    @Query("select * from articles")
    public Articles[] getAllArticles();
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertArticle(Articles article);
    @Update
    public void updateArticle(Articles article);
    @Delete
    public void deleteArticle (Articles article);




    //transactions
    @Transaction
    @Query("SELECT * FROM clients")
    public List<ClientWithCommands> getClientWithCommands();

    @Transaction
    @Query("SELECT * FROM commands ")
    public List<CommandWithArticles> getCommandWithArticles();


}