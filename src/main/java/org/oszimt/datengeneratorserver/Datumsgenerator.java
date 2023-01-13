package org.oszimt.datengeneratorserver;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.GregorianCalendar;

@ApplicationScoped

public class Datumsgenerator {

    public String[] genJahre(int n) {
        String[] jahre = new String[n];
        GregorianCalendar gc = new GregorianCalendar();

        for(int i = 0; i < n; i++) {
            int year = randBetween(1933, 2023);

            gc.set(GregorianCalendar.YEAR, year);

            int dayOfYear = randBetween(1, gc.getActualMaximum(GregorianCalendar.DAY_OF_YEAR));

            gc.set(GregorianCalendar.DAY_OF_YEAR, dayOfYear);

            jahre[i] = gc.get(GregorianCalendar.DAY_OF_MONTH) + "." + (gc.get(GregorianCalendar.MONTH) + 1) + "." + gc.get(GregorianCalendar.YEAR);
        }
        return jahre;
    }

    public int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }
}