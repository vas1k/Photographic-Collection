package photographicCollection;

import javax.swing.*;
import java.io.*;

public class ListRecords {

	public static void listRecords(Record [] record)  throws IOException { 	//declare listRecords method

		final FileReader inputFile = new FileReader("Collection.txt"); 		// read the Collections.txt file
		final BufferedReader inputBuffer = new BufferedReader(inputFile); 	// buffer the read data

		int lines=0; //declare lines variable to hold the number of line existing in the Collections.txt file and initialize it to 0 (zero)
		while (inputBuffer.readLine() != null) { 					// declare loop and set its condition to iterate until there are existing lines in Collection.txt file
			String title = inputBuffer.readLine(); 					//declare String variable to hold the read data for title, get the data with readLine and assign it
			int year = Integer.parseInt(inputBuffer.readLine()); 	//declare String variable to hold the read data for year, get the data with readLine, convert it to inteer and assign it
			String desc = inputBuffer.readLine(); 					//declare String variable to hold the read data for desc, get the data with readLine and assign it

			record[lines] = new Record(title, year, desc); 			// insert the title, year and description data in element of the record array on each loop iteration
			lines++; 												// increment the lines value by one each time the loop iterates, so the consequant record array element to be accessed
		}

		inputBuffer.close(); 	// end the reading of Collection.txt file

		printAll(record); 		// call printAll method and pass the record array to it
	}
	public static void printAll(Record [] record)  throws IOException { // declare pritAll method

		for(int i=0; i<record.length; i++) { //declare loop to iterate for the length of the record array -1 ( to skip the provisional record 0 0 0)
			System.out.println(record[i].title + " " + record[i].year + " " + record[i].description); // print each record array element in the console
			JOptionPane.showMessageDialog(null, record[i].title + " " + record[i].year + " " + record[i].description); // print each record array element in dialog screen
		}
	
		subMenu(record); // call the subMenu method and pass the record array to it.

	}

	public static void subMenu (Record [] record)  throws IOException  {

		String sub_menu = JOptionPane.showInputDialog ( // declare String variable "sub_menu" to hold the value for user choise. Show menu with options to the user and ask for input.
				"What do you want to do next?" +
						"\n- 1 - List all records" +
						"\n- 2 - Back to the Main Menu" +
				"\n- 0 - Quit\n");

		if (sub_menu == null) { // check if the user press Cancel button, or windon X (Close) button or Esc on the keyboard
			Main.menu(record); 	// call menu method from Main class and pass the record array to it
		}

		if (sub_menu != null && !sub_menu.equals("") && sub_menu.matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+")) { // chech the valid input with regular expressions to check whether "sub_menu" string contain numeric value or not. 
			int int_menu = Integer.parseInt(sub_menu); 														 // declare integer variable "int_menu" to hold the valid menu option, convert the String sub_menu to integer and assign it to int_menu

			if (int_menu < 0 || int_menu > 2) { 															 // declare if statement to check if the user input contains invalid option
				JOptionPane.showMessageDialog(null, "Invalid input!\nPlease, enter valid option."); 				 // display message screen to the user about invalid input
				subMenu(record); 																			 //call the sub menu to enable the user to try again and pass the record array to it
			}
			else if (sub_menu.equals("1")) { // declare if statement to catch the input when the user inputs 1 (one)
				printAll(record); 			 // call the printAll method and pass the record array to it
			}
			else if (sub_menu.equals("2")) { // declare if statement to catch the input when the user inputs 2 (two)
				Main.menu(record); 			 // call the menu method from Main class and pass the pass the record array to it
			}
			else if (sub_menu.equals("0")) { // declare if statement to catch the input when the user inputs 0 (zero)
				JOptionPane.showMessageDialog(null, "Thank you for using Photographic Collection.\nGood Bye."); // display Goodbye message to the user
				System.exit(0); 			 // exit the program when the user click OK in the Goodbye message screen
			}
		}

		else
		JOptionPane.showMessageDialog(null, "Invalid input!\nPlease, enter valid option."); // display message screen to the user about invalid input in all other cases
		subMenu(record); 															// call the sub menu to enable the user to try again and pass the record array to it
	}
}
