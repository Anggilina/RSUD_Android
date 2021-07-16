package com.example.project_uts;

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

import androidx.appcompat.app.AppCompatActivity;

import com.example.project_uts.database.Dokterdatabase;

import java.util.Calendar;

public class TambahDokter extends AppCompatActivity {
    DatePickerDialog picker;
    EditText idField;
    EditText namaField;
    EditText alamatField;
    EditText nohpField;
    EditText umurField;
    EditText tglLahirField;
    EditText spesialisField;
    EditText genderField1;
    RadioGroup genderField;
    RadioButton radioSexButton;
    ImageButton calender;
    Button simpan;
    Button cancel;
    String id, nama, alamat, umur, tglLahir, gender,nohp,spesialis;
    Dokterdatabase SQLite = new Dokterdatabase(this);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tambah_dokter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        idField = (EditText) findViewById(R.id.id);
        namaField = (EditText) findViewById(R.id.name);
        alamatField = (EditText) findViewById(R.id.alamat);
        nohpField = (EditText) findViewById(R.id.nohp);
        umurField = (EditText) findViewById(R.id.umur);
        tglLahirField=(EditText) findViewById(R.id.tglLahir);
        genderField = (RadioGroup) findViewById(R.id.gender);
        spesialisField=(EditText) findViewById(R.id.spesialis);

        calender=(ImageButton) findViewById(R.id.tgl);
        simpan = (Button) findViewById(R.id.simpan);
        cancel = (Button) findViewById(R.id.cencel);

        // get selected radio button from radioGroup
        int selectedId = genderField.getCheckedRadioButtonId();

        // find the radiobutton by returned id
        radioSexButton = (RadioButton) findViewById(selectedId);

        id = getIntent().getStringExtra(DokterActivity.TAG_ID);
        nama = getIntent().getStringExtra(DokterActivity.TAG_NAME);
        tglLahir=getIntent().getStringExtra(DokterActivity.TAG_TGLLAHIR);
        gender=getIntent().getStringExtra(DokterActivity.TAG_Gender);
        umur=getIntent().getStringExtra(DokterActivity.TAG_UMUR);
        alamat = getIntent().getStringExtra(DokterActivity.TAG_ADDRESS);
        nohp=getIntent().getStringExtra(DokterActivity.TAG_NOHP);
        spesialis=getIntent().getStringExtra(DokterActivity.TAG_SPESIALIS);

        calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(TambahDokter.this,
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
            spesialisField.setText(spesialis);
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
        spesialisField.setText(null);
    }

    // Save data to SQLite database
    private void save() {
        if (String.valueOf(namaField.getText()).equals(null) || String.valueOf(alamatField.getText()).equals(null) ||
                String.valueOf(nohpField.getText()).equals(null) || String.valueOf(umurField.getText()).equals(null)
                || String.valueOf(tglLahirField.getText()).equals(null) ) {
            Toast.makeText(getApplicationContext(),
                    "Ada Yang Belum Terisi", Toast.LENGTH_SHORT).show();
        } else {
            SQLite.insert(namaField.getText().toString().trim(), tglLahirField.getText().toString().trim(),radioSexButton.getText().toString(),
                    umurField.getText().toString().trim(),alamatField.getText().toString().trim(),
                    nohpField.getText().toString().trim(), spesialisField.getText().toString().trim());
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
                    nohpField.getText().toString().trim(),spesialisField.getText().toString().trim());
            blank();
            finish();
        }
    }

}
