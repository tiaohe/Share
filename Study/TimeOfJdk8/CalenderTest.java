package TimeOfJdk8;

import java.util.Calendar;



public class CalenderTest {
    public static void main(String[] args) {
        Calendar calender = Calendar.getInstance();
        System.out.println(calender.get(Calendar.YEAR));

        System.out.println(calender.get(Calendar.MONTH));
        System.out.println(calender.get(Calendar.DATE + 1));
        System.out.println(calender.get(Calendar.DAY_OF_MONTH));
        System.out.println(calender.get(Calendar.DAY_OF_WEEK - 1));
        calender.add(Calendar.YEAR,1);
        calender.add(Calendar.MONTH,1);
        calender.roll(Calendar.MONTH,1);
        calender.set(2020,1,7);
    }
}
