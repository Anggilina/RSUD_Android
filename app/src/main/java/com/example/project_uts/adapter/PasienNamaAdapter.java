package com.example.project_uts.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.project_uts.R;
import com.example.project_uts.model.Pasien;

import java.util.List;

public class PasienNamaAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Pasien> items;

    public PasienNamaAdapter(Activity activity, List<Pasien> items) {
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
            convertView = inflater.inflate(R.layout.list_spinner_pasien, null);

        TextView name = (TextView) convertView.findViewById(R.id.nama_pasien);

        Pasien data = items.get(position);

        name.setText(data.getNama());

        return convertView;
    }
}
