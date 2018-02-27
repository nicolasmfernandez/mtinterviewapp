package com.globallogic.mtinterviewapp.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;

import com.globallogic.mtinterviewapp.interfaces.BackHandlerInterface;

/**
 * Created by nicolasfernandez on 2/23/18.
 */

public abstract class BaseFragment extends Fragment {

  protected BackHandlerInterface backHandlerInterface;

  public abstract String getTagText();

  public abstract boolean onBackPressed();

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (!(getActivity() instanceof BackHandlerInterface)) {
      throw new ClassCastException("Hosting activity must implement BackHandlerInterface");
    } else {
      backHandlerInterface = (BackHandlerInterface) getActivity();
    }
  }

  @Override
  public void onStart() {
    super.onStart();
    backHandlerInterface.setSelectedFragment(this);
  }


  protected BaseActivity getBaseActivity() {
    try {
      return (BaseActivity) getActivity();
    } catch (ClassCastException ex) {
      throw new ClassCastException("Base fragments must be attached to BaseActivities");
    }
  }

  protected void setTitle(int aTitleRes) {
    getBaseActivity().setTitle(aTitleRes);
  }

  protected void setTitle(String aTitleRes) {
    getBaseActivity().setTitle(aTitleRes);
  }

  protected void hideHomeAsUp() {
    ActionBar bar = getBaseActivity().getSupportActionBar();
    if (bar != null) {
      bar.setDisplayHomeAsUpEnabled(false);
    }
  }

}
