package com.globallogic.mtinterviewapp.fragments;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.globallogic.mtinterviewapp.R;
import com.globallogic.mtinterviewapp.activities.MainActivity;
import com.globallogic.mtinterviewapp.adapters.LaptopsAdapter;
import com.globallogic.mtinterviewapp.base.BaseSwipeRefreshFragment;
import com.globallogic.mtinterviewapp.interfaces.OnRecyclerItemClickListener;
import com.globallogic.mtinterviewapp.mvp.model.MainModel;
import com.globallogic.mtinterviewapp.mvp.presenter.MainPresenter;
import com.globallogic.mtinterviewapp.mvp.view.MainView;
import com.globallogic.mtinterviewapp.utils.BusProvider;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by nicolasfernandez on 2/23/18.
 */

public class MainFragment extends BaseSwipeRefreshFragment {

  public static String fragmentTag = "MainFragment";
  private Unbinder mUnbinder;
  private MainPresenter mPresenter;

  @BindString(R.string.main_fragment_title)
  String mTitle;

  @BindView(R.id.rv_laptops)
  RecyclerView laptopsRecycler;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    ViewGroup parentView = (ViewGroup) super.onCreateView(inflater, container, savedInstanceState);
    View baseview = inflater.inflate(R.layout.fragment_main, parentView, true);
    mUnbinder = ButterKnife.bind(this, baseview);
    return baseview;
  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    LaptopsAdapter adapter = new LaptopsAdapter(getActivity());
    laptopsRecycler.setHasFixedSize(true);
    DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(laptopsRecycler.getContext(),
            LinearLayoutManager.VERTICAL);
    laptopsRecycler.addItemDecoration(dividerItemDecoration);
    laptopsRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
    laptopsRecycler.setAdapter(adapter);
    mPresenter = new MainPresenter(new MainModel(BusProvider.getInstance()), adapter,
            new MainView((MainActivity) getActivity(), BusProvider.getInstance(), adapter));
    adapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
      @Override
      public void onItemClick(RecyclerView.Adapter adapter1, int position) {
        mPresenter.onLaptopItemClick(position);
      }
    });
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
  public void onStart() {
    super.onStart();
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
  public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
  }

  @Override
  public void onRefresh() {
    mPresenter.onRefresh();
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    if (mUnbinder != null) {
      mUnbinder.unbind();
    }
  }

  public void hideRefreshLoader() {
    hideSyncProgress();
  }

}