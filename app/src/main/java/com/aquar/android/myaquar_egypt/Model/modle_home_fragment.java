package com.aquar.android.myaquar_egypt.Model;

public class modle_home_fragment {
    String start,end;
    int image ;
    String text_one,text_two;

    public modle_home_fragment(String start, String end, int image, String text_one, String text_two) {
        this.start = start;
        this.end = end;
        this.image = image;
        this.text_one = text_one;
        this.text_two = text_two;
    }


    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public int getImage() {
        return image;
    }

    public String getText_one() {
        return text_one;
    }

    public String getText_two() {
        return text_two;
    }
}
