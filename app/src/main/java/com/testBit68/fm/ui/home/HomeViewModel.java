package com.testBit68.fm.ui.home;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

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

import static android.widget.Toast.LENGTH_LONG;

public class HomeViewModel extends ViewModel {

    APIInterface apiInterface;




    MutableLiveData<List<CategoriesModel>> CategoriesMutableLiveData=new MutableLiveData<>();

    public void GetHomeViewModel() {
        apiInterface = APIClient.getClient().create(APIInterface .class);



        Call<List<CategoriesModel>> call = apiInterface.GetCategories();
         final List<CategoriesModel> categoriesModel=new ArrayList<>();
        final List<ProductDetailsModel>[] productDetailsModelList = new List[]{new ArrayList<>()};
        CategoriesMutableLiveData.setValue(categoriesModel);
        call.enqueue(new Callback<List<CategoriesModel>>() {
            @Override
            public void onResponse(Call<List<CategoriesModel>> call, Response<List<CategoriesModel>> response) {
                if (response.isSuccessful()) {


                    Boolean AdFlag=false;
                    for (int i=0;i<response.body().size();i++) {
                        CategoriesModel categoriesModel1=new CategoriesModel();
                        if (i%2==0){
                            categoriesModel1.setCategoryId(-1);
                            categoriesModel1.setCategoryName("ad"+i);
                            categoriesModel1.setCategoryImage("add image link");

                            AdFlag=true;
                            categoriesModel.add(categoriesModel1);
                        }else {
                            int x=i;
                            if (AdFlag){
                                x=x-1;
                                i--;
                                AdFlag=false;
                            }
                            categoriesModel1.setCategoryId(response.body().get(x).getCategoryId());
                            categoriesModel1.setCategoryName(response.body().get(x).getCategoryName());
                            categoriesModel1.setCategoryImage(response.body().get(x).getCategoryImage());
                            productDetailsModelList[0] =new ArrayList<>();
                            for (int j=0;j<response.body().get(x).getProductDetailsModelList().size();j++){
                            ProductDetailsModel productDetailsModel=new ProductDetailsModel();
                            productDetailsModel.setCategoryId(response.body().get(x).getProductDetailsModelList().get(j).getCategoryId());
                                productDetailsModel.setCategoryName(response.body().get(x).getProductDetailsModelList().get(j).getCategoryName());
//                                Log.e("sssss",response.body().get(x).getProductDetailsModelList().get(j).getCategoryName());
                                productDetailsModel.setWeight(response.body().get(x).getProductDetailsModelList().get(j).getWeight());
                                productDetailsModel.setPrice(response.body().get(x).getProductDetailsModelList().get(j).getPrice());
                                productDetailsModel.setCategoryImage(response.body().get(x).getProductDetailsModelList().get(j).getCategoryImage());


                                productDetailsModelList[0].add(productDetailsModel);
                            }
                            categoriesModel1.setProductDetailsModelList(productDetailsModelList[0]);
                            categoriesModel.add(categoriesModel1);
                        }
                    }
                    CategoriesMutableLiveData.setValue(categoriesModel);

                }

                else {


                    try {
                        JSONObject jobError = new JSONObject(response.errorBody().string());
//                        Log.e("error asta",resource.message+"");

                        Log.d("error", jobError.getString("message"));
                        Log.d("error", "1");


                    } catch (JSONException e) {
                        e.printStackTrace();

                        Log.d("error", "2");
                    } catch (IOException e) {
                        Log.d("error", "3");
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<List<CategoriesModel>> call, Throwable t) {
                Log.d("failure", "fail");

            }
        });


    }



}