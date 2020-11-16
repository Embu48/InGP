package com.example.myactionbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RidersFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class RidersFragment extends Fragment {
    Context thiscontext;
    private RiderAdapter adapter;
    private String[] dataName;
    private String[] dataDescription;
    private TypedArray dataPhoto;
    private ArrayList<Rider> riders;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RidersFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RidersFragment newInstance(String param1, String param2) {
        RidersFragment fragment = new RidersFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public RidersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_riders, container, false);
        ListView listview =(ListView) rootView.findViewById(R.id.list);
        adapter = new RiderAdapter(getActivity().getApplicationContext());
        listview.setAdapter(adapter);

        prepare();
        addItem();

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity().getApplicationContext(), riders.get(i).getName(), Toast.LENGTH_SHORT).show();
            }
        });
        return rootView;
    }

    private void prepare() {
        dataName = getResources().getStringArray(R.array.data_name);
        dataDescription = getResources().getStringArray(R.array.data_description);
        dataPhoto = getResources().obtainTypedArray(R.array.data_photo);
    }

    private void addItem() {
        riders = new ArrayList<>();
        for (int i = 0; i < dataName.length; i++) {
            Rider rider = new Rider();
            rider.setPhoto(dataPhoto.getResourceId(i, -1));
            rider.setName(dataName[i]);
            rider.setDescription(dataDescription[i]);
            riders.add(rider);
        }
        adapter.setRider(riders);
    }
}