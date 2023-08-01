package com.sharebysocial.com.Adapter;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.sharebysocial.com.Model.AccountModel;
import com.sharebysocial.com.Model.ProfileModel;
import com.sharebysocial.com.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class FriendSMAdapter extends FirebaseRecyclerAdapter<ProfileModel, FriendSMAdapter.MyViewModel> {

    FirebaseRecyclerOptions<AccountModel> accountModel;

    public FriendSMAdapter(@NonNull FirebaseRecyclerOptions<ProfileModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull FriendSMAdapter.MyViewModel holder, int position, @NonNull ProfileModel model) {
        setSocialAccount(holder, model);
//        Log.d("socialAccount", "onBindViewHolder: " + model.getAccountName());
        holder.socialMediaBtn.setOnClickListener(v -> {
            openProfile(v,model.getUserName());

        });
    }

    private void openProfile(View view,String user) {
        String facebookPlayStoreUrl = "market://details?id=com.facebook.katana";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(user));
        PackageManager packageManager = view.getContext().getPackageManager();

        if (intent.resolveActivity(packageManager) != null) {
            // Facebook app or browser is available, open the profile URL
            view.getContext().startActivity(intent);
//            Log.d("ProfileClicked", "onBindViewHolder: " + user);
        } else {
            Intent playStoreIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(facebookPlayStoreUrl));
            view.getContext().startActivity(playStoreIntent);
        }

    }

    private void setSocialAccount(MyViewModel holder, ProfileModel model) {
        if (model.getAccountName().equals("Facebook")) {
            holder.profileTextView.setText(R.string.fb);
            holder.profileImage.setImageResource(R.drawable.facebook);
        }

        if (model.getAccountName().equals("Instagram")) {
            holder.profileTextView.setText(R.string.insta);
            holder.profileImage.setImageResource(R.drawable.instagram);
        }

        if (model.getAccountName().equals("GitHub")) {
            holder.profileTextView.setText(R.string.git);
            holder.profileImage.setImageResource(R.drawable.github);
        }

        if (model.getAccountName().equals("Twitter")) {
            holder.profileTextView.setText(R.string.twitter);
            holder.profileImage.setImageResource(R.drawable.twitter);
        }

        if (model.getAccountName().equals("Snapchat")) {
            holder.profileTextView.setText(R.string.snap);
            holder.profileImage.setImageResource(R.drawable.snapchat);
        }

        if (model.getAccountName().equals("Whatsapp")) {
            holder.profileTextView.setText(R.string.whatsapp);
            holder.profileImage.setImageResource(R.drawable.whatsapp);
        }

        if (model.getAccountName().equals("Reddit")) {
            holder.profileTextView.setText(R.string.reddit);
            holder.profileImage.setImageResource(R.drawable.reddit);
        }

        if (model.getAccountName().equals("Linkedin")) {
            holder.profileTextView.setText(R.string.linkedin);
            holder.profileImage.setImageResource(R.drawable.linkedin);
        }

        if (model.getAccountName().equals("TikTok")) {
            holder.profileTextView.setText(R.string.tiktok);
            holder.profileImage.setImageResource(R.drawable.tik_tok);
        }

        if (model.getAccountName().equals("YouTube")) {
            holder.profileTextView.setText(R.string.yt);
            holder.profileImage.setImageResource(R.drawable.youtube);
        }

        if (model.getAccountName().equals("Pinterest")) {
            holder.profileTextView.setText(R.string.pinterest);
            holder.profileImage.setImageResource(R.drawable.pinterest);
        }

        if (model.getAccountName().equals("Quora")) {
            holder.profileTextView.setText(R.string.quora);
            holder.profileImage.setImageResource(R.drawable.quora);
        }

        if (model.getAccountName().equals("Tumblr")) {
            holder.profileTextView.setText(R.string.tumblr);
            holder.profileImage.setImageResource(R.drawable.tumblr);
        }

        if (model.getAccountName().equals("Twitch")) {
            holder.profileTextView.setText(R.string.twitch);
            holder.profileImage.setImageResource(R.drawable.twitch);
        }

        if (model.getAccountName().equals("Discord")) {
            holder.profileTextView.setText(R.string.discord);
            holder.profileImage.setImageResource(R.drawable.discord);
        }

        if (model.getAccountName().equals("Mastodon")) {
            holder.profileTextView.setText(R.string.mastodon);
            holder.profileImage.setImageResource(R.drawable.mastodon);
        }

        if (model.getAccountName().equals("VKontakte")) {
            holder.profileTextView.setText(R.string.vkontakte);
            holder.profileImage.setImageResource(R.drawable.vkontakte);
        }

        if (model.getAccountName().equals("Weibo")) {
            holder.profileTextView.setText(R.string.vkontakte);
            holder.profileImage.setImageResource(R.drawable.sina_weibo);
        }

        if (model.getAccountName().equals("KakaoTalk")) {
            holder.profileTextView.setText(R.string.talk);
            holder.profileImage.setImageResource(R.drawable.kakao_talk);
        }

        if (model.getAccountName().equals("Website")) {
            holder.profileTextView.setText(R.string.web);
            holder.profileImage.setImageResource(R.drawable.world_wide_web);
        }

        if (model.getAccountName().equals("Email")) {
            holder.profileTextView.setText(R.string.email);
//            holder.profileImage.setImageResource(R.drawable.gmail);
        }

        if (model.getAccountName().equals("Other")) {
            holder.profileTextView.setText(R.string.other);
            holder.profileImage.setImageResource(R.drawable.other);
        }

    }

    @NonNull
    @Override
    public FriendSMAdapter.MyViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_social_profile, parent, false);
        return new MyViewModel(view);
    }

    public static class MyViewModel extends RecyclerView.ViewHolder {
        CircleImageView profileImage;
        TextView profileTextView;
        ConstraintLayout socialMediaBtn;

        public MyViewModel(@NonNull View itemView) {
            super(itemView);
            profileImage = itemView.findViewById(R.id.friend_social_profileView);
            profileTextView = itemView.findViewById(R.id.friend_social_profileName);
            socialMediaBtn = itemView.findViewById(R.id.profile_socialMediaBtn);
        }
    }


}

