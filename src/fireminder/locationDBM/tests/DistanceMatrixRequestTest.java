package fireminder.locationDBM.tests;

import org.junit.Test;

import fireminder.google.distancematrix.DistanceMatrixRequest;
import fireminder.google.distancematrix.DistanceMatrixRequest.Mode;

public class DistanceMatrixRequestTest {

	@Test
	public void testBuild() {
		DistanceMatrixRequest.Builder builder = new DistanceMatrixRequest.Builder();
		builder.setOrigin("CSUEB");
		builder.setDestination("UCSB");
		String url = builder.build();
	}
}
