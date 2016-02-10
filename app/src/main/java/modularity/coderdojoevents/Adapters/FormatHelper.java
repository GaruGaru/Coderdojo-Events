package modularity.coderdojoevents.Adapters;

import java.util.Calendar;

import modularity.coderdojoevents.EventBrite.Response.End;
import modularity.coderdojoevents.EventBrite.Response.Start;
import modularity.coderdojoevents.EventBrite.Response.Venue;
import modularity.coderdojoevents.Utils.DateUtils;
import modularity.coderdojoevents.Utils.StringUtils;

/**
 * Created by Garu on 24/01/2016.
 */
public class FormatHelper {

    public static String formatVenue(Venue venue) {
        String str = "";

        str += venue.getAddress().getCity();

        if (venue.getAddress().getAddress_1() != null && !venue.getAddress().getAddress_1().equals("null"))
            str += " - " + venue.getAddress().getAddress_1();

        str = StringUtils.shorten(str, 30);
        return str;
    }

    public static String formatDate(Start start, End end) {
        Calendar startCal = DateUtils.getCalendar(DateUtils.utcToDate(start.getLocal()));
        Calendar endCal = DateUtils.getCalendar(DateUtils.utcToDate(end.getLocal()));
        String startHour = formatHour(startCal.get(Calendar.HOUR_OF_DAY)) + ":" + formatHour(startCal.get(Calendar.MINUTE));
        String endHour = formatHour(endCal.get(Calendar.HOUR_OF_DAY)) + ":" + formatHour(endCal.get(Calendar.MINUTE));
        String newDate = getDayName(startCal.get(Calendar.DAY_OF_WEEK)) + " " + (startCal.get(Calendar.DAY_OF_MONTH)) + " " + getMonthName(startCal.get(Calendar.MONTH));
        return newDate + "\n" + startHour + " - " + endHour;
    }


    public static String formatHour(String string) {
        return (string.length() == 1) ? "0" + string : string;
    }

    public static String formatHour(int val) {
        return formatHour(String.valueOf(val));
    }

    public static String getDayName(int day) {
        return DateUtils.dateFormat.getWeekdays()[day];
    }

    public static String getMonthName(int month) {
        return DateUtils.dateFormat.getMonths()[month];
    }


}
