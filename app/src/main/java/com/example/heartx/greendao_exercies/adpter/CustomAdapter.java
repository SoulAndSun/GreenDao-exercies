package com.example.heartx.greendao_exercies.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.heartx.greendao_exercies.R;
import com.example.heartx.greendao_exercies.util.test.AdaptiveScreenBeta;

import java.util.List;


public class CustomAdapter extends BaseAdapter {

    private List<String> data;

    private LayoutInflater inflater;

    public CustomAdapter(Context c, List<String> data) {
        this.data = data;
        inflater = LayoutInflater.from(c);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Holder holder;

        if (convertView == null) {
            holder = new Holder();
            convertView = inflater.inflate(R.layout.list_item, parent, false);
            holder.tv1 = (TextView) convertView.findViewById(R.id.tv1);

            convertView.setTag(holder);
            AdaptiveScreenBeta.adaptive(convertView).start();

        } else {
            holder = (Holder) convertView.getTag();
        }

        holder.tv1.setText(data.get(position));

        return convertView;
    }

    private class Holder {
        private TextView tv1;
    }
}
