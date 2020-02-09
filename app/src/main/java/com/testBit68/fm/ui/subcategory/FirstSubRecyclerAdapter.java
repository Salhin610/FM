package com.testBit68.fm.ui.subcategory;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.testBit68.fm.Models.ProductDetailsModel;
import com.testBit68.fm.R;
import com.testBit68.fm.ui.ProductDetails.ProductDetailsFragment;

import java.util.ArrayList;
import java.util.List;

public class FirstSubRecyclerAdapter extends RecyclerView.Adapter<FirstSubRecyclerAdapter.ViewHolder> {

    private List<ProductDetailsModel> list=new ArrayList<>();
    Context context;
    private AppCompatActivity activity;

    public void FirstSubRecyclerAdapter(AppCompatActivity activity,Context context, List<ProductDetailsModel> list){
        this.list=list;
        this.context=context;

        this.activity=activity;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_product, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final NavController mNavController = Navigation.findNavController( activity, R.id.nav_host_fragment);

        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProductDetailsFragment fragment=new ProductDetailsFragment();

                Bundle bundle=new Bundle();
                bundle.putString("CatName",list.get(position).getCategoryName());

                fragment.setArguments(bundle);
                FragmentManager fragmentManager=activity.getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.nav_host_fragment,fragment).commit();
                mNavController.navigate(R.id.ProductDetails);

            }
        });

        Glide.with(context)
                .asBitmap()
                .load(list.get(position).getCategoryImage())
                .into(holder.img);

        Log.e("dd",list.get(position).getCategoryName());
        holder.title.setText(list.get(position).getCategoryName());
        holder.weight.setText(list.get(position).getWeight());
        holder.price.setText(list.get(position).getPrice());


    }

    @Override
    public int getItemCount() {
        return list.size() ;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView title,price,weight;



        public ViewHolder(View itemView) {
            super(itemView);
            img=(ImageView)itemView.findViewById(R.id.image);
            title=(TextView)itemView.findViewById(R.id.title);
            price=(TextView)itemView.findViewById(R.id.price);
            weight=(TextView)itemView.findViewById(R.id.weight);


        }

    }
}
