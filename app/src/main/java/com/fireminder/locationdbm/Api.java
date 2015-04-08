package com.fireminder.locationdbm;

import com.fireminder.locationdbm.model.DistanceMatrixResponse;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface Api {
  @GET("/json")
  void getDistanceMatrix(@Query("origins") String[] origins,
                         @Query("destinations") String[] destinations,
                         @Query("units") String units,
                         Callback<DistanceMatrixResponse> callback);

}
