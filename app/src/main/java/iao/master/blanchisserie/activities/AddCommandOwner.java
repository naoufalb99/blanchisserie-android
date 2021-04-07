package iao.master.blanchisserie.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import iao.master.blanchisserie.R;
import iao.master.blanchisserie.models.Clients;

public class AddCommandOwner extends AppCompatActivity {
    String nom,email,phone;
    Boolean subscribed;


    RadioGroup radioGroup;
    RadioButton radioButton;

    TextInputLayout nomInput,emailInput,phoneInput;

    Button continuer,annuler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_command_owner);

        radioGroup = findViewById(R.id.radioGroup);


         nomInput = findViewById(R.id.nomClient);
         emailInput = findViewById(R.id.clientEmail);
         phoneInput = findViewById(R.id.clientPhone);

        continuer = (Button) findViewById(R.id.continuer);
        annuler = (Button) findViewById(R.id.annuler);


    }

    @Override
    protected void onStart() {
        super.onStart();
        continuer.setOnClickListener(v -> {

            nom = nomInput.getEditText().getText().toString();
            email = emailInput.getEditText().getText().toString();
            phone = phoneInput.getEditText().getText().toString();

            if(nom.isEmpty() || phone.isEmpty()  || email.isEmpty()){
                Toast.makeText(getApplicationContext(),"please fill in all the fields",Toast.LENGTH_SHORT).show();
            }else{
                Clients client = new Clients(nom,email,phone,subscribed);
            }
        });

        annuler.setOnClickListener(v -> {
            startActivity(new Intent(this, HomeActivity.class));
        });
    }

    public void checked(View v){
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        if(radioButton.getText().toString().equals("abonne"))
            subscribed=true;
        else
            subscribed=false;
    }
}