package com.aquar.android.myaquar_egypt.Model;

public class modle_home_fragment {
    private String textView_1_2_type,textView_1_2_price,textView_1_2_number
           ;

    private int imageView_one_one, love;

    public modle_home_fragment(String textView_1_2_type, String textView_1_2_price, String textView_1_2_number, int love,int imageView_one_one) {
        this.textView_1_2_type = textView_1_2_type;
        this.textView_1_2_price = textView_1_2_price;
        this.textView_1_2_number = textView_1_2_number;
        this.love=love;
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




    public int getImageView_one_one() {
        return imageView_one_one;
    }

public  int getLove(){return love;}
}
