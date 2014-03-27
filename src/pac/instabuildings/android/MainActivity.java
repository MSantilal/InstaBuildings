package pac.instabuildings.android;

import java.util.ArrayList;

import pac.instabuildings.android.adapter.NavDrawerListAdapter;
import pac.instabuildings.android.model.NavDrawerItem;
import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;


import android.graphics.Color;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

public class MainActivity extends Activity 
{
	private DrawerLayout mDrawerLayout;
	//A listview just to hold the navigation list items
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;

	//Nav drawer title when opened
	private CharSequence mDrawerTitle;

	//Used to store app title
	private CharSequence mTitle;
		
	//Slide menu items
	//Titles for each of the navigation menu options.
	private String[] navMenuTitles;
	//Array which holds all the icons for each of navigation drawer items.
	private TypedArray navMenuIcons;

	private ArrayList<NavDrawerItem> navDrawerItems;
	private NavDrawerListAdapter adapter;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
				
		//sets the title for the activity from the inbuilt get activity class
		mTitle = mDrawerTitle = getTitle();
				 
        // load slide menu items
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);
 
        // nav drawer icons from resources
        navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);
 
        //load layouts from the XML resources
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.list_slidermenu);
        //create new ArrayList just to hold items for the nav drawer
        navDrawerItems = new ArrayList<NavDrawerItem>();
 
        // adding nav drawer items to array
        // Home
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
        // Your Projects
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
        // Starred Projects
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
        // Trending Near You
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1), true, "22"));

        // Recycle the typed array for later use in the app
        navMenuIcons.recycle();
        
        //utilises an on click listener which can be later called on when carrying out any of the options 
        mDrawerList.setOnItemClickListener(new SlideMenuClickListener());
 
        // setting the nav drawer list adapter
        adapter = new NavDrawerListAdapter(getApplicationContext(),navDrawerItems);
        mDrawerList.setAdapter(adapter);
 
        // enabling action bar app icon and behaving it as toggle button
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        //colour of actionbar
        getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_background)); 
        
        
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_drawer, //nav menu toggle icon
                R.string.app_name, // nav drawer open - description for accessibility
                R.string.app_name // nav drawer close - description for accessibility
        ) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                // calling onPrepareOptionsMenu() to show nav bar icons
                invalidateOptionsMenu();
            }
 
            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                // calling onPrepareOptionsMenu() to hide nav bar icons
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        
        if (savedInstanceState == null) {
            // on first time display view for first nav item
            displayView(0);
        }
        
        
    }
 
    /**
     * Slide menu item click listener
     * */
    private class SlideMenuClickListener implements ListView.OnItemClickListener 
    {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,long id) 
        {
            // display view for selected nav drawer item fragment
            displayView(position);
        }
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		//Imports the SearchView package which allows me to use the embedded search bar in the action bar.
		SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
		//Calls the searchManager to handle the searches for the application.
		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
	   	if (null != searchManager)
	    {
	   		//Allows the app to build the necessary components to display labels,hints, suggestions etc to launch search results
	   		//they have been presented. They also allow for extra additions like a voice button.
	    	  searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
	    }
	   	
	   	int id = searchView.getContext().getResources().getIdentifier("android:id/search_src_text",null,null);
	    TextView textView = (TextView) searchView.findViewById(id);
	    //Sets the hint colour for the user.
	    textView.setHintTextColor(Color.WHITE);
	    //Shows the search bar as an icon unless tapped on by a user
	    searchView.setIconifiedByDefault(true);
	    //set the hint for the user.
	    searchView.setQueryHint("Search InstaBuildings");
	    
	    return super.onCreateOptionsMenu(menu);
	}

	public boolean onOptionsItemSelected (MenuItem item) 
//	Method outlining what action will take place when a user
//	taps one of these options
	{
		if (mDrawerToggle.onOptionsItemSelected(item)) 
		{
			return true;
		}
		switch (item.getItemId())
		{
		case R.id.notification_bulb:
			openNotifications();
			break;
		case R.id.search:
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

	public boolean onPrepareOptionsMenu(Menu menu) {
		// if nav drawer is opened, hide the following items
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		menu.findItem(R.id.notification_bulb).setVisible(!drawerOpen); //when navbar is open hide notifications
		menu.findItem(R.id.new_post).setVisible(!drawerOpen); //when navbar is open hide notifications
		menu.findItem(R.id.account).setVisible(!drawerOpen); //when navbar is open hide notifications
			
		return super.onPrepareOptionsMenu(menu);
	}
	
	private void displayView(int position) {
		// update the main content by replacing fragments
		Fragment fragment = null;
		switch (position) {
		case 0:
			fragment = new Home();
			break;
		case 1:
			fragment = new Your_Projects();
			break;
		case 2:
			fragment = new Starred_Projects();
			break;
		case 3:
			fragment = new Trending_Area();
			break;

		default:
			break;
		}

		if (fragment != null) {
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
			.replace(R.id.frame_container, fragment).commit();

			// update selected item and title, then close the drawer
			mDrawerList.setItemChecked(position, true);
			mDrawerList.setSelection(position);
			setTitle(navMenuTitles[position]);
			mDrawerLayout.closeDrawer(mDrawerList);
		} else {
			// error in creating fragment
			Log.e("MainActivity", "Error in creating fragment");
		}
	}

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }
	
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }
 
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
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

