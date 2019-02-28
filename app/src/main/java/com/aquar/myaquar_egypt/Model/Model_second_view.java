package com.aquar.myaquar_egypt.Model;

public class Model_second_view {




    int image, love ,share;
    String name_secound,price_second,number_second;

    public Model_second_view(int image, int love, int share, String name_secound, String price_second, String number_second) {
        this.image = image;
        this.love = love;
        this.share = share;
        this.name_secound = name_secound;
        this.price_second = price_second;
        this.number_second = number_second;
    }

    public int getLove() {
        return love;
    }

    public int getShare() {
        return share;
    }

    public String getName_secound() {
        return name_secound;
    }

    public String getPrice_second() {
        return price_second;
    }

    public String getNumber_second() {
        return number_second;
    }

    public int getImage() {
        return image;
    }
}
