package com.fireminder.locationdbm.model;

public class Distance {

  String value;

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Distance(String value, String text) {

    this.value = value;
    this.text = text;
  }

  String text;
}
