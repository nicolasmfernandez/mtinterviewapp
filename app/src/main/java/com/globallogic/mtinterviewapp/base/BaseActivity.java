package com.globallogic.mtinterviewapp.base;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.globallogic.mtinterviewapp.R;
import com.globallogic.mtinterviewapp.interfaces.BackHandlerInterface;

/**
 * Created by nicolasfernandez on 2/23/18.
 */

public abstract class BaseActivity extends AppCompatActivity implements BackHandlerInterface {

  protected BaseFragment selectedFragment;
  protected ProgressBar mProgressBar;
  protected boolean isShowingProgressBar;

  @Override
  protected void onStart() {
    initProgressBar();
    super.onStart();
  }

  @Override
  protected void onResume() {
    if (isShowingProgressBar) {
      showLoader();

    }
    super.onResume();
  }

  @Override
  protected void onPause() {
    if (isShowingProgressBar) {
      hideLoader();
    }
    super.onPause();
  }

  @Override
  protected void onStop() {
    if (isShowingProgressBar) {
      hideLoader();
    }
    super.onStop();
  }

  public void initProgressBar() {
    mProgressBar = this.findViewById(R.id.progressBar);
  }

  public void showLoader() {
    isShowingProgressBar = true;
    if (mProgressBar != null) {
      mProgressBar.post(new Runnable() {
        @Override
        public void run() {
          if (isShowingProgressBar) {
            mProgressBar.setVisibility(View.VISIBLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
          }
        }
      });
    }
  }

  public void hideLoader() {
    isShowingProgressBar = false;
    if (mProgressBar != null) {
      mProgressBar.setVisibility(View.INVISIBLE);
      getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }
  }

  @Override
  public void onBackPressed() {
    if (selectedFragment == null || !selectedFragment.onBackPressed()) {
      // Selected fragment did not consume the back press event.
      super.onBackPressed();
    }
  }

  @Override
  public void setSelectedFragment(BaseFragment fr) {
    selectedFragment = fr;
  }

}
