package com.testBit68.fm.ui.ProductDetails;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.testBit68.fm.MainActivity;
import com.testBit68.fm.Models.CategoriesModel;
import com.testBit68.fm.R;


public class ProductDetailsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container != null) {
            container.removeAllViews();
        }
        CategoriesModel categoriesModel = null;

        Bundle bundle=getArguments();
        if (bundle!=null){
            Log.e("ff",bundle.getString("CatName"));
             categoriesModel.SelectedSecondSub=bundle.getString("CatName");

        }
        ((MainActivity)getActivity()).getSupportActionBar().setTitle(categoriesModel.SelectedSecondSub);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_details, container, false);
    }





}
