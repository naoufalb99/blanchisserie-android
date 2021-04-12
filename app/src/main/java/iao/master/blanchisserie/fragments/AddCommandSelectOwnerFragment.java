package iao.master.blanchisserie.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import iao.master.blanchisserie.R;
import iao.master.blanchisserie.activities.HomeActivity;
import iao.master.blanchisserie.activities.NewCommandActivity;
import iao.master.blanchisserie.adapters.ClientsAdapter;
import iao.master.blanchisserie.daos.BlanchisserieDao;
import iao.master.blanchisserie.database.Database;


public class AddCommandSelectOwnerFragment extends Fragment {

    RecyclerView recyclerClients;
    Database db;

    private Long clientId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_command_select_owner, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NewCommandActivity newCommandActivity = ((NewCommandActivity) getActivity());

        this.db = Database.getInstance(getActivity());
        BlanchisserieDao blanchisserieDao = db.blanchisserieDao();

        recyclerClients = (RecyclerView) view.findViewById(R.id.recycler_clients);
        ClientsAdapter clientsAdapter = new ClientsAdapter(blanchisserieDao.getAllClients());
        recyclerClients.setAdapter(clientsAdapter);
        recyclerClients.setLayoutManager(new LinearLayoutManager(getActivity()));

        clientsAdapter.setOnClickListener(clientId -> {
            if (this.clientId == null) {
                this.clientId = clientId;
                return true;
            } else if (this.clientId.equals(clientId)) {
                this.clientId = null;
                return false;
            } else {
                Toast.makeText(getContext(), "Vous pouvez sélectionner au plus un client", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        newCommandActivity.getButtonContinue().setOnClickListener(v -> {
            if (clientId == null) {
                Toast.makeText(getContext(), "Pour continuer veuillez sélectionner un client", Toast.LENGTH_SHORT).show();
            } else {
                newCommandActivity.setClient(this.db.blanchisserieDao().getClientById(clientId));
                newCommandActivity.nextFragment();
            }
        });

    }
}