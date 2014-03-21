package photographicCollection;

import javax.swing.*;
import java.io.*;

public class EditRecord {
	public static void editRecord(Record [] record)  throws IOException { 	//declare editRecord method

		final FileReader inputFile = new FileReader("Collection.txt"); 		// read the Collections.txt file
		final BufferedReader inputBuffer = new BufferedReader(inputFile); 	// buffer the read data

		int lines=0; //declare lines variable to hold the number of line existing in the Collections.txt file and initialize it to 0 (zero)
		while (inputBuffer.readLine() != null) { 					// declare loop and set its condition to iterate until there are existing lines in Collection.txt file
			String title = inputBuffer.readLine(); 					//declare String variable to hold the read data for title, get the data with readLine and assign it
			int year = Integer.parseInt(inputBuffer.readLine()); 	//declare String variable to hold the read data for year, get the data with readLine, convert it to inteer and assign it
			String desc = inputBuffer.readLine(); 					//declare String variable to hold the read data for desc, get the data with readLine and assign it

			record[lines] = new Record(title, year, desc); // insert the title, year and description data in element of the record array on each loop iteration
			
			lines++; // increment the lines value by one each time the loop iterates, so the consequant record array element to be accessed
		}

		inputBuffer.close(); // end the reading of Collection.txt file

		editOptions(record); //call editOptions method and pass the record array to it
	}
	public static void editOptions(Record [] record)  throws IOException { //declare editOptions method

		String edit = JOptionPane.showInputDialog ( //declare String "edit" to hold the user choise, present the available options and ask the user for input
				"Choose one of the edit options.\n" +
				"\n- 1 - Edit Title" +
				"\n- 2 - Edit Year" +
				"\n- 3 - Edit Description" +
				"\n- 4 - Go back to the Main Menu" +
				"\n- 0 - Quit\n");

		int title_result, year_result, desc_result; 	// declare integer variables for title, year and description
		title_result = year_result = desc_result = 0; 	// initialise the title, year and description integer to 0 (zero)

		if (edit == null) { 	// with if statement check if the user press Cancel button, or windon X (Close) button or Esc on the keyboard
			Main.menu(record); 	// call the menu method from Main class, and pass the record array to it
		}

		if (edit != null && !edit.equals("") && edit.matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+")) { // chech the valid input with regular expressions to check whether a string contain numeric value or not. 
			int int_edit = Integer.parseInt(edit); 	//declare integer variable "int_search", convert the search value to integer and assign it to int_search

			while (int_edit < 0 || int_edit > 4) { 	// declare if statement to check if the user input contains invalid option
				JOptionPane.showMessageDialog(null, "Invalid input!\nPlease, enter valid edit option."); // display invalid input message if the input value does not exist as option
				editOptions(record); 				// call back the editOptions method to enable the user to try again and pass the record array to it
			}

			if (int_edit == 1) {								 // when the user enter option 1
				title_result = SearchRecord.searchTitle(record); // call the searchTitle method from SearchRecord class and assign the return search result to the title_result variable
				editTitle(record, title_result);				 // call the editTitle method and pass the record array and the title_result to it
			}
			else if (int_edit == 2) {							// when the user enter option 2
				year_result = SearchRecord.searchYear(record);	// call the searchYear method from SearchRecord class and assign the return search result to the year_result variable
				editYear(record, year_result);					// call the editTitle method and pass the record array and the year_result to it
			}
			else if (int_edit == 3) {							// when the user enter option 3
				desc_result = SearchRecord.searchDesc(record);	// call the searchDesc method from SearchRecord class and assign the return search result to the desc_result variable
				editDesc(record, desc_result);					// call the editTitle method and pass the record array and the desc_result to it
			}
			else if (int_edit == 4) {	// when the user enter option 4
				Main.menu(record);		// call the menu method from Main class and pass the record array to it
			}
			else if (int_edit == 0) { 																			// when the user enter option 0
				JOptionPane.showMessageDialog(null, "Thank you for using Photographic Collection.\nGood Bye."); // display Good bye message to the user
				System.exit(0);																					// exit the program when the user click OK in the Good bye screen
			}
		}
		else // if any other case display message about invalid input
			JOptionPane.showMessageDialog(null, "Invalid input!\nPlease, enter valid option.");
			editOptions(record); // call the editOptions method to enable the user to try again and pass the record array ot it
	}
	public static void editTitle (Record [] record, int title_result) throws IOException { 					// declare editTitle method
		while (title_result == -1) { 																		// declare loop to check if the title return result from the search is -1 (no records found)
			String no_found = JOptionPane.showInputDialog("No photos with the given Title were found!" +	// declare String variable "no_found" to hold the user choise
					"\nOnly existing Title may be updated." + "\n" +										// present to the user the available options and ask for input
					"\n- 1 - Try again" +
					"\n- 2 - Back to the Edit options" +
					"\n- 3 - Back to the Main Menu" +
					"\n- 0 - Quit\n");

			if (no_found.equals("1")) {							 // when teh user enter 1 (one)
				title_result = SearchRecord.searchTitle(record); // call the searchTitle method from SearchRecord class pass the record array to it and assign the return value to the title_result variable
			}
			else if (no_found.equals("2")) { // when the user enter 2 (two)
				editOptions(record); 		 // call the editOptions and pass the record array to it
			}
			else if (no_found.equals("3")) { // when the user enter 3 (three)
				Main.menu(record);			 // call the menu method from Main class and pass the record array to it
			}
			else if (no_found.equals("0")) { // when the user enter 0 (zero)
				JOptionPane.showMessageDialog(null, "Thank you for using Photographic Collection.\nGood Bye."); // display Good bye message
				System.exit(0); 			 // exit the program when the user click OK in the Good Bye message screen
			}
		}

		if (title_result >= 0) { 															// when the title_result variable hold value equal or biggfer than zero
			String new_title = JOptionPane.showInputDialog( 								// declare String variable "new_title" to hold the new title
				"You are about to update the Title data \nfor the following file:" + "\n" + // present the user all current information about the found record
				"\nTitle: " + record[title_result].title + "\n" +							// as record title,
				"Year: " + record[title_result].year + "\n" +								// record year
				"Description: " + record[title_result].description + "\n" +					// and record description
				"\nPlease, enter new Title:");												// and then ask for input of the new title
			
			
			if (new_title == null) { 													// check if the user press Cancel button, or windon X (Close) button or Esc on the keyboard
				JOptionPane.showMessageDialog(null, "The Title change is cancelled." + 	// then show cancellation message
				"\nCurrent Photo data remains as follows: " + "\n" +				   	// saying that the current record remain as it is
				"\nTitle: " + record[title_result].title + "\n" +					   	// with its current title,
				"Year: " + record[title_result].year + "\n" +						   	// its current year
				"Description: " + record[title_result].description);				   	// and its current description
			}
			else if (new_title.equals("")) { 										  	// check the input is empty and the user press OK button, or Enter on the keyboard
				JOptionPane.showMessageDialog(null, "Title cannot be empty."); 			// display message to the user that he/she has to provide title in order to find and edit it
				editTitle(record, title_result); 										// then call the editTitle method again to enable the user to try again and pass the record array and the title_result varialble to it
			}
			else if (new_title != null && !new_title.equals("")) { // check that the user entered a valid input (alpha-numrical value)
			
				int result = newTitle(record, title_result, new_title); // declare integer variable "result" to hols the returned position of the modified array element, 
																		// then call the newTitle method and pass the record array, title_result position and the new_title value to it.

				JOptionPane.showMessageDialog(null, "The photo Title was successfully updated to : " + record[result].title); // display message to the user showing that the new title is successfully assigned to the modified record
			}
			
			subMenu(record); // call the subMenu method
		}
	}
	public static void editYear (Record [] record, int year_result) throws IOException { 				// declare the editYear method
		while (year_result == -1) { 																	// declare loop to check if the year return result from the search is -1 (no records found)
			String no_found = JOptionPane.showInputDialog("No photos with the given Year were found!" + // declare String variable "no_found" to hold the user choise
					"\nOnly existing Year may be updated." + "\n" +										// present to the user the available options and ask for input
					"\n- 1 - Try again" +
					"\n- 2 - Back to the Edit options" +
					"\n- 3 - Back to the Main Menu" +
					"\n- 0 - Quit\n");

			if (no_found.equals("1")) {							// when the user enter 1 (one)
				year_result = SearchRecord.searchYear(record);	// call the searchYear method from SearchRecord class pass the record array to it and assign the return value to the year_result variable
			}
			else if (no_found.equals("2")) {	// when the user enter 2 (two)
				editOptions(record);			// call the editOptions and pass the record array to it
			}
			else if (no_found.equals("3")) {	// when the user enter 3 (three)
				Main.menu(record);				// call the menu method from Main class and pass the record array to it
			}
			else if (no_found.equals("0")) { 	// when the user enter 0 (zero)
				JOptionPane.showMessageDialog(null, "Thank you for using Photographic Collection.\nGood Bye."); // display Good bye message
				System.exit(0); 				// exit the program when the user click OK in the Good Bye message screen
			}
		}

		if (year_result >= 0) { 															// when the year_result variable hold value equal or bigger than zero
			String new_year = JOptionPane.showInputDialog(									// declare String variable "new_year" to hold the new year
					"You are about to update the Year data \nfor the following file:\n" +   // present the user all current information about the found record
					"\nTitle: " + record[year_result].title + "\n" +						// as record title,
					"Year: " + record[year_result].year + "\n" +							// record year
					"Description: " + record[year_result].description + "\n" +				// and record description
					"\nPlease, enter new Year:");											// and then ask for input of the new year
			
			
			if (new_year == null) {														// check if the user press Cancel button, or windon X (Close) button or Esc on the keyboard
				JOptionPane.showMessageDialog(null, "The Year change is cancelled." + 	// then show cancellation message
						"\nCurrent Photo data remains as follows: " + "\n" +			// saying that the current record remain as it is
						"\nTitle: " + record[year_result].title + "\n" +				// with its current title,
						"Year: " + record[year_result].year + "\n" +					// its current year
						"Description: " + record[year_result].description);				// and its current description
			}
			else if (new_year.equals("")) { 											// check if the input is empty and the user press OK button, or Enter on the keyboard
				JOptionPane.showMessageDialog(null, "Year cannot be empty.");			// display message to the user that he/she has to provide year in order to find and edit it
				editYear(record, year_result); 											// then call the editYear method again to enable the user to try again and pass the record array and the year_result varialble to it
			}
			else if (!new_year.matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+")) { 				// with regular expressions check whether a string contain numeric value or not.
				JOptionPane.showMessageDialog(null, "Year must be numeric."); 			// display message that the new value for year has to be numeric.
				editYear(record, year_result); 											// call the editYear method to enable the user to try again and pass the record array and the year_result variable to it.
			}
			else if (new_year != null && !new_year.equals("")) { 						// check if the user entered new year value
				int int_year = Integer.parseInt(new_year); 								// declare integer variable "int_year", convert the numerical String value of new_year to integer and assign it to int_year
				int result = newYear(record, year_result, int_year); 					// declare integer variable "result" to hold the returned position of the modified array element, 
																	 					// then call the newYear method and pass the record array, year_result position and the int_year value to it.
																	 
				JOptionPane.showMessageDialog(null, "The photo Year was successfully updated to : " + record[result].year); // display message to the user showing that the new year is successfully assigned to the modified record
			}
			subMenu(record); // call the subMenu method
		}
	}
	public static void editDesc (Record [] record, int desc_result) throws IOException {					   //declare editDesc method	
		while (desc_result == -1) { 																		   // declare loop to check if the desc return result from the search is -1 (no records found)
			String no_found = JOptionPane.showInputDialog("No photos with the given Description were found!" + // declare String variable "no_found" to hold the user choise
					"\nOnly existing Description may be updated." + "\n" +									   // present to the user the available options and ask for input
					"\n- 1 - Try again" +
					"\n- 2 - Back to the Edit options" +
					"\n- 3 - Back to the Main Menu" +
					"\n- 0 - Quit\n");

			if (no_found.equals("1")) {							// when the user enter 1 (one)
				desc_result = SearchRecord.searchDesc(record);	// call the searchDesc method from SearchRecord class pass the record array to it and assign the return value to the year_result variable
			}
			else if (no_found.equals("2")) { // when the user enter 2 (two)
				editOptions(record);		 // call the editOptions and pass the record array to it
			}
			else if (no_found.equals("3")) { // when the user enter 3 (three)
				Main.menu(record);			 // call the menu method from Main class and pass the record array to it
			}
			else if (no_found.equals("0")) { 																	// when the user enter 0 (zero)
				JOptionPane.showMessageDialog(null, "Thank you for using Photographic Collection.\nGood Bye."); // display Good bye message
				System.exit(0); 																				// exit the program when the user click OK in the Good Bye message screen
			}
		}

		if (desc_result >= 0) {																 // when the desc_result variable hold value equal or bigger than zero
			String new_desc = JOptionPane.showInputDialog( 									 // declare String variable "new_desc" to hold the new desc
				"You are about to update the Description data \nfor the following file:\n" + // present the user all current information about the found record
				"\nTitle: " + record[desc_result].title + "\n" +							 // as record title,
				"Year: " + record[desc_result].year + "\n" +								 // record year
				"Description: " + record[desc_result].description + "\n" +					 // and record description
				"\nPlease, enter new Description:");										 // and then ask for input of the new description
				
			if 	(new_desc == null) { 														 // check if the user press Cancel button, or windon X (Close) button or Esc on the keyboard
				JOptionPane.showMessageDialog(null, "The Description change is cancelled." + // then show cancellation message
				"\nCurrent Photo data remains as follows: " + "\n" +						 // saying that the current record remain as it is
				"\nTitle: " + record[desc_result].title + "\n" +							 // with its current title,
				"Year: " + record[desc_result].year + "\n" +								 // its current year
				"Description: " + record[desc_result].description);							 // and its current description
			}
			else if (new_desc.equals("")) {													 // check the input is empty and the user press OK button, or Enter on the keyboard
				JOptionPane.showMessageDialog(null, "Description cannot be empty.");		 // display message to the user that he/she has to provide description in order to find and edit it
				editTitle(record, desc_result);												 // then call the editDesc method again to enable the user to try again and pass the record array and the desc_result varialble to it
			}
			else if (new_desc != null && !new_desc.equals("")) { 							 // check that the user entered a valid input (alpha-numrical value)
				int result = newDesc(record, desc_result, new_desc); 						 // declare integer variable "result" to hols the returned position of the modified array element, 
																	 						 // then call the newDesc method and pass the record array, desc_result and the new_desc value to it.
				JOptionPane.showMessageDialog(null, "The photo Description was successfully updated to : " + record[result].description); // display message to the user showing that the new description is successfully assigned to the modified record
			}
			subMenu(record); // call the subMenu method
		}
	}
	public static int newTitle(Record [] record, int title_result, String new_title)  throws IOException { 		//declare newTitle method

		record[title_result].title = new_title; // assign the new_title value to the relevant title position in the record array

		final FileWriter outputFile = new FileWriter("Collection.txt"); 	// initialize File Writer and set it overwrite Collection.txt file.
		final BufferedWriter outputBuffer = new BufferedWriter(outputFile); // initilize Buffered Writter to hold the data that is to be written in the file
		final PrintWriter printstream = new PrintWriter(outputBuffer); 		// initialize Print Writer to insert the actual data to the file

		for (int i=0; i<record.length; i++) { 																	// declare a loop to iterate for the length of the record array
			printstream.println("\n" + record[i].title + "\n" + record[i].year + "\n" + record[i].description); // on each loop iteration record the current record array position to the Collection.txt file
		}

		printstream.close(); // close the Print Writer

		return title_result; // return the title_result value to the editTitle method.

	}
	public static int newYear(Record [] record, int year_result, int int_year)  throws IOException { //declare newYear method

		record[year_result].year = int_year; 								// assign the int_year value to the relevant year position in the record array

		final FileWriter outputFile = new FileWriter("Collection.txt"); 	// initialize File Writer and set it overwrite Collection.txt file.
		final BufferedWriter outputBuffer = new BufferedWriter(outputFile); // initilize Buffered Writter to hold the data that is to be written in the file
		final PrintWriter printstream = new PrintWriter(outputBuffer); 		// initialize Print Writer to insert the actual data to the file

		for (int i=0; i<record.length; i++) { 								// declare a loop to iterate for the length of the record array
			printstream.println("\n" + record[i].title + "\n" + record[i].year + "\n" + record[i].description); // on each loop iteration record the current record array position to the Collection.txt file
		}

		printstream.close(); // close the Print Writer

		return year_result; // return the year_result value to the editYear method.
	}
	public static int newDesc(Record [] record, int desc_result, String new_desc)  throws IOException { //declare newdesc method

		record[desc_result].description = new_desc; 						// assign the int_desc value to the relevant description position in the record array

		final FileWriter outputFile = new FileWriter("Collection.txt"); 	// initialize File Writer and set it overwrite Collection.txt file.
		final BufferedWriter outputBuffer = new BufferedWriter(outputFile); // initilize Buffered Writter to hold the data that is to be written in the file
		final PrintWriter printstream = new PrintWriter(outputBuffer); 		// initialize Print Writer to insert the actual data to the file

		for (int i=0; i<record.length; i++) { // declare a loop to iterate for the length of the record array
			printstream.println("\n" + record[i].title + "\n" + record[i].year + "\n" + record[i].description); // on each loop iteration record the current record array position to the Collection.txt file
		}

		printstream.close(); // close the Print Writer

		return desc_result;  // return the desc_result value to the editYear method.

	}
	public static void subMenu (Record [] record)  throws IOException  { // declare subMenu method

		String sub_menu = JOptionPane.showInputDialog ( 				 // declare String variable "sub_menu" to hold the value for user choise. Show menu with options to the user and ask for input.
				"What do you want to do next?" +
				"\n- 1 - Edit another photo" +
				"\n- 2 - Back to the Main Menu" +
				"\n- 0 - Quit\n");
		
		if (sub_menu == null) { // check if the user press Cancel button, or windon X (Close) button or Esc on the keyboard
			Main.menu(record);	// call menu method from Main class and pass the record array to it
		}

		if (sub_menu != null && !sub_menu.equals("") && sub_menu.matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+")) { // chech the valid input with regular expressions to check whether "sub_menu" string contain numeric value or not. 
			int int_menu = Integer.parseInt(sub_menu); 														 // declare integer variable "int_menu" to hold the valid menu option, convert the String sub_menu to integer and assign it to int_menu

			while (int_menu < 0 || int_menu > 2) { 											// declare if statement to check if the user input contains invalid option
				JOptionPane.showMessageDialog(null, "Invalid input!\nPlease, enter valid option."); // display message screen to the user about invalid input
				subMenu(record); 															//call the sub menu to enable the user to try again and pass the record array to it
			}
			if (sub_menu.equals("1")) { // declare if statement to catch the input when the user inputs 1 (one)
				editOptions(record);	// call the searchOptions method and pass the record array to it
			}
			else if (sub_menu.equals("2")) { // declare if statement to catch the input when the user inputs 2 (two)
				Main.menu(record);			 // call the menu method from Main class and pass the record array to it
			}
			else if (sub_menu.equals("0")) { 																	// declare if statement to catch the input when the user inputs 0 (zero)
				JOptionPane.showMessageDialog(null, "Thank you for using Photographic Collection.\nGood Bye."); // display Goodbye message to the user
				System.exit(0); 																				// exit the program when the user click OK in the Goodbye message screen
			}
		}

		else
			JOptionPane.showMessageDialog(null, "Invalid input!\nPlease, enter valid option."); // display message screen to the user about invalid input in all other cases
			subMenu(record); 															// call the sub menu to enable the user to try again and pass the record array to it
	}
}
