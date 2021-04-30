import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Observable;
import queuemanager.SortedLinkedList;

/*
* Gavin Bruce - 11000148
*/

public class Model extends Observable {
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); // Formatting object for making date more readble
    String display = "analog";
    String day;
    int alarmCount;
    SortedLinkedList<Alarm> alarms = new SortedLinkedList(); 
    int hour = 0;
    int minute = 0;
    int second = 0;
    int oldSecond = 0;
    String amPm;
    
    public Model() {
        update();
    }
    
    public void update() {
        Calendar date = Calendar.getInstance();
        
        alarmCount = alarms.getSize();
        day = dateFormat.format(date.getTime()); // Formats date for date label
        hour = date.get(Calendar.HOUR);
        if(hour==0) hour = 12; // Incase it displays 0 o'clock - set it to 12
        minute = date.get(Calendar.MINUTE);
        oldSecond = second;
        second = date.get(Calendar.SECOND);
        if (oldSecond != second) {
            setChanged();
            notifyObservers();
        }
        if (date.get(Calendar.AM_PM) == Calendar.PM) amPm = "pm"; // Checks calendar for whether time of day is in am or pm
        else amPm = "am";
        
    }
    
    
}