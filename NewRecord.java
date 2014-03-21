package photographicCollection;

import javax.swing.*;
import java.io.*;

public class NewRecord {

	public static void newRecord(Record [] record) throws IOException { 		 // declare newRecord method

		String new_title = JOptionPane.showInputDialog("Enter new title:"); 	 // declare String variable "new_title" to hold the title value for the new record and ask the user for input
			if (new_title == null) { 											 // check if the user press Cancel button, or windon X (Close) button or Esc on the keyboard
				subMenu(record); 												 // call the sub menu
			}
			else if (new_title.equals("")) { 									 // check the input is empty and the user press OK button, or Enter on the keyboard
				JOptionPane.showMessageDialog(null, "Title cannot be empty."); 	 // display message screen to the user about invalid input
				newRecord(record); 												 // call back the newRecord method enabling the user to try again and pass the record array as expected data
			}
		String new_year = JOptionPane.showInputDialog("Enter new year:"); 		 // declare String variable "new_year" to hold the year value for the new record and ask the user for input
			if (new_year == null) { 											 // check if the user press Cancel button, or windon X (Close) button or Esc on the keyboard
				subMenu(record); 												 // call the sub menu
			}
			else if (new_year.equals("")) { 									 // check the input is empty and the user press OK button, or Enter on the keyboard
				JOptionPane.showMessageDialog(null, "Year cannot be empty."); 	 // display message screen to the user about invalid input
				newRecord(record); 												 // call back the newRecord method enabling the user to try again and pass the record array as expected data
			}
			else if (!new_year.matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+")) { 		 // chech the valid input with regular expressions to check whether "new_year" string contain numeric value or not. 
				JOptionPane.showMessageDialog(null, "Invalid input!\nYear must be numeric.\nSorry, the new record entry was aborted, please start again."); // display message screen to the user about invalid input
				newRecord(record); 												 // call back the newRecord method enabling the user to try again and pass the record array as expected data
			}
			int int_year = Integer.parseInt(new_year); 							 // declare integer variable "int_year", convert and assign to it the new_year String value
			
		String new_desc = JOptionPane.showInputDialog("Enter new description:"); // declare String variable "new_desc" to hold the description value for the new record and ask the user for input
			if (new_desc == null) { 											 // check if the user press Cancel button, or windon X (Close) button or Esc on the keyboard
				subMenu(record); 												 // call the sub menu
			}
			else if (new_desc.equals("")) { 										 // check if the input is empty and the user press OK button, or Enter on the keyboard
				JOptionPane.showMessageDialog(null, "Description cannot be empty."); // display message screen to the user about invalid input
				newRecord(record); 													 // call back the newRecord method enabling the user to try again and pass the record array as expected data
			}

		int i = 0; 												// declate integer variable "i" and initialize it to 0 (zero)
		record[i] = new Record (new_title, int_year, new_desc); // assign the record array element on position i the values for title, year and description

		final FileWriter outputFile = new FileWriter("Collection.txt", true); 	// initialize File Writer and set it to look for Collection.txt file to to be able to write data into it.
		final BufferedWriter outputBuffer = new BufferedWriter(outputFile); 	// initilize Buffered Writter to hold the data that is to be written in the file
		final PrintWriter printstream = new PrintWriter(outputBuffer); 			// initialize Print Writer to insert the actual data to the file

		printstream.println("\n" + record[i].title + "\n" + record[i].year + "\n" + record[i].description); // write the title, year and description data into the Collection.txt file

		printstream.close(); // close the Print Writer

		subMenu(record); 	 //call the sub menu and pass the record array to it
	}

	public static void subMenu (Record [] record)  throws IOException  { //declare subMenu method

		String sub_menu = JOptionPane.showInputDialog ( 				 // declare String variable "sub_menu" to hold the value for user choise. Show menu with options to the user and ask for input.
				"What do you want to do next?" +
				"\n- 1 - Add photo" +
				"\n- 2 - Back to the Main Menu" +
				"\n- 0 - Quit\n");

		if (sub_menu == null) { // check if the user press Cancel button, or windon X (Close) button or Esc on the keyboard
			Main.menu(record);  // call menu method from Main class and pass the record array to it
		}

		if (sub_menu != null && !sub_menu.equals("") && sub_menu.matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+")) { // chech the valid input with regular expressions to check whether "sub_menu" string contain numeric value or not. 
			int int_menu = Integer.parseInt(sub_menu); 														 // declare integer variable "int_menu" to hold the valid menu option, convert the String sub_menu to integer and assign it to int_menu

			if (int_menu < 0 || int_menu > 2) { 											// declare if statement to check if the user input contains invalid option
				JOptionPane.showMessageDialog(null, "Invalid input!\nPlease, enter valid option."); // display message screen to the user about invalid input
				subMenu(record); 															//call the sub menu to enable the user to try again and pass the record array to it
			}
			else if (sub_menu.equals("1")) { 												// declare if statement to catch the input when the user inputs 1 (one)
				newRecord(record); 															// call the newRecord method and pass the record array to it
			}
			else if (sub_menu.equals("2")) { 												// declare if statement to catch the input when the user inputs 2 (two)
				Main.readExistingData(); 													// call the readExistingData method from Main class
			}
			else if (sub_menu.equals("0")) { 												// declare if statement to catch the input when the user inputs 0 (zero)
				JOptionPane.showMessageDialog(null, "Thank you for using Photographic Collection.\nGood Bye."); // display Goodbye message to the user
				System.exit(0); 															// exit the program when the user click OK in the Goodbye message screen
			}
		}

		else
			JOptionPane.showMessageDialog(null, "Invalid input!\nPlease, enter valid option."); // display message screen to the user about invalid input in all other cases
		subMenu(record); // call the sub menu to enable the user to try again and pass the record array to it
	}
}
