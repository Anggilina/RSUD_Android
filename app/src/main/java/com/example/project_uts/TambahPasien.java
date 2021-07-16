package com.example.project_uts;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project_uts.database.Pasiendatabase;
import com.example.project_uts.model.Pasien;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class TambahPasien extends AppCompatActivity {
     Pasiendatabase mDatabase;
    DatePickerDialog picker;
    EditText idField;
    EditText namaField;
    EditText alamatField;
    EditText nohpField;
    EditText umurField;
    EditText tglLahirField;
    EditText genderField1;
    RadioGroup genderField;
    RadioButton radioSexButton;
    ImageButton calender;
    Button simpan;
    Button cancel;
    String id, nama, alamat, umur, tglLahir, gender,nohp;
    Pasiendatabase SQLite = new Pasiendatabase(this);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tambah_pasien);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        idField = (EditText) findViewById(R.id.id);
        namaField = (EditText) findViewById(R.id.name);
        alamatField = (EditText) findViewById(R.id.alamat);
        nohpField = (EditText) findViewById(R.id.nohp);
        umurField = (EditText) findViewById(R.id.umur);
        tglLahirField=(EditText) findViewById(R.id.tglLahir);
        genderField = (RadioGroup) findViewById(R.id.gender);
        calender=(ImageButton) findViewById(R.id.tgl);
        simpan = (Button) findViewById(R.id.simpan);
        cancel = (Button) findViewById(R.id.cencel);

        // get selected radio button from radioGroup
        int selectedId = genderField.getCheckedRadioButtonId();

        // find the radiobutton by returned id
        radioSexButton = (RadioButton) findViewById(selectedId);

        id = getIntent().getStringExtra(PasienActivity.TAG_ID);
        nama = getIntent().getStringExtra(PasienActivity.TAG_NAME);
        tglLahir=getIntent().getStringExtra(PasienActivity.TAG_TGLLAHIR);
        gender=getIntent().getStringExtra(PasienActivity.TAG_Gender);
        umur=getIntent().getStringExtra(PasienActivity.TAG_UMUR);
        alamat = getIntent().getStringExtra(PasienActivity.TAG_ADDRESS);
        nohp=getIntent().getStringExtra(PasienActivity.TAG_NOHP);

        calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(TambahPasien.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                tglLahirField.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
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
            alamatField.setText(alamat);
            nohpField.setText(nohp);
            tglLahirField.setText(tglLahir);
            umurField.setText(umur);
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
        alamatField.setText(null);
        nohpField.setText(null);
        umurField.setText(null);
        tglLahirField.setText(null);
    }

    // Save data to SQLite database
    private void save() {
        if (String.valueOf(namaField.getText()).equals(null) || String.valueOf(alamatField.getText()).equals(null) ||
                String.valueOf(nohpField.getText()).equals(null) || String.valueOf(umurField.getText()).equals(null)
                || String.valueOf(tglLahirField.getText()).equals(null)) {
            Toast.makeText(getApplicationContext(),
                    "Ada Yang Belum Terisi", Toast.LENGTH_SHORT).show();
        } else {
            SQLite.insert(namaField.getText().toString().trim(), tglLahirField.getText().toString().trim(),radioSexButton.getText().toString(),
                    umurField.getText().toString().trim(),alamatField.getText().toString().trim(),
                    nohpField.getText().toString().trim());
            blank();
            finish();
        }
    }

    // Update data in SQLite database
    private void edit() {
        if (String.valueOf(namaField.getText()).equals(null) || String.valueOf(alamatField.getText()).equals(null) ||
                String.valueOf(nohpField.getText()).equals(null) || String.valueOf(umurField.getText()).equals(null)
                || String.valueOf(tglLahirField.getText()).equals(null))  {
            Toast.makeText(getApplicationContext(),
                    "Ada Yang Belum Terisi", Toast.LENGTH_SHORT).show();
        } else {
            SQLite.update(Integer.parseInt(idField.getText().toString().trim()),namaField.getText().toString().trim(), tglLahirField.getText().toString().trim(),radioSexButton.getText().toString().trim(),
                    umurField.getText().toString().trim(),alamatField.getText().toString().trim(),
                    nohpField.getText().toString().trim());
            blank();
            finish();
        }
    }


}
