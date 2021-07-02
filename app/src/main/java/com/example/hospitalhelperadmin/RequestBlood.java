package com.example.hospitalhelperadmin;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class RequestBlood extends Fragment {
    RecyclerView recyclerView;
    BloodAdapter recycleviewAdapter;
    private ArrayList<BloodDetailHolder> bloodDetailHolders;
    Context context;

    DatabaseReference mdatabaseref;
    StorageReference mstorageref;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_request_blood, container, false);

        context = getContext();
        recyclerView = view.findViewById(R.id.recycleview);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<BloodDetailHolder> options =
                new FirebaseRecyclerOptions.Builder<BloodDetailHolder>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("BloodRequest"),BloodDetailHolder.class).build();

        recycleviewAdapter = new BloodAdapter(options);
        recyclerView.setAdapter(recycleviewAdapter);

        return view;
        /*// Product Set Layout
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        mdatabaseref = FirebaseDatabase.getInstance().getReference();
        mstorageref = FirebaseStorage.getInstance().getReference();

        bloodDetailHolders = new ArrayList<>();

        Blood();


        return view;
    }

    private void Blood() {
        final Query query = mdatabaseref.child("BloodRequest");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    BloodDetailHolder Holder = new BloodDetailHolder();
                    Holder.setFullname(dataSnapshot.child("fullname").getValue().toString());
                    Holder.setEmail(dataSnapshot.child("email").getValue().toString());
                    Holder.setMobileno(dataSnapshot.child("mobilno").getValue().toString());
                    Holder.setAge(dataSnapshot.child("age").getValue().toString());
                    Holder.setBloodgroup(dataSnapshot.child("bloodgroup").getValue().toString());
                    Holder.setGenderbtn(dataSnapshot.child("genderbtn").getValue().toString());

                    bloodDetailHolders.add(Holder);
                }
                GridLayoutManager manager = new GridLayoutManager(context,1);
                recyclerView.setLayoutManager(manager);
                recycleviewAdapter = new BloodAdapter(context,bloodDetailHolders);
                recyclerView.setAdapter(recycleviewAdapter);
                recycleviewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }*/
    }

    @Override
    public void onStart() {
        super.onStart();
        recycleviewAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        recycleviewAdapter.stopListening();
    }
}