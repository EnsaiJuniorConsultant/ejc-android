package com.ejc.appli.article;

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

}