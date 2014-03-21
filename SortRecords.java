package photographicCollection;

import javax.swing.*;

import java.io.*;

public class SortRecords {

	public static void sortRecords(Record [] record)  throws IOException {

		final FileReader inputFile = new FileReader("Collection.txt"); 	  	// read the Collections.txt file
		final BufferedReader inputBuffer = new BufferedReader(inputFile); 	// buffer the read data

		int lines=0; 													  	//declare lines variable to hold the number of line existing in the Collections.txt file and initialize it to 0 (zero)
		while (inputBuffer.readLine() != null) { 							// declare loop and set its condition to iterate until there are existing lines in Collection.txt file
			String title = inputBuffer.readLine(); 							//declare String variable to hold the read data for title, get the data with readLine and assign it
			int year = Integer.parseInt(inputBuffer.readLine()); 			//declare String variable to hold the read data for year, get the data with readLine, convert it to inteer and assign it
			String desc = inputBuffer.readLine(); 							//declare String variable to hold the read data for desc, get the data with readLine and assign it

			record[lines] = new Record(title, year, desc);  // insert the title, year and description data in element of the record array on each loop iteration
			
			lines++; 										// increment the lines value by one each time the loop iterates, so the consequant record array element to be accessed
		}

		inputBuffer.close(); // end the reading of Collection.txt file

		sortOptions(record); // call the sortOptions method and pass the record array to it.
	}
	public static void sortOptions(Record [] record)  throws IOException { 	// declare the sortOptions method

		String sort = JOptionPane.showInputDialog ( 						// declare String variable "sort" to hold the user choise, display menu options to the user and ask for input
				"Choose one of the sort options.\n" +
				"\n- 1 - Sort all records by Title" +
				"\n- 2 - Sort all records by Year" +
				"\n- 3 - Sort all records by Description" +
				"\n- 4 - Back to the Main Menu" +
				"\n- 0 - Quit\n");

		if (sort == null) { 	// check if the user press Cancel button, or windon X (Close) button or Esc on the keyboard
			Main.menu(record);  // call the menu method from Main class and pass the record array to it
		}

		if (sort != null && !sort.equals("") && sort.matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+")) { // check if the user input for sort variabne is valid and numerical

			int int_sort = Integer.parseInt(sort); 												 // declare integer variable "int_sort", convert the sort value to integer and assign it to int_sort

			while (int_sort < 0 || int_sort > 4) { 											// set if condition to check for int_sort with existing menu option value from 0 to 4
				JOptionPane.showMessageDialog(null, "Invalid input!\nPlease, enter valid option."); // when the int_sort value does not exist as menu option display message screen to the user about invalid input
				sortOptions(record); 														// call back the sortOptions method enabling the user to try again and pass the record array as expected data
			}

			if (int_sort == 1) 		// When the user input is 1 (one)
				sortTitle(record);	// call the sortTitle method and pass the record array

			else if (int_sort == 2) // When the user input is 2 (two)
				sortYear(record);	// call the sortYear method and pass the record array

			else if (int_sort == 3)	// When the user input is 3 (three)
				sortDesc(record);	// call the sortDesc method and pass the record array

			else if (int_sort == 4) // When the user input is 4 (four)
				Main.menu(record);	// call the menu method from Main class and pass the record array

			else if (int_sort == 0) { // When the user input is 4 (four)
				JOptionPane.showMessageDialog(null, "Thank you for using Photographic Collection.\nGood Bye."); //display Good Bye message
				System.exit(0); 	  // exit the program when the user press OK in the Good Bye message screen
			}
		}
		else { 																			// for any other conditions
			JOptionPane.showMessageDialog(null, "Invalid input!\nPlease, enter valid option."); // display invalid input message to the user
			sortOptions(record); 														// call the sortOptions method to enable the user to try again and pass the record array to it.
		}

	}
	public static void sortTitle(Record [] record)  throws IOException { //declare sortTitle method

		for(int j=0; j<record.length; j++) {						// declare loop which will hold the sorting of the records titles, the loop will iterate for the record array length
			for (int i=j+1 ; i<record.length; i++) {				// declare nested loop to iterate second index variable, this loop will iterate for the record array length -1
				if(record[i].title.compareTo(record[j].title)<0) {	// compare the title strings
				
					String temp_title= record[j].title;				// declare String variable "temp_title" to hold temporarily the title value which has to be moved and therefore sorted
					record[j].title= record[i].title;				// swap relevant titles on each loop iteration (right to left)
					record[i].title=temp_title;						// put back the temporary kept title to the relevant position
					
					int temp_year= record[j].year;					// declare String variable "temp_year" to hold temporarily the year value which has to be moved and therefore sorted
					record[j].year= record[i].year;					// swap relevant years on each loop iteration (right to left)
					record[i].year=temp_year;						// put back the temporary kept year to the relevant position
					
					String temp_desc= record[j].description;		// declare String variable "temp_desc" to hold temporarily the description value which has to be moved and therefore sorted
					record[j].description= record[i].description;	// swap relevant descriptions on each loop iteration (right to left)
					record[i].description=temp_desc;				// put back the temporary kept description to the relevant position
				}
			}
			System.out.print(record[j].title + " " + record[j].year + " " + record[j].description + "\n"); //print the results in the console
			JOptionPane.showMessageDialog(null, record[j].title + " " + record[j].year + " " + record[j].description); //display the results in dialog screens.
		}
		subMenu(record);
	}
	public static void sortYear(Record [] record)  throws IOException {

		for(int j=0; j<record.length;j++) {							// declare loop which will hold the sorting of the records years, the loop will iterate for the record array length
			for (int i=j+1 ; i<record.length; i++) {				// declare nested loop to iterate second index variable, this loop will iterate for the record array length -1
				if(record[i].year < record[j].year) {				// compare the year integers
				
					String temp_title= record[j].title;				// declare String variable "temp_title" to hold temporarily the title value which has to be moved and therefore sorted
					record[j].title= record[i].title;				// swap relevant titles on each loop iteration (right to left)
					record[i].title=temp_title;						// put back the temporary kept title to the relevant position
					
					int temp_year= record[j].year;					// declare String variable "temp_year" to hold temporarily the year value which has to be moved and therefore sorted
					record[j].year= record[i].year;					// swap relevant years on each loop iteration (right to left)
					record[i].year=temp_year;						// put back the temporary kept year to the relevant position
					
					String temp_desc= record[j].description;		// declare String variable "temp_desc" to hold temporarily the description value which has to be moved and therefore sorted
					record[j].description= record[i].description;	// swap relevant descriptions on each loop iteration (right to left)
					record[i].description=temp_desc;				// put back the temporary kept description to the relevant position
				}
			}
			System.out.print(record[j].title + " " + record[j].year + " " + record[j].description + "\n"); //print the results in the console
			JOptionPane.showMessageDialog(null, record[j].title + " " + record[j].year + " " + record[j].description); //display the results in dialog screens.
		}
		subMenu(record);
	}
	public static void sortDesc(Record [] record)  throws IOException {

		for(int j=0; j<record.length;j++) {							// declare loop which will hold the sorting of the records descriptions, the loop will iterate for the record array length
			for (int i=j+1 ; i<record.length; i++) {				// declare nested loop to iterate second index variable, this loop will iterate for the record array length -1
				if(record[i].description.compareTo(record[j].description)<0) { // compare the description strings
				
					String temp_title= record[j].title;				// declare String variable "temp_title" to hold temporarily the title value which has to be moved and therefore sorted
					record[j].title= record[i].title;				// swap relevant titles on each loop iteration (right to left)
					record[i].title=temp_title;						// put back the temporary kept title to the relevant position
					
					int temp_year= record[j].year;					// declare String variable "temp_year" to hold temporarily the year value which has to be moved and therefore sorted
					record[j].year= record[i].year;					// swap relevant years on each loop iteration (right to left)
					record[i].year=temp_year;						// put back the temporary kept year to the relevant position
					
					String temp_desc= record[j].description;		// declare String variable "temp_desc" to hold temporarily the description value which has to be moved and therefore sorted
					record[j].description= record[i].description;	// swap relevant descriptions on each loop iteration (right to left)
					record[i].description=temp_desc;				// put back the temporary kept description to the relevant position
				}
			}
			System.out.print(record[j].title + " " + record[j].year + " " + record[j].description + "\n");
			JOptionPane.showMessageDialog(null, record[j].title + " " + record[j].year + " " + record[j].description);
		}
		subMenu(record);
	}
	public static void subMenu (Record [] record)  throws IOException  {  //declare subMenu method

		String sub_menu = JOptionPane.showInputDialog ( // declare String variable "sub_menu" to hold the value for user choise. Show menu with options to the user and ask for input.
				"What do you want to do next?" +
						"\n- 1 - Sort all records" +
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
				sortOptions(record); 														// call the sortOptions method and pass the record array to it
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
