package com.example.project_uts.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.project_uts.model.Dokter;

import java.util.ArrayList;
import java.util.HashMap;

public class Dokterdatabase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "project_uts";

    public static final int DATABASE_VERSION = 5;

    public static final String TABLE_DOKTER = "dokter";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAMA = "nama";
    public static final String COLUMN_TGLLAHIR = "tglLahir";
    public static final String COLUMN_GENDER = "gender";
    public static final String COLUMN_UMUR = "umur";
    public static final String COLUMN_ALAMAT = "alamat";
    public static final String COLUMN_NOHP = "nohp";
    public static final String COLUMN_SPESIALIS = "spesialis";

    public Dokterdatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*String CREATE_DOKTERS_TABLE = "CREATE TABLE " + TABLE_DOKTER + "(" + COLUMN_ID
                + " INTEGER PRIMARY KEY autoincrement," + COLUMN_NAMA + " TEXT," + COLUMN_TGLLAHIR + " DATE," +
                COLUMN_GENDER + " TEXT,"+ COLUMN_ALAMAT + " TEXT," + COLUMN_NOHP + " TEXT,"+ COLUMN_UMUR + " INTEGER," +COLUMN_SPESIALIS + " TEXT" + ")";
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
        String selectQuery = "SELECT * FROM " + TABLE_DOKTER;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put(COLUMN_ID, cursor.getString(0));
                map.put(COLUMN_NAMA, cursor.getString(1));
                map.put(COLUMN_TGLLAHIR, cursor.getString(2));
                map.put(COLUMN_GENDER, cursor.getString(3));
                map.put(COLUMN_ALAMAT, cursor.getString(4));
                map.put(COLUMN_NOHP, cursor.getString(5));
                map.put(COLUMN_UMUR, cursor.getString(6));
                map.put(COLUMN_SPESIALIS, cursor.getString(7));
                wordList.add(map);
            } while (cursor.moveToNext());
        }

        Log.e("select dokter ", "" + wordList);

        database.close();
        return wordList;
    }

    public void insert(String nama, String tglLahir, String gender,String umur,String address, String nohp, String spesialis ) {
        SQLiteDatabase database = this.getWritableDatabase();
        String queryValues = "INSERT INTO " + TABLE_DOKTER + " (nama, tglLahir, gender, umur, alamat, nohp, spesialis) " +
                "VALUES ('" + nama + "','" +tglLahir + "','"+gender + "',"+umur + ", '" +address + "','" +nohp + "','"+ spesialis + "')";

        Log.e("insert dokter ", "" + queryValues);
        database.execSQL(queryValues);
        database.close();
    }

    public void update(int id, String nama, String tglLahir, String gender,String umur,String address, String nohp,String spesialis ) {
        SQLiteDatabase database = this.getWritableDatabase();

        String updateQuery = "UPDATE " + TABLE_DOKTER + " SET "
                + COLUMN_NAMA + "='" + nama + "', "
                + COLUMN_TGLLAHIR + "='" + tglLahir + "', "
                + COLUMN_GENDER + "='" + gender + "', "
                + COLUMN_UMUR + "=" + umur + ", "
                + COLUMN_ALAMAT+ "='" + address + "', "
                + COLUMN_NOHP+ "='" + nohp + "',"
                + COLUMN_SPESIALIS+ "='" + spesialis+ "'"
                + " WHERE " + COLUMN_ID + "=" + "'" + id + "'";
        Log.e("update dokter ", updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }


    public Dokter findDokter(String name){
        String query = "SELECT * FROM " + TABLE_DOKTER + " WHERE " + COLUMN_NAMA + " = " + "name";
        SQLiteDatabase db =  this.getWritableDatabase();
        Dokter mDokter = null;
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            String id =cursor.getString(0);
            String nama = cursor.getString(1);
            String tglLahir = cursor.getString(2);
            String gender = cursor.getString(3);
            String umur = cursor.getString(4);
            String alamat = cursor.getString(5);
            String nohp = cursor.getString(6);
            String spesialis=cursor.getString(7);
            mDokter = new Dokter(id, nama, tglLahir,gender,umur,alamat,nohp,spesialis);
        }
        cursor.close();
        return mDokter;
    }

    public void deletePasien(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DOKTER, COLUMN_ID + " = ?",
                new String[] {
                        String.valueOf(id)
                });
    }

}
