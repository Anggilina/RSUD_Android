package com.example.project_uts;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.project_uts.adapter.PasienAdapter;
import com.example.project_uts.database.Pasiendatabase;
import com.example.project_uts.model.Pasien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Adapter;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PasienActivity extends AppCompatActivity {

    ListView listView;
    AlertDialog.Builder dialog;
    List<Pasien> itemList = new ArrayList<Pasien>();
    Pasiendatabase SQLite = new Pasiendatabase(this);
    PasienAdapter adapter;

    public static final String TAG_ID = "id";
    public static final String TAG_NAME = "nama";
    public static final String TAG_ADDRESS = "alamat";
    public static final String TAG_NOHP = "nohp";
    public static final String TAG_UMUR = "umur";
    public static final String TAG_Gender = "gender";
    public static final String TAG_TGLLAHIR = "tglLahir";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pasien);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SQLite = new Pasiendatabase(getApplicationContext());

        FloatingActionButton fab = findViewById(R.id.fab);
        listView = (ListView) findViewById(R.id.list_view_pasient);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PasienActivity.this, TambahPasien.class);
                startActivity(intent);
            }
        });
        adapter= new PasienAdapter(PasienActivity.this, itemList);
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(final AdapterView<?> parent, View view,
                                           final int position, long id) {
                // TODO Auto-generated method stub
                final String idx = itemList.get(position).getId();
                final String name = itemList.get(position).getNama();
                final String address = itemList.get(position).getAlamat();
                final String umur = itemList.get(position).getUmur();
                final String tglLahir= itemList.get(position).getTglLahir();
                final String gender=itemList.get(position).getGender();
                final String nohp=itemList.get(position).getNohp();

                final CharSequence[] dialogitem = {"Lihat","Edit", "Delete"};
                dialog = new AlertDialog.Builder(PasienActivity.this);
                dialog.setCancelable(true);
                dialog.setItems(dialogitem, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        switch (which) {
                            case 0:
                                Intent intent1 = new Intent(PasienActivity.this, DetalPasienActivity.class);
                                intent1.putExtra(TAG_ID, idx);
                                intent1.putExtra(TAG_NAME, name);
                                intent1.putExtra(TAG_TGLLAHIR,tglLahir);
                                intent1.putExtra(TAG_Gender,gender);
                                intent1.putExtra(TAG_NOHP,nohp);
                                intent1.putExtra(TAG_ADDRESS, address);
                                intent1.putExtra(TAG_UMUR, umur);
                                startActivity(intent1);
                                break;
                            case 1:
                                Intent intent = new Intent(PasienActivity.this, TambahPasien.class);
                                intent.putExtra(TAG_ID, idx);
                                intent.putExtra(TAG_NAME, name);
                                intent.putExtra(TAG_TGLLAHIR,tglLahir);
                                intent.putExtra(TAG_Gender,gender);
                                intent.putExtra(TAG_NOHP,nohp);
                                intent.putExtra(TAG_ADDRESS, address);
                                intent.putExtra(TAG_UMUR, umur);
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
            String tglLahir = row.get(i).get(TAG_TGLLAHIR);
            String gender = row.get(i).get(TAG_Gender);
            String noHp = row.get(i).get(TAG_NOHP);
            String umur = row.get(i).get(TAG_UMUR);
            String title = row.get(i).get(TAG_ADDRESS);

            Pasien data = new Pasien();

            data.setId(id);
            data.setNama(poster);
            data.setTglLahir(tglLahir);
            data.setGender(gender);
            data.setNohp(noHp);
            data.setUmur(umur);
            data.setAlamat(title);



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
