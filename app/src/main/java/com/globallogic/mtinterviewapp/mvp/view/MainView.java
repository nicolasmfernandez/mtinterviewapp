package com.globallogic.mtinterviewapp.mvp.view;

import com.globallogic.mtinterviewapp.activities.MainActivity;
import com.globallogic.mtinterviewapp.entities.Laptop;
import com.globallogic.mtinterviewapp.flow.ScreenManager;
import com.globallogic.mtinterviewapp.fragments.CustomAlertDialogFragment;
import com.squareup.otto.Bus;

import butterknife.ButterKnife;

/**
 * Created by nicolasfernandez on 2/23/18.
 */

public class MainView extends ActivityView<MainActivity> implements IMainView {

  private final Bus mBus;
  MainAdapterView mMainAdapterView;

  public MainView(MainActivity activity, Bus bus, MainAdapterView adapter) {
    super(activity);
    this.mBus = bus;
    this.mMainAdapterView = adapter;
    ButterKnife.bind(this, activity);

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
  public void refreshList() {
    mMainAdapterView.refresh();
  }

  @Override
  public void hideRefreshLoader() {
    if (getActivity() != null) {
      getActivity().hideRefreshLoader();
    }
  }

  @Override
  public void laptopsLoadFailure(int errMsg) {
    CustomAlertDialogFragment.newInstance(errMsg).show(getFragmentManager(), "alertDialogFragment");
  }

  @Override
  public void laptopsLoadFailure(String errMsg) {
    CustomAlertDialogFragment.newInstance(errMsg).show(getFragmentManager(), "alertDialogFragment");
  }

  @Override
  public void laptopsLoadFailure(int errMsg, boolean finish) {
    CustomAlertDialogFragment.newInstance(errMsg, finish, getActivity()).show(getFragmentManager(), "alertDialogFragment");
  }

  @Override
  public void showRefreshError() {

  }

  @Override
  public void goToDetailScreen(Laptop laptop) {
    ScreenManager.showLaptopDetailScreen(getActivity(), laptop);
  }

  //==============================================================================================
  // GETTERS AND SETTERS
  //==============================================================================================

  public static class HideRefreshLoaderEvent {

  }

  private class LaptopItemClickedEvent {
    public int position;

    public LaptopItemClickedEvent(int pos) {
      position = pos;
    }
  }

}