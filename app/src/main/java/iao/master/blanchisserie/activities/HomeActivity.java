package iao.master.blanchisserie.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import iao.master.blanchisserie.R;
import iao.master.blanchisserie.daos.BlanchisserieDao;
import iao.master.blanchisserie.database.Database;

public class HomeActivity extends AppCompatActivity {

    Button delete;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Database db = Database.getInstance(this);
        BlanchisserieDao blanchisserieDao = db.settingsDao();

        delete = (Button) findViewById(R.id.delete);
        delete.setOnClickListener(v -> {
            blanchisserieDao.deleteSettings();
            startActivity(new Intent(this, WelcomeActivity.class));
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "Home Activity", Toast.LENGTH_LONG).show();
    }

}
