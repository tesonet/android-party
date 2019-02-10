package com.example.partyapp.Database;

import com.example.partyapp.Models.Server;
import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

public class DatabaseConfigUtil extends OrmLiteConfigUtil {
    private static final Class<?>[] classes = new Class[]{
            Server.class
    };

    public static void main(String[] args) throws Exception {
        writeConfigFile("ormlite_config.txt");
    }
}
