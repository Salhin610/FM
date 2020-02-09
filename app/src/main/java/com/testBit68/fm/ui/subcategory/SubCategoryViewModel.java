package com.testBit68.fm.ui.subcategory;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.testBit68.fm.Models.CategoriesModel;
import com.testBit68.fm.Models.ProductDetailsModel;
import com.testBit68.fm.calls.APIClient;
import com.testBit68.fm.calls.APIInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubCategoryViewModel extends ViewModel {

    APIInterface apiInterface;

    MutableLiveData<List<CategoriesModel>> SubCategoriesMutableLiveData=new MutableLiveData<>();


    public void GetSubViewModel() {



    }


}