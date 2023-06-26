package com.sharebysocial.com.Adapter;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.card.MaterialCardView;
import com.sharebysocial.com.Model.ProfileModel;
import com.sharebysocial.com.R;

public class ProfileAdapter extends FirebaseRecyclerAdapter<ProfileModel,ProfileAdapter.ProfileViewHolder> {

    public ProfileAdapter(@NonNull FirebaseRecyclerOptions<ProfileModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ProfileAdapter.ProfileViewHolder holder, int position, @NonNull ProfileModel model) {
        holder.materialCardView.setOnClickListener(v ->
                        Toast.makeText(v.getContext(), "https://facebook/user/"+model.getUserName(), Toast.LENGTH_SHORT).show()
                );
    }

    @NonNull
    @Override
    public ProfileAdapter.ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_account_layout,parent,false);
        return new ProfileViewHolder(v);
    }

    public static class ProfileViewHolder extends RecyclerView.ViewHolder {
        MaterialCardView materialCardView;
        public ProfileViewHolder(@NonNull View itemView) {
            super(itemView);
            materialCardView = itemView.findViewById(R.id.ProfileCardClickId);
        }
    }
}
