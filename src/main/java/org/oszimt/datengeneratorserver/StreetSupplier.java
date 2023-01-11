package org.oszimt.datengeneratorserver;

import jakarta.enterprise.context.ApplicationScoped;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

@ApplicationScoped
public class StreetSupplier {

    private final ArrayList<String> strassen = new ArrayList<>();
    private final ArrayList<String> orte = new ArrayList<>();

    public StreetSupplier() {
        try (BufferedReader readerStreet = Files.newBufferedReader(Paths.get(".","zuordnung_plz_ort.csv"))){
            String line = readerStreet.readLine();
            while((line = readerStreet.readLine()) != null) {
                String[] components = line.split(",");
                String ort = components[3] + components[2] + components[5];
                orte.add(ort);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }

        try (BufferedReader readerStreet = Files.newBufferedReader(Paths.get(".","Strassennamen.txt"))){
            String line = readerStreet.readLine();
            while((line = readerStreet.readLine()) != null) {
                strassen.add(line);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

}