package com.fireminder.locationdbm.model;

public class ResponseMessage {
  public String currentLocation;
  public String destination;
  public String duration;
  public String distance;
  public String phoneNumber;

  public String toString() {
    return String.format("I'm %s away from %s. I should be there in %s. \n Sent via LocationDBM",
        distance,
        destination,
        duration);
  }
}
