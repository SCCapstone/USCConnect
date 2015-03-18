package com.example.uscconnect;

import java.io.File;

import android.app.backup.BackupAgentHelper;
import android.app.backup.FileBackupHelper;

public class MyBackupAgent extends BackupAgentHelper
{
   static final String STUDENT_LOG_FILENAME = "data";
   
   static final String LOG_BACKUP_KEY = "myLog";
   
   @Override
   public void onCreate()
   {
	FileBackupHelper helper = new FileBackupHelper(this, STUDENT_LOG_FILENAME);
	addHelper(LOG_BACKUP_KEY, helper);
   }
   
   @Override
   public File getFilesDir()
   {
	   File path = getDatabasePath(STUDENT_LOG_FILENAME);
	   return path.getParentFile();
   }

}
