package com.example.myfragmentandnavigators;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class JsonActivity extends AppCompatActivity {

    ListView lvJson;
    ProgressBar pb;
    ArrayList<Airport> arr;
    ArrayAdapter<Airport> adapter;
    Airport airport;
    String info_url;
    Thread thread;
    String all = "", temp;
    int n = 0;
    int status;
    double lat, lon;
    String name, city;

    FrameLayout frameLayout;
    MapsFragment mapsFragment;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);
        pb = findViewById(R.id.pb);
        lvJson = findViewById(R.id.lvJson);
        frameLayout=findViewById(R.id.fljson);
        arr = new ArrayList<>();
        info_url = "https://api.travelpayouts.com/data/airports.json";

        goToJsonFile(info_url); // save the json file to string

        new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long l) {
                pb.setVisibility(View.VISIBLE);
            }
            @Override
            public void onFinish() {
                pb.setVisibility(View.GONE);
                buildArrayList(); // convert json string to arrayList
            }
        }.start();

        lvJson.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                lat=Double.parseDouble(arr.get(i).getLat());
                lon=Double.parseDouble(arr.get(i).getLon());
                name=arr.get(i).getLon();
                city=arr.get(i).getLon();

                Bundle bundle = new Bundle();// יצירת אובייקט לשליחת נתונים לפראגמנט
                bundle.putDouble("latKey", lat );
                bundle.putDouble("lonKey", lon );
                bundle.putString("nameKey", name );
                bundle.putString("cityKey", city );
                mapsFragment=new MapsFragment();
                mapsFragment.setArguments(bundle);
                FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.fljson,mapsFragment);
                ft.commit();
            }
        });
    }
    private String goToJsonFile(String infoUrl) {
        Toast.makeText(this, "Start downloading", Toast.LENGTH_SHORT).show();

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // save the json file to string file
                try {
                    URL url = new URL(infoUrl);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.connect();
                    InputStream is;
                    status = connection.getResponseCode();

                    if (status == HttpURLConnection.HTTP_OK) {
                        is = connection.getInputStream();
                        InputStreamReader isr = new InputStreamReader(is);
                        BufferedReader br = new BufferedReader(isr);
                        while ((temp = br.readLine()) != null) {
                            n++;
                            all += temp + "\n";
                        }
                        br.close();
                    }
                } catch (MalformedURLException e) {
                    all = e.toString();
                } catch (IOException e) {
                    all = e.toString();
                } catch (Exception e) {
                    all = e.toString();
                }
            }
        });
        thread.start();
        return all;
    }

    // convert json string to arrayList
    private void buildArrayList() {
        try {
            JSONArray jsonArray = new JSONArray(all);
            String t1, t2, t3, t4;
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject arrayElement = jsonArray.getJSONObject(i);
                t1= arrayElement.getString("name") + "\n";
                t2= arrayElement.getString("time_zone") + "\n";
                t3=arrayElement.getJSONObject("coordinates").getString("lat");
                t4=arrayElement.getJSONObject("coordinates").getString("lon");
                airport=new Airport(t1,t2,t3,t4);
                arr.add(airport);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        adapter = new ArrayAdapter<Airport>(this, android.R.layout.simple_list_item_1, arr);
        lvJson.setAdapter(adapter);
    }
}