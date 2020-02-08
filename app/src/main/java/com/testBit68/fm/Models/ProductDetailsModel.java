package com.testBit68.fm.Models;

import com.google.gson.annotations.SerializedName;

public class ProductDetailsModel {
    @SerializedName("id")
    private int  CategoryId;
    @SerializedName("name")
    private String CategoryName;
    @SerializedName("weight")
    private String Weight;
    @SerializedName("price")
    private String price;

    @SerializedName("product_img")
    private String CategoryImage;


    public int getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(int categoryId) {
        CategoryId = categoryId;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public String getWeight() {
        return Weight;
    }

    public void setWeight(String weight) {
        Weight = weight;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCategoryImage() {
        return CategoryImage;
    }

    public void setCategoryImage(String categoryImage) {
        CategoryImage = categoryImage;
    }

}
