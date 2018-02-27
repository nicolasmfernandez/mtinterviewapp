package com.globallogic.mtinterviewapp.mvp.model;

import com.globallogic.mtinterviewapp.R;
import com.globallogic.mtinterviewapp.entities.Laptop;
import com.globallogic.mtinterviewapp.rest.RestManager;
import com.globallogic.mtinterviewapp.rest.requests.LaptopsApi;
import com.squareup.otto.Bus;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by nicolasfernandez on 2/23/18.
 */

public class MainModel {

  private Bus mBus;
  private LaptopsApi mLaptopsApi;
  private Call laptopsCall;
  private int callState;
  private ArrayList<Laptop> laptops;

  public MainModel() {
    init();
  }

  public MainModel(Bus bus) {
    init();
    this.mBus = bus;
  }

  public MainModel(Bus bus, LaptopsApi laptopsApi) {
    this.mBus = null;
    this.mLaptopsApi = laptopsApi;
  }

  public void init() {
    this.mBus = null;
    mLaptopsApi = RestManager.getLaptopsService();
  }

  public void post(Object obj) {
    if (mBus != null) {
      mBus.post(obj);
    }
  }

  //==============================================================================================
  // MODEL METHODS
  //==============================================================================================
  public void loadLaptops() {
    laptopsCall = mLaptopsApi.getLaptops();
    callState = 0;
    laptopsCall.enqueue(new Callback<ArrayList<Laptop>>() {
      @Override
      public void onResponse(Call<ArrayList<Laptop>> call, Response<ArrayList<Laptop>> response) {
        if (response.isSuccessful()) {
          ArrayList laptops = response.body();
          if (laptops != null && !laptops.isEmpty()) {
            post(new GetLaptopsSuccessEvent(laptops));
            callState = 1;
          } else {
            post(new GetLaptopsFailureEvent(R.string.no_results));
            callState = 2;
          }
        } else {
          int statusCode = response.code();
          if (statusCode < 500) {
            //Handle Errors
          } else {
            post(new GetLaptopsFailureEvent(R.string.error_msg_failure_error));
            callState = 3;
          }
        }
        laptopsCall = null;
      }

      @Override
      public void onFailure(Call<ArrayList<Laptop>> call, Throwable t) {
        if (!call.isCanceled()) {
          post(new GetLaptopsFailureEvent(R.string.error_msg_failure_error));
          callState = 4;
        }
        laptopsCall = null;
      }
    });
  }

  public boolean cancelLaptopsCall() {
    if (laptopsCall != null) {
      laptopsCall.cancel();
      return true;
    }
    return false;
  }

  //==============================================================================================
  //==============================================================================================

  //==============================================================================================
  //    GETTERS AND SETTERS
  //==============================================================================================
  public int getCallState() {
    return callState;
  }

  //==============================================================================================
  // MODEL BUS EVENTS
  //==============================================================================================
  public static class GetLaptopsSuccessEvent {

    public ArrayList<Laptop> laptopsList;

    public GetLaptopsSuccessEvent(ArrayList laptops) {
      laptopsList = laptops;
    }
  }

  public class GetLaptopsFailureEvent {
    public int mErrorMsg;

    public GetLaptopsFailureEvent(int errorMsg) {
      mErrorMsg = errorMsg;
    }

    public int getErrorMessage() {
      return mErrorMsg;
    }
  }

  public class GetLaptopsFailureMessageEvent {
    public String mErrorMsg;

    public GetLaptopsFailureMessageEvent(String errorMsg) {
      mErrorMsg = errorMsg;
    }

    public String getErrorMessage() {
      return mErrorMsg;
    }
  }

  //==============================================================================================
  //==============================================================================================

}