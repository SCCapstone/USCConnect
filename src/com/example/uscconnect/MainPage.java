package com.example.uscconnect;


import java.io.IOException;
import java.net.MalformedURLException;

import com.parse.Parse;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainPage extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

		 
        setContentView(R.layout.activity_main_page);
        
        Button Search = (Button)findViewById(R.id.button1);
        Search.setOnClickListener(new View.OnClickListener() {
        	@Override
        	public void onClick(View view) {
        		Intent myIntent = new Intent(view.getContext(), SearchPage.class);
        		//ProgressDialog dialog = ProgressDialog.show(MainPage.this, "", 
                  //      "Loading. Please wait...", true);
        		startActivityForResult(myIntent, 0);
        		
        	}
        });
        
        Button Log = (Button)findViewById(R.id.button2);
        Log.setOnClickListener(new View.OnClickListener() {
            @Override
			public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), StudentLog.class);
                startActivityForResult(myIntent, 0);
            }
        });
        
        Button About = (Button)findViewById(R.id.button3);
        About.setOnClickListener(new View.OnClickListener() {
            @Override
			public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), AboutPage.class);
                startActivityForResult(myIntent, 0);
            }
        });
        
        Button Application = (Button)findViewById(R.id.button4);
        Application.setOnClickListener(new View.OnClickListener() {
            @Override
			public void onClick(View view) {
        		startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.sc.edu/uscconnect/gldapplication")));

            }
        });
      //  Parse.initialize(this, "uv0wiLDntmTYohfjvjJrICI6nSae1hFc20GPf9JJ", "yvbChURyeihU8wCB6dyLWwZjSwkU9V0l11oQqeA7");
        
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_page, menu);
        return true;
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
        return super.onOptionsItemSelected(item);
    }
}
