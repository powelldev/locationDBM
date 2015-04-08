package com.fireminder.locationdbm.model;

public class LatLng {

  double latitude;
  double longitude;

  public LatLng(double latitude, double longitude) {
    this.latitude = latitude;
    this.longitude = longitude;
  }

  @Override
  public String toString() {
    return String.valueOf(latitude) + "," + String.valueOf(longitude);
  }
}
