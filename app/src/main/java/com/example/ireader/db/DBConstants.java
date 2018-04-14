package com.example.ireader.db;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by ChenR on 2017/9/28.
 */

public class DBConstants {
    public static final String BOOK_FILE_TYPE_UNKNOWN = "unknown";
    public static final String BOOK_FILE_TYPE_TXT = "txt";
    public static final String BOOK_FILE_TYPE_EPUB = "epub";
    public static final String BOOK_FILE_TYPE_CHM = "chm";
    public static final String BOOK_FILE_TYPE_HTML = "html";
    public static final String BOOK_FILE_TYPE_MOBI = "mobi";
    public static final String BOOK_FILE_TYPE_UMD = "umd";
    public static final String BOOK_FILE_TYPE_PDF = "pdf";

    public static final Set<String> DB_TABLE_TYPE_VALUES = new HashSet<String>(){
        {
            add(BOOK_FILE_TYPE_UNKNOWN);
            add(BOOK_FILE_TYPE_TXT);
            add(BOOK_FILE_TYPE_EPUB);
            add(BOOK_FILE_TYPE_CHM);
            add(BOOK_FILE_TYPE_HTML);
            add(BOOK_FILE_TYPE_MOBI);
            add(BOOK_FILE_TYPE_UMD);
            add(BOOK_FILE_TYPE_PDF);
        }
    };

}
