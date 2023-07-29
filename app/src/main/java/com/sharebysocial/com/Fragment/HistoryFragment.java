package com.sharebysocial.com.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        fetchData(view); // this function is using to set data in recyclerview
        return view;
    }

    public void fetchData(View view) {
        RecyclerView historyRecView = view.findViewById(R.id.friendSearchId);
        historyRecView.setLayoutManager(new LinearLayoutManager(requireContext()));
        DatabaseHelper helper = DatabaseHelper.getDB(requireContext());
        ArrayList<HistoryModel> historyModels = (ArrayList<HistoryModel>) helper.historyDAO().getAllHistory();
        HistoryAdapter adapter = new HistoryAdapter(historyModels);
        historyRecView.setAdapter(adapter);
    }
}