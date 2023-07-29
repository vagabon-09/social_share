package com.sharebysocial.com.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
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
import com.sharebysocial.com.RoomDB.Helper.DatabaseHelper;
import com.sharebysocial.com.RoomDB.Model.HistoryModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        String user = radarModelsList.get(position).getUserId(); // Fetching user id from db
        holder.scanningName.setText(NameFormation.getShortName(radarModelsList.get(position).getUserName())); // Fetching user name from db

        holder.friendProfileScreen.setOnClickListener(v -> { // when some one click on friend icon button
            insertIntoDB(holder, position); // Inserting data to room database
            Intent intent = new Intent(context, FriendViewActivity.class);
            intent.putExtra("userAuthId", user);
//            intent.putExtra("userName", radarModelsList.get(position).getUserName());
//            intent.putExtra("userImg", radarModelsList.get(position).getUserImage());
            context.startActivity(intent);
        });
        // Log.d("UserId", "onBindViewHolder: " + radarModelsList.get(position).getUserId());
    }

    private void insertIntoDB(MyViewHolder holder, int position) {
        boolean isDataExist = false;
        DatabaseHelper helper = DatabaseHelper.getDB(holder.itemView.getContext());
        String name = radarModelsList.get(position).getUserName();
        String imgUrl = radarModelsList.get(position).getUserImage();
        String userId = radarModelsList.get(position).getUserId();
        long currentMillisecond = System.currentTimeMillis();
//        Log.d("userDetails", "insertIntoDB: " + name + "\n" + imgUrl + "\n" + userId);
        ArrayList<HistoryModel> historyModels = (ArrayList<HistoryModel>) helper.historyDAO().getAllHistory();
        if (historyModels.size() == 0) {

            Log.d("isDbEmpty", "insertIntoDB: " + "room db is empty");
            helper.historyDAO().addHistory(new HistoryModel(name, imgUrl, userId, currentMillisecond));


        } else {
            Log.d("isDbEmpty", "insertIntoDB: " + "room db is not empty");
            for (int i = 0; i < historyModels.size(); i++) {
                if (Objects.equals(historyModels.get(i).getUserId(), userId)) {
                    isDataExist = true;
                }
            }
        }
        if (!isDataExist) {
            helper.historyDAO().addHistory(new HistoryModel(name, imgUrl, userId, currentMillisecond));
        } else {
            Log.d("isDataExist", "insertIntoDB: " + "data is exist now");
        }


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
