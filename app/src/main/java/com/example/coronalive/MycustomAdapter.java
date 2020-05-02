package com.example.coronalive;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class MycustomAdapter extends ArrayAdapter<helper> {

    private Context context;
    private List<helper> helperList;


    public MycustomAdapter(Context context, List<helper> helperList) {
        super(context, R.layout.list_item, helperList);

        this.context = context;
        this.helperList = helperList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,null,true);

        TextView state = view.findViewById(R.id.state);
        TextView confirm = view.findViewById(R.id.confirm);
        TextView recover = view.findViewById(R.id.recover);
        TextView death = view.findViewById(R.id.deaths);

        state.setText(helperList.get(position).getState());
        confirm.setText(helperList.get(position).getConfirm());
        recover.setText(helperList.get(position).getRecover());
        death.setText(helperList.get(position).getDeath());


        return view;
    }
}
