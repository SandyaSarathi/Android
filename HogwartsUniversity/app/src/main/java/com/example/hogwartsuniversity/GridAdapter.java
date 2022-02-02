package com.example.hogwartsuniversity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class GridAdapter extends ArrayAdapter<DataClass> {
    public GridAdapter(@NonNull Context context, @NonNull ArrayList<DataClass> arrayList) {
        super(context,0, arrayList);
    }
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
        {
            View gridView_item = convertView;
            if(gridView_item==null) {

                gridView_item = LayoutInflater.from(getContext()).inflate(R.layout.grid_view_items, parent, false);
            }
            DataClass dataClass = getItem(position);
            TextView lbl_description = gridView_item.findViewById(R.id.lbl_description);
            TextView lbl_count = gridView_item.findViewById(R.id.lbl_count);
            ImageView image = gridView_item.findViewById(R.id.img);
            lbl_description.setText(dataClass.getDescription());
            lbl_count.setText(String.valueOf(dataClass.getCount()));
            image.setImageResource(dataClass.getImg_name());

            return gridView_item;
    }
}
