package com.example.project_uts;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.project_uts.adapter.FasilitasAdapter;
import com.example.project_uts.database.Fasilitasdatabase;
import com.example.project_uts.model.Fasilitas;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FasilitasActivity extends AppCompatActivity {
    ListView listView;
    AlertDialog.Builder dialog;
    List<Fasilitas> itemList = new ArrayList<Fasilitas>();
    Fasilitasdatabase SQLite = new Fasilitasdatabase(this);
    FasilitasAdapter adapter;

    public static final String TAG_ID = "id";
    public static final String TAG_NAME = "nama_kamar";
    public static final String TAG_LANTAI = "lantai";
    public static final String TAG_TARIF = "tarif";
    public static final String TAG_FASILITAS = "fasilitas";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fasilitas);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SQLite = new Fasilitasdatabase(getApplicationContext());

        FloatingActionButton fab = findViewById(R.id.fab);
        listView = (ListView) findViewById(R.id.list_view_fasilitas);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FasilitasActivity.this, TambahFasilitas.class);
                startActivity(intent);
            }
        });

        adapter= new FasilitasAdapter(FasilitasActivity.this, itemList);
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(final AdapterView<?> parent, View view,
                                           final int position, long id) {
                // TODO Auto-generated method stub
                final String idx = itemList.get(position).getId();
                final String name = itemList.get(position).getNama_kamar();
                final String lantai = itemList.get(position).getLantai();
                final String tarif = itemList.get(position).getTarif();
                final String fasilitas= itemList.get(position).getFasilitas();

                final CharSequence[] dialogitem = {"Lihat","Edit", "Delete"};
                dialog = new AlertDialog.Builder(FasilitasActivity.this);
                dialog.setCancelable(true);
                dialog.setItems(dialogitem, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        switch (which) {
                            case 0:
                                Intent intent1 = new Intent(FasilitasActivity.this, DetalPasienActivity.class);
                                intent1.putExtra(TAG_ID, idx);
                                intent1.putExtra(TAG_NAME, name);
                                intent1.putExtra(TAG_LANTAI,lantai);
                                intent1.putExtra(TAG_TARIF,tarif);
                                intent1.putExtra(TAG_FASILITAS,fasilitas);
                                startActivity(intent1);
                                break;
                            case 1:
                                Intent intent = new Intent(FasilitasActivity.this, TambahFasilitas.class);
                                intent.putExtra(TAG_ID, idx);
                                intent.putExtra(TAG_NAME, name);
                                intent.putExtra(TAG_LANTAI,lantai);
                                intent.putExtra(TAG_TARIF,tarif);
                                intent.putExtra(TAG_FASILITAS,fasilitas);
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
            String poster = row.get(i).get(TAG_NAME);
            String lantai = row.get(i).get(TAG_LANTAI);
            String tarif = row.get(i).get(TAG_TARIF);
            String fasilitas = row.get(i).get(TAG_FASILITAS);

            Fasilitas data = new Fasilitas();

            data.setId(id);
            data.setNama_kamar(poster);
            data.setLantai(lantai);
            data.setTarif(tarif);
            data.setFasilitas(fasilitas);


            itemList.add(data);
        }
        adapter.notifyDataSetChanged();
    }
    @Override
    protected void onResume() {
        super.onResume();
        itemList.clear();
        getAllData();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}