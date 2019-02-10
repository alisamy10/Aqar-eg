package com.aquar.android.myaquar_egypt.Model;

public class modle_home_fragment {
    private String textView_1_2_type,textView_1_2_price,textView_1_2_number,
            textView_2_3_type,textView_2_3_price,textView_2_3_number;

    private int imageView_1_2_share,imageView_1_2_love,imageView_2_3_share,imageView_2_3_love ,imageView_one_one,imageView_two_four;

    public modle_home_fragment(String textView_1_2_type, String textView_1_2_price, String textView_1_2_number, String textView_2_3_type, String textView_2_3_price, String textView_2_3_number, int imageView_1_2_share, int imageView_1_2_love, int imageView_2_3_share, int imageView_2_3_love, int imageView_one_one, int imageView_two_four) {
        this.textView_1_2_type = textView_1_2_type;
        this.textView_1_2_price = textView_1_2_price;
        this.textView_1_2_number = textView_1_2_number;
        this.textView_2_3_type = textView_2_3_type;
        this.textView_2_3_price = textView_2_3_price;
        this.textView_2_3_number = textView_2_3_number;
        this.imageView_1_2_share = imageView_1_2_share;
        this.imageView_1_2_love = imageView_1_2_love;
        this.imageView_2_3_share = imageView_2_3_share;
        this.imageView_2_3_love = imageView_2_3_love;
        this.imageView_one_one = imageView_one_one;
        this.imageView_two_four = imageView_two_four;
    }
public modle_home_fragment(String textView_1_2_type, String textView_1_2_price, String textView_1_2_number, int imageView_1_2_share, int imageView_1_2_love,  int imageView_one_one){

    this.textView_1_2_type = textView_1_2_type;
    this.textView_1_2_price = textView_1_2_price;
    this.textView_1_2_number = textView_1_2_number;
    this.imageView_1_2_share = imageView_1_2_share;
    this.imageView_1_2_love = imageView_1_2_love;
    this.imageView_one_one = imageView_one_one;
}
    public String getTextView_1_2_type() {
        return textView_1_2_type;
    }

    public String getTextView_1_2_price() {
        return textView_1_2_price;
    }

    public String getTextView_1_2_number() {
        return textView_1_2_number;
    }

    public String getTextView_2_3_type() {
        return textView_2_3_type;
    }

    public String getTextView_2_3_price() {
        return textView_2_3_price;
    }

    public String getTextView_2_3_number() {
        return textView_2_3_number;
    }

    public int getImageView_1_2_share() {
        return imageView_1_2_share;
    }

    public int getImageView_1_2_love() {
        return imageView_1_2_love;
    }

    public int getImageView_2_3_share() {
        return imageView_2_3_share;
    }

    public int getImageView_2_3_love() {
        return imageView_2_3_love;
    }

    public int getImageView_one_one() {
        return imageView_one_one;
    }

    public int getImageView_two_four() {
        return imageView_two_four;
    }
}
