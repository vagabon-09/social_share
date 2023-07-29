package com.sharebysocial.com.RoomDB.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.sharebysocial.com.Helper.DateConverter;
import com.sharebysocial.com.R;
import com.sharebysocial.com.RoomDB.Model.HistoryModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.MyViewHolder> {
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private ArrayList<HistoryModel> historyModels = new ArrayList<>();

    public HistoryAdapter(ArrayList<HistoryModel> historyModels) {
        this.historyModels = historyModels;
    }

    @NonNull
    @Override
    public HistoryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_history_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.MyViewHolder holder, int position) {
        DateConverter dateConverter = new DateConverter();
        String userId = mAuth.getUid();
        Log.d("userId", "onBindViewHolder: " + userId);

        if (!Objects.equals(userId, historyModels.get(position).getUserId())) {

        }
        holder.sf_name.setText(historyModels.get(position).getUserName());
//        holder.sf_date.setText(historyModels.get(position).getDate());
        Glide.with(holder.sf_circleImg.getContext()).load(historyModels.get(position).getUserImage()).into(holder.sf_circleImg);
        holder.sf_date.setText(dateConverter.convertDate("dd - MM - yy", historyModels, position));

    }

    @Override
    public int getItemCount() {
        return historyModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView sf_circleImg;
        TextView sf_name;
        TextView sf_date;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            sf_circleImg = itemView.findViewById(R.id.circleImageView);
            sf_name = itemView.findViewById(R.id.sf_user_name);
            sf_date = itemView.findViewById(R.id.sf_user_date);
        }
    }
}
