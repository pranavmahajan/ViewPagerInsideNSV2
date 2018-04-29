package pranavmahajan21.com.viewpagermaterial.util;

/**
 * Created by pranav on 1/12/15.
 */

import android.annotation.SuppressLint;
import android.text.format.DateUtils;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@SuppressLint("SimpleDateFormat")
public class DateFormatter {
//    https://www.mkyong.com/java/how-to-convert-string-to-date-java/
    // TODO: 3/2/18 All of these functions will crash if string is not provided in proper format
    public String formatDateToString(Date date) {
        if (date == null) {
            return "-";
        }

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        // formatter.setTimeZone(TimeZone.getTimeZone("GMT"));

        String dateStr = formatter.format(date);
        System.out.println(">><<><><><" + dateStr);
        return dateStr;
    }

    public static String formatDateToString2(Date date) {

        if (date == null) {
            return "-";
        }

        SimpleDateFormat formatter = new SimpleDateFormat(
                "MMM dd, yyyy hh:mmaa");
        // formatter.setTimeZone(TimeZone.getTimeZone("GMT"));

        String dateStr = formatter.format(date);
        System.out.println(">><<><><><" + dateStr);
        return dateStr;
    }


    public String formatDateToString3(Date date) {

        if (date == null) {
            return "-";
        }

        SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, HH:mm");

        String dateStr = formatter.format(date);
        System.out.println(">><<><><><" + dateStr);
        return dateStr;
    }

    public String formatDateToString4(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss'Z'");

        String dateStr = formatter.format(date);
        System.out.println(">><<><><><" + dateStr);
        return dateStr;
    }

    public static String formatDateToString5(Date startDate, String endDateString) {
        Date endDate = DateFormatter.formatStringToDateEvent(endDateString);

        if (startDate == null) {
            Log.e(Constants.APP_NAME, "Start date is NULL. HOW?????");
            return null;
        }

        String dateStr = null;

        if (endDate == null) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM");
            dateStr = formatter.format(startDate);
        } else {
            SimpleDateFormat formatter = new SimpleDateFormat("dd");
            dateStr = formatter.format(startDate) + "-" + formatter.format(endDate) + " ";

            formatter = new SimpleDateFormat("MMMM");
            dateStr = dateStr + formatter.format(startDate);
        }
        System.out.println(">><<><><><" + dateStr);
        return dateStr;
    }

    public static String formatDateToString7(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM");

        String dateStr = formatter.format(date);
        System.out.println(">><<><><><" + dateStr);
        return dateStr;
    }

    public static String formatDateToStringDayMonth(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM");

        String dateStr = formatter.format(date);
        System.out.println(">><<><><><" + dateStr);
        return dateStr;
    }

    public static String formatDateToStringOnlyTime(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm");

        String dateStr = formatter.format(date);
        System.out.println(">><<><><><" + dateStr);
        return dateStr;
    }

    public static String formatDateToStringSession(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");

        String dateStr = formatter.format(date);
        System.out.println(">><<><><><" + dateStr);
        return dateStr;
    }

    public static String formatDateToStringEvent(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy HH:mm:ss");

        String dateStr = formatter.format(date);
        System.out.println(">><<><><><" + dateStr);
        return dateStr;
    }

    public static Date formatStringToDateEvent(String dateString) {
        System.out.println("1212  :  " + dateString);
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy hh:mm:ss");

        Date date = null;
        try {
            date = formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // System.out.println(">><<><><><" + dateStr);
        return date;
    }

    public static String formatDateToString(Date date, String pattern) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);

        String dateStr = formatter.format(date);
        System.out.println(">><<><><><" + dateStr);
        return dateStr;
    }


    public static String formatDateToString6(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("hh:mma");

        String dateStr = formatter.format(date);
        System.out.println(">><<><><><" + dateStr);
        return dateStr;
    }

    public Date formatStringToDate(String dateString) {
        System.out.println("1212  :  " + dateString);
        SimpleDateFormat formatter = new SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ssZ");

        Date date = null;
        try {
            date = formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // System.out.println(">><<><><><" + dateStr);
        return date;
    }

    public Date formatStringToDate2(String dateString) {
        System.out.println("1212  :  " + dateString);
        SimpleDateFormat formatter = new SimpleDateFormat(
                "EEE, dd MMM, hh:mmaa");

        Date date = null;
        try {
            date = formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // System.out.println(">><<><><><" + dateStr);
        return date;
    }


    public Date formatStringToDate5(String dateString) {
        System.out.println("1212  :  " + dateString);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        Date date = null;
        try {
            date = formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // System.out.println(">><<><><><" + dateStr);
        return date;
    }

    public static Date formatStringToDateX(String dateString) {
        System.out.println("1212  :  " + dateString);
        SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy hh:mmaa");

        Date date = null;
        try {
            date = formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // System.out.println(">><<><><><" + dateStr);
        return date;
    }

    public Date formatStringToDate3Copy(String dateString) {
        System.out.println("1212  :  " + dateString);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy, hh:mm");

        Date date = null;
        try {
            date = formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // System.out.println(">><<><><><" + dateStr);
        return date;
    }

    @SuppressWarnings("deprecation")
    public Date formatStringSpecialToDate(String dateString) {
        /**
         * http://stackoverflow.com/questions/20036075/json-datetime-parsing-in-
         * android
         **/
        if (dateString == null) {
            return null;
        }
        System.out.println("1212  : " + dateString);
        // String ackwardDate = "/Date(1376841597000)/";

        Calendar calendar = Calendar.getInstance();
        String ackwardRipOff = dateString.replace("\\/Date(", "").replace(
                ")\\/", "");
        Long timeInMillis = Long.valueOf(ackwardRipOff);
        calendar.setTimeInMillis(timeInMillis);
        // calendar.setTimeZone(TimeZone.get);
        System.out.println(calendar.getTime().toGMTString());
        System.out.println(calendar.getTime());
        return calendar.getTime();
    }

    public static String formatDateToStringSec2(Date date) {

        if (date == null) {
            return "-";
        }

        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss.SSS");

        String dateStr = formatter.format(date);
        dateStr = dateStr.replaceAll("[@.|?*<\":>+\\[\\]/']", "_");
        System.out.println(">><<><><><" + dateStr);
        return dateStr;
    }

    public static String formatDateToString1(Date date) {
        if (date == null) {
            return "-";
        }

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        // formatter.setTimeZone(TimeZone.getTimeZone("GMT"));

        String dateStr = formatter.format(date);
        System.out.println(">><<><><><" + dateStr);
        return dateStr;
    }

    public static int getDaysDifferenceFromDate(Date date) {

        boolean isToday = DateUtils.isToday(date.getTime());
        if (isToday) {
            return 0;
        } else {
            // TODO: 9/27/17 This may not work if difference b/w dates is b/w 24 & 48hours. Test.
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
                return -1;
            } else if (tomorrowCalendar.get(Calendar.YEAR) == beginDateCalendar.get(Calendar.YEAR)
                    && tomorrowCalendar.get(Calendar.MONTH) == beginDateCalendar.get(Calendar.MONTH)
                    && tomorrowCalendar.get(Calendar.DATE) == beginDateCalendar.get(Calendar.DATE)) {
                return 1;
            } else {

                Date todayDate = new Date();
                long diff = date.getTime() - todayDate.getTime();
                System.out.println("Days: " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
                long noOfDays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                return (int) noOfDays;
            }
        }
    }

    public static String getDateStatusString(Date date) {
        int i = getDaysDifferenceFromDate(date);
        switch (i) {
            case -1:
                return "Happened yesterday";
            case 0:
                return "Happening TODAY";
//            case 1:
//                return "1 day to go";
        }

        if (i > 0) {
            return i + " days to go";
        } else {
            return "Happened " + (i * -1) + " days back";
        }
    }


    public static String getStringFromDate(Date date) {
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
