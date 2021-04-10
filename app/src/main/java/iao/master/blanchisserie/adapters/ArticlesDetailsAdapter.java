package iao.master.blanchisserie.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Map;

import iao.master.blanchisserie.R;
import iao.master.blanchisserie.models.Articles;
import iao.master.blanchisserie.models.Clients;

public class ArticlesDetailsAdapter extends RecyclerView.Adapter<ArticlesDetailsAdapter.ViewHolder> {

    private List<Articles> articlesList;
    private Map<Long, Integer> articlesQuantity;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textArticleQuantity, textArticleName, textArticleSubPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textArticleQuantity = (TextView) itemView.findViewById(R.id.text_article_quantity);
            textArticleName = (TextView) itemView.findViewById(R.id.text_article_name);
            textArticleSubPrice = (TextView) itemView.findViewById(R.id.text_article_sub_price);
        }

        public void setData(Articles articles, Integer quantity) {
            textArticleQuantity.setText(String.valueOf(quantity));
            textArticleName.setText(articles.getName());
            textArticleSubPrice.setText(String.valueOf(articles.getPrice()) + " Dhs");
        }

    }

    public ArticlesDetailsAdapter(List<Articles> articlesList, Map<Long, Integer> articlesQuantity) {
        this.articlesList = articlesList;
        this.articlesQuantity = articlesQuantity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.articles_details_row, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Articles article = articlesList.get(position);
        holder.setData(article, articlesQuantity.get(article.getArticle_id()));
    }

    @Override
    public int getItemCount() {
        return articlesList.size();
    }

}
