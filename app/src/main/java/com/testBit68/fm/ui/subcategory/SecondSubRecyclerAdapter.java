package com.testBit68.fm.ui.subcategory;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.testBit68.fm.R;

import java.util.ArrayList;
import java.util.List;

public class SecondSubRecyclerAdapter extends RecyclerView.Adapter<SecondSubRecyclerAdapter.ViewHolder> {
    Context context;
    Activity activity;

    public void FirstSubAdapter(Activity activity,Context context) {
        this.context = context;

        this.activity=activity;
    }
    @NonNull
    @Override
    public SecondSubRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_product, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;    }

    @Override
    public void onBindViewHolder(@NonNull SecondSubRecyclerAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {




        public ViewHolder(View itemView) {
            super(itemView);



        }

    }
}
