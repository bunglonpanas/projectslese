package com.appinventory.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.appinventory.Model.Model;
import com.appinventory.R;

import java.util.ArrayList;

public class RecordListStockAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Model> recordList;

    public RecordListStockAdapter(Context context, int layout, ArrayList<Model> recordList) {
        this.context = context;
        this.layout = layout;
        this.recordList = recordList;
    }

    @Override
    public int getCount() {
        return recordList.size();
    }

    @Override
    public Object getItem(int i) {
        return recordList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private class ViewHolder{
        ImageView imageView;
        TextView  txtTags;
        EditText txtQty;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View row = view;
        RecordListStockAdapter.ViewHolder holder = new RecordListStockAdapter.ViewHolder();

        if (row==null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.txtTags = row.findViewById(R.id.descTags);
            holder.txtQty = row.findViewById(R.id.stockLimit);
            holder.imageView = row.findViewById(R.id.imgSctok);
            row.setTag(holder);
        }
        else {
            holder = (RecordListStockAdapter.ViewHolder)row.getTag();
        }

        Model model = recordList.get(i);

//        holder.txtName.setText(model.getName());
        holder.txtTags.setText(model.getTags());
        holder.txtQty.setText(model.getCurrentStock().toString());

        byte[] recordImage = model.getImage();
        if( recordImage != null){

            Bitmap bitmap = BitmapFactory.decodeByteArray(recordImage, 0, recordImage.length);
            holder.imageView.setImageBitmap(bitmap);

        }

        return row;
    }
}
