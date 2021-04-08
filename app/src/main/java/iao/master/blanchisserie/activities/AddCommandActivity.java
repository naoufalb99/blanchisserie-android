package iao.master.blanchisserie.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import iao.master.blanchisserie.R;
import iao.master.blanchisserie.adapters.ArticlesAdapter;
import iao.master.blanchisserie.models.Articles;



public class AddCommandActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_command);

        List<Articles> articles = new ArrayList<>();
        Articles articles1 = new Articles("chemise",15f,"image_chemise");
        Articles articles2 = new Articles("pantalon",15f,"image_pantalon");

        articles.add(articles1);
        articles.add(articles2);

        RecyclerView rvArticles = (RecyclerView) findViewById(R.id.articles);
        ArticlesAdapter adapter = new ArticlesAdapter(articles);
        rvArticles.setAdapter(adapter);
        rvArticles.setLayoutManager(new LinearLayoutManager(this));







    }



    //layout inflater
    public void renderArticles(List<Articles> articles,LinearLayout root){
        LinearLayout v;
        for (Articles article : articles){
            String articleName = article.getName();
            String articleImage = "@drawable/"+article.getImage();

            //LayoutInflater l = getLayoutInflater();
            //View v = l.inflate(R.layout.component_article,(ViewGroup) findViewById(R.id.articleComponent));

            v = (LinearLayout) View.inflate(this,R.layout.component_article,null);

            //article image
            ImageView icon = (ImageView) v.findViewById(R.id.articleIcon);
            int imageResource = getResources().getIdentifier(articleImage, null, getPackageName());
            @SuppressLint("UseCompatLoadingForDrawables")
            Drawable res = getResources().getDrawable(imageResource);
            icon.setImageDrawable(res);

            ///article name
            TextView name = (TextView) v.findViewById(R.id.articleName);
            name.setText(articleName);

            //buttons
            TextView quantityText = (TextView) findViewById(R.id.article_quantity);
            int quantity = Integer.parseInt(((TextView) findViewById(R.id.article_quantity)).toString());
            Button add = (Button) findViewById(R.id.plus_article);







            root.addView(v);
        }

    }






//    public Clients getOwner(Bundle extras){
//        Bundle clientBundle = extras.getBundle("commandOwner");
//        Clients owner = new Clients();
//
//        owner.setName(clientBundle.getString("nomClient"));
//        owner.setEmail(clientBundle.getString("emailClient"));
//        owner.setPhone(clientBundle.getString("phoneClient"));
//        owner.setSubscribed(clientBundle.getBoolean("subscribed"));
//
//        return owner;
//    }
}