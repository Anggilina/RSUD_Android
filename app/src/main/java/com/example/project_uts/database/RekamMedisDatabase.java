package com.example.project_uts.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.project_uts.model.Pasien;

import java.util.ArrayList;
import java.util.HashMap;

public class RekamMedisDatabase  extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "db";

    public static final int DATABASE_VERSION = 7;

    public static final String TABLE_REKAMMEDIS = "RekamMedis";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAMA_PASIEN = "nama_pasien";
    public static final String COLUMN_NAMA_DOKTER = "nama_dokter";
    public static final String COLUMN_TGLPENGOBATAN= "tglPengobatan";
    public static final String COLUMN_WAKTUPENGOBATAN = "waktuPengobatan";
    public static final String COLUMN_KELUHAN_PASIEN = "keluhanPasien";
    public static final String COLUMN_HASIL_DIAGNOSA = "hasil_diagnosa";
    public static final String COLUMN_BIAYA = "biaya";

    public RekamMedisDatabase (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_REKAMMEDISS_TABLE = "CREATE TABLE " + TABLE_REKAMMEDIS + "(" + COLUMN_ID
                + " INTEGER PRIMARY KEY autoincrement," + COLUMN_NAMA_PASIEN + " TEXT," + COLUMN_NAMA_DOKTER + " TEXT," +
                COLUMN_TGLPENGOBATAN + " TEXT,"+ COLUMN_WAKTUPENGOBATAN + " TEXT," + COLUMN_KELUHAN_PASIEN + " TEXT,"
                + COLUMN_HASIL_DIAGNOSA + " TEXT," +COLUMN_BIAYA + " INTEGER" + ")";
        db.execSQL(CREATE_REKAMMEDISS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       db.execSQL("DROP TABLE IF EXISTS " + TABLE_REKAMMEDIS);
        onCreate(db);
    }

    public ArrayList<HashMap<String, String>> getAllData() {
        ArrayList<HashMap<String, String>> wordList;
        wordList = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT * FROM " + TABLE_REKAMMEDIS;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put(COLUMN_ID, cursor.getString(0));
                map.put(COLUMN_NAMA_PASIEN, cursor.getString(1));
                map.put(COLUMN_NAMA_DOKTER, cursor.getString(2));
                map.put(COLUMN_TGLPENGOBATAN, cursor.getString(3));
                map.put(COLUMN_WAKTUPENGOBATAN, cursor.getString(4));
                map.put(COLUMN_KELUHAN_PASIEN, cursor.getString(5));
                map.put(COLUMN_HASIL_DIAGNOSA, cursor.getString(6));
                map.put(COLUMN_BIAYA, cursor.getString(7));
                wordList.add(map);
            } while (cursor.moveToNext());
        }

        Log.e("select RekamMedis ", "" + wordList);

        database.close();
        return wordList;
    }

    public void insert(String nama_pasien, String nama_dokter, String tglPengobatan,String waktuPengobatan,String keluhanPasien, String hasil_diagnosa,String biaya) {
        SQLiteDatabase database = this.getWritableDatabase();
        String queryValues = "INSERT INTO " + TABLE_REKAMMEDIS + " (nama, tglLahir, gender, umur, alamat, nohp) " +
                "VALUES ('" + nama_pasien + "', '" +nama_dokter + "', '"+tglPengobatan + "', " +
                "'"+waktuPengobatan + "','" +keluhanPasien + "','" +hasil_diagnosa + "',"+ biaya + ")";

        Log.e("insert RekamMedis ", "" + queryValues);
        database.execSQL(queryValues);
        database.close();
    }

    public void update(int id,String nama_pasien, String nama_dokter, String tglPengobatan,String waktuPengobatan,String keluhanPasien, String hasil_diagnosa,String biaya) {
        SQLiteDatabase database = this.getWritableDatabase();

        String updateQuery = "UPDATE " + TABLE_REKAMMEDIS + " SET "
                + COLUMN_NAMA_PASIEN + "='" + nama_pasien + "', "
                + COLUMN_NAMA_DOKTER + "='" + nama_dokter + "', "
                + COLUMN_TGLPENGOBATAN + "='" + tglPengobatan + "', "
                + COLUMN_WAKTUPENGOBATAN + "=" + waktuPengobatan + ", "
                + COLUMN_KELUHAN_PASIEN+ "='" + keluhanPasien + "', "
                + COLUMN_HASIL_DIAGNOSA+ "='" + hasil_diagnosa + "',"
                + COLUMN_BIAYA+ "=" + biaya + ""
                + " WHERE " + COLUMN_ID + "=" + "'" + id + "'";
        Log.e("update RekamMedis ", updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }


    /*public Pasien findPasien(String name){
        String query = "SELECT * FROM " + TABLE_REKAMMEDIS + " WHERE " + COLUMN_NAMA_PASIEN + " = " + "name";
        SQLiteDatabase db =  this.getWritableDatabase();
        Pasien mPasien = null;
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            String id =cursor.getString(0);
            String nama = cursor.getString(1);
            String tglLahir = cursor.getString(2);
            String gender = cursor.getString(3);
            String umur = cursor.getString(4);
            String alamat = cursor.getString(5);
            String nohp = cursor.getString(6);
            mPasien = new Pasien(id, nama, tglLahir,gender,umur,alamat,nohp);
        }
        cursor.close();
        return mPasien;
    }*/

    public void deletePasien(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_REKAMMEDIS, COLUMN_ID + " = ?",
                new String[] {
                        String.valueOf(id)
                });
    }
}
