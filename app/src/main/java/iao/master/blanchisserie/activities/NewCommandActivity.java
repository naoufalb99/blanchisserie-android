package iao.master.blanchisserie.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.Enumeration;
import java.util.Objects;
import java.util.Vector;

import iao.master.blanchisserie.R;
import iao.master.blanchisserie.fragments.AddCommandArticlesFragment;
import iao.master.blanchisserie.fragments.AddCommandOwnerFragment;
import iao.master.blanchisserie.models.Clients;

public class NewCommandActivity extends AppCompatActivity {

    private Vector<Class<? extends Fragment>> fragmentsClasses;
    private Integer currentFragmentIndex = -1;

    private Clients client;

    Toolbar topAppBar;
    Button buttonContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_command);

        buttonContinue = (Button) findViewById(R.id.button_continue);
        topAppBar = (Toolbar) findViewById(R.id.topAppBar_new_command);

        loadFragmentsClasses();
        nextFragment();
        configureTopAppBar();
    }

    private void loadFragmentsClasses() {
        fragmentsClasses = new Vector<>();
        fragmentsClasses.add(AddCommandArticlesFragment.class);
        fragmentsClasses.add(AddCommandOwnerFragment.class);
    }

    private void configureTopAppBar() {
        topAppBar.setNavigationOnClickListener(v -> {
            if(!prevFragment()) {
                finish();
            }
        });
    }

    private void selectMainFragment(Fragment fragment, Boolean addToBackStack) {
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

    public void setClient(Clients client) {
        this.client = client;
    }

    public void nextFragment(){
        Boolean isFirst = currentFragmentIndex == -1;
        if(currentFragmentIndex >= fragmentsClasses.size()) return;
        try {
            Fragment fragment = fragmentsClasses.get(++currentFragmentIndex).newInstance();
            selectMainFragment(fragment, false);
        }catch (Exception e) {
            Log.e("NewCommandActivity", Objects.requireNonNull(e.getMessage()));
        }
    }

    public Boolean prevFragment() {
        if(currentFragmentIndex <= 0) return false;
        try {
            Fragment fragment = fragmentsClasses.get(--currentFragmentIndex).newInstance();
            selectMainFragment(fragment, false);
        }catch (Exception e) {
            Log.e("NewCommandActivity", Objects.requireNonNull(e.getMessage()));
        }
        return true;
    }

    public Button getButtonContinue() {
        return buttonContinue;
    }

    public void setTopAppBarTitle(String value) {
        topAppBar.setTitle(value);
    }

    @Override
    public void onBackPressed() {
        currentFragmentIndex--;
        super.onBackPressed();
    }
}