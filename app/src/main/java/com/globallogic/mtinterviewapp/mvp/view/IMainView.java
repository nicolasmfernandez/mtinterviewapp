package com.globallogic.mtinterviewapp.mvp.view;

import com.globallogic.mtinterviewapp.entities.Laptop;

/**
 * Created by nicolasfernandez on 2/23/18.
 */

public interface IMainView extends IBaseView {

  void refreshList();

  void hideRefreshLoader();

  void laptopsLoadFailure(int errMsg);

  void laptopsLoadFailure(String errMsg);

  void laptopsLoadFailure(int errMsg, boolean finish);

  void showRefreshError();

  void goToDetailScreen(Laptop laptop);

}