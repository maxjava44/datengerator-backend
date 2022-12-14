package org.oszimt.datengeneratorserver.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.io.BufferedReader;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Region {
  private String name;
  private ArrayList<Land> laender;

  public Region(String ordnerPfad) {
      File regionsOrder = new File(ordnerPfad);
      String[] path_componenten = regionsOrder.getName().split(File.pathSeparator);
      name = path_componenten[path_componenten.length-1];
      HashMap<String,ArrayList<File>> laenderFiles = new HashMap<>();
      for(File file : regionsOrder.listFiles()) {
        String landName = file.getName().split("_")[0];
        laenderFiles.put(landName,laenderFiles.getOrDefault(landName,new ArrayList<>()));
        laenderFiles.get(landName).add(file);
      }

      laender = new ArrayList<>();

      for(String land : laenderFiles.keySet()){
          ArrayList<Vornamen> vornamen = new ArrayList<>();
          Namen namen = null;
          for(File file : laenderFiles.get(land)){
              ArrayList<String> unkategorizedNames = new ArrayList<>();
              try (BufferedReader reader = Files.newBufferedReader(file.toPath())){
                  String line;
                  while ((line=reader.readLine()) != null){
                      if(!line.equals("")){
                          unkategorizedNames.add(line.trim());
                      }

                }
              }catch (IOException e) {
                  e.printStackTrace();
              }

              if(!file.getName().contains("DS_Store") && file.getName().split("_")[1].startsWith("Vorname")){
                  vornamen.add(new Vornamen(unkategorizedNames,file.getName().split("_")[2].startsWith("Female")));
              }else {
                  namen = new Namen(unkategorizedNames);
              }

          }
          laender.add(new Land(vornamen,namen,land));
      }
  }

    public String getName() {
        return name;
    }

    public ArrayList<Land> getLaender() {
        return laender;
    }
}