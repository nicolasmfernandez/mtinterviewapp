package com.globallogic.mtinterviewapp.mvp.model;

import com.globallogic.mtinterviewapp.entities.Laptop;
import com.squareup.otto.Bus;

/**
 * Created by nicolasfernandez on 2/26/18.
 */

public class LaptopDetailModel {

  private Bus mBus;
  private Laptop mLaptop;

  public LaptopDetailModel() {
    init();
  }

  public LaptopDetailModel(Bus bus) {
    init();
    this.mBus = bus;
  }

  public LaptopDetailModel(Bus bus, Laptop laptop) {
    init();
    this.mBus = bus;
    this.mLaptop = laptop;
  }

  public void init() {
    this.mBus = null;
  }

  public void post(Object obj) {
    if (mBus != null) {
      mBus.post(obj);
    }
  }

  public Laptop getLaptop() {
    return mLaptop;
  }

}
