package org.oszimt.datengeneratorserver;

import io.helidon.microprofile.cors.CrossOrigin;
import jakarta.inject.Inject;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonValue;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.headers.Header;
import org.oszimt.datengeneratorserver.model.Land;
import org.oszimt.datengeneratorserver.model.Region;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Path("/namen")
public class NamenGenerator {
    @Inject
    RegionSupplier supplier;

    @Inject
    StreetSupplier streetSupplier;

    @Inject
    GenerateEmail emailSupplier;

    @Inject
    Telefonnummer telNrSupplier;

    @Inject
    Datumsgenerator dateSupplier;

    @Path("/datum/{howMany}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> getDatum(@PathParam("howMany") int howMany) {
        if(howMany > 200){
            return new ArrayList<>();
        }
        return List.of(dateSupplier.genJahre(howMany));
    }

    @Path("/telnr/{howMany}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> getTel(@PathParam("howMany") int howMany) {
        if(howMany > 200){
            return new ArrayList<>();
        }
        return List.of(telNrSupplier.randomTelefonnummern(howMany));
    }

    @Path("/email/{howMany}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> getMail(@PathParam("howMany") int howMany) {
        if(howMany > 200){
            return new ArrayList<>();
        }
        return List.of(emailSupplier.generateRandomEmail(howMany));
    }


    @OPTIONS
    @CrossOrigin()
    public void optionsForRetrievingUnnamedGreeting() {}

    @Path("/regions")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> getRegions() {
        List<String> regionNames = new ArrayList<>();
        supplier.getRegions().forEach(r -> regionNames.add(r.getName()));
        return regionNames;
    }

    @Path("/street/{howMany}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> getStreets(@PathParam("howMany") int howMany) {
        if(howMany > 200){
            return new ArrayList<>();
        }
        return List.of(streetSupplier.getRandomStrassen(howMany));
    }

    @Path("/countries/{region}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> getCountries(@PathParam("region") String region){
        List<String> countryNames = new ArrayList<>();
        for(Region regionObj : supplier.getRegions()){
            if(regionObj.getName().equals(region)){
                regionObj.getLaender().forEach(land -> countryNames.add(land.getLandName()));
                return countryNames;
            }
        }
        return countryNames;
    }

    @Path("/{region}/{country}/{n}/{female}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String[] getNames(@PathParam("region") String region,
                             @PathParam("country") String country,
                             @PathParam("n") int n,
                             @PathParam("female") String female){
        if(n > 200){
            return new String[0];
        }
        List<String> names = new ArrayList<>();
        Land land = null;
        for(Region regionObj : supplier.getRegions()){
            if(regionObj.getName().equals(region)){
                for(Land landObj : regionObj.getLaender()){
                    if (landObj.getLandName().equals(country)){
                        land = landObj;
                    }
                }
            }
        }
        boolean isFemale = Boolean.parseBoolean(female);
        if(land != null) {
            return GenerateNames.generateNames(supplier.getRegions(),land,n,isFemale);
        }
            return new String[0];
    }

    @Path("/{country}/{n}/{female}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String[] getNamesWithoutGivingRegion(@PathParam("country") String country,@PathParam("n") int n,@PathParam("female") String female){
        List<String> names = new ArrayList<>();
        Land land = null;
        for(Region regionObj : supplier.getRegions()){
                for(Land landObj : regionObj.getLaender()){
                    if (landObj.getLandName().equals(country)){
                        land = landObj;
                        break;
                    }
                }
        }
        if(n > 200){
            return new String[0];
        }
        boolean isFemale = Boolean.parseBoolean(female);
        if(land != null) {
            return GenerateNames.generateNames(supplier.getRegions(),land,n,isFemale);
        }
        return new String[0];
    }

    @Path("/region/{country}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getRegionFromCountry(@PathParam("country") String country) {
        for(Region regionObj : supplier.getRegions()){
            for(Land landObj : regionObj.getLaender()){
                if (landObj.getLandName().equals(country)){
                    return regionObj.getName();
                }
            }
        }
        return "";
    }

    @Path("download")
    @POST
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getRegionFromCountry(ArrayList<ArrayList<String>> jsonObject) {
        StringBuilder result = new StringBuilder("Name;Addresse;E-Mail;Tel Nr.;Geburtsdatum\n");

        for (ArrayList<String> jsonArr : jsonObject) {
            for(String jsonContent : jsonArr) {
                jsonContent = jsonContent.replace("ü","ue").replace("ä","ae").replace("ö","oe").replace("ß","ss");
                result.append(jsonContent);
                result.append(";");
            }
            result.deleteCharAt(result.lastIndexOf(";"));
            result.append("\n");
        }


        return Response.ok(result.toString()).header("Content-Disposition","attachment; filename=\"data.csv\"; filename*=\"data.csv\"").build();
    }

    @Path("downloadalt")
    @POST
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response downloadalt(ArrayList<String> jsonObject) {
        StringBuilder result = new StringBuilder("Vorname;Nachname\n");

         for (String jsonname : jsonObject) {
             jsonname = jsonname.replace("ü","ue").replace("ä","ae").replace("ö","oe").replace("ß","ss");
             String[] gesamterName = jsonname.split(" ");
             String vorname = gesamterName[0];
             String name = gesamterName[1];
             result.append(vorname);
             result.append(";");
             result.append(name);
             result.append("\n");
        }


        return Response.ok(result.toString()).header("Content-Disposition","attachment; filename=\"data.csv\"; filename*=\"data.csv\"").build();
    }


}