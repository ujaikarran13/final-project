package com.techelevator;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.Arrays;
import java.util.List;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class FinderTest {
    @BeforeEach
    void setUp(){
        Finder finder = new Finder();
    }
@Test
    public void return_the_largest_value_withA_Nonempty_list(){
    List<Integer> integers = Arrays.asList(1,2,3,4,5,6,7,8);
    Integer result = Finder.findLargest(integers);
    assertEquals(8,8, result);

}
    @Test
    public void return_the_largest_value_withOneValueList(){
        List<Integer> integers = Collections.singletonList(10);
        Integer result = Finder.findLargest(integers);
        assertEquals(10,10, result);

    }
    @Test
    public void return_the_largest_value_withEmptyList(){
        List<Integer> integers = Collections.emptyList();
        Integer result = Finder.findLargest(integers);
        assertNull(result);
    }
    @Test
    public void return_the_largest_value_withNegativeNumbers(){
        List<Integer> integers = Arrays.asList(-1, -2, -3, -4, -5);
        Integer result = Finder.findLargest(integers);
        assertEquals(-3, -3, result);
    }
}
