package com.example.USCconnect;


import java.util.ArrayList;

import com.example.search.R;

import android.app.AlertDialog;



import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
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
	 
	 AlertDialog alertDialog; 

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_result);
		
		 alertDialog = new AlertDialog.Builder(this).create();
		 alertDialog.setButton("got it!", new DialogInterface.OnClickListener() {
		 public void onClick(DialogInterface dialog, int which) {
		 // here you can add functions
		 }
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
			c = SearchPage.myDb.test(searchKeyword);
			setUpSearchResults(c, searchKeyword);
		}
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
	

}
