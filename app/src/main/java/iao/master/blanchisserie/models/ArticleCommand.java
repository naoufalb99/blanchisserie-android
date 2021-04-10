package iao.master.blanchisserie.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "article_command",primaryKeys ={"command_id","article_id"} )
public class ArticleCommand {
    @NonNull
    public Long command_id;
    @NonNull
    public Long article_id;
    @ColumnInfo(defaultValue = "0")
    public Integer quantity;

    @NonNull
    public Long getCommand_id() {
        return command_id;
    }

    public void setCommand_id(@NonNull Long command_id) {
        this.command_id = command_id;
    }

    @NonNull
    public Long getArticle_id() {
        return article_id;
    }

    public void setArticle_id(@NonNull Long article_id) {
        this.article_id = article_id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ArticleCommand(@NonNull Long command_id, @NonNull Long article_id, Integer quantity) {
        this.command_id = command_id;
        this.article_id = article_id;
        this.quantity = quantity;
    }
}
