package com.example.rk.photoblog;

public class User {

    public String Image, Name;

    public User() {
    }



    public String getImage() {

        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public User(String image, String name) {

        Image = image;
        Name = name;
    }
}
