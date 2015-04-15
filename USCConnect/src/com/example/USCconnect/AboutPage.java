package com.example.uscconnect;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;

public class AboutPage extends Activity {

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
		setContentView(R.layout.activity_about_page);
		
		addDrawerItems();
//		startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=JYuMMhll4TA")));
	}

	private void addDrawerItems() 
	{
		dataList = new ArrayList<DrawerItem>();
		mTitle = mDrawerTitle = getTitle();
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);

		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

		// Add Drawer Item to dataList
		dataList.add(new DrawerItem("Main Menu"));
		dataList.add(new DrawerItem("Find Experiences"));
		dataList.add(new DrawerItem("Personal Log"));
		dataList.add(new DrawerItem("GLD Application"));

		

		adapter = new CustomDrawerAdapter(this, R.layout.custom_drawer_item,
				dataList);

		mDrawerList.setAdapter(adapter);
		
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {
			@Override
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}

			@Override
			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mDrawerTitle);
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}
		};

		mDrawerLayout.setDrawerListener(mDrawerToggle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.about_page, menu);
		return true;
	}

	public void imgBtnClicked(View v) {
		// Inflate the menu; this adds items to the action bar if it is present.
		startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=JYuMMhll4TA")));

	}
	
	public void adviseBtnClicked(View v) {
		// Inflate the menu; this adds items to the action bar if it is present.
		startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://advisingappointments.sc.edu/USCConnectGLD/eSARSRedirect.aspx")));

	}
	public void instaClicked(View v) {
		// Inflate the menu; this adds items to the action bar if it is present.
		startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://instagram.com/uscconnect/")));

	}
	
	public void FBKClicked(View v) {
		// Inflate the menu; this adds items to the action bar if it is present.
		startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/pages/USC-Connect-University-of-South-Carolina/435527389824000")));

	}
	
	public void TweetClicked(View v) {
		// Inflate the menu; this adds items to the action bar if it is present.
		startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/USCConnect")));

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
	
	public void SelectItem(int possition) {

		switch (possition) {
		case 0:
			Intent myIntent = new Intent(this,
					MainPage.class);
			startActivityForResult(myIntent, 0);
			break;
		case 1:
			myIntent = new Intent(this,
					SearchPage.class);
			startActivityForResult(myIntent, 0);
			
			break;
			
		case 2:
			myIntent = new Intent(this,
					StudentLog.class);
			startActivityForResult(myIntent, 0);

			
			break;
			
		case 3:
			startActivity(new Intent(Intent.ACTION_VIEW, Uri
					.parse("https://www.sc.edu/uscconnect/gldapplication")));
			
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


	
	private class DrawerItemClickListener implements ListView.OnItemClickListener {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) 
           {
	         SelectItem(position);

           }
}
	
}
