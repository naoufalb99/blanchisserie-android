package iao.master.blanchisserie.models;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class CommandWithArticles {
    @Embedded public Commands command;
    @Relation(
            parentColumn = "command_id",
            entityColumn = "command_id"
    )
    public List<ArticleCommand> articleCommands;
    @Relation(
            parentColumn = "command_id",
            entityColumn = "article_id",
            associateBy = @Junction(ArticleCommand.class)
    )
    public List<Articles> articles;


}
