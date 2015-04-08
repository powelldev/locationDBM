package com.fireminder.locationdbm;

public class TextUtils {

  public static boolean containsKeyphrase(String message, String beginningPhrase, String endPhrase) {
    return message.contains(beginningPhrase) && message.contains(endPhrase);
  }
}
