package com.petelevator.crm;

import java.util.ArrayList;
import java.util.List;

public class Pet {

    private String name;
    private String species;
    private List<String> vaccinations;

    public Pet(String name, String species) {
        this.name = name;
        this.species = species;
        vaccinations = new ArrayList<>();
    }

    public String listVaccinations() {
        String vaccString = "";
        for (String vaccination : vaccinations) {
            vaccString += vaccination;
            if (vaccinations.indexOf(vaccination) < vaccinations.size() -1) {
                vaccString += ", ";
            }
        }

        return vaccString;
    }

    public void addVaccination(String newVaccination) {
        vaccinations.add(newVaccination);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public List<String> getVaccinations() {
        return vaccinations;
    }

}
