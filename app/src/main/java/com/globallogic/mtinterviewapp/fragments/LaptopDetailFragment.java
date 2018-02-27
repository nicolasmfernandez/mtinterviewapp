package com.globallogic.mtinterviewapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.globallogic.mtinterviewapp.R;
import com.globallogic.mtinterviewapp.activities.LaptopDetailActivity;
import com.globallogic.mtinterviewapp.base.BaseFragment;
import com.globallogic.mtinterviewapp.entities.Laptop;
import com.globallogic.mtinterviewapp.mvp.model.LaptopDetailModel;
import com.globallogic.mtinterviewapp.mvp.presenter.LaptopDetailPresenter;
import com.globallogic.mtinterviewapp.mvp.view.LaptopDetailView;
import com.globallogic.mtinterviewapp.utils.BusProvider;

import butterknife.BindString;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by nicolasfernandez on 2/26/18.
 */

public class LaptopDetailFragment extends BaseFragment {

  public static String fragmentTag = "LaptopDetailFragment";
  private static String laptopKey = "laptop";
  private Unbinder mUnbinder;
  private LaptopDetailPresenter mPresenter;

  @BindString(R.string.laptop_detail_title)
  String mTitle;

  public static LaptopDetailFragment newInstance(Laptop laptop) {
    LaptopDetailFragment fragment = new LaptopDetailFragment();
    Bundle args = new Bundle();
    args.putParcelable(laptopKey, laptop);
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    ViewGroup parentView = (ViewGroup) super.onCreateView(inflater, container, savedInstanceState);
    View baseview = inflater.inflate(R.layout.fragment_laptop_detail, parentView, true);
    mUnbinder = ButterKnife.bind(this, baseview);
    return baseview;
  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    Laptop laptop = getArguments().getParcelable(laptopKey);
    mPresenter = new LaptopDetailPresenter(new LaptopDetailModel(BusProvider.getInstance(), laptop),
            new LaptopDetailView((LaptopDetailActivity) getActivity(), BusProvider.getInstance()));
    mPresenter.onActivityCreated();
  }

  @Override
  public String getTagText() {
    return fragmentTag;
  }

  @Override
  public boolean onBackPressed() {
    return mPresenter.onBackPressed();
  }

  @Override
  public void onResume() {
    super.onResume();
    mPresenter.onResume();
    setTitle(mTitle);
  }

  @Override
  public void onPause() {
    super.onPause();
    mPresenter.onPause();
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    if (mUnbinder != null) {
      mUnbinder.unbind();
    }
  }

}
