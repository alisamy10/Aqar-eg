package com.aquar.myaquar_egypt.Utils;

import android.content.Context;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.util.Log;
import android.widget.Toast;

import com.aquar.myaquar_egypt.Model.Error.ErrorModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Locale;

/**
 * Created by aswany on 3/3/19.
 */

public class myUtils {
    //QueryValue
    public static final int Residential = 1;
    public static final int Commercial = 2;
    public static final int Medical = 3;
    public static final int HolidayHome = 4;
    public static final int LunchSoon = 5;

    public static void handleError(Context context, String errorRes, int errorStatusCode) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        ErrorModel error = gson.fromJson(errorRes, ErrorModel.class);
        Log.d("ErrorRes", errorStatusCode + ":" + error.getMsg() + " : " + error.getCode());
        Toast.makeText(context, error.getMsg(), Toast.LENGTH_SHORT).show();


    }

    public static Boolean isConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();

    }
    public static void setLocale(Context context) {

        Locale locale = new Locale("en");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        context.getResources().updateConfiguration(config,
                context.getResources().getDisplayMetrics());


    }
}
