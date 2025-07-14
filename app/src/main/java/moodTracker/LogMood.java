//package declaration
package moodTracker;

//add imports
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LogMood {

    //initialize private data members
    private String date;
    private String mood;
    private String reason;
    private String timeOfDay;

    //Default constructor
    LogMood() {
        this.date = "";
        this.mood = "";
        this.reason = "";
        this.timeOfDay = "";
    }

    // Constructor to initialize mood and reason
    LogMood(String date, String mood, String reason, String timeOfDay) {
        this.date = date;
        this.mood = mood;
        this.reason = reason;
        this.timeOfDay = timeOfDay;
    }

    //Setters and Getters
    //set time of day
    public void setTimeOfDay(String timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    //set date
    public void setDate(String date) {
        this.date = date;
    }

    //set mood
    public void setMood(String mood) {
        this.mood = mood;
    }

    //set reason
    public void setReason(String reason) {
        this.reason = reason;
    }

    //get time of day
    public String getTimeOfDay() {
        return timeOfDay;
    }

    //get date
    public String getDate() {
        return date;
    }

    //get mood
    public String getMood() {
        return mood;
    }

    //get reason
    public String getReason() {
        return reason;
    }

    //append to a .txt file
    public void appendMood() {
        try {
            boolean stopper = false;
            String filePath = System.getProperty("user.dir") + "/src/main/resources/mood_logs.txt";
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            reader.readLine(); 

            String str;
            while((str = reader.readLine()) != null){
                String [] words = str.split(",\\s*");
                String firstWord = words[0].trim();
                String secWord = words[1].trim();
                String thirdWord = words[2].trim();
                if(firstWord.equals(date) && secWord.equals(mood) && thirdWord.equals(timeOfDay)) {
                    System.out.println("Mood for this date and time of day already exists. Please try again with a different date or time of day.");
                    stopper = true;
                    reader.close();
                    break;
                }
            }

            if(!stopper){
                BufferedWriter text = new BufferedWriter(new FileWriter(filePath, true));
                text.write(date + ", " + mood + ", " + timeOfDay + ", " + reason + "\n");
                text.close();
            }
            stopper = false; 
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    //retrieve of a specific date from the mood log
    public void retrieveSpecificDate() {
        try {
            String filePath = System.getProperty("user.dir") + "/src/main/resources/mood_logs.txt";
            BufferedReader text = new BufferedReader(new FileReader(filePath));

            String newFile = System.getProperty("user.dir") + "/src/main/resources/mood_log_by_date.txt";
            BufferedWriter writer = new BufferedWriter(new FileWriter(newFile, false));
            text.readLine(); 

            String str;
            while((str = text.readLine()) != null){
                //get the first word
                String [] words = str.split(",\\s*");
                String secWord = words[0].trim();

                if(secWord.equals(date)) {
                    System.out.println(str);
                    writer.write(str + "\n");
                }
            }
            text.close();
            writer.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    //filter by mood type
    public void retrieveSpecificMood() {
            try {
            String filePath = System.getProperty("user.dir") + "/src/main/resources/mood_logs.txt";
            BufferedReader text = new BufferedReader(new FileReader(filePath));

            String moodFile = System.getProperty("user.dir") + "/src/main/resources/mood_log_by_mood.txt";
            BufferedWriter writer = new BufferedWriter(new FileWriter(moodFile, false));
            text.readLine();

            String str;
            while((str = text.readLine()) != null){

                String [] words = str.split(",\\s*");
                String secWord = words[1].trim();

                if(secWord.equals(mood)) {
                    System.out.println(str);
                    writer.write(str + "\n");
                }
            }
            text.close();
            writer.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    //sort date in ascending order
    public void sortDateAscending() {
        try {
            String filePath = System.getProperty("user.dir") + "/src/main/resources/mood_logs.txt";
            BufferedReader text = new BufferedReader(new FileReader(filePath));

            String newFile = System.getProperty("user.dir") + "/src/main/resources/sorted_mood_log_by_date.txt";
            BufferedWriter writer = new BufferedWriter(new FileWriter(newFile, false));
            text.readLine(); 
            List<String> allLines = new ArrayList<>();
            String str;
            while((str = text.readLine()) != null){
                allLines.add(str);
            }
            
            DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("MM/dd/yyyy");

            allLines.sort(Comparator.comparing(line -> {
                String dateString = line.split(",")[0].trim();
                return LocalDate.parse(dateString, formattedDate);
            }));

            //print sorted lines
            for(String line : allLines) {
                System.out.println(line);
                writer.write(line + "\n");
            }
            text.close();
            writer.close();
        }
        catch(IOException e) {
            e.printStackTrace();    
        }
    }

}
