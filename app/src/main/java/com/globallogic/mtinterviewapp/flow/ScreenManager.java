package com.globallogic.mtinterviewapp.flow;

import android.app.Activity;

import com.globallogic.mtinterviewapp.R;
import com.globallogic.mtinterviewapp.base.BaseActivity;
import com.globallogic.mtinterviewapp.entities.Laptop;
import com.globallogic.mtinterviewapp.fragments.LaptopDetailFragment;
import com.globallogic.mtinterviewapp.fragments.MainFragment;

/**
 * Created by nicolasfernandez on 2/23/18.
 * <p>
 * Class that manages screen flows.
 */

public class ScreenManager {

  //    Activities

  public static void showLaptopDetailScreen(Activity origin, Laptop laptop) {
    origin.startActivity(IntentFactory.getLaptopDetailIntent(origin).putExtra("Laptop", laptop));
  }

  //    Fragments

  public static void showMainFragment(BaseActivity origin) {
    origin.getSupportFragmentManager().beginTransaction().replace(R.id.container, new MainFragment(), MainFragment.fragmentTag).commit();
  }

  public static void showLaptopDetailFragment(BaseActivity origin, Laptop laptop) {
    origin.getSupportFragmentManager().beginTransaction().replace(R.id.container, LaptopDetailFragment.newInstance(laptop), LaptopDetailFragment.fragmentTag).commit();
  }
}
