package iao.master.blanchisserie.models;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Relation;

import java.util.List;
public class ClientWithCommands {
    @Embedded public Clients client;
    @Relation(
            parentColumn = "id",
            entityColumn = "client_id"
    )
    public List<Commands> commands;
}
