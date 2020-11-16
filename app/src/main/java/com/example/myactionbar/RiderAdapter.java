package com.example.myactionbar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class RiderAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Rider> riders = new ArrayList<>();

    public void setRider(ArrayList<Rider> riders) {
        this.riders = riders;
    }

    public RiderAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return riders.size();
    }

    @Override
    public Object getItem(int i) {
        return riders.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_rider, viewGroup, false);
        }

        ViewHolder viewHolder = new ViewHolder(view);
        Rider hero = (Rider) getItem(i);
        viewHolder.bind(hero);
        return view;
    }
    private class ViewHolder {
        private TextView txtName;
        private TextView txtDescription;
        private ImageView imgPhoto;
        ViewHolder(View view) {
            txtName = view.findViewById(R.id.txt_name);
            txtDescription = view.findViewById(R.id.txt_description);
            imgPhoto = view.findViewById(R.id.img_photo);
        }
        void bind(Rider hero) {
            txtName.setText(hero.getName());
            txtDescription.setText(hero.getDescription());
            imgPhoto.setImageResource(hero.getPhoto());
        }
    }
}

