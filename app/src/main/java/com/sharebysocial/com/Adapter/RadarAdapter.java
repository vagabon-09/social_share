package com.sharebysocial.com.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.sharebysocial.com.Activities.FriendViewActivity;
import com.sharebysocial.com.Algorithm.NameFormation;
import com.sharebysocial.com.Model.RadarModel;
import com.sharebysocial.com.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RadarAdapter extends RecyclerView.Adapter<RadarAdapter.MyViewHolder> {
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    String userId = mAuth.getUid();

    List<RadarModel> radarModelsList;
    Context context;

    public RadarAdapter(List<RadarModel> radarModelsList, Context context) {
        this.radarModelsList = radarModelsList;
        this.context = context;
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
        holder.friendProfileScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FriendViewActivity.class);
                intent.putExtra("userAuthId", user);
                context.startActivity(intent);
            }
        });
//        Log.d("UserId", "onBindViewHolder: " + radarModelsList.get(position).getUserId());
    }


    @Override
    public int getItemCount() {
        return radarModelsList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        CircleImageView scanningImage;
        TextView scanningName;
        ConstraintLayout friendProfileScreen;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            scanningImage = itemView.findViewById(R.id.profile_image);
            scanningName = itemView.findViewById(R.id.scanning_user_name_id);
            friendProfileScreen = itemView.findViewById(R.id.friend_profile_card_id);
        }
    }
}
