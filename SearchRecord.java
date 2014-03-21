package photographicCollection;

import javax.swing.*;
import java.io.*;

public class SearchRecord {

	public static void searchRecord(Record [] record)  throws IOException { //declare searchRecord method

		final FileReader inputFile = new FileReader("Collection.txt"); 		// read the Collections.txt file
		final BufferedReader inputBuffer = new BufferedReader(inputFile); 	// buffer the read data

		int lines=0; 											 //declare lines variable to hold the number of line existing in the Collections.txt file and initialize it to 0 (zero)
		while (inputBuffer.readLine() != null) { 				 //declare loop and set its condition to iterate until there are existing lines in Collection.txt file
			String title = inputBuffer.readLine(); 				 //declare String variable to hold the read data for title, get the data with readLine and assign it
			int year = Integer.parseInt(inputBuffer.readLine()); //declare String variable to hold the read data for year, get the data with readLine, convert it to inteer and assign it
			String desc = inputBuffer.readLine(); 				 //declare String variable to hold the read data for desc, get the data with readLine and assign it

			record[lines] = new Record(title, year, desc); // insert the title, year and description data in element of the record array on each loop iteration
			
			lines++; 									   // increment the lines value by one each time the loop iterates, so the consequant record array element to be accessed
		}
		inputBuffer.close(); 	// end the reading of Collection.txt file

		searchOptions(record);  // call the serchOptions record and pass the record array to it.
	}
	public static void searchOptions(Record [] record)  throws IOException { // declare serchOptions method

		String search = JOptionPane.showInputDialog ( 	// declare String variable "search" to hold the user input and ask for input
				"Choose one of the search options.\n" + // present the menu options to the user
				"\n- 1 - Search Title" +
				"\n- 2 - Search Year" +
				"\n- 3 - Search Description" +
				"\n- 4 - Back to the Main Menu" +
				"\n- 0 - Quit\n");

		int result=0; 			// declare integer variable "result" to store the search result

		if (search == null) { 	// with if statement check if the user press Cancel button, or windon X (Close) button or Esc on the keyboard
			Main.menu(record); 	// call the menu method from Main class, and pass the record array to it
		}
		if (search != null && !search.equals("") && search.matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+")) {  // chech the valid input with regular expressions to check whether a string contain numeric value or not. 
			int int_search = Integer.parseInt(search); 													//declare integer variable "int_search", convert the search value to integer and assign it to int_search

		if (int_search < 0 || int_search > 4) { 														// declare if statement to check if the user input contains invalid option
			JOptionPane.showMessageDialog(null, "Invalid input!\nPlease, enter valid option."); 		// display invalid input message if the input value does not exist as option
			searchOptions(record); 																		// call back the searchRecord method to enable the user to try again and pass the record array to it
		}
		else if (int_search == 1) 			// catch when the user enters 1 (one)
			result = searchTitle(record); 	// call the searchTitle method and pass the record array to it

		else if (int_search == 2)			// catch when the user enters 2 (two)
			result = searchYear(record); 	//call the searchYear method and pass the record array to it

		else if (int_search == 3) 			// catch when the user enters 3 (three)
			result = searchDesc(record); 	// call the searchDesc method and pass the record array to it

		else if (int_search == 4) 			// catch when the user enters 4 (four)
			Main.menu(record); 				// call the menu method from Main class and pass the record array to it

		else if (int_search == 0) { 		// catch when the user enters 0 (zero)
			JOptionPane.showMessageDialog(null, "Thank you for using Photographic Collection.\nGood Bye."); //display Good Bye message to the user
			System.exit(0); 				// exit the program when the user presses OK in the Good Bye screen
		}
		}
		else {
			JOptionPane.showMessageDialog(null, "Invalid input!\nPlease, enter valid option."); // display invalid input message in all other cases if any
			searchOptions(record); 														// call searchOptions method to enable the user to try again and pass the record array to it
		}

		if (result >= 0) { 												// enter this block when the result variable is set to zero or number above zero
			JOptionPane.showMessageDialog(null, "Search Results:\n" + 	// display message screen to the user, presentin the search results
					"Photo title: " + record[result].title +			// with relevant title,
					"\nYear taken: " + record[result].year +			// year
					"\nDescription: " + record[result].description);	// and description.
		}
		else 																						// when the result variable is below zero
			JOptionPane.showMessageDialog(null,"No photos match your search.\nPlease, try again."); // display no results found message

		subMenu(record); // call the subMenu and pass the record array to it

	}
	public static int searchTitle(Record [] record)  throws IOException { 	// declare searchTitle method

		String search_title = JOptionPane.showInputDialog("Enter title:"); 	// declare String variable "search_title" and ask the user for input

		if (search_title == null) { // check if the user press Cancel button, or windon X (Close) button or Esc on the keyboard
			subMenu(record); 		// call the sub menu
		}
		else if (search_title.equals("")) { 								// check if the input is empty and the user press OK button, or Enter on the keyboard
			JOptionPane.showMessageDialog(null, "Title cannot be empty.");	// display message to the user that search_title cannot be empty
			searchTitle(record); 											// call back the searchTitle method to enable the user to try again and pass the record array to it
		}

		for (int i=0; i<record.length; i++){ 								// declare loop to iterate for the length of the record array
			if (record[i].title.equals(search_title)) 						// using if statement match the existing titles in the record array to the search key "search_title"
				return i; 													// if the title exist return the value of i (element position) to the result variable
		}
		return -1; // if the searched title does not exist return -1 to the result variable
	}
	public static int searchYear(Record [] record)  throws IOException { 	// declare searchYear method

		String search_year = JOptionPane.showInputDialog("Enter year:"); 	// declare String variable "search_year" and ask the user for input

		if (search_year == null) { 	// check if the user press Cancel button, or windon X (Close) button or Esc on the keyboard
			subMenu(record); 		// call the sub menu
		}
		else if (search_year.equals("")) { 									// check if the input is empty and the user press OK button, or Enter on the keyboard
			JOptionPane.showMessageDialog(null, "Year cannot be empty."); 	// display message to the user that search_year cannot be empty
			searchYear(record); 											// call back the searchYear method to enable the user to try again and pass the record array to it
		}
		else if (!search_year.matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+")) { 	// check if the user input for search_year IS NOT numerical
			JOptionPane.showMessageDialog(null, "Year must be numeric."); 	// display message to the user that the input have to be numeric
			searchYear(record); 											// call back the searchYear method to enable the user to try again and pass the record array to it
		}

		if (search_year.matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+")) { 		// check if the user input for search_year IS numerical
			int year = Integer.parseInt(search_year); 						// convert the user input to integer

			for (int i=0; i<record.length; i++){ 							// declare loop to iterate for the length of the record array
				if (record[i].year==(year)) 								// using if statement match the existing years in the record array to the search key "search_year"
					return i; 												// if the year exist return the value of i (element position) to the result variable
			}
		}
		return -1; // if the searched year does not exist return -1 to the result variable
	}
	public static int searchDesc(Record [] record)  throws IOException { 		// declare searchDesc method

		String search_desc = JOptionPane.showInputDialog("Enter description:"); // declare String variable "search_desc" and ask the user for input

		if (search_desc == null) {  // check if the user press Cancel button, or windon X (Close) button or Esc on the keyboard
			subMenu(record); 		// call the sub menu
		}
		else if (search_desc.equals("")) { 										 // check if the input is empty and the user press OK button, or Enter on the keyboard
			JOptionPane.showMessageDialog(null, "Description cannot be empty."); // display message to the user that search_desc cannot be empty
			searchDesc(record); 												 // call back the searchYear method to enable the user to try again and pass the record array to it
		}

			for (int i=0; i<record.length; i++){ 				// declare loop to iterate for the length of the record array
				if (record[i].description.equals(search_desc))  // using if statement match the existing descriptions in the record array to the search key "search_desc"
					return i; 									// if the description exist return the value of i (element position) to the result variable
			}
		return -1; // if the searched description does not exist return -1 to the result variable
	}
	public static void subMenu (Record [] record)  throws IOException  {  //declare subMenu method

		String sub_menu = JOptionPane.showInputDialog ( // declare String variable "sub_menu" to hold the value for user choise. Show menu with options to the user and ask for input.
				"What do you want to do next?" +
						"\n- 1 - Search photo" +
						"\n- 2 - Back to the Main Menu" +
				"\n- 0 - Quit\n");

		if (sub_menu == null) { // check if the user press Cancel button, or windon X (Close) button or Esc on the keyboard
			Main.menu(record); 	// call menu method from Main class and pass the record array to it
		}

		if (sub_menu != null && !sub_menu.equals("") && sub_menu.matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+")) { // chech the valid input with regular expressions to check whether "sub_menu" string contain numeric value or not. 
			int int_menu = Integer.parseInt(sub_menu); 														 // declare integer variable "int_menu" to hold the valid menu option, convert the String sub_menu to integer and assign it to int_menu

			if (int_menu < 0 || int_menu > 2) { 											// declare if statement to check if the user input contains invalid option
				JOptionPane.showMessageDialog(null, "Invalid input!\nPlease, enter valid option."); // display message screen to the user about invalid input
				subMenu(record); 															//call the sub menu to enable the user to try again and pass the record array to it
			}
			else if (sub_menu.equals("1")) { 												// declare if statement to catch the input when the user inputs 1 (one)
				searchOptions(record); 														// call the searchOptions method and pass the record array to it
			}
			else if (sub_menu.equals("2")) { 												// declare if statement to catch the input when the user inputs 2 (two)
				Main.menu(record); 															// call the menu method from Main class and pass the record array to it
			}
			else if (sub_menu.equals("0")) { 												// declare if statement to catch the input when the user inputs 0 (zero)
				JOptionPane.showMessageDialog(null, "Thank you for using Photographic Collection.\nGood Bye."); // display Goodbye message to the user
				System.exit(0); 															// exit the program when the user click OK in the Goodbye message screen
			}
		}

		else
		JOptionPane.showMessageDialog(null, "Invalid input!\nPlease, enter valid option."); // display message screen to the user about invalid input in all other cases
		subMenu(record); 															// call the sub menu to enable the user to try again and pass the record array to it
	}
}
