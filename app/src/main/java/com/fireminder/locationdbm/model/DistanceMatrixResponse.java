package com.fireminder.locationdbm.model;

public class DistanceMatrixResponse {

  String status;
  String[] origin_addresses;
  String[] destination_addresses;
  Row[] rows;

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String[] getOrigin_addresses() {
    return origin_addresses;
  }

  public void setOrigin_addresses(String[] origin_addresses) {
    this.origin_addresses = origin_addresses;
  }

  public String[] getDestination_addresses() {
    return destination_addresses;
  }

  public void setDestination_addresses(String[] destination_addresses) {
    this.destination_addresses = destination_addresses;
  }


  public DistanceMatrixResponse(String status, String[] origin_addresses, String[] destination_addresses, Row[] rows) {

    this.status = status;
    this.origin_addresses = origin_addresses;
    this.destination_addresses = destination_addresses;
    this.rows = rows;
  }

  public Row[] getRows() {
    return rows;
  }

  public void setRows(Row[] rows) {
    this.rows = rows;
  }
}
