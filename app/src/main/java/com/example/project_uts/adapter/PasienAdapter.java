package com.example.project_uts.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.project_uts.R;
import com.example.project_uts.database.Pasiendatabase;
import com.example.project_uts.model.Pasien;

import java.util.List;

public class PasienAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Pasien> items;

    public PasienAdapter(Activity activity, List<Pasien> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int location) {
        return items.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_pasien, null);

        TextView id = (TextView) convertView.findViewById(R.id.id);
        TextView name = (TextView) convertView.findViewById(R.id.nama);
        TextView address = (TextView) convertView.findViewById(R.id.alamat);

        Pasien data = items.get(position);

        id.setText(data.getId());
        name.setText(data.getNama());
        address.setText(data.getAlamat());

        return convertView;
    }
}
