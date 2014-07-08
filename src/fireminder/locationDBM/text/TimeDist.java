package fireminder.locationDBM.text;

public class TimeDist {
	
	

	private String destination;
	private String origin;
	private long timeInSeconds;
	private int distanceInMiles;

	public TimeDist(String destination, String origin, long timeInSeconds,
			int distanceInMiles) {
		this.destination = destination;
		this.origin = origin;
		this.timeInSeconds = timeInSeconds;
		this.distanceInMiles = distanceInMiles;
	}

	public String timeInMinutes() {
		return "" + (timeInSeconds * 60);
	}

	public class TimeDistBuilder {
		TimeDistBuilder() {}
		private String destination;
		private String origin;
		private long timeInSeconds;
		private int distanceInMiles;
		
		public void setDestination(String destination) {
			this.destination = destination;
		}
		public void setOrigin(String origin) {
			this.origin = origin;
		}
		public void setTimeInSeconds(long timeInSeconds) {
			this.timeInSeconds = timeInSeconds;
		}
		public void setDistanceInMiles(int distanceInMiles) {
			this.distanceInMiles = distanceInMiles;
		}
		
		public TimeDist build(){
			return new TimeDist(destination, origin, timeInSeconds, distanceInMiles);
		}
	}

	public String getDestination() {
		return destination;
	}

	public long getTimeInSeconds() {
		return timeInSeconds;
	}

	public int getDistanceInMiles() {
		return distanceInMiles;
	}

	public String getOrigin() {
		return origin;
	}


}
