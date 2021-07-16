package com.example.project_uts;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class MainActivity extends AppCompatActivity {
    ImageView GambarGif;
    ImageView pasien;
    ImageView dokter;
    ImageView fasilitas;
    ImageView obat;
    ImageView list;
    Button keluar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        keluar=(Button) findViewById(R.id.keluar);

        GambarGif = (ImageView)findViewById(R.id.bff);

        Glide.with(MainActivity.this)
                .load(R.drawable.bff)
                .asGif()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(GambarGif);

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pasien:
                Intent pasien = new Intent (getApplicationContext(), PasienActivity.class);
                startActivity(pasien);
                break;
            case R.id.dokter:
                Intent dokter = new Intent (getApplicationContext(), DokterActivity.class);
                startActivity(dokter);
                break;
            /*case R.id.fasilitas:
                Intent fasilitas = new Intent (getApplicationContext(), FasilitasActivity.class);
                startActivity(fasilitas);
                break;
            case R.id.obat:
                Intent obat = new Intent (getApplicationContext(), ObatActivity.class);
                startActivity(obat);
                break;
            case R.id.list:
                Intent list = new Intent (getApplicationContext(), RekamMedisActivity.class);
                startActivity(list);
                break;*/
        }
    }

    public void Keluar(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Konfirmasi!").
                setMessage("Apakah Anda yakin ingin Keluar?");
        builder.setPositiveButton("Ya",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent i = new Intent(getApplicationContext(),
                                LoginActivity.class);
                        startActivity(i);
                    }
                });
        builder.setNegativeButton("Tidak",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert11 = builder.create();
        alert11.show();
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater=  getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==R.id.menu){
            startActivity(new Intent(this,MainActivity.class));
        }
        else if(item.getItemId()==R.id.tentang){
            startActivity(new Intent(this,TentangActivity.class));
        }
        else if(item.getItemId()==R.id.lokasi){
            startActivity(new Intent(this,MainActivity.class));
        }
        return true;
    }
}