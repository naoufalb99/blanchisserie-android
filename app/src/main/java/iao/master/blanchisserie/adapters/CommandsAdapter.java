package iao.master.blanchisserie.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.List;
import java.util.Map;

import iao.master.blanchisserie.R;
import iao.master.blanchisserie.models.Articles;
import iao.master.blanchisserie.models.CommandWithArticles;
import iao.master.blanchisserie.models.Commands;

public class CommandsAdapter extends RecyclerView.Adapter<CommandsAdapter.ViewHolder> {

    private List<CommandWithArticles> commandsWithArticles;

    private CharSequence[] dialogItems = {"Un e-mail", "Un SMS"};
    private boolean[] dialogCheckedItems = {false, true};

    private CompletedListener onCompletedListener;

    public CommandsAdapter(List<CommandWithArticles> commandsWithArticles) {
        this.commandsWithArticles = commandsWithArticles;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.commands_row, parent, false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(commandsWithArticles.get(position), position);
    }


    @Override
    public int getItemCount() {
        return commandsWithArticles.size();
    }

    //viewHolder class
    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView commandID;
        public TextView commandOwner;
        public TextView commandArticles;
        public TextView total;

        Button buttonCommandCompleted;

        private Context context;


        public ViewHolder(View itemView) {
            super(itemView);

            commandID = (TextView) itemView.findViewById(R.id.command_id);
            commandOwner = (TextView) itemView.findViewById(R.id.command_owner);
            commandArticles = (TextView) itemView.findViewById(R.id.command_articles);
            total = (TextView) itemView.findViewById(R.id.command_totale);
            buttonCommandCompleted = (Button) itemView.findViewById(R.id.button_command_completed);

            commandArticles.setSelected(true);

            context = itemView.getContext();
        }

        public Context getContext() {
            return context;
        }

        public void setData(CommandWithArticles commandWithArticles, int position) {
            StringBuilder articles = new StringBuilder();

            commandID.setText(commandWithArticles.command.getCommand_id().toString());
            commandOwner.setText(commandWithArticles.command.getClient_id().toString());
            total.setText(commandWithArticles.command.getTotal_price().toString() + " Dhs");

            for (int i = 0; i < commandWithArticles.articles.size(); i++) {
                articles.append(commandWithArticles.articles.get(i).getName());
                articles.append("(" + commandWithArticles.articleCommands.get(i).getQuantity() + ")" + ", ");
            }
            articles.deleteCharAt(articles.length() - 2);
            commandArticles.setText(articles.toString());

            if (commandWithArticles.command.getStatus().equals(Commands.STATUS_COMPLETED)) {
                buttonCommandCompleted.setVisibility(View.GONE);
            } else {
                buttonCommandCompleted.setVisibility(View.VISIBLE);
                buttonCommandCompleted.setOnClickListener(v -> {
                    new MaterialAlertDialogBuilder(context)
                            .setTitle("Notifier le client par")
                            .setNeutralButton("Annuler", null)
                            .setPositiveButton("Enregistrer", (dialog, which) -> {
                                if (onCompletedListener != null) {
                                    onCompletedListener.execute(position);
                                }
                            })
                            .setMultiChoiceItems(dialogItems, dialogCheckedItems, (dialog, which, isChecked) -> {
                                dialogCheckedItems[which] = isChecked;
                            })
                            .show();
                });

            }

        }

    }

    public interface CompletedListener {
        void execute(int position);
    }

    public void setOnCompletedListener(CompletedListener onCompletedListener) {
        this.onCompletedListener = onCompletedListener;
    }

    public void setCommandsWithArticles(List<CommandWithArticles> commandsWithArticles) {
        this.commandsWithArticles = commandsWithArticles;
    }
}
