package com.example.myfragmentandnavigators;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class CountriesFragment extends Fragment {


    ListView lvCountries;
    ArrayList<String>countries;
    ArrayAdapter<String> adapter;
    InputStream is;
    InputStreamReader isr;
    BufferedReader br;
    String line;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_countries, container, false);
        lvCountries=view.findViewById(R.id.lvCountries);

        is=getResources().openRawResource(R.raw.countries);
        isr=new InputStreamReader(is);
        br=new BufferedReader(isr);
        countries=new ArrayList<>();
        try {
            while ((line=br.readLine())!=null){
                countries.add(line);
            is.close();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        adapter=new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,countries);
        lvCountries.setAdapter(adapter);

        lvCountries.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity(), ""+countries.get(i)  , Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}