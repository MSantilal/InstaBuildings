package pac.instabuildings.android;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Trending_Area extends Fragment {
	
	public Trending_Area(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
		//Creates a fragment layout for the landing page of the application.
        View rootView = inflater.inflate(R.layout.fragment_trending_area, container, false);

        return rootView;
    }



}
