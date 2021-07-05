package com.example.hospitalhelperadmin;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class DonateBlood extends Fragment {
    RecyclerView recyclerView;
    BloodDonateAdapter recycleviewAdapter;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_donate_blood, container, false);

        context = getContext();
        recyclerView = view.findViewById(R.id.recycleview);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<BloodDetailHolder> options =
                new FirebaseRecyclerOptions.Builder<BloodDetailHolder>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("BloodDonateRequest"),BloodDetailHolder.class).build();

        recycleviewAdapter = new BloodDonateAdapter(options);
        recyclerView.setAdapter(recycleviewAdapter);

        return view;
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