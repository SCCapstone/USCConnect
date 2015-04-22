# Website and Video
http://sccapstone.github.io/USCConnect/

# USCConnect
Android app for [USC Connect](http://www.sc.edu/uscconnect/).

## Using the App

To begin using the app, [download](https://github.com/SCCapstone/USCConnect/blob/master/USCConnect.apk?raw=true) the .apk and install.

Once the app is running, you will be at the main page with four buttons:
* "Search Opportunities"
* "Student Log"
* "GLD Application"
* "About USC Connect"

The "Search Opportunities" should open up to an activity page with a find experiences search box, two drop downboxes for extra criteria, and a search button. After the user presses search, the button will take you to the next page displaying all the results in a clickable list that, when clicked, will display extra details about the events.

The "Student Log" page has an "Add" button that then lets the user input text into a title and body field, and then can click "Save" to save the text. Each entry is clickable to view the text that was previously entered.

The "GLD Application" page opens a browser and direct the user to the school's GLD login page.

The "About USC Connect" page has a clickable youtube video at the top of the screen, followed by a small paragraph of text, with links to USC Connect's facebook, instagram, and twitter account pages.
  
## Special Testing Instructions
1: The app includes a backup feature for the student log; however, the backup feature will not backup the log right away. Either keep the app on your phone for a few hours after creating a log before deleting, 
or you can run apb (Android Debug Bridge) commands from a command window. You also need to make sure that you go to your android phone settings and enable backup and restore features.
   
APB How To:
   	
a. Download the android [sdk](http://developer.android.com/sdk/installing/index.html) on to your pc and navigate to             the platform-tools sub-folder in the sdk folder.

b. Open up a command window in this sub-folder and type in the following (no quotes) after installing the app and               making sure the phone is connected to the pc
	   
	   "apb shell bmgr backup com.example.uscconnect"
	   "apb shell bmgr run"
	   
c. Now uninstall and reinstall the app and your student log should have been backed up and restored.

e. Additional Info:

[here](http://developer.android.com/tools/help/adb.html)

[here](http://developer.android.com/tools/help/bmgr.html)

[here](http://androidcookbook.com/Recipe.seam;jsessionid=C3FDE2B5FC4813FAD435C4D15FC76AAA?recipeId=2968)
 

## Issues

## Update History
