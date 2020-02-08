package com.testBit68.fm.calls;


import com.testBit68.fm.Models.CategoriesModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {


    ///////////////////////////////////////////////////////////////////////////////
    //Get Categories
    @GET("categories")
    Call<List<CategoriesModel>> GetCategories();

}


