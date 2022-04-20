package com.example.fpbm;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;


public class ScolariteFragment extends Fragment {

    private View view;
    private FirebaseAuth firebaseAuth;
    private RadioGroup radioGroup;
    private RadioButton radioButton1, radioButton2, radioButton3, radioButton4;
    private Button valide;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference dbRef = db.getReference();
    private String currentUser;

    private RecyclerView DemandeRecycler;
    private FirebaseRecyclerAdapter<DemandeModel, demandeHolderView> firebaseRecyclerAdapterDemande;
    private LinearLayoutManager mLayoutManagerDemande;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_scolarite, container, false);


        firebaseAuth = FirebaseAuth.getInstance();
        currentUser = firebaseAuth.getCurrentUser().getUid();

        Toast.makeText(getContext(), currentUser, Toast.LENGTH_SHORT).show();

        widget();

        radioButton1=view.findViewById(R.id.button1);
        radioGroup=view.findViewById(R.id.radioHassan);
        radioButton2=view.findViewById(R.id.button2);
        radioButton3=view.findViewById(R.id.button3);
        radioButton4=view.findViewById(R.id.button4);


        valide=view.findViewById(R.id.buttonValide);

        valide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (radioButton1.isChecked()){
                    String zeec = radioButton1.getText().toString();


                    long yourmilliseconds = System.currentTimeMillis();
                    yourmilliseconds=yourmilliseconds+259200000;
                    SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy");
                    Date resultdate = new Date(yourmilliseconds);
                    String test = sdf.format(resultdate);

                    HashMap<String, String> map = new HashMap<>();

                    map.put("appapoinetement",test);
                    map.put("title",zeec);

                    FirebaseDatabase.getInstance().getReference().child("dm").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .child(zeec).setValue(map);

                    Toast.makeText(getContext(), "Validate", Toast.LENGTH_SHORT).show();


                }else  if (radioButton2.isChecked()){

                    String zeec = radioButton2.getText().toString();


                    long yourmilliseconds = System.currentTimeMillis();
                    yourmilliseconds=yourmilliseconds+259200000;
                    SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy");
                    Date resultdate = new Date(yourmilliseconds);
                    String test = sdf.format(resultdate);

                    HashMap<String, String> map = new HashMap<>();

                    map.put("appapoinetement",test);
                    map.put("title",zeec);

                    FirebaseDatabase.getInstance().getReference().child("dm").child(currentUser)
                            .child(zeec).setValue(map);

                    Toast.makeText(getContext(), "Validate", Toast.LENGTH_SHORT).show();

                }else  if (radioButton3.isChecked()){
                    String zeec = radioButton3.getText().toString();


                    long yourmilliseconds = System.currentTimeMillis();
                    yourmilliseconds=yourmilliseconds+259200000;
                    SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy");
                    Date resultdate = new Date(yourmilliseconds);
                    String test = sdf.format(resultdate);

                    HashMap<String, String> map = new HashMap<>();

                    map.put("appapoinetement",test);
                    map.put("title",zeec);

                    FirebaseDatabase.getInstance().getReference().child("dm").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .child(zeec).setValue(map);

                    Toast.makeText(getContext(), "Validate", Toast.LENGTH_SHORT).show();
                }else  if (radioButton4.isChecked()){
                    String zeec = radioButton4.getText().toString();


                    long yourmilliseconds = System.currentTimeMillis();
                    yourmilliseconds=yourmilliseconds+259200000;
                    SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy");
                    Date resultdate = new Date(yourmilliseconds);
                    String test = sdf.format(resultdate);

                    HashMap<String, String> map = new HashMap<>();

                    map.put("appapoinetement",test);
                    map.put("title",zeec);

                    FirebaseDatabase.getInstance().getReference().child("dm").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .child(zeec).setValue(map);

                    Toast.makeText(getContext(), "Validate", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getContext(), "Please check something", Toast.LENGTH_SHORT).show();
                }
            }
        });







        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Demande();
    }

    private void Demande(){

        final Query avisQuery = FirebaseDatabase.getInstance().getReference().child("dm").child(currentUser);
        firebaseRecyclerAdapterDemande = new FirebaseRecyclerAdapter<DemandeModel, demandeHolderView>(
                DemandeModel.class,
                R.layout.demande_item,
                demandeHolderView.class,
                avisQuery
        ) {

            @Override
            protected void populateViewHolder(final demandeHolderView viewHolder, final DemandeModel model, int position) {

                final String listPostKey = getRef(position).getKey();



                dbRef.child("dm").child(currentUser).child(listPostKey).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String title = String.valueOf(dataSnapshot.child("title").getValue());
                        String date = String.valueOf(dataSnapshot.child("appapoinetement").getValue());

                        viewHolder.title.setText(title);
                        viewHolder.date.setText(date);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });




            }
        };
        DemandeRecycler.setAdapter(firebaseRecyclerAdapterDemande);


    }




    public static class demandeHolderView extends RecyclerView.ViewHolder {

        View view;
        TextView title;
        TextView date;


        public demandeHolderView(View itemView) {
            super(itemView);

            view = itemView;

            title = view.findViewById(R.id.demandeTitle);
            date = view.findViewById(R.id.demandeDate);



        }


    }

    private void widget() {

        DemandeRecycler = view.findViewById(R.id.demandeRC);
        DemandeRecycler.setHasFixedSize(true);
        mLayoutManagerDemande = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        DemandeRecycler.setLayoutManager(mLayoutManagerDemande);
    }

}