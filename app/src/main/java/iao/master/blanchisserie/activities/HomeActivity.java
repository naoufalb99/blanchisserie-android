package iao.master.blanchisserie.activities;

import android.content.Intent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "Home Activity", Toast.LENGTH_LONG).show();
    }

}
