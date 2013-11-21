package pac.grproject.android_instabuildings;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity 
{

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public boolean onOptionsItemSelected (MenuItem item) 
//	Method outlining what action will take place when a user
//	taps one of these options
	{
		switch (item.getItemId())
		{
		case R.drawable.ic_action_warning:
			openNotifications();
			break;
		case R.drawable.ic_action_search:
			openSearch();
			break;
		case R.drawable.ic_action_picture:
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
	
	public void openNotifications()
	//Method used to inform users of their new notifications
	{
		//Insert Notification code here
	}
	
	public void openSearch()
	//Method used to search through posts or the "InstaBuildings" network
	{
		//Insert search code here
	}
	
	public void newPost()
	//Method used to create new posts for the "InstaBuildings" network
	{
		//Insert new post code here
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
}
