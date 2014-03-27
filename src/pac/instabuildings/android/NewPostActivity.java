package pac.instabuildings.android;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class NewPostActivity extends Activity {
	
	//Sets the request code for the camera to capture image
	private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
	//request code for the application to access the gallery
	private static final int Select_Picture = 1;
	//set the input and output of the media type
	private static final int MEDIA_TYPE_IMAGE = 1;
	//Declares the class ImageView for latter use to access gallery
	ImageView gallery;
	//Declares the class ImageView for latter use to allow the gallery to display the image selected.
	private ImageView imageView;
	//set file type
	private Bitmap bitmap;
	//image output folder
	private static final String IMAGE_DIRECTORY_NAME = "InstaBuildings";
	private Uri fileUri; //file directory url to store store image
	private ImageView imgPreview;
	//Button to initiate camera
	private Button btnCapturePicture;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_post);
		getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_background));
		//ImageView of Gallery to listen for the user input and open gallery, which then allows the user to select and display the photo
		gallery = (ImageView) findViewById(R.id.result);
		gallery.setOnClickListener(new OnClickListener() {
		
		public void onClick(View v)
		{
			Intent gallery = new Intent();
			gallery.setType("image/*");
			gallery.setAction(gallery.ACTION_GET_CONTENT);
			startActivityForResult(gallery.createChooser(gallery, "Select Picture"), Select_Picture);
		}
	});
		imageView = (ImageView) findViewById(R.id.result);
		//Brings the selected image to front.
		imageView.bringToFront();
		
		//ImageView of Camera to listen for the user input and open the camera, which then allows the user to take and display the photo
		imgPreview = (ImageView) findViewById(R.id.imgPreview);
		btnCapturePicture = (Button) findViewById(R.id.btCapturePicture);
		btnCapturePicture.setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				//calls the method to start the intent of the camera
				captureImage();
			}
		});

	}		

	public void pickImage (View View)
	{
		//Select image intent method.
		
		Intent intent = new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		intent.addCategory(Intent.CATEGORY_OPENABLE);
		startActivityForResult(intent,Select_Picture);
		
		
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		//if the SELECT_PICTURE and RESULT are the same then the application has the goahead to go into the gallery
		//else it doesn't allow the application and fails.
		if (requestCode == Select_Picture && resultCode == Activity.RESULT_OK)
		{
			try 
			{
				if (bitmap != null)
				{
					//if there is no bitmap then dump any useless info regarding it and create a new reference 
					//for a new bitmap (if there is one)
					bitmap.recycle();
				}
				InputStream stream = getContentResolver().openInputStream(data.getData());
				bitmap = BitmapFactory.decodeStream(stream);
				stream.close();
				//show the image on screen
				imageView.setImageBitmap(bitmap);
			} 
			catch (FileNotFoundException e) 
			{
				e.printStackTrace();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}	
		
		if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE)
		{
			if (resultCode == RESULT_OK)
			{
				//successfully captured image
				//display it in imageview
				previewCaptureImage();
			}
			else if (resultCode == RESULT_CANCELED)
			{
				//if user cancels then display toast
				Toast.makeText(getApplicationContext(), "Image Capture Cancelled", Toast.LENGTH_SHORT).show();
			}
			else
			{
				//if it fails, then display toast that it has failed.
				Toast.makeText(getApplicationContext(), "Sorry! Failed to capture image", Toast.LENGTH_SHORT).show();
			}	
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
		
	@Override
	public boolean onOptionsItemSelected (MenuItem item) 
//	Method outlining what action will take place when a user
//	taps one of these options
	{
	
		switch (item.getItemId())
		{
		case R.id.new_post: 
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

	private boolean isDeviceSupportCamera()
	{
		if (getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
    
	private void captureImage()
	{
		//Intent method to open up the camera for app's use
		Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
		camera.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
		startActivityForResult(camera, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
	}
	
	private void previewCaptureImage()
	{
		try 
		{
			//show a preview of the image before accepting the final image
			imgPreview.setVisibility(View.VISIBLE);
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inSampleSize = 8;
			final Bitmap bitmap = BitmapFactory.decodeFile(fileUri.getPath(),options);
			imgPreview.setImageBitmap(bitmap);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

	}

	@Override
	protected void onSaveInstanceState(Bundle outState)
	{
		super.onSaveInstanceState(outState);
		outState.putParcelable("file_uri", fileUri);
	}
	
	protected void onRestoreInstanceState(Bundle savedInstanceState)
	{
		super.onRestoreInstanceState(savedInstanceState);
		fileUri = savedInstanceState.getParcelable("file_uri");
	}

	public Uri getOutputMediaFileUri(int type)
	{
		return Uri.fromFile(getOutputMediaFile(type));
	}

	private static File getOutputMediaFile(int type)
	{

		// External Memory location
		File mediaStorageDir = new File(
				Environment
				.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
				IMAGE_DIRECTORY_NAME);

		// Create the storage directory if it does not exist
		if (!mediaStorageDir.exists()) {
			if (!mediaStorageDir.mkdirs()) {
				Log.d(IMAGE_DIRECTORY_NAME, "Oops! Failed create "
						+ IMAGE_DIRECTORY_NAME + " directory");
				return null;
			}
		}

		// Create a media file name
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
				Locale.getDefault()).format(new Date());
		File mediaFile;
		if (type == MEDIA_TYPE_IMAGE) 
		{
			mediaFile = new File(mediaStorageDir.getPath() + File.separator
					+ "IMG_" + timeStamp + ".jpg");
		}
		else {
			return null;
		}

		return mediaFile;
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
