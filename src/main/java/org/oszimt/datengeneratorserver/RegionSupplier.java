package org.oszimt.datengeneratorserver;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Singleton;
import org.oszimt.datengeneratorserver.model.Region;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

@ApplicationScoped
public class RegionSupplier {

    final private ArrayList<Region> regions;

    public RegionSupplier(){
        regions = new ArrayList<>();
        File[] regionFiles = Paths.get(".","regionen").toFile().listFiles(pathname -> !pathname.toString().contains(".DS_Store"));
        if(regionFiles != null){
            Arrays.stream(regionFiles).forEach(file -> regions.add(new Region(file.toPath().toAbsolutePath().normalize().toString())));
        }
    }


    public ArrayList<Region> getRegions() {
        return regions;
    }
}