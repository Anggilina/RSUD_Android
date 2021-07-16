package com.example.project_uts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class DetailObatActivity extends AppCompatActivity {
    TextView idText;
    TextView namaText;
    TextView hargaText;
    TextView stockText;
    TextView satuanText;
    TextView tglKadaluarsaText;
    String id, nama, harga, stock, satuan, tglKadaluarsa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_obat);

        idText = (TextView) findViewById(R.id.id);
        namaText = (TextView) findViewById(R.id.nama_obat);
        hargaText = (TextView) findViewById(R.id.harga);
        stockText = (TextView) findViewById(R.id.stock);
        satuanText = (TextView) findViewById(R.id.satuan);
        tglKadaluarsaText=(TextView) findViewById(R.id.tglKadaluarsa);

        id = getIntent().getStringExtra(ObatActivity.TAG_ID);
        nama = getIntent().getStringExtra(ObatActivity.TAG_NAME);
        harga=getIntent().getStringExtra(ObatActivity.TAG_HARGA);
        stock=getIntent().getStringExtra(ObatActivity.TAG_STOCK);
        satuan=getIntent().getStringExtra(ObatActivity.TAG_SATUAN);
        tglKadaluarsa = getIntent().getStringExtra(ObatActivity.TAG_TGL_KADALUARSA);

        if(id==null || id==""){
            Toast.makeText(DetailObatActivity.this, "Data yang dipilih Tidak Ada ",  Toast.LENGTH_LONG).show();
        }else{
            idText.setText(id);
            namaText.setText(nama);
            hargaText.setText(harga);
            stockText.setText(stock);
            satuanText.setText(satuan);
            tglKadaluarsaText.setText(tglKadaluarsa);
        }
    }
}