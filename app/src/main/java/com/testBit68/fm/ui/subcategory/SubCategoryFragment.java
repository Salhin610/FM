package com.testBit68.fm.ui.subcategory;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.testBit68.fm.MainActivity;
import com.testBit68.fm.Models.CategoriesModel;
import com.testBit68.fm.Models.ProductDetailsModel;
import com.testBit68.fm.R;
import com.testBit68.fm.ui.home.HomeViewModel;

import java.util.ArrayList;
import java.util.List;

public class SubCategoryFragment extends Fragment {


    ImageView SubCatImage;
    Context context;
    RecyclerView FirstSubRecycler,SecondSubRecycler;

    TabLayout tabLayout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        if (container != null) {
            container.removeAllViews();
        }
context=this.getActivity();
        View root = inflater.inflate(R.layout.fragment_sub_category, container, false);
        FirstSubRecycler=(RecyclerView)root.findViewById(R.id.FirstSubRecycler) ;
        SecondSubRecycler=(RecyclerView)root.findViewById(R.id.SecondSubRecycler) ;
        tabLayout=(TabLayout)root.findViewById(R.id.tabLayout);
        CategoriesModel categoriesModel = null;
        Bundle bundle=getArguments();
        if (bundle!=null){
            int length=bundle.getInt("length");
            int length0=bundle.getInt("length0");
           categoriesModel.firstSub= bundle.getString("first_name");
            categoriesModel.secondSub= bundle.getString("second_name");
            categoriesModel.CatImage= bundle.getString("CategoryImage");
            categoriesModel.CatImage0= bundle.getString("CategoryImage1");


            for (int i=0;i<length;i++){

                ProductDetailsModel productDetailsModel=new ProductDetailsModel();
                productDetailsModel.setCategoryName(bundle.getString("Name"+i));
                productDetailsModel.setCategoryImage(bundle.getString("image"+i));
                productDetailsModel.setPrice(bundle.getString("Price"+i));
                productDetailsModel.setWeight(bundle.getString("Weight"+i));
//                Log.e("img",bundle.getString("image"+i));

                categoriesModel.firstlist.add(productDetailsModel);

            }
            for (int j=0;j<length0;j++){

                ProductDetailsModel productDetailsModel=new ProductDetailsModel();
                productDetailsModel.setCategoryName(bundle.getString("Name0"+j));
                productDetailsModel.setCategoryImage(bundle.getString("image0"+j));
                productDetailsModel.setPrice(bundle.getString("Price0"+j));
                productDetailsModel.setWeight(bundle.getString("Weight0"+j));
//                Log.e("img",bundle.getString("image0"+j));

                categoriesModel.secondlist.add(productDetailsModel);

            }

        }
        TabLayout.Tab tab1 = tabLayout.getTabAt(0);
        TabLayout.Tab tab2 = tabLayout.getTabAt(1);
        tab1.setText(categoriesModel.firstSub);
        tab2.setText(categoriesModel.secondSub);

        final FirstSubRecyclerAdapter adapter=new FirstSubRecyclerAdapter();
        FirstSubRecycler.setLayoutManager(new GridLayoutManager(getContext(),2));
        FirstSubRecycler.setAdapter(adapter);
//        Log.e("s",categoriesModel.firstlist.size()+"");
//        Log.e("s1",categoriesModel.secondlist.size()+"");
        adapter.FirstSubRecyclerAdapter((AppCompatActivity) context,getContext(),categoriesModel.firstlist);

        final SecondSubRecyclerAdapter adapter1=new SecondSubRecyclerAdapter();
        SecondSubRecycler.setLayoutManager(new GridLayoutManager(getContext(),2));
        SecondSubRecycler.setAdapter(adapter1);
        adapter1.SecondSubRecyclerAdapter((AppCompatActivity) context,getContext(),categoriesModel.secondlist);

tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        int position=tab.getPosition();

        if (position==0){

            FirstSubRecycler.setVisibility(View.VISIBLE);
            SecondSubRecycler.setVisibility(View.GONE);
        }else{

            FirstSubRecycler.setVisibility(View.GONE);
            SecondSubRecycler.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {


    }
});
        ((MainActivity)getActivity()).getSupportActionBar().setTitle(categoriesModel.firstSub+" & "+categoriesModel.secondSub);


//        MainActivity mainActivity = new MainActivity();
//        mainActivity.setTitle(categoriesModel.firstSub+" & "+categoriesModel.secondSub);

//getActivity().setTitle();
        SubCatImage=(ImageView)root.findViewById(R.id.SubCatImage);
        Glide.with(getContext())
                .asBitmap()
                .load(categoriesModel.CatImage)
                .into(SubCatImage);


        return root;
    }
}