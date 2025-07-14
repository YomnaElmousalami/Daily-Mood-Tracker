package moodTracker;

//add imports
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

public class LogMoodTest {
 
    String moodFile = System.getProperty("user.dir") + "/src/main/resources/mood_logs.txt";

    @Test
    public void testDefaultConstructor() {
        LogMood logMood1 = new LogMood();
        LogMood logMood2 = new LogMood("01/01/2023", "Happy", "Morning", "New Year new beginnings.");
        assertNotNull(logMood1.getTimeOfDay());
        assertNotNull(logMood1.getDate());
        assertNotNull(logMood1.getMood());
        assertNotEquals(logMood1, logMood2);
    }

    @Test
    public void testParameterizedConstructor() {
        LogMood logMood1 = new LogMood("01/01/2023", "Happy", "New Year new beginnings.", "Morning");
        LogMood logMood2 = new LogMood();
        assertThat(logMood1.getTimeOfDay(), is("Morning"));
        assertNotNull(logMood1.getTimeOfDay());
        assertThat(logMood1.getDate(), is("01/01/2023"));
        assertNotNull(logMood1.getDate());
        assertThat(logMood1.getMood(), is("Happy"));
        assertNotNull(logMood1.getMood());
        assertThat(logMood1.getReason(), is("New Year new beginnings."));
        assertNotNull(logMood1.getReason());
        assertNotEquals(logMood1, logMood2);
    }

    @Test
    public void testSetTimeOfDay() {
        LogMood logMood1 = new LogMood();
        logMood1.setTimeOfDay("Afternoon");
        assertThat(logMood1.getTimeOfDay(), is("Afternoon"));
        assertNotNull(logMood1.getTimeOfDay());
        assertNotNull(logMood1.getDate());
        assertNotNull(logMood1.getMood());
        assertNotNull(logMood1.getReason());
    }

    @Test
    public void testSetDate() {
        LogMood logMood1 = new LogMood();
        logMood1.setDate("02/02/2023");
        assertThat(logMood1.getDate(), is("02/02/2023"));
        assertNotNull(logMood1.getDate());
        assertNotNull(logMood1.getTimeOfDay());
        assertNotNull(logMood1.getMood());
        assertNotNull(logMood1.getReason());
    }

    @Test
    public void testSetMood() {
        LogMood logMood1 = new LogMood();
        logMood1.setMood("Sad");
        assertThat(logMood1.getMood(), is("Sad"));
        assertNotNull(logMood1.getMood());
        assertNotNull(logMood1.getTimeOfDay());
        assertNotNull(logMood1.getDate());
        assertNotNull(logMood1.getReason());
    }

    @Test
    public void testSetReason() {
        LogMood logMood1 = new LogMood();
        logMood1.setReason("Feeling down today.");
        assertThat(logMood1.getReason(), is("Feeling down today."));
        assertNotNull(logMood1.getReason());
        assertNotNull(logMood1.getTimeOfDay());
        assertNotNull(logMood1.getDate());
        assertNotNull(logMood1.getMood());
    }

    @Test
    public void getTimeOfDay() {
        LogMood logMood1 = new LogMood("01/01/2023", "Happy", "New Year new beginnings.", "Morning");
        String timeOfDay = "Morning";
        assertThat(logMood1.getTimeOfDay(), is(timeOfDay));
    }

    @Test
    public void getDate() {
        LogMood logMood1 = new LogMood("01/01/2023", "Happy", "New Year new beginnings.", "Morning");
        String date = "01/01/2023";
        assertThat(logMood1.getDate(), is(date));
    }

    @Test
    public void getMood() {
        LogMood logMood1 = new LogMood("01/01/2023", "Happy", "New Year new beginnings.", "Morning");
        String mood = "Happy";
        assertThat(logMood1.getMood(), is(mood));
    }

    @Test
    public void getReason() {
        LogMood logMood1 = new LogMood("01/01/2023", "Happy", "New Year new beginnings.", "Morning");
        String reason = "New Year new beginnings.";
        assertThat(logMood1.getReason(), is(reason));
    }

    @Test
    public void testAppendMood() {
        LogMood logMood1 = new LogMood("01/01/2023", "Happy", "New Year new beginnings.", "Morning");
        logMood1.appendMood();
        String appendedMood = "";

        try{
            BufferedReader text = new BufferedReader(new FileReader(moodFile));
            String str;
            while((str = text.readLine()) != null){
                appendedMood = str;
            }
            text.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        assertEquals("01/01/2023, Happy, Morning, New Year new beginnings.", appendedMood);
    }

    @Test
    public void testRetrieveSpecificDate() {
        LogMood logMood1 = new LogMood();
        logMood1.setDate("01/01/2023");
        logMood1.retrieveSpecificDate();
        boolean matchedDates = true;
        try {
            String dateFile = System.getProperty("user.dir") + "/src/main/resources/mood_log_by_date.txt";
            BufferedReader reader = new BufferedReader(new FileReader(dateFile));
            String str;
            while((str = reader.readLine()) != null){
                String [] words = str.split(",\\s*");
                String firstWord = words[0].trim();
                if(!firstWord.equals("01/01/2023")) {
                    matchedDates = false;
                    break;
                }
            }
            reader.close();
            assertThat(matchedDates, is(true));
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRetrieveSpecificMood() {
        LogMood logMood1 = new LogMood();
        logMood1.setMood("Happy");
        logMood1.retrieveSpecificMood();
        boolean matchedMoods = true;
        try {
            String moodFile = System.getProperty("user.dir") + "/src/main/resources/mood_log_by_mood.txt";
            BufferedReader reader = new BufferedReader(new FileReader(moodFile));
            String str;
            while((str = reader.readLine()) != null){
                String [] words = str.split(",\\s*");
                String firstWord = words[1].trim();
                if(!firstWord.equals("Happy")) {
                    matchedMoods = false;
                    break;
                }
            }
            reader.close();
            assertThat(matchedMoods, is(true));
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSortDateAscending() {
        LogMood logMood1 = new LogMood();
        logMood1.sortDateAscending();
        boolean sorted = true;
        try {
            String sortedFile = System.getProperty("user.dir") + "/src/main/resources/sorted_mood_log_by_date.txt";
            BufferedReader reader = new BufferedReader(new FileReader(sortedFile));
            String previousLine = null;
            String currentLine;
            while((currentLine = reader.readLine()) != null){
                if(previousLine != null) {
                    String[] previousWords = previousLine.split(",\\s*");
                    String[] currentWords = currentLine.split(",\\s*");
                    DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                    LocalDate previousDate = LocalDate.parse(previousWords[0].trim(), formattedDate);
                    LocalDate currentDate = LocalDate.parse(currentWords[0].trim(), formattedDate);
                    if(previousDate.isAfter(currentDate)) {
                        sorted = false;
                        break;
                    }
                }
                previousLine = currentLine;
            }
            reader.close();
            assertThat(sorted, is(true));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}