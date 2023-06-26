package com.sharebysocial.com.Fragment;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
        Spinner profile_spinner = view.findViewById(R.id.profile_spinner_id);
        TextView userIdTitle = view.findViewById(R.id.add_activity_user_name_id);
        // Setting content to dropdown list
        SetDropDown();
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
        return view;
    }

    public void selectProfile(int position, View view) {

        if (position == 1) {
            profileImage.setImageResource(R.drawable.facebook);
        }

        if (position == 2) {
            Toast.makeText(getContext(), "The position is " + position, Toast.LENGTH_SHORT).show();
        }
        if (position == 3) {
            Toast.makeText(getContext(), "The position is " + position, Toast.LENGTH_SHORT).show();
        }
        if (position == 4) {
            Toast.makeText(getContext(), "The position is " + position, Toast.LENGTH_SHORT).show();
        }
        if (position == 5) {
            Toast.makeText(getContext(), "The position is " + position, Toast.LENGTH_SHORT).show();
        }
        if (position == 6) {
            Toast.makeText(getContext(), "The position is " + position, Toast.LENGTH_SHORT).show();
        }
        if (position == 7) {
            Toast.makeText(getContext(), "The position is " + position, Toast.LENGTH_SHORT).show();
        }
        if (position == 8) {
            Toast.makeText(getContext(), "The position is " + position, Toast.LENGTH_SHORT).show();
        }
        if (position == 9) {
            Toast.makeText(getContext(), "The position is " + position, Toast.LENGTH_SHORT).show();
        }
        if (position == 10) {
            Toast.makeText(getContext(), "The position is " + position, Toast.LENGTH_SHORT).show();
        }
        if (position == 11) {
            Toast.makeText(getContext(), "The position is " + position, Toast.LENGTH_SHORT).show();
        }
        if (position == 12) {
            Toast.makeText(getContext(), "The position is " + position, Toast.LENGTH_SHORT).show();
        }
        if (position == 13) {
            Toast.makeText(getContext(), "The position is " + position, Toast.LENGTH_SHORT).show();
        }
        if (position == 14) {
            Toast.makeText(getContext(), "The position is " + position, Toast.LENGTH_SHORT).show();
        }
        if (position == 15) {
            Toast.makeText(getContext(), "The position is " + position, Toast.LENGTH_SHORT).show();
        }
        if (position == 16) {
            Toast.makeText(getContext(), "The position is " + position, Toast.LENGTH_SHORT).show();
        }
        if (position == 17) {
            Toast.makeText(getContext(), "The position is " + position, Toast.LENGTH_SHORT).show();
        }
        if (position == 18) {
            Toast.makeText(getContext(), "The position is " + position, Toast.LENGTH_SHORT).show();
        }
        if (position == 19) {
            Toast.makeText(getContext(), "The position is " + position, Toast.LENGTH_SHORT).show();
        }
        if (position == 20) {
            Toast.makeText(getContext(), "The position is " + position, Toast.LENGTH_SHORT).show();
        }
        if (position == 21) {
            Toast.makeText(getContext(), "The position is " + position, Toast.LENGTH_SHORT).show();
        }

    }

    private void SetDropDown() {
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
        arrayList.add("Other");

    }
}