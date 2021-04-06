package iao.master.blanchisserie.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import iao.master.blanchisserie.R;
import iao.master.blanchisserie.activities.WelcomeActivity;
import iao.master.blanchisserie.daos.BlanchisserieDao;
import iao.master.blanchisserie.database.Database;


public class HomeFragment extends Fragment {

    Button buttonDelete;

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

        buttonDelete = (Button) view.findViewById(R.id.button_delete);
        buttonDelete.setOnClickListener(v -> {
            blanchisserieDao.deleteSettings();
            startActivity(new Intent(view.getContext(), WelcomeActivity.class));
        });
    }
}