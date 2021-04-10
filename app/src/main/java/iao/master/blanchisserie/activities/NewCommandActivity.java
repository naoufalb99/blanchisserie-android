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
import com.google.android.material.snackbar.Snackbar;

import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.Vector;

import iao.master.blanchisserie.R;
import iao.master.blanchisserie.database.Database;
import iao.master.blanchisserie.fragments.AddCommandArticlesFragment;
import iao.master.blanchisserie.fragments.AddCommandDetailsFragment;
import iao.master.blanchisserie.fragments.AddCommandOwnerFragment;
import iao.master.blanchisserie.models.ArticleCommand;
import iao.master.blanchisserie.models.Clients;
import iao.master.blanchisserie.models.Commands;

public class NewCommandActivity extends AppCompatActivity {

    private Database db;

    private Map<Long, Integer> articlesCount;

    private Vector<Class<? extends Fragment>> fragmentsClasses;
    private Integer currentFragmentIndex = -1;
    private Float currentPrice = 0f;

    private Clients client;

    Toolbar topAppBar;

    TextView textNewCommandPrice, textNewCommandTotal;

    Button buttonContinue;
    Drawable buttonContinueIcon;
    private String commandService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_command);

        this.db = Database.getInstance(this);

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
            if (!prevFragment()) {
                finish();
            }
        });
    }

    private void selectMainFragment(Fragment fragment, Boolean addToBackStack) {
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_new_command_container, fragment);

        if (addToBackStack) {
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

    public void nextFragment() {
        Boolean isFirst = currentFragmentIndex == -1;
        if (currentFragmentIndex >= fragmentsClasses.size() - 1) return;
        try {
            Fragment fragment = fragmentsClasses.get(++currentFragmentIndex).newInstance();
            selectMainFragment(fragment, false);
        } catch (Exception e) {
            Log.e("NewCommandActivity", Objects.requireNonNull(e.getMessage()));
        }
        if (currentFragmentIndex == fragmentsClasses.size() - 1) {
            setupForFinalFragment();
        } else {
            resetSetupForFinalFragment();
        }
    }

    private void resetSetupForFinalFragment() {
        if (buttonContinueIcon != null) {
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
        if (currentFragmentIndex <= 0) return false;
        try {
            Fragment fragment = fragmentsClasses.get(--currentFragmentIndex).newInstance();
            selectMainFragment(fragment, false);
        } catch (Exception e) {
            Log.e("NewCommandActivity", Objects.requireNonNull(e.getMessage()));
        }
        if (currentFragmentIndex == fragmentsClasses.size() - 1) {
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
    public void updatePrice(String op, float newPrice) {

        if (op.equals("add")) {
            currentPrice += newPrice;
            textNewCommandPrice.setText(currentPrice.toString() + " Dhs");
        } else if (op.equals("sub")) {
            if (currentPrice > 0) {
                currentPrice -= newPrice;
                textNewCommandPrice.setText(currentPrice.toString() + " Dhs");
            }
        }

    }

    public void persistCommand() {
        topAppBar.setNavigationIcon(null);
        findViewById(R.id.bottom_bar_new_command).setVisibility(View.GONE);

        db.runInTransaction(new Runnable() {
            @Override
            public void run() {
                final Long clientId = db.blanchisserieDao().insertClient(client);
                final Long commandId = db.blanchisserieDao().insertCommand(new Commands(new Date(), Commands.STATUS_IN_PROGRESS, currentPrice, getCommandService(), clientId));

                final Set<Map.Entry<Long, Integer>> articlesCountEntries = articlesCount.entrySet();
                final List<ArticleCommand> articleCommands = new LinkedList<>();

                for (Map.Entry<Long, Integer> articlesCountEntry : articlesCountEntries) {
                    Long articleId = articlesCountEntry.getKey();
                    Integer quantity = articlesCountEntry.getValue();
                    if (quantity > 0) {
                        articleCommands.add(new ArticleCommand(commandId, articleId, quantity));
                    }
                }

                db.blanchisserieDao().insertArticleCommands(articleCommands);


            }
        });


        Snackbar.make(findViewById(R.id.context_view), R.string.text_label_command_success, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.action_text_command_success, v -> {
                    finish();
                })
                .show();
    }

    public void setArticlesCount(Map<Long, Integer> articlesCount) {
        this.articlesCount = articlesCount;
    }

    public Map<Long, Integer> getArticlesCount() {
        return articlesCount;
    }

    public void setCommandService(String commandService) {
        this.commandService = commandService;
    }

    public String getCommandService() {
        return this.commandService;
    }

    public Float getCurrentPrice() {
        return this.currentPrice;
    }
}