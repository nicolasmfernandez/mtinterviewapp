package com.globallogic.mtinterviewapp.mvp.presenter;

import com.globallogic.mtinterviewapp.mvp.model.LaptopDetailModel;
import com.globallogic.mtinterviewapp.mvp.view.ILaptopDetailView;
import com.globallogic.mtinterviewapp.utils.BusProvider;

/**
 * Created by nicolasfernandez on 2/26/18.
 */

public class LaptopDetailPresenter {

  private LaptopDetailModel mModel;
  private ILaptopDetailView mView;

  public LaptopDetailPresenter(LaptopDetailModel model, ILaptopDetailView view) {
    this.mModel = model;
    this.mView = view;
  }

  public void onActivityCreated() {
    mView.showLaptopDetail(mModel.getLaptop());
  }

  public void onResume() {
    BusProvider.register(this);
  }

  public void onPause() {
    BusProvider.unregister(this);
  }

  public boolean onBackPressed() {
    mView.dismissDetailScreen();
    return true;
  }
}
