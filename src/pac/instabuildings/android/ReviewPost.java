package pac.instabuildings.android;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ReviewPost extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_review_post);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.review_post, menu);
		return true;
	}

}
