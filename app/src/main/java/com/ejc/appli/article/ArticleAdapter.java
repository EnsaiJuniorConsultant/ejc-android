package com.ejc.appli.article;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ejc.appli.test.R;
import com.ejc.appli.tools.CustomNetwork;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ArticleAdapter  extends ArrayAdapter<Article> {

    public ArticleAdapter(Context context, ArrayList<Article> articles) {
        super(context, 0, articles);
    }

    @NonNull
    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        // Get the data item for this position
        Article mArticle = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_article, parent, false);
        }
        // Lookup view for data population
        TextView titre = convertView.findViewById(R.id.titreArticle);
        TextView auteur = convertView.findViewById(R.id.auteurArticle);
        ImageView imageView = convertView.findViewById(R.id.imgArticle);
        // Populate the data into the template view using the data object
        assert mArticle != null;
        titre.setText(mArticle.titre);
        String by = "Ecrit par ";
        auteur.setText(by+mArticle.auteur);

        if(CustomNetwork.isNetworkAvailable(imageView.getContext())) {
            Picasso.get()
                    .load(mArticle.urlImage)
                    .placeholder(R.mipmap.fondaccueil)
                    .fit()
                    .centerInside()
                    .into(imageView);
        }else{
            imageView.setImageResource(R.mipmap.fondaccueil);
        }

        // Return the completed view to render on screen
        return convertView;
    }

}