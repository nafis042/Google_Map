package com.example.nafis.google_map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.nafis.google_map.models.MovieModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 11050 on 3/4/2017.
 */

public class MyCustomBaseAdapter  extends BaseAdapter {
     private static List<MovieModel> searchArrayList;
      private LayoutInflater mInflater;
    public MyCustomBaseAdapter(Context context, List<MovieModel> results) {
        searchArrayList = results;
        mInflater = LayoutInflater.from(context);
        }
    public int getCount() {
        return searchArrayList.size();
        }
    public Object getItem(int position) {
        return searchArrayList.get(position);
        }
    public long getItemId(int position) {
        return position;
        }
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.custom_row_view, null);
            holder = new ViewHolder();
            holder.txtName = (TextView) convertView.findViewById(R.id.name);
            holder.txtLongitude = (TextView) convertView.findViewById(R.id.cityState);
            holder.txtLatitude = (TextView) convertView.findViewById(R.id.phone);
            convertView.setTag(holder);
            } else {
            holder = (ViewHolder) convertView.getTag();
            }

        holder.txtName.setText(searchArrayList.get(position).getName());
        holder.txtLongitude.setText(searchArrayList.get(position).getLongitude());
        holder.txtLatitude.setText(searchArrayList.get(position).getLatitude());
        return convertView;
        }
    static class ViewHolder {
        TextView txtName;
        TextView txtLongitude;
        TextView txtLatitude;
        }
}