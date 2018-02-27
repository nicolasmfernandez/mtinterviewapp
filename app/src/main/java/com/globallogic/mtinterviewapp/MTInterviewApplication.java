package com.globallogic.mtinterviewapp;

import android.app.Application;

/**
 * Created by nicolasfernandez on 2/23/18.
 */

public class MTInterviewApplication extends Application {

  private static MTInterviewApplication sInstance;

  public static MTInterviewApplication getInstance() {
    return sInstance;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    sInstance = this;
  }

}
