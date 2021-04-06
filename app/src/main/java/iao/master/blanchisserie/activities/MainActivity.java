package iao.master.blanchisserie.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
        Articles[] articlesPresent = blanchisserieDao.getAllArticles();

        if(articlesPresent == null || articlesPresent.length == 0 ){
            List<Articles> articles = new ArrayList<>();
            Articles pantalon = new Articles("pantalon",20f,"imagePantalon");
            articles.add(pantalon);
            Articles chemise = new Articles("chemise",15f,"imageChemise");
            articles.add(chemise);
            Articles jacket = new Articles("jacket",30f,"imageJacket");
            articles.add(jacket);
            Articles tricot = new Articles("tricot",25f,"imageTricot");
            articles.add(tricot);

            for(Articles article : articles){
                blanchisserieDao.insertArticle(article);
            }
        }



    }
}
