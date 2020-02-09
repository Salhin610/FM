package com.testBit68.fm.ui.home;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.slidingpanelayout.widget.SlidingPaneLayout;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.testBit68.fm.MainActivity;
import com.testBit68.fm.Models.CategoriesModel;
import com.testBit68.fm.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    Context context;
    private RecyclerView myRecyclerView;
    private HomeAdapter myRecyclerViewAdapter;

    private LinearLayoutManager linearLayoutManagerVertical;
    private GridLayoutManager gridLayoutManagerVertical;
    HashMap<String,String> images=new HashMap<>();

    boolean callHappened=false;


    SliderLayout sliderLayout;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        if (container != null) {
            container.removeAllViews();
        }
        ((MainActivity)getActivity()).getSupportActionBar().setTitle("Home");

        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        context=this.getActivity();
        myRecyclerView = (RecyclerView)root.findViewById(R.id.myrecyclerview);
        sliderLayout=(SliderLayout) root.findViewById(R.id.slider);

        homeViewModel.GetHomeViewModel();

        linearLayoutManagerVertical = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        gridLayoutManagerVertical = new GridLayoutManager(getContext(), 2,LinearLayoutManager.VERTICAL, false);
        gridLayoutManagerVertical.setSpanSizeLookup(new MySpanSizeLookup(3, 1, 2));
        myRecyclerView.setLayoutManager(linearLayoutManagerVertical);
        myRecyclerView.setLayoutManager(gridLayoutManagerVertical);



        for (String name : images.keySet()) {
            TextSliderView textSliderView = new TextSliderView(getActivity());
            // initialize a SliderLayout
            textSliderView
                    .image(images.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name + "");

            sliderLayout.addSlider(textSliderView);
        }

        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderLayout.setDuration(4000);


        homeViewModel.CategoriesMutableLiveData.observe(this, new Observer<List<CategoriesModel>>() {
            @Override
            public void onChanged(List<CategoriesModel> list) {
                myRecyclerViewAdapter = new HomeAdapter((AppCompatActivity) context,getContext(),list);

                myRecyclerView.setAdapter(myRecyclerViewAdapter);
                for (int i=0;i<list.size();i++){
                    images.put("image"+i,list.get(i).getCategoryImage());
                }
                for (String name : images.keySet()) {
                    TextSliderView textSliderView = new TextSliderView(getActivity());
                    // initialize a SliderLayout
                    textSliderView
                            .image(images.get(name))
                            .setScaleType(BaseSliderView.ScaleType.Fit);

                    //add your extra information
                    textSliderView.bundle(new Bundle());
                    textSliderView.getBundle()
                            .putString("extra", name + "");

                    sliderLayout.addSlider(textSliderView);
                }

                sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
                sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
                sliderLayout.setDuration(4000);

            }
        });



        return root;
    }



}