package com.sharebysocial.com.RoomDB.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sharebysocial.com.Helper.DateConverter;
import com.sharebysocial.com.R;
import com.sharebysocial.com.RoomDB.Helper.DatabaseHelper;
import com.sharebysocial.com.RoomDB.Model.HistoryModel;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.MyViewHolder> {
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
        DatabaseHelper helper = DatabaseHelper.getDB(holder.sf_circleImg.getContext()); // created object of DatabaseHelper class
        //setting user name from firebase db
        holder.sf_name.setText(historyModels.get(position).getUserName());
        // Using Glide library setting image to image view (from url)
        Glide.with(holder.sf_circleImg.getContext()).load(historyModels.get(position).getUserImage()).into(holder.sf_circleImg);
        //Converting millisecond in customise data , month and year
        holder.sf_date.setText(DateConverter.globalConvertTime("dd-MMM-yy", historyModels.get(position).getDate()));
        holder.cardBtn.setOnLongClickListener(v -> { // when long click on history card
            holder.sf_deleteBtn.setVisibility(View.VISIBLE); // make delete button visible
            return false;
        });
        holder.sf_deleteBtn.setOnClickListener(v -> { // when click on delete button

            helper.historyDAO().deleteHistory(historyModels.get(position).getUserId()); // deleting data from delete history database
            holder.sf_deleteBtn.setVisibility(View.GONE); // delete button visibility is gone
            historyModels.remove(position); // removing data from history models
            notifyItemRemoved(position); // now notifying to recycler view that data is removed
        });

    }

    @Override
    public int getItemCount() {
        return historyModels.size();
    }

    public void filteredList(ArrayList<HistoryModel> arrayList) {
        this.historyModels = arrayList;
        notifyDataSetChanged();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView sf_circleImg, sf_deleteBtn;
        TextView sf_name;
        TextView sf_date;
        ConstraintLayout cardBtn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            sf_circleImg = itemView.findViewById(R.id.circleImageView);
            sf_name = itemView.findViewById(R.id.sf_user_name);
            sf_date = itemView.findViewById(R.id.sf_user_date);
            sf_deleteBtn = itemView.findViewById(R.id.sh_deleteBtnId);
            cardBtn = itemView.findViewById(R.id.single_card_history);
        }
    }
}
