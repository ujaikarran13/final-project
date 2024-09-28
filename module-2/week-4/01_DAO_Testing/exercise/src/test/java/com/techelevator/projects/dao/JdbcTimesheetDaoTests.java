package com.techelevator.projects.dao;

import com.techelevator.projects.model.Timesheet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class JdbcTimesheetDaoTests extends BaseDaoTests {

    private static final Timesheet TIMESHEET_1 = new Timesheet(1, 1, 1,
            LocalDate.parse("2021-01-01"), 1.0, true, "Timesheet 1");
    private static final Timesheet TIMESHEET_2 = new Timesheet(2, 1, 1,
            LocalDate.parse("2021-01-02"), 1.5, true, "Timesheet 2");
    private static final Timesheet TIMESHEET_3 = new Timesheet(3, 2, 1,
            LocalDate.parse("2021-01-01"), 0.25, true, "Timesheet 3");
    private static final Timesheet TIMESHEET_4 = new Timesheet(4, 2, 2,
            LocalDate.parse("2021-02-01"), 2.0, false, "Timesheet 4");

    private JdbcTimesheetDao dao;


    @Before
    public void setup() {
        dao = new JdbcTimesheetDao(dataSource);
    }

    @Test
    public void getTimesheetById_with_valid_id_returns_correct_timesheet() {
        int timesheetId = TIMESHEET_1.getTimesheetId();
        Timesheet timesheet = dao.getTimesheetById(timesheetId);
        assertNotNull("Timesheet should not be null", timesheet);
        assertTimesheetsMatch(TIMESHEET_1, timesheet);
    }

    @Test
    public void getTimesheetById_with_invalid_id_returns_null_timesheet() {
        int invalidId = -1;
        Timesheet timesheet = dao.getTimesheetById(invalidId);
        assertNull("Timesheet should be null", timesheet);

    }

    @Test
    public void getTimesheetsByEmployeeId_with_valid_employee_id_returns_list_of_timesheets_for_employee() {
        int employeeId = TIMESHEET_1.getEmployeeId();
        List<Timesheet> timesheets = dao.getTimesheetsByEmployeeId(employeeId);
        assertFalse("Timesheets should not be empty", timesheets.isEmpty());
        assertTrue("Timesheets should be valid for employee",timesheets.size() > 0 );
        assertTrue("Timesheets should contain timesheet 1", timesheets.contains(TIMESHEET_1));
        assertTrue("Timesheets should contain timesheet 2", timesheets.contains(TIMESHEET_2));

    }

    @Test
    public void getTimesheetsByEmployeeId_with_invalid_employee_id_returns_empty_list_of_timesheets() {
        int invalidEmployeeId = -1;
        List<Timesheet> timesheets = dao.getTimesheetsByEmployeeId(invalidEmployeeId);
        Assert.assertNotNull("Timesheets list should not be null", timesheets);
        Assert.assertTrue("Timesheets list should be empty", timesheets.isEmpty());
    }

    @Test
    public void getTimesheetsByProjectId_with_valid_project_id_returns_list_of_all_timesheets_for_project() {
        int projectId = TIMESHEET_1.getProjectId();
        List<Timesheet> timesheets = dao.getTimesheetsByProjectId(projectId);
        assertNotNull("Timesheets list should not be null", timesheets);
        Assert.assertTrue("Timesheets list should contain timesheets", timesheets.size() > 0);
        Assert.assertTrue("Timesheets list should contain timesheet 1", timesheets.contains(TIMESHEET_1));
        Assert.assertTrue("Timesheets list should contain timesheet 2", timesheets.contains(TIMESHEET_2));
        Assert.assertTrue("Timesheets list should contain timesheet 3", timesheets.contains(TIMESHEET_3));
    }

    @Test
    public void getTimesheetsByProjectId_with_invalid_project_id_returns_empty_list_of_timesheets() {
        int invalidProjectId = -1;
        List<Timesheet> timesheets = dao.getTimesheetsByProjectId(invalidProjectId);
        Assert.assertNotNull("Timesheets list should not be null", timesheets);
        Assert.assertTrue("Timesheets list should be empty", timesheets.isEmpty());
    }

    @Test
    public void createTimesheet_creates_timesheet() {
        Timesheet newTimesheet = new Timesheet();
        newTimesheet.setEmployeeId(1);
        newTimesheet.setProjectId(1);
        newTimesheet.setDateWorked(LocalDate.now());
        newTimesheet.setHoursWorked(4.0);
        newTimesheet.setBillable(true);
        newTimesheet.setDescription("Created Timesheet");

        Timesheet createdTimesheet = dao.createTimesheet(newTimesheet);

        assertNotNull("Created timesheet should not be null", createdTimesheet);
        assertNotEquals("Created timesheet should have a non-zero ID", 0, createdTimesheet.getTimesheetId());
        assertTimesheetsMatch(newTimesheet, createdTimesheet);
    }

    @Test
    public void updateTimesheet_updates_timesheet() {
        Timesheet existingTimesheet = dao.getTimesheetById(TIMESHEET_1.getTimesheetId());
        existingTimesheet.setDescription("Updated description");

        Timesheet updatedTimesheet = dao.updateTimesheet(existingTimesheet);

        assertNotNull("Updated timesheet should not be null", updatedTimesheet);
        Assert.assertEquals("Description should be updated", "Updated description", updatedTimesheet.getDescription());
    }

    @Test
    public void deleteTimesheetById_deletes_timesheet() {
        int timesheetId = TIMESHEET_1.getTimesheetId();

        int rowsDeleted = dao.deleteTimesheetById(timesheetId);

        Assert.assertEquals("Expected one row to be deleted", 1, rowsDeleted);
        Assert.assertNull("Timesheet should be null after deletion", dao.getTimesheetById(timesheetId));
    }

    @Test
    public void getBillableHours_returns_correct_total() {
        int employeeId = 1;
        int projectId = 1;
        double expectedBillableHours = 2.5;

        double billableHours = dao.getBillableHours(employeeId, projectId);

        Assert.assertEquals("Billable hours should match expected value", expectedBillableHours, billableHours, 0.001);

    }

    private void assertTimesheetsMatch(Timesheet expected, Timesheet actual) {
        Assert.assertEquals(expected.getTimesheetId(), actual.getTimesheetId());
        Assert.assertEquals(expected.getEmployeeId(), actual.getEmployeeId());
        Assert.assertEquals(expected.getProjectId(), actual.getProjectId());
        Assert.assertEquals(expected.getDateWorked(), actual.getDateWorked());
        Assert.assertEquals(expected.getHoursWorked(), actual.getHoursWorked(), 0.001);
        Assert.assertEquals(expected.isBillable(), actual.isBillable());
        Assert.assertEquals(expected.getDescription(), actual.getDescription());
    }



}
