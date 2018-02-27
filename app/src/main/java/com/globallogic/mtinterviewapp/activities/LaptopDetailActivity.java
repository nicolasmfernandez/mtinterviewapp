package com.globallogic.mtinterviewapp.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.globallogic.mtinterviewapp.R;
import com.globallogic.mtinterviewapp.base.BaseActivity;
import com.globallogic.mtinterviewapp.entities.Laptop;
import com.globallogic.mtinterviewapp.flow.ScreenManager;

/**
 * Created by nicolasfernandez on 2/26/18.
 */

public class LaptopDetailActivity extends BaseActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_container);

    Toolbar toolbar = findViewById(R.id.custom_toolbar);
    setSupportActionBar(toolbar);
    if (getSupportActionBar() != null) {
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    Laptop laptop = getIntent().getParcelableExtra("Laptop");
    if (laptop != null) {
      ScreenManager.showLaptopDetailFragment(this, laptop);
    }
  }

  @Override
  public boolean onSupportNavigateUp() {
    onBackPressed();
    return true;
  }

}
