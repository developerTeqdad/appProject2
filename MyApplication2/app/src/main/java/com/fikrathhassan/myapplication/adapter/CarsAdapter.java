package com.fikrathhassan.myapplication.adapter;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fikrathhassan.myapplication.R;
import com.fikrathhassan.myapplication.model.CarRegistration;

import java.util.List;

public class CarsAdapter extends RecyclerView.Adapter<CarsAdapter.MyViewHolder> {

    private List<CarRegistration> carsList;
    private ClickListener clickListener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView item_name, item_price, item_model, item_color;
        public ImageView item_image;

        public MyViewHolder(View view) {
            super(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.onItemClick(getAdapterPosition());
                }
            });
            item_name = (TextView) view.findViewById(R.id.item_name);
            item_price = (TextView) view.findViewById(R.id.item_price);
            item_model = (TextView) view.findViewById(R.id.item_model);
            item_color = (TextView) view.findViewById(R.id.item_color);
            item_image = (ImageView) view.findViewById(R.id.item_image);
        }
    }


    public CarsAdapter(List<CarRegistration> carsList, ClickListener clickListener) {
        this.carsList = carsList;
        this.clickListener = clickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cars, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        CarRegistration car = carsList.get(position);
        holder.item_name.setText(car.getName());
        holder.item_price.setText("Rs. "+car.getPrice());
        holder.item_model.setText("Model: "+car.getModel());
        holder.item_color.setText("Color: "+car.getColor());
        Glide.with(holder.itemView.getContext()).load(car.getImage()).into(holder.item_image);
    }

    @Override
    public int getItemCount() {
        return carsList.size();
    }

    public interface ClickListener {
        void onItemClick(int position);
    }
}