package pac.instabuildings.android;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Your_Projects extends Fragment implements OnClickListener
{
	Button button;
	TextView outputText;
	
	public Your_Projects(){}
	
	public static final String URL = "http://172.31.81.17:8085/DB";
	
	//http://172.31.81.17:8085/DB
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		//Creates a fragment layout for the landing page of the application.
        View rootView = inflater.inflate(R.layout.fragment_your_projects,
        		container, false);
        
//        findViewsById();
        button = (Button) rootView.findViewById(R.id.button);
		outputText = (TextView) rootView.findViewById(R.id.outputTxt);
        button.setOnClickListener(this);
        
        return rootView;
    }

//	private void findViewsById()
//	{
//		button = (Button) rootView.findViewById(R.id.button);
//		outputText = (TextView) findViewById(R.id.outputTxt);
//	}

	public void onClick(View view) {
		GetXMLTask task = new GetXMLTask();
		task.execute(new String[] { URL });
	}

	private class GetXMLTask extends AsyncTask<String, Void, String> {
		@Override
		protected String doInBackground(String... urls) {
			String output = null;
			for (String url : urls) {
				output = getOutputFromUrl(url);
			}
			return output;
		}

		private String getOutputFromUrl(String url) {
			StringBuffer output = new StringBuffer("");
			try {
				InputStream stream = getHttpConnection(url);
				BufferedReader buffer = new BufferedReader(
						new InputStreamReader(stream));
				String s = "";
				while ((s = buffer.readLine()) != null)
					output.append(s);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			return output.toString();
		}

		// Makes HttpURLConnection and returns InputStream
		private InputStream getHttpConnection(String urlString)
				throws IOException {
			InputStream stream = null;
			URL url = new URL(urlString);
			URLConnection connection = url.openConnection();

			try {
				HttpURLConnection httpConnection = (HttpURLConnection) connection;
				httpConnection.setRequestMethod("GET");
				httpConnection.connect();

				if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
					stream = httpConnection.getInputStream();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return stream;
		}

		@Override
		protected void onPostExecute(String output) {
			outputText.setText(output);
		}
	}
}