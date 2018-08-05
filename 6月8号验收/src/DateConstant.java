/**
 * This is a class containing the relevant constant to deal with the date processing
 */
public class DateConstant {
    final static String[] monthString = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
    final static String[] dayString = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09"};

    final static int[] dayOfMonthNonLeap = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    final static int[] dayOfMonthLeap = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
}
