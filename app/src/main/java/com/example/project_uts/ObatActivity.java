package com.example.project_uts;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.project_uts.adapter.ObatAdapter;

import com.example.project_uts.database.Obatdatabase;
import com.example.project_uts.model.Obat;
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

public class ObatActivity extends AppCompatActivity {

    ListView listView;
    AlertDialog.Builder dialog;
    List<Obat> itemList = new ArrayList<Obat>();
    Obatdatabase SQLite = new Obatdatabase(this);
    ObatAdapter adapter;

    public static final String TAG_ID = "id";
    public static final String TAG_NAME = "nama_kamar";
    public static final String TAG_HARGA = "harga";
    public static final String TAG_STOCK = "stock";
    public static final String TAG_SATUAN = "satuan";
    public static final String TAG_TGL_KADALUARSA = "tgl_kadaluarsa";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obat);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SQLite = new Obatdatabase(getApplicationContext());

        FloatingActionButton fab = findViewById(R.id.fab);
        listView = (ListView) findViewById(R.id.list_view_obat);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ObatActivity.this, TambahObat.class);
                startActivity(intent);
            }
        });
        adapter = new ObatAdapter(ObatActivity.this, itemList);
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(final AdapterView<?> parent, View view,
                                           final int position, long id) {
                // TODO Auto-generated method stub
                final String idx = itemList.get(position).getId();
                final String name = itemList.get(position).getNama_obat();
                final String harga = itemList.get(position).getHarga();
                final String stock = itemList.get(position).getStock();
                final String satuan = itemList.get(position).getSatuan();
                final String tgl_kadaluarsa = itemList.get(position).getTgl_kadaluarsa();

                final CharSequence[] dialogitem = {"Lihat", "Edit", "Delete"};
                dialog = new AlertDialog.Builder(ObatActivity.this);
                dialog.setCancelable(true);
                dialog.setItems(dialogitem, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        switch (which) {
                            case 0:
                                Intent intent1 = new Intent(ObatActivity.this, DetalPasienActivity.class);
                                intent1.putExtra(TAG_ID, idx);
                                intent1.putExtra(TAG_NAME, name);
                                intent1.putExtra(TAG_HARGA, harga);
                                intent1.putExtra(TAG_STOCK, stock);
                                intent1.putExtra(TAG_SATUAN, satuan);
                                intent1.putExtra(TAG_TGL_KADALUARSA, tgl_kadaluarsa);
                                startActivity(intent1);
                                break;
                            case 1:
                                Intent intent = new Intent(ObatActivity.this, TambahObat.class);
                                intent.putExtra(TAG_ID, idx);
                                intent.putExtra(TAG_NAME, name);
                                intent.putExtra(TAG_HARGA, harga);
                                intent.putExtra(TAG_STOCK, stock);
                                intent.putExtra(TAG_SATUAN, satuan);
                                intent.putExtra(TAG_TGL_KADALUARSA, tgl_kadaluarsa);
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
            String harga = row.get(i).get(TAG_HARGA);
            String stock = row.get(i).get(TAG_STOCK);
            String satuan= row.get(i).get(TAG_SATUAN);
            String tgl_kadaluarsa = row.get(i).get(TAG_TGL_KADALUARSA);

            Obat data = new Obat();

            data.setId(id);
            data.setNama_obat(poster);
            data.setHarga(harga);
            data.setStock(stock);
            data.setSatuan(satuan);
            data.setTgl_kadaluarsa(tgl_kadaluarsa);


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