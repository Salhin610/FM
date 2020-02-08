package com.testBit68.fm.ui.subcategory;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.testBit68.fm.Models.CategoriesModel;
import com.testBit68.fm.Models.ProductDetailsModel;
import com.testBit68.fm.R;
import com.testBit68.fm.ui.home.HomeViewModel;

import java.util.ArrayList;
import java.util.List;

public class SubCategoryFragment extends Fragment {

    private SubCategoryViewModel subCategoryViewModel;
    ImageView SubCatImage;
    HomeViewModel homeViewModel;
    List<ProductDetailsModel> productDetailsModel0=new ArrayList<>();
    List<ProductDetailsModel> productDetailsModel1=new ArrayList<>();
    RecyclerView FirstSubRecycler,SecondSubRecycler;
    TabLayout tabLayout;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        if (container != null) {
            container.removeAllViews();
        }
        String first_sub = "",second_sub="";
        subCategoryViewModel =
                ViewModelProviders.of(this).get(SubCategoryViewModel.class);

        View root = inflater.inflate(R.layout.fragment_sub_category, container, false);
        FirstSubRecycler=(RecyclerView)root.findViewById(R.id.FirstSubRecycler) ;
        SecondSubRecycler=(RecyclerView)root.findViewById(R.id.SecondSubRecycler) ;
        tabLayout=(TabLayout)root.findViewById(R.id.tabLayout);

        Bundle bundle=getArguments();
        if (bundle!=null){
            int length=bundle.getInt("length");
            int length0=bundle.getInt("length0");
           first_sub= bundle.getString("first_name");
            second_sub= bundle.getString("second_name");





            for (int i=0;i<length;i++){

                ProductDetailsModel productDetailsModel=new ProductDetailsModel();
                productDetailsModel.setCategoryName(bundle.getString("Name"+i));
                productDetailsModel.setCategoryImage(bundle.getString("image"+i));
                productDetailsModel.setPrice(bundle.getString("Price"+i));
                productDetailsModel.setWeight(bundle.getString("Weight"+i));
                Log.e("weight = ",bundle.getString("image"+i));

                productDetailsModel0.add(productDetailsModel);

            }
            for (int j=0;j<length0;j++){

                ProductDetailsModel productDetailsModel=new ProductDetailsModel();
                productDetailsModel.setCategoryName(bundle.getString("Name0"+j));
                productDetailsModel.setCategoryImage(bundle.getString("image0"+j));
                productDetailsModel.setPrice(bundle.getString("Price0"+j));
                productDetailsModel.setWeight(bundle.getString("Weight0"+j));
                Log.e("Image0 = ",bundle.getString("image0"+j)+"");

                productDetailsModel1.add(productDetailsModel);

            }


        }

        tabLayout.addTab(tabLayout.newTab().setText(first_sub+"x"));
        tabLayout.addTab(tabLayout.newTab().setText(second_sub+"x"));









        SubCatImage=(ImageView)root.findViewById(R.id.SubCatImage);
        Glide.with(getContext())
                .asBitmap()
                .load("")
                .into(SubCatImage);


        return root;
    }
}