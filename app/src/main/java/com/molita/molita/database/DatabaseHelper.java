package com.molita.molita.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    static final private String DB_NAME = "db_buku";
    static final private int DB_VERSION = 2;

    static final private String TABLE_USERS = "users";
    static final private String COLUMN_USER_ID = "id";
    static final private String COLUMN_USER_username = "username";
    static final private String COLUMN_USER_password = "password";

    static final private String TABLE_BOOKS = "books";
    static final private String COLUMN_BOOK_ID = "id";
    static final private String COLUMN_BOOK_judul = "judul";
    static final private String COLUMN_BOOK_penulis = "penulis";
    static final private String COLUMN_BOOK_tahun = "tahun";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String querySqlUsers = "CREATE TABLE IF NOT EXISTS " + TABLE_USERS + " ("
                + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_USER_username + " TEXT UNIQUE NOT NULL,"
                + COLUMN_USER_password + " TEXT NOT NULL);";

        String querySqlBooks = "CREATE TABLE IF NOT EXISTS " + TABLE_BOOKS + " ("
                + COLUMN_BOOK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_BOOK_judul + " TEXT NOT NULL,"
                + COLUMN_BOOK_penulis + " TEXT NOT NULL,"
                + COLUMN_BOOK_tahun + " INTEGER NOT NULL);";
        sqLiteDatabase.execSQL(querySqlUsers);
        sqLiteDatabase.execSQL(querySqlBooks);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKS);
        onCreate(sqLiteDatabase);
    }

    public Cursor getAllUsers() {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_USERS, null);
    }

    public boolean insertUser(String username, String password) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_USER_username, username);
        contentValues.put(COLUMN_USER_password, password);

        long result = db.insert(TABLE_USERS, null, contentValues);

        return result != -1;
    }

    public Cursor getAllBooks() {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_BOOKS, null);
    }

    public Cursor getUserByUsername(String username) {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_USERS + " WHERE username = ?";
        return db.rawQuery(sql, new String[]{username});
    }

    public boolean insertBook(String judul, String penulis, int tahun) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_BOOK_judul, judul);
        contentValues.put(COLUMN_BOOK_penulis, penulis);
        contentValues.put(COLUMN_BOOK_tahun, tahun);

        long result = db.insert(TABLE_BOOKS, null, contentValues);

        return result != -1;
    }
}
