package com.techelevator;

import com.techelevator.dao.*;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Campground;
import com.techelevator.model.Park;
import com.techelevator.model.Site;
import com.techelevator.util.BasicConsole;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

public class ApplicationController {

    private final String DB_NAME = "campground";

    private final BasicConsole console;
    private final ApplicationView view;

    private final ParkDao parkDao;
    private final CampgroundDao campgroundDao;
    private final SiteDao siteDao;

    private Park currentPark;
    private Campground  currentCampground;
    private Site currentSite;

    public ApplicationController(BasicConsole console) throws SQLException {

        this.console = console;
        // Set View for controller
        view = new ApplicationView(console);
        // Set DataSource for campground database and assign to all the JDBC-DAOs
        DataSource dataSource = setupDataSource(DB_NAME);
        parkDao = new JdbcParkDao(dataSource);
        campgroundDao = new JdbcCampgroundDao(dataSource);
        siteDao = new JdbcSiteDao(dataSource);
    }

    /**
     * This method is called to begin the main menu loop. It's usually called from the application's
     * entry-point method (e.g., main)
     */
    public void run() {
        displayMainMenu();
    }

    public void displayMainMenu() {

        final String PARK_SELECT = "Parks - select";
        final String CAMPGROUND_SELECT = "Campground - select";
        final String SITE_SELECT = "Site - select";
        final String SITES_ALLOW_RVS = "Query: Sites that allow RVs for selected park";
        final String AVAILABLE_SITES = "Query: Available sites for selected park";
        final String EXIT = "Exit the program";
        final String[] MENU_OPTIONS = {PARK_SELECT, CAMPGROUND_SELECT, SITE_SELECT, SITES_ALLOW_RVS,
                AVAILABLE_SITES, EXIT};

        console.printBlankLine();
        console.printMessage("Welcome to the National Parks application.");

        boolean finished = false;
        while (!finished) {
            console.printBlankLine();
            String mainMenuTitle = "Main Menu\n" + selectedBreadCrumbs();
            String selection = view.getMenuSelection(mainMenuTitle, MENU_OPTIONS);
            try {
                if (selection.equals(PARK_SELECT)) {
                    console.printBlankLine();
                    List<Park> parks = parkDao.getParks();
                    Park selectedPark = view.getParkSelection(parks);
                    if (selectedPark == null) {
                        // Deselect current park, campground and site
                        currentPark = null;
                        currentCampground = null;
                        currentSite = null;
                    } else if (selectedPark.equals(currentPark) == false) {
                        // Switch to selected park and deselect campground and site
                        currentPark = selectedPark;
                        currentCampground = null;
                        currentSite = null;
                    }
                } else if (selection.equals(CAMPGROUND_SELECT)) {
                    console.printBlankLine();
                    if (currentPark != null) {
                        List<Campground> campgrounds = campgroundDao.getCampgroundsByParkId(currentPark.getParkId());
                        Campground selectedCampground = view.getCampgroundSelection(campgrounds);
                        if (selectedCampground == null) {
                            // Deselect campground and site
                            currentCampground = null;
                            currentSite = null;
                        } else if (selectedCampground.equals(currentCampground) == false) {
                            // Switch to selected campground and deselect site
                            currentCampground = selectedCampground;
                            currentSite = null;
                        }
                    } else {
                        console.printErrorMessage("No park selected.");
                    }
                } else if (selection.equals(SITE_SELECT)) {
                    console.printBlankLine();
                    if (currentCampground != null) {
                        List<Site> sites = siteDao.getSitesByCampgroundId(currentCampground.getCampgroundId());
                        Site selectedSite = view.getSiteSelection(sites);
                        if (selectedSite == null) {
                            // Deselect site
                            currentSite = null;
                        } else if (selectedSite.equals(currentSite) == false) {
                            // Switch to selected
                            currentSite = selectedSite;
                        }
                    } else {
                        console.printErrorMessage("No campground selected.");
                    }
                } else if (selection.equals(SITES_ALLOW_RVS)) {
                    console.printBlankLine();
                    if (currentPark != null) {
                        List<Site> sites = siteDao.getSitesThatAllowRVsByParkId(currentPark.getParkId());
                        if (sites.size() > 0) {
                            console.printMessage("Sites that allow RVs by selected park");
                            console.printDivider();
                            for (Site site : sites) {
                                console.printMessage(site.toString());
                            }
                        } else {
                            console.printMessage("No sites that allow RVs found.");
                        }
                    } else {
                        console.printErrorMessage("No park selected.");
                    }
                } else if (selection.equals(AVAILABLE_SITES)) {
                    console.printBlankLine();
                    if (currentPark != null) {
                        List<Site> sites = siteDao.getAvailableSitesByParkId(currentPark.getParkId());
                        if (sites.size() > 0) {
                            console.printMessage("Available sites by selected park");
                            console.printDivider();
                            for (Site site : sites) {
                                console.printMessage(site.toString());
                            }
                        } else {
                            console.printMessage("No available sites found.");
                        }
                    }
                    else {
                        console.printErrorMessage("No park selected.");
                    }
                } else if (selection.equals(EXIT)) {
                    finished = true;
                }
            }
            catch (DaoException e) {
                console.printErrorMessage("DAO error - " + e.getMessage());
            }
        }
    }

    private DataSource setupDataSource(String databaseName) throws SQLException {

        // Drop the existing database under separate "admin" connection
        SingleConnectionDataSource adminDataSource = new SingleConnectionDataSource();
        adminDataSource.setUrl("jdbc:postgresql://localhost:5432/postgres");
        adminDataSource.setUsername("postgres");
        adminDataSource.setPassword("postgres1");
        JdbcTemplate adminJdbcTemplate = new JdbcTemplate(adminDataSource);
        adminJdbcTemplate.update("DROP DATABASE IF EXISTS \"" + DB_NAME + "\";");
        adminJdbcTemplate.update("CREATE DATABASE \"" + DB_NAME + "\";");
        adminJdbcTemplate = new JdbcTemplate(adminDataSource);
        adminJdbcTemplate.update("DROP DATABASE IF EXISTS \"" + DB_NAME + "\";");
        adminJdbcTemplate.update("CREATE DATABASE \"" + DB_NAME + "\";");
        adminDataSource.destroy();;

        // Setup up the "application" connection and refresh the database by running the setup script
        SingleConnectionDataSource dataSource = new SingleConnectionDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/" + databaseName);
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres1");
        ScriptUtils.executeSqlScript(dataSource.getConnection(), new ClassPathResource("campground.sql"));

        return dataSource;
    }

    private String selectedBreadCrumbs() {

        return "Selected: " + (currentPark != null ? currentPark.getName() : "---") + " >> " +
                (currentCampground != null ? currentCampground.getName() : "---") + " >> " +
                (currentSite != null ? currentSite.getSiteNumber() : "---");
    }
}
