package iao.master.blanchisserie.fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        BlanchisserieDao blanchisserieDao = db.blanchisserieDao();



        commandsWithArticles = blanchisserieDao.getCommandWithArticlesByService(commandService);

        RecyclerView commandsRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_commands);
        CommandsAdapter adapter = new CommandsAdapter(commandsWithArticles);
        commandsRecyclerView.setAdapter(adapter);
        commandsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    }
}