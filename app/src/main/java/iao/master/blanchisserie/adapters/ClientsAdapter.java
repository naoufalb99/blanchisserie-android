package iao.master.blanchisserie.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.List;

import iao.master.blanchisserie.R;
import iao.master.blanchisserie.models.Clients;

public class ClientsAdapter extends RecyclerView.Adapter<ClientsAdapter.ViewHolder> {

    private Clients[] clientsArray;

    private clickListener onClickListener;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private Long clientId;

        MaterialCardView cardClient;
        View chipSubscribed;
        TextView textClientName,
                textClientEmail,
                textClientPhone;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cardClient = (MaterialCardView) itemView.findViewById(R.id.card_client);
            chipSubscribed = itemView.findViewById(R.id.chip_subscribed);
            textClientName = (TextView) itemView.findViewById(R.id.text_client_name);
            textClientEmail = itemView.findViewById(R.id.text_client_email);
            textClientPhone = itemView.findViewById(R.id.text_client_phone);

            configureCardClient();
        }

        public void setData(Clients client) {
            clientId = client.getId();

            textClientName.setText(client.getName());
            textClientEmail.setText(client.getEmail());
            textClientPhone.setText(client.getPhone());

            if(client.isSubscribed()) {
                chipSubscribed.setVisibility(View.VISIBLE);
            }
        }

        private void configureCardClient() {
            cardClient.setOnClickListener(v -> {
                if (onClickListener != null) {
                    cardClient.setChecked(onClickListener.execute(clientId));
                }
            });
        }

    }

    public interface clickListener {
        boolean execute(Long clientId);
    }

    public ClientsAdapter(Clients[] clientsArray) {
        this.clientsArray = clientsArray;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.clients_row, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(clientsArray[position]);
    }

    @Override
    public int getItemCount() {
        return clientsArray.length;
    }

    public void setOnClickListener(clickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
