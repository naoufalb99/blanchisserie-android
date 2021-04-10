package iao.master.blanchisserie.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import iao.master.blanchisserie.R;
import iao.master.blanchisserie.activities.NewCommandActivity;
import iao.master.blanchisserie.models.Articles;

public class ArticlesAdapter  extends
        RecyclerView.Adapter<ArticlesAdapter.ViewHolder> {

    List<Articles> articles;
    Map<Long, Integer> articlesQuantities;

    OnUpdateListener updateListener;

    public void setUpdateListener(OnUpdateListener updateListener){
        this.updateListener = updateListener;
    }


    public ArticlesAdapter(List<Articles> articles, Map<Long, Integer> articlesQuantities) {
        this.articles = articles;
        if(articlesQuantities == null) {
            this.articlesQuantities = new HashMap<Long, Integer>();
        }else {
            this.articlesQuantities = articlesQuantities;
        }
    }

    @NonNull
    @Override
    public ArticlesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View articleView = inflater.inflate(R.layout.component_article, parent, false);
        ViewHolder viewHolder = new ViewHolder(articleView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ArticlesAdapter.ViewHolder holder, int position) {
        Articles article = articles.get(position);

        //setting view by properties
            //setting the image
        String articleImage = "@drawable/"+article.getImage();
        int id = holder.getContext().getResources().getIdentifier(articleImage, null, holder.getContext().getPackageName());
        holder.articleIcon.setImageResource(id);
            //setting the name
        holder.articleName.setText(article.getName());

        //setting the counter
        if(articlesQuantities.get(article.getArticle_id()) == null) {
            articlesQuantities.put(article.getArticle_id(), 0);
        }else {
            holder.articleQuantity.setText(String.valueOf(articlesQuantities.get(article.getArticle_id())));
        }
            //buttons settings
        holder.addCounter.setOnClickListener(v -> {
            articlesQuantities.put(article.getArticle_id(),articlesQuantities.get(article.getArticle_id())+1);
            holder.articleQuantity.setText(String.valueOf(articlesQuantities.get(article.getArticle_id())));
            updateListener.onUpdate("add",article.getPrice());
        });

        holder.minusCounter.setOnClickListener(v -> {
            if(articlesQuantities.get(article.getArticle_id())>0){
            articlesQuantities.put(article.getArticle_id(),articlesQuantities.get(article.getArticle_id())-1);
            holder.articleQuantity.setText(String.valueOf(articlesQuantities.get(article.getArticle_id())));
            updateListener.onUpdate("sub",article.getPrice());
            }
        });



    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public Map<Long,Integer> getArticlesCount(){
        return articlesQuantities;
    }

    public  interface OnUpdateListener{
         void onUpdate(String op,Float newPrice);
    }

    //view holder class
    public class ViewHolder extends RecyclerView.ViewHolder {
       public ImageView articleIcon;
       public TextView articleName;
       public TextView articleQuantity;
       public Button addCounter;
       public Button minusCounter;

       private Context context;


        public ViewHolder(View itemView) {
            super(itemView);
            articleIcon = (ImageView) itemView.findViewById(R.id.articleIcon);
            articleName = (TextView) itemView.findViewById(R.id.articleName);
            articleQuantity = (TextView) itemView.findViewById(R.id.article_quantity);
            addCounter = (Button) itemView.findViewById(R.id.plus_article);
            minusCounter = (Button) itemView.findViewById(R.id.minus_article);


            context = itemView.getContext();

        }

        public Context getContext(){
            return context;
        }
    }
}
