package com.example.fpbm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

//    DrawerLayout drawerLayout;
//    ActionBarDrawerToggle toggle;
//    Toolbar toolbar;
//    NavigationView navigationView;
//    private CircleImageView userImage;
//    private TextView textViewUsername,textViewCNE;
   private FirebaseAuth mFirebaseAuth;
   private FirebaseDatabase db = FirebaseDatabase.getInstance();
   private DatabaseReference dbRef = db.getReference();

    boolean doubleBackToExitPressedOnce = false;
    private MeowBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        widget();


        bottomNavigation.add(new MeowBottomNavigation.Model(1,R.drawable.ic_news));
        bottomNavigation.add(new MeowBottomNavigation.Model(2,R.drawable.id_date_emploi));
        bottomNavigation.add(new MeowBottomNavigation.Model(3,R.drawable.ic_school));
        bottomNavigation.add(new MeowBottomNavigation.Model(4,R.drawable.ic_notes));
        bottomNavigation.add(new MeowBottomNavigation.Model(5,R.drawable.ic_account_circle));



        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {

                Fragment fragment = null;

                switch (item.getId()){
                    case 1:
                        fragment = new AvisFragment();
                        break;
                    case 2:
                        fragment = new EmploiFragment();
                        break;
                    case 3:
                        fragment = new ScolariteFragment();
                        break;
                    case 4:
                        fragment = new NotesFragment();
                        break;
                    case 5:
                        fragment = new SupportFragment();
                        break;
                }

                loadFragment(fragment);
            }
        });



        bottomNavigation.show(1, true);



        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {

            }
        });

        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
            }
        });


//        if (savedInstanceState == null) {
//            FragmentManager fragmentManager= getSupportFragmentManager();
//            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction ();
//            drawerLayout.closeDrawer (GravityCompat.START) ;
//            fragmentTransaction.addToBackStack (null);
//        }

        mFirebaseAuth = FirebaseAuth.getInstance();


        if (mFirebaseAuth.getCurrentUser() != null){
            FirebaseUser user = mFirebaseAuth.getCurrentUser();

            dbRef.child("student").child(user.getUid()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                    String etdName = snapshot.child("fullName").getValue(String.class);

//                    textViewUsername.setText(etdName);
//                    String etdCNE = snapshot.child("cne").getValue(String.class);
//
//                    textViewCNE.setText(etdCNE);
//                    Log.d("hassan", etdName);
//
//                    if (snapshot.hasChild("image")){
//                        String image = snapshot.child("image").getValue(String.class);
//                        Picasso.get().load(image).into(userImage);
//                    }


                }

                @Override
                public void onCancelled(@NonNull @NotNull DatabaseError error) {
                    Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }




    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void widget() {

        bottomNavigation = findViewById(R.id.bottom_navigation);

//        drawerLayout = findViewById(R.id.drawer);
//        toolbar = findViewById(R.id.toolBar);
//        navigationView = findViewById(R.id.nav_view);
//
//        textViewUsername = navigationView.getHeaderView(0).findViewById(R.id.etdName);
//        userImage = navigationView.getHeaderView(0).findViewById(R.id.circleImageView);
//        textViewCNE = navigationView.getHeaderView(0).findViewById(R.id.etdCNE);

    }


    private void loadFragment(Fragment fragment) {

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout,fragment)
                .commit();
    }

}