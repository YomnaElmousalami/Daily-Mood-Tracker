package moodTracker;

//add imports
import java.util.Scanner; 

public class MoodTracker {
    public static void main(String[] args) {

        //initialize variables
        Scanner userInput = new Scanner(System.in);
        Scanner moodInput = new Scanner(System.in);
        Scanner dateInput = new Scanner(System.in);
        Scanner goAgain = new Scanner(System.in);
        String choice, date, mood, reason, timeOfDay, repeatAgain;
        String[] inputs;
       
        do{
            System.out.println("\nWelcome to the Mood Tracker Application!\n");
            System.out.println("This application allows you to track your mood over time.");
            System.out.println("You can log your mood for the day, view the mood log by date, and/or view the mood log by mood type.");
            System.out.println("Select one of the following options:\n");
            System.out.println("1. Log Mood");
            System.out.println("2. View Mood Log by Date");
            System.out.println("3. View Mood Log by Mood Type");
            System.out.println("4. Sort Mood Log by Date (Ascending Order)");
            System.out.println("5. Exit\n");

            choice = userInput.nextLine();

            if(choice.equals("1")){
                System.out.println("You selected to log your mood.");
                System.out.println("Please enter the date, mood, time of day (morning, afternoon, evening), and reason for your mood. Seperate your answers by a comma:");
                do{
                    mood = moodInput.nextLine();
                    inputs = mood.split(",");
                    if(inputs.length == 4 && !inputs[0].trim().isEmpty() && !inputs[1].trim().isEmpty() && !inputs[2].trim().isEmpty() && !inputs[3].trim().isEmpty()) {
                        date = inputs[0].trim();
                        mood = inputs[1].trim();
                        timeOfDay = inputs[2].trim();
                        reason = inputs[3].trim();

                        LogMood specificMood = new LogMood(date, mood, timeOfDay, reason); 
                        specificMood.appendMood();
                    }
                else{
                    System.out.println("Invalid input format. Please enter your mood and reason separated by a comma.");
                    }
                }
                while(inputs.length != 4);
            }

            else if(choice.equals("2")){
                boolean stopper = false;
                System.out.println("You selected to view the mood log by date.");
                System.out.println("Select the date you want to view the mood log for (format: MM/DD/YYYY):");
                do{
                    date = dateInput.nextLine();
                    if(date.matches("\\d{2}/\\d{2}/\\d{4}")) {
                        LogMood dateMood = new LogMood();
                        dateMood.setDate(date);
                        dateMood.retrieveSpecificDate();
                        stopper = true;
                    } 
                    else {
                        System.out.println("Invalid date format. Please enter the date in MM/DD/YYYY format.");
                    }
                }
                while(stopper == false);
            }

            else if(choice.equals("3")){
                System.out.println("You selected to view the mood log by mood type.");
                System.out.println("Please enter the mood type you want to view:");
                mood = moodInput.nextLine();
                LogMood moodType = new LogMood();
                moodType.setMood(mood);
                moodType.retrieveSpecificMood();
            }

            else if(choice.equals("4")){
                System.out.println("You selected to sort the mood log by date in ascending order.");
                LogMood sortDate = new LogMood();
                sortDate.sortDateAscending();
            }
            else if(choice.equals("5")){
                System.exit(0);
            }
            else{
                System.out.println("Invalid choice. Please select a valid option (1-4).");
            }

            do{
                System.out.println("\nWould you like to go back to the main menu? (yes/no)");
                repeatAgain = goAgain.nextLine();
                if(!repeatAgain.equals("Yes") && !repeatAgain.equals("yes") && !repeatAgain.equals("No") && !repeatAgain.equals("no")){
                    System.out.println("Invalid input. Please enter 'yes' or 'no'.");
                }
            }
            while(!repeatAgain.equals("Yes") && !repeatAgain.equals("yes") && !repeatAgain.equals("No") && !repeatAgain.equals("no"));

            }
        while(repeatAgain.equals("Yes") || repeatAgain.equals("yes") || !choice.equals("1") && !choice.equals("2") && !choice.equals("3") && !choice.equals("4") && !choice.equals("5"));
        
        userInput.close();
    }
}
