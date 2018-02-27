package com.globallogic.mtinterviewapp.mvp.view;

import com.globallogic.mtinterviewapp.entities.Laptop;

/**
 * Created by nicolasfernandez on 2/26/18.
 */

public interface ILaptopDetailView extends IBaseView {

  void showLaptopDetail(Laptop laptop);

  void dismissDetailScreen();

}
