package com.aquar.myaquar_egypt.Model.Search;

import java.util.List;

public class SearchModelObject {


    private List<String> max_price;
    private List<String> min_price;
    private List<String> max_area;
    private List<String> min_area;
    private List<String> max_badrooms;
    private List<String> min_badrooms;
    private List<String> max_bathrooms;
    private List<String> min_bathrooms;
    private List<String> locations;

    public List<String> getMax_price() {
        return max_price;
    }

    public void setMax_price(List<String> max_price) {
        this.max_price = max_price;
    }

    public List<String> getMin_price() {
        return min_price;
    }

    public void setMin_price(List<String> min_price) {
        this.min_price = min_price;
    }

    public List<String> getMax_area() {
        return max_area;
    }

    public void setMax_area(List<String> max_area) {
        this.max_area = max_area;
    }

    public List<String> getMin_area() {
        return min_area;
    }

    public void setMin_area(List<String> min_area) {
        this.min_area = min_area;
    }

    public List<String> getMax_badrooms() {
        return max_badrooms;
    }

    public void setMax_badrooms(List<String> max_badrooms) {
        this.max_badrooms = max_badrooms;
    }

    public List<String> getMin_badrooms() {
        return min_badrooms;
    }

    public void setMin_badrooms(List<String> min_badrooms) {
        this.min_badrooms = min_badrooms;
    }

    public List<String> getMax_bathrooms() {
        return max_bathrooms;
    }

    public void setMax_bathrooms(List<String> max_bathrooms) {
        this.max_bathrooms = max_bathrooms;
    }

    public List<String> getMin_bathrooms() {
        return min_bathrooms;
    }

    public void setMin_bathrooms(List<String> min_bathrooms) {
        this.min_bathrooms = min_bathrooms;
    }

    public List<String> getLocations() {
        return locations;
    }

    public void setLocations(List<String> locations) {
        this.locations = locations;
    }
}
