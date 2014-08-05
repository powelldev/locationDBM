package fireminder.locationDBM;

import android.content.Context;
import android.location.Location;

public class LocationHelper {

	
	public LocationHelper(Context applicationContext, LocationHelperListener listener) {
	}

	public interface LocationHelperListener {
		public void onLocationUpdated(Location location);
	}

	public void poll(int updateTimeMs) {
		// TODO Auto-generated method stub
		
	}

	public void stop() {
		// TODO Auto-generated method stub
		
	}

	public Location obtainLocation() {
		// TODO Auto-generated method stub
		return null;
	}
}
