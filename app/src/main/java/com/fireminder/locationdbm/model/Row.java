package com.fireminder.locationdbm.model;

public class Row {

  Element[] elements;

  public Row(Element[] elements) {
    this.elements = elements;
  }

  public Element[] getElements() {
    return elements;
  }

  public void setElements(Element[] elements) {
    this.elements = elements;
  }
}
