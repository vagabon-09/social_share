package com.sharebysocial.com.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.auth.FirebaseAuth;
import com.sharebysocial.com.Activities.FriendViewActivity;
import com.sharebysocial.com.Model.RadarModel;
import com.sharebysocial.com.R;
import com.sharebysocial.com.RoomDB.Helper.DatabaseHelper;
import com.sharebysocial.com.RoomDB.Model.HistoryModel;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RadarAdapter extends RecyclerView.Adapter<RadarAdapter.MyViewHolder> {
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
//    String userId = mAuth.getUid();

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
        String user = radarModelsList.get(position).getUserId(); // Fetching user id from db
        holder.scanningName.setText(radarModelsList.get(position).getUserName()); // Fetching user name from db
        String profileImg = radarModelsList.get(position).getUserImage();
        String userName = radarModelsList.get(position).getUserName();
        Glide.with(holder.scanningName.getContext()).load(profileImg).centerCrop().into(holder.scanningImage);

        holder.friendScreen.setOnClickListener(v -> { // when some one click on friend icon button
            insertIntoDB(holder, position); // Inserting data to room database
            Intent intent = new Intent(context, FriendViewActivity.class);
            intent.putExtra("userAuthId", user); // transferring data form one activity to another activity
            intent.putExtra("userName", userName); // transferring data form one activity to another activity
            intent.putExtra("profileImg", profileImg); // transferring data form one activity to another activity
            context.startActivity(intent);
        });
    }


    private void insertIntoDB(MyViewHolder holder, int position) { // This function is using to insert data to room database
        boolean isDataExist = false;
        DatabaseHelper helper = DatabaseHelper.getDB(holder.itemView.getContext());
        String name = radarModelsList.get(position).getUserName(); //Fetching user name form db
        String imgUrl = radarModelsList.get(position).getUserImage(); // Fetching user image from db
        String userId = radarModelsList.get(position).getUserId(); // Fetching userId form db
        long currentMillisecond = System.currentTimeMillis(); // Fetching current time in millisecond

        // History model setUp
        ArrayList<HistoryModel> historyModels = (ArrayList<HistoryModel>) helper.historyDAO().getAllHistory();
        for (HistoryModel hmd : historyModels) { // Fetching all data from room db
            if (userId.equals(hmd.getUserId())) { // comparing all data with current data if exits then setting isDataExist = true
                isDataExist = true; // if exist then setting data to true
                break;
            }
        }


        if (isDataExist) { // if isDataExist then we ignoring the data or we can say we are not inserting data
            Log.d("isDataExist", "insertIntoDB: " + "data is exist now");
        } else {
            // if current data don't exist then we are inserting to data base
            helper.historyDAO().addHistory(new HistoryModel(name, imgUrl, userId, currentMillisecond));
        }


    }


    @Override
    public int getItemCount() {
        return radarModelsList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        CircleImageView scanningImage;
        TextView scanningName;
        MaterialCardView friendScreen;
        LinearLayout shimmerLinear;
        RecyclerView radderRv;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            scanningImage = itemView.findViewById(R.id.profile_image);
            friendScreen = itemView.findViewById(R.id.friendCardId);
            scanningName = itemView.findViewById(R.id.scanning_user_name_id);
            shimmerLinear = itemView.findViewById(R.id.fragmentRadarShimmerId);
            radderRv = itemView.findViewById(R.id.searchResultScanningId);
        }
    }
}
