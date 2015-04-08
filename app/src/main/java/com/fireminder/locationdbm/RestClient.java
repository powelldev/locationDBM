package com.fireminder.locationdbm;

import retrofit.RestAdapter;
import retrofit.client.UrlConnectionClient;

public class RestClient {

  private static Api REST_CLIENT;
  private static String ROOT =
      "https://maps.googleapis.com/maps/api/distancematrix";

  static {
    setupRestClient();
  }

  private RestClient() {}

  public static Api get() {
    return REST_CLIENT;
  }

  private static void setupRestClient() {
    RestAdapter.Builder builder = new RestAdapter.Builder()
        .setEndpoint(ROOT)
        .setClient(new UrlConnectionClient())
        .setLogLevel(RestAdapter.LogLevel.FULL);
    RestAdapter restAdapter = builder.build();
    REST_CLIENT = restAdapter.create(Api.class);
  }
}
