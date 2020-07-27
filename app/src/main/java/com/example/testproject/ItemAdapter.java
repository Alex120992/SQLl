package com.example.testproject;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


class ItemHolder extends RecyclerView.ViewHolder {
    TextView number;
    TextView data;

    public ItemHolder(View itemView) {
        super(itemView);
        number = (TextView) itemView.findViewById(R.id.number);
        data = (TextView) itemView.findViewById(R.id.text);

    }


}

class ItemAdapter extends RecyclerView.Adapter<ItemHolder> {

    Cursor mCursor;
    Context mContext;

    ItemAdapter(Context context, Cursor cursor) {
        mCursor = cursor;
        mContext = context;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_element, parent, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        if (!mCursor.moveToPosition(position))
            return; // используется при создании таблицы, если отсутсвует курсор то исключение


        String number = mCursor.getString(mCursor.getColumnIndex(CreateDbSchem.Table.Columns.NUMBER));
        String data = mCursor.getString(mCursor.getColumnIndex(CreateDbSchem.Table.Columns.DATA));
        holder.number.setText(number);
        holder.data.setText(data);


    }


    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public void swapCursor(Cursor cursor) {
        if (mCursor != null) {
            mCursor.close(); // Закрываем курсор если не пустой
        }
        mCursor = cursor;
        if (mCursor != null) notifyDataSetChanged();
//            notifyItemChanged(mCursor.getPosition()); // если не пустой то обновляем список


    }
}



