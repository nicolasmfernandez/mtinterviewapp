package com.globallogic.mtinterviewapp.mvp.view;

import android.widget.ImageView;
import android.widget.TextView;

import com.globallogic.mtinterviewapp.R;
import com.globallogic.mtinterviewapp.activities.LaptopDetailActivity;
import com.globallogic.mtinterviewapp.entities.Laptop;
import com.squareup.otto.Bus;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nicolasfernandez on 2/26/18.
 */

public class LaptopDetailView extends ActivityView<LaptopDetailActivity> implements ILaptopDetailView {

  @BindView(R.id.tv_title)
  TextView laptopTitleTextView;

  @BindView(R.id.tv_description)
  TextView laptopDescriptionTextView;

  @BindView(R.id.iv_laptop_icon)
  ImageView laptopIconImageView;

  private Bus mBus;

  public LaptopDetailView(LaptopDetailActivity activity, Bus bus) {
    super(activity);
    ButterKnife.bind(this, activity);
    this.mBus = bus;
  }

  //==============================================================================================
  // VIEW INTERFACE METHODS
  //==============================================================================================

  //Loader hide/show
  @Override
  public void showLoading() {
    if (getActivity() != null) {
      getActivity().showLoader();
    }
  }

  @Override
  public void hideLoading() {
    if (getActivity() != null) {
      getActivity().hideLoader();
    }
  }

  @Override
  public void showLaptopDetail(Laptop laptop) {

    laptopTitleTextView.setText(laptop.getTitle());
    laptopDescriptionTextView.setText(laptop.getDescription());
    Picasso.with(getContext()).load(laptop.getImage())
            .error(R.drawable.ic_icon_alert)
            .placeholder(R.drawable.ic_laptop_placeholder)
            .resize(230, 230)
            .centerCrop()
            .into(laptopIconImageView);

  }

  @Override
  public void dismissDetailScreen() {
    if (getActivity() != null) {
      getActivity().finish();
    }
  }
  //==============================================================================================
  //==============================================================================================

}
