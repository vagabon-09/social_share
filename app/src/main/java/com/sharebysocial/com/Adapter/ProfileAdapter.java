package com.sharebysocial.com.Adapter;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.materialswitch.MaterialSwitch;
import com.google.firebase.database.DatabaseReference;
import com.sharebysocial.com.Interfaces.ItemTouchHelperAdapter;
import com.sharebysocial.com.Model.ProfileModel;
import com.sharebysocial.com.R;


public class ProfileAdapter extends FirebaseRecyclerAdapter<ProfileModel, ProfileAdapter.ProfileViewHolder> implements ItemTouchHelperAdapter {

    ProfileModel model;

    public ProfileAdapter(@NonNull FirebaseRecyclerOptions<ProfileModel> options) {
        super(options);
    }


    @Override
    protected void onBindViewHolder(@NonNull ProfileAdapter.ProfileViewHolder holder, int position, @NonNull ProfileModel model) {
       this.model = model;
        holder.materialCardView.setOnClickListener(v -> {
//            Toast.makeText(holder.appIcon.getContext(), "You clicked card", Toast.LENGTH_SHORT).show();
        });

        holder.visibleBtn.setOnClickListener(v -> {
            holder.invisibleBtn.setVisibility(View.VISIBLE);
            holder.visibleBtn.setVisibility(View.INVISIBLE);
        });

        holder.invisibleBtn.setOnClickListener(v -> {
            holder.visibleBtn.setVisibility(View.VISIBLE);
            holder.invisibleBtn.setVisibility(View.INVISIBLE);
        });

        changeIconName(holder, model);
    }

    private void changeIconName(ProfileViewHolder holder, ProfileModel model) {

        if (model.getAccountName().equals("Facebook")) {
            holder.appName.setText(R.string.fb);
            holder.appIcon.setImageResource(R.drawable.facebook);
        }

        if (model.getAccountName().equals("Instagram")) {
            holder.appName.setText(R.string.insta);
            holder.appIcon.setImageResource(R.drawable.instagram);
        }

        if (model.getAccountName().equals("GitHub")) {
            holder.appName.setText(R.string.git);
            holder.appIcon.setImageResource(R.drawable.github);
        }

        if (model.getAccountName().equals("Twitter")) {
            holder.appName.setText(R.string.twitter);
            holder.appIcon.setImageResource(R.drawable.twitter);
        }

        if (model.getAccountName().equals("Snapchat")) {
            holder.appName.setText(R.string.snap);
            holder.appIcon.setImageResource(R.drawable.snapchat);
        }

        if (model.getAccountName().equals("Whatsapp")) {
            holder.appName.setText(R.string.whatsapp);
            holder.appIcon.setImageResource(R.drawable.whatsapp);
        }

        if (model.getAccountName().equals("Reddit")) {
            holder.appName.setText(R.string.reddit);
            holder.appIcon.setImageResource(R.drawable.reddit);
        }

        if (model.getAccountName().equals("Linkedin")) {
            holder.appName.setText(R.string.linkedin);
            holder.appIcon.setImageResource(R.drawable.linkedin);
        }

        if (model.getAccountName().equals("TikTok")) {
            holder.appName.setText(R.string.tiktok);
            holder.appIcon.setImageResource(R.drawable.tik_tok);
        }

        if (model.getAccountName().equals("YouTube")) {
            holder.appName.setText(R.string.yt);
            holder.appIcon.setImageResource(R.drawable.youtube);
        }

        if (model.getAccountName().equals("Pinterest")) {
            holder.appName.setText(R.string.pinterest);
            holder.appIcon.setImageResource(R.drawable.pinterest);
        }

        if (model.getAccountName().equals("Quora")) {
            holder.appName.setText(R.string.quora);
            holder.appIcon.setImageResource(R.drawable.quora);
        }

        if (model.getAccountName().equals("Tumblr")) {
            holder.appName.setText(R.string.tumblr);
            holder.appIcon.setImageResource(R.drawable.tumblr);
        }

        if (model.getAccountName().equals("Twitch")) {
            holder.appName.setText(R.string.twitch);
            holder.appIcon.setImageResource(R.drawable.twitch);
        }

        if (model.getAccountName().equals("Discord")) {
            holder.appName.setText(R.string.discord);
            holder.appIcon.setImageResource(R.drawable.discord);
        }

        if (model.getAccountName().equals("Mastodon")) {
            holder.appName.setText(R.string.mastodon);
            holder.appIcon.setImageResource(R.drawable.mastodon);
        }

        if (model.getAccountName().equals("VKontakte")) {
            holder.appName.setText(R.string.vkontakte);
            holder.appIcon.setImageResource(R.drawable.vkontakte);
        }

        if (model.getAccountName().equals("Weibo")) {
            holder.appName.setText(R.string.vkontakte);
            holder.appIcon.setImageResource(R.drawable.sina_weibo);
        }

        if (model.getAccountName().equals("KakaoTalk")) {
            holder.appName.setText(R.string.talk);
            holder.appIcon.setImageResource(R.drawable.kakao_talk);
        }

        if (model.getAccountName().equals("Website")) {
            holder.appName.setText(R.string.web);
            holder.appIcon.setImageResource(R.drawable.world_wide_web);
        }

        if (model.getAccountName().equals("Email")) {
            holder.appName.setText(R.string.email);
            holder.appIcon.setImageResource(R.drawable.gmail);
        }

        if (model.getAccountName().equals("Other")) {
            holder.appName.setText(R.string.other);
            holder.appIcon.setImageResource(R.drawable.other);
        }


    }

    @NonNull
    @Override

    public ProfileAdapter.ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_account_layout, parent, false);
        return new ProfileViewHolder(v);
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
    }

    @Override
    public void onItemDismiss(int position) {

        notifyItemRemoved(position);
    }

    public static class ProfileViewHolder extends RecyclerView.ViewHolder {
        MaterialCardView materialCardView;
        ImageView appIcon, visibleBtn, invisibleBtn;
        TextView appName;
        LinearLayout EditButton;


        public ProfileViewHolder(@NonNull View itemView) {
            super(itemView);
            materialCardView = itemView.findViewById(R.id.ProfileCardClickId);
            appIcon = itemView.findViewById(R.id.appIconId);
            appName = itemView.findViewById(R.id.appNameId);
            visibleBtn = itemView.findViewById(R.id.visibleId);
            invisibleBtn = itemView.findViewById(R.id.invisibleId);
        }
    }
}
