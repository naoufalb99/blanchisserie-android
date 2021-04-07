package iao.master.blanchisserie.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import iao.master.blanchisserie.R;
import iao.master.blanchisserie.models.Articles;
import iao.master.blanchisserie.models.Clients;

public class AddCommandActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_command);

        //Clients commandOwner = getOwner(getIntent().getExtras());
        ViewGroup main = (ViewGroup) findViewById(R.id.commandsContainer);
        //layout inflater
        Articles article = new Articles("chemise",15f,"image_chemise");
        //renderArticle(article);
        renderArticle(article,main);




    }



    //layout inflater
    public void renderArticle(Articles article,ViewGroup root){

        String articleName = article.getName();
        String articleImage = "@drawable/"+article.getImage();
        Float articlePrice = article.getPrice();

        LayoutInflater l = getLayoutInflater();
        View v = l.inflate(R.layout.component_article,(ViewGroup) findViewById(R.id.articleComponent));

        //article image
        ImageView icon = (ImageView) v.findViewById(R.id.articleIcon);
        int imageResource = getResources().getIdentifier(articleImage, null, getPackageName());
        @SuppressLint("UseCompatLoadingForDrawables")
        Drawable res = getResources().getDrawable(imageResource);
        icon.setImageDrawable(res);

        ///article name
        TextView name = (TextView) v.findViewById(R.id.articleName);
        name.setText(articleName);

        root.addView(v);

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