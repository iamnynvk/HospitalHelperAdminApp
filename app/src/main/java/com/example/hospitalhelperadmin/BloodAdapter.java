package com.example.hospitalhelperadmin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.List;

public class BloodAdapter extends FirebaseRecyclerAdapter<BloodDetailHolder,BloodAdapter.mybloodadapter> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public BloodAdapter(@NonNull FirebaseRecyclerOptions<BloodDetailHolder> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull mybloodadapter mybloodadapter, int i, @NonNull BloodDetailHolder bloodDetailHolder) {
        mybloodadapter.patientname.setText(bloodDetailHolder.getFullname());
        mybloodadapter.emailid.setText(bloodDetailHolder.getEmail());
        mybloodadapter.mobileno.setText(bloodDetailHolder.getMobilno());
        mybloodadapter.gender.setText(bloodDetailHolder.getGenderbtn());
        mybloodadapter.bloodgroup.setText(bloodDetailHolder.getBloodgroup());
        mybloodadapter.age.setText(bloodDetailHolder.getAge());
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
        ImageView DeleteButton,UpdateButton;

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
            DeleteButton = (ImageView)itemView.findViewById(R.id.deleteButton);
            UpdateButton = (ImageView)itemView.findViewById(R.id.updateButton);
        }
    }

    /*Context mcontext;
    List<BloodDetailHolder> BloodDetail;

    public BloodAdapter(Context mcontext, List<BloodDetailHolder> doctorList) {
        this.mcontext = mcontext;
        BloodDetail = doctorList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.blood_view,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BloodAdapter.ViewHolder holder, int position) {

        holder.patientname.setText(BloodDetail.get(position).getFullname());
        holder.emailid.setText(BloodDetail.get(position).getEmail());
        holder.mobileno.setText(BloodDetail.get(position).getMobileno());
        holder.gender.setText(BloodDetail.get(position).getGenderbtn());
        holder.age.setText(BloodDetail.get(position).getAge());
        holder.bloodgroup.setText(BloodDetail.get(position).getBloodgroup());

       Picasso is Slower than Glide i don't know why?
        Picasso.get().load(DoctorList.get(position).getDoctorurl())
                .into(holder.doctorImage, new Callback() {
                    @Override
                    public void onSuccess() {
                    }

                    @Override
                    public void onError(Exception e) {
                    }
                });

        Glide is Faster Fatching Image on Server's
        Glide.with(holder.doctorImage.getContext()).load(BloodDetail.get(position).getDoctorurl()).into(holder.doctorImage);

        Select Data Options
        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(v.getContext(), Doctor_Detail.class);

                in.putExtra("DoctorImage",DoctorList.get(position).getDoctorurl());
                in.putExtra("DoctorName",DoctorList.get(position).getDoctorname());
                in.putExtra("DoctorQualification",DoctorList.get(position).getQualification());
                in.putExtra("DoctorType",DoctorList.get(position).getType());
                in.putExtra("DoctorTime",DoctorList.get(position).getTime());

                v.getContext().startActivity(in);

            }
        });
    }

    @Override
    public int getItemCount() {
        return BloodDetail.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardview;
        RelativeLayout Blood_Relative;
        TextView patientname,emailid,mobileno,gender,bloodgroup,age;
        ImageView DeleteButton,UpdateButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cardview = (CardView)itemView.findViewById(R.id.cardView);
            Blood_Relative = (RelativeLayout)itemView.findViewById(R.id.blood_relative);
            patientname = (TextView) itemView.findViewById(R.id.patient_name_txt);
            emailid = (TextView)itemView.findViewById(R.id.email_id_txt);
            mobileno = (TextView)itemView.findViewById(R.id.mobileno_txt);
            gender = (TextView)itemView.findViewById(R.id.gendertxt);
            bloodgroup = (TextView)itemView.findViewById(R.id.bloodgrouptxt);
            age = (TextView)itemView.findViewById(R.id.agetxt);
            DeleteButton = (ImageView)itemView.findViewById(R.id.deleteButton);
            UpdateButton = (ImageView)itemView.findViewById(R.id.updateButton);
        }
    }*/
}
