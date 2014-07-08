package fireminder.locationDBM.tests;

import junit.framework.TestCase;
import fireminder.locationDBM.text.SmsParser;

public class SmsParserTest extends TestCase {

	public void test_parseSubstring() {
		String dest = SmsParser.parseSubstringFromString(
				"How far are you from Antioch?", "from", "?");
		assertTrue(dest.contains("Antioch"));
	}

	public void test_triggerWords() {
		boolean detect = SmsParser.detectTriggerWords(new String[] {"Where are you?"}, "Where are you?");
		assertTrue(detect);
	}
}
