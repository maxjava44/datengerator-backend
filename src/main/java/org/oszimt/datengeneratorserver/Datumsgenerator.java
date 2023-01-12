import java.util.GregorianCalendar;

public class Datumsgenerator {

    public static void main(String[] args) {

        GregorianCalendar gc = new GregorianCalendar();

        int year = randBetween(2019, 2023);

        gc.set(gc.YEAR, year);

        int dayOfYear = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));

        gc.set(gc.DAY_OF_YEAR, dayOfYear);

        System.out.println(gc.get(gc.DAY_OF_MONTH) + "." + (gc.get(gc.MONTH) + 1) + "." + gc.get(gc.YEAR));

    }

    public static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }
}