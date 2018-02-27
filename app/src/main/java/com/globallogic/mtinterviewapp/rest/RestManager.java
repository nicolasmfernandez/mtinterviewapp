package com.globallogic.mtinterviewapp.rest;

import com.globallogic.mtinterviewapp.rest.requests.LaptopsApi;
import com.globallogic.mtinterviewapp.utils.Constants;

/**
 * Created by nicolasfernandez on 2/23/18.
 */

public class RestManager {

  private static final String BASE_URL = Constants.BASE_REST_API_URL;

  public static LaptopsApi getLaptopsService() {

    return RestClient.getClient(BASE_URL).create(LaptopsApi.class);
  }

}
