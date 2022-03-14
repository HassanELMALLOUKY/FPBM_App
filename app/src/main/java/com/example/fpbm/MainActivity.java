package com.example.fpbm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;
    NavigationView navigationView;
    private CircleImageView userImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer);
        toolbar = findViewById(R.id.toolBar);
        userImage = findViewById(R.id.circleImageView);
        setSupportActionBar(toolbar);
        toggle= new ActionBarDrawerToggle(this, drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id=item.getItemId();
                Fragment fragment = null;

                switch (id) {
                    case R.id.notes:
                        fragment = new NotesFragment();
                        loadFragment(fragment);
                        break;
                    case R.id.emploi:
                        fragment = new EmploiFragment();
                        loadFragment(fragment);
                        break;
                    case R.id.notification:
                        break;
                    case R.id.scolarite:
                        fragment = new ScolariteFragment();
                        loadFragment(fragment);
                        break;
                    case R.id.logout:
                        break;
                    case R.id.setting:
                        fragment = new SettingFragment();
                        loadFragment(fragment);
                        break;
                    case R.id.support:
                        fragment = new SupportFragment();
                        loadFragment(fragment);
                        break;
                    default:
                        return true;
                }

                return  true;
            }
        });




    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction ();
        fragmentTransaction.replace (R.id.frame, fragment).commit ();
        drawerLayout.closeDrawer (GravityCompat.START) ;
        fragmentTransaction.addToBackStack (null);
    }
}