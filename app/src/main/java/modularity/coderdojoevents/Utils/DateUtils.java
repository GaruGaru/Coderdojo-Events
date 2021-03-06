package modularity.coderdojoevents.Utils;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Garu on 23/01/2016.
 */
public class DateUtils {

    public static final SimpleDateFormat defaultUtc = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", getLocale());
    public static final SimpleDateFormat localUtc = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", getLocale());
    public static final DateFormatSymbols dateFormat = new DateFormatSymbols(getLocale());

    public static Calendar getCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }


    public static Locale getLocale() {
        return Locale.getDefault();
    }

    public static Date utcToDate(String utc) {
        try {
            utc = utc.replace("T", " ");
            return defaultUtc.parse(utc);
        } catch (ParseException e) {
            return null;
        }
    }

    public static String dateToString(Date date) {
        return localUtc.format(date);
    }

    public static String formatUtc(String utc) {
        return dateToString(utcToDate(utc));
    }
}
