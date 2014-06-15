package fireminder.locationDBM.text;

public class SmsParser {
	
	static boolean containsRelevantInformation(String smsBody) {
		// How far are you from XXXX?
		if (smsBody.contains("from") && smsBody.contains("?")) {
			return true;
		}
		return false;
	}
	
	static String parseLocationFromString(String body) {
		int start, end;
		start = body.indexOf("from");
		end = body.indexOf("?");
		String location = body.substring(start, end);
		return location;
	}

}
