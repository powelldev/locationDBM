package fireminder.locationDBM.text;

import java.util.List;


public class SmsParser {
	
	public static boolean detectTriggerWords(String[] strings, String smsBody) {
		for (String s : strings) {
			if (smsBody.contains(s)) {
				return true;
			}
		}
		return false;
	}
	public static String parseSubstringFromString(String smsBody, String begWord, String endWord) {
		if (smsBody.contains(begWord) && smsBody.contains(endWord)){
			int begIndex = smsBody.indexOf(begWord)+begWord.length();
			int endIndex = smsBody.indexOf(endWord);
			return smsBody.substring(begIndex, endIndex);
		} else {
			return null;
		}
	}
}
