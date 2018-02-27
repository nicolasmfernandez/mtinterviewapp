package com.globallogic.mtinterviewapp.base;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.globallogic.mtinterviewapp.R;
import com.globallogic.mtinterviewapp.views.CustomSwipeRefresh;

/**
 * Created by nicolasfernandez on 2/23/18.
 */

public abstract class BaseSwipeRefreshFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

  protected CustomSwipeRefresh mSwipeRefreshBar;
  protected boolean isShowingSwipeRefresh;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (savedInstanceState != null) {
      isShowingSwipeRefresh = savedInstanceState.getBoolean("isShowingSwipeRefresh");
    }
  }

  @Override
  public void onStop() {
    if (isShowingSwipeRefresh) {
      hideSyncProgress();
    }
    super.onStop();
  }

  @Override
  public void onPause() {
    if (isShowingSwipeRefresh) {
      hideSyncProgress();
    }
    super.onPause();
  }

  @Override
  public void onResume() {
    super.onResume();
    if (isShowingSwipeRefresh) {
      showSyncProgress();
    }
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
  }

  @Override
  public void onSaveInstanceState(Bundle outState) {
    outState.putBoolean("isShowingSwipeRefresh", isShowingSwipeRefresh);
    super.onSaveInstanceState(outState);
  }

  /**
   * Shows {@link SwipeRefreshLayout} on top of the screen. Override this
   * method to provide additional actions but remember to call super.
   */
  protected void showSyncProgress() {
    isShowingSwipeRefresh = true;
    if (mSwipeRefreshBar != null) {
      mSwipeRefreshBar.post(new Runnable() {
        @Override
        public void run() {
          mSwipeRefreshBar.setRefreshing(isShowingSwipeRefresh);
        }
      });
    }
  }


  /**
   * Hides {@link SwipeRefreshLayout}.Override this method to provide
   * additional actions but remember to call super.
   */
  protected void hideSyncProgress() {
    isShowingSwipeRefresh = false;
    mSwipeRefreshBar.setRefreshing(isShowingSwipeRefresh);
  }


  /**
   * Call this on your fragment's {@link #onActivityCreated(Bundle)} if you
   * want to disable swipe refresh gesture.
   */
  protected void disableRefreshSwipe() {
    mSwipeRefreshBar.setEnabled(false);
  }

  /**
   * Enables swipe refresh gesture.
   */
  protected void enableSwipeGesture() {
    mSwipeRefreshBar.setEnabled(true);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View baseview = inflater.inflate(R.layout.fragment_base, container, false);
    mSwipeRefreshBar = baseview.findViewById(R.id.swipe_container);
    mSwipeRefreshBar.setOnRefreshListener(this);
    return baseview;
  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    mSwipeRefreshBar.setColorSchemeResources(R.color.colorGray, R.color.colorPrimary, R.color.colorPrimaryDark, R.color.colorAccent);
  }

}
