package com.globallogic.mtinterviewapp.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.globallogic.mtinterviewapp.R;
import com.globallogic.mtinterviewapp.base.BaseActivity;
import com.globallogic.mtinterviewapp.flow.ScreenManager;
import com.globallogic.mtinterviewapp.fragments.MainFragment;

public class MainActivity extends BaseActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_container);

    Toolbar toolbar = findViewById(R.id.custom_toolbar);
    setSupportActionBar(toolbar);

    if (savedInstanceState == null) {
      ScreenManager.showMainFragment(this);
    }
  }

  public void hideRefreshLoader() {
    MainFragment fragment = (MainFragment) getSupportFragmentManager().findFragmentByTag(MainFragment.fragmentTag);
    if (fragment != null) {
      fragment.hideRefreshLoader();
    }
  }
}
