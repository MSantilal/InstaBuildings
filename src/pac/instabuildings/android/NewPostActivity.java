package pac.instabuildings.android;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class NewPostActivity extends Activity {
	
	private static final int Select_Picture = 1;
	Button gallery;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_post);
		getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_background));

		gallery = (Button) findViewById(R.id.button1);
		gallery.setOnClickListener(new OnClickListener() {
		
		public void onClick(View v)
		{
			Intent gallery = new Intent();
			gallery.setType("image/*");
			gallery.setAction(gallery.ACTION_GET_CONTENT);
			startActivityForResult(gallery.createChooser(gallery, "Select Picture"), Select_Picture);
		}
	});
		
	}		
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		switch(requestCode) 
		{
		case Select_Picture: // our request code
			if(resultCode == Activity.RESULT_OK)
			{ // result was ok
				Uri selectedImage = data.getData();
				Intent i = new Intent(getApplicationContext(),ImageView.class);
				
				i.putExtra("PICTURE_LOCATION", selectedImage.toString());
				startActivity(i);
			}
		}
	}
	
	@Override
	
	
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
