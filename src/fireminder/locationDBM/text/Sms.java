package fireminder.locationDBM.text;


public class Sms {

	private String body;
	private String originatingNumber;

	public String getBody() {
		return body;
	}

	public String getOriginatingNumber() {
		return originatingNumber;
	}

	public Sms(String body, String number) {
		this.body = body;
		this.originatingNumber = number;
	}
	
	class SmsBuilder {
		SmsBuilder() {}

		TimeDist timeDist;
		Inquirer inq;

		public void setTimeDist(TimeDist timeDist) {
			this.timeDist = timeDist;
		}

		public void setInquirer(Inquirer inq) {
			this.inq = inq;
		}

		public Sms build() {
			String message = "I am " + timeDist.timeInMinutes()
					+ " minutes away from " + timeDist.getDestination() + ".";
			String originatingAddress = inq.address;
			return new Sms(message, originatingAddress);
		}
	}

}
