package org.oszimt.datengeneratorserver.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Land {

    private ArrayList<Vornamen> vornamen;
    private Namen namen;
    private String landName;

    public Land(ArrayList<Vornamen> vornamen,Namen namen,String landName) {
        this.vornamen = vornamen;
        this.namen = namen;
        this.landName = landName;
    }


    public ArrayList<Vornamen> getVornamen() {
        return vornamen;
    }

    public void setVornamen(ArrayList<Vornamen> vornamen) {
        this.vornamen = vornamen;
    }

    public Namen getNamen() {
        return namen;
    }

    public void setNamen(Namen namen) {
        this.namen = namen;
    }

    public String getLandName() {
        return landName;
    }

    public void setLandName(String landName) {
        this.landName = landName;
    }
}