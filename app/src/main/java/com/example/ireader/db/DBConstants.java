package com.example.ireader.db;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by ChenR on 2017/9/28.
 */

public class DBConstants {

    public static final Set<String> DB_TABLE_TYPE_VALUES = new HashSet<String>(){
        {
            add("TXT");
            add("ePub");
            add("chm");
            add("html");
            add("mobi");
            add("umd");
            add("pdf");
        }
    };

}
