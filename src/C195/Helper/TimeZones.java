package C195.Helper;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.TimeZone;

public class TimeZones {

    public static String convertToCurrentTimeZone(String Date) {
        String converted_date = "";
        try {
            DateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            utcFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            java.util.Date date = utcFormat.parse(Date);
            DateFormat currentTFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            currentTFormat.setTimeZone(TimeZone.getTimeZone(getCurrentTimeZone()));
            converted_date =  currentTFormat.format(date);
        }catch (Exception e){
            e.printStackTrace();
        }
        return converted_date;
    }

    //get the current time zone
    public static String getCurrentTimeZone(){
        TimeZone tz = Calendar.getInstance().getTimeZone();
        System.out.println(tz.getDisplayName());
        return tz.getID();
    }

    //convert from local time to UTC
    public static String getUTCTime(){
        LocalDateTime ldt = LocalDateTime.now();
        ZonedDateTime zdt = ZonedDateTime.of(ldt, ZoneId.systemDefault());
        ZonedDateTime utc = zdt.withZoneSameInstant(ZoneId.of("UTC"));
        Timestamp timestamp = Timestamp.valueOf(utc.toLocalDateTime());
        return timestamp.toString();
    }

    public static String convertToUTCTimeZone(String Date) {
        String converted_date = "";
        try {
            DateFormat localFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            localFormat.setTimeZone(TimeZone.getTimeZone(ZoneId.systemDefault().getId()));
            java.util.Date date = localFormat.parse(Date);
            DateFormat currentTFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            currentTFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            converted_date =  currentTFormat.format(date);
        }catch (Exception e){
            e.printStackTrace();
        }
        return converted_date;
    }

}
