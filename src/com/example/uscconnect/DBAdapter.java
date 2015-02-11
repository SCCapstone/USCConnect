<<<<<<< HEAD
// ------------------------------------ DBADapter.java ---------------------------------------------

// TODO: Change the package to match your project.
package com.example.uscconnect;

=======
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

>>>>>>> origin/master
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
<<<<<<< HEAD
	public static final String KEY_NAME = "name";
	public static final String KEY_STUDENTNUM = "studentnum";
	public static final String KEY_FAVCOLOUR = "favcolour";
	
	// TODO: Setup your field numbers here (0 = KEY_ROWID, 1=...)
	public static final int COL_NAME = 1;
	public static final int COL_STUDENTNUM = 2;
	public static final int COL_FAVCOLOUR = 3;

	
	public static final String[] ALL_KEYS = new String[] {KEY_ROWID, KEY_NAME, KEY_STUDENTNUM, KEY_FAVCOLOUR};
=======
	public static final String KEY_ID = "name";
	public static final String KEY_TITLE = "studentnum";
	public static final String KEY_FAVCOLOUR = "favcolour";
	public static final String KEY_forth = "forth";
	public static final String KEY_5 = "five";
	public static final String KEY_6= "six";
	public static final String KEY_7= "seven";
	public static final String KEY_8= "eight";
	public static final String KEY_9 = "nine";
	public static final String KEY_10= "ten";
	public static final String KEY_11= "eleven";
	public static final String KEY_12= "twelve";
	public static final String KEY_13 = "thirteen";
	public static final String KEY_14= "fourteen";
	public static final String KEY_15= "fifteen";
	public static final String KEY_16= "sixteen";
	public static final String KEY_17 = "seventeen";
	public static final String KEY_18= "eightteen";
	public static final String KEY_19= "nineteen";
	public static final String KEY_20= "twinty";
	public static final String KEY_21 = "twintyone";
	public static final String KEY_22= "twintytwo";
	public static final String KEY_23= "twintythree";
	public static final String KEY_24= "twintyfour";
	public static final String KEY_25= "twenetyfive";
	public static final String KEY_26= "twenetysix";
	public static final String KEY_27 = "twenetyseven";
	public static final String KEY_28= "twenetyeight";
	public static final String KEY_29= "twenetynine";
	public static final String KEY_30= "thirty";
	public static final String KEY_31 = "thirtyone";
	public static final String KEY_32= "thirtytwo";
	public static final String KEY_33= "thirtythree";
	public static final String KEY_34= "thirtyfour";

	// TODO: Setup your field numbers here (0 = KEY_ROWID, 1=...)
	public static final int COL_ID = 1;
	public static final int COL_TITLE = 2;
	public static final int COL_FAVCOLOUR = 3;
	public static final int COL_forth = 4;
	public static final int COL_5 = 5;
	public static final int COL_6 = 6;
	public static final int COL_7 = 7;
	public static final int COL_8 = 8;
	public static final int COL_9 = 9;
	public static final int COL_10 = 10;
	public static final int COL_11 = 11;
	public static final int COL_12 = 12;
	public static final int COL_13 = 13;
	public static final int COL_14 = 14;
	public static final int COL_15 = 15;
	public static final int COL_16 = 16;
	public static final int COL_17 = 17;
	public static final int COL_18 = 18;
	public static final int COL_19 = 19;
	public static final int COL_20 = 20;
	public static final int COL_21 = 21;
	public static final int COL_22 = 22;
	public static final int COL_23 = 23;
	public static final int COL_24 = 24;
	public static final int COL_25 = 25;
	public static final int COL_26 = 26;
	public static final int COL_27 = 27;
	public static final int COL_28 = 28;
	public static final int COL_29 = 29;
	public static final int COL_30 = 30;
	public static final int COL_31 = 31;
	public static final int COL_32 = 32;
	public static final int COL_33 = 33;
	public static final int COL_34 = 34;
	


	
	public static final String[] ALL_KEYS = new String[] {
		KEY_ROWID,
		KEY_ID,
		KEY_TITLE, 
		KEY_FAVCOLOUR,
		KEY_forth,
		KEY_5,
		KEY_6,
		KEY_7,
		KEY_8,
		KEY_8,
		KEY_9,
		KEY_10,
		KEY_11,
		KEY_12,
		KEY_13,
		KEY_14,
		KEY_15,
		KEY_16,
		KEY_17,
		KEY_18,
		KEY_19,
		KEY_20,
		KEY_21,
		KEY_22,
		KEY_23,
		KEY_24,
		KEY_25,
		KEY_26,
		KEY_27,
		KEY_28,
		KEY_29,
		KEY_30,
		KEY_31,
		KEY_32,
		KEY_33,
		KEY_34
		};
>>>>>>> origin/master
	
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
<<<<<<< HEAD
			+ KEY_NAME + " text not null, "
			+ KEY_STUDENTNUM + " integer not null, "
			+ KEY_FAVCOLOUR + " string not null"
=======
			+ KEY_ID + " text not null, "
			+ KEY_TITLE + " string not null, "
			+ KEY_FAVCOLOUR + " string not null,"
			+ KEY_forth + " string not null,"
			+ KEY_5 + " string not null,"
			+ KEY_6 + " string not null,"
			+ KEY_7 + " string not null,"
			+ KEY_8 + " string not null,"
			+ KEY_9 + " string not null,"
			+ KEY_10 + " string not null,"
			+ KEY_11 + " string not null,"
			+ KEY_12 + " string not null,"
			+ KEY_13 + " string not null,"
			+ KEY_14 + " string not null,"
			+ KEY_15 + " string not null,"
			+ KEY_16 + " string not null,"
			+ KEY_17 + " string not null,"
			+ KEY_18 + " string not null,"
			+ KEY_19 + " string not null,"
			+ KEY_20 + " string not null,"
			+ KEY_21 + " string not null,"
			+ KEY_22 + " string not null,"
			+ KEY_23 + " string not null,"
			+ KEY_24 + " string not null,"
			+ KEY_25 + " string not null,"
			+ KEY_26 + " string not null,"
			+ KEY_27 + " string not null,"
			+ KEY_28 + " string not null,"
			+ KEY_29 + " string not null,"
			+ KEY_30 + " string not null,"
			+ KEY_31 + " string not null,"
			+ KEY_32 + " string not null,"
			+ KEY_33 + " string not null,"
			+ KEY_34 + " string not null"
>>>>>>> origin/master
			
			// Rest  of creation:
			+ ");";
	
	// Context of application who uses us.
	private final Context context;
	
	private DatabaseHelper myDBHelper;
<<<<<<< HEAD
	private SQLiteDatabase db;
=======
	public static SQLiteDatabase db;
>>>>>>> origin/master

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
<<<<<<< HEAD
=======
		
	
>>>>>>> origin/master
		return this;
	}
	
	// Close the database connection.
	public void close() {
		myDBHelper.close();
	}
	
	// Add a new set of values to the database.
<<<<<<< HEAD
	public long insertRow(String name, int studentNum, String favColour) {
=======
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
>>>>>>> origin/master
		/*
		 * CHANGE 3:
		 */		
		// TODO: Update data in the row with new fields.
		// TODO: Also change the function's arguments to be what you need!
		// Create row's data:
		ContentValues initialValues = new ContentValues();
<<<<<<< HEAD
		initialValues.put(KEY_NAME, name);
		initialValues.put(KEY_STUDENTNUM, studentNum);
		initialValues.put(KEY_FAVCOLOUR, favColour);
=======
		initialValues.put(KEY_ID, opport_id);
		initialValues.put(KEY_TITLE, title);
		initialValues.put(KEY_FAVCOLOUR, favColour);
		initialValues.put(KEY_forth, forth);
		initialValues.put(KEY_5, five);
		initialValues.put(KEY_6, six);
		initialValues.put(KEY_7, seven);
		initialValues.put(KEY_8, eight);
		initialValues.put(KEY_9, nine);
		initialValues.put(KEY_10, ten);
		initialValues.put(KEY_11, eleven);
		initialValues.put(KEY_12, twelve);
		initialValues.put(KEY_13, thirteen);
		initialValues.put(KEY_14, fourteen);
		initialValues.put(KEY_15, fifteen);
		initialValues.put(KEY_16, sixteen);
		initialValues.put(KEY_17, seventeen);
		initialValues.put(KEY_18, eightteen);
		initialValues.put(KEY_19, nineteen);
		initialValues.put(KEY_20, twenty);
		initialValues.put(KEY_21, twentyone);
		initialValues.put(KEY_22, twentytwo);
		initialValues.put(KEY_23, twentythree);
		initialValues.put(KEY_24, twentyfour);
		initialValues.put(KEY_25, twentyfive);
		initialValues.put(KEY_26, twentysix);
		initialValues.put(KEY_27, twentyseven);
		initialValues.put(KEY_28, twentyeight);
		initialValues.put(KEY_29, twentynine);
		initialValues.put(KEY_30, thirty);
		initialValues.put(KEY_31, thirtyone);
		initialValues.put(KEY_32, thirtytwo);
		initialValues.put(KEY_33, thirtythree);
		initialValues.put(KEY_34, thirtyfour);
>>>>>>> origin/master
		
		// Insert it into the database.
		return db.insert(DATABASE_TABLE, null, initialValues);
	}
	
	// Delete a row from the database, by rowId (primary key)
	public boolean deleteRow(long rowId) {
<<<<<<< HEAD
		String where = KEY_ROWID + "=" + rowId;
=======
		String where = KEY_ROWID + "=" + rowId;		
>>>>>>> origin/master
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
<<<<<<< HEAD
		newValues.put(KEY_NAME, name);
		newValues.put(KEY_STUDENTNUM, studentNum);
=======
		newValues.put(KEY_ID, name);
		newValues.put(KEY_TITLE, studentNum);
>>>>>>> origin/master
		newValues.put(KEY_FAVCOLOUR, favColour);
		
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
<<<<<<< HEAD

		@Override
		public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion) {
			Log.w(TAG, "Upgrading application's database from version " + oldVersion
=======
		
		
		@Override
		public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion) {
			Log.w(TAG, "UpgradinCOL_forthCOL_forthg application's database from version " + oldVersion
>>>>>>> origin/master
					+ " to " + newVersion + ", which will destroy all old data!");
			
			// Destroy old database:
			_db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			
			// Recreate new database:
			onCreate(_db);
		}
	}
<<<<<<< HEAD
=======


	public Cursor test(String description) {
		// TODO Auto-generated method stub
		Cursor crs=db.rawQuery("SELECT * FROM "
				+ DATABASE_TABLE +" WHERE " 
				+ KEY_forth + " LIKE '%" + description + "%';", null);
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
>>>>>>> origin/master
}
