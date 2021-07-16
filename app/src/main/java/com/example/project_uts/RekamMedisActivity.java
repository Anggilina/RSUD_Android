package com.example.project_uts;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.project_uts.adapter.RekamMedisAdapter;
import com.example.project_uts.database.RekamMedisDatabase;
import com.example.project_uts.model.RekamMedis;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RekamMedisActivity extends AppCompatActivity {

    ListView listView;
    AlertDialog.Builder dialog;
    List<RekamMedis> itemList = new ArrayList<RekamMedis>();
    RekamMedisDatabase SQLite = new RekamMedisDatabase(this);
    RekamMedisAdapter adapter;

    public static final String TAG_ID = "id";
    public static final String TAG_NAMA_PASIEN = "nama_pasien";
    public static final String TAG_NAMA_DOKTER = "nama";
    public static final String TAG_TGL_PEROBATAN = "tglPengobatan";
    public static final String TAG_WAKTU_PEROBATAN = "waktuPengobatan";
    public static final String TAG_KELUHAN_PASIEN = "keluhanPasien";
    public static final String TAG_HASIL_DIAGNOSA = "hasil_diagnosa";
    public static final String TAG_BIAYA = "biaya";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rekam_medis);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SQLite = new RekamMedisDatabase(getApplicationContext());

        FloatingActionButton fab = findViewById(R.id.fab);
        listView = (ListView) findViewById(R.id.list_view_rekam_medis);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RekamMedisActivity.this, TambahRekamMedis.class);
                startActivity(intent);
            }
        });
        adapter= new RekamMedisAdapter(RekamMedisActivity.this, itemList);
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(final AdapterView<?> parent, View view,
                                           final int position, long id) {
                // TODO Auto-generated method stub
                final String idx = itemList.get(position).getId();
                final String nama_pasien = itemList.get(position).getNama_pasien();
                final String nama_dokter = itemList.get(position).getNama_dokter();
                final String tgl_perobatan = itemList.get(position).getTgl_pengobatan();
                final String waktu_perobatan= itemList.get(position).getWaktu_pengobatan();
                final String keluhan_pasien=itemList.get(position).getKeluhan_pasien();
                final String hasil_diagnosa=itemList.get(position).getHasil_diagnosa();
                final String biaya=itemList.get(position).getBiaya();

                final CharSequence[] dialogitem = {"Lihat","Edit", "Delete"};
                dialog = new AlertDialog.Builder(RekamMedisActivity.this);
                dialog.setCancelable(true);
                dialog.setItems(dialogitem, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        switch (which) {
                            case 0:
                                Intent intent1 = new Intent(RekamMedisActivity.this, DetalPasienActivity.class);
                                intent1.putExtra(TAG_ID, idx);
                                intent1.putExtra(TAG_NAMA_PASIEN, nama_pasien);
                                intent1.putExtra(TAG_NAMA_DOKTER,nama_dokter);
                                intent1.putExtra(TAG_TGL_PEROBATAN,tgl_perobatan);
                                intent1.putExtra(TAG_WAKTU_PEROBATAN,waktu_perobatan);
                                intent1.putExtra(TAG_KELUHAN_PASIEN, keluhan_pasien);
                                intent1.putExtra(TAG_HASIL_DIAGNOSA, hasil_diagnosa);
                                intent1.putExtra(TAG_BIAYA, biaya);
                                startActivity(intent1);
                                break;
                            case 1:
                                Intent intent = new Intent(RekamMedisActivity.this, TambahRekamMedis.class);
                                intent.putExtra(TAG_ID, idx);
                                intent.putExtra(TAG_NAMA_PASIEN, nama_pasien);
                                intent.putExtra(TAG_NAMA_DOKTER,nama_dokter);
                                intent.putExtra(TAG_TGL_PEROBATAN,tgl_perobatan);
                                intent.putExtra(TAG_WAKTU_PEROBATAN,waktu_perobatan);
                                intent.putExtra(TAG_KELUHAN_PASIEN, keluhan_pasien);
                                intent.putExtra(TAG_HASIL_DIAGNOSA, hasil_diagnosa);
                                intent.putExtra(TAG_BIAYA, biaya);
                                startActivity(intent);
                                break;
                            case 2:
                                SQLite.deletePasien(Integer.parseInt(idx));
                                itemList.clear();
                                getAllData();
                                break;
                        }
                    }
                }).show();
                return false;
            }
        });
        getAllData();
    }
    private void getAllData() {
        ArrayList<HashMap<String, String>> row = SQLite.getAllData();

        for (int i = 0; i < row.size(); i++) {
            String id = row.get(i).get(TAG_ID);
            String poster = row.get(i).get(TAG_NAMA_PASIEN);
            String dokter = row.get(i).get(TAG_NAMA_DOKTER);
            String tglpengobatan = row.get(i).get(TAG_TGL_PEROBATAN);
            String waktupengobatan = row.get(i).get(TAG_WAKTU_PEROBATAN);
            String keluhan_pasien = row.get(i).get(TAG_KELUHAN_PASIEN);
            String hasil_diagnosa = row.get(i).get(TAG_HASIL_DIAGNOSA);
            String biaya = row.get(i).get(TAG_BIAYA);

            RekamMedis data = new RekamMedis();

            data.setId(id);
            data.setNama_pasien(poster);
            data.setNama_dokter(dokter);
            data.setTgl_pengobatan(tglpengobatan);
            data.setWaktu_pengobatan(waktupengobatan);
            data.setKeluhan_pasien(keluhan_pasien);
            data.setHasil_diagnosa(hasil_diagnosa);
            data.setBiaya(biaya);


            itemList.add(data);
        }
        adapter.notifyDataSetChanged();
    }
}