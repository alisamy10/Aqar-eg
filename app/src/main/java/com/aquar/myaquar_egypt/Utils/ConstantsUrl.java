package com.aquar.myaquar_egypt.Utils;

/**
 * Created by aswany on 2/7/19.
 */

public class ConstantsUrl {

    //ExtraKeys
    public static final String userDataBundleKey = "userData";

    //Urls


    private static final String BasicURL = "http://aquar.me/myaquar_eg/api/";
    public static final String Home = BasicURL + "allprojects";
    public static final String Registration = BasicURL + "registration";
    public static final String Login = BasicURL + "login";
    public static final String Event = BasicURL + "events_news";
    public static final String allFavorites = BasicURL + "allfavorites";
    public static final String favorite = BasicURL + "favorite";


    //post Function
    //params key : id =  value :1
    public static final String SingleProject = BasicURL + "single_project";

    //getFunction
    //params key : id = value :1
    // Residential = 1 , Commercial =2 ,Medical =3, HolidayHome = 4, LunchSoon=5
    public static final String Category = BasicURL + "category_projects";


}
