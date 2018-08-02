package com.axborn.androidparty.utils;

import com.axborn.androidparty.features.feed.Sorter;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class SorterTest {

    private ArrayList<HashMap<String, String>> serverList = new ArrayList<>();

    @Before
    public void setup(){
        serverList = new ArrayList<>();

        HashMap<String, String> server = new HashMap<>();
        server.put("name", "BBB");
        server.put("distance", "111");

        serverList.add(server);

        server = new HashMap<>();
        server.put("name", "AAA");
        server.put("distance", "111");

        serverList.add(server);

        server = new HashMap<>();
        server.put("name", "CCC");
        server.put("distance", "111");

        serverList.add(server);
    }

    @Test
    public void testSortAscending() {

        Sorter.sortAscending(serverList);

        assertEquals("AAA", serverList.get(0).get("name"));
        assertEquals("BBB", serverList.get(1).get("name"));
        assertEquals("CCC", serverList.get(2).get("name"));
    }

    @Test
    public void testSortDescending() {

        Sorter.sortDescending(serverList);

        assertEquals("CCC", serverList.get(0).get("name"));
        assertEquals("BBB", serverList.get(1).get("name"));
        assertEquals("AAA", serverList.get(2).get("name"));
    }

    @Test
    public void testSortAscendingNull() {
        Sorter.sortAscending(null);
    }

    @Test
    public void testSortDescendingNull() {
        Sorter.sortDescending(null);
    }

    @Test
    public void testSortAscendingEmpty() {
        Sorter.sortAscending(new ArrayList<HashMap<String, String>>());
    }

    @Test
    public void testSortDescendingEmpty() {
        Sorter.sortDescending(new ArrayList<HashMap<String, String>>());
    }

}
