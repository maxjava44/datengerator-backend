package org.oszimt.datengeneratorserver.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Namen {

    private ArrayList<String> namen;

    public Namen(ArrayList<String> namen) {
        this.namen = namen;
    }


    public ArrayList<String> getNamen() {
        return namen;
    }

    public void setNamen(ArrayList<String> namen) {
        this.namen = namen;
    }
}