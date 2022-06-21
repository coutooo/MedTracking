package com.example.medtracking.User;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.medtracking.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<Pacient> {

    public ListAdapter(Context context, ArrayList<Pacient> pacientArrayList){
        super(context, R.layout.list_item,pacientArrayList);


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Pacient pacient = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }

        ImageView imageView = convertView.findViewById(R.id.profile_pic);

        TextView bednumb = convertView.findViewById(R.id.bedNumber);

        TextView description = convertView.findViewById(R.id.pacientDescription);

        TextView inoutText = convertView.findViewById(R.id.inoutText);

        //imageView.setImageResource(pacient.imageId);
        bednumb.setText(pacient.bed);
        description.setText(pacient.nameAndcause);
        inoutText.setText(pacient.inAndout);

        return convertView;
    }
}
