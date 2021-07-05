package com.example.hospitalhelperadmin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class AppoinmentAdapter extends  FirebaseRecyclerAdapter<DoctorAppoinmentDataHolder,AppoinmentAdapter.myadapter>  {

    public AppoinmentAdapter(@NonNull FirebaseRecyclerOptions<DoctorAppoinmentDataHolder> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull AppoinmentAdapter.myadapter myadapter, int i, @NonNull DoctorAppoinmentDataHolder doctorAppoinmentDataHolder) {
        myadapter.FirstName.setText(doctorAppoinmentDataHolder.getFirstname());
        myadapter.LastName.setText(doctorAppoinmentDataHolder.getLastname());
        myadapter.MobileNo.setText(doctorAppoinmentDataHolder.getMobileno());
        myadapter.BirthDate.setText(doctorAppoinmentDataHolder.getBirthdate());
        myadapter.DoctorName.setText(doctorAppoinmentDataHolder.getDoctorname());
        myadapter.AppoinmentDate.setText(doctorAppoinmentDataHolder.getDoctorbookdate());
        myadapter.TimeRule.setText(doctorAppoinmentDataHolder.getTime());
        myadapter.DoctorType.setText(doctorAppoinmentDataHolder.getType());

        Glide.with(myadapter.UserPhoto.getContext()).load(doctorAppoinmentDataHolder.getProfileimg()).into(myadapter.UserPhoto);
        Glide.with(myadapter.DoctorPhoto.getContext()).load(doctorAppoinmentDataHolder.getDoctorurl()).into(myadapter.DoctorPhoto);

        myadapter.DeleteBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseDatabase.getInstance().getReference().child("DoctorAppoimentDatail")
                        .child(getRef(i).getKey()).removeValue();
            }
        });

        myadapter.UpdateBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogPlus dialogPlus = DialogPlus.newDialog(myadapter.UpdateBTN.getContext())
                        .setContentHolder(new ViewHolder(R.layout.patientsappoimentupdatedata))
                        .setExpanded(true, 1100)  // This will enable the expand feature, (similar to android L share dialog)
                        .create();

                View myview = dialogPlus.getHolderView();
                final EditText firstname = myview.findViewById(R.id.fullname_edittext1);
                final EditText lastname = myview.findViewById(R.id.lastname_edittext1);
                final EditText mobileno = myview.findViewById(R.id.mobile_edittext1);
                final Button Submit = myview.findViewById(R.id.submit_button1);

                firstname.setText(doctorAppoinmentDataHolder.getFirstname());
                lastname.setText(doctorAppoinmentDataHolder.getLastname());
                mobileno.setText(doctorAppoinmentDataHolder.getMobileno());

                dialogPlus.show();

                Submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("firstname",firstname.getText().toString());
                        map.put("lastname",lastname.getText().toString());
                        map.put("mobileno",mobileno.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("DoctorAppoimentDatail")
                                .child(getRef(i).getKey()).updateChildren(map)
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
    public AppoinmentAdapter.myadapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.appoinment_view,parent,false);

        return new myadapter(view);
    }

    public class myadapter extends RecyclerView.ViewHolder {
        CardView CardViews;
        RelativeLayout AppoinmentRelative;
        TextView FirstName,LastName,MobileNo,BirthDate,DoctorName,AppoinmentDate,TimeRule,DoctorType;
        ImageView DeleteBTN,UpdateBTN;
        CircleImageView UserPhoto,DoctorPhoto;

        public myadapter(@NonNull View itemView) {
            super(itemView);

            CardViews = (CardView)itemView.findViewById(R.id.cardView1);
            AppoinmentRelative = (RelativeLayout)itemView.findViewById(R.id.appoint_view);
            FirstName = (TextView) itemView.findViewById(R.id.patient_firstname);
            LastName = (TextView)itemView.findViewById(R.id.patient_lastname);
            MobileNo = (TextView)itemView.findViewById(R.id.mobileno_patient);
            BirthDate = (TextView)itemView.findViewById(R.id.birthdate_patient);
            DoctorName = (TextView)itemView.findViewById(R.id.doctorname);
            AppoinmentDate = (TextView)itemView.findViewById(R.id.appoint_date_fix);
            TimeRule = (TextView)itemView.findViewById(R.id.timebook);
            DoctorType = (TextView)itemView.findViewById(R.id.type);
            DeleteBTN = (ImageView)itemView.findViewById(R.id.deleteButton1);
            UpdateBTN = (ImageView)itemView.findViewById(R.id.updateButton1);
            UserPhoto = (CircleImageView)itemView.findViewById(R.id.patient_profile_photo);
            DoctorPhoto = (CircleImageView)itemView.findViewById(R.id.doctor_profile_photo);
        }
    }
}
