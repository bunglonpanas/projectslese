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
import com.appinventory.Model.ModelUser;
import com.appinventory.R;

import java.util.ArrayList;

public class RecordListUserAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<ModelUser> recordList;

    public RecordListUserAdapter(Context context, int layout, ArrayList<ModelUser> recordList) {
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

    private class ViewHolder {
        ImageView imageView;
        TextView txtName , txtTelp;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View row = view;
        RecordListUserAdapter.ViewHolder holder = new RecordListUserAdapter.ViewHolder();

        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.txtName = row.findViewById(R.id.txtName);
            holder.txtTelp = row.findViewById(R.id.txtTelp);
            holder.imageView = row.findViewById(R.id.imgSctok);
            row.setTag(holder);
        } else {
            holder = (RecordListUserAdapter.ViewHolder) row.getTag();
        }

        ModelUser model = recordList.get(i);

//        holder.txtName.setText(model.getName());
        holder.txtName.setText(model.getName());
        holder.txtTelp.setText(model.getTelp());

        byte[] recordImage = model.getImage();
        if (recordImage != null) {

            Bitmap bitmap = BitmapFactory.decodeByteArray(recordImage, 0, recordImage.length);
            holder.imageView.setImageBitmap(bitmap);

        }

        return row;
    }
}