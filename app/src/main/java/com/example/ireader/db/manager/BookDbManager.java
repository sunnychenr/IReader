package com.example.ireader.db.manager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ireader.db.DBConstants;

import java.util.Set;

/**
 * Created by ChenR on 2017/9/28.
 */

public class BookDbManager extends SQLiteOpenHelper {
    private static final String DB_NAME = "MyReaderBook";
    private static final String DB_TABLE_BASE_BOOK = "base_book";
    private static final String DB_TABLE_BOOK_READ = "read";
    private static final String DB_TABLE_BOOK_TYPE = "type";

    // database column name
    private static final String DB_BASE_BOOK_COLUMN_NAME = "name";
    private static final String DB_BASE_BOOK_COLUMN_TITLE = "title";
    private static final String DB_BASE_BOOK_COLUMN_AUTHOR = "author";
    private static final String DB_BASE_BOOK_COLUMN_COVER = "cover"; // 封面???
    private static final String DB_BASE_BOOK_COLUMN_FILE_LENGTH = "file_length";
    private static final String DB_BASE_BOOK_COLUMN_SAVE_PATH = "save_path";
    private static final String DB_BASE_BOOK_COLUMN_ADD_TIME = "add_time";

    private static final String DB_BOOK_READ_COLUMN_PROGRESS = "progress";
    private static final String DB_BOOK_READ_COLUMN_FONT_SIZE = "font_size";
    private static final String DB_BOOK_READ_COLUMN_PAGE_BG = "page_background";
    private static final String DB_BOOK_READ_COLUMN_PROGRESS_TAG = "progress_tag";
    private static final String DB_BOOK_READ_COLUMN_LAST_READ_TIME = "read_time";
    private static final String DB_BOOK_READ_COLUMN_FONT = "font";

    private static final String DB_TYPE_COLUME_TYPE = "type";

    private SQLiteDatabase db;

    public BookDbManager(Context context) {
        super(context, DB_NAME, null, 1);

        this.db = getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTypeTable(db);
        createBaseTable(db);
        createReadTable(db);

        initTypeDb(db);
    }

    private void initTypeDb(SQLiteDatabase db) {
        Set<String> typeValues = DBConstants.DB_TABLE_TYPE_VALUES;

        for (String value : typeValues) {
            String insertSQL = "insert into " + DB_TABLE_BOOK_TYPE + "(" + DB_TYPE_COLUME_TYPE + ") values ('" +
                    value + "');";
            db.execSQL(insertSQL);
        }

    }

    private void createTypeTable(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + DB_TABLE_BOOK_TYPE + " ('_id' INTEGER PRIMARY KEY AUTOINCREMENT, '" +
                DB_TYPE_COLUME_TYPE + "' CHAR(5) NOT NULL);";
        db.execSQL(sql);
    }

    private void createReadTable(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + DB_TABLE_BOOK_READ + " ('_id' INTEGER PRIMARY KEY AUTOINCREMENT, '" +
                DB_BOOK_READ_COLUMN_PROGRESS + "' INTEGER NOT NULL, '" +
                DB_BOOK_READ_COLUMN_LAST_READ_TIME + "' INTEGER NOT NULL, '" +
                DB_BOOK_READ_COLUMN_FONT_SIZE + "' INTEGER NOT NULL, '" +
                DB_BOOK_READ_COLUMN_FONT + "' CHAR(30), '" +
                DB_BOOK_READ_COLUMN_PROGRESS_TAG + "' TEXT NOT NULL, '" +
                DB_BOOK_READ_COLUMN_PAGE_BG + "' TEXT NOT NULL);";
        db.execSQL(sql);
    }

    private void createBaseTable(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + DB_TABLE_BASE_BOOK + " ('_id' INTEGER PRIMARY KEY AUTOINCREMENT, '" +
                DB_BASE_BOOK_COLUMN_FILE_LENGTH + "' INTEGER NOT NULL, '" +
                DB_BASE_BOOK_COLUMN_ADD_TIME + "' INTEGER NOT NULL, '" +
                DB_BASE_BOOK_COLUMN_NAME + "' TEXT NOT NULL, '" +
                DB_BASE_BOOK_COLUMN_TITLE + "' TEXT NOT NULL, '" +
                DB_BASE_BOOK_COLUMN_AUTHOR + "' CHAR(30), '" +
                DB_BASE_BOOK_COLUMN_COVER + "' VARCHAR(150), '" +
                DB_BASE_BOOK_COLUMN_SAVE_PATH + "' TEXT NOT NULL, " +
                "CONSTRAINT type_id FOREIGN KEY (_id) REFERENCES " + DB_TABLE_BOOK_TYPE + "(_id));";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
