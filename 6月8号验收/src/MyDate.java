import java.util.Calendar;

public class MyDate {
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    private int year, month, date, hour, minute, second;

    public MyDate() {
        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH) + 1;
        date = c.get(Calendar.DATE);
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);
        second = c.get(Calendar.SECOND);
    }

    public MyDate(int year, int month, int date, int hour, int minute, int second) {
        setYear(year);
        setMonth(month);
        setDate(date);
        setHour(hour);
        setMinute(minute);
        setSecond(second);
    }

    public MyDate(int year, int month, int date) {
        setYear(year);
        setMonth(month);
        setDate(date);
        setHour(12);
        setMinute(0);
        setSecond(0);
     }
}
