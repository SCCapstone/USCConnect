package com.example.uscconnect.test;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.Button;

import com.example.uscconnect.MainPage;
import com.example.uscconnect.R;

//import android.widget.TextView;

//http://code.tutsplus.com/tutorials/android-sdk-unit-testing-with-the-junit-testing-framework--mobile-421

public class TestMain extends ActivityInstrumentationTestCase2<MainPage> {

	private MainPage mPage;

	private Button Search;
	private Button Log;
	private Button About;
	private Button Application;
	
	private View mainLayout;

	public TestMain() {
		super("com.example.uscconnect", MainPage.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		mPage = getActivity();

		Search = (Button) mPage.findViewById(R.id.button1);
		Log = (Button) mPage.findViewById(R.id.button2);
		About = (Button) mPage.findViewById(R.id.button3);
		Application = (Button) mPage.findViewById(R.id.button4);
		
		//mainLayout = (View) mPage.findViewById(R.id.mainLayout);

	}

//	public void testAddButtonOnScreen() {
//		int fullWidth = mainLayout.getWidth();
//		int fullHeight = mainLayout.getHeight();
//		int[] mainLayoutLocation = new int[2];
//		mainLayout.getLocationOnScreen(mainLayoutLocation);
//
//		int[] viewLocation = new int[2];
//		addValues.getLocationOnScreen(viewLocation);
//
//		Rect outRect = new Rect();
//		addValues.getDrawingRect(outRect);
//
//		assertTrue("Add button off the right of the screen", fullWidth
//				+ mainLayoutLocation[0] > outRect.width() + viewLocation[0]);
//
//		assertTrue("Add button off the bottom of the screen", fullHeight
//				+ mainLayoutLocation[1] > outRect.height() + viewLocation[1]);
//	}

}
