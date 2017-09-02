package com.shir.androidfinalproject.Fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.shir.androidfinalproject.Models.Votes;
import com.shir.androidfinalproject.R;

import java.util.ArrayList;

/**
 * Created by shir on 02-Sep-17.
 */
public class VotesListAdapter<T extends Votes> extends BaseAdapter implements View.OnClickListener {

    private LayoutInflater lyt_Inflater = null;

    private ArrayList<T> lstVotes;
    private Context context;

    @Override
    public int getCount() {
        return lstVotes.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public VotesListAdapter(Context cnt, ArrayList<T> items)
    {
        this.context=cnt;
        this.lstVotes = items;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        View view_lyt = convertView;
        try
        {
            if(lstVotes.size()>0)
            {
                final T item =lstVotes.get(position);
                String Name= item.toString();

                lyt_Inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view_lyt = lyt_Inflater.inflate(R.layout.item_row, null);

                TextView tvItem = (TextView)view_lyt.findViewById(R.id.tv_item_text);
                ImageButton btnDeleteItemBtn = (ImageButton) view_lyt.findViewById(R.id.btn_delete_item);
                tvItem.setText(Name);

                btnDeleteItemBtn.setOnClickListener(this);
            }
        }
        catch (Exception e) {}

        return view_lyt;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_delete_item:
                if (lstVotes.size() > 0)
                    //removeItemFromList(position);
                break;
        }
    }
}

//    // Method for remove Single item from list
//    protected void removeItemFromList(int position)
//    {
//        final int deletePosition = position;
//
//        lstVotes.remove(deletePosition);
//        this.notifyDataSetChanged();
//        this.notifyDataSetInvalidated();
//    }

