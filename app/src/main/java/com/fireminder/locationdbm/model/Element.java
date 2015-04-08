package com.fireminder.locationdbm.model;

public class Element {
  String status;

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Distance getDistance() {
    return distance;
  }

  public void setDistance(Distance distance) {
    this.distance = distance;
  }

  public Duration getDuration() {
    return duration;
  }

  public void setDuration(Duration duration) {
    this.duration = duration;
  }

  public Element(String status, Distance distance, Duration duration) {

    this.status = status;
    this.distance = distance;
    this.duration = duration;
  }

  Distance distance;
  Duration duration;
}
