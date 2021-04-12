package iao.master.blanchisserie.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.List;

import iao.master.blanchisserie.R;
import iao.master.blanchisserie.activities.NewCommandActivity;
import iao.master.blanchisserie.adapters.ArticlesAdapter;
import iao.master.blanchisserie.daos.BlanchisserieDao;
import iao.master.blanchisserie.database.Database;
import iao.master.blanchisserie.models.Articles;
import iao.master.blanchisserie.models.Commands;


public class AddCommandArticlesFragment extends Fragment {

    private String commandService;

    ChipGroup chipGroupServices;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_command_articles, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        chipGroupServices = (ChipGroup) view.findViewById(R.id.chip_group_services);

        NewCommandActivity newCommandActivity = ((NewCommandActivity) getActivity());
        newCommandActivity.setTopAppBarTitle("Ajouter vos articles");

        configureChipGroupServices(newCommandActivity.getCommandService(), view);

        //db
        Database db = Database.getInstance(getContext());
        BlanchisserieDao blanchisserieDao = db.blanchisserieDao();

        List<Articles> articles = blanchisserieDao.getAllArticles();


        RecyclerView recyclerAddCommandArticles = (RecyclerView) view.findViewById(R.id.recycler_add_command_articles);
        ArticlesAdapter adapter = new ArticlesAdapter(articles, newCommandActivity.getArticlesCount());
        recyclerAddCommandArticles.setAdapter(adapter);
        recyclerAddCommandArticles.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter.setUpdateListener((op, newPrice) -> {
            ((NewCommandActivity) getActivity()).updatePrice(op, newPrice);
        });

        newCommandActivity.getButtonContinue().setOnClickListener(v -> {
            newCommandActivity.setArticlesCount(adapter.getArticlesCount());
            newCommandActivity.setCommandService(this.commandService);
            if (newCommandActivity.getCurrentPrice() == 0F) {
                Toast.makeText(getContext(), "Veuillez choisir au moins un article", Toast.LENGTH_SHORT).show();
            } else {

                String[] items = {"Selectionner un client?", "Créer un nouveau client?"};
                new MaterialAlertDialogBuilder(getContext())
                        .setTitle("Voulez-vous")
                        .setItems(items, (dialog, which) -> {
                            if (which == 0) {
                                newCommandActivity.setOwnerFragment(AddCommandSelectOwnerFragment.class);
                            } else {
                                newCommandActivity.setOwnerFragment(AddCommandOwnerFragment.class);
                            }
                            dialog.dismiss();
                            newCommandActivity.nextFragment();
                        })
                        .show();
            }
        });

    }

    private void configureChipGroupServices(String defaultCommandService, View v) {
        if (defaultCommandService != null) {
            ((Chip) v.findViewById(getChipIdByCommandService(defaultCommandService))).setChecked(true);
        }
        updateCommandService(chipGroupServices.getCheckedChipId());
        chipGroupServices.setOnCheckedChangeListener((group, checkedId) -> {
            updateCommandService(checkedId);
        });
    }

    @SuppressLint("NonConstantResourceId")
    void updateCommandService(int chipId) {
        switch (chipId) {
            case R.id.chip_wash_and_iron:
                this.commandService = Commands.SERVICE_WASH_AND_IRON;
                break;
            case R.id.chip_ironing:
                this.commandService = Commands.SERVICE_IRONING;
                break;
            case R.id.chip_dry_cleaning:
                this.commandService = Commands.SERVICE_DRY_CLEANING;
                break;
            case R.id.chip_darning:
                this.commandService = Commands.SERVICE_DARNING;
                break;
        }
    }

    private int getChipIdByCommandService(String commandService) {
        switch (commandService) {
            case Commands.SERVICE_WASH_AND_IRON:
                return R.id.chip_wash_and_iron;
            case Commands.SERVICE_IRONING:
                return R.id.chip_ironing;
            case Commands.SERVICE_DRY_CLEANING:
                return R.id.chip_dry_cleaning;
            case Commands.SERVICE_DARNING:
                return R.id.chip_darning;
        }
        return View.NO_ID;
    }

}

