package pac.instabuildings.android;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

public class AccountActivity extends Activity 
{

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_accounts);
		//Initialises the actionbar class to set the background colour as blue.
		getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_background));
	}

	public boolean onOptionsItemSelected (MenuItem item) 
//	Method outlining what action will take place when a user
//	taps one of these options that are on the actionbar overflow
	{
		//Using a switch statement linked to the ID of each of 
		//the icons in the XML file for AccountActivity to call upon its 
		//respective methods
		switch (item.getItemId())
		{
		case R.id.settings:
			appSettings();
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.account, menu);
		return true;
	}

	public void appSettings()
	//Method used to access system settings
	{
		//Calls an intent to open up the SettingsActivity class for the
		//settings button on the actionbar
		Intent settings = new Intent(this, SettingsActivity.class);
		startActivity(settings);
	}

}
