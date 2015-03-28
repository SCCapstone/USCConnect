

package com.example.search;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.example.search.NoteEdit;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class StudentLog extends ListActivity {
	  private static final int ACTIVITY_CREATE=0;
	    private static final int ACTIVITY_EDIT=1;
	    
	    private static final int INSERT_ID = Menu.FIRST;
	    private static final int DELETE_ID = Menu.FIRST + 1;
	    
	    
	    private NotesDbAdapter mDbHelper;
	    
	    /** Called when the activity is first created. */
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_student_log);
	        mDbHelper = new NotesDbAdapter(this);
	        mDbHelper.open();
	        fillData();
	        Button add = (Button)findViewById(R.id.button1);
	        add.setOnClickListener(new View.OnClickListener() {
	        	@Override
	        	public void onClick(View view) {
	        		createNote();
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
	       // String[] date = new String[]{NotesDbAdapter.CREATION_DATE};
	        
	        
	        
	        // and an array of the fields we want to bind those fields to (in this case just text1)
	        int[] to = new int[]{R.id.text1};
	        //int[] correctDate = new int[]{R.id.date};
	        
	        // Now create a simple cursor adapter and set it to display
	        SimpleCursorAdapter notes = 
	        	    new SimpleCursorAdapter(this, R.layout.notes_row, notesCursor, from, to);
	        setListAdapter(notes);
	    }
	    
	    @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
//			getMenuInflater().inflate(R.menu.student_log, menu);
			 //menu.add(0, INSERT_ID, 0, R.string.menu_insert);
		     //menu.add(0, DELETE_ID, 0,  R.string.menu_delete);
			return true;
		}

	    @Override
	    public boolean onMenuItemSelected(int featureId, MenuItem item) {
	        switch(item.getItemId()) {
	        case INSERT_ID:
	            createNote();
	            return true;
	        case DELETE_ID:
	            mDbHelper.deleteNote(getListView().getSelectedItemId());
	            fillData();
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
}
