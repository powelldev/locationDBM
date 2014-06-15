package fireminder.locationDBM;

public class Sms {
	
	private String body;
	private String originatingNumber;
	
	public Sms(String body, String number) {
		this.body = body;
		this.originatingNumber = number;
	}
	
}
