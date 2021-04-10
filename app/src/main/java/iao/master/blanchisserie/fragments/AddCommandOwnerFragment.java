package iao.master.blanchisserie.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputLayout;

import java.util.EventListener;

import iao.master.blanchisserie.R;
import iao.master.blanchisserie.activities.MainActivity;
import iao.master.blanchisserie.activities.NewCommandActivity;
import iao.master.blanchisserie.models.Clients;


public class AddCommandOwnerFragment extends Fragment {

    String nom, email, phone;
    Boolean subscribed;

    RadioGroup radioGroup;
    RadioButton radioButton;

    TextInputLayout nomInput, emailInput, phoneInput;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_command_owner, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        radioGroup = view.findViewById(R.id.radioGroup);

        nomInput = view.findViewById(R.id.nomClient);
        emailInput = view.findViewById(R.id.clientEmail);
        phoneInput = view.findViewById(R.id.clientPhone);

        subscribed = radioGroup.getCheckedRadioButtonId() == R.id.abonne;
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.abonne) {
                subscribed = true;
            } else if (checkedId == R.id.nonAbonne) {
                subscribed = false;
            }
        });

        NewCommandActivity newCommandActivity = ((NewCommandActivity) getActivity());
        newCommandActivity.getButtonContinue().setOnClickListener(v -> {
            nom = nomInput.getEditText().getText().toString();
            email = emailInput.getEditText().getText().toString();
            phone = phoneInput.getEditText().getText().toString();

            if (nom.isEmpty() || phone.isEmpty() || email.isEmpty()) {
                Toast.makeText(getContext(), "please fill in all the fields", Toast.LENGTH_SHORT).show();
            } else {
                Clients client = new Clients(nom, email, phone, subscribed);
                newCommandActivity.setClient(client);
                newCommandActivity.nextFragment();
            }
        });


    }


    public void checked(View v) {
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = v.findViewById(radioId);
        if (radioButton.getText().toString().equals("abonne"))
            subscribed = true;
        else
            subscribed = false;
    }

}