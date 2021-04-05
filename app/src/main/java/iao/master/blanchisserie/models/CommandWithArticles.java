package iao.master.blanchisserie.models;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class CommandWithArticles {
    @Embedded public Commands command;
    @Relation(
            parentColumn = "command_id",
            entityColumn = "article_id"
    )
    public List<Articles> articles;
}
