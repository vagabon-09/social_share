package com.sharebysocial.com.Adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.materialswitch.MaterialSwitch;
import com.sharebysocial.com.Activities.BottomSheetfFragment;
import com.sharebysocial.com.Fragment.AddFragment;
import com.sharebysocial.com.Fragment.HomeFragment;
import com.sharebysocial.com.Helper.Helper;
import com.sharebysocial.com.Model.ProfileModel;
import com.sharebysocial.com.R;

import java.util.Objects;


public class ProfileAdapter extends FirebaseRecyclerAdapter<ProfileModel, ProfileAdapter.ProfileViewHolder> {


    public ProfileAdapter(@NonNull FirebaseRecyclerOptions<ProfileModel> options) {
        super(options);

    }

    @Override
    protected void onBindViewHolder(@NonNull ProfileAdapter.ProfileViewHolder holder, int position, @NonNull ProfileModel model) {
        holder.materialCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        /*
        holder.materialCardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
              BottomSheetDialogFragment bottomSheetDialogFragment = new BottomSheetfFragment();
                bottomSheetDialogFragment.show((((AppCompatActivity) holder.appName.getContext()).getSupportFragmentManager()), bottomSheetDialogFragment.getTag());

                return false;
            }
        });
         */

        holder.visibilitySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    Toast.makeText(buttonView.getContext(), "Checked", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(buttonView.getContext(), "Unchecked", Toast.LENGTH_SHORT).show();
                }
            }
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

    public static class ProfileViewHolder extends RecyclerView.ViewHolder {
        MaterialCardView materialCardView;
        MaterialSwitch visibilitySwitch;
        ImageView appIcon;
        TextView appName;
        LinearLayout EditButton;

        public ProfileViewHolder(@NonNull View itemView) {
            super(itemView);
            materialCardView = itemView.findViewById(R.id.ProfileCardClickId);
            visibilitySwitch = itemView.findViewById(R.id.visibilitySwitchId);
            appIcon = itemView.findViewById(R.id.appIconId);
            appName = itemView.findViewById(R.id.appNameId);
        }
    }
}
