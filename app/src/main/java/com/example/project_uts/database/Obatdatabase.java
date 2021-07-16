package com.example.project_uts.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.project_uts.model.Obat;

import java.util.ArrayList;
import java.util.HashMap;

public class Obatdatabase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "project_uts";

    public static final int DATABASE_VERSION = 5;

    public static final String TABLE_OBAT = "obat";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAMA_OBAT = "nama_obat";
    public static final String COLUMN_HARGA = "harga";
    public static final String COLUMN_STOCK = "stock";
    public static final String COLUMN_SATUAN = "satuan";
    public static final String COLUMN_TGL_KADALUARSA = "tgl_kadaluarsa";

    public Obatdatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*String CREATE_OBATS_TABLE = "CREATE TABLE " + TABLE_OBAT + "(" + COLUMN_ID
                + " INTEGER PRIMARY KEY autoincrement," + COLUMN_NAMA_OBAT + " TEXT," + COLUMN_HARGA + "INTEGER," +
                COLUMN_STOCK + " INTEGER,"+ COLUMN_SATUAN + " TEXT," + COLUMN_TGL_KADALUARSA + " DATE" + ")";
        db.execSQL(CREATE_PASIENS_TABLE);*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       /* db.execSQL("DROP TABLE IF EXISTS " + TABLE_PASIEN);
        onCreate(db);*/
    }

    public ArrayList<HashMap<String, String>> getAllData() {
        ArrayList<HashMap<String, String>> wordList;
        wordList = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT * FROM " + TABLE_OBAT;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put(COLUMN_ID, cursor.getString(0));
                map.put(COLUMN_NAMA_OBAT, cursor.getString(1));
                map.put(COLUMN_HARGA, cursor.getString(2));
                map.put(COLUMN_STOCK, cursor.getString(3));
                map.put(COLUMN_SATUAN, cursor.getString(4));
                map.put(COLUMN_TGL_KADALUARSA, cursor.getString(5));
                wordList.add(map);
            } while (cursor.moveToNext());
        }

        Log.e("select obat ", "" + wordList);

        database.close();
        return wordList;
    }

    public void insert(String nama, String harga, String stock,String satuan,String tgl_kadaluarsa ) {
        SQLiteDatabase database = this.getWritableDatabase();
        String queryValues = "INSERT INTO " + TABLE_OBAT + " (nama, harga, stock, satuan, tgl_kadaluarsa) " +
                "VALUES ('" + nama + "'," +harga + ", "+stock + ", '"+satuan + "', '" +tgl_kadaluarsa + "')";

        Log.e("insert pasien ", "" + queryValues);
        database.execSQL(queryValues);
        database.close();
    }

    public void update(int id, String nama,  String harga, String stock,String satuan,String tgl_kadaluarsa) {
        SQLiteDatabase database = this.getWritableDatabase();

        String updateQuery = "UPDATE " + TABLE_OBAT + " SET "
                + COLUMN_NAMA_OBAT + "='" + nama + "', "
                + COLUMN_HARGA + "=" + harga + ", "
                + COLUMN_STOCK + "=" + stock + ", "
                + COLUMN_SATUAN + "='" + satuan + "', "
                + COLUMN_TGL_KADALUARSA+ "='" + tgl_kadaluarsa + "' "
                + " WHERE " + COLUMN_ID + "=" + "'" + id + "'";
        Log.e("update pasien ", updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }


    public Obat findPasien(String name){
        String query = "SELECT * FROM " + TABLE_OBAT + " WHERE " + COLUMN_NAMA_OBAT + " = " + "name";
        SQLiteDatabase db =  this.getWritableDatabase();
        Obat mObat = null;
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            String id =cursor.getString(0);
            String nama = cursor.getString(1);
            String harga = cursor.getString(2);
            String stock = cursor.getString(3);
            String satuan = cursor.getString(4);
            String tgl_kadaluarsa = cursor.getString(5);
            mObat = new Obat(id, nama, harga,stock,satuan,tgl_kadaluarsa);
        }
        cursor.close();
        return mObat;
    }

    public void deletePasien(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_OBAT, COLUMN_ID + " = ?",
                new String[] {
                        String.valueOf(id)
                });
    }

}
