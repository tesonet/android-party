package com.yegor.tesonet.partyapp;

import com.yegor.tesonet.partyapp.networking.ServerApi;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * Test example
 */
public class ServerApiTests {

    @Test
    public void isTodayTest() {
        ServerApi api = new ServerApi();
        long now = System.currentTimeMillis();
        Assert.assertTrue(api.isToday(now));
        Assert.assertFalse(api.isToday(now + TimeUnit.DAYS.toMillis(1)));
        Assert.assertFalse(api.isToday(0));
    }
}
