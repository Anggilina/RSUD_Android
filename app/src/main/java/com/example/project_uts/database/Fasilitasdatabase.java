package com.example.project_uts.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.project_uts.model.Fasilitas;

import java.util.ArrayList;
import java.util.HashMap;

public class Fasilitasdatabase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "project_uts";

    public static final int DATABASE_VERSION = 5;

    public static final String TABLE_FASILITAS = "fasilitas";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAMA_KAMAR = "nama_kamar";
    public static final String COLUMN_FASILITAS = "fasilitas";
    public static final String COLUMN_LANTAI = "lantai";
    public static final String COLUMN_TARIF = "tarif";

    public Fasilitasdatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*String CREATE_FASILITASS_TABLE = "CREATE TABLE " + TABLE_FASILITAS + "(" + COLUMN_ID
                + " INTEGER PRIMARY KEY autoincrement," + COLUMN_NAMA_KAMAR + " TEXT," + COLUMN_FASILITAS + " TEXT," +
                COLUMN_LANTAI + " INTEGER,"+ COLUMN_TARIF + " INTEGER " + ")";
        db.execSQL(CREATE_DOKTERS_TABLE);*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       /* db.execSQL("DROP TABLE IF EXISTS " + TABLE_DOKTER);
        onCreate(db);*/
    }

    public ArrayList<HashMap<String, String>> getAllData() {
        ArrayList<HashMap<String, String>> wordList;
        wordList = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT * FROM " + TABLE_FASILITAS;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put(COLUMN_ID, cursor.getString(0));
                map.put(COLUMN_NAMA_KAMAR, cursor.getString(1));
                map.put(COLUMN_LANTAI, cursor.getString(2));
                map.put(COLUMN_TARIF, cursor.getString(3));
                map.put(COLUMN_FASILITAS, cursor.getString(4));
                wordList.add(map);
            } while (cursor.moveToNext());
        }

        Log.e("select dokter ", "" + wordList);

        database.close();
        return wordList;
    }

    public void insert(String nama, String lantai, String tarif,String fasilitas ) {
        SQLiteDatabase database = this.getWritableDatabase();
        String queryValues = "INSERT INTO " + TABLE_FASILITAS + " (nama, lantai, tarif, fasilitas) " +
                "VALUES ('" + nama + "'," +lantai + ","+tarif + ","+fasilitas +"')";

        Log.e("insert fasilitas ", "" + queryValues);
        database.execSQL(queryValues);
        database.close();
    }

    public void update(int id,String nama, String lantai, String tarif,String fasilitas ) {
        SQLiteDatabase database = this.getWritableDatabase();

        String updateQuery = "UPDATE " + TABLE_FASILITAS + " SET "
                + COLUMN_NAMA_KAMAR + "='" + nama + "', "
                + COLUMN_LANTAI + "='" + lantai + "', "
                + COLUMN_TARIF + "='" + tarif + "', "
                + COLUMN_FASILITAS + "=" + fasilitas + " "
                + " WHERE " + COLUMN_ID + "=" + "'" + id + "'";
        Log.e("update fasilitas ", updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }


    public Fasilitas findDokter(String name){
        String query = "SELECT * FROM " + TABLE_FASILITAS + " WHERE " + COLUMN_NAMA_KAMAR + " = " + "name";
        SQLiteDatabase db =  this.getWritableDatabase();
        Fasilitas mFasilitas = null;
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            String id =cursor.getString(0);
            String nama = cursor.getString(1);
            String lantai = cursor.getString(2);
            String tarif = cursor.getString(3);
            String fasilitas = cursor.getString(4);
            mFasilitas = new Fasilitas(id, nama, lantai,tarif,fasilitas);
        }
        cursor.close();
        return mFasilitas;
    }

    public void deletePasien(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_FASILITAS, COLUMN_ID + " = ?",
                new String[] {
                        String.valueOf(id)
                });
    }
}
