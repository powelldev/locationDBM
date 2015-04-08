package com.fireminder.locationdbm;

import android.app.Service;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import com.fireminder.locationdbm.model.DistanceMatrix;
import com.fireminder.locationdbm.model.DistanceMatrixResponse;
import com.fireminder.locationdbm.model.LatLng;
import com.fireminder.locationdbm.model.ResponseMessage;

import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainService extends Service {

  public static final String LOG_TAG = MainService.class.getSimpleName();
  public static final int OPERATION_TIMEOUT = 30000;

  private static final String ACTION_SMS_RECIEVED = "android.provider.Telephony.SMS_RECEIVED";

  private ResponseMessage mResponseMessage;
  private Handler mHandler = new Handler();
  private boolean timeoutOccurred = false;

  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {
    if (intent != null && intent.getAction().matches(ACTION_SMS_RECIEVED)) {
      handleStartCommand(intent);
    }
    return START_STICKY;
  }

  private void handleStartCommand(Intent intent) {
    Message message = parseMessage(intent);
    String keyphrase = getString(R.string.keyphrase);

    if (TextUtils.containsKeyphrase(message.getBody(), keyphrase, "?")) {
      generateResponse(message);
    }

  }

  /**
   * Combine all messages recieved from intent into one giant message body.
   */
  private Message parseMessage(Intent intent) {
    SmsMessage[] messages = SmsUtils.getMessagesFromIntent(intent);
    String originating = messages[0].getOriginatingAddress();
    String msgBody = SmsUtils.parseMessageBody(messages);
    return new Message(originating, msgBody);
  }

  private void generateResponse(Message message) {
    mResponseMessage = new ResponseMessage();
    String location = parseLocationFromMessage(message.getBody());
    mResponseMessage.destination = location;
    mResponseMessage.phoneNumber = message.getOriginatingAddress();
    performLocationRequest();
  }

  private void performLocationRequest() {
    LocationManager locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);

    // Attempt cheap retrieval of location. If it's not too old, use it instead of activating GPS sensor
    Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
    if (location != null &&
        (System.currentTimeMillis() - location.getTime()) > 60000) {
      handleLocationFound(location);
      return;
    }

    // Ideal use case is in a car while travel to a destination, so power consumption isn't too big
    // of an issue.
    Criteria criteria = new Criteria();
    criteria.setAccuracy(Criteria.ACCURACY_FINE);
    criteria.setPowerRequirement(Criteria.POWER_HIGH);

    // Will handle location callbacks on main thread
    locationManager.requestSingleUpdate(criteria, locationListener, null);

    mHandler.postDelayed(timeoutRunnable, OPERATION_TIMEOUT);
  }

  Runnable timeoutRunnable = new Runnable() {
    @Override
    public void run() {
      timeoutOccurred = true;
    }
  };

  LocationListener locationListener = new LocationListener() {
    @Override
    public void onLocationChanged(Location location) {
      handleLocationFound(location);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onProviderDisabled(String provider) {
    }
  };

  /**
   * Take the retrieved location and perform next step in process.
   *
   */
  private void handleLocationFound(Location location) {
    mHandler.removeCallbacks(timeoutRunnable);
    if (timeoutOccurred) {
      return;
    }
    LatLng latlng = new LatLng(location.getLatitude(), location.getLongitude());
    performDistanceCalculation(latlng.toString(), mResponseMessage.destination);
  }

  /**
   * Call the API that will construct the DistanceMatrix.
   *
   * @param origin      original address
   * @param destination desired destination
   */
  private void performDistanceCalculation(String origin, String destination) {
    RestClient.get()
        .getDistanceMatrix(new String[]{origin},
            new String[]{destination},
            Units.imperial.name(),
            distanceMatrixCallback);
    mHandler.postDelayed(timeoutRunnable, OPERATION_TIMEOUT);
  }

  // TODO: add in preference for which units to use
  private enum Units {
    imperial, metric
  }

  Callback<DistanceMatrixResponse> distanceMatrixCallback = new Callback<DistanceMatrixResponse>() {
    @Override
    public void success(DistanceMatrixResponse distanceMatrixResponse, Response response) {
      DistanceMatrix dm = responseToDistanceMatrix(distanceMatrixResponse).get(0);
      handleSuccessfulDistanceCalculation(dm);
    }

    @Override
    public void failure(RetrofitError error) {
      Log.e(LOG_TAG, error.getMessage());
      error.printStackTrace();
    }
  };

  private void handleSuccessfulDistanceCalculation(DistanceMatrix distanceMatrix) {
    mHandler.removeCallbacks(timeoutRunnable);
    if (timeoutOccurred) {
      return;
    }
    mResponseMessage.currentLocation = distanceMatrix.getOrigin();
    mResponseMessage.destination = distanceMatrix.getDestination();
    mResponseMessage.duration = distanceMatrix.getDuration().getText();
    mResponseMessage.distance = distanceMatrix.getDistance().getText();
    sendMessage(mResponseMessage);
  }

  /**
   * Parse the contents of the response into distinct DistanceMatrix objects. We only care
   * about the first item. But since the API supports multiple origin-destination pairs, we
   * will too.
   */
  private List<DistanceMatrix> responseToDistanceMatrix(DistanceMatrixResponse response) {

    int numOfEntries = response.getRows().length;

    List<DistanceMatrix> distanceMatrixList = new ArrayList<>(numOfEntries);

    for (int i = 0; i < numOfEntries; i++) {
      DistanceMatrix dm = new DistanceMatrix.Builder()
          .setDistance(response.getRows()[i].getElements()[i].getDistance())
          .setDuration(response.getRows()[i].getElements()[i].getDuration())
          .setDestination(response.getDestination_addresses()[i])
          .setOrigin(response.getOrigin_addresses()[i])
          .build();
      distanceMatrixList.add(dm);
    }

    return distanceMatrixList;
  }

  private void sendMessage(ResponseMessage message) {
    Toast.makeText(this, message.toString(), Toast.LENGTH_LONG).show();
    Toast.makeText(this, message.phoneNumber, Toast.LENGTH_LONG).show();
    this.stopSelf();
  }

  private String parseLocationFromMessage(String message) throws IllegalFormatException {
    try {
      String location = message.substring("How far are you from".length(),
          message.lastIndexOf("?"));
      return location;
    } catch (Exception e) {
      throw new IllegalArgumentException("Incorrect keyphrase");
    }
  }

  @Override
  public IBinder onBind(Intent intent) {
    return null;
  }
}
