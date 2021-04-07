package iao.master.blanchisserie.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import iao.master.blanchisserie.R;
import iao.master.blanchisserie.models.Clients;

public class AddCommandActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_command);

        Clients commandOwner = getOwner(getIntent().getExtras());
        TextView t = (TextView) findViewById(R.id.test);
        if(commandOwner.isSubscribed())
        t.setText("yes");
        else
            t.setText("no");

    }





    public Clients getOwner(Bundle extras){
        Bundle clientBundle = extras.getBundle("commandOwner");
        Clients owner = new Clients();

        owner.setName(clientBundle.getString("nomClient"));
        owner.setEmail(clientBundle.getString("emailClient"));
        owner.setPhone(clientBundle.getString("phoneClient"));
        owner.setSubscribed(clientBundle.getBoolean("subscribed"));

        return owner;
    }
}