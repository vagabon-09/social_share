package com.sharebysocial.com.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.sharebysocial.com.R;

import java.util.ArrayList;

public class AddFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private final ArrayList<String> arrayList = new ArrayList<>();
    private ImageView profileImage;
    private EditText profileFindId;
    private TextView userIdTitle;


    public AddFragment() {
        // Required empty public constructor
    }

    public static AddFragment newInstance(String param1, String param2) {
        AddFragment fragment = new AddFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // TODO: Rename and change types of parameters
            String mParam1 = getArguments().getString(ARG_PARAM1);
            String mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add, container, false);
        profileImage = view.findViewById(R.id.ProfileIconId);
        profileFindId = view.findViewById(R.id.profile_find_id);
        Spinner profile_spinner = view.findViewById(R.id.profile_spinner_id);
        userIdTitle = view.findViewById(R.id.add_activity_user_name_id);
        // Setting content to dropdown list
        SetDropDown(profile_spinner);

        return view;
    }

    public void selectProfile(int position, View view) {

        if (position == 0) {
            profileImage.setImageResource(R.drawable.share);
            userIdTitle.setText("User Name");
            profileFindId.setHint("user@123");
        }

        if (position == 1) {
            profileImage.setImageResource(R.drawable.facebook);
            userIdTitle.setText("User Name");
            profileFindId.setHint("user@123");
        }

        if (position == 2) {
            profileImage.setImageResource(R.drawable.instagram);
            userIdTitle.setText("User Name");
            profileFindId.setHint("user@123");
        }
        if (position == 3) {
            profileImage.setImageResource(R.drawable.github);
            userIdTitle.setText("User Name");
            profileFindId.setHint("user@123");
        }
        if (position == 4) {
            profileImage.setImageResource(R.drawable.twitter);
            userIdTitle.setText("User Name");
            profileFindId.setHint("user@123");
        }
        if (position == 5) {
            profileImage.setImageResource(R.drawable.snapchat);
            userIdTitle.setText("User Name");
            profileFindId.setHint("user@123");
        }
        if (position == 6) {
            profileImage.setImageResource(R.drawable.whatsapp);
            userIdTitle.setText("User Name");
            profileFindId.setHint("user@123");
        }
        if (position == 7) {
            profileImage.setImageResource(R.drawable.reddit);
            userIdTitle.setText("User Name");
            profileFindId.setHint("user@123");
        }
        if (position == 8) {
            profileImage.setImageResource(R.drawable.linkedin);
            userIdTitle.setText("User Name");
            profileFindId.setHint("user@123");
        }
        if (position == 9) {
            profileImage.setImageResource(R.drawable.tik_tok);
            userIdTitle.setText("User Name");
            profileFindId.setHint("user@123");
        }
        if (position == 10) {
            profileImage.setImageResource(R.drawable.youtube);
            userIdTitle.setText("User Name");
            profileFindId.setHint("user@123");
        }
        if (position == 11) {
            profileImage.setImageResource(R.drawable.pinterest);
            userIdTitle.setText("User Name");
            profileFindId.setHint("user@123");
        }
        if (position == 12) {
            profileImage.setImageResource(R.drawable.quora);
            userIdTitle.setText("User Name");
            profileFindId.setHint("user@123");
        }
        if (position == 13) {
            profileImage.setImageResource(R.drawable.tumblr);
            userIdTitle.setText("User Name");
            profileFindId.setHint("user@123");
        }
        if (position == 14) {
            profileImage.setImageResource(R.drawable.twitch);
            userIdTitle.setText("User Name");
            profileFindId.setHint("user@123");
        }
        if (position == 15) {
            profileImage.setImageResource(R.drawable.discord);
            userIdTitle.setText("User Name");
            profileFindId.setHint("user@123");
        }
        if (position == 16) {
            profileImage.setImageResource(R.drawable.mastodon);
            userIdTitle.setText("User Name");
            profileFindId.setHint("user@123");
        }
        if (position == 17) {
            profileImage.setImageResource(R.drawable.vkontakte);
            userIdTitle.setText("User Name");
            profileFindId.setHint("user@123");
        }
        if (position == 18) {
            profileImage.setImageResource(R.drawable.sina_weibo);
            userIdTitle.setText("User Name");
            profileFindId.setHint("user@123");
        }
        if (position == 19) {
            profileImage.setImageResource(R.drawable.kakao_talk);
            userIdTitle.setText("User Name");
            profileFindId.setHint("user@123");
        }
        if (position == 20) {
            profileImage.setImageResource(R.drawable.world_wide_web);
            userIdTitle.setText("Web URL");
            profileFindId.setHint("https://rajeshb.com");
        }
        if (position == 21) {
            profileImage.setImageResource(R.drawable.gmail);
            userIdTitle.setText("Email Id");
            profileFindId.setHint("nearbyshare@gmail.com");
        }
        if (position == 22) {
            profileImage.setImageResource(R.drawable.other);
        }


    }

    private void SetDropDown(Spinner profile_spinner) {
        arrayList.add("Select Profile");
        arrayList.add("Facebook");
        arrayList.add("Instagram");
        arrayList.add("GitHub");
        arrayList.add("Twitter");
        arrayList.add("Snapchat");
        arrayList.add("Whatsapp");
        arrayList.add("Reddit");
        arrayList.add("Linkedin");
        arrayList.add("TikTok");
        arrayList.add("YouTube");
        arrayList.add("Pinterest");
        arrayList.add("Quora");
        arrayList.add("Tumblr");
        arrayList.add("Twitch");
        arrayList.add("Discord");
        arrayList.add("Mastodon");
        arrayList.add("VKontakte");
        arrayList.add("Weibo");
        arrayList.add("KakaoTalk");
        arrayList.add("Website");
        arrayList.add("Email");
        arrayList.add("Other");


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, arrayList);
        profile_spinner.setAdapter(arrayAdapter);
        profile_spinner.setSelection(0);
        profile_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectProfile(position, view);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}