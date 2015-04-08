package com.fireminder.locationdbm.model;

/**
 * Class containing the result of a call to the DistanceMatrix API. Namely, the
 * origin and destination addresses, the time and distance values required to
 * travel between them.
 */
public class DistanceMatrix {

  /** Originating address */
  private String origin;

  /** Destination address */
  private String destination;

  /** Duration of a trip from origin to destination */
  private Duration duration;

  /** Physical distance of a trip from origin to destination */
  private Distance distance;

  private void setDistance(Distance distance) {
    this.distance = distance;
  }

  private void setDuration(Duration duration) {
    this.duration = duration;
  }

  private void setDestination(String destination) {
    this.destination = destination;
  }

  private void setOrigin(String origin) {
    this.origin = origin;
  }

  public Distance getDistance() {
    return distance;
  }

  public Duration getDuration() {
    return duration;
  }

  public String getDestination() {
    return destination;
  }

  public String getOrigin() {
    return origin;
  }


  public static class Builder {

    private DistanceMatrix distanceMatrix;

    public Builder() {
      distanceMatrix = new DistanceMatrix();
    }

    public Builder setOrigin(String origin) {
      distanceMatrix.setOrigin(origin);
      return this;
    }

    public Builder setDestination(String destination) {
      distanceMatrix.setDestination(destination);
      return this;
    }

    public Builder setDuration(Duration duration) {
      distanceMatrix.setDuration(duration);
      return this;
    }

    public Builder setDistance(Distance distance) {
      distanceMatrix.setDistance(distance);
      return this;
    }

    public DistanceMatrix build() {
      return distanceMatrix;
    }

  }
}
