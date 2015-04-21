package com.example.uscconnect;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SearchPage extends ActionBarActivity {
	String selectedTimeFrame = "";
	String selectedType = "";
	String resString = "";
	static DBAdapter myDb;
	private String[] arraySpinner;
	final String[] data ={"one","two","three"};
	
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
        setContentView(R.layout.activity_search);
        
        addDrawerItems();
        new LongOperation().execute("");
        
		
//        MainActivity m = new MainActivity();
//        m.onCreateDrawer();
        
        CheckBox yourCheckBox = (CheckBox) findViewById (R.id.checkBoxUpdate);

        yourCheckBox.setOnClickListener(new OnClickListener() {

              @Override
              public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                	((CheckBox) v).setEnabled(false);
//            		new LongOperation().execute("");
            		Toast.makeText(SearchPage.this,
            			      "Updating... Please wait." , Toast.LENGTH_LONG)
            			      .show();
            		((CheckBox) v).toggle();
//            		((CheckBox) v).setEnabled(true);
                }                	
              }
        });
        
		Button Log = (Button)findViewById(R.id.button1);
        Log.setOnClickListener(new View.OnClickListener() {
            @Override
			public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), searchResult.class);
                Bundle b = new Bundle();
                String[] value={selectedTimeFrame,selectedType,(((EditText) findViewById(R.id.editText1)).getText().toString()).toString()};                
                b.putStringArray("key", value);
                myIntent.putExtras(b);
                startActivity(myIntent);
            }
        });
        
		setKeyboard();
//		openDB();
		setTimeFrameDropList();
		setOpportunitiesDropList();

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
		dataList.add(new DrawerItem("Personal Log"));
		dataList.add(new DrawerItem("GLD Application"));
		dataList.add(new DrawerItem("About Us"));

		

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


	private void setOpportunitiesDropList() {
		// TODO Auto-generated method stub
		this.arraySpinner = new String[] { 
				"Any",
				"Community Service",
				"National Experience",
				"International Experience",
				"Professional Work-based Experience", 
				"Research/Inquiry", 
				"Student Organization",
				"Leadership Opportunity",
				"Includes integrative learning/creative component",
				"Scholarship"
				};
		Spinner s = (Spinner) findViewById(R.id.Spinner01);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, arraySpinner);
		s.setAdapter(adapter);
		s.setOnItemSelectedListener(new OnItemSelectedListener() {
	
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {
				selectedType = parent.getItemAtPosition(position).toString();						
//				Toast.makeText(parent.getContext(),
//						"\"" + selectedType + "\" was selected.",
//						Toast.LENGTH_SHORT).show();
			}
	
			@Override
			public void onNothingSelected(AdapterView<?> parent) {}
		});
		
	}

	private void setKeyboard() {
		EditText editText =(EditText)findViewById(R.id.editText1); 
		editText.setOnEditorActionListener(new TextView.OnEditorActionListener(){  

		    @Override 
		    public boolean onEditorAction(TextView arg0, int arg1, KeyEvent arg2) { 
		        if(arg1 == EditorInfo.IME_ACTION_SEARCH)  
		        { 
		            // search pressed and perform your functionality.
		        }
		        return false; 
		    }

		});
		
	}
//	public void searchButtonOnClick(View v) {
//		Intent intent = new Intent(v.this, ToActivity.class);
//	    startActivity(intent);	
//	}
	
	private void setTimeFrameDropList() {
		// TODO Auto-generated method stub
		this.arraySpinner = new String[] {
				"Any", 
				"Fall Semester",
				"Maymester",
				"Summer", 
				"Ongoing"
				};
		Spinner s = (Spinner) findViewById(R.id.spinner1);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, arraySpinner);
		s.setAdapter(adapter);
		s.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {
				selectedTimeFrame = parent.getItemAtPosition(position).toString();						
//				Toast.makeText(parent.getContext(),
//						"\"" + selectedTimeFrame + "\" was selected.",
//						Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
		return true;
	}
	
	private void parseFile() {
//		LinearLayout linlaHeaderProgress = (LinearLayout) findViewById(R.id.linlaHeaderProgress);

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
				File myFile = new File("/sdcard/uscconnect_opportunities_1414761945.csv");
				myFile.createNewFile();
				reader = new BufferedReader(new FileReader(myFile));
				// displayText("I've started ot open.");
	
				// do reading, usually loop until end of file reading
				String mLine = reader.readLine();
//				EditText editText = (EditText)findViewById(R.id.editText1);
//				editText.setText(mLine, TextView.BufferType.EDITABLE);
				
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
					mLine = reader.readLine();
				}
			} catch (IOException e) {} 
			finally {
				if (reader != null) {
					try {
						reader.close();
					} catch (IOException e) {}
				}
			}
		}

	}

	private void closeDB() {
		myDb.close();
	}
	
	private class LongOperation extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            parseFile();
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {

        	View b = findViewById(R.id.button1);
        	b.setVisibility(View.VISIBLE);
//        	View c = findViewById(R.id.checkBoxUpdate);
//        	c.setVisibility(View.VISIBLE);
//        	c.setEnabled(true);
        }

        @Override
        protected void onPreExecute() {
        	  EditText editText = (EditText)findViewById(R.id.editText1);
        	  View b = findViewById(R.id.button1);
          	  b.setVisibility(View.VISIBLE);
          	  View c = findViewById(R.id.checkBoxUpdate);
//          	  c.setVisibility(View.INVISIBLE);
//  			  editText.setText("Executed", TextView.BufferType.EDITABLE);
        }

        @Override
        protected void onProgressUpdate(Void... values) {}
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
