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
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.project_uts.database.Obatdatabase;

import java.util.Calendar;

public class TambahObat extends AppCompatActivity {

    DatePickerDialog picker;
    EditText idField;
    EditText namaField;
    EditText hargaField;
    EditText stockField;
    EditText satuanField;
    EditText tglKadaluarsaField;
    ImageView calender;
    Button simpan;
    Button cancel;
    String id, nama, harga, stock,satuan,tglKadaluarsa;
    Obatdatabase SQLite = new Obatdatabase(this);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tambah_obat);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        idField = (EditText) findViewById(R.id.id);
        namaField = (EditText) findViewById(R.id.name);
        hargaField = (EditText) findViewById(R.id.harga);
        stockField = (EditText) findViewById(R.id.stock);
        satuanField = (EditText) findViewById(R.id.satuan);
        tglKadaluarsaField=(EditText) findViewById(R.id.tglKadaluarsa);
        calender=(ImageView) findViewById(R.id.tgl);
        simpan = (Button) findViewById(R.id.simpan);
        cancel = (Button) findViewById(R.id.cencel);

        // get selected radio button from radioGroup


        id = getIntent().getStringExtra(ObatActivity.TAG_ID);
        nama = getIntent().getStringExtra(ObatActivity.TAG_NAME);
        harga=getIntent().getStringExtra(ObatActivity.TAG_HARGA);
        stock=getIntent().getStringExtra(ObatActivity.TAG_STOCK);
        satuan=getIntent().getStringExtra(ObatActivity.TAG_SATUAN);
        tglKadaluarsa = getIntent().getStringExtra(ObatActivity.TAG_TGL_KADALUARSA);

        calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(TambahObat.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                tglKadaluarsaField.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
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
            namaField.setText(nama);
            hargaField.setText(harga);
            stockField.setText(stock);
            satuanField.setText(satuan);
            tglKadaluarsaField.setText(tglKadaluarsa);
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
        hargaField.setText(null);
        stockField.setText(null);
        satuanField.setText(null);
        tglKadaluarsaField.setText(null);
    }

    // Save data to SQLite database
    private void save() {
        if (String.valueOf(namaField.getText()).equals(null) || String.valueOf(hargaField.getText()).equals(null) ||
                String.valueOf(stockField.getText()).equals(null) || String.valueOf(satuanField.getText()).equals(null)
                || String.valueOf(tglKadaluarsaField.getText()).equals(null)) {
            Toast.makeText(getApplicationContext(),
                    "Ada Yang Belum Terisi", Toast.LENGTH_SHORT).show();
        } else {
            SQLite.insert(namaField.getText().toString().trim(), hargaField.getText().toString().trim(),stockField.getText().toString().trim(),
                    satuanField.getText().toString().trim(),tglKadaluarsaField.getText().toString().trim());
            blank();
            finish();
        }
    }

    // Update data in SQLite database
    private void edit() {
        if (String.valueOf(namaField.getText()).equals(null) || String.valueOf(hargaField.getText()).equals(null) ||
                String.valueOf(stockField.getText()).equals(null) || String.valueOf(satuanField.getText()).equals(null)
                || String.valueOf(tglKadaluarsaField.getText()).equals(null))  {
            Toast.makeText(getApplicationContext(),
                    "Ada Yang Belum Terisi", Toast.LENGTH_SHORT).show();
        } else {
            SQLite.update(Integer.parseInt(idField.getText().toString().trim()),namaField.getText().toString().trim(), hargaField.getText().toString().trim(),
                    stockField.getText().toString().trim(),satuanField.getText().toString().trim(),
                    tglKadaluarsaField.getText().toString().trim());
            blank();
            finish();
        }
    }
}