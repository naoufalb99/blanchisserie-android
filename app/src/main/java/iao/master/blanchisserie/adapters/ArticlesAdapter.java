package iao.master.blanchisserie.adapters;

import android.content.Context;
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
import iao.master.blanchisserie.models.Articles;

public class ArticlesAdapter  extends
        RecyclerView.Adapter<ArticlesAdapter.ViewHolder> {

    List<Articles> articles;
    Map<String,Integer> articlesQuantities;

    public ArticlesAdapter(List<Articles> articles) {
        this.articles = articles;
        articlesQuantities = new HashMap<String, Integer>();
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
        int quantity = Integer.parseInt(holder.articleQuantity.getText().toString());
        articlesQuantities.put(article.getName(),quantity);
            //buttons settings
        holder.addCounter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                articlesQuantities.put(article.getName(),articlesQuantities.get(article.getName())+1);
                holder.articleQuantity.setText(articlesQuantities.get(article.getName()).toString());

            }
        });

        holder.minusCounter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(articlesQuantities.get(article.getName())>0)
                articlesQuantities.put(article.getName(),articlesQuantities.get(article.getName())-1);
                holder.articleQuantity.setText(articlesQuantities.get(article.getName()).toString());
            }
        });



    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public Map<String,Integer> getArticlesCount(){
        return articlesQuantities;
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
