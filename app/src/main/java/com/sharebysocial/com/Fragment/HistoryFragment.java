package com.sharebysocial.com.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.sharebysocial.com.Helper.InternetWarning;
import com.sharebysocial.com.Helper.NetworkCheck;
import com.sharebysocial.com.R;
import com.sharebysocial.com.RoomDB.Adapter.HistoryAdapter;
import com.sharebysocial.com.RoomDB.Helper.DatabaseHelper;
import com.sharebysocial.com.RoomDB.Model.HistoryModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HistoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HistoryFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private EditText searchId;
    private ArrayList<HistoryModel> historyModels;
    private HistoryAdapter adapter;
    private String mParam1;
    private String mParam2;

    public HistoryFragment() {
        // Required empty public constructor
    }

    public static HistoryFragment newInstance(String param1, String param2) {
        HistoryFragment fragment = new HistoryFragment();
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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        internetCheck();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        setItemView(view); // Finding all views form
        fetchData(view); // this function is using to set data in recyclerview
        implementSearch(); // Default search function
        searchBtnCLick(view);// when some one click on search btb
        return view;
    }
    public void internetCheck() {
        if (NetworkCheck.isNetworkConnected(requireContext())) {
            InternetWarning internetWarning = new InternetWarning(requireActivity());
        }
    }
    private void searchBtnCLick(View view) {
        ImageView searchBtn = view.findViewById(R.id.searchBtn);
        searchBtn.setOnClickListener(v -> {
            implementSearch();
        });

    }

    private void implementSearch() {
        searchId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
    }

    private void filter(String s) {
        ArrayList<HistoryModel> arrayList = new ArrayList<>();
        for (HistoryModel model : historyModels) {
            if (model.getUserName().toLowerCase().contains(s.toLowerCase())) {
                arrayList.add(model);
            }
        }
        adapter.filteredList(arrayList);
    }

    private void setItemView(View view) {
        searchId = view.findViewById(R.id.friendSearchViewId);
    }

    public void fetchData(View view) {
        RecyclerView historyRecView = view.findViewById(R.id.friendSearchId);
        historyRecView.setLayoutManager(new LinearLayoutManager(requireContext()));
        DatabaseHelper helper = DatabaseHelper.getDB(requireContext());
        historyModels = (ArrayList<HistoryModel>) helper.historyDAO().getAllHistory();
        adapter = new HistoryAdapter(historyModels);
        historyRecView.setAdapter(adapter);
    }
}