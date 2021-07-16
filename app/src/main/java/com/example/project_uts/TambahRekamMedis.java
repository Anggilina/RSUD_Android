package com.example.project_uts;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.example.project_uts.adapter.PasienNamaAdapter;
import com.example.project_uts.database.Dokterdatabase;
import com.example.project_uts.database.Pasiendatabase;
import com.example.project_uts.database.RekamMedisDatabase;
import com.example.project_uts.model.Pasien;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TambahRekamMedis extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    Pasiendatabase mDatabase;
    DatePickerDialog picker;
    EditText idField;
    Spinner nama_pasienField;
    Spinner nama_dokterField;
    EditText tgl_pengobatanField;
    EditText waktu_pengobatanField;
    EditText keluhan_pasienField;
    EditText hasil_diagnosaField;
    EditText biayaField;
    ImageView calender;
    Button simpan;
    Button cancel;
    String nama_pasien2;
    String nama_dokter2;
    String id, nama_pasien, nama_dokter, tgl_pengobatan, waktu_pengobatan, keluhan_pasien,hasil_diagnosa, biaya;
    RekamMedisDatabase SQLite = new RekamMedisDatabase(this);
    PasienNamaAdapter adapter;
    List<Pasien> listPasien = new ArrayList<Pasien>();
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tambah_rekam_medis);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        idField = (EditText) findViewById(R.id.id);
        nama_pasienField = (Spinner) findViewById(R.id.nama_pasien);
        nama_dokterField = (Spinner) findViewById(R.id.nama_dokter);
        tgl_pengobatanField = (EditText) findViewById(R.id.tglPengobatan);
        waktu_pengobatanField = (EditText) findViewById(R.id.waktuPengobatan);
        keluhan_pasienField=(EditText) findViewById(R.id.keluhan_pasien);
        hasil_diagnosaField=(EditText) findViewById(R.id.hasil_diagnosa);
        biayaField = (EditText) findViewById(R.id.biaya);
        calender=(ImageView) findViewById(R.id.tgl);
        simpan = (Button) findViewById(R.id.simpan);
        cancel = (Button) findViewById(R.id.cencel);


        id = getIntent().getStringExtra(RekamMedisActivity.TAG_ID);
        nama_pasien = getIntent().getStringExtra(RekamMedisActivity.TAG_NAMA_PASIEN);
        nama_dokter=getIntent().getStringExtra(RekamMedisActivity.TAG_NAMA_DOKTER);
        tgl_pengobatan=getIntent().getStringExtra(RekamMedisActivity.TAG_TGL_PEROBATAN);
        waktu_pengobatan=getIntent().getStringExtra(RekamMedisActivity.TAG_WAKTU_PEROBATAN);
        keluhan_pasien = getIntent().getStringExtra(RekamMedisActivity.TAG_KELUHAN_PASIEN);
        hasil_diagnosa=getIntent().getStringExtra(RekamMedisActivity.TAG_HASIL_DIAGNOSA);
        biaya=getIntent().getStringExtra(RekamMedisActivity.TAG_BIAYA);

        nama_pasienField.setOnItemSelectedListener(this);
        nama_dokterField.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position,
                                       long id) {
                // On selecting a spinner item
                nama_dokter2 = parent.getItemAtPosition(position).toString();

                // Showing selected spinner item
                Toast.makeText(parent.getContext(), "You selected: " + nama_dokter2,
                        Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });

        loadSpinner();

        loadSpinnerDokter();

        calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(TambahRekamMedis.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                tgl_pengobatanField.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        if (id == null || id == "") {
            setTitle("Tambah Data");
        } else {
            setTitle("Edit Data");
            idField.setText(id);
            tgl_pengobatanField.setText(tgl_pengobatan);
            waktu_pengobatanField.setText(waktu_pengobatan);
            keluhan_pasienField.setText(keluhan_pasien);
            hasil_diagnosaField.setText(hasil_diagnosa);
            biayaField.setText(biaya);
        }

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (idField.getText().toString().equals("")) {
                        save();
                    } else {
                        edit();
                    }
                } catch (Exception e){
                    Log.e("Submit", e.toString());
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blank();
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    // Make blank all Edit Text
    private void blank() {
        tgl_pengobatanField.setText(null);
        waktu_pengobatanField.setText(null);
        keluhan_pasienField.setText(null);
        hasil_diagnosaField.setText(null);
        biayaField.setText(null);
    }

    // Save data to SQLite database
    private void save() {
        if (String.valueOf(tgl_pengobatanField.getText()).equals(null)
                || String.valueOf(waktu_pengobatanField.getText()).equals(null) ) {
            Toast.makeText(getApplicationContext(),
                    "Ada Yang Belum Terisi", Toast.LENGTH_SHORT).show();
        } else {
            SQLite.insert(nama_pasien2,
                    nama_dokter2,
                    tgl_pengobatanField.getText().toString().trim(),
                    waktu_pengobatanField.getText().toString(),
                    keluhan_pasienField.getText().toString().trim(),
                    hasil_diagnosaField.getText().toString().trim(),
                    biayaField.getText().toString().trim());
            blank();
            finish();
        }
    }

    // Update data in SQLite database
    private void edit() {
        if (String.valueOf(tgl_pengobatanField.getText()).equals(null)
                || String.valueOf(waktu_pengobatanField.getText()).equals(null))  {
            Toast.makeText(getApplicationContext(),
                    "Ada Yang Belum Terisi", Toast.LENGTH_SHORT).show();
        } else {
            SQLite.update(Integer.parseInt(idField.getText().toString().trim()),nama_pasien2,nama_dokter2, tgl_pengobatanField.getText().toString().trim(),waktu_pengobatanField.getText().toString(),
                    keluhan_pasienField.getText().toString().trim(),hasil_diagnosaField.getText().toString().trim(),
                    biayaField.getText().toString().trim());
            blank();
            finish();
        }
    }

    private void loadSpinner() {
        // database handler
        Pasiendatabase db = new Pasiendatabase(getApplicationContext());

        // Spinner Drop down elements
        List<String> lables = db.getAllNama();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lables);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        nama_pasienField.setAdapter(dataAdapter);
    }


    private void loadSpinnerDokter() {
        // database handler
        Dokterdatabase db = new Dokterdatabase(getApplicationContext());

        // Spinner Drop down elements
        List<String> lables = db.getAllNama();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lables);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        nama_dokterField.setAdapter(dataAdapter);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {
        // On selecting a spinner item
        nama_pasien2 = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "You selected: " + nama_pasien2,
                Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }

}