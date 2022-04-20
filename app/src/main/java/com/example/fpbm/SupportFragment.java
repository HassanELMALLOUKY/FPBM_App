package com.example.fpbm;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;


public class SupportFragment extends Fragment {
    View view;
    Button button;
    FirebaseAuth mFirebaseAuth;
    TextView fullname;
    TextView cne;
    TextView birthday;
    TextView filiere;
    CircleImageView circleImageView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.fragment_support, container, false);
        button=view.findViewById(R.id.logout);

        fullname=view.findViewById(R.id.fullname);
        cne=view.findViewById(R.id.cne);
        birthday=view.findViewById(R.id.birthday);
        filiere=view.findViewById(R.id.filiere);
        circleImageView= view.findViewById(R.id.imageView10);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFirebaseAuth.signOut();
                startActivity(new Intent(getContext(),SingInActivity.class));
                getActivity().finish();
            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mFirebaseAuth=FirebaseAuth.getInstance();
        FirebaseDatabase.getInstance().getReference().child("student").child(mFirebaseAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name = String.valueOf(snapshot.child("fullName").getValue());
                String image = String.valueOf(snapshot.child("image").getValue());
                String cneS = String.valueOf(snapshot.child("cne").getValue());
                String fr = String.valueOf(snapshot.child("filiere").getValue());
                String date = String.valueOf(snapshot.child("password").getValue());

                Picasso.get().load(image).into(circleImageView);

                fullname.setText(name);
                cne.setText(cneS);
                filiere.setText(fr);
                birthday.setText(date);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}