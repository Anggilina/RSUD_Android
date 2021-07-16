package com.example.project_uts;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.project_uts.database.Fasilitasdatabase;

import java.util.Calendar;

public class TambahFasilitas extends AppCompatActivity {
    DatePickerDialog picker;
    EditText idField;
    EditText namaField;
    EditText lantaiField;
    EditText tarifField;
    EditText fasilitasField;
    Button simpan;
    Button cancel;
    String id, nama, lantai, tarif, fasilitas;
    Fasilitasdatabase SQLite = new Fasilitasdatabase(this);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tambah_fasilitas);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        idField = (EditText) findViewById(R.id.id);
        namaField = (EditText) findViewById(R.id.nama_kamar);
        lantaiField = (EditText) findViewById(R.id.lantai);
        tarifField = (EditText) findViewById(R.id.tarif);
        fasilitasField = (EditText) findViewById(R.id.fasilitas);
        simpan = (Button) findViewById(R.id.simpan);
        cancel = (Button) findViewById(R.id.cencel);

        // get selected radio button from radioGroup

        id = getIntent().getStringExtra(FasilitasActivity.TAG_ID);
        nama = getIntent().getStringExtra(FasilitasActivity.TAG_NAME);
        lantai=getIntent().getStringExtra(FasilitasActivity.TAG_LANTAI);
        tarif=getIntent().getStringExtra(FasilitasActivity.TAG_TARIF);
        fasilitas=getIntent().getStringExtra(FasilitasActivity.TAG_FASILITAS);


        if (id == null || id == "") {
            setTitle("Tambah Data");
        } else {
            setTitle("Edit Data");
            idField.setText(id);
            namaField.setText(nama);
            lantaiField.setText(lantai);
            tarifField.setText(tarif);
            fasilitasField.setText(fasilitas);
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
        namaField.requestFocus();
        lantaiField.setText(null);
        tarifField.setText(null);
        fasilitasField.setText(null);
    }

    // Save data to SQLite database
    private void save() {
        if (String.valueOf(namaField.getText()).equals(null) || String.valueOf(lantaiField.getText()).equals(null) ||
                String.valueOf(tarifField.getText()).equals(null) || String.valueOf(fasilitasField.getText()).equals(null)) {
            Toast.makeText(getApplicationContext(),
                    "Ada Yang Belum Terisi", Toast.LENGTH_SHORT).show();
        } else {
            SQLite.insert(namaField.getText().toString().trim(), lantaiField.getText().toString().trim(),
                    tarifField.getText().toString().trim(),fasilitasField.getText().toString().trim());
            blank();
            finish();
        }
    }

    // Update data in SQLite database
    private void edit() {
        if (String.valueOf(namaField.getText()).equals(null) || String.valueOf(lantaiField.getText()).equals(null) ||
                String.valueOf(tarifField.getText()).equals(null) || String.valueOf(fasilitasField.getText()).equals(null)){
            Toast.makeText(getApplicationContext(),
                    "Ada Yang Belum Terisi", Toast.LENGTH_SHORT).show();
        } else {
            SQLite.update(Integer.parseInt(idField.getText().toString().trim()),namaField.getText().toString().trim(),
                    lantaiField.getText().toString().trim(),tarifField.getText().toString().trim(),
                    fasilitasField.getText().toString().trim());
            blank();
            finish();
        }
    }
}