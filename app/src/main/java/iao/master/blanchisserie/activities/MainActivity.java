package iao.master.blanchisserie.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import iao.master.blanchisserie.daos.SettingsDao;
import iao.master.blanchisserie.database.Database;
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
    }

    private void activitiesScheduling() {
        SettingsDao settingsDao = db.settingsDao();
        Settings[] settings = settingsDao.getAllSettings();

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
}
