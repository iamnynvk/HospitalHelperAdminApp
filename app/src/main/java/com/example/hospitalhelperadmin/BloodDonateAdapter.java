package com.example.hospitalhelperadmin;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnItemClickListener;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BloodDonateAdapter extends FirebaseRecyclerAdapter<BloodDetailHolder,BloodDonateAdapter.mybloodadapter> {

    public BloodDonateAdapter(@NonNull FirebaseRecyclerOptions<BloodDetailHolder> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull mybloodadapter mybloodadapter, int position, @NonNull BloodDetailHolder bloodDetailHolder) {
        mybloodadapter.patientname.setText(bloodDetailHolder.getFullname());
        mybloodadapter.emailid.setText(bloodDetailHolder.getEmail());
        mybloodadapter.mobileno.setText(bloodDetailHolder.getMobilno());
        mybloodadapter.bloodgroup.setText(bloodDetailHolder.getBloodgroup());
        mybloodadapter.age.setText(bloodDetailHolder.getAge());

        mybloodadapter.DeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseDatabase.getInstance().getReference().child("BloodDonateRequest")
                        .child(getRef(position).getKey()).removeValue();
            }
        });

        mybloodadapter.UpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogPlus dialogPlus = DialogPlus.newDialog(mybloodadapter.bloodgroup.getContext())
                        .setContentHolder(new ViewHolder(R.layout.donatebloodupdate))
                        .setExpanded(true,1100)  // This will enable the expand feature, (similar to android L share dialog)
                        .create();

                View myview = dialogPlus.getHolderView();
                final EditText fullname = myview.findViewById(R.id.fullname_edittext);
                final EditText mobileno = myview.findViewById(R.id.mobile_edittext);
                final EditText emailid = myview.findViewById(R.id.email_edittext);
                final EditText age = myview.findViewById(R.id.ageB);
                final EditText bloodgroup = myview.findViewById(R.id.bloodgroup_edittext);
                final Button submitbtn = myview.findViewById(R.id.submit_button);

                fullname.setText(bloodDetailHolder.getFullname());
                mobileno.setText(bloodDetailHolder.getMobilno());
                emailid.setText(bloodDetailHolder.getEmail());
                age.setText(bloodDetailHolder.getAge());
                bloodgroup.setText(bloodDetailHolder.getBloodgroup());

                dialogPlus.show();

                submitbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("fullname",fullname.getText().toString());
                        map.put("mobilno",mobileno.getText().toString());
                        map.put("email",emailid.getText().toString());
                        map.put("age",age.getText().toString());
                        map.put("bloodgroup",bloodgroup.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("BloodDonateRequest")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });
            }
        });
    }

    @NonNull
    @Override
    public mybloodadapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.blood_view,parent,false);

        return new mybloodadapter(view);
    }

    public class mybloodadapter extends RecyclerView.ViewHolder{
        CardView cardview;
        RelativeLayout Blood_Relative;
        TextView patientname,emailid,mobileno,gender,bloodgroup,age;
        ImageView DeleteBtn,UpdateButton;

        public mybloodadapter(@NonNull View itemView) {
            super(itemView);

            cardview = (CardView)itemView.findViewById(R.id.cardView);
            Blood_Relative = (RelativeLayout)itemView.findViewById(R.id.blood_relative);
            patientname = (TextView) itemView.findViewById(R.id.patient_name_txt);
            emailid = (TextView)itemView.findViewById(R.id.email_id_txt);
            mobileno = (TextView)itemView.findViewById(R.id.mobileno_txt);
            gender = (TextView)itemView.findViewById(R.id.gendertxt);
            bloodgroup = (TextView)itemView.findViewById(R.id.bloodgrouptxt);
            age = (TextView)itemView.findViewById(R.id.agetxt);
            DeleteBtn = (ImageView)itemView.findViewById(R.id.deleteButton);
            UpdateButton = (ImageView)itemView.findViewById(R.id.updateButton);
        }
    }
}
