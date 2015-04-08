package com.fireminder.locationdbm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class SmsReceiver extends BroadcastReceiver{
  @Override
  public void onReceive(Context context, Intent intent) {
    intent.setClass(context, MainService.class);
    context.startService(intent);
    Log.i("SmsReceiver", "broadcast received");
  }

}
