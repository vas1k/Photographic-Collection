package photographicCollection;

public class Record { //declare record object class

	public String title; 		//declare String variable to set the title element in the Record objects
	public int year; 			//declare String variable to set the year element in the Record objects
	public String description;  //declare String variable to set the description element in the Record objects

	public Record (String t, int y, String d){ //initialize the Record object
		title=t;
		year=y;
		description=d;
	}

}
