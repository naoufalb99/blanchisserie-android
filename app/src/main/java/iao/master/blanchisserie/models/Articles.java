package iao.master.blanchisserie.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "articles")
public class Articles {
    @PrimaryKey(autoGenerate = true)
    private Long article_id;
    private String name;
    private Float price ;
    private String image;

    public Articles(String name, Float price, String image) {
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public Long getArticle_id() {
        return article_id;
    }

    public void setArticle_id(Long article_id) {
        this.article_id = article_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
