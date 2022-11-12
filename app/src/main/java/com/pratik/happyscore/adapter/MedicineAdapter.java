package com.pratik.happyscore.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pratik.happyscore.R;
import com.pratik.happyscore.mainFragments.ReminderFragment;
import com.pratik.happyscore.models.MedicineModel;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MedicineAdapter extends RecyclerView.Adapter <MedicineAdapter.ViewHolder>{

    String[] month = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
    String[] day = {"Sun","Mon","Tue","Wed","Thus","Fri","Sat"};

    Context remideractivity;
    ArrayList<MedicineModel> medicineModelArrayList;

    FirebaseAuth auth;
    FirebaseDatabase database;



    public MedicineAdapter(@NotNull FragmentActivity requireActivity, @NotNull ArrayList<MedicineModel> medicineArrayList) {
        Log.d("adapter","initialized");
        this.remideractivity = requireActivity;
        this.medicineModelArrayList = medicineArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("adapter","veiw holder created");

        View view = LayoutInflater.from(remideractivity).inflate(R.layout.item_pill,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("adapter","view holder binded");

        MedicineModel medicine = medicineModelArrayList.get(position);

        holder.title.setText(medicine.getTitle());
        holder.discription.setText(medicine.getDiscription());
        holder.time.setText(medicine.getTime());
        if (medicine.getStatus()) {
            holder.status.setText("COMPLETED");
            holder.status.setTextColor(Color.parseColor("#11F31E"));
        }
        else {
            holder.status.setText("PENDING");
            holder.status.setTextColor(Color.RED);
        }
        try {
            Date date  = new SimpleDateFormat("dd/MM/yyyy").parse(medicine.getDate());
            int tdate = date.getDate();
            holder.date.setText(String.valueOf(tdate));
            holder.month.setText(month[date.getMonth()]);
            holder.day.setText(day[date.getDay()]);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.time.setText(medicine.getTime());

        int pos = position;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth = FirebaseAuth.getInstance();
                database = FirebaseDatabase.getInstance();
                BottomSheetDialog dailog  = new BottomSheetDialog(remideractivity,R.style.BottomSheetStyle);
                dailog.setContentView(R.layout.confirstatus);
                dailog.show();
                TextView yes,no;
                yes = dailog.findViewById(R.id.yesbtn);
                no = dailog.findViewById(R.id.nobtn);

                if (auth==null)return;
                DatabaseReference ref = database.getReference("Users").child(auth.getUid()).child("medicines").child(String.valueOf(pos+1));

                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dailog.dismiss();
                        ref.child("status").setValue(true);


                    }
                });
                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dailog.dismiss();
                        ref.child("status").setValue(false);

                    }
                });
            }
        });


    }

    @Override
    public int getItemCount() {
        return medicineModelArrayList == null ? 0 : medicineModelArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView day, month, date , time , title , discription, status;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.date);
            day = itemView.findViewById(R.id.day);
            month = itemView.findViewById(R.id.month);
            time = itemView.findViewById(R.id.time);
            title = itemView.findViewById(R.id.title);
            status = itemView.findViewById(R.id.status);
            discription = itemView.findViewById(R.id.description);
        }
    }
}
