package com.globallogic.mtinterviewapp.mvp.view;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.support.annotation.Nullable;

import java.lang.ref.WeakReference;

/**
 * Created by nicolasfernandez on 2/23/18.
 */

public class ActivityView<T> {
  private WeakReference<T> activityRef;

  public ActivityView(T activity) {
    activityRef = new WeakReference<T>(activity);
  }

  @Nullable
  public T getActivity() {
    return activityRef.get();
  }

  @Nullable
  public Context getContext() {
    return (Context) getActivity();
  }

  @Nullable
  public FragmentManager getFragmentManager() {
    Activity activity = (Activity) getActivity();
    return (activity != null) ? activity.getFragmentManager() : null;
  }
}
