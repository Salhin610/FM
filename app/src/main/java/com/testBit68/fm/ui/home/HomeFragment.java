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

import com.testBit68.fm.MainActivity;
import com.testBit68.fm.Models.CategoriesModel;
import com.testBit68.fm.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    Context context;
    private RecyclerView myRecyclerView;
    private HomeAdapter myRecyclerViewAdapter;

    private LinearLayoutManager linearLayoutManagerVertical;
    private GridLayoutManager gridLayoutManagerVertical;

    boolean callHappened=false;

    SlidingPaneLayout slidingPaneLayout;

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
        slidingPaneLayout=(SlidingPaneLayout)root.findViewById(R.id.slidingPaneLayout);
        homeViewModel.GetHomeViewModel();

        linearLayoutManagerVertical = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        gridLayoutManagerVertical = new GridLayoutManager(getContext(), 2,LinearLayoutManager.VERTICAL, false);
        gridLayoutManagerVertical.setSpanSizeLookup(new MySpanSizeLookup(3, 1, 2));
        myRecyclerView.setLayoutManager(linearLayoutManagerVertical);
        myRecyclerView.setLayoutManager(gridLayoutManagerVertical);


        slidingPaneLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeViewModel.GetHomeViewModel();

            }
        });
        homeViewModel.CategoriesMutableLiveData.observe(this, new Observer<List<CategoriesModel>>() {
            @Override
            public void onChanged(List<CategoriesModel> list) {
                myRecyclerViewAdapter = new HomeAdapter((AppCompatActivity) context,getContext(),list);

                myRecyclerView.setAdapter(myRecyclerViewAdapter);

            }
        });



        return root;
    }



}