package com.example.fpbm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

public class MainActivity2 extends AppCompatActivity {

    boolean doubleBackToExitPressedOnce = false;
    MeowBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        bottomNavigation = findViewById(R.id.bottom_navigation);


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

    }

    private void loadFragment(Fragment fragment) {

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout,fragment)
                .commit();
    }
}