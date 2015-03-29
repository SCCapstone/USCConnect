package com.example.uscconnect;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import android.content.res.Configuration;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainPage extends Activity {

	ProgressDialog mProgressDialog; // ### for .csv downloading
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;

	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	CustomDrawerAdapter adapter;

	List<DrawerItem> dataList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		

		// ### for .csv downloading
		mProgressDialog = new ProgressDialog(MainPage.this);
		mProgressDialog.setMessage("Downloading database file...");
		mProgressDialog.setIndeterminate(true);
		mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		// mProgressDialog.setCancelable(true);
		final DownloadTask downloadTask = new DownloadTask(MainPage.this);
		downloadTask
				.execute("http://cse.sc.edu/~ammer/uscconnect_opportunities_1414761945.csv");
		// ###

		setContentView(R.layout.activity_main_page);
		addDrawerItems();
		Button Search = (Button) findViewById(R.id.button1);
		Search.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent myIntent = new Intent(view.getContext(),
						SearchPage.class);
				// ProgressDialog dialog = ProgressDialog.show(MainPage.this,
				// "",
				// "Loading. Please wait...", true);
				startActivityForResult(myIntent, 0);

			}
		});

		Button Log = (Button) findViewById(R.id.button2);
		Log.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent myIntent = new Intent(view.getContext(),
						StudentLog.class);
				startActivityForResult(myIntent, 0);
			}
		});

		Button About = (Button) findViewById(R.id.button3);
		About.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent myIntent = new Intent(view.getContext(), AboutPage.class);
				startActivityForResult(myIntent, 0);
			}
		});

		Button Application = (Button) findViewById(R.id.button4);
		Application.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				startActivity(new Intent(Intent.ACTION_VIEW, Uri
						.parse("https://www.sc.edu/uscconnect/gldapplication")));

			}
		});
		// Parse.initialize(this, "uv0wiLDntmTYohfjvjJrICI6nSae1hFc20GPf9JJ",
		// "yvbChURyeihU8wCB6dyLWwZjSwkU9V0l11oQqeA7");
		

	}

	private void addDrawerItems() 
	{
		dataList = new ArrayList<DrawerItem>();
		mTitle = mDrawerTitle = getTitle();
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);

		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

		// Add Drawer Item to dataList
		dataList.add(new DrawerItem("Find Experiences", R.drawable.ic_action_search));
		dataList.add(new DrawerItem("Personal Log", R.drawable.ic_drawer));
		dataList.add(new DrawerItem("GLD Application", R.drawable.ic_action_good));
		dataList.add(new DrawerItem("About Us", R.drawable.ic_action_about));
		

		adapter = new CustomDrawerAdapter(this, R.layout.custom_drawer_item,
				dataList);

		mDrawerList.setAdapter(adapter);
		
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}

			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mDrawerTitle);
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}
		};

		mDrawerLayout.setDrawerListener(mDrawerToggle);
		
	}
	
	public void SelectItem(int possition) {

		switch (possition) {
		case 0:
			Intent myIntent = new Intent(this,
					SearchPage.class);
			startActivityForResult(myIntent, 0);
			break;
		case 1:
			myIntent = new Intent(this,
					StudentLog.class);
			startActivityForResult(myIntent, 0);
			
			break;
			
		case 2:
			startActivity(new Intent(Intent.ACTION_VIEW, Uri
					.parse("https://www.sc.edu/uscconnect/gldapplication")));
			
			break;
			
		case 3:
			myIntent = new Intent(this,
					AboutPage.class);
			startActivityForResult(myIntent, 0);
			
			break;
		default:
			break;
		}

		mDrawerList.setItemChecked(possition, true);
		setTitle(dataList.get(possition).getItemName());
		mDrawerLayout.closeDrawer(mDrawerList);

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
		// Pass any configuration change to the drawer toggles
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_page, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		if (mDrawerToggle.onOptionsItemSelected(item))
		{
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	// ### for downloading .csv
	// usually, subclasses of AsyncTask are declared inside the activity class.
	// that way, you can easily modify the UI thread from here
	private class DownloadTask extends AsyncTask<String, Integer, String> {

		private Context context;
		private PowerManager.WakeLock mWakeLock;

		public DownloadTask(Context context) {
			this.context = context;
		}

		@Override
		protected String doInBackground(String... sUrl) {
			InputStream input = null;
			OutputStream output = null;
			HttpURLConnection connection = null;
			try {
				URL url = new URL(sUrl[0]);
				connection = (HttpURLConnection) url.openConnection();
				connection.connect();

				// expect HTTP 200 OK, so we don't mistakenly save error report
				// instead of the file
				if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
					return "Server returned HTTP "
							+ connection.getResponseCode() + " "
							+ connection.getResponseMessage();
				}

				// this will be useful to display download percentage
				// might be -1: server did not report the length
				int fileLength = connection.getContentLength();

				// download the file
				input = connection.getInputStream();
				output = new FileOutputStream(
						"/sdcard/uscconnect_opportunities_1414761945.csv");

				byte data[] = new byte[4096];
				long total = 0;
				int count;
				while ((count = input.read(data)) != -1) {
					// allow canceling with back button
					if (isCancelled()) {
						input.close();
						return null;
					}
					total += count;
					// publishing the progress....
					if (fileLength > 0) // only if total length is known
						publishProgress((int) (total * 100 / fileLength));
					output.write(data, 0, count);
				}
			} catch (Exception e) {
				return e.toString();
			} finally {
				try {
					if (output != null)
						output.close();
					if (input != null)
						input.close();
				} catch (IOException ignored) {
				}

				if (connection != null)
					connection.disconnect();
			}
			return null;
		}
	}
	
	private class DrawerItemClickListener implements ListView.OnItemClickListener {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) 
           {
	         SelectItem(position);

           }
}

}

