package com.globallogic.mtinterviewapp.flow;

import android.content.Context;
import android.content.Intent;

import com.globallogic.mtinterviewapp.activities.LaptopDetailActivity;

/**
 * Created by nicolasfernandez on 2/23/18.
 */

public class IntentFactory {

  public static Intent getLaptopDetailIntent(Context context) {
    return new Intent(context, LaptopDetailActivity.class);
  }

}
