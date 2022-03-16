package com.example.fpbm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;
    NavigationView navigationView;
    private CircleImageView userImage;
    private TextView textViewUsername,textViewCNE;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference dbRef = db.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer);
        toolbar = findViewById(R.id.toolBar);
        navigationView = findViewById(R.id.nav_view);

        textViewUsername = navigationView.getHeaderView(0).findViewById(R.id.etdName);
        userImage = navigationView.getHeaderView(0).findViewById(R.id.circleImageView);
        textViewCNE = navigationView.getHeaderView(0).findViewById(R.id.etdCNE);


        setSupportActionBar(toolbar);
        toggle= new ActionBarDrawerToggle(this, drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


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

        if (savedInstanceState == null) {
            FragmentManager fragmentManager= getSupportFragmentManager();
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction ();
            fragmentTransaction.replace (R.id.frame, new AvisFragment()).commit ();
            drawerLayout.closeDrawer (GravityCompat.START) ;
            fragmentTransaction.addToBackStack (null);
        }

        mFirebaseAuth = FirebaseAuth.getInstance();


        if (mFirebaseAuth.getCurrentUser() != null){
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

            dbRef.child("student").child(user.getUid()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                    String etdName = snapshot.child("fullName").getValue(String.class);

                    textViewUsername.setText(etdName);
                    String etdCNE = snapshot.child("cne").getValue(String.class);

                    textViewCNE.setText(etdCNE);
                    Log.d("hassan", etdName);

                    if (snapshot.hasChild("image")){
                        String image = snapshot.child("image").getValue(String.class);
                        Picasso.get().load(image).into(userImage);
                    }


                }

                @Override
                public void onCancelled(@NonNull @NotNull DatabaseError error) {
                    Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }




    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction ();
        fragmentTransaction.replace (R.id.frame, fragment).commit ();
        drawerLayout.closeDrawer (GravityCompat.START) ;
        fragmentTransaction.addToBackStack (null);
    }
}