package CarShop;


public class Data {

    public Data(){

    }

    public String time (int dayAmount) {

        int days = (getDate(dayAmount));
        int year = currentYear(days);
        int dayInYear = currentDayInYear(days);

        String[][] months = { { "January", "31" }, { "February", "28" },
                { "March", "31" }, { "April", "30" }, { "May", "31" },
                { "June", "30" }, { "July", "31" }, { "August", "31" },
                { "September", "30" }, { "October", "31" },
                { "November", "30" }, { "December", "31" } };

        if (year % 4 == 0) {
            months[1][1] = "29";
        }

        return(data(months, dayInYear) + " " + year);

    }

    private int currentDayInYear(int days) {
        int dayInYear = days % 1461;
        dayInYear = dayInYear - (dayInYear / 365) * 365;
        return dayInYear;
    }

    private  int currentYear(int days) {
        int year = 1970;
        year = year + days / 1461 * 4 + days % 1461 / 365;
        return year;
    }

    public int getDate(int dayCount) {
        long time = System.currentTimeMillis();
        time = time-dayCount*(1000*60*60*24);
        long sec = time / 1000;
        long min = (sec / 60);
        int hour = (int) min / 60;
        int days = hour / 24;
        return days;
    }

    private  String data(String[][] months, int dayInYear) {
        int day = 0;
        String month = null;
        if (dayInYear >= 31) {
            for (int i = 0; i < months.length
                    && dayInYear >= Integer.parseInt(months[i][1]); i++) {

                dayInYear = dayInYear - Integer.parseInt(months[i][1]);

                month = months[i + 1][0];
            }
        } else {
            month = months[0][0];

        }
        day = dayInYear+1;
        String data = (day + " " + month);

        return data;

    }

}

