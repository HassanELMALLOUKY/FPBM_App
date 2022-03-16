package com.example.fpbm;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;


public class ScolariteFragment extends Fragment {

    private View view;
    private FirebaseAuth firebaseAuth;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_scolarite, container, false);

        firebaseAuth = FirebaseAuth.getInstance();




        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        if (firebaseAuth.getCurrentUser() == null ){
            startActivity(new Intent(getActivity(), SingInActivity.class));
            getActivity().finish();
        }
    }

}