package fireminder.google.distancematrix;

import android.util.Log;

public class DistanceMatrixResponse {

	String str;
	public DistanceMatrixResponse(String string) {
		this.str = string;
		Log.e("LOG_TAG", string);
	}
	
	@Override
	public String toString() {
		return str;
	}

}
