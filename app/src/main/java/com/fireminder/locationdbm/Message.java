package com.fireminder.locationdbm;

public class Message {

  private String originatingAddress;
  private String body;

  public Message(String originatingAddress, String body) {
    this.originatingAddress = originatingAddress;
    this.body = body;
  }

  public String getOriginatingAddress() {
    return originatingAddress;
  }

  public void setOriginatingAddress(String originatingAddress) {
    this.originatingAddress = originatingAddress;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }
}
