package com.fireminder.locationdbm.model;

public class Duration {
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

  public Duration(String value, String text) {

    this.value = value;
    this.text = text;
  }

  String text;
}
