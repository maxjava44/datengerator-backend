package org.oszimt.datengeneratorserver;



import org.oszimt.datengeneratorserver.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GenerateNames {

    

    public static String[] generateNames(Land chosenCountry, int n, boolean female) {
        String[] namen = new String[n];
        List<String> vornamen = null;
        if(chosenCountry.getVornamen() != null) {
            List<Vornamen> filteredList = chosenCountry.getVornamen().stream().filter(v -> v.getFemale() == female).collect(Collectors.toList());
            if(filteredList.size() == 1){
                vornamen = filteredList.get(0).getVornamen();
            }
        }

        List<String> nachnamen = null;
        if(chosenCountry.getNamen() != null) {
            nachnamen = chosenCountry.getNamen().getNamen();
        }

        if(vornamen == null && nachnamen == null) {
            return new String[0];
        }

        for (int i = 0;i<n;i++){
            if(vornamen != null) {
                namen[i] = vornamen.get((int) (Math.random() * vornamen.size()));
            }
            if(nachnamen != null && vornamen != null) {
                namen[i] += " " + nachnamen.get((int) (Math.random() * nachnamen.size()));
            }else if(nachnamen != null){
                namen[i] = nachnamen.get((int) (Math.random() * nachnamen.size()));
            }
        }

        return namen;


    }
}