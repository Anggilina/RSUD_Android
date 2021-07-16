package com.example.project_uts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class DetalPasienActivity extends AppCompatActivity {

    TextView idText;
    TextView namaText;
    TextView alamatText;
    TextView nohpText;
    TextView umurText;
    TextView tglLahirText;
    TextView genderText;
    String id, nama, alamat, umur, tglLahir, gender,nohp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detal_pasien);

        idText = (TextView) findViewById(R.id.id);
        namaText = (TextView) findViewById(R.id.name);
        alamatText = (TextView) findViewById(R.id.alamat);
        nohpText = (TextView) findViewById(R.id.nohp);
        umurText = (TextView) findViewById(R.id.umur);
        tglLahirText=(TextView) findViewById(R.id.tglLahir);
        genderText = (TextView) findViewById(R.id.gender);

        id = getIntent().getStringExtra(PasienActivity.TAG_ID);
        nama = getIntent().getStringExtra(PasienActivity.TAG_NAME);
        tglLahir=getIntent().getStringExtra(PasienActivity.TAG_TGLLAHIR);
        gender=getIntent().getStringExtra(PasienActivity.TAG_Gender);
        umur=getIntent().getStringExtra(PasienActivity.TAG_UMUR);
        alamat = getIntent().getStringExtra(PasienActivity.TAG_ADDRESS);
        nohp=getIntent().getStringExtra(PasienActivity.TAG_NOHP);

        if(id==null || id==""){
            Toast.makeText(DetalPasienActivity.this, "Data yang dipilih Tidak Ada ",  Toast.LENGTH_LONG).show();
        }else{
            idText.setText(id);
            namaText.setText(nama);
            alamatText.setText(alamat);
            nohpText.setText(nohp);
            genderText.setText(gender);
            tglLahirText.setText(tglLahir);
            umurText.setText(umur);
        }

    }
}