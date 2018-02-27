package com.globallogic.mtinterviewapp.mvp.presenter;

import com.globallogic.mtinterviewapp.entities.Laptop;
import com.globallogic.mtinterviewapp.mvp.model.MainAdapterModel;
import com.globallogic.mtinterviewapp.mvp.model.MainModel;
import com.globallogic.mtinterviewapp.mvp.view.IMainView;
import com.globallogic.mtinterviewapp.mvp.view.MainView;
import com.globallogic.mtinterviewapp.utils.BusProvider;
import com.squareup.otto.Subscribe;

/**
 * Created by nicolasfernandez on 2/23/18.
 */

public class MainPresenter {

  private MainModel mModel;
  private IMainView mView;
  private MainAdapterModel mMainAdapterModel;

  public MainPresenter(MainModel model, MainAdapterModel adapterModel, MainView view) {
    this.mModel = model;
    this.mView = view;
    this.mMainAdapterModel = adapterModel;
  }

  public void onActivityCreated() {
  }

  public void onResume() {
    BusProvider.register(this);
    mView.showLoading();
    mModel.loadLaptops();
  }

  public void onPause() {
    BusProvider.unregister(this);
  }

  public boolean onBackPressed() {
    mView.hideLoading();
    mView.hideRefreshLoader();
    return mModel.cancelLaptopsCall();
  }

  //==============================================================================================
  // VIEW SUBSCRIPTIONS
  //==============================================================================================
  public void onLaptopItemClick(int position) {
    Laptop selectedLaptop = mMainAdapterModel.getLaptop(position);
    mView.goToDetailScreen(selectedLaptop);
  }

  public void onRefresh() {
    mModel.loadLaptops();
  }

  //==============================================================================================
  //==============================================================================================


  //==============================================================================================
  // MODEL SUBSCRIPTIONS
  //==============================================================================================
  @Subscribe
  public void onLaptopsObtained(MainModel.GetLaptopsSuccessEvent onLaptopsObtainedEvent) {
    mMainAdapterModel.setAll(onLaptopsObtainedEvent.laptopsList);
    mView.refreshList();
    mView.hideRefreshLoader();
    mView.hideLoading();

  }

  @Subscribe
  public void onLaptopsFailure(MainModel.GetLaptopsFailureEvent failureEvent) {
    mView.laptopsLoadFailure(failureEvent.getErrorMessage());
    mView.hideRefreshLoader();
    mView.hideLoading();
  }

  @Subscribe
  public void onLaptopsFailureMessage(MainModel.GetLaptopsFailureMessageEvent failureEvent) {
    mView.laptopsLoadFailure(failureEvent.getErrorMessage());
    mView.hideRefreshLoader();
    mView.hideLoading();
  }

  //==============================================================================================
  //==============================================================================================

}