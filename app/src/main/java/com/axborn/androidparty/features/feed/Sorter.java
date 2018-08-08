package com.axborn.androidparty.features.feed;

import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Sorter {

    public static ArrayList<HashMap<String, String>> retrieveList(ListView viewById){
        ArrayList<HashMap<String, String>> serverList = new ArrayList<>();

        ListAdapter adapter = viewById.getAdapter();
        for (int i = 0; i < adapter.getCount(); i++){
            serverList.add((HashMap<String, String>) adapter.getItem(i));
        }

        return serverList;
    }

    public static void sortAscending (ArrayList<HashMap<String, String>> serverList){
        if (serverList == null || serverList.isEmpty()) {
            return;
        }
        Collections.sort(serverList, new Comparator<Map<String, String>>() {

            public int compare(Map<String, String> first,
                               Map<String, String> second)
            {
                String firstValue = first.get("name");
                String secondValue = second.get("name");
                return nullSafeStringComparator(firstValue, secondValue);
            }
        });
    }

    public static void sortDescending (ArrayList<HashMap<String, String>> serverList){
        if (serverList == null || serverList.isEmpty()) {
            return;
        }

        Collections.sort(serverList, new Comparator<Map<String, String>>() {

            public int compare(Map<String, String> first,
                               Map<String, String> second)
            {
                String firstValue = first.get("name");
                String secondValue = second.get("name");
                return nullSafeStringComparator(secondValue, firstValue);
            }
        });

    }

    private static int nullSafeStringComparator(final String one, final String two) {
        if (one == null ^ two == null) {
            return (one == null) ? -1 : 1;
        }

        if (one == null && two == null) {
            return 0;
        }

        return one.compareToIgnoreCase(two);
    }

}
