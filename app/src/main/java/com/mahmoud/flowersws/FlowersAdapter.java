package com.mahmoud.flowersws;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import Fragments.ItemDetailsFragment;

/**
 * Created by HP Pro 3500 on 16/11/2016.
 */

public class FlowersAdapter extends RecyclerView.Adapter<FlowersAdapter.MyViewHolder> {

    private List<Flower> flowersList;
    Context context;
    final String BASE_URL = "http://services.hanselandpetal.com/photos/";
    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        public TextView name, cat, instructions,price;
        public ImageView photo;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.tv_name);
            cat = (TextView) view.findViewById(R.id.tv_Category);
            price = (TextView) view.findViewById(R.id.tv_Price);
            photo = (ImageView) view.findViewById(R.id.iv_Photo);
        }
    }
    public FlowersAdapter(List<Flower> flowersList, Context context)
    {
        this.flowersList = flowersList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Flower flower = this.flowersList.get(position);
        holder.name.setText(flower.getName());
        holder.cat.setText(flower.getCategory());
        holder.price.setText(flower.getPrice()+"");
        Picasso.with(this.context).load(BASE_URL+flower.getPhoto()).placeholder(R.drawable.loadings).error(R.mipmap.ic_launcher).into(holder.photo);

        holder.photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AppCompatActivity)context).getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.details,ItemDetailsFragment.getinstance(flower))
                        .addToBackStack("")
                        .commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return flowersList.size();
    }



}
