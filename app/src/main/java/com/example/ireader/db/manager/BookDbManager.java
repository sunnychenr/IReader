package com.example.ireader.db.manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;

import com.example.ireader.db.DBConstants;
import com.example.ireader.util.LogUtil;

import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by ChenR on 2017/9/28.
 */

public class BookDbManager extends SQLiteOpenHelper {
    private static final String TAG = "BookDbManager";

    private static final String DB_NAME = "MyReaderBook";
    private static final String DB_TABLE_BASE_BOOK = "base_book";
    private static final String DB_TABLE_BOOK_READ = "read";
    private static final String DB_TABLE_BOOK_TYPE = "type";

    // database table primary key name;
    private static final String DB_PRIMARY_KEY_NAME = "_id";

    // database column name
    public static final String DB_BASE_BOOK_COLUMN_TITLE = "title";
    public static final String DB_BASE_BOOK_COLUMN_AUTHOR = "author"; // 作者
    public static final String DB_BASE_BOOK_COLUMN_COVER = "cover"; // 封面???
    public static final String DB_BASE_BOOK_COLUMN_SAVE_PATH = "save_path"; // 书本文件保存路径;
    public static final String DB_BASE_BOOK_COLUMN_NAME = "name";
    public static final String DB_BASE_BOOK_COLUMN_FILE_LENGTH = "file_length";
    public static final String DB_BASE_BOOK_COLUMN_ADD_TIME = "add_time";
    public static final String DB_BASE_BOOK_COLUMN_FILE_TYPE_ID = "file_type_id";

    public static final String DB_BOOK_READ_COLUMN_PROGRESS = "progress";
    public static final String DB_BOOK_READ_COLUMN_FONT_SIZE = "font_size";
    public static final String DB_BOOK_READ_COLUMN_PAGE_BG = "page_background";
    public static final String DB_BOOK_READ_COLUMN_PROGRESS_TAG = "progress_tag";
    public static final String DB_BOOK_READ_COLUMN_LAST_READ_TIME = "read_time";
    public static final String DB_BOOK_READ_COLUMN_FONT = "font";

    private static final String DB_TYPE_COLUMN_TYPE = "type";

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

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if(!db.isReadOnly()) { // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

    private void initTypeDb(SQLiteDatabase db) {
        Set<String> typeValues = DBConstants.DB_TABLE_TYPE_VALUES;

        for (String value : typeValues) {
            String insertSQL = "insert into " + DB_TABLE_BOOK_TYPE + "(" + DB_TYPE_COLUMN_TYPE + ") values ('" +
                    value + "');";
            db.execSQL(insertSQL);
        }

    }

    private void createTypeTable(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + DB_TABLE_BOOK_TYPE + " ('"
                + DB_PRIMARY_KEY_NAME + "' INTEGER PRIMARY KEY AUTOINCREMENT, '"
                + DB_TYPE_COLUMN_TYPE + "' CHAR(5) NOT NULL);";
        db.execSQL(sql);
    }

    private void createReadTable(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + DB_TABLE_BOOK_READ + " ('" +
                DB_PRIMARY_KEY_NAME + "' INTEGER PRIMARY KEY AUTOINCREMENT, '" +
                DB_BOOK_READ_COLUMN_PROGRESS + "' INTEGER NOT NULL, '" +
                DB_BOOK_READ_COLUMN_LAST_READ_TIME + "' INTEGER NOT NULL, '" +
                DB_BOOK_READ_COLUMN_FONT_SIZE + "' INTEGER NOT NULL, '" +
                DB_BOOK_READ_COLUMN_FONT + "' CHAR(30), '" +
                DB_BOOK_READ_COLUMN_PROGRESS_TAG + "' TEXT NOT NULL, '" +
                DB_BOOK_READ_COLUMN_PAGE_BG + "' TEXT NOT NULL);";
        db.execSQL(sql);
    }

    private void createBaseTable(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + DB_TABLE_BASE_BOOK + " ('" +
                DB_PRIMARY_KEY_NAME + "' INTEGER PRIMARY KEY AUTOINCREMENT, '" +
                DB_BASE_BOOK_COLUMN_FILE_LENGTH + "' INTEGER NOT NULL, '" +
                DB_BASE_BOOK_COLUMN_ADD_TIME + "' INTEGER NOT NULL, '" +
                DB_BASE_BOOK_COLUMN_NAME + "' TEXT NOT NULL, '" +
                DB_BASE_BOOK_COLUMN_TITLE + "' TEXT NOT NULL, '" +
                DB_BASE_BOOK_COLUMN_AUTHOR + "' CHAR(30), '" +
                DB_BASE_BOOK_COLUMN_COVER + "' VARCHAR(150), '" +
                DB_BASE_BOOK_COLUMN_SAVE_PATH + "' TEXT NOT NULL, '" +
                DB_BASE_BOOK_COLUMN_FILE_TYPE_ID + "' INTEGER NOT NULL, " +
                "FOREIGN KEY (" + DB_BASE_BOOK_COLUMN_FILE_TYPE_ID + ") REFERENCES " + DB_TABLE_BOOK_TYPE + "(_id));";
        db.execSQL(sql);
    }

    public void AddBaseBook (Map<String, String> parameter) {
        if (parameter == null || parameter.isEmpty()) {
            LogUtil.e(TAG, TAG + " add base book error: No base book params.");
            return;
        }

        StringBuffer sb1 = new StringBuffer();
        sb1.append("INSERT INTO ").append(DB_TABLE_BASE_BOOK).append(" (");
        StringBuffer sb2 = new StringBuffer();
        sb2.append(") VALUES (");

        for (Map.Entry<String, String> entry : parameter.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            sb1.append(key).append(", ");
            if (key.equals(DB_BASE_BOOK_COLUMN_NAME) || key.equals(DB_BASE_BOOK_COLUMN_TITLE)
                    || key.equals(DB_BASE_BOOK_COLUMN_AUTHOR) || key.equals(DB_BASE_BOOK_COLUMN_COVER)
                    || key.equals(DB_BASE_BOOK_COLUMN_SAVE_PATH)) {
                sb2.append("'").append(value).append("', ");
            } else {
                sb2.append(value).append(", ");
            }
        }

        sb2.delete(sb2.indexOf(", "), sb2.length());
        sb1.delete(sb1.indexOf(", "), sb1.length()).append(sb2).append(");");
        db.execSQL(sb1.toString());
    }

    public void addBaseBook (final String bookSavePath) {
        if (TextUtils.isEmpty(bookSavePath)) {
            LogUtil.e(TAG, TAG + " add base book error: book save path is null.");
            return;
        }

        File bookFile = new File(bookSavePath);
        if (!bookFile.exists() || bookFile.isDirectory()) {
            LogUtil.e(TAG, TAG + " add base book error: book file is not exist or that is not file.");
            return;
        }

        String savePath = bookSavePath;
        String bookName = bookFile.getName();
        String title = bookName.substring(0, bookName.indexOf("."));
        long fileLength = bookFile.length();
        long addTime = System.currentTimeMillis();
        int bookTypeId = getBookTypeId(bookSavePath);

        ContentValues values = new ContentValues();
        values.put(DB_BASE_BOOK_COLUMN_ADD_TIME, addTime);
        values.put(DB_BASE_BOOK_COLUMN_SAVE_PATH, savePath);
        values.put(DB_BASE_BOOK_COLUMN_NAME, bookName);
        values.put(DB_BASE_BOOK_COLUMN_TITLE, title);
        values.put(DB_BASE_BOOK_COLUMN_FILE_LENGTH, fileLength);
        values.put(DB_BASE_BOOK_COLUMN_FILE_TYPE_ID, bookTypeId);

        db.insert(DB_TABLE_BASE_BOOK, null, values);
    }

    public void updateBaseBook (int baseBookId, ContentValues parameter) {
        if (baseBookId < 1) {
            return;
        }

        if (parameter == null || parameter.size() == 0) {
            return;
        }

        db.update(DB_TABLE_BASE_BOOK, parameter, DB_PRIMARY_KEY_NAME + "=" + baseBookId, null);
    }

    public int getBookTypeId(String bookSavePath) {
        if (TextUtils.isEmpty(bookSavePath)) {
            return -1;
        }

        File bookFile = new File(bookSavePath);
        if (!bookFile.exists() || bookFile.isDirectory()) {
            return -1;
        }

        String bookType = DBConstants.BOOK_FILE_TYPE_UNKNOWN;
        String fileType = bookFile.getName().substring(bookFile.getName().indexOf(".") + 1);
        Iterator<String> iterator = DBConstants.DB_TABLE_TYPE_VALUES.iterator();
        while (iterator.hasNext()) {
            String type = iterator.next();
            if (fileType.equalsIgnoreCase(type)) {
                bookType = type;
                break;
            }
        }

        int bookTypeId = -1;
        Cursor query = db.query(DB_TABLE_BOOK_TYPE,
                new String[]{
                        DB_PRIMARY_KEY_NAME
                },
                DB_TYPE_COLUMN_TYPE + "=" + bookType,
                null, null, null, null);
        if (query != null) {
            query.moveToFirst();
            bookTypeId = query.getInt(0);
            query.close();
        }
        return bookTypeId;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
