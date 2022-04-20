package com.example.fpbm;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class EmploiActivity extends AppCompatActivity {

    private String data;
    private FirebaseDatabase db = com.google.firebase.database.FirebaseDatabase.getInstance();
    private DatabaseReference dbRef = db.getReference();

    private RecyclerView fileRecycler, testRecycler;
    private FirebaseRecyclerAdapter<EmploiModel, emploiHolderView> firebaseRecyclerAdapterMessages;
    private FirebaseRecyclerAdapter<EmploiModel, testHolderView> firebaseRecyclerAdapter;
    private LinearLayoutManager mLayoutManagersEmploi, mLayoutManagersTest;
    private String pdf;
    boolean check=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emploi);

        data = getIntent().getStringExtra("data");

        widget();


    }

    @Override
    protected void onStart() {
        super.onStart();
        emploi();

    }

    private void test(String s) {
        final Query testQuery = FirebaseDatabase.getInstance().getReference().child("Emplois du temps").child(data).child(s);
        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<EmploiModel, testHolderView>(
                EmploiModel.class,
                R.layout.semester_item,
                testHolderView.class,
                testQuery
        ) {

            @Override
            protected void populateViewHolder(final testHolderView viewHolder, final EmploiModel model, int position) {

                final String listPostKey = getRef(position).getKey();


                dbRef.child("Emplois du temps").child(data).child(s).child(listPostKey).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String s1 = String.valueOf(dataSnapshot.child("Name").getValue());
                        /*String s2 = String.valueOf(dataSnapshot.child("s2").getValue());
                        String s3 = String.valueOf(dataSnapshot.child("s3").getValue());
                        String s4 = String.valueOf(dataSnapshot.child("s4").getValue());

                         */


                        viewHolder.title.setText(s1);



                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                viewHolder.layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dbRef.child("Emplois du temps").child(data).child(s).child(listPostKey).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                pdf = String.valueOf(snapshot.child("PDF").getValue());
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                        CharSequence options[] = new CharSequence[]{
                                "Download",
                                "View",
                                "Cancel"
                        };
                        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                        builder.setTitle("Choose One");
                        builder.setItems(options, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // we will be downloading the pdf
                                if (which == 0) {
                                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(pdf));
                                    startActivity(intent);
                                }
                                // We will view the pdf
                                if (which == 1) {
                                    Intent intent = new Intent(EmploiActivity.this, EmploiViewActivity.class);
                                    intent.putExtra("url", pdf);
                                    startActivity(intent);


                                }
                            }
                        });
                        builder.show();
                    }
                });



            }
        };
        testRecycler.setAdapter(firebaseRecyclerAdapter);
    }

    public static class testHolderView extends RecyclerView.ViewHolder {

        View view;
        TextView title;
        LinearLayout layout;

        public testHolderView(View itemView) {
            super(itemView);

            view = itemView;

            title = view.findViewById(R.id.emploiFile1);
            layout = view.findViewById(R.id.linearLayoutFile1);


        }
    }


    private void emploi() {
        final Query avisQuery = FirebaseDatabase.getInstance().getReference().child("Emplois du temps").child(data);
        firebaseRecyclerAdapterMessages = new FirebaseRecyclerAdapter<EmploiModel, emploiHolderView>(
                EmploiModel.class,
                R.layout.emploi_item,
                emploiHolderView.class,
                avisQuery
        ) {

            @Override
            protected void populateViewHolder(final emploiHolderView viewHolder, final EmploiModel model, int position) {

                final String listPostKey = getRef(position).getKey();


                dbRef.child("Emplois du temps").child(data).child(listPostKey).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String semester = String.valueOf(dataSnapshot.getKey());

                        FirebaseDatabase.getInstance().getReference().child("Emplois du temps").child("filleSelect").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                String s1 = String.valueOf(snapshot.getValue());
                                if (s1.equals(listPostKey)){
                                    viewHolder.layout.setBackgroundResource(R.drawable.bg_card_background);

                                }else {
                                    viewHolder.layout.setBackgroundResource(R.color.white);
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });


                        viewHolder.title.setText(semester);


                        if (check){
                            check = false;
                            test(listPostKey);
                            FirebaseDatabase.getInstance().getReference().child("Emplois du temps").child("filleSelect").setValue(listPostKey);
                        }




                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                viewHolder.layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        FirebaseDatabase.getInstance().getReference().child("Emplois du temps").child("filleSelect").setValue(listPostKey);
                        test(listPostKey);
                    }
                });






            }
        };
        fileRecycler.setAdapter(firebaseRecyclerAdapterMessages);
    }


    public static class emploiHolderView extends RecyclerView.ViewHolder {

        View view;
        TextView title;
        LinearLayout layout;

        public emploiHolderView(View itemView) {
            super(itemView);

            view = itemView;

            title = view.findViewById(R.id.emploiFile);
            layout = view.findViewById(R.id.linearLayoutFile);



        }
    }


    private void widget() {

        testRecycler = findViewById(R.id.test1Recycler);
        testRecycler.setHasFixedSize(true);
        mLayoutManagersTest = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        testRecycler.setLayoutManager(mLayoutManagersTest);

        fileRecycler = findViewById(R.id.fileRecycler);
        fileRecycler.setHasFixedSize(true);
        mLayoutManagersEmploi = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        fileRecycler.setLayoutManager(mLayoutManagersEmploi);



    }
}