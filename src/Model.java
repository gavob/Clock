import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Observable;
//import java.util.GregorianCalendar;

public class Model extends Observable {
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    
    String display = "analog";
    
    String day;
    
    Alarm alarm = null;
    
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
        
        day = dateFormat.format(date.getTime()); //Needs checked for formatting
        
        hour = date.get(Calendar.HOUR);
        if(hour==0) hour = 12; // incase it displays 0pm
        minute = date.get(Calendar.MINUTE);
        oldSecond = second;
        second = date.get(Calendar.SECOND);
        if (oldSecond != second) {
            setChanged();
            notifyObservers();
        }
        if (date.get(Calendar.AM_PM) == Calendar.PM) amPm = "pm";
        else amPm = "am";
        
    }
}