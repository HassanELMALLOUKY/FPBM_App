package com.example.fpbm;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class EmploiFragment extends Fragment {
    View view;
    private CardView cardViewMaster, cardViewLP, cardViewLF;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_emploi, container, false);

        cardViewMaster = view.findViewById(R.id.cardMaster);
        cardViewLP = view.findViewById(R.id.cardLP);
        cardViewLF = view.findViewById(R.id.cardLF);

        cardViewMaster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),EmploiActivity.class);
                intent.putExtra("data","master");
                startActivity(intent);
            }
        });

        cardViewLF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),EmploiActivity.class);
                intent.putExtra("data","lf");
                startActivity(intent);
            }
        });

        cardViewLP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),EmploiActivity.class);
                intent.putExtra("data","lp");
                startActivity(intent);
            }
        });



        return view;
    }
}