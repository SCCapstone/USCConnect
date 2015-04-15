package com.example.uscconnect;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.example.uscconnect.NoteEdit;
import com.example.uscconnect.R;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.app.backup.BackupManager;
public class StudentLog extends ListActivity 
{
	  private static final int ACTIVITY_CREATE=0;
	    private static final int ACTIVITY_EDIT=1;
	    
	    private static final int INSERT_ID = Menu.FIRST;
	    private static final int DELETE_ID = Menu.FIRST + 1;
	    
		private DrawerLayout mDrawerLayout;
		private ListView mDrawerList;
		private ActionBarDrawerToggle mDrawerToggle;

		private CharSequence mDrawerTitle;
		private CharSequence mTitle;
		CustomDrawerAdapter adapter;

		List<DrawerItem> dataList;
	    BackupManager bm = new BackupManager(this);
	    
	    private NotesDbAdapter mDbHelper;
	    
	    /** Called when the activity is first created. */
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_student_log);
			addDrawerItems();
	        mDbHelper = new NotesDbAdapter(this);
	        mDbHelper.open();
	        fillData();
	        Button add = (Button)findViewById(R.id.button1);
	        add.setOnClickListener(new View.OnClickListener() {
	        	@Override
	        	public void onClick(View view) {
	        		createNote();
	        		bm.dataChanged();
	        	}
	        });
	        
	        /*Button delete = (Button)findViewById(R.id.button2);
	        delete.setOnClickListener(new View.OnClickListener() {
	            @Override
				public void onClick(View view) {
	            	mDbHelper.deleteNote(getListView().getSelectedItemId());
		            fillData();
	            }
	        });*/
	    }
	    
	    private void fillData() {
	        Cursor notesCursor = mDbHelper.fetchAllNotes();
	        startManagingCursor(notesCursor);
	        

	        
	        // Create an array to specify the fields we want to display in the list (only TITLE)
	        String[] from = new String[]{NotesDbAdapter.KEY_TITLE};
	        
	        
	        
	        // and an array of the fields we want to bind those fields to (in this case just text1)
	        int[] to = new int[]{R.id.text1};
	        
	        // Now create a simple cursor adapter and set it to display
	        SimpleCursorAdapter notes = 
	        	    new SimpleCursorAdapter(this, R.layout.notes_row, notesCursor, from, to);
	        setListAdapter(notes);
	    }
	    
	    @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.student_log, menu);
			return true;
		}

	    @Override
	    public boolean onMenuItemSelected(int featureId, MenuItem item) {
	        switch(item.getItemId()) {
	        case INSERT_ID:
	            createNote();
	            bm.dataChanged();
	            return true;
	        case DELETE_ID:
	            mDbHelper.deleteNote(getListView().getSelectedItemId());
	            fillData();
	            bm.dataChanged();
	            return true;
	        }
	       
	        return super.onMenuItemSelected(featureId, item);
	    }

	    private void createNote() {
	        Intent i = new Intent(this, NoteEdit.class);
	        startActivityForResult(i, ACTIVITY_CREATE);
	    }
	    
	    @Override
	    protected void onListItemClick(ListView l, View v, int position, long id) {
	        super.onListItemClick(l, v, position, id);
	        Intent i = new Intent(this, NoteEdit.class);
	        i.putExtra(NotesDbAdapter.KEY_ROWID, id);
	        startActivityForResult(i, ACTIVITY_EDIT);
	    }

	    @Override
	    protected void onActivityResult(int requestCode, int resultCode, 
	                                    Intent intent) {
	        super.onActivityResult(requestCode, resultCode, intent);
	        fillData();
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

		
		private class DrawerItemClickListener implements ListView.OnItemClickListener {
	           @Override
	           public void onItemClick(AdapterView<?> parent, View view, int position, long id) 
	           {
		         SelectItem(position);

	           }
	}
}
