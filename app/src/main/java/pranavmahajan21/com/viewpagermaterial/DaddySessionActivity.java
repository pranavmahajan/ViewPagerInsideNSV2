package pranavmahajan21.com.viewpagermaterial;

import android.os.Bundle;
import android.text.format.DateUtils;


import java.util.Calendar;
import java.util.Date;

import pranavmahajan21.com.viewpagermaterial.util.DateFormatter;

public class DaddySessionActivity extends DaddyActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public String getStringFromDate(Date date) {
        String dateString = DateFormatter.formatDateToString7(date);

        boolean isToday = DateUtils.isToday(date.getTime());
        if (isToday) {
            dateString = "Today, " + dateString;
        } else {
                    /* https://stackoverflow.com/a/37213500/2937847 */
            Calendar tomorrowCalendar = Calendar.getInstance();
            tomorrowCalendar.add(Calendar.DATE, 1);

            Calendar yesterdayCalendar = Calendar.getInstance();
            yesterdayCalendar.add(Calendar.DATE, -1);

            Calendar beginDateCalendar = Calendar.getInstance();
            beginDateCalendar.setTimeInMillis(date.getTime());

            if (yesterdayCalendar.get(Calendar.YEAR) == beginDateCalendar.get(Calendar.YEAR)
                    && yesterdayCalendar.get(Calendar.MONTH) == beginDateCalendar.get(Calendar.MONTH)
                    && yesterdayCalendar.get(Calendar.DATE) == beginDateCalendar.get(Calendar.DATE)) {
                dateString = "Yesterday, " + dateString;
            } else if (tomorrowCalendar.get(Calendar.YEAR) == beginDateCalendar.get(Calendar.YEAR)
                    && tomorrowCalendar.get(Calendar.MONTH) == beginDateCalendar.get(Calendar.MONTH)
                    && tomorrowCalendar.get(Calendar.DATE) == beginDateCalendar.get(Calendar.DATE)) {
                dateString = "Tomorrow, " + dateString;
            }
        }
        return dateString;
    }

}
