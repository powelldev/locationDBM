package fireminder.google.distancematrix;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.util.Log;

public class DistanceMatrixRequest {
	String url;
	
	public DistanceMatrixRequest(DistanceMatrixRequest.Builder builder) {
		this.url = builder.build();
	}
	
	public DistanceMatrixResponse performRequest() {
		try {
			HttpClient client = new DefaultHttpClient();
			HttpGet get = new HttpGet(url);
			HttpResponse response = client.execute(get);
			if (response.getStatusLine().toString().contains("200")) {
				return new DistanceMatrixResponse(EntityUtils.toString(response.getEntity()));
			}
		} catch (Exception e) {
			Log.e("LOG_TAG", "Http failure");
			e.printStackTrace();
		}
		return null;
	}

	public enum Mode {
		DRIVING, WALKING, BICYCLING;
	}

	public enum Unit {
		METRIC, IMPERIAL
	};

	public static class Builder {
		String origin;
		String mode;
		String language;
		String unit;
		String destination;

		public String build() {
			String key = "AIzaSyCxUm9wQ7wU6haxgfPVHpZNiV8eIh4aJB8";
			String url = String
					.format("https://maps.googleapis.com/maps/api/distancematrix/json?origins=%s&destinations=%s&mode=driving&language=en-US&key=%s",
							origin, destination, key);
			return url;
		}

		public void setOrigin(double lng, double lat) {
			origin = new String(lat + "," + lng);
		}

		public void setOrigin(String originAddress) {
			origin = sanitizeString(originAddress);
		}

		public void setDestination(String destination) {
			this.destination = sanitizeString(destination);
		}

		public void setUnit(Unit unit) {
			switch (unit) {
			case METRIC:
				this.unit = "metric";
				break;
			case IMPERIAL:
				this.unit = "imperial";
				break;
			}
		}

		public void setMode(Mode mode) {
			switch (mode) {
			case DRIVING:
				this.mode = "driving";
				break;
			case WALKING:
				this.mode = "walking";
				break;
			case BICYCLING:
				this.mode = "bicycling";
				break;
			}
		}

		private String sanitizeString(String string) {
			string = string.replaceAll(" ", "+");
			return string;
		}

	}
}
