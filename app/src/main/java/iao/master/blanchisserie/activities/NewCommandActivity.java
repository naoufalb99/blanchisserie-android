package iao.master.blanchisserie.activities;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.view.ViewGroup;

import android.view.ViewDebug;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.button.MaterialButton;

import java.util.Enumeration;
import java.util.Objects;
import java.util.Vector;

import iao.master.blanchisserie.R;
import iao.master.blanchisserie.fragments.AddCommandArticlesFragment;
import iao.master.blanchisserie.fragments.AddCommandDetailsFragment;
import iao.master.blanchisserie.fragments.AddCommandOwnerFragment;
import iao.master.blanchisserie.models.Clients;

public class NewCommandActivity extends AppCompatActivity {

    private Vector<Class<? extends Fragment>> fragmentsClasses;
    private Integer currentFragmentIndex = -1;
    private Float currentPrice=0f;

    private Clients client;

    Toolbar topAppBar;

    TextView textNewCommandPrice, textNewCommandTotal;

    Button buttonContinue;
    Drawable buttonContinueIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_command);

        buttonContinue = (Button) findViewById(R.id.button_continue);
        topAppBar = (Toolbar) findViewById(R.id.topAppBar_new_command);

        textNewCommandPrice = (TextView) findViewById(R.id.text_new_command_price);
        textNewCommandTotal = (TextView) findViewById(R.id.text_new_command_total);




        loadFragmentsClasses();
        nextFragment();
        configureTopAppBar();
    }

    private void loadFragmentsClasses() {
        fragmentsClasses = new Vector<>();
        fragmentsClasses.add(AddCommandArticlesFragment.class);
        fragmentsClasses.add(AddCommandOwnerFragment.class);
        fragmentsClasses.add(AddCommandDetailsFragment.class);
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

    public Clients getClient() {
        return client;
    }

    public void nextFragment(){
        Boolean isFirst = currentFragmentIndex == -1;
        if(currentFragmentIndex >= fragmentsClasses.size() - 1) return;
        try {
            Fragment fragment = fragmentsClasses.get(++currentFragmentIndex).newInstance();
            selectMainFragment(fragment, false);
        }catch (Exception e) {
            Log.e("NewCommandActivity", Objects.requireNonNull(e.getMessage()));
        }
        if(currentFragmentIndex == fragmentsClasses.size() - 1) {
            setupForFinalFragment();
        } else {
            resetSetupForFinalFragment();
        }
    }

    private void resetSetupForFinalFragment() {
        if(buttonContinueIcon != null) {
            ((MaterialButton) buttonContinue).setIcon(buttonContinueIcon);
            buttonContinueIcon = null;
            buttonContinue.setText("Continuer");

            ViewGroup.LayoutParams params = buttonContinue.getLayoutParams();
            params.width = ViewGroup.LayoutParams.WRAP_CONTENT;
            buttonContinue.setLayoutParams(params);

            textNewCommandPrice.setVisibility(View.VISIBLE);
            textNewCommandTotal.setVisibility(View.VISIBLE);
        }
    }

    private void setupForFinalFragment() {
        buttonContinueIcon = ((MaterialButton) buttonContinue).getIcon();
        ((MaterialButton) buttonContinue).setIcon(null);
        buttonContinue.setText("Commander");

        ViewGroup.LayoutParams params = buttonContinue.getLayoutParams();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        buttonContinue.setLayoutParams(params);

        textNewCommandPrice.setVisibility(View.GONE);
        textNewCommandTotal.setVisibility(View.GONE);
    }

    public Boolean prevFragment() {
        if(currentFragmentIndex <= 0) return false;
        try {
            Fragment fragment = fragmentsClasses.get(--currentFragmentIndex).newInstance();
            selectMainFragment(fragment, false);
        }catch (Exception e) {
            Log.e("NewCommandActivity", Objects.requireNonNull(e.getMessage()));
        }
        if(currentFragmentIndex == fragmentsClasses.size() - 1) {
            setupForFinalFragment();
        } else {
            resetSetupForFinalFragment();
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

    //update price
     public void updatePrice(String op,float newPrice){

        if(op.equals("add")){
            currentPrice+=newPrice;
            textNewCommandPrice.setText(currentPrice.toString()+" Dhs");
        }else if(op.equals("sub")){
            if(currentPrice>0){
            currentPrice-=newPrice;
            textNewCommandPrice.setText(currentPrice.toString()+" Dhs");
            }
        }

    }
}