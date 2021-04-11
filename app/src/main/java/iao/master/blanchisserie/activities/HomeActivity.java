package iao.master.blanchisserie.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import iao.master.blanchisserie.R;
import iao.master.blanchisserie.fragments.ClientsFragment;
import iao.master.blanchisserie.fragments.HomeFragment;
import iao.master.blanchisserie.fragments.SettingsFragment;

// TODO: rename this activity
public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigation;
    FloatingActionButton floatingButtonNewCommand;
    Toolbar topAppBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        topAppBar = (Toolbar) findViewById(R.id.topAppBar);
        bottomNavigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        floatingButtonNewCommand = (FloatingActionButton) findViewById(R.id.floating_action_button_new_command);

        configureBottomView();
        configureFloatingButtonNewCommand();

        selectMainFragment(new HomeFragment());
        setTopAppBarTitle(bottomNavigation.getMenu().findItem(R.id.menu_item_home).getTitle().toString());
    }

    private void configureFloatingButtonNewCommand() {
        floatingButtonNewCommand.setOnClickListener(v -> showNewCommandActivity());
    }

    private void configureBottomView() {

        bottomNavigation.setOnNavigationItemSelectedListener(item -> updateMainFragment(item.getItemId()));
    }

    private void showNewCommandActivity() {
        startActivity(new Intent(this, NewCommandActivity.class));
    }

    private void selectMainFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_container, fragment)
                .commit();
    }

    private Boolean updateMainFragment(Integer itemId) {
        Fragment selectedFragment;
        switch(itemId) {
            case R.id.menu_item_home:
                selectedFragment = new HomeFragment();
                break;
            case R.id.menu_item_settings:
                selectedFragment = new SettingsFragment();
                break;
            case R.id.menu_item_clients:
                selectedFragment = new ClientsFragment();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + itemId);
        }

        setTopAppBarTitle(bottomNavigation.getMenu().findItem(itemId).getTitle().toString());
        selectMainFragment(selectedFragment);

        return true;
    }

    public void setTopAppBarTitle(String value) {
        topAppBar.setTitle(value);
    }

}
