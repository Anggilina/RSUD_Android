package com.example.project_uts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class DetailFasilitasActivity extends AppCompatActivity {
    TextView idText;
    TextView namaText;
    TextView lantaiText;
    TextView tarifText;
    TextView fasilitasText;
    String id, nama, lantai, tarif, fasilitas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_fasilitas);

        idText = (TextView) findViewById(R.id.id);
        namaText = (TextView) findViewById(R.id.nama_kamar);
        lantaiText = (TextView) findViewById(R.id.lantai);
        tarifText = (TextView) findViewById(R.id.tarif);
        fasilitasText = (TextView) findViewById(R.id.fasilitas);

        id = getIntent().getStringExtra(FasilitasActivity.TAG_ID);
        nama = getIntent().getStringExtra(FasilitasActivity.TAG_NAME);
        lantai=getIntent().getStringExtra(FasilitasActivity.TAG_LANTAI);
        tarif=getIntent().getStringExtra(FasilitasActivity.TAG_TARIF);
        fasilitas=getIntent().getStringExtra(FasilitasActivity.TAG_FASILITAS);

        if(id==null || id==""){
            Toast.makeText(DetailFasilitasActivity.this, "Data yang dipilih Tidak Ada ",  Toast.LENGTH_LONG).show();
        }else{
            idText.setText(id);
            namaText.setText(nama);
            lantaiText.setText(lantai);
            tarifText.setText(tarif);
            fasilitasText.setText(fasilitas);
        }
    }
}