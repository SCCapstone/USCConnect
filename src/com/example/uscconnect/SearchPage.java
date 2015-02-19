package com.example.uscconnect;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.Date;
import java.util.Calendar;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class SearchPage extends Activity {

	DBAdapter myDb;
	String endl = "\n";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_page);

//		openDB();
		new LongOperation().execute("");

		
		final EditText editText = (EditText) findViewById(R.id.autoCompleteTextView1);
		
		//editText.setOnEditorActionListener(){

			editText.setOnEditorActionListener(new OnEditorActionListener() {
			    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
			        boolean handled = false;
			        if (actionId == EditorInfo.IME_ACTION_GO) {
			        	EditText Player = (EditText) findViewById(R.id.autoCompleteTextView1);
			    		String searchKeyword = Player.getText().toString(); // actv.getText().toString();
			    		Cursor c = myDb.test(searchKeyword);

			    		displayRecordSet2(c, searchKeyword);
			    		InputMethodManager imm = (InputMethodManager)getSystemService( Context.INPUT_METHOD_SERVICE); //txtName is a reference of an EditText Field 
			    		imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
			            handled = true;
			        }
			        return handled;
			    }
			});
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		closeDB();
	}

	private void OnClickGO() {
		
	}

	void openDB() {
//		LinearLayout linlaHeaderProgress = (LinearLayout) findViewById(R.id.linlaHeaderProgress);
//		ProgressDialog progress;
//		progress = ProgressDialog.show(this, "dialog title", "dialog message", true);

		myDb = new DBAdapter(this);
		myDb.open();
		BufferedReader reader = null;
		Calendar cal = Calendar.getInstance();
		Date date = new Date(0);
		cal.setTime(date);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minutes = cal.get(Calendar.MINUTE);
		if (1==1/*hour == 10 && minutes >= 30 || hour == 11 && minutes <= 30*/) {
			myDb.deleteAll();

			try {
				reader = new BufferedReader(new InputStreamReader(getAssets().open(
						"uscconnect_opportunities_1414761945.csv"), "UTF-8"));
				// displayText("I've started ot open.");
	
				// do reading, usually loop until end of file reading
				String mLine = reader.readLine();

				// To dismiss the dialog
				while (mLine != null) {
					// process line
					String[] databaseArray = mLine.split("\",");
					myDb.insertRow(
					 databaseArray[0],
					 databaseArray[1],
					 databaseArray[2],
					 databaseArray[3],
					 databaseArray[4],
					 databaseArray[5],
					 databaseArray[6],
					 databaseArray[7],
					 databaseArray[8],
					 databaseArray[9],
					 databaseArray[10],
					 databaseArray[11],
					 databaseArray[12],
					 databaseArray[13],
					 databaseArray[14],
					 databaseArray[15],
					 databaseArray[16],
					 databaseArray[17],
					 databaseArray[18],
					 databaseArray[19],
					 databaseArray[20],
					 databaseArray[21],
					 databaseArray[22],
					 databaseArray[23],
					 databaseArray[24],
					 databaseArray[25],
					 databaseArray[26],
					 databaseArray[27],
					 databaseArray[28],
					 databaseArray[29],
					 databaseArray[30],
					 databaseArray[31],
					 databaseArray[32],
					 databaseArray[33]
					);
					//for (int i = 0; i < databaseArray.length; i++) {
	//					myDb.insertRow(
	//					 databaseArray[0],
	//					 databaseArray[1],
	//					 databaseArray[2],
	//					 databaseArray[3],
	//					 databaseArray[4],
	//					 databaseArray[5],
	//					 databaseArray[6],
	//					 databaseArray[7],
	//					 databaseArray[8],
	//					 databaseArray[9],
	//					 databaseArray[10],
	//					 databaseArray[11],
	//					 databaseArray[12],
	//					 databaseArray[13],
	//					 databaseArray[14],
	//					 databaseArray[15],
	//					 databaseArray[16],
	//					 databaseArray[17],
	//					 databaseArray[18],
	//					 databaseArray[19],
	//					 databaseArray[20],
	//					 databaseArray[21],
	//					 databaseArray[22],
	//					 databaseArray[23],
	//					 databaseArray[24],
	//					 databaseArray[25],
	//					 databaseArray[26],
	//					 databaseArray[27],
	//					 databaseArray[28],
	//					 databaseArray[29],
	//					 databaseArray[30],
	//					 databaseArray[31],
	//					 databaseArray[32],
	//					 databaseArray[33]
	//					);
					//}
	//				for (String retval: s){
	//					displayText(s[s.length-1]);
	//					//System.out.println(retval);
	//			      }
	//				displayText(mLine);
					mLine = reader.readLine();
				}
			} catch (IOException e) {
				// log the exception
			} finally {
				if (reader != null) {
					try {
						reader.close();
					} catch (IOException e) {
						// log the exception
					}
				}
			}
		}
//		progress.dismiss();
	}

	private void closeDB() {
		myDb.close();
	}

	private void displayText(String message) {
		TextView textView = (TextView) findViewById(R.id.textDisplay);
		textView.setText(message);
//		textView.setText(Html.fromHtml("blah blah <a href=\"http://www.sc.edu/uscconnect/participate/opport_detail.php?oid=630\">some text to be linkified</a> blah blah"));

		textView.setKeyListener(null);
	}
	
	public void downoladFile(){
		URL url = null;
		try {
			url = new URL("some url");
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//create the new connection
		HttpURLConnection urlConnection = null;
		try {
			urlConnection = (HttpURLConnection) url.openConnection();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//set up some things on the connection
		try {
			urlConnection.setRequestMethod("GET");
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		urlConnection.setDoOutput(true);
		//and connect!
		try {
			urlConnection.connect();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//set the path where we want to save the file
		//in this case, going to save it on the root directory of the
		//sd card.
		File SDCardRoot = new File("/sdcard/"+"Some Folder Name/");
		//create a new file, specifying the path, and the filename
		//which we want to save the file as.
		File file = new File(SDCardRoot,"some file name");
		//this will be used to write the downloaded data into the file we created
		FileOutputStream fileOutput = null;
		try {
			fileOutput = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//this will be used in reading the data from the internet
		InputStream inputStream = null;
		try {
			inputStream = urlConnection.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//this is the total size of the file
		int totalSize = urlConnection.getContentLength();
		//variable to store total downloaded bytes
		int downloadedSize = 0;
		//create a buffer...
		byte[] buffer = new byte[1024];
		int bufferLength = 0; //used to store a temporary size of the buffer
		//now, read through the input buffer and write the contents to the file
		try {
			while ( (bufferLength = inputStream.read(buffer)) > 0 ) 
			{
			//add the data in the buffer to the file in the file output stream (the file on the sd card
			fileOutput.write(buffer, 0, bufferLength);
			//add up the size so we know how much is downloaded
			downloadedSize += bufferLength;
			int progress=(int)(downloadedSize*100/totalSize);
			//this is where you would do something to report the prgress, like this maybe

			//updateProgress(downloadedSize, totalSize);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//close the output stream when done
		try {
			fileOutput.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void onClick_test(View v) {
		// AutoCompleteTextView actv;
		// actv = (AutoCompleteTextView)
		// findViewById(R.id.autoCompleteTextView1);
		InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE); 

		inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                   InputMethodManager.HIDE_NOT_ALWAYS);

		EditText Player = (EditText) findViewById(R.id.autoCompleteTextView1);
		String searchKeyword = Player.getText().toString(); // actv.getText().toString();
		Cursor c = null;
		if (searchKeyword.length() != 0){
			c = myDb.test(searchKeyword);
			displayRecordSet2(c, searchKeyword);
		}
	}
	
	public void onClick_AddRecord(View v) {
		displayText("Clicked add record!");

		long newId = myDb.insertRow("Jenny", "5559", "Green", "forth", "five",
				"six", "seven", "eight", "nine", "ten", "a11", "a12", "a13",
				"a14", "a15", "a16", "a17", "18", "a199", "a200", "a21", "a22",
				"a23", "a24", "a25", "a26", "a27", "28", "a299", "a300", "a31",
				"a32", "a33", "ab34");

		// Query for the record we just added.
		// Use the ID:
		Cursor cursor = myDb.getRow(newId);
		displayRecordSet(cursor);
	}

	public void onClick_ClearAll(View v) {
		displayText("Clicked clear all!");
		myDb.deleteAll();
	}

	public void onClick_DisplayRecords(View v) {
		displayText("Clicked display record!");

		Cursor cursor = myDb.getAllRows();
		displayRecordSet(cursor);
	}

	// Display an entire record set to the screen.
	private void displayRecordSet(Cursor cursor) {
		String message = "";
		// populate the message from the cursor

		// Reset cursor to start, checking to see if there's data:
		if (cursor.moveToFirst()) {
			do {
				// Process the data:
				int id = cursor.getInt(DBAdapter.COL_ROWID);
				String name = cursor.getString(DBAdapter.COL_ID);
				int studentNumber = cursor.getInt(DBAdapter.COL_TITLE);
				String favColour = cursor.getString(DBAdapter.COL_FAVCOLOUR);
				String forth = cursor.getString(DBAdapter.COL_forth);
				String five = cursor.getString(DBAdapter.COL_5);
				String six = cursor.getString(DBAdapter.COL_6);
				String seven = cursor.getString(DBAdapter.COL_7);
				String eight = cursor.getString(DBAdapter.COL_8);
				String nine = cursor.getString(DBAdapter.COL_9);
				String ten = cursor.getString(DBAdapter.COL_10);
				String eleven = cursor.getString(DBAdapter.COL_11);
				String twelve = cursor.getString(DBAdapter.COL_12);
				String thirteen = cursor.getString(DBAdapter.COL_13);
				String forteen = cursor.getString(DBAdapter.COL_14);
				String fifteen = cursor.getString(DBAdapter.COL_15);
				String sixteen = cursor.getString(DBAdapter.COL_16);
				String seventeen = cursor.getString(DBAdapter.COL_17);
				String eightteen = cursor.getString(DBAdapter.COL_18);
				String nineteen = cursor.getString(DBAdapter.COL_19);
				String twenty = cursor.getString(DBAdapter.COL_20);
				String twentyone = cursor.getString(DBAdapter.COL_21);
				String twentytwo = cursor.getString(DBAdapter.COL_22);
				String twentythree = cursor.getString(DBAdapter.COL_23);
				String twentyfour = cursor.getString(DBAdapter.COL_24);
				String twentyfive = cursor.getString(DBAdapter.COL_25);
				String twentysix = cursor.getString(DBAdapter.COL_26);
				String twentyseven = cursor.getString(DBAdapter.COL_27);
				String twentyeight = cursor.getString(DBAdapter.COL_28);
				String twentynine = cursor.getString(DBAdapter.COL_29);
				String thirty = cursor.getString(DBAdapter.COL_30);
				String thirtyone = cursor.getString(DBAdapter.COL_31);
				String thirtytwo = cursor.getString(DBAdapter.COL_32);
				String thirtythree = cursor.getString(DBAdapter.COL_33);
				String thirtyfour = cursor.getString(DBAdapter.COL_34);

				// String = cursor.getString(DBAdapter.COL_8);

				// Append data to the message:
				message += "id=" + id + ", name=" + name + ", #="
						+ studentNumber + ", Colour=" + favColour + ", 4="
						+ forth + ", 5=" + five + ", 6=" + six + ", 7=" + seven
						+ ", 8=" + eight + ", 4=" + nine + ", 6=" + ten
						+ ", 7=" + eleven + ", 8=" + twelve + ", 4=" + thirteen
						+ ", 5=" + forteen + ", 6=" + fifteen + ", 7="
						+ sixteen + ", 8=" + seventeen + ", 4=" + eightteen
						+ ", 5=" + nineteen + ", 6=" + twenty + ", twentyone="
						+ twentyone + ", twentytwo=" + twentytwo
						+ ", twentythree=" + twentythree + ", twentyfour="
						+ twentyfour + ", 6=" + twentyfive + ", 7=" + twentysix
						+ ", 8=" + twentyseven + ", 4=" + twentyeight + ", 5="
						+ twentynine + ", 6=" + thirty + ", twentyone="
						+ thirtyone + ", twentytwo=" + thirtytwo
						+ ", twentythree=" + thirtythree + ", twentyfour="
						+ thirtyfour

						+ "\n ************* \n";
			} while (cursor.moveToNext());
		}

		// Close the cursor to avoid a resource leak.
		cursor.close();

		displayText(message);
	}

	private void displayRecordSet2(Cursor cursor, String searchKeyword) {
		String message = "";
		// populate the message from the cursor

		// Reset cursor to start, checking to see if there's data:
		if (cursor.moveToFirst()) {
			do {
				// Process the data:
//				int id = cursor.getInt(DBAdapter.COL_ROWID);
				String id = cursor.getString(DBAdapter.COL_ID).replace('\"', '\0');
				String name = cursor.getString(DBAdapter.COL_TITLE).replace('\"', '\0');
				int studentNumber = cursor.getInt(9);
				String favColour = cursor.getString(3).replace('\"', '\0');

				// Append data to the message:
				String website = "http://www.sc.edu/uscconnect/participate/opport_detail.php?oid=" + id ;
				message += name + "\n"
						+ website
						+ "\n----------------------------------------------\n";
			} while (cursor.moveToNext());
		}

		// Close the cursor to avoid a resource leak.
		cursor.close();

		displayText(message);
	
	
	/*
	 * @Override public boolean onCreateOptionsMenu(Menu menu) { // Inflate the
	 * menu; this adds items to the action bar if it is present.
	 * getMenuInflater().inflate(R.menu.search_page, menu); return true; }
	 * 
	 * @Override public boolean onOptionsItemSelected(MenuItem item) { // Handle
	 * action bar item clicks here. The action bar will // automatically handle
	 * clicks on the Home/Up button, so long // as you specify a parent activity
	 * in AndroidManifest.xml. int id = item.getItemId(); if (id ==
	 * R.id.action_settings) { return true; } return
	 * super.onOptionsItemSelected(item); }
	 */

}

//	private class LongOperation extends AsyncTask<String, Void, String> {
//
//        @Override
//        protected String doInBackground(String... params) {
////            openDB();
//            return "Executed";
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//
//        	View b = findViewById(R.id.button1);
//        	b.setVisibility(View.VISIBLE);
////            EditText editText = (EditText)findViewById(R.id.editText1);
////			editText.setText("Executed", TextView.BufferType.EDITABLE);
//            // might want to change "executed" for the returned string passed
//            // into onPostExecute() but that is upto you
//        }
//
//        @Override
//        protected void onPreExecute() {
//        }
//
//        @Override
//        protected void onProgressUpdate(Void... values) {}
//    }
//	
	
	 private class LongOperation extends AsyncTask<String, Void, String> {

	        @Override
	        protected String doInBackground(String... params) {
	            openDB();
	            return "Executed";
	        }

	        @Override
	        protected void onPostExecute(String result) {
	            TextView txt = (TextView) findViewById(R.id.autoCompleteTextView1);
	            txt.setText("Executed"); // txt.setText(result);
	            // might want to change "executed" for the returned string passed
	            // into onPostExecute() but that is upto you
	        }

	        @Override
	        protected void onPreExecute() {}

	        @Override
	        protected void onProgressUpdate(Void... values) {}
	    }
}
