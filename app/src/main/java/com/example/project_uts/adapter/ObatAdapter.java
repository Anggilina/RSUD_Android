package com.example.project_uts.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.project_uts.R;
import com.example.project_uts.model.Obat;

import java.util.List;

public class ObatAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Obat> items;

    public ObatAdapter(Activity activity, List<Obat> items) {
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
            convertView = inflater.inflate(R.layout.list_obat, null);

        TextView id = (TextView) convertView.findViewById(R.id.id);
        TextView name = (TextView) convertView.findViewById(R.id.nama);
        TextView stock = (TextView) convertView.findViewById(R.id.stock);
        TextView satuan = (TextView) convertView.findViewById(R.id.satuan);
        TextView tglKadaluarsa = (TextView) convertView.findViewById(R.id.tglKadaluarsa);

        Obat data = items.get(position);

        id.setText(data.getId());
        name.setText(data.getNama_obat());
        stock.setText(data.getStock());
        satuan.setText(data.getSatuan());
        tglKadaluarsa.setText(data.getTgl_kadaluarsa());

        return convertView;
    }
}
