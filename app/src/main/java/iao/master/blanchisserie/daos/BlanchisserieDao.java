package iao.master.blanchisserie.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

import iao.master.blanchisserie.models.ArticleCommand;
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
    public void insertSetting(String nom, String adresse, String email, String numero);

    @Update
    public void updateSetting(Settings setting);

    @Query("DELETE FROM settings")
    public void deleteSettings();

    @Query("SELECT * FROM settings")
    public Settings[] getAllSettings();


    @Query("select * from clients")
    public Clients[] getAllClients();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Long insertClient(Clients client);

    @Update
    public void updateClient(Clients client);

    @Delete
    public void deleteClient(Clients client);


    //commands functions
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Long insertCommand(Commands command);

    @Update
    public void updateCommand(Commands command);

    @Delete
    public void deleteCommand(Commands command);

    //articles functions
    @Query("select * from articles")
    public List<Articles> getAllArticles();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertArticle(Articles article);

    @Update
    public void updateArticle(Articles article);

    @Delete
    public void deleteArticle(Articles article);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertArticleCommands(List<ArticleCommand> articleCommands);

    //transactions
    @Transaction
    @Query("SELECT * FROM clients")
    public List<ClientWithCommands> getClientWithCommands();

    @Transaction
    @Query("SELECT * FROM commands ")
    public List<CommandWithArticles> getCommandWithArticles();


}
