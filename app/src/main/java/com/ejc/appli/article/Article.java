package com.ejc.appli.article;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Article {

    public final String titre;
    public final String auteur;
    public final String url;
    public final String idArticle;
    public final String urlImage;

    public Article(String idArticle, String titre,String auteur, String urlImage,String type){
        this.auteur=auteur;
        this.titre=titre;
        this.idArticle=idArticle;
        this.url= "http://www.blog.ejc.fr/art"+idArticle+"."+type;
        this.urlImage=urlImage;
    }

    public static ArrayList<Article> ParseJSONArray(JSONArray mJSONArray){

        ArrayList<Article> arrayOfArticles = new ArrayList<>();
        try{
            for (int i=0; i < mJSONArray.length(); i++) {
                JSONObject oneObject = mJSONArray.getJSONObject(i);
                arrayOfArticles.add(new Article(
                        oneObject.getString("idArticle"),
                        oneObject.getString("titre"),
                        oneObject.getString("auteur"),
                        oneObject.getString("urlImage").replaceAll("http://", "https://"),
                        oneObject.getString("type")));
            }
            // TODO: Add date in json file and then use the date to sort article
            // Use idArticle to sort them
            Collections.sort(arrayOfArticles, new Comparator<Article>() {
                @Override
                public int compare(Article art2, Article art1)
                {
                    int id1 = Integer.parseInt(art1.idArticle.replace("_",""));
                    int id2 = Integer.parseInt(art2.idArticle.replace("_",""));
                    return  id1-id2;
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return arrayOfArticles;
    }

}