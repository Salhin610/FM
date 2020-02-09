package com.testBit68.fm.ui.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.testBit68.fm.Models.CategoriesModel;
import com.testBit68.fm.R;
import com.testBit68.fm.ui.subcategory.SubCategoryFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    NavController mNavController;

    private List<CategoriesModel> list;
    private Context context;
    AppCompatActivity activity;

    public HomeAdapter(AppCompatActivity activity,Context context, List<CategoriesModel>list){

        this.activity=activity;
        this.list=list;
        this.context=context;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case 0:
                view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_main_first, parent, false);
                HomeAdapter.ViewHolder0 viewHolder0 = new HomeAdapter.ViewHolder0(view);
                return viewHolder0;
            case 2:
                view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_main_second, parent, false);
                HomeAdapter.ViewHolder1 viewHolder1 = new HomeAdapter.ViewHolder1(view);
                return viewHolder1;
            default:
                throw new IllegalStateException("Unexpected value: " + viewType);
        }
    }

    @Override
    public int getItemViewType(int position) {
        // Just as an example, return 0 or 2 depending on position
        // Note that unlike in ListView adapters, types don't have to be contiguous
        if (position%3==0){
            return 2;
        }else {
            return 0;
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position)
    {
        final NavController mNavController = Navigation.findNavController( activity, R.id.nav_host_fragment);

        switch (holder.getItemViewType()) {
            case 0:
                HomeAdapter.ViewHolder0 viewHolder0 = (HomeAdapter.ViewHolder0) holder;
                Glide.with(context)
                        .asBitmap()
                        .load(list.get(position).getCategoryImage())
                        .into(viewHolder0.CategoryImage);

                viewHolder0.CategoryTitle.setText(list.get(position).getCategoryName());
                viewHolder0.CategoryImage.setOnClickListener(new View.OnClickListener() {
                    @Override

                    public void onClick(View view) {

                        SubCategoryFragment fragment=new SubCategoryFragment();

                        Bundle bundle=new Bundle();
                        int x=position+1;
                        if (position%3==2){
                            x=position-1;
                        }
                        bundle.putString("first_name",list.get(position).getCategoryName());
                        bundle.putString("second_name",list.get(x).getCategoryName());
                        bundle.putString("CategoryImage1",list.get(x).getCategoryImage());
                        bundle.putString("CategoryImage",list.get(position).getCategoryImage());
                        bundle.putInt("length",list.get(position).getProductDetailsModelList().size());
                        bundle.putInt("length0",list.get(x).getProductDetailsModelList().size());
                        for (int i=0;i<list.get(position).getProductDetailsModelList().size();i++){
//                            Log.e("name",list.get(position).getProductDetailsModelList().get(i).getCategoryName()+"");
                            bundle.putString("image"+i,list.get(position).getProductDetailsModelList().get(i).getCategoryImage());
                            bundle.putString("Name"+i,list.get(position).getProductDetailsModelList().get(i).getCategoryName());
                            bundle.putString("Price"+i,list.get(position).getProductDetailsModelList().get(i).getPrice());
                            bundle.putString("Weight"+i,list.get(position).getProductDetailsModelList().get(i).getWeight());

                        }
                        for (int j=0;j<list.get(x).getProductDetailsModelList().size();j++){
//                            Log.e("name",list.get(x).getProductDetailsModelList().get(j).getCategoryName()+"");

                            bundle.putString("image0"+j,list.get(x).getProductDetailsModelList().get(j).getCategoryImage());
                            bundle.putString("Name0"+j,list.get(x).getProductDetailsModelList().get(j).getCategoryName());
                            bundle.putString("Price0"+j,list.get(x).getProductDetailsModelList().get(j).getPrice());
                            bundle.putString("Weight0"+j,list.get(x).getProductDetailsModelList().get(j).getWeight());

                        }

                        fragment.setArguments(bundle);
                        FragmentManager fragmentManager=activity.getSupportFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment,fragment).commit();
                        mNavController.navigate(R.id.SubCategory);

                    }
                });
                break;
            case 2:
                HomeAdapter.ViewHolder1 viewHolder1 = (HomeAdapter.ViewHolder1) holder;




                break;

        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }




    public class ViewHolder0 extends RecyclerView.ViewHolder {

            ImageView CategoryImage;
            TextView CategoryTitle;

        public ViewHolder0(View itemView) {
            super(itemView);
            CategoryImage=(ImageView)itemView.findViewById(R.id.CategoryImage);
            CategoryTitle=(TextView)itemView.findViewById(R.id.CategoryTitle);




        }

    }

    public class ViewHolder1 extends RecyclerView.ViewHolder {

        ImageView AdImage;
        TextView AdDescription;

        public ViewHolder1(View itemView) {
            super(itemView);

            AdImage=(ImageView)itemView.findViewById(R.id.AdImage);
            AdDescription=(TextView)itemView.findViewById(R.id.AdDescription);


        }

    }

}
