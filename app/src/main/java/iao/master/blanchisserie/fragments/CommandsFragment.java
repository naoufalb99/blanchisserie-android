package iao.master.blanchisserie.fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.ChipGroup;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import iao.master.blanchisserie.R;
import iao.master.blanchisserie.adapters.CommandsAdapter;
import iao.master.blanchisserie.daos.BlanchisserieDao;
import iao.master.blanchisserie.database.Database;
import iao.master.blanchisserie.models.ArticleCommand;
import iao.master.blanchisserie.models.Articles;
import iao.master.blanchisserie.models.CommandWithArticles;
import iao.master.blanchisserie.models.Commands;


public class CommandsFragment extends Fragment {

    String commandService;
    Database db;

    List<CommandWithArticles> commandsWithArticles;

    String commandsStatus;
    Map<Integer, String> mapChipToStatus;

    ChipGroup chipGroupServices;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_commands, container, false);
        Bundle data = this.getArguments();
         commandService = data.getString("operation");
        return v;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.db = Database.getInstance(getActivity());

        chipGroupServices = (ChipGroup) view.findViewById(R.id.chip_group_services);
        RecyclerView commandsRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_commands);

        new Thread(() -> {
            mapChipToStatus = new HashMap<>();
            mapChipToStatus.put(R.id.chip_status_in_progress, Commands.STATUS_IN_PROGRESS);
            mapChipToStatus.put(R.id.chip_status_completed, Commands.STATUS_COMPLETED);

            commandsWithArticles = queryCommandsWithArticles(chipGroupServices.getCheckedChipId());
            CommandsAdapter adapter;
            adapter = new CommandsAdapter(commandsWithArticles);
            commandsRecyclerView.post(() -> {
                commandsRecyclerView.setAdapter(adapter);
                commandsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            });

            chipGroupServices.setOnCheckedChangeListener((group, checkedId) -> {
                commandsWithArticles = queryCommandsWithArticles(checkedId);
                adapter.setCommandsWithArticles(commandsWithArticles);
                adapter.notifyDataSetChanged();
            });

            adapter.setOnCompletedListener(position -> {
                new Thread(()-> {
                    Commands command = commandsWithArticles.get(position).command;
                    commandsWithArticles = queryCommandsWithArticles(chipGroupServices.getCheckedChipId());
                    adapter.setCommandsWithArticles(commandsWithArticles);
                    view.post(() -> {
                        adapter.notifyDataSetChanged();
                    });
                    command.setStatus(Commands.STATUS_COMPLETED);
                    this.db.blanchisserieDao().updateCommand(command);
                }).start();
            });

        }).start();

    }

    private List<CommandWithArticles> queryCommandsWithArticles(int checkedChipId){
        String status = mapChipToStatus.get(checkedChipId);
        if(status == null || status.isEmpty()) {
            return this.db.blanchisserieDao().getCommandWithArticlesByService(commandService);
        }else {
            return this.db.blanchisserieDao().getCommandWithArticlesByServiceAndStatus(commandService, status);
        }
    }
}