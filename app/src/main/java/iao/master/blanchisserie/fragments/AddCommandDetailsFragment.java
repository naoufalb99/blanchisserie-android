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
import android.widget.TextView;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import iao.master.blanchisserie.R;
import iao.master.blanchisserie.activities.NewCommandActivity;
import iao.master.blanchisserie.adapters.ArticlesAdapter;
import iao.master.blanchisserie.adapters.ArticlesDetailsAdapter;
import iao.master.blanchisserie.models.Articles;
import iao.master.blanchisserie.models.Clients;


public class AddCommandDetailsFragment extends Fragment {

    TextView textClientName, textClientEmail, textClientPhone ,textArticlesDetailsTotalPrice;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_command_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textClientName = (TextView) view.findViewById(R.id.text_client_name);
        textClientEmail = (TextView) view.findViewById(R.id.text_client_email);
        textClientPhone = (TextView) view.findViewById(R.id.text_client_phone);
        textArticlesDetailsTotalPrice = (TextView) view.findViewById(R.id.text_articles_details_total_price);

        List<Articles> articlesList = new LinkedList<>();
        articlesList.add(new Articles("T-shirt", 25F, ""));
        articlesList.add(new Articles("Chemise", 30F, ""));

        Map<Integer, Integer> articlesQuantity = new HashMap<>();
        articlesQuantity.put(0, 2);
        articlesQuantity.put(1, 3);

        calculateTotalPrice(articlesList, articlesQuantity, textArticlesDetailsTotalPrice);

        RecyclerView recyclerArticlesDetails = (RecyclerView) view.findViewById(R.id.recycler_articles_details);
        ArticlesDetailsAdapter adapter = new ArticlesDetailsAdapter(articlesList, articlesQuantity);
        recyclerArticlesDetails.setAdapter(adapter);
        recyclerArticlesDetails.setLayoutManager(new LinearLayoutManager(getActivity()));

        NewCommandActivity newCommandActivity = ((NewCommandActivity) getActivity());
        newCommandActivity.setTopAppBarTitle("Détails de la commande");

        setClientDetailsView(newCommandActivity.getClient());

        newCommandActivity.getButtonContinue().setOnClickListener(v -> {
            newCommandActivity.persistCommand();
//            newCommandActivity.nextFragment();
        });

    }

    private void calculateTotalPrice(List<Articles> articlesList, Map<Integer, Integer> articlesQuantity, TextView v) {
        new Thread(() -> {
            Float totalPrice = 0F;
            for(int i = 0; i < articlesList.size(); i++) {
                totalPrice += articlesList.get(i).getPrice() * articlesQuantity.get(i);
            }
            Float finalTotalPrice = totalPrice;
            v.post(() -> {
                v.setText(String.valueOf(finalTotalPrice));
            });
        }).start();
    }


    private void setClientDetailsView(Clients client) {
        textClientName.setText(client.getName());
        textClientEmail.setText(client.getEmail());
        textClientPhone.setText(client.getPhone());
    }

}