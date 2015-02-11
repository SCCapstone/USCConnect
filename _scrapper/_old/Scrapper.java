import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/**
 * HTML Scrapper for USC Connect database App 
 * www.sc.edu/uscconnect/participate/
 * Joseph Ammer js.ammer@gmail.com 10/15/2014
 */

public class Scrapper {

	public static void main(String[] args) throws IOException {

		// TODO add variables for user input to modify theURL to search database

		// search database url, can modify for all user inputs
		String theURL = "http://www.sc.edu/uscconnect/participate/opportlist.php?q=&tf=&typ=&selin=&cam=USC+Lancaster&dept=0&col=0";

		// gets source file from url
		String content = null;
		URLConnection connection = null;
		try {
			connection = new URL(theURL).openConnection();
			Scanner scanner = new Scanner(connection.getInputStream());
			scanner.useDelimiter("\\Z");
			content = scanner.next();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// System.out.println(content.substring(topLine));
		// removes top of source file, before actual results
		String EventHTML = content
				.substring(content.indexOf("Student Group") + 57);
		// removes bottom of source file, after actual results
		EventHTML = EventHTML.substring(0, EventHTML.indexOf("vline") - 72);
		// System.out.println(EventHTML+"\n");

		// writes source text to file eventURL.txt
		String URLHTML = EventHTML; // copies source text to find urls
		int eventCount = 0;

		FileWriter outFile = new FileWriter("eventURL.txt", false);
		try {
			PrintWriter printer = new PrintWriter(outFile);
			try {
				printer.write(URLHTML.trim());
			} finally {
				printer.close();
			}
		} finally {
			outFile.close();
		}

		// reads lines of source code for opportunity detail url link number
		// removes everything except right side number
		// (opport_detail.php?oid=154)
		BufferedReader br = new BufferedReader(new FileReader("eventURL.txt"));
		String line = "";
		while ((line = br.readLine()) != null) { // reads source text
			if (line.contains("?oid=")) { // if has opportunity url
				// removes whitespace, tabs, and non-digits
				line = line.replaceAll("\\D", " ").replaceAll("\n", "").trim();
				int index = line.indexOf(" "); // gets first space position, if
												// there are numbers in title
				if (index > 1) { // checks if it has a space or not
					// found space, prints url number only
					System.out.println(line.substring(0, index));
				} else {
					System.out.println(line); // else only number found, prints
												// url
				}
				eventCount++; // counts total found events
			} else
				continue;
		}
		System.out.println("event count = " + eventCount + "\n");

		// FIXME write url line results back to file or to arraylist

		// get opportunity details from source code. strips tags, etc... the
		// table user displayed results remain.

		// removes html tags
		String noHTMLString = EventHTML.replaceAll("\\<.*?>", "");

		// System.out.println(noHTMLString.trim());
		// System.out.println(noHTMLString);

		// writes opportunity table plain text to file, line by line
		outFile = new FileWriter("searchoutput.txt", false);
		try {
			PrintWriter printer = new PrintWriter(outFile);
			try {
				printer.write(noHTMLString.trim());
			} finally {
				printer.close();
			}
		} finally {
			outFile.close();
		}

		// reads opportunity text
		br = new BufferedReader(new FileReader("searchoutput.txt"));
		line = null;
		while ((line = br.readLine()) != null) {
			if (line.contains("-->") == true) {
				// fixes campus text output, removes extra comment
				System.out.println(line.substring(0, line.indexOf("-->"))
						.replaceAll("\n", "").trim());
			} else {
				System.out.println(line.replaceAll("\n", "").trim());
			}
		}

		// FIXME write opportunity text event back to file or to arraylists
	}
}