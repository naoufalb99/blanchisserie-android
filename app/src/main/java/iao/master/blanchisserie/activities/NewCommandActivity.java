package iao.master.blanchisserie.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import iao.master.blanchisserie.R;
import iao.master.blanchisserie.fragments.AddCommandOwnerFragment;

public class NewCommandActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_command);

        selectMainFragment(new AddCommandOwnerFragment(), false);
    }

    public void selectMainFragment(Fragment fragment, Boolean addToBackStack) {
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_new_command_container, fragment);

        if(addToBackStack) {
            transaction.addToBackStack(null);
        }

        transaction.commit();
    }

    public void selectMainFragment(Fragment fragment) {
        selectMainFragment(fragment, true);
    }

}