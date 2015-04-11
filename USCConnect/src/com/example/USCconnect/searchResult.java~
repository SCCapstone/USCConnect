package com.example.uscconnect;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import android.app.AlertDialog;



import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class searchResult extends Activity {

	 ListView listview;
	 MyArrayAdapter studentArrayAdapter;
	 ArrayList<String> IDs = new ArrayList<String>();
	 ArrayList<String> moreDetails = new ArrayList<String>();
	 ArrayList<Oppertunity> studentArray = new ArrayList<Oppertunity>();
	 String timeFrame ;
	 String typeOfOppertunity;
	 String searchKeyword;
	 String currentOppertunityTitle;
	 String currentOppertunityBody;
	 
	 AlertDialog alertDialog; 
	 
		private DrawerLayout mDrawerLayout;
		private ListView mDrawerList;
		private ActionBarDrawerToggle mDrawerToggle;

		private CharSequence mDrawerTitle;
		private CharSequence mTitle;
		CustomDrawerAdapter adapter;

		List<DrawerItem> dataList;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_result);
		
		addDrawerItems();
		
		 alertDialog = new AlertDialog.Builder(this).create();
		 alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Email to myself"
				 , new DialogInterface.OnClickListener() {

		      public void onClick(DialogInterface dialog, int id) {
		    	  Intent i = new Intent(Intent.ACTION_SEND);
		    	  i.setType("message/rfc822");
		    	  i.putExtra(Intent.EXTRA_EMAIL  , new String[]{""});
		    	  i.putExtra(Intent.EXTRA_SUBJECT, currentOppertunityTitle);
		    	  i.putExtra(Intent.EXTRA_TEXT   , currentOppertunityBody);
		    	  try {
		    	      startActivity(Intent.createChooser(i, "Send mail..."));
		    	  } catch (android.content.ActivityNotFoundException ex) {
		    	      Toast.makeText(searchResult.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
		    	  }
		    } 
		 }); 

		 alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Got it!"
		    		, new DialogInterface.OnClickListener() {
		      public void onClick(DialogInterface dialog, int id) {}
		 }); 
//		 alertDialog.setIcon(R.drawable.ic_launcher);

		Bundle b = getIntent().getExtras();
		String[] value = b.getStringArray("key");
		
		EditText editText = (EditText)findViewById(R.id.editText1);
		timeFrame = value[0];
		typeOfOppertunity = value[1]; 
		searchKeyword = value[2];

		editText.setText(searchKeyword /*value[1] + value[1] + value[0]*/);
		DBAdapter myDb = SearchPage.myDb;
		
		Cursor c = null;
		if (searchKeyword.length() != 0){
			c = SearchPage.myDb.doTheSearch(searchKeyword, timeFrame, typeOfOppertunity);
			setUpSearchResults(c, searchKeyword);
		}
	}
	
	private void addDrawerItems()
	{
		dataList = new ArrayList<DrawerItem>();
		mTitle = mDrawerTitle = getTitle();
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);

		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

		// Add Drawer Item to dataList
		dataList.add(new DrawerItem("Main Menu", R.drawable.ic_action_good));
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

	private void setUpSearchResults(Cursor cursor, String searchKeyword) {
		// populate the message from the cursor

		// Reset cursor to start, checking to see if there's data:
		if (cursor.moveToFirst()) {
			do {
				// Process the data:
				String title = cursor.getString(DBAdapter.COL_TITLE);
				String sponsor = cursor.getString(DBAdapter.COL_SPONSORCOLLEGE);
				String targetGroup = cursor.getString(DBAdapter.COL_TARGETGROUP);
				String id = cursor.getString(DBAdapter.COL_ID).replace('\"', '\0');
				String campus = cursor.getString(DBAdapter.COL_STORYCAMPUS).replace('\"', '\0');
				String description = cursor.getString(DBAdapter.COL_DESCRIPTION).replace('\"', '\0');
				String howToParticipate = cursor.getString(DBAdapter.COL_PARTICIPATIONINSTRUCTIONS).replace('\"', '\0');
				String link = cursor.getString(DBAdapter.COL_EXTERNALLINK).replace('\"', '\0');
				String email = cursor.getString(DBAdapter.COL_EMAIL).replace('\"', '\0');
				String phone = cursor.getString(DBAdapter.COL_PHONE).replace('\"', '\0');
				
				if (sponsor.length() == 2){
					sponsor = cursor.getString(DBAdapter.COL_SPONSORPROGRAM);
				}
				if (sponsor.contains("Other") || sponsor.length() == 2){
					sponsor = cursor.getString(DBAdapter.COL_SPONSOROTHER);
				}
				if (targetGroup.contains("Other") || targetGroup.length() == 2){
					targetGroup = cursor.getString(DBAdapter.COL_TARGETGROUPOTHER);
				}
				
				// remove (")
				title = title.replace('\"','\0');
				sponsor = sponsor.replace('\"','\0');
				targetGroup = targetGroup.replace('\"','\0');
				
				IDs.add(id);
				String allDetails = title + ".\nCampus: " + campus + ".\nSponsor: "
						+ sponsor + ".\n\nDescription:\n" + description + "\n\n"
						+ "Type of Opportunity:\n" 
						+ typeOfOppertunity + ".\n" + "Time Frame: " + timeFrame
						+ ".\n" + "Student Groups: " + targetGroup 
						+ ".\n\n-----------------------------------------------\n"
						+ "How to Participate: \n" + howToParticipate + ".\n\n" 
						+ "Contact Information:\n" + email + "\n" + phone + "\n";// + link;
				moreDetails.add(allDetails);
				studentArray.add(new Oppertunity(title, sponsor, targetGroup));
				
			} while (cursor.moveToNext());
		}

		// Close the cursor to avoid a resource leak.
		cursor.close();
		displayResults();
	}
	
	private void goToUrl (String url) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }
	
	private String getOppertunityDetails(int index){
//		Cursor c = SearchPage.myDb.test(id);
		alertDialog.setTitle("More details ..." );
        	alertDialog.setMessage(moreDetails.get(index).replaceAll("[^\\x00-\\x7F]", ""));
        	Scanner scanner = new Scanner(moreDetails.get(index).replaceAll("[^\\x00-\\x7F]", ""));
        	currentOppertunityTitle = scanner.nextLine();
        	currentOppertunityBody = moreDetails.get(index).replaceAll("[^\\x00-\\x7F]", "");
		return "D";
		
	}
	
	private void displayResults() {
		EditText textView = (EditText) findViewById(R.id.editText1);
//		textView.setText(message);
		textView.setText(String.valueOf(studentArray.size()));
		textView.setKeyListener(null);

//		textView.setText(Html.fromHtml("blah blah <a href=\"http://www.sc.edu/uscconnect/participate/opport_detail.php?oid=630\">some text to be linkified</a> blah blah"));

		  studentArrayAdapter = new MyArrayAdapter(searchResult.this, R.layout.list_item, studentArray);
		  listview = (ListView) findViewById(R.id.listView);
		  listview.setItemsCanFocus(false);
		  listview.setAdapter(studentArrayAdapter);
		 
		  listview.setOnItemClickListener(new OnItemClickListener() {

		   @Override
		   public void onItemClick(AdapterView<?> parent, View v,
		     final int position, long id) {
		    			   
			   String website = "http://www.sc.edu/uscconnect/participate/opport_detail.php?oid=" + IDs.get(position+1);
//			   goToUrl (website);
		    
//			   Toast.makeText(searchResult.this,
//		      "List Item Clicked:" + String.valueOf(IDs.get(position+1)), Toast.LENGTH_LONG)
//		      .show();
    		   String details = getOppertunityDetails((position));
			   alertDialog.show();

		   }
		  });

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


	
	private class DrawerItemClickListener implements ListView.OnItemClickListener {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) 
           {
	         SelectItem(position);

           }
}
	

}
