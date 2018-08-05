import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DateLogic {
    public MyDate getCurrentTime() {
        return new MyDate();
    }

    /**
     * This is the method to return the number of days between the two date
     * @param x start date
     * @param y end date
     * @return number of days between the two given date
     */
    public double DifferTransform2Day(MyDate x, MyDate y) {
        double answer;
        int xDay = calcDayOfYear(x.getYear(), x.getMonth(), x.getDate());
        int yDay = calcDayOfYear(y.getYear(), y.getMonth(), y.getDate());
        answer = (xDay - yDay) + (x.getHour() - y.getHour()) / 24.0 + (x.getMinute() - y.getMinute()) / 1440.0 + (x.getSecond() - y.getSecond()) / 86400.0;
        return answer;
    }

    /**
     * @param year given year
     * @param month given month
     * @param day given day
     * @return the correct format of the date
     */
    public String getFormat(int year,int month,int day){
        String dateString;
        String yearString = String.valueOf(year);
        String monthString = DateConstant.monthString[month];
        String dayString;
        if (day < 10) {
            dayString = DateConstant.dayString[day];
        }
        else {
            dayString = String.valueOf(day);
        }
        dateString = yearString + "-" + monthString + "-" + dayString;
        return dateString;
    }

    /**
     * @param year given year
     * @param month given month
     * @param day given day
     * @return what's the number of day in the year of the given date
     */
    public int calcDayOfYear(int year, int month, int day) {
        int sum = 0;
        boolean leap = false;

        switch(month)  {
            case 1:
                sum = 0;
                break;
            case 2:
                sum = 31;
                break;
            case 3:
                sum = 59;
                break;
            case 4:
                sum = 90;
                break;
            case 5:
                sum = 120;
                break;
            case 6:
                sum = 151;
                break;
            case 7:
                sum = 181;
                break;
            case 8:
                sum = 212;
                break;
            case 9:
                sum = 243;
                break;
            case 10:
                sum = 273;
                break;
            case 11:
                sum = 304;
                break;
            case 12:
                sum = 334;
                break;
            default:
                System.out.println("data error");
                break;
        }
        sum = sum + day;
        if(year%400==0 || (year%4==0&&year%100!=0)) leap = true;
        if (leap && month>2) sum++;
        return sum;
    }

    /**
     * @param year given year
     * @param month given month
     * @param day given date
     * @return return the week information of the given date
     */
    public int calcWeek(int year, int month, int day) { //calculate which day in a week
        int result;
        // complete date format :-<
        String dateString = getFormat(year,month,day);
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        Date tempDate;
        try {
            tempDate = f.parse(dateString);
            cal.setTime(tempDate);
        }
        catch (ParseException e){
            e.printStackTrace();
        }
        result = cal.get(Calendar.DAY_OF_WEEK) - 1;
        return  result;
    }

    /**
     * @param startDate start date
     * @param sustainedDay end date
     * @return a list of date
     */
    public ArrayList<MyDate> ergodicDate(MyDate startDate, int sustainedDay) {
        ArrayList<MyDate> myErgodicDate = new ArrayList<>();
        String formatDateStart = getFormat(startDate.getYear(),startDate.getMonth(),startDate.getDate());
        //String formatDateEnd = getFormat(endDate.getYear(),endDate.getMonth(),endDate.getDate());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date start = null;
        try {
            start = sdf.parse(formatDateStart);
            start = new Date(start.getTime() + 86400000);
            //end = sdf.parse(formatDateEnd);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        //Long spi = end.getTime() - start.getTime();
        //Long step = spi / (24 * 60 * 60 * 1000);
        // 相隔天数
        ArrayList<Date> dateList = new ArrayList<>();

        dateList.add(start);
        for (int i = 1; i <= sustainedDay; i++) {
            dateList.add(new Date(dateList.get(i - 1).getTime() + 86400000));
        }
        for (int i = 0; i < dateList.size() - 1; i++) {
            String tempDateString[] = sdf.format(dateList.get(i)).split("-");
            int tempYear = Integer.parseInt(tempDateString[0]);
            int tempMonth = Integer.parseInt(tempDateString[1]);
            int tempDate = Integer.parseInt(tempDateString[2]);
            MyDate tempMyDate = new MyDate(tempYear, tempMonth, tempDate,
                                            startDate.getHour(), startDate.getMinute(), startDate.getSecond());
            myErgodicDate.add(tempMyDate);
        }

        return myErgodicDate;
    }

    /**
     * @param year given year
     * @param month given month
     * @param date given date
     * @param hour given hour
     * @param minute given minute
     * @param second given second
     * @return the correct time format of the day
     */

    public int[] correctTime(int year, int month, int date, int hour, int minute, int second) {
        boolean isLeap = false;
        if ((year % 400 == 0) || (year%4==0) && (year%100!=0)) isLeap = true;
        int[] returnTimeArray = new int[7];
        if (second >= 60) {
            second -= 60;
            minute += 1;
        }
        if (minute >= 60) {
            minute -= 60;
            hour += 1;
        }

        if (hour >= 24) {
            hour -=24;
            date += 1;
        }

        if (isLeap) {
            if (date >= DateConstant.dayOfMonthLeap[month]) {
                date -= DateConstant.dayOfMonthLeap[month];
                month += 1;
            }
        }
        else {
            if (date > DateConstant.dayOfMonthNonLeap[month]) {
                date -= DateConstant.dayOfMonthNonLeap[month];
                month += 1;
            }
        }

        if (month > 12) {
            month -= 12;
            year += 1;
        }

        returnTimeArray[0] = year;
        returnTimeArray[1] = month;
        returnTimeArray[2] = date;
        returnTimeArray[3] = hour;
        returnTimeArray[4] = minute;
        returnTimeArray[5] = second;

        return returnTimeArray;
    }
}
