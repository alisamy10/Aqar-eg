package com.aquar.myaquar_egypt.Model.Search;

import java.util.List;

public class SearchModelObject {


    private List<Integer> max_price;
    private List<Integer> min_price;
    private List<Integer> max_area;
    private List<Integer> min_area;
    private List<Integer> max_badrooms;
    private List<Integer> min_badrooms;
    private List<Integer> max_bathrooms;
    private List<Integer> min_bathrooms;
    private List<String> locations;

    public List<Integer> getMax_price() {
        return max_price;
    }

    public void setMax_price(List<Integer> max_price) {
        this.max_price = max_price;
    }

    public List<Integer> getMin_price() {
        return min_price;
    }

    public void setMin_price(List<Integer> min_price) {
        this.min_price = min_price;
    }

    public List<Integer> getMax_area() {
        return max_area;
    }

    public void setMax_area(List<Integer> max_area) {
        this.max_area = max_area;
    }

    public List<Integer> getMin_area() {
        return min_area;
    }

    public void setMin_area(List<Integer> min_area) {
        this.min_area = min_area;
    }

    public List<Integer> getMax_badrooms() {
        return max_badrooms;
    }

    public void setMax_badrooms(List<Integer> max_badrooms) {
        this.max_badrooms = max_badrooms;
    }

    public List<Integer> getMin_badrooms() {
        return min_badrooms;
    }

    public void setMin_badrooms(List<Integer> min_badrooms) {
        this.min_badrooms = min_badrooms;
    }

    public List<Integer> getMax_bathrooms() {
        return max_bathrooms;
    }

    public void setMax_bathrooms(List<Integer> max_bathrooms) {
        this.max_bathrooms = max_bathrooms;
    }

    public List<Integer> getMin_bathrooms() {
        return min_bathrooms;
    }

    public void setMin_bathrooms(List<Integer> min_bathrooms) {
        this.min_bathrooms = min_bathrooms;
    }

    public List<String> getLocations() {
        return locations;
    }

    public void setLocations(List<String> locations) {
        this.locations = locations;
    }
}
