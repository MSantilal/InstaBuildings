package pac.instabuildings.android;

import android.os.Bundle;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.TextView;

public class NotificationActivity extends Activity 
{

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notification);
		getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_background));

	}

	public boolean onOptionsItemSelected (MenuItem item) 
//	Method outlining what action will take place when a user
//	taps one of these options
	{
		switch (item.getItemId())
		{

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
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.notification, menu);
		SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
	   	if (null != searchManager)
	    {
	    	  searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
	    }
	   	
	   	int id = searchView.getContext().getResources().getIdentifier("android:id/search_src_text",null,null);
	    TextView textView = (TextView) searchView.findViewById(id);
	    textView.setHintTextColor(Color.WHITE);
	    searchView.setIconifiedByDefault(true);
	    searchView.setQueryHint("Search InstaBuildings");
	    
	    return super.onCreateOptionsMenu(menu);
	}

    //---------------------- ActionBar actions --------------------//
   
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
