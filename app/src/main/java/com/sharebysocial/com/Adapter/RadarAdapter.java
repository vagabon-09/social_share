package com.sharebysocial.com.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.sharebysocial.com.Algorithm.NameFormation;
import com.sharebysocial.com.Model.RadarModel;
import com.sharebysocial.com.R;

import java.util.List;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class RadarAdapter extends RecyclerView.Adapter<RadarAdapter.MyViewHolder> {
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    String userId = mAuth.getUid();

    List<RadarModel> radarModelsList;

    public RadarAdapter(List<RadarModel> radarModelsList) {
        this.radarModelsList = radarModelsList;
    }

    @NonNull
    @Override
    public RadarAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_scanning_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RadarAdapter.MyViewHolder holder, int position) {
        String user = radarModelsList.get(position).getUserId();
        holder.scanningName.setText(NameFormation.getShortName(radarModelsList.get(position).getUserName()));
//        Log.d("UserId", "onBindViewHolder: " + radarModelsList.get(position).getUserId());
    }


    @Override
    public int getItemCount() {
        return radarModelsList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        CircleImageView scanningImage;
        TextView scanningName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            scanningImage = itemView.findViewById(R.id.profile_image);
            scanningName = itemView.findViewById(R.id.scanning_user_name_id);
        }
    }
}
