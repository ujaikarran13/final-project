package com.techelevator;

import com.techelevator.model.Campground;
import com.techelevator.model.Park;
import com.techelevator.model.Site;
import com.techelevator.util.BasicConsole;

import java.util.List;

public class ApplicationView {

    private final BasicConsole console;

    public ApplicationView(BasicConsole console) {
        this.console = console;
    }

    public String getMenuSelection(String menuTitle, String[] options) {
        console.printBanner(menuTitle);
        return console.getMenuSelection(options);
    }

    public Park getParkSelection(List<Park> parks) {
        Park selectedPark = null;

        if (parks.size() > 0) {
            console.printMessage("Park List");
            console.printDivider();
            String[] options = new String[parks.size()];
            for (int i = 0; i < parks.size(); i++) {
                options[i] = parks.get(i).toString();
            }
            Integer selection = console.getMenuSelectionIndex(options, true);
            if (selection != null) {
                selectedPark = parks.get(selection);
            }
        } else {
            console.printErrorMessage("No parks available.");
        }
        return selectedPark;
    }

    public Campground getCampgroundSelection(List<Campground> campgrounds) {
        Campground selectedCampground = null;

        if (campgrounds.size() > 0) {
            console.printMessage("Campground List");
            console.printDivider();
            String[] options = new String[campgrounds.size()];
            for (int i = 0; i < campgrounds.size(); i++) {
                options[i] = campgrounds.get(i).toString();
            }
            Integer selection = console.getMenuSelectionIndex(options, true);
            if (selection != null) {
                selectedCampground = campgrounds.get(selection);
            }
        } else {
            console.printErrorMessage("No campgrounds available.");
        }
        return selectedCampground;
    }

    public Site getSiteSelection(List<Site> sites) {
        Site selectedSite = null;

        if (sites.size() > 0) {
            console.printMessage("Site List");
            console.printDivider();
            String[] options = new String[sites.size()];
            for (int i = 0; i < sites.size(); i++) {
                options[i] = sites.get(i).toString();
            }
            Integer selection = console.getMenuSelectionIndex(options, true);
            if (selection != null) {
                selectedSite = sites.get(selection);
            }
        } else {
            console.printErrorMessage("No parks available.");
        }
        return selectedSite;
    }
}
