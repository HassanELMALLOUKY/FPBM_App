package com.example.fpbm;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class NotesFragment extends Fragment {

    private View view;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference dbRef = db.getReference();
    private FirebaseUser firebaseUser;
    private RecyclerView noteRecycler;
    private LinearLayoutManager mLayoutManagersNotes;
    private FirebaseRecyclerAdapter<NotesModel,notesHolderView>firebaseRecyclerAdapterNotes;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_notes, container, false);

        widget();
        mFirebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = mFirebaseAuth.getCurrentUser();

        //Toast.makeText(getActivity(), firebaseUser.getUid(), Toast.LENGTH_SHORT).show();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        notes();
    }

    private void notes() {
        final Query queryNotes = FirebaseDatabase.getInstance().getReference().child("markes").child(firebaseUser.getUid());
        firebaseRecyclerAdapterNotes = new FirebaseRecyclerAdapter<NotesModel, notesHolderView>(
                NotesModel.class,
                R.layout.notes_item,
                notesHolderView.class,
                queryNotes
        ) {
            @Override
            protected void populateViewHolder(notesHolderView notesHolderView, NotesModel notesModel, int i) {

                final String listPostKey = getRef(i).getKey();

                //FirebaseDatabase.getInstance().getReference().child("notes")
                dbRef.child("markes").child(firebaseUser.getUid()).child(listPostKey).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String note = String.valueOf(dataSnapshot.child("Model1").getValue());
                        String name = String.valueOf(dataSnapshot.child("Model1").getKey());

                        String note2 = String.valueOf(dataSnapshot.child("Model2").getValue());
                        String name2 = String.valueOf(dataSnapshot.child("Model2").getKey());

                        String note3 = String.valueOf(dataSnapshot.child("Model3").getValue());
                        String name3 = String.valueOf(dataSnapshot.child("model3").getKey());



                        notesHolderView.modelNote.setText(note);
                        notesHolderView.semester.setText(listPostKey);
                        notesHolderView.modelName.setText(name);

                        notesHolderView.model2Note.setText(note2);
                        notesHolderView.model2Name.setText(name2);

                        notesHolderView.model3Note.setText(note3);
                        notesHolderView.model3Name.setText(name3);

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        };
        noteRecycler.setAdapter(firebaseRecyclerAdapterNotes);
    }

    private void widget() {

        noteRecycler = view.findViewById(R.id.noteRecycler);
        noteRecycler.setHasFixedSize(true);
        mLayoutManagersNotes = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        noteRecycler.setLayoutManager(mLayoutManagersNotes);
    }

    public static class notesHolderView extends RecyclerView.ViewHolder{

        View view;
        TextView modelName, modelNote;
        TextView model2Name, model2Note;
        TextView model3Name, model3Note;
        TextView semester;

        public notesHolderView(@NonNull View itemView) {
            super(itemView);

            view = itemView;
            modelNote = view.findViewById(R.id.mNote1);
            modelName = view.findViewById(R.id.model1Name);
            model2Note = view.findViewById(R.id.mNote2);
            model2Name = view.findViewById(R.id.model2Name);
            model3Note = view.findViewById(R.id.mNote3);
            model3Name = view.findViewById(R.id.model3Name);
            semester = view.findViewById(R.id.semesterName);
        }
    }


}