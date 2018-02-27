package com.globallogic.mtinterviewapp.rest;

import com.globallogic.mtinterviewapp.utils.Constants;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nicolasfernandez on 2/23/18.
 */

public class RestClient {

  private static final String CONTENT_TYPE = "Content-Type";
  private static Retrofit sRetrofit = null;

  public static Retrofit getClient(String baseUrl) {

    if (sRetrofit == null) {

      HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
      logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
      OkHttpClient okClient = new OkHttpClient.Builder()
              .addInterceptor(
                      new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {

                          Request originalReq = chain.request();
                          Request.Builder requestBuilder = originalReq.newBuilder()
                                  .header(CONTENT_TYPE, Constants.APPLICATION_JSON)
                                  .method(originalReq.method(), originalReq.body());

                          Request request = requestBuilder.build();
                          return chain.proceed(request);
                        }
                      })
              .addInterceptor(logInterceptor)
              .connectTimeout(60, TimeUnit.SECONDS)
              .readTimeout(60, TimeUnit.SECONDS)
              .build();
      sRetrofit = new Retrofit.Builder()
              .baseUrl(baseUrl)
              .client(okClient)
              .addConverterFactory(GsonConverterFactory.create())
              .build();
    }
    return sRetrofit;
  }
}