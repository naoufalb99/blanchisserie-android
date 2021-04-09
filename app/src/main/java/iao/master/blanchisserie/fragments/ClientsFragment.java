package iao.master.blanchisserie.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import iao.master.blanchisserie.R;
import iao.master.blanchisserie.adapters.ClientsAdapter;
import iao.master.blanchisserie.daos.BlanchisserieDao;
import iao.master.blanchisserie.database.Database;
import iao.master.blanchisserie.models.Clients;
import iao.master.blanchisserie.models.Settings;


public class ClientsFragment extends Fragment {

    RecyclerView recyclerClients;
    Database db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_clients, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.db = Database.getInstance(getActivity());
        BlanchisserieDao blanchisserieDao = db.blanchisserieDao();

        recyclerClients = (RecyclerView) view.findViewById(R.id.recycler_clients);
        recyclerClients.setAdapter(new ClientsAdapter(blanchisserieDao.getAllClients()));
        recyclerClients.setLayoutManager(new LinearLayoutManager(getActivity()));


    }
}