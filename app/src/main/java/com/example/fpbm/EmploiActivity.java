package com.example.fpbm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
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

    private RecyclerView fileRecycler;
    private FirebaseRecyclerAdapter<EmploiModel, emploiHolderView> firebaseRecyclerAdapterMessages;
    private LinearLayoutManager mLayoutManagersEmploi;
    private String semester;

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
                        String title = String.valueOf(dataSnapshot.getKey());
                        String s1 = String.valueOf(dataSnapshot.child("s1").getValue());
                        String s2 = String.valueOf(dataSnapshot.child("s2").getValue());
                        String s3 = String.valueOf(dataSnapshot.child("s3").getValue());
                        String s4 = String.valueOf(dataSnapshot.child("s4").getValue());


                        viewHolder.title.setText(title);

                        if (s1.equals("---")){
                            viewHolder.buttonS1.setVisibility(View.GONE);
                        }

                        if (s2.equals("---")){
                            viewHolder.buttonS2.setVisibility(View.GONE);
                        }

                        if (s3.equals("---")){
                            viewHolder.buttonS3.setVisibility(View.GONE);
                        }

                        if (s4.equals("---")){
                            viewHolder.buttonS4.setVisibility(View.GONE);
                        }


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                viewHolder.layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(EmploiActivity.this, " "+position, Toast.LENGTH_SHORT).show();
                    }
                });

                viewHolder.buttonS1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        dbRef.child("Emplois du temps").child(data).child(listPostKey).child("s1").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                semester = String.valueOf(snapshot.getValue());
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
                                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(semester));
                                    startActivity(intent);
                                }
                                // We will view the pdf
                                if (which == 1) {
                                    Intent intent = new Intent(EmploiActivity.this, EmploiViewActivity.class);
                                    intent.putExtra("url", semester);
                                    startActivity(intent);


                                }
                            }
                        });
                        builder.show();
                    }
                });

                viewHolder.buttonS2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        dbRef.child("Emplois du temps").child(data).child(listPostKey).child("s2").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                semester= String.valueOf(snapshot.getValue());
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
                                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(semester));
                                    startActivity(intent);
                                }
                                // We will view the pdf
                                if (which == 1) {
                                    Intent intent = new Intent(EmploiActivity.this, EmploiViewActivity.class);
                                    intent.putExtra("url", semester);
                                    startActivity(intent);


                                }
                            }
                        });
                        builder.show();
                    }
                });

                viewHolder.buttonS3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        dbRef.child("Emplois du temps").child(data).child(listPostKey).child("s3").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                semester = String.valueOf(snapshot.getValue());
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
                                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(semester));
                                    startActivity(intent);
                                }
                                // We will view the pdf
                                if (which == 1) {
                                    Intent intent = new Intent(EmploiActivity.this, EmploiViewActivity.class);
                                    intent.putExtra("url", semester);
                                    startActivity(intent);


                                }
                            }
                        });
                        builder.show();
                    }
                });

                viewHolder.buttonS4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        dbRef.child("Emplois du temps").child(data).child(listPostKey).child("s4").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                semester= String.valueOf(snapshot.getValue());
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
                                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(semester));
                                    startActivity(intent);
                                }
                                // We will view the pdf
                                if (which == 1) {
                                    Intent intent = new Intent(EmploiActivity.this, EmploiViewActivity.class);
                                    intent.putExtra("url", semester);
                                    startActivity(intent);


                                }
                            }
                        });
                        builder.show();
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
        Button buttonS1, buttonS2,buttonS3,buttonS4;

        public emploiHolderView(View itemView) {
            super(itemView);

            view = itemView;

            title = view.findViewById(R.id.emploiFile);
            layout = view.findViewById(R.id.linearLayoutFile);

            buttonS1 = view.findViewById(R.id.button1);
            buttonS2 = view.findViewById(R.id.button2);
            buttonS3 = view.findViewById(R.id.button3);
            buttonS4 = view.findViewById(R.id.button4);

        }
    }


    private void widget() {

        fileRecycler = findViewById(R.id.fileRecycler);
        fileRecycler.setHasFixedSize(true);
        mLayoutManagersEmploi = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        fileRecycler.setLayoutManager(mLayoutManagersEmploi);

    }
}