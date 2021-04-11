package iao.master.blanchisserie.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import iao.master.blanchisserie.daos.BlanchisserieDao;
import iao.master.blanchisserie.database.Database;
import iao.master.blanchisserie.models.Articles;
import iao.master.blanchisserie.models.Settings;

public class MainActivity extends AppCompatActivity {

    Database db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.db = Database.getInstance(getApplicationContext());
    }

    @Override
    protected void onStart() {
        super.onStart();


        activitiesScheduling();
        setDefaultArticles();
    }

    private void activitiesScheduling() {
        BlanchisserieDao blanchisserieDao = db.blanchisserieDao();
        Settings[] settings = blanchisserieDao.getAllSettings();

        if(settings == null || settings.length == 0) {
            startWelcomeActivity();
        }else {
            startHomeActivity();
        }
    }

    private void startWelcomeActivity() {
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
    }

    private void startHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    private void setDefaultArticles(){
        BlanchisserieDao blanchisserieDao = db.blanchisserieDao();
        List<Articles> articlesPresent = blanchisserieDao.getAllArticles();

        if(articlesPresent == null || articlesPresent.size() == 0 ){
            List<Articles> articles = new ArrayList<>();
            Articles pantalon = new Articles("pantalon",20f,"image_pantalon");
            articles.add(pantalon);
            Articles chemise = new Articles("chemise",15f,"image_chemise");
            articles.add(chemise);
            Articles jacket = new Articles("jacket",30f,"image_jacket");
            articles.add(jacket);
            Articles tricot = new Articles("tricot",25f,"image_tricot");
            articles.add(tricot);

            for(Articles article : articles){
                blanchisserieDao.insertArticle(article);
            }
        }



    }
}
