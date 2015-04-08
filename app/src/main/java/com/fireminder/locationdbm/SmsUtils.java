package com.fireminder.locationdbm;


import android.content.Intent;
import android.telephony.SmsMessage;

public class SmsUtils {

  /**
   * Sms messages are stored in a pdus object in the intent. This method
   * will retrieve those messages.
   */
  public static  SmsMessage[] getMessagesFromIntent(Intent intent) {
    Object[] messages = (Object[]) intent.getSerializableExtra("pdus");
    return SmsUtils.getMessagesFromPdu(messages);
  }

  private static SmsMessage[] getMessagesFromPdu(Object[] pdus) {
    SmsMessage[] smsMessages = new SmsMessage[pdus.length];
    for (int i = 0; i < pdus.length; i++) {
      smsMessages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
    }
    return smsMessages;
  }

  /**
   * Because sms messages have a character limit, combine split messages into one
   */
  public static String parseMessageBody(SmsMessage[] msgs) {
    StringBuilder builder = new StringBuilder();
    for (SmsMessage msg : msgs) {
      builder.append(msg.getMessageBody());
    }
    return builder.toString();
  }

}
