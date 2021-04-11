package iao.master.blanchisserie.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import iao.master.blanchisserie.R;
import iao.master.blanchisserie.activities.WelcomeActivity;
import iao.master.blanchisserie.daos.BlanchisserieDao;
import iao.master.blanchisserie.database.Database;
import iao.master.blanchisserie.models.Commands;


public class HomeFragment extends Fragment {

    Button buttonDelete;

    CardView wash_iron;
    CardView iron;
    CardView dry_cleaning;
    CardView darning;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Database db = Database.getInstance(view.getContext());
        BlanchisserieDao blanchisserieDao = db.blanchisserieDao();

        goToCommand(view);

        buttonDelete = (Button) view.findViewById(R.id.button_delete);
        buttonDelete.setOnClickListener(v -> {
            blanchisserieDao.deleteSettings();
            startActivity(new Intent(view.getContext(), WelcomeActivity.class));
        });
    }

    public void goToCommand(View view){
        Bundle data = new Bundle();
        wash_iron = (CardView) view.findViewById(R.id.card_wash_iron);
        iron = (CardView) view.findViewById(R.id.card_iron);
        dry_cleaning = (CardView) view.findViewById(R.id.card_dry_cleaning);
        darning = (CardView) view.findViewById(R.id.card_darning);

        wash_iron.setOnClickListener(v -> {
            data.putString("operation", Commands.SERVICE_WASH_AND_IRON);
            CommandsFragment fragment = new CommandsFragment();
            fragment.setArguments(data);
            getFragmentManager().beginTransaction().replace(R.id.frame_container,fragment).commit();

        });

        iron.setOnClickListener(v -> {
            data.putString("operation",Commands.SERVICE_IRONING);
            CommandsFragment fragment = new CommandsFragment();
            fragment.setArguments(data);
            getFragmentManager().beginTransaction().replace(R.id.frame_container,fragment).commit();

        });

        dry_cleaning.setOnClickListener(v -> {
            data.putString("operation",Commands.SERVICE_DRY_CLEANING);
            CommandsFragment fragment = new CommandsFragment();
            fragment.setArguments(data);
            getFragmentManager().beginTransaction().replace(R.id.frame_container,fragment).commit();

        });

        darning.setOnClickListener(v -> {
            data.putString("operation",Commands.SERVICE_DARNING);
            CommandsFragment fragment = new CommandsFragment();
            fragment.setArguments(data);
            getFragmentManager().beginTransaction().replace(R.id.frame_container,fragment).commit();

        });

    }

}