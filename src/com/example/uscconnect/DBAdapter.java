/*
 * Copyright (C) 2008 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
// TODO: Change the package to match your project.
package com.example.uscconnect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


// TO USE:
// Change the package (at top) to match your project.
// Search for "TODO", and make the appropriate changes.
public class DBAdapter {

	/////////////////////////////////////////////////////////////////////
	//	Constants & Data
	/////////////////////////////////////////////////////////////////////
	// For logging:
	private static final String TAG = "DBAdapter";
	
	// DB Fields
	public static final String KEY_ROWID = "_id";
	public static final int COL_ROWID = 0;
	/*
	 * CHANGE 1:
	 */
	// TODO: Setup your fields here:
	public static final String KEY_ID = "name";
	public static final String KEY_TITLE = "studentnum";
	public static final String KEY_EXTERNALLINK = "favcolour";
	public static final String KEY_DESCRIPTION = "forth";
	public static final String KEY_EXPIRATIONDATE = "five";
	public static final String KEY_OPPORTUNITYTYPE= "six";
	public static final String KEY_OPPORTUNITYTYPEOTHER= "seven";
	public static final String KEY_TIMEFRAME= "eight";
	public static final String KEY_TIMEFRAMEOTHER = "nine";
	public static final String KEY_TARGETGROUP= "ten";
	public static final String KEY_TARGETGROUPOTHER= "eleven";
	public static final String KEY_CREATIVECOMPONENT= "twelve";
	public static final String KEY_CREATIVECOMPONENTOTHER = "thirteen";
	public static final String KEY_STORYTITLE= "fourteen";
	public static final String KEY_STORYEXTERNALLINK= "fifteen";
	public static final String KEY_STORYDESCRIPTION= "sixteen";
	public static final String KEY_STORYPHOTO = "seventeen";
	public static final String KEY_SPONSORCAMPUS= "eightteen";
	public static final String KEY_SPONSORPROGRAM= "nineteen";
	public static final String KEY_SPONSORCOLLEGE= "twinty";
	public static final String KEY_SPONSOROTHER = "twintyone";
	public static final String KEY_PARTICIPATIONINSTRUCTIONS= "twintytwo";
	public static final String KEY_EMAIL= "twintythree";
	public static final String KEY_PHONE= "twintyfour";
	public static final String KEY_PHONEEXT= "twenetyfive";
	public static final String KEY_NAME= "twenetysix";
	public static final String KEY_SUBMITTEDEMAIL = "twenetyseven";
	public static final String KEY_STATUS= "twenetyeight";
	public static final String KEY_AUTOARCHIVEDATE= "twenetynine";
	public static final String KEY_USERADDED= "thirty";
	public static final String KEY_USER2ACCES = "thirtyone";
	public static final String KEY_DATEADDED= "thirtytwo";
	public static final String KEY_DATEDELETED= "thirtythree";
	public static final String KEY_DATEUPDATED= "thirtyfour";

	// TODO: Setup your field numbers here (0 = KEY_ROWID, 1=...)
	public static final int COL_ID = 1;
	public static final int COL_TITLE = 2;
	public static final int COL_EXTERNALLINK = 3;
	public static final int COL_DESCRIPTION = 4;
	public static final int COL_EXPIRATIONDATE = 5;
	public static final int COL_OPPORTUNITYTYPE = 6;
	public static final int COL_OPPORTUNITYTYPEOTHER = 7;
	public static final int COL_TIMEFRAME = 8;
	public static final int COL_TIMEFRAMEOTHER = 9;
	public static final int COL_TARGETGROUP = 10;
	public static final int COL_TARGETGROUPOTHER = 11;
	public static final int COL_CREATIVECOMPONENT = 12;
	public static final int COL_CREATIVECOMPONENTOTHER = 13;
	public static final int COL_STORYTITLE = 14;
	public static final int COL_STORYEXTERNALLINK = 15;
	public static final int COL_STORYDESCRIPTION = 16;
	public static final int COL_STORYPHOTO = 17;
	public static final int COL_STORYCAMPUS = 18;
	public static final int COL_SPONSORPROGRAM = 19;
	public static final int COL_SPONSORCOLLEGE = 20;
	public static final int COL_SPONSOROTHER = 21;
	public static final int COL_PARTICIPATIONINSTRUCTIONS = 22;
	public static final int COL_EMAIL = 23;
	public static final int COL_PHONE = 24;
	public static final int COL_PHONEEXT = 25;
	public static final int COL_NAME = 26;
	public static final int COL_SUBMITTEDEMAIL = 27;
	public static final int COL_STATUS = 28;
	public static final int COL_AUTOARCHIVEDATE = 29;
	public static final int COL_USERADED = 30;
	public static final int COL_USER2ADDED = 31;
	public static final int COL_DATEADDED = 32;
	public static final int COL_DATEDELETED = 33;
	public static final int COL_DATEUPDATED = 34;
	


	
	public static final String[] ALL_KEYS = new String[] {
		KEY_ROWID,
		KEY_ID,
		KEY_TITLE, 
		KEY_EXTERNALLINK,
		KEY_DESCRIPTION,
		KEY_EXPIRATIONDATE,
		KEY_OPPORTUNITYTYPE,
		KEY_OPPORTUNITYTYPEOTHER,
		KEY_TIMEFRAME,
		KEY_TIMEFRAME,
		KEY_TIMEFRAMEOTHER,
		KEY_TARGETGROUP,
		KEY_TARGETGROUPOTHER,
		KEY_CREATIVECOMPONENT,
		KEY_CREATIVECOMPONENTOTHER,
		KEY_STORYTITLE,
		KEY_STORYEXTERNALLINK,
		KEY_STORYDESCRIPTION,
		KEY_STORYPHOTO,
		KEY_SPONSORCAMPUS,
		KEY_SPONSORPROGRAM,
		KEY_SPONSORCOLLEGE,
		KEY_SPONSOROTHER,
		KEY_PARTICIPATIONINSTRUCTIONS,
		KEY_EMAIL,
		KEY_PHONE,
		KEY_PHONEEXT,
		KEY_NAME,
		KEY_SUBMITTEDEMAIL,
		KEY_STATUS,
		KEY_AUTOARCHIVEDATE,
		KEY_USERADDED,
		KEY_USER2ACCES,
		KEY_DATEADDED,
		KEY_DATEDELETED,
		KEY_DATEUPDATED
		};
	
	// DB info: it's name, and the table we are using (just one).
	public static final String DATABASE_NAME = "MyDb";
	public static final String DATABASE_TABLE = "mainTable";
	// Track DB version if a new version of your app changes the format.
	public static final int DATABASE_VERSION = 2;	
	
	private static final String DATABASE_CREATE_SQL = 
			"create table " + DATABASE_TABLE 
			+ " (" + KEY_ROWID + " integer primary key autoincrement, "
			
			/*
			 * CHANGE 2:
			 */
			// TODO: Place your fields here!
			// + KEY_{...} + " {type} not null"
			//	- Key is the column name you created above.
			//	- {type} is one of: text, integer, real, blob
			//		(http://www.sqlite.org/datatype3.html)
			//  - "not null" means it is a required field (must be given a value).
			// NOTE: All must be comma separated (end of line!) Last one must have NO comma!!
			+ KEY_ID + " text not null, "
			+ KEY_TITLE + " string not null, "
			+ KEY_EXTERNALLINK + " string not null,"
			+ KEY_DESCRIPTION + " string not null,"
			+ KEY_EXPIRATIONDATE + " string not null,"
			+ KEY_OPPORTUNITYTYPE + " string not null,"
			+ KEY_OPPORTUNITYTYPEOTHER + " string not null,"
			+ KEY_TIMEFRAME + " string not null,"
			+ KEY_TIMEFRAMEOTHER + " string not null,"
			+ KEY_TARGETGROUP + " string not null,"
			+ KEY_TARGETGROUPOTHER + " string not null,"
			+ KEY_CREATIVECOMPONENT + " string not null,"
			+ KEY_CREATIVECOMPONENTOTHER + " string not null,"
			+ KEY_STORYTITLE + " string not null,"
			+ KEY_STORYEXTERNALLINK + " string not null,"
			+ KEY_STORYDESCRIPTION + " string not null,"
			+ KEY_STORYPHOTO + " string not null,"
			+ KEY_SPONSORCAMPUS + " string not null,"
			+ KEY_SPONSORPROGRAM + " string not null,"
			+ KEY_SPONSORCOLLEGE + " string not null,"
			+ KEY_SPONSOROTHER + " string not null,"
			+ KEY_PARTICIPATIONINSTRUCTIONS + " string not null,"
			+ KEY_EMAIL + " string not null,"
			+ KEY_PHONE + " string not null,"
			+ KEY_PHONEEXT + " string not null,"
			+ KEY_NAME + " string not null,"
			+ KEY_SUBMITTEDEMAIL + " string not null,"
			+ KEY_STATUS + " string not null,"
			+ KEY_AUTOARCHIVEDATE + " string not null,"
			+ KEY_USERADDED + " string not null,"
			+ KEY_USER2ACCES + " string not null,"
			+ KEY_DATEADDED + " string not null,"
			+ KEY_DATEDELETED + " string not null,"
			+ KEY_DATEUPDATED + " string not null"
			
			// Rest  of creation:
			+ ");";
	
	// Context of application who uses us.
	private final Context context;
	
	private DatabaseHelper myDBHelper;
	public static SQLiteDatabase db;

	/////////////////////////////////////////////////////////////////////
	//	Public methods:
	/////////////////////////////////////////////////////////////////////
	
	public DBAdapter(Context ctx) {
		this.context = ctx;
		myDBHelper = new DatabaseHelper(context);
	}
	
	// Open the database connection.
	public DBAdapter open() {
		db = myDBHelper.getWritableDatabase();
		
	
		return this;
	}
	
	// Close the database connection.
	public void close() {
		myDBHelper.close();
	}
	
	// Add a new set of values to the database.
	public long insertRow(String opport_id,
			String title,
			String favColour,
			String forth,
			String five,
			String six,
			String seven,
			String eight,
			String nine,
			String ten,
			String eleven,
			String twelve,
			String thirteen,
			String fourteen,
			String fifteen,
			String sixteen,
			String seventeen,
			String eightteen,
			String nineteen,
			String twenty,
			String twentyone,
			String twentytwo,
			String twentythree,
			String twentyfour,
			String twentyfive,
			String twentysix,
			String twentyseven,
			String twentyeight,
			String twentynine,
			String thirty,
			String thirtyone,
			String thirtytwo,
			String thirtythree,
			String thirtyfour
			) {
		/*
		 * CHANGE 3:
		 */		
		// TODO: Update data in the row with new fields.
		// TODO: Also change the function's arguments to be what you need!
		// Create row's data:
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_ID, opport_id);
		initialValues.put(KEY_TITLE, title);
		initialValues.put(KEY_EXTERNALLINK, favColour);
		initialValues.put(KEY_DESCRIPTION, forth);
		initialValues.put(KEY_EXPIRATIONDATE, five);
		initialValues.put(KEY_OPPORTUNITYTYPE, six);
		initialValues.put(KEY_OPPORTUNITYTYPEOTHER, seven);
		initialValues.put(KEY_TIMEFRAME, eight);
		initialValues.put(KEY_TIMEFRAMEOTHER, nine);
		initialValues.put(KEY_TARGETGROUP, ten);
		initialValues.put(KEY_TARGETGROUPOTHER, eleven);
		initialValues.put(KEY_CREATIVECOMPONENT, twelve);
		initialValues.put(KEY_CREATIVECOMPONENTOTHER, thirteen);
		initialValues.put(KEY_STORYTITLE, fourteen);
		initialValues.put(KEY_STORYEXTERNALLINK, fifteen);
		initialValues.put(KEY_STORYDESCRIPTION, sixteen);
		initialValues.put(KEY_STORYPHOTO, seventeen);
		initialValues.put(KEY_SPONSORCAMPUS, eightteen);
		initialValues.put(KEY_SPONSORPROGRAM, nineteen);
		initialValues.put(KEY_SPONSORCOLLEGE, twenty);
		initialValues.put(KEY_SPONSOROTHER, twentyone);
		initialValues.put(KEY_PARTICIPATIONINSTRUCTIONS, twentytwo);
		initialValues.put(KEY_EMAIL, twentythree);
		initialValues.put(KEY_PHONE, twentyfour);
		initialValues.put(KEY_PHONEEXT, twentyfive);
		initialValues.put(KEY_NAME, twentysix);
		initialValues.put(KEY_SUBMITTEDEMAIL, twentyseven);
		initialValues.put(KEY_STATUS, twentyeight);
		initialValues.put(KEY_AUTOARCHIVEDATE, twentynine);
		initialValues.put(KEY_USERADDED, thirty);
		initialValues.put(KEY_USER2ACCES, thirtyone);
		initialValues.put(KEY_DATEADDED, thirtytwo);
		initialValues.put(KEY_DATEDELETED, thirtythree);
		initialValues.put(KEY_DATEUPDATED, thirtyfour);
		
		// Insert it into the database.
		return db.insert(DATABASE_TABLE, null, initialValues);
	}
	
	// Delete a row from the database, by rowId (primary key)
	public boolean deleteRow(long rowId) {
		String where = KEY_ROWID + "=" + rowId;		
		return db.delete(DATABASE_TABLE, where, null) != 0;
	}
	
	public void deleteAll() {
		Cursor c = getAllRows();
		long rowId = c.getColumnIndexOrThrow(KEY_ROWID);
		if (c.moveToFirst()) {
			do {
				deleteRow(c.getLong((int) rowId));				
			} while (c.moveToNext());
		}
		c.close();
	}
	
	// Return all data in the database.
	public Cursor getAllRows() {
		String where = null;
		Cursor c = 	db.query(true, DATABASE_TABLE, ALL_KEYS, 
							where, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	// Get a specific row (by rowId)
	public Cursor getRow(long rowId) {
		String where = KEY_ROWID + "=" + rowId;
		Cursor c = 	db.query(true, DATABASE_TABLE, ALL_KEYS, 
						where, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}
	
	// Change an existing row to be equal to new data.
	public boolean updateRow(long rowId, String name, int studentNum, String favColour) {
		String where = KEY_ROWID + "=" + rowId;

		/*
		 * CHANGE 4:
		 */
		// TODO: Update data in the row with new fields.
		// TODO: Also change the function's arguments to be what you need!
		// Create row's data:
		ContentValues newValues = new ContentValues();
		newValues.put(KEY_ID, name);
		newValues.put(KEY_TITLE, studentNum);
		newValues.put(KEY_EXTERNALLINK, favColour);
		
		// Insert it into the database.
		return db.update(DATABASE_TABLE, newValues, where, null) != 0;
	}
	
	
	
	/////////////////////////////////////////////////////////////////////
	//	Private Helper Classes:
	/////////////////////////////////////////////////////////////////////
	
	/**
	 * Private class which handles database creation and upgrading.
	 * Used to handle low-level database access.
	 */
	private static class DatabaseHelper extends SQLiteOpenHelper
	{
		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase _db) {
			_db.execSQL(DATABASE_CREATE_SQL);			
		}
		
		
		@Override
		public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion) {
			Log.w(TAG, "UpgradinCOL_forthCOL_forthg application's database from version " + oldVersion
					+ " to " + newVersion + ", which will destroy all old data!");
			
			// Destroy old database:
			_db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			
			// Recreate new database:
			onCreate(_db);
		}
	}


	public Cursor test(String description) {
		// TODO Auto-generated method stub
		Cursor crs=db.rawQuery("SELECT * FROM "
				+ DATABASE_TABLE +" WHERE " 
				+ KEY_DESCRIPTION + " LIKE '%" + description + "%';", null);
		return crs;
//		db.execSQL("SELECT * FROM " + DATABASE_TABLE +" WHERE " + COL_8 + " LIKE '%gh%';");	
	}
	public static void insertMultipleRows(String query) {
		db.execSQL(query);			
	}
	
	public static void deletAll() {
		db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);			
//		db.execSQL(query);			
	}
}
