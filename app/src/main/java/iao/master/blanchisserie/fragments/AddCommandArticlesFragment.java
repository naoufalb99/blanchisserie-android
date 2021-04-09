package iao.master.blanchisserie.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import iao.master.blanchisserie.R;
import iao.master.blanchisserie.activities.NewCommandActivity;
import iao.master.blanchisserie.adapters.ArticlesAdapter;
import iao.master.blanchisserie.models.Articles;


public class AddCommandArticlesFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_command_articles, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NewCommandActivity newCommandActivity = ((NewCommandActivity) getActivity());
        newCommandActivity.setTopAppBarTitle("Ajouter vos articles");

        newCommandActivity.getButtonContinue().setOnClickListener(v -> {
            newCommandActivity.nextFragment();
        });

        List<Articles> articles = new ArrayList<>();
        Articles articles1 = new Articles("chemise",15f,"image_chemise");
        Articles articles2 = new Articles("pantalon",15f,"image_pantalon");

        articles.add(articles1);
        articles.add(articles2);

        RecyclerView recyclerAddCommandArticles = (RecyclerView) view.findViewById(R.id.recycler_add_command_articles);
        ArticlesAdapter adapter = new ArticlesAdapter(articles);
        recyclerAddCommandArticles.setAdapter(adapter);
        recyclerAddCommandArticles.setLayoutManager(new LinearLayoutManager(getActivity()));

    }
}