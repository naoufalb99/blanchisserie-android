package iao.master.blanchisserie.activities;


import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import iao.master.blanchisserie.R;
import iao.master.blanchisserie.daos.BlanchisserieDao;
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
        Database db = Database.getInstance(this);
        BlanchisserieDao blanchisserieDao = db.settingsDao();



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

            if(nom.isEmpty() || adresse.isEmpty() || numero.isEmpty() || email.isEmpty()){
                Toast.makeText(getApplicationContext(),"please fill in all the fields",Toast.LENGTH_SHORT).show();
            }else{
                blanchisserieDao.insertSetting(nom,adresse,email,numero);
                startActivity(new Intent(this, HomeActivity.class));

            }



        });

        annuler = (Button) findViewById(R.id.annuler);
        annuler.setOnClickListener(v -> {
            startActivity(new Intent(this, WelcomeActivity.class));
        });
    }
}
