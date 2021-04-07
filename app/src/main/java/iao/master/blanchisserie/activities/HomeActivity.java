package iao.master.blanchisserie.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

import iao.master.blanchisserie.R;
import iao.master.blanchisserie.daos.BlanchisserieDao;
import iao.master.blanchisserie.database.Database;
import iao.master.blanchisserie.fragments.ClientsFragment;
import iao.master.blanchisserie.fragments.CommandsFragment;
import iao.master.blanchisserie.fragments.HomeFragment;

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
                // TODO: replace it with SettingsFragment
                selectedFragment = new CommandsFragment();
                break;
            case R.id.menu_item_clients:
                selectedFragment = new ClientsFragment();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + itemId);
        }

        topAppBar.setTitle(bottomNavigation.getMenu().findItem(itemId).getTitle());
        selectMainFragment(selectedFragment);

        return true;
    }

}
