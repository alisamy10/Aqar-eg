package com.aquar.android.myaquar_egypt.Model.ModelsOfProjectDetails;

import java.util.List;

public class ModelObjectsOfProjectDetails {


    /**
     * id : 1
     * type : department
     * category : residential
     * developer : HDG
     * project : Azad
     * favorite : false
     * rooms : 3
     * bathsrooms : 1
     * price : 5000
     * area : 80
     * garden : Not available
     * accommodation : null
     * location : New Cairo
     * video : null
     * viewer_360 : http://aquar.me/myaquar_eg/uploads/products/360_2.jpg
     * description : prime location with an attractive payment plan, 10% Downpayment - 10% after 3 Months - 10% on Delivery &  Equal installments over 6 Years
     * slider_images : [{"image_id":2,"image_url":"http://aquar.me/myaquar_eg/uploads/projects/Azad_Mob_5.jpg"},{"image_id":3,"image_url":"http://aquar.me/myaquar_eg/uploads/projects/Azad_Mob_7.jpg"},{"image_id":4,"image_url":"http://aquar.me/myaquar_eg/uploads/projects/Azad_Mob_9.jpg"},{"image_id":5,"image_url":"http://aquar.me/myaquar_eg/uploads/projects/Azad_Mob_11.jpg"},{"image_id":6,"image_url":"http://aquar.me/myaquar_eg/uploads/projects/Azad_Mob_31.jpg"},{"image_id":7,"image_url":"http://aquar.me/myaquar_eg/uploads/projects/azad_pqroject.jpg"}]
     */

    private int id;
    private String type;
    private String category;
    private String developer;
    private String project;
    private String favorite;
    private int rooms;
    private int bathsrooms;
    private int price;
    private int area;
    private String garden;
    private Object accommodation;
    private String location;
    private Object video;
    private String viewer_360;
    private String description;
    private List<SliderImagesBean> slider_images;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public int getBathsrooms() {
        return bathsrooms;
    }

    public void setBathsrooms(int bathsrooms) {
        this.bathsrooms = bathsrooms;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public String getGarden() {
        return garden;
    }

    public void setGarden(String garden) {
        this.garden = garden;
    }

    public Object getAccommodation() {
        return accommodation;
    }

    public void setAccommodation(Object accommodation) {
        this.accommodation = accommodation;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Object getVideo() {
        return video;
    }

    public void setVideo(Object video) {
        this.video = video;
    }

    public String getViewer_360() {
        return viewer_360;
    }

    public void setViewer_360(String viewer_360) {
        this.viewer_360 = viewer_360;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<SliderImagesBean> getSlider_images() {
        return slider_images;
    }

    public void setSlider_images(List<SliderImagesBean> slider_images) {
        this.slider_images = slider_images;
    }

    public static class SliderImagesBean {
        /**
         * image_id : 2
         * image_url : http://aquar.me/myaquar_eg/uploads/projects/Azad_Mob_5.jpg
         */

        private int image_id;
        private String image_url;

        public int getImage_id() {
            return image_id;
        }

        public void setImage_id(int image_id) {
            this.image_id = image_id;
        }

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }
    }
}
