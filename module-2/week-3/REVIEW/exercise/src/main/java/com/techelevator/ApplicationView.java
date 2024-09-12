package com.techelevator;

import com.techelevator.model.Campground;
import com.techelevator.model.Park;
import com.techelevator.util.BasicConsole;

import java.math.BigDecimal;
import java.time.LocalDate;
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
                options[i] = parks.get(i).getName();
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

    public Park createPark() {

        Park newPark = null;

        console.printMessage("Enter new park information");
        console.printDivider();
        String name = console.promptForString("Name: ");
        String location = console.promptForString("Location: ");
        LocalDate establishDate = console.promptForLocalDate("Date established: ");
        int area = console.promptForInteger("Area: ");
        int visitors = console.promptForInteger("Number of yearly visitors: ");
        String description = console.promptForString("Description: ");
        console.printDivider();

        console.printBlankLine();
        boolean okToCreate = console.promptForYesNo("Are you sure you wish to create the new park? ");
        if (okToCreate) {
            newPark = new Park();
            newPark.setName(name);
            newPark.setLocation(location);
            newPark.setEstablishDate(establishDate);
            newPark.setArea(area);
            newPark.setVisitors(visitors);
            newPark.setDescription(description);
        }
        return newPark;
    }

    public void displayPark(Park park) {

        console.printMessage("Current park information");
        console.printDivider();
        console.printMessage("Name: " + park.getName());
        console.printMessage("Location: " + park.getLocation());
        console.printMessage("Date established: " + park.getEstablishDate());
        console.printMessage("Area: " + park.getArea());
        console.printMessage("Number of yearly visitor: " + park.getVisitors());
        console.printMessage("Description: " + park.getDescription());
    }

    public Park updatePark(Park park) {

        displayPark(park);
        console.printDivider();

        console.printBlankLine();
        console.printMessage("Update park information");
        console.printDivider();
        Park updatedPark = new Park();
        updatedPark.setParkId(park.getParkId());
        updatedPark.setName(defaultOnEnter(console.promptForString("Name: "), park.getName()));
        updatedPark.setLocation(defaultOnEnter(console.promptForString("Location: "), park.getLocation()));
        updatedPark.setEstablishDate(defaultOnEnter(console.promptForLocalDate("Date established: "), park.getEstablishDate()));
        updatedPark.setArea(defaultOnEnter(console.promptForInteger("Area: "), park.getArea()));
        updatedPark.setVisitors(defaultOnEnter(console.promptForInteger("Number of yearly visitors: "), park.getVisitors()));
        updatedPark.setDescription(defaultOnEnter(console.promptForString("Description: "), park.getDescription()));
        console.printDivider();

        console.printBlankLine();
        console.printMessage("Updated park information");
        displayPark(updatedPark);
        console.printDivider();

        console.printBlankLine();
        boolean okToUpdate = console.promptForYesNo("Are you sure you wish to update the park? ");
        if (! okToUpdate) {
            updatedPark = null;
        }
        return updatedPark;
    }

    public boolean deletePark(Park park) {
        displayPark(park);
        console.printDivider();

        console.printBlankLine();
        return console.promptForYesNo("Are you sure you wish to delete the park? ");
    }

    public Campground getCampgroundSelection(List<Campground> campgrounds) {
        Campground selectedCampground = null;
        if (campgrounds.size() > 0) {
            console.printMessage("Campground List");
            console.printDivider();
            String[] options = new String[campgrounds.size()];
            for (int i = 0; i < campgrounds.size(); i++) {
                options[i] = campgrounds.get(i).getName();
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

    public Campground createCampground(Park park) {

        Campground newCampground = null;

        console.printMessage("Enter new campground information");
        console.printDivider();
        String name = console.promptForString("Name: ");
        int openFromMonth = console.promptForInteger("Open from month: ");
        int openToMonth = console.promptForInteger("Open to month: ");
        BigDecimal dailyFee = console.promptForBigDecimal("Daily fee: ");
        console.printDivider();

        console.printBlankLine();
        boolean createNewPark = console.promptForYesNo("Are you sure you wish to create the new campground? ");
        if (createNewPark) {
            newCampground = new Campground();
            newCampground.setParkId(park.getParkId());
            newCampground.setName(name);
            newCampground.setOpenFromMonth(openFromMonth);
            newCampground.setOpenToMonth(openToMonth);
            newCampground.setDailyFee(dailyFee);
        }
        return newCampground;
    }

    public void displayCampground(Campground campground) {
        console.printMessage("Current campground information");
        console.printDivider();
        console.printMessage("Name: " + campground.getName());
        console.printMessage("Open from month: " + campground.getOpenFromMonth());
        console.printMessage("Open to month: " + campground.getOpenToMonth());
        console.printMessage("Daily fee: " + campground.getDailyFee());

    }

    public Campground updateCampground(Campground campground) {

        displayCampground(campground);
        console.printDivider();

        console.printBlankLine();
        console.printMessage("Update campground information");
        console.printDivider();
        Campground updatedCampground = new Campground();
        updatedCampground.setCampgroundId(campground.getCampgroundId());
        updatedCampground.setParkId(campground.getParkId());
        updatedCampground.setName(defaultOnEnter(console.promptForString("Name: "), campground.getName()));
        updatedCampground.setOpenFromMonth(defaultOnEnter(console.promptForInteger("Open from month: "), campground.getOpenFromMonth()));
        updatedCampground.setOpenToMonth(defaultOnEnter(console.promptForInteger("Open to month: "), campground.getOpenToMonth()));
        updatedCampground.setDailyFee(defaultOnEnter(console.promptForBigDecimal("Daily fee: "), campground.getDailyFee()));
        console.printDivider();

        console.printBlankLine();
        console.printMessage("Updated campground information");
        displayCampground(updatedCampground);
        console.printDivider();

        console.printBlankLine();
        boolean okToUpdate = console.promptForYesNo("Are you sure you wish to update the campground? ");
        if (! okToUpdate) {
            updatedCampground = null;
        }
        return updatedCampground;
    }

    public boolean deleteCampground(Campground campground) {
        displayCampground(campground);
        console.printDivider();

        console.printBlankLine();
        return console.promptForYesNo("Are you sure you wish to delete the campground? ");
    }

    private String defaultOnEnter(String response, String defaultValue) {
        if (response.isBlank()) {
            return defaultValue;
        }
        else {
            return response;
        }
    }

    private LocalDate defaultOnEnter(LocalDate response, LocalDate defaultValue) {
        if (response == null) {
            return defaultValue;
        }
        else {
            return response;
        }
    }

    private Integer defaultOnEnter(Integer response, Integer defaultValue) {
        if (response == null) {
            return defaultValue;
        }
        else {
            return response;
        }
    }

    private BigDecimal defaultOnEnter(BigDecimal response, BigDecimal defaultValue) {
        if (response == null) {
            return defaultValue;
        }
        else {
            return response;
        }
    }
}
