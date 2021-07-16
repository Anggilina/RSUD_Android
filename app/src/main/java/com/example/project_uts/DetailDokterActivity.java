package com.example.project_uts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class DetailDokterActivity extends AppCompatActivity {
    TextView idText;
    TextView namaText;
    TextView alamatText;
    TextView nohpText;
    TextView umurText;
    TextView tglLahirText;
    TextView genderText;
    TextView spesialisText;
    String id, nama, alamat, umur, tglLahir, gender,nohp,spesialis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_dokter);

        idText = (TextView) findViewById(R.id.id);
        namaText = (TextView) findViewById(R.id.nama);
        alamatText = (TextView) findViewById(R.id.alamat);
        nohpText = (TextView) findViewById(R.id.nohp);
        umurText = (TextView) findViewById(R.id.umur);
        tglLahirText=(TextView) findViewById(R.id.tglLahir);
        genderText = (TextView) findViewById(R.id.gender);
        spesialisText=(TextView) findViewById(R.id.spesialis);

        id = getIntent().getStringExtra(DokterActivity.TAG_ID);
        nama = getIntent().getStringExtra(DokterActivity.TAG_NAME);
        tglLahir=getIntent().getStringExtra(DokterActivity.TAG_TGLLAHIR);
        gender=getIntent().getStringExtra(DokterActivity.TAG_Gender);
        umur=getIntent().getStringExtra(DokterActivity.TAG_UMUR);
        alamat = getIntent().getStringExtra(DokterActivity.TAG_ADDRESS);
        nohp=getIntent().getStringExtra(DokterActivity.TAG_NOHP);
        spesialis=getIntent().getStringExtra(DokterActivity.TAG_SPESIALIS);

        if(id==null || id==""){
            Toast.makeText(DetailDokterActivity.this, "Data yang dipilih Tidak Ada ",  Toast.LENGTH_LONG).show();
        }else{
            idText.setText(id);
            namaText.setText(nama);
            alamatText.setText(alamat);
            nohpText.setText(nohp);
            genderText.setText(gender);
            tglLahirText.setText(tglLahir);
            umurText.setText(umur);
            spesialisText.setText(spesialis);
        }
    }
}