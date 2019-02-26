package com.aquar.android.myaquar_egypt.Model.Login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by aswany on 2/19/19.
 */

public class userObjPOJO {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("job_title")
    @Expose
    private String job_title;
    @SerializedName("image")
    @Expose
    private String image;


    /**
     * No args constructor for use in serialization
     *
     */
    public userObjPOJO() {
    }

    /**
     *
     * @param id
     * @param phone
     * @param email
     * @param name
     * @param job_title
     * @param image
     */

    public userObjPOJO(Integer id, String name, String email, String phone,String job_title,String image) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.job_title=job_title;
        this.image=image;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
