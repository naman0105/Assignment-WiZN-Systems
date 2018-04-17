package com.example.naman.task3;

/**
 * Created by naman on 17-Apr-18.
 */

public class Item {
    private String title, genre, year, link;

    public Item() {
    }

    public Item(String title, String genre, String year, String link) {
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public String getLink() {
        return link;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}