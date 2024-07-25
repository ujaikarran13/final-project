package com.techelevator;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
public class DateFashionTest {

    @Test
    public void will_Not_Get_A_Table() {
        // Act - call the constructor by creating a new object, passing valid parameters
        DateFashion dateFashion = new DateFashion();
        // Assert - verify the properties are set appropriately
        assertEquals(0, dateFashion.getATable(1, 3));
        assertEquals(0, dateFashion.getATable(3, 1));
        assertEquals(0, dateFashion.getATable(1, 1));
    }

    @Test
    public void will_Get_A_Table() {
        // Act - call the constructor by creating a new object, passing valid parameters
        DateFashion dateFashion = new DateFashion();
        // Assert - verify the properties are set appropriately
        assertEquals(2, dateFashion.getATable(8, 5));
        assertEquals(2, dateFashion.getATable(5, 8));
        assertEquals(2, dateFashion.getATable(8, 8));
    }

    @Test
    public void will_Maybe_Get_A_Table() {
        // Act - call the constructor by creating a new object, passing valid parameters
        DateFashion dateFashion = new DateFashion();
        // Assert - verify the properties are set appropriately
        assertEquals(1, dateFashion.getATable(4, 5));
        assertEquals(1, dateFashion.getATable(5, 4));
        assertEquals(1, dateFashion.getATable(5, 5));
    }
}