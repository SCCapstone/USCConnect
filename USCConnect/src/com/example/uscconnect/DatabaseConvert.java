package com.example.uscconnect;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConvert {

	public static void saveUrl(final String filename, final String urlString) throws MalformedURLException, IOException {
		BufferedInputStream in = null;
		FileOutputStream fout = null;
		try {
			in = new BufferedInputStream(new URL(urlString).openStream());
			fout = new FileOutputStream(filename);

			final byte data[] = new byte[1024];
			int count;
			while ((count = in.read(data, 0, 1024)) != -1) {
				fout.write(data, 0, count);
			}
		} finally {
			if (in != null) {
				in.close();
			}
			if (fout != null) {
				fout.close();
			}
		}
	}

	/*public static void main(String[] args) throws MalformedURLException, IOException {
		DatabaseConvert t = new DatabaseConvert();
		String[][] testarray= new String[619][34]; // 619 opportunities || 34 fields in each
		testarray=t.arrayReturn("http://cse.sc.edu/~ammer/uscconnect_opportunities_1414761945.csv");
		// Print off array - TESTING ONLY - checks databaseArray
		for (int i = 0; i < 619 - 1; i++) {
			for (int j = 0; j < 34; j++) {
				// System.out.println(i + " : " + j);
				System.out.println(testarray[i][j]);
			}
		}
	}*/

	public static String[][] arrayReturn(String getURL) throws MalformedURLException, IOException {
		int counter = 0;
		List<String> splitStringList = new ArrayList<String>();
		// gets file from server
		String theURL = getURL;// "http://cse.sc.edu/~ammer/uscconnect_opportunities_1414761945.csv"; // url of file to retrieve
		String theFile = "./uscconnect_opportunities.csv"; // where to save file on device

		saveUrl(theFile, theURL); // method to get file from internet

		try {
			File file = new File(theFile);
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				if (counter == 0) {
					counter++;
				} else {
					stringBuffer.append(line);
					stringBuffer.append("\n");

					boolean insideDoubleQuotes = false;
					StringBuffer field = new StringBuffer();
					for (int i = 0; i < line.length(); i++) {
						if (line.charAt(i) == '"' && !insideDoubleQuotes) {
							insideDoubleQuotes = true;
						} else if (line.charAt(i) == '"' && insideDoubleQuotes) {
							insideDoubleQuotes = false;
							splitStringList.add(field.toString().trim());
							field.setLength(0);
						} else if (line.charAt(i) == ',' && !insideDoubleQuotes) {
							// ignore the comma after double quotes.
							if (field.length() > 0) {
								splitStringList.add(field.toString().trim());
							}
							// clear the field for next word
							field.setLength(0);
						} else {
							field.append(line.charAt(i));
						}
					}
					counter++;
				}
			}
			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Print off array - TESTING ONLY - checks array list
		// for (int i = 0; i < splitStringList.size(); i++) {
		// System.out.println(i % 34 + ":" + splitStringList.get(i));
		// }

		// converts array list into an array of an array
		String[][] databaseArray = new String[counter - 1][34];
		int arrayCounter = 0;
		int oppCounter = 0;
		for (int i = 0; i < (counter - 1); i++) { // each opportunity event
			for (int j = 0; j < 34; j++) { // each data entry in a single opportunity
				// checks enumeration of array of array positions to array list position
				// System.out.println("oppCounter: "+oppCounter +" arrayCounter: "+ (arrayCounter % 34) +" = "+ arrayCounter);
				databaseArray[oppCounter][(arrayCounter % 34)] = splitStringList.get(arrayCounter);
				arrayCounter++;
			}
			oppCounter++;
		}
		// Print off array - TESTING ONLY - checks databaseArray
		// for (int i = 0; i < counter - 1; i++) {
		// for (int j = 0; j < 34; j++) {
		// // System.out.println(i + " : " + j);
		// System.out.println(databaseArray[i][j]);
		// }
		// }

		return (databaseArray);
	}
}