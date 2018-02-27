package com.globallogic.mtinterviewapp.fragments;

import android.app.DialogFragment;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.globallogic.mtinterviewapp.R;
import com.globallogic.mtinterviewapp.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by nicolasfernandez on 2/23/18.
 */

public class CustomAlertDialogFragment extends DialogFragment {

  protected Unbinder mUnbinder;

  private int errorMsgId;
  private String errorMessage;
  private boolean finishAct = false;
  private static BaseActivity mActivity;
  @BindView(R.id.tv_alert_message)
  TextView alertMessageTextView;
  @BindView(R.id.bt_ok)
  Button okButton;

  public static CustomAlertDialogFragment newInstance(int errMsg) {
    Bundle args = new Bundle();
    args.putInt("errMsg", errMsg);
    CustomAlertDialogFragment customAlertFragment = getCustomAlertDialogFragment(args);
    return customAlertFragment;
  }

  public static CustomAlertDialogFragment newInstance(String errorMessage) {
    Bundle args = new Bundle();
    args.putString("errorMessage", errorMessage);
    CustomAlertDialogFragment customAlertFragment = getCustomAlertDialogFragment(args);
    return customAlertFragment;
  }

  public static CustomAlertDialogFragment newInstance(int errMsg, boolean finish, BaseActivity activity) {
    Bundle args = new Bundle();
    args.putInt("errMsg", errMsg);
    args.putBoolean("finish", finish);
    mActivity = activity;
    CustomAlertDialogFragment customAlertFragment = getCustomAlertDialogFragment(args);
    return customAlertFragment;
  }

  @NonNull
  private static CustomAlertDialogFragment getCustomAlertDialogFragment(Bundle args) {
    CustomAlertDialogFragment customAlertFragment = new CustomAlertDialogFragment();
    customAlertFragment.setCancelable(false);
    customAlertFragment.setArguments(args);
    return customAlertFragment;
  }


  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setStyle(STYLE_NO_FRAME, R.style.MyDialogTheme);
    errorMsgId = getArguments().getInt("errMsg", -1);
    errorMessage = getArguments().getString("errorMessage", "generic error");//TODO set generic error
    finishAct = getArguments().getBoolean("finish");
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View baseview = inflater.inflate(R.layout.fragment_custom_alert, container, true);
    getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorGrayOverlay)));

    mUnbinder = ButterKnife.bind(this, baseview);
    return baseview;
  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    if (errorMsgId != -1) {
      alertMessageTextView.setText(getString(errorMsgId));
    } else {
      alertMessageTextView.setText(errorMessage);
    }
  }

  @Override
  public void onStart() {
    super.onStart();
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    if (mUnbinder != null) {
      mUnbinder.unbind();
    }
  }


  @OnClick(R.id.bt_ok)
  public void onOkButtonClicked() {
    if (!finishAct) {
      dismiss();
    } else {
      mActivity.finish();
    }
  }

}