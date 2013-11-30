package pac.instabuildings.android;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

public class NotificationActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notification);
		getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_background));
		
		
		Intent notifications = getIntent();
		Intent new_post = getIntent();
	
	}

	public boolean onOptionsItemSelected (MenuItem item) 
//	Method outlining what action will take place when a user
//	taps one of these options
	{
	
		switch (item.getItemId())
		{
		case R.id.notification_bulb:
			openNotifications();
			break;
		case R.drawable.ic_action_search:
			openSearch();
			break;
		case R.id.new_post:
			newPost();
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
		getMenuInflater().inflate(R.menu.notification, menu);
		return true;
	}

    //---------------------- ActionBar actions --------------------//
    
	public void openNotifications()
	//Method used to inform users of their new notifications
	{
		Intent notifications = new Intent(this, NotificationActivity.class);
		startActivity(notifications);
	}
	
	public void openSearch()
	//Method used to search through posts or the "InstaBuildings" network
	{
		//Insert search code here
	}
	
	public void newPost()
	//Method used to create new posts for the "InstaBuildings" network
	{
		Intent new_post = new Intent(this, NewPostActivity.class);
		startActivity(new_post);
	}
	
	public void accountSettings()
	//Method used to access accounts for the user logged in
	{
		//Insert accounts setting code here code here
	}
	
	public void appSettings()
	//Method used to access system settings
	{
		//Insert system settings code here code here
	}

	//---------------------- ActionBar actions --------------------//
	
}
