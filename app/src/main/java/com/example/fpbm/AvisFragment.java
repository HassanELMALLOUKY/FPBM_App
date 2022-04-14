package com.example.fpbm;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class AvisFragment extends Fragment {

    private FirebaseAuth mFirebaseAuth;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference dbRef = db.getReference();
    private View view;

    private RecyclerView avisRecycler;
    private FirebaseRecyclerAdapter<AvisClass, avisHolderView> firebaseRecyclerAdapterMessages;
    private LinearLayoutManager mLayoutManagersAvis;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_avis, container, false);

        widget();




        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        avis();
    }

    private void widget() {

        avisRecycler = view.findViewById(R.id.avisRecycler);
        avisRecycler.setHasFixedSize(true);
        mLayoutManagersAvis = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        avisRecycler.setLayoutManager(mLayoutManagersAvis);
    }

    private void avis(){

        final Query avisQuery = FirebaseDatabase.getInstance().getReference().child("avis").orderByChild("date");
        firebaseRecyclerAdapterMessages = new FirebaseRecyclerAdapter<AvisClass, avisHolderView>(
                AvisClass.class,
                R.layout.avis_item,
                avisHolderView.class,
                avisQuery
        ) {

            @Override
            protected void populateViewHolder(final avisHolderView viewHolder, final AvisClass model, int position) {

                final String listPostKey = getRef(position).getKey();



                dbRef.child("avis").child(listPostKey).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String title = String.valueOf(dataSnapshot.child("title").getValue());
                        String description = String.valueOf(dataSnapshot.child("description").getValue());
                        String date = String.valueOf(dataSnapshot.child("date").getValue());

                        viewHolder.title.setText(title);
                        viewHolder.description.setText(description);
                        viewHolder.date.setText(date);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });



            }
        };
        avisRecycler.setAdapter(firebaseRecyclerAdapterMessages);


    }




    public static class avisHolderView extends RecyclerView.ViewHolder {

        View view;
        TextView title;
        TextView description,date;
        CardView cardView;


        public avisHolderView(View itemView) {
            super(itemView);

            view = itemView;

            title = view.findViewById(R.id.avisTitle);
            description = view.findViewById(R.id.avisDescription);
            date = view.findViewById(R.id.avisDate);
            cardView = view.findViewById(R.id.avisCard);


        }


    }
}