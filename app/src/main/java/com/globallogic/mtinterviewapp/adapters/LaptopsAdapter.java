package com.globallogic.mtinterviewapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.globallogic.mtinterviewapp.R;
import com.globallogic.mtinterviewapp.entities.Laptop;
import com.globallogic.mtinterviewapp.interfaces.OnRecyclerItemClickListener;
import com.globallogic.mtinterviewapp.mvp.model.MainAdapterModel;
import com.globallogic.mtinterviewapp.mvp.view.MainAdapterView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nicolasfernandez on 2/23/18.
 */

public class LaptopsAdapter extends RecyclerView.Adapter<LaptopsAdapter.LaptopViewHolder> implements MainAdapterModel, MainAdapterView {

  private final Context context;
  private List<Laptop> laptopsList;
  private OnRecyclerItemClickListener onRecyclerItemClickListener;

  public LaptopsAdapter(Context context) {
    this.context = context;
    this.laptopsList = new ArrayList<>();
  }

  @Override
  public LaptopViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView = LayoutInflater.from(context)
            .inflate(R.layout.item_laptop_view, parent, false);
    return new LaptopViewHolder(itemView);
  }

  @Override
  public void onBindViewHolder(LaptopViewHolder holder, final int position) {
    Laptop laptop = getLaptop(position);

    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (onRecyclerItemClickListener != null) {
          onRecyclerItemClickListener.onItemClick(LaptopsAdapter.this, position);
        }
      }
    });

    holder.titleTextView.setText(laptop.getTitle());
    holder.descriptionTextView.setText(laptop.getDescription());
    Picasso.with(context).load(laptop.getImage())
            .error(R.drawable.ic_icon_alert)
            .placeholder(R.drawable.ic_laptop_placeholder)
            .resize(230, 230)
            .centerCrop()
            .into(holder.laptopImageView);
  }

  @Override
  public int getItemCount() {
    return getSize();
  }

  @Override
  public void setAll(ArrayList<Laptop> laptops) {
    laptopsList = laptops;
  }

  @Override
  public void add(Laptop laptop) {
    laptopsList.add(laptop);
  }

  @Override
  public Laptop getLaptop(int position) {
    return laptopsList.get(position);
  }

  @Override
  public int getSize() {
    return laptopsList.size();
  }

  @Override
  public void refresh() {
    notifyDataSetChanged();
  }

  public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener onRecyclerItemClickListener) {
    this.onRecyclerItemClickListener = onRecyclerItemClickListener;
  }

  class LaptopViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_title)
    TextView titleTextView;

    @BindView(R.id.tv_description)
    TextView descriptionTextView;

    @BindView(R.id.iv_laptop_icon)
    ImageView laptopImageView;

    public LaptopViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }

}