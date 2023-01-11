package org.oszimt.datengeneratorserver.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Vornamen {
    private boolean female;
    private ArrayList<String> vornamen;

    public Vornamen(ArrayList<String> vornamen,boolean female) {
        this.female = female;
        this.vornamen = vornamen;
    }

    public boolean getFemale() {
        return female;
    }

    public void setFemale(boolean female) {
        this.female = female;
    }

    public ArrayList<String> getVornamen() {
        return vornamen;
    }

    public void setVornamen(ArrayList<String> vornamen) {
        this.vornamen = vornamen;
    }
}