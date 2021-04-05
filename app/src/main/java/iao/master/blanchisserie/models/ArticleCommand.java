package iao.master.blanchisserie.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(tableName = "article_command",primaryKeys ={"command_id","article_id"} )
public class ArticleCommand {
    @NonNull
    public Long command_id;
    @NonNull
    public Long article_id;

}
