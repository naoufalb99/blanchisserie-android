package iao.master.blanchisserie.activities;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import iao.master.blanchisserie.R;
import iao.master.blanchisserie.daos.SettingsDao;
import iao.master.blanchisserie.database.Database;
import iao.master.blanchisserie.models.Settings;


public class AdminSignUpActivity extends AppCompatActivity {


    //inputs
    String nom, adresse, numero, email;
    EditText nomInput, adresseInput,numeroInput,emailInput;
    Button enregistrer, annuler;

    //settings var
    Settings setting;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //show the activity in full screen

        //database params
        Database db = Room.databaseBuilder(getApplicationContext(),
                Database.class, "Blanchisserie").allowMainThreadQueries().build();
        SettingsDao settingsDao = db.settingsDao();


        setContentView(R.layout.activity_signup);
        System.out.println("signup activity");

        nomInput = (EditText) findViewById(R.id.nomBlanchisserie);
        adresseInput = (EditText) findViewById(R.id.adresseBlanchisserie);
        numeroInput = (EditText) findViewById(R.id.numeroBlanchisserie);
        emailInput = (EditText) findViewById(R.id.emailBlanchisserie);

        enregistrer = (Button) findViewById(R.id.enregistrer);
        annuler = (Button) findViewById(R.id.annuler);



        enregistrer.setOnClickListener(v -> {
            nom = nomInput.getText().toString();
            adresse = adresseInput.getText().toString();
            numero = numeroInput.getText().toString();
            email = emailInput.getText().toString();



            settingsDao.insertSetting(nom,adresse,email,numero);

        });

        annuler = (Button) findViewById(R.id.annuler);
        annuler.setOnClickListener(v -> {
            startActivity(new Intent(this, WelcomeActivity.class));
        });
    }
}
