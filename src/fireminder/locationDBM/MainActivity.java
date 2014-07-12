package fireminder.locationDBM;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import fireminder.google.distancematrix.DistanceMatrixRequest;
import fireminder.google.distancematrix.DistanceMatrixResponse;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		MyBroadcastReceiver receiver = new MyBroadcastReceiver();

		@Override
		public void onResume() {
			super.onResume();
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
			try {
				DistanceMatrixRequest.Builder builder = new DistanceMatrixRequest.Builder();
				builder.setOrigin("CSUEB");
				builder.setDestination("UCSB");
				DistanceMatrixRequest request = new DistanceMatrixRequest(builder);
				DistanceMatrixResponse response = request.performRequest();
				Log.e("LOG_TAG", response.toString());
			} catch (Exception e) {
				Log.e("LOG_TAG", "Error: " + e.getMessage());
			}
			/*
			 * IntentFilter filter = new
			 * IntentFilter("android.provider.Telephony.SMS_RECEIVED");
			 * getActivity().registerReceiver(receiver, filter); SmsManager
			 * smsManager = SmsManager.getDefault();
			 * smsManager.sendTextMessage("19258134932", "19258134932",
			 * "How far are you from Antioch?", null, null);
			 */
		}

		@Override
		public void onDestroy() {
			getActivity().unregisterReceiver(receiver);

		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

}
