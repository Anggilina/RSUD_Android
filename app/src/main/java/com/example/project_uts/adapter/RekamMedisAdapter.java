package com.example.project_uts.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.project_uts.R;
import com.example.project_uts.model.RekamMedis;

import java.util.List;

public class RekamMedisAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<RekamMedis> items;

    public RekamMedisAdapter(Activity activity, List<RekamMedis> items) {
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
        TextView name = (TextView) convertView.findViewById(R.id.nama_pasien);
        TextView dokter = (TextView) convertView.findViewById(R.id.nama_dokter);

        RekamMedis data = items.get(position);

        id.setText(data.getId());
        name.setText(data.getNama_pasien());
        dokter.setText(data.getNama_pasien());

        return convertView;
    }
}
