package com.example.myfragmentandnavigators;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

// שימוש בתפריט צדדי
public class MainActivity extends AppCompatActivity {
    Button btnYT, btnSite,btnImage, btnList,btnAirport,btnPage;
    CountriesFragment countriesFragment;
    YouTubeFragment youtubeFragment;
    SiteFragment siteFragment;
    ImageFragment imageFragment;
    FrameLayout frameLayout;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    MaterialToolbar toolbar;
    View headerView;
    ImageView closeImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnYT=findViewById(R.id.btnYT);
        btnSite=findViewById(R.id.btnSite);
        btnImage=findViewById(R.id.btnImage);
        btnList=findViewById(R.id.btnList);
        btnAirport=findViewById(R.id.btnAirport);
        btnPage=findViewById(R.id.btnPage);

        frameLayout=findViewById(R.id.fl);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        toolbar = findViewById(R.id.topAppBar);

        // פתיחת תפריט
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        // סגירת תפריט
        headerView = navigationView.getHeaderView(0);
        closeImage = headerView.findViewById(R.id.closeDrawer);
        closeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //כאשר לוחצים על התמונה של הסגור, המגירה תיסגר
                drawerLayout.closeDrawer(navigationView);
            }
        });

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                drawerLayout.closeDrawer(GravityCompat.START);
                if (id == R.id.yt) {
                    replaceFragment(new YouTubeFragment());
                }
                if (id == R.id.image) {
                    replaceFragment(new ImageFragment());
                }
                if (id == R.id.site) {
                    replaceFragment(new SiteFragment());
                }
                if (id == R.id.countries) {
                    replaceFragment(new CountriesFragment());
                }
                if (id == R.id.bottomActivity) {
                    Intent intent = new Intent(MainActivity.this, BottomNavigationActivity.class);
                    startActivity(intent);
                }
                return true;
            }
        });


        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // הכנסת פרגמנט לתוך framelayout
                countriesFragment=new CountriesFragment();
                FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.fl,countriesFragment);
                ft.setReorderingAllowed(true);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        btnYT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // הכנסת פרגמנט לתוך framelayout
                youtubeFragment=new YouTubeFragment();
                FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.fl,youtubeFragment);
                ft.setReorderingAllowed(true);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        btnSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // הכנסת פרגמנט לתוך framelayout
                siteFragment=new SiteFragment();
                FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.fl,siteFragment);
                ft.commit();
            }
        });

        btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // הכנסת פרגמנט לתוך framelayout
                imageFragment=new ImageFragment();
                FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.fl,imageFragment);
                ft.commit();
            }
        });

        btnAirport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent go=new Intent(MainActivity.this,JsonActivity.class);
                startActivity(go);
            }
        });

        btnPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent go=new Intent(MainActivity.this,PageViewerActivity.class);
                startActivity(go);
            }
        });
    }

    // פעולה אשר תסגור תפריט אם נלחץ כפתור חזור המכשיר
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(navigationView)){
            drawerLayout.closeDrawer(navigationView);
        } else {
            super.onBackPressed();
        }
    }

    // פעולה להפעלת פראגמנט
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fl, fragment);
        fragmentTransaction.commit();
    }

}