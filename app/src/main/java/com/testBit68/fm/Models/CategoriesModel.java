package com.testBit68.fm.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoriesModel {
    @SerializedName("id")
    private int  CategoryId;
    @SerializedName("name")
    private String CategoryName;
    @SerializedName("category_img")
    private String CategoryImage;

    @SerializedName("products")
    private List<ProductDetailsModel>productDetailsModelList;

    public List<ProductDetailsModel> getProductDetailsModelList() {
        return productDetailsModelList;
    }

    public void setProductDetailsModelList(List<ProductDetailsModel> productDetailsModelList) {
        this.productDetailsModelList = productDetailsModelList;
    }


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

    public String getCategoryImage() {
        return CategoryImage;
    }

    public void setCategoryImage(String categoryImage) {
        CategoryImage = categoryImage;
    }
}
