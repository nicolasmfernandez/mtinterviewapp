package com.globallogic.mtinterviewapp.rest.requests;

import com.globallogic.mtinterviewapp.entities.Laptop;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by nicolasfernandez on 2/23/18.
 */

public interface LaptopsApi {

  String LAPTOPS = "list";

  @GET(LAPTOPS)
  @Headers({"Content-Type: application/json"})
  Call<ArrayList<Laptop>> getLaptops();
}
