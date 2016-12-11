package com.example.jayeshvaghela.project_vaghela;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {
    public static final String KEY_COL1 = "_id";
    public static final String KEY_COL2 = "score";
    public static final String KEY_COL3 = "comment";
    private static final String TAG = "DBAdapter";

    private static final String DATABASE_NAME = "MyDB";
    private static final String DATABASE_TABLE = "student";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE =
            "create table student (_id integer primary key autoincrement, "
                    + "score text not null, comment text not null);";

    private final Context context;

    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public DBAdapter(Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(DATABASE_CREATE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS student");
            onCreate(db);
        }
    }

    //---opens the database---
    public DBAdapter open() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //---closes the database---
    public void close() {
        DBHelper.close();
    }

    //---insert a contact into the database---
    public long insertStudentInfo(String studentID, String score, String comment) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_COL1, studentID);
        initialValues.put(KEY_COL2, score);
        initialValues.put(KEY_COL3, comment);
        long val = db.insert(DATABASE_TABLE, null, initialValues);
        return val;
    }

    //---deletes a particular contact---
    public boolean deleteContact(long rowId) {
        return db.delete(DATABASE_TABLE, KEY_COL1 + "=" + rowId, null) > 0;
    }

    //---retrieves all the contacts---
    public Cursor getAllStudentInfo() {
        return db.query(DATABASE_TABLE, new String[]{KEY_COL1, KEY_COL2,
                KEY_COL3}, null, null, null, null, null);
    }

    //---retrieves a particular contact---
    public Cursor getContact(long rowId) throws SQLException {
        Cursor mCursor =
                db.query(true, DATABASE_TABLE, new String[]{KEY_COL1,
                                KEY_COL2, KEY_COL3}, KEY_COL1 + "=" + rowId, null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    //---updates a contact---
    public boolean updateContact(long rowId, String score, String comment) {
        ContentValues args = new ContentValues();
        args.put(KEY_COL2, score);
        args.put(KEY_COL3, comment);
        return db.update(DATABASE_TABLE, args, KEY_COL1 + "=" + rowId, null) > 0;
    }
}