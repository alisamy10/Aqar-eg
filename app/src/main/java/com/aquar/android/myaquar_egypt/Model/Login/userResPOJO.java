package com.aquar.android.myaquar_egypt.Model.Login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by aswany on 2/19/19.
 */

public class userResPOJO {
    @SerializedName("userInfo")
    @Expose
    private userObjPOJO userInfo;

    /**
     * No args constructor for use in serialization
     */
    public userResPOJO() {
    }

    /**
     * @param userInfo
     */
    public userResPOJO(userObjPOJO userInfo) {
        super();
        this.userInfo = userInfo;
    }

    public userObjPOJO getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(userObjPOJO userInfo) {
        this.userInfo = userInfo;
    }
}
