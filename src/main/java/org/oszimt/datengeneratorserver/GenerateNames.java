package org.oszimt.datengeneratorserver;



import org.oszimt.datengeneratorserver.model.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class GenerateNames {

    static Map<String,String> vornamenMapping = new HashMap<>();
    static Map<String,String> namenMapping = new HashMap<>();

    static {
        vornamenMapping.put("Europa","Vereinigtes Königreich");
        vornamenMapping.put("Afrika","Marokko");
        vornamenMapping.put("Amerikas","USA");
        vornamenMapping.put("Ozeanien","Australien");
        vornamenMapping.put("Asien","China");

        namenMapping.put("Afrika","Frankreich");
        namenMapping.put("Europa","Vereinigtes Königreich");
        namenMapping.put("Amerikas","USA");
        namenMapping.put("Ozeanien","Vereinigtes Königreich");
        namenMapping.put("Asien","China");
    }

    static Optional<String> getRegionFromCountry(List<Region> regions, Land chosenCountry){
        for(Region regionObj : regions){
            for(Land landObj : regionObj.getLaender()){
                if (landObj.getLandName().equals(chosenCountry.getLandName())){
                    return Optional.of(regionObj.getName());
                }
            }
        }
        return Optional.empty();
    }

    public static String[] generateNames(List<Region> regions,Land chosenCountry, int n, boolean female) {
        String[] namen = new String[n];
        List<String> vornamen = null;
        if(!chosenCountry.getVornamen().isEmpty()) {
            List<Vornamen> filteredList = chosenCountry.getVornamen().stream().filter(v -> v.getFemale() == female).collect(Collectors.toList());
            if(filteredList.size() == 1){
                vornamen = filteredList.get(0).getVornamen();
            }
        }else{
            Optional<String> regionName = getRegionFromCountry(regions,chosenCountry);
            if(regionName.isPresent()) {
                String replacementCountry = vornamenMapping.get(regionName.get());
                for(Region regionObj : regions){
                    for(Land landObj : regionObj.getLaender()){
                        if (landObj.getLandName().equals(replacementCountry)){
                            List<Vornamen> filteredList = landObj.getVornamen().stream().filter(v -> v.getFemale() == female).collect(Collectors.toList());
                            if(filteredList.size() == 1){
                                vornamen = filteredList.get(0).getVornamen();
                                break;
                            }
                        }
                    }
                }
            }
        }

        List<String> nachnamen = null;
        if(chosenCountry.getNamen() != null) {
            nachnamen = chosenCountry.getNamen().getNamen();
        }else{
            Optional<String> regionName = getRegionFromCountry(regions,chosenCountry);
            if(regionName.isPresent()) {
                String replacementCountry = namenMapping.get(regionName.get());
                for(Region regionObj : regions){
                    for(Land landObj : regionObj.getLaender()){
                        if (landObj.getLandName().equals(replacementCountry)){
                            nachnamen = landObj.getNamen().getNamen();
                            break;
                        }
                    }
                }
            }
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