package com.example.bds_mobile.model;

import java.io.Serializable;

public class Post implements Serializable {
    private Long postId;
    private String postTitle;
    private String description;
    private Integer numberOfRooms;
    private Double squareArea;
    private Double price;
    private String detailsAddress;
    private String imageUrl;
    private String videoUrl;
    private boolean isSold;
    private String typeOfApartment;
    private Long authorId;
    private Integer totalLike;

    public Post(Long postId, String postTitle, String description, Integer numberOfRooms, Double squareArea, Double price, String detailsAddress, String imageUrl, String videoUrl, boolean isSold, String typeOfApartment, Long authorId, Integer totalLike) {
        this.postId = postId;
        this.postTitle = postTitle;
        this.description = description;
        this.numberOfRooms = numberOfRooms;
        this.squareArea = squareArea;
        this.price = price;
        this.detailsAddress = detailsAddress;
        this.imageUrl = imageUrl;
        this.videoUrl = videoUrl;
        this.isSold = isSold;
        this.typeOfApartment = typeOfApartment;
        this.authorId = authorId;
        this.totalLike = totalLike;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(Integer numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public Double getSquareArea() {
        return squareArea;
    }

    public void setSquareArea(Double squareArea) {
        this.squareArea = squareArea;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDetailsAddress() {
        return detailsAddress;
    }

    public void setDetailsAddress(String detailsAddress) {
        this.detailsAddress = detailsAddress;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public boolean isSold() {
        return isSold;
    }

    public void setSold(boolean sold) {
        isSold = sold;
    }

    public String getTypeOfApartment() {
        return typeOfApartment;
    }

    public void setTypeOfApartment(String typeOfApartment) {
        this.typeOfApartment = typeOfApartment;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Integer getTotalLike() {
        return totalLike;
    }

    public void setTotalLike(Integer totalLike) {
        this.totalLike = totalLike;
    }
}
