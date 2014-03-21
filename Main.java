package photographicCollection;

import javax.swing.*;
import java.io.*;

public class Main {
	public static void main (String[]args) throws IOException {		// declare Main method
		readExistingData(); 										//call readExistingData method to read and load the existing data to the program
	}

	public static void readExistingData () throws IOException{ 			// declare readExistingData method with return parameter set to void as this method doen#t have to respond to the calling method (Main)
		final FileReader readFile = new FileReader("Collection.txt"); 	// look for existing Collection.txt file in the program files directory
		final BufferedReader readBuffer = new BufferedReader(readFile);	// set reading buffer to read the data from the file
		
		/* declare lines integer variable and initialize it to zero. 
		* The lines variable will be used to hold the total number 
		* of existing lines in Collection.txt, which is the required minimum 
		* of record objects array size in order to store and enable all existing data */
		int lines=0; 	

		// declare a loop to iterate depending on the read buffer data from file
		while (readBuffer.readLine() != null) { // set condition to enter the loop only when the buffer read data is not empty, e.g. there is existing data in the file
			lines++; 							// increment lines variable with 1, each time the loop repeats
		}

		if (lines == 0) { 	// check  if the file does not contain any data, e.g. while loop is skipped
			lines++; 		// increment lines with 1, to enable at least one record array element to be created in order to store new data entered by the user
		}

		else
			lines = (lines / 4); // if the lines variable is with value 1 or bigger, devide the lines variable by 4 to reach the real records number ( 1 record is saved as 4 lines -> -space, -title, -year, -description)



		Record record[] = new Record[lines]; // declare and initialize record object array with the size of current lines value
		
		// declare a loop to iterate while the read line buffer reads data form the file
		while (readBuffer.readLine() != null) { 					// set condition to enter the loop only when the buffer read data is not empty, e.g. there is existing data in the file
			for (int i=0; i<lines; i++) { 							// declare a loop which will iterate 
				String title = readBuffer.readLine(); 				// declare String variable "title" and assign to it the readLine data to hold the title extracted from the Collection.txt file
				int year = Integer.parseInt(readBuffer.readLine()); // declare int variable "year" and assign to it the readLine data to hold the year extracted from the Collection.txt file
				String desc = readBuffer.readLine();  				// declare String variable "desc" and assign to it the readLine data to hold the description extracted from the Collection.txt file

				record[i] = new Record(title, year, desc); 			// insert new object array element for each line that is read from the Collection.txt file
			}
		}

		readBuffer.close(); // end the reading of Collection.txt file
		
		menu(record); 		// call the menu method and pass the initialised record array to it.
	}

	public static void menu (Record [] record) throws IOException{ // declare menu method

		//present the options menu to the user.
		String input = JOptionPane.showInputDialog ( // declare string variable to hold the user input
				"<<< Photographic Collection Software >>>\nversion 1.00\n\nCopyright © 2013 Vasil Dimitrov, M00421664." +
				"\n\nWelcome to the Main Menu" +
				"\n\nEnter the digit of one of the available options and press OK to continue:\n" +
				"\n- 1 - Add new photo" +
				"\n- 2 - Search for a photo" +
				"\n- 3 - Edit a photo" +
				"\n- 4 - View all photos" +
				"\n- 5 - Sorted view" +
				"\n- 6 - Export all data" +
				"\n- 7 - View the latest export" +
				"\n- 0 - Quit"
				);
		if (input == null) { 																				// check if the user press Cancel button, or windon X (Close) button or Esc on the keyboard
			JOptionPane.showMessageDialog(null, "Thank you for using Photographic Collection.\nGood Bye.");	// when input == null, display Goodbye message to the user
			System.exit(0);																					// exit the program when the user click OK in the Goodbye message screen
		}

		if (input != null && !input.equals("") && input.matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+")) {	// with regular expressions check whether a string contain numeric value or not. 
			
			int choise = Integer.parseInt(input); 											 // convert the input string numeric value to integer
			
			if (choise < 0 || choise > 7) { 												 // set if condition to check for input with existing menu option value from 0 to 7
				JOptionPane.showMessageDialog(null, "Invalid input!\nPlease, enter valid option.");	 // when the input value does not exist as menu option display message screen to the user about invalid input
				menu(record); 																 // call back the menu method enabling the user to try again and pass the record array as expected data
			}

			else if (choise == 1) {					// When the user input is 1 (Add new photo),
				NewRecord.newRecord(record);		// call the newRecord method from NewRecord class and pass the record array to it.
			}
			else if (choise == 2) {					// When the user input is 2 (Search for a photo),
				SearchRecord.searchRecord(record);	// call the searchRecord method from SearchRecord class and pass the record array to it.
			}
			else if (choise == 3) {					// When the user input is 3 (Edit a photo),
				EditRecord.editRecord(record);		// call the editRecord method from EditRecord class and pass the record array to it.
			}
			else if (choise == 4) {					// When the user input is 4 (View all photos),
				ListRecords.listRecords(record);	// call the listRecords method from ListRecords class and pass the record array to it.
			}
			else if (choise == 5) {					// When the user input is 5 (Sorted view),
				SortRecords.sortRecords(record);	// call the sortRecords method from SortRecords class and pass the record array to it.
			}
			else if (choise == 6) {					// When the user input is 6 (Export all data),
				ExportRecords.exportRecords(record);// call the exportRecords method from ExportRecords class and pass the record array to it.
			}
			else if (choise == 7) {					// When the user input is 7 (View the latest export),
				LastExport.lastExport(record);		// call the lastExport method from LastExport class and pass the record array to it.
			}
			else if (choise == 0) {					// When the user input is 0 (Quit),
				JOptionPane.showMessageDialog(null, "Thank you for using Photographic Collection.\nGood Bye."); // display Goodbye message to the user,
				System.exit(0);						// exit the program when the user click OK in the Goodbye message screen
			}
		}
		else 										// check the input is empty and the user press OK button, or Enter on the keyboard
			JOptionPane.showMessageDialog(null, "Invalid input!\nPlease, enter valid option.");		//display message screen to the user about invalid input
			menu(record);							// call back the menu method enabling the user to try again and pass the record array as expected data
	}
}

