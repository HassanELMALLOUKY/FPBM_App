package com.example.fpbm;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;


public class SupportFragment extends Fragment {
    View view;
    Button button;
    FirebaseAuth mFirebaseAuth;
    TextView fullname;
    TextView cne;
    TextView birthday;
    TextView filiere;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.fragment_support, container, false);
        button=view.findViewById(R.id.logout);
        mFirebaseAuth=FirebaseAuth.getInstance();
        fullname=view.findViewById(R.id.fullname);
        cne=view.findViewById(R.id.cne);
        birthday=view.findViewById(R.id.birthday);
        filiere=view.findViewById(R.id.filiere);
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
}