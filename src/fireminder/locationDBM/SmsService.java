package fireminder.locationDBM;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.os.IBinder;
import fireminder.google.distancematrix.DistanceMatrixRequest;
import fireminder.google.distancematrix.DistanceMatrixResponse;
import fireminder.locationDBM.LocationHelper.LocationHelperListener;

public class SmsService extends Service implements LocationHelperListener {

	private static final int UPDATE_TIME_MS = 60 * 1000;
	LocationHelper locationHelper;

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		locationHelper = new LocationHelper(this.getApplicationContext(), this);
		locationHelper.poll(UPDATE_TIME_MS);
		return super.onStartCommand(intent, flags, startId);
	}

	public void constructResponse() {
		Location location = locationHelper.obtainLocation();
		DistanceMatrixRequest.Builder builder = new DistanceMatrixRequest.Builder();
		builder.setOrigin(location.getLongitude(), location.getLatitude());
		builder.setDestination("UCSB");
		DistanceMatrixRequest request = new DistanceMatrixRequest(builder);
		DistanceMatrixResponse response = request.performRequest();
	}

	@Override
	public void onDestroy() {
		locationHelper.stop();
		super.onDestroy();
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onLocationUpdated(Location location) {
		// update something or other
	}

}
