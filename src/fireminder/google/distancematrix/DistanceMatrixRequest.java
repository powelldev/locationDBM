package fireminder.google.distancematrix;

public class DistanceMatrixRequest {
	// https://maps.googleapis.com/maps/api/distancematrix/output?parameters
	
	String origin;
	String mode;
	String language;
	String unit;
	
	
	public String build() {
		StringBuffer buffer = new StringBuffer(1024);
		buffer.append("https://maps.googleapis.com/maps/api/distancematrix/");
		buffer.append(format + "/" );
	}
	public void setOrigin(double lng, double lat) {
		origin = new String( lat + "," + lng );
	}
	public void setOrigin(String originAddress){
		origin = sanitizeString(originAddress);
	}
	
	public enum Unit { METRIC, IMPERIAL };
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

	public enum Mode { DRIVING, WALKING, BICYCLING; }
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
