package iao.master.blanchisserie.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import iao.master.blanchisserie.R;
import iao.master.blanchisserie.activities.NewCommandActivity;


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
    }
}