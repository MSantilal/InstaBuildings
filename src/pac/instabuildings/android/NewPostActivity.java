package pac.instabuildings.android;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

public class NewPostActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_post);
		getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_background));

		
	}
	
	public boolean onOptionsItemSelected (MenuItem item) 
//	Method outlining what action will take place when a user
//	taps one of these options
	{
	
		switch (item.getItemId())
		{
		case R.id.new_post: //change when implementing sendmessage function
			sendMessage();
			break;
		case R.id.account:
			accountSettings();
			break;
		case R.id.settings:
			appSettings();
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_post, menu);
		return true;
	}

    //---------------------- ActionBar actions --------------------//
	
	public void sendMessage()
	//Method used to create new posts for the "InstaBuildings" network
	{
		
	}
	
	public void accountSettings()
	//Method used to access accounts for the user logged in
	{
		Intent accounts = new Intent(this, AccountActivity.class);
		startActivity(accounts);
	}
	
	public void appSettings()
	//Method used to access system settings
	{
		Intent settings = new Intent(this, SettingsActivity.class);
		startActivity(settings);
	}
	//---------------------- ActionBar actions --------------------//
	
	
}
