package com.globallogic.mtinterviewapp.mvp.model;

import com.globallogic.mtinterviewapp.entities.Laptop;

import java.util.ArrayList;

/**
 * Created by nicolasfernandez on 2/23/18.
 */

public interface MainAdapterModel {

  void setAll(ArrayList<Laptop> laptops);

  void add(Laptop laptop);

  Laptop getLaptop(int position);

  int getSize();
}