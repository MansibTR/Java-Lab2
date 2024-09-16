package ca.bcit.comp2522.fantasycreatures;

/**
 * Date objects contain a year, month, and day.
 * Can display date and determine day of the week.
 *
 * @author Ben Henry
 * @version 0.1
 */
public class Date {
    private final static int MIN_DATE_VAL = 1;
    private final static int MIN_YEAR_VAL = 1800;
    private final static int CURRENT_YEAR = 2024;

    private final static int EIGHTEEN_HUNDREDS = 1800;
    private final static int EIGHTEENS_MOD = 2;

    private final static int TWO_THOUSANDS = 2000;
    private final static int TWO_THOUSANDS_MOD = 6;

    private final static int LEAP_INTERVAL = 4;
    private final static int LEAP_MOD = 6;

    private final static int DAYS_PER_WEEK = 7;
    private final static int MONTHS_PER_YEAR = 12;
    private final static int YEARS_PER_CENTURY = 100;

    private final static int REMAINDER_ZERO = 0;



    private final int year;
    private final Month monthEnum;
    private final int day;


    private enum Month {
        INVALID         (0, 0, 0, "Invalid month"),
        FEBRUARY_LEAP   (2, 29, 4, "February"),
        JANUARY         (1, 31, 1, "January"),
        FEBRUARY        (2, 28, 4, "February"),
        MARCH           (3, 31, 4, "March"),
        APRIL           (4, 30, 0, "April"),
        MAY             (5, 31, 2, "May"),
        JUNE            (6, 30, 5, "June"),
        JULY            (7, 31, 0, "July"),
        AUGUST          (8, 31, 3, "August"),
        SEPTEMBER       (9, 30, 6, "September"),
        OCTOBER         (10, 31, 1, "October"),
        NOVEMBER        (11, 30, 4, "November"),
        DECEMBER        (12, 31, 6, "December");

        final int numeric;
        final int numberOfDays;
        final int monthCode;
        final String monthText;


        Month(int numeric, int numberOfDays, int monthCode, String monthText) {
            this.numeric = numeric;
            this.numberOfDays = numberOfDays;
            this.monthCode = monthCode;
            this.monthText = monthText;
        }
    }

    private enum DayOfTheWeek {
        SATURDAY    (0, "Saturday"),
        SUNDAY      (1, "Sunday"),
        MONDAY      (2, "Monday"),
        TUESDAY     (3, "Tuesday"),
        WEDNESDAY   (4, "Wednesday"),
        THURSDAY    (5, "Thursday"),
        FRIDAY      (6, "Friday"),;

        final int dayCode;
        final String dayText;

        DayOfTheWeek(int dayCode, String dayText) {
            this.dayCode = dayCode;
            this.dayText = dayText;

        }
    }

    /**
     * Constructs a date object
     * @param year A year between 1800 and the current year
     * @param month A month, represented by an integer between 1 (January) and 12 (December)
     * @param day A day, valid for the given month/year 1-28/29/30/31
     */
    public Date(final int year, final int month, final int day) {
        Month curMonth;

        curMonth = Month.INVALID;

        for (final Month m : Month.values()) {
            if (m.numeric == month) {
                curMonth = m;
            }
        }
        if (leapCheck(year) && curMonth == Month.FEBRUARY) {
            curMonth = Month.FEBRUARY_LEAP;
        }

        validateYear(year);
        validateMonth(month);
        validateDay(day, curMonth);

        this.year = year;
        this.monthEnum = curMonth;
        this.day = day;
    }


    private static void validateYear(final int year) {
        if (year < MIN_YEAR_VAL || year > CURRENT_YEAR) {
            throw new IllegalArgumentException("Invalid Year, must be between "
                    + MIN_YEAR_VAL + " and " + CURRENT_YEAR);
        }
    }

    private static void validateMonth(final int month) {
        if (month < MIN_DATE_VAL || month > MONTHS_PER_YEAR) {
            throw new IllegalArgumentException("Invalid Month, must be between " +
                    MIN_DATE_VAL + " and " + MONTHS_PER_YEAR);
        }
    }

    private boolean leapCheck(final int year) {
        return (year % LEAP_INTERVAL == REMAINDER_ZERO); //no remainder
    }

    /*
    * Validate that the day is acceptable for the given month (i.e. no 30th day in February)
     */
    private static void validateDay(final int day, Month monthEnum) {
        if (day < MIN_DATE_VAL || day > monthEnum.numberOfDays) {
            throw new IllegalArgumentException("Invalid Day, must be between" +
                    MIN_DATE_VAL+ " and " + monthEnum.numberOfDays);
        }
    }

    private Month getMonth() {
        return monthEnum;
    }

    /**
     * Returns the day of this Date
     * @return the day
     */
    public int getDay() {
        return day;
    }

    /**
     * Returns a String representing the Month of this Date
     * @return the month as a String
     */
    public String getMonthText(){
        return monthEnum.monthText;
    }

    /**
     * Returns the year of this Date
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * Returns the date as a String in the format YYYY-MM-DD
     * (e.g. January 2nd, 2003 becomes 2003-01-02
     * @return the formatted date String
     */
    public String getYYYYMMDD() {
        return getYear() + "-" + String.format("%02d", getMonth().numeric) + "-" + String.format("%02d", getDay());
    }

    /**
     * Returns a Date string in the format "Monday, January 1, 2000"
     * @return The formatted date string
     */
    public String dateFormatted(){
        final StringBuilder sb;
        final String s;

        sb = new StringBuilder();
        sb.append(this.getDayOfTheWeek());
        sb.append(", ");
        sb.append(this.getMonthText());
        sb.append(" ");
        sb.append(this.getDay());
        sb.append(", ");
        sb.append(this.getYear());
        s = sb.toString();

        return s;
    }


    /**
     * To get the day of the week, do the following seven steps for dates in the 1900s:
     * e.g. October 31, 1977:
     * step 1: calculate the number of twelves in 77:
     * 6
     * step 2: calculate the remainder from step 1: 77 - 12*6 = 77 - 72 =
     * 5
     * step 3: calculate the number of fours in step 2: 5/4 = 1.25, so
     * 1
     * step 4: add the day of the month to each step above: 31 + 6 + 5 + 1 =
     * 43
     * step 5: add the month code (for jfmamjjasond: 144025036146): for october it is 1: 43 + 1 =
     * 44
     * step 6: add the previous five numbers: (44) and mod by 7: 44%7 = 2 (44/7 = 6 remainder 2)
     * step 7: sat sun mon tue wed thu fri is 0 1 2 3 4 5 6; our 2 means Oct 31 1977 was monday
     * Extra notes:
     * a) for January/February dates in leap years, add 6 at the start
     * b) for all dates in the 2000s, add 6 at step 5
     * c) for all dates in the 1800s, add 2 at step 5
     *
     * @return The day of the week
     */
    public String getDayOfTheWeek() {

        int curYear;
        int curCentury;
        int centuryMod;
        int leapMod;

        int twelves;
        int twelvesRemainder;
        int fours;
        int weekdayValue;

        int initValue;

        curYear = this.getYear();
        curCentury = curYear/ YEARS_PER_CENTURY;
        initValue = curYear%(curCentury* YEARS_PER_CENTURY);

        centuryMod = switch (curCentury) {
            case EIGHTEEN_HUNDREDS/ YEARS_PER_CENTURY -> EIGHTEENS_MOD;
            case TWO_THOUSANDS/ YEARS_PER_CENTURY -> TWO_THOUSANDS_MOD;
            default -> 0;
        };

        if (this.leapCheck(curYear) &&
                (this.getMonth().numeric == Month.JANUARY.numeric ||
                        this.getMonth().numeric == Month.FEBRUARY.numeric)) {
            leapMod = LEAP_MOD;
        } else {
            leapMod = 0;
        }

        //Step 1
        twelves = (initValue/MONTHS_PER_YEAR);
        //Step 2
        twelvesRemainder = initValue - twelves*MONTHS_PER_YEAR;
        //Step 3
        fours = twelvesRemainder/LEAP_INTERVAL;
        //Step 4
        weekdayValue = this.getDay()+twelves+twelvesRemainder+fours;
        //Step 5
        weekdayValue += (this.getMonth().monthCode + leapMod + centuryMod);
        //Step 6
        weekdayValue %= DAYS_PER_WEEK;

        return DayOfTheWeek.values()[weekdayValue].dayText;
    }
}
