package com.example.myactionbar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class RaceAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<Race> races = new ArrayList<>();

    public void setRace(ArrayList<Race> races) {
        this.races = races;
    }

    public RaceAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return races.size();
    }

    @Override
    public Object getItem(int i) {
        return races.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_race, viewGroup, false);
        }

        ViewHolder viewHolder = new ViewHolder(view);
        Race hero = (Race) getItem(i);
        viewHolder.bind(hero);
        return view;
    }
    private class ViewHolder {
        private TextView txtName;
        private TextView txtDescription;
        private ImageView imgPhoto;
        ViewHolder(View view) {
            txtName = view.findViewById(R.id.txt_name_race);
            txtDescription = view.findViewById(R.id.txt_description_race);
            imgPhoto = view.findViewById(R.id.img_photo_race);
        }
        void bind(Race hero) {
            txtName.setText(hero.getName());
            txtDescription.setText(hero.getDescription());
            imgPhoto.setImageResource(hero.getPhoto());
        }
    }
}
