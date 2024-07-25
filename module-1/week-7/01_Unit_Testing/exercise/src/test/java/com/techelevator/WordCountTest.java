package com.techelevator;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class WordCountTest {

    private final WordCount wordCount = new WordCount();

    @Test
    public void getWord_Count_From_A_MapOf_Strings(){
        String[] words = {"yellow", "yellow", "green", "green", "yellow", "green"};
        Map<String, Integer> expected = Map.of("yellow",3, "green",3);
        assertEquals(expected,wordCount.getCount(words));
    }
    @Test
    public void getWord_Count_From_An_Empty_MapOf_Strings(){
        String[] words = {};
        Map<String, Integer> expected = Map.of();
        assertEquals(expected,wordCount.getCount(words));
    }
    @Test
    public void getWord_Count_From_One_Element_InMap() {
        String[] words = {"Blue"};
        Map<String, Integer> expected = Map.of("Blue", 1);
        assertEquals(expected, wordCount.getCount(words));
    }
}
