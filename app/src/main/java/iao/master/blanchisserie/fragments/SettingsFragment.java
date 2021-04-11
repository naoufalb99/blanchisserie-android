package iao.master.blanchisserie.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.preference.EditTextPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import iao.master.blanchisserie.R;
import iao.master.blanchisserie.activities.WelcomeActivity;
import iao.master.blanchisserie.daos.BlanchisserieDao;
import iao.master.blanchisserie.database.Database;
import iao.master.blanchisserie.models.Settings;

public class SettingsFragment extends PreferenceFragmentCompat {
    Context context;
    Preference factoryReset ;
    EditTextPreference nomB,adresseB,emailB,numB;
    Settings[] settings;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);

        factoryReset = findPreference("factory_reset");

        nomB = (EditTextPreference) findPreference("nom_blanchisserie");
        adresseB = (EditTextPreference) findPreference("adresse_blanchisserie");
        emailB = (EditTextPreference) findPreference("email_blanchisserie");
        numB = (EditTextPreference) findPreference("num_blanchisserie");




    }




    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = view.getContext();
        Database db = Database.getInstance(view.getContext());
        BlanchisserieDao blanchisserieDao = db.blanchisserieDao();


        showDefaultBlanchisserieSettings(blanchisserieDao);
        updateBlanchisserieInfo(blanchisserieDao);



        factoryReset.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                showFactoryResetAlert(blanchisserieDao);
                return true;
            }
        });
    }

    public void updateBlanchisserieInfo(BlanchisserieDao blanchisserieDao){
        nomB.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                settings[0].setNomBlanchisserie((String) newValue);
                blanchisserieDao.updateSetting(settings[0]);
                preference.setSummary((String)newValue);
                Toast.makeText(context,"update succesfull",
                        Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        adresseB.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                settings[0].setAdresse((String) newValue);
                blanchisserieDao.updateSetting(settings[0]);
                preference.setSummary((String)newValue);
                Toast.makeText(context,"update succesfull",
                        Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        emailB.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                settings[0].setEmail((String) newValue);
                blanchisserieDao.updateSetting(settings[0]);
                preference.setSummary((String)newValue);
                Toast.makeText(context,"update succesfull",
                        Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        numB.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                settings[0].setNumeroTelephone((String) newValue);
                blanchisserieDao.updateSetting(settings[0]);
                preference.setSummary((String)newValue);
                Toast.makeText(context,"update succesfull",
                        Toast.LENGTH_SHORT).show();
                return true;
            }
        });

    }

    public void showDefaultBlanchisserieSettings(BlanchisserieDao blanchisserieDao){
        settings = blanchisserieDao.getAllSettings();
        nomB.setSummary(settings[0].getNomBlanchisserie());
        adresseB.setSummary(settings[0].getAdresse());
        emailB.setSummary(settings[0].getEmail());
        numB.setSummary(settings[0].getNumeroTelephone());
    }

    public void showFactoryResetAlert(BlanchisserieDao blanchisserieDao){
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle("Factory Reset");
        alert.setMessage("are you sure you want to reset the app");
        alert.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                blanchisserieDao.deleteAllClients();
                blanchisserieDao.deleteAllCommands();
                blanchisserieDao.deleteAllArticleCommands();
                blanchisserieDao.deleteSettings();
                startActivity(new Intent(context, WelcomeActivity.class));

            }
        }).setNegativeButton("Non", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
                Toast.makeText(context,"Factory reset canceled",
                        Toast.LENGTH_SHORT).show();
            }
        });
        alert.create().show();
    }






}