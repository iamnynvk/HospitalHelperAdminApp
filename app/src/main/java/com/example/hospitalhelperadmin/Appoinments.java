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

public class Appoinments extends Fragment {

    RecyclerView viewMain;
    AppoinmentAdapter adapter;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_appoinments, container, false);

        context = getContext();
        viewMain = view.findViewById(R.id.recycleview1);

        viewMain.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<DoctorAppoinmentDataHolder> recyclerOptions =
                new FirebaseRecyclerOptions.Builder<DoctorAppoinmentDataHolder>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("DoctorAppoimentDatail"),DoctorAppoinmentDataHolder.class)
                .build();

        adapter = new AppoinmentAdapter(recyclerOptions);
        viewMain.setAdapter(adapter);

        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}