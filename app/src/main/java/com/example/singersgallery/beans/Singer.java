package com.example.singersgallery.beans;

public class Singer {
    private int id;
    private String name;
    private String genre;
    private String img;
    private float rating;
    private static int counter = 0;

    public Singer(String name, String genre, String img, float rating) {
        this.id = ++counter;
        this.name = name;
        this.genre = genre;
        this.img = img;
        this.rating = rating;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getGenre() { return genre; }
    public String getImg() { return img; }
    public float getRating() { return rating; }
    public void setName(String name) { this.name = name; }
    public void setImg(String img) { this.img = img; }
    public void setGenre(String genre) { this.genre = genre; }
    public void setRating(float rating) { this.rating = rating; }
}