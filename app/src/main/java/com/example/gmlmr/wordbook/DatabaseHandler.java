package com.example.gmlmr.wordbook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "WordBook";
    // Words table name
    private static final String TABLE_WORDS = "Words";

    // Words Table Column names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "word";
    private static final String KEY_DEF = "definition";
    private static final String KEY_FLAG = "flag";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_WORDS_TABLE = "CREATE TABLE " + TABLE_WORDS + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_NAME + " TEXT UNIQUE NOT NULL ,"
                + KEY_DEF + " TEXT ,"
                + KEY_FLAG + " INTEGER" + ")";
        db.execSQL(CREATE_WORDS_TABLE);
    }

    // Updating database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORDS);
        onCreate(db);
    }

    // Adding new word
    public void addWords(String word, String def, int flag) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, word);
        values.put(KEY_DEF,def);
        values.put(KEY_FLAG, flag);
        db.insert(TABLE_WORDS, null, values);
        db.close();
    }



    // Getting All Words
    public ArrayList<String> getAllWords() {
        ArrayList<String> wordsList = new ArrayList();

        try {
            String selectQuery = "SELECT * FROM " + TABLE_WORDS;

            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    String name = cursor.getString(cursor.getColumnIndex("word"));
                   // String definition = cursor.getString(cursor.getColumnIndex("definition"));
                    wordsList.add(name);
                   // wordsList.add(definition);
                } while (cursor.moveToNext());
            }
            cursor.close();
            db.close();
            return wordsList;
        } catch (Exception e) {
            Log.e("all_word", "" + e);
        }
        return wordsList;
    }

    // Getting Favorite Words
    public ArrayList<String> getFavWords() {
        ArrayList<String> favList = new ArrayList();

        try {
            String selectQuery1 = "SELECT * FROM "+ TABLE_WORDS + " WHERE "+ KEY_FLAG + "=" + 1 ;

            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor1 = db.rawQuery(selectQuery1, null);
            if (cursor1.moveToFirst()) {
                do {
                    String name = cursor1.getString(cursor1.getColumnIndex("word"));
                    favList.add(name);
                } while (cursor1.moveToNext());
            }
            cursor1.close();
            db.close();
            return favList;
        } catch (Exception e) {
            Log.e("all_word", "" + e);
        }
        return favList;
    }

    // Generating Random Words
    public String getRandomWords() {
        String ranWord = new String();

        try {
            //String selectQuery2 = "SELECT * FROM "+ TABLE_WORDS + " ORDER BY RAND() LIMIT 1";
            String selectQuery3 = "SELECT " + KEY_NAME +" FROM "+ TABLE_WORDS + " WHERE " + KEY_ID + " IN (SELECT " + KEY_ID +
                                  " FROM " + TABLE_WORDS + " ORDER BY RANDOM() LIMIT 1)";

            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor2 = db.rawQuery(selectQuery3, null);
             if (cursor2.moveToFirst()) {
                do {
                    ranWord = cursor2.getString(cursor2.getColumnIndex("word"));
                   // String def = cursor2.getString(cursor2.getColumnIndex("definition"));
                } while (cursor2.moveToNext());
            }
            cursor2.close();
            db.close();
            return ranWord;
        } catch (Exception e) {
            Log.e("all_word", "" + e);
        }
        return ranWord;
    }



    //get word by name
    public String getWordbyID(String val) {
        String word = new String();

        try {
            String selectQuery1 = "SELECT * FROM "+ TABLE_WORDS + " WHERE "+ KEY_NAME + " = ? " ;

            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor3 = db.rawQuery(selectQuery1,  new String[] { String.valueOf(val) });
            if (cursor3.moveToFirst()) {
                do {
                    word = cursor3.getString(cursor3.getColumnIndex("word"));
                } while (cursor3.moveToNext());
            }
            cursor3.close();
            db.close();
            return word;
        } catch (Exception e) {
            Log.e("all_word", "" + e);
        }
        return word;
    }
    //get definition by name
    public String getDefbyID(String val) {
        String definition = new String();

        try {
            String selectQuery1 = "SELECT * FROM "+ TABLE_WORDS + " WHERE "+ KEY_NAME + " = ?" ;

            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor4 = db.rawQuery(selectQuery1,  new String[] { String.valueOf(val) });
            if (cursor4.moveToFirst()) {
                do {
                    definition = cursor4.getString(cursor4.getColumnIndex("definition"));
                } while (cursor4.moveToNext());
            }
            cursor4.close();
            db.close();
            return definition;
        } catch (Exception e) {
            Log.e("all_word", "" + e);
        }
        return definition;
    }




}