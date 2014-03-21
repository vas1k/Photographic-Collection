package photographicCollection;

import java.io.*;

import javax.swing.JOptionPane;

public class ExportRecords {

	public static void exportRecords(Record [] record)  throws IOException {  //declare exportRecords method

		final FileReader inputFile = new FileReader("Collection.txt"); 		  // read the Collections.txt file
		final BufferedReader inputBuffer = new BufferedReader(inputFile); 	  // buffer the read data
		
		int lines=0; 								//declare lines variable to hold the number of line existing in the Collections.txt file and initialize it to 0 (zero)
		while (inputBuffer.readLine() != null) { 	// declare loop and set its condition to iterate until there are existing lines in Collection.txt file
			String title = inputBuffer.readLine();  //declare String variable to hold the read data for title, get the data with readLine and assign it
			int year = Integer.parseInt(inputBuffer.readLine()); //declare String variable to hold the read data for year, get the data with readLine, convert it to inteer and assign it
			String desc = inputBuffer.readLine();   //declare String variable to hold the read data for desc, get the data with readLine and assign it
	
			record[lines] = new Record(title, year, desc); // insert the title, year and description data in element of the record array on each loop iteration
			
			lines++; // increment the lines value by one each time the loop iterates, so the consequant record array element to be accessed
		}
		
		inputBuffer.close(); // end the reading of Collection.txt file
		
		exportFile(record);  // call the exportFile method and pass the record array to it
	}

	public static void exportFile (Record [] record)  throws IOException  { // declare exportFile method
		
		final FileWriter outputFile = new FileWriter("C:/Users/Public/Documents/Collection_Export.txt"); // initialize File Writer and set it to create Collection_Export.txt file C:/Users/Public/Documents/ directory, so it will be available for writing data into it.
		final BufferedWriter outputBuffer = new BufferedWriter(outputFile); // initilize Buffered Writter to hold the data that is to be written in the file
		final PrintWriter printstream = new PrintWriter(outputBuffer); 		// initialize Print Writer to insert the actual data to the file

		for (int i=0; i<record.length; i++) { // declare loop which will iterate for the length of the record array -1 (to skip the provisional record 0 0 0)
			printstream.println("\n" + record[i].title + "\n" + record[i].year + "\n" + record[i].description); // write the title, year and description data into the Collection.txt file on each loop iteration
		}

		printstream.close(); // close the Print Writer
		
		JOptionPane.showMessageDialog(null, "The photograph collection data is successfully exported to directory:" + // present message to the user
											"\nC:/Users/Public/Documents/, with following name:"+"" +				  // saying that the export is successful
											"\nCollection_Export.txt");												  // and the file is exported to the specified directory
																													  // with filename Collection_Export.txt

		subMenu(record); // call the subMenu and pass the record array to it
	}

	public static void subMenu (Record [] record)  throws IOException  {

		String sub_menu = JOptionPane.showInputDialog (	// declare String variable "sub_menu" to hold the value for user choise. Show menu with options to the user and ask for input.
				"What do you want to do next?" +
				"\n- 1 - View latest export" +
				"\n- 2 - Make another export" +
				"\n- 3 - Back to the Main Menu" +
				"\n- 0 - Quit\n");

		if (sub_menu == null) { // check if the user press Cancel button, or windon X (Close) button or Esc on the keyboard
			Main.menu(record);  // call menu method from Main class and pass the record array to it
		}

		if (sub_menu != null && !sub_menu.equals("") && sub_menu.matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+")) { // chech the valid input with regular expressions to check whether "sub_menu" string contain numeric value or not. 
			int int_menu = Integer.parseInt(sub_menu); // declare integer variable "int_menu" to hold the valid menu option, convert the String sub_menu to integer and assign it to int_menu

			if (int_menu < 0 || int_menu > 3) { // declare if statement to check if the user input contains invalid option
				JOptionPane.showMessageDialog(null, "Invalid input!\nPlease, enter valid option."); // display message screen to the user about invalid input
				subMenu(record); 				//call the sub menu to enable the user to try again and pass the record array to it
			}
			if (sub_menu.equals("1")) { 		// declare if statement to catch the input when the user inputs 1 (one)
				LastExport.lastExport(record); 	// call the lastExport method from LastExport class and pass the record array to it
			}
			if (sub_menu.equals("2")) { 		// declare if statement to catch the input when the user inputs 2 (two)
				exportRecords(record); 			// call back the exportRecords method and pass the record array to it
			}
			else if (sub_menu.equals("3")) { 	// declare if statement to catch the input when the user inputs 3 (three)
				Main.menu(record); 				// call the menu method from Main class and pass the record array to it
			}
			else if (sub_menu.equals("0")) { 	// declare if statement to catch the input when the user inputs 0 (zero)
				JOptionPane.showMessageDialog(null, "Thank you for using Photographic Collection.\nGood Bye."); // display Goodbye message to the user
				System.exit(0); 				// exit the program when the user click OK in the Goodbye message screen
			}
		}

		else
			JOptionPane.showMessageDialog(null, "Invalid input!\nPlease, enter valid option."); // display message screen to the user about invalid input in all other cases
		subMenu(record); 																// call the sub menu to enable the user to try again and pass the record array to it
	}

}
