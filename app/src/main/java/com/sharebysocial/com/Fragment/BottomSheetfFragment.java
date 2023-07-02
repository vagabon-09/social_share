package com.sharebysocial.com.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.sharebysocial.com.R;

public class BottomSheetfFragment extends BottomSheetDialogFragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    static LinearLayout DeleteButton;
    static LinearLayout EditButton;


    public BottomSheetfFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static BottomSheetfFragment newInstance(String param1, String param2) {
        BottomSheetfFragment fragment = new BottomSheetfFragment();
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
        View v = inflater.inflate(R.layout.fragment_bottomsheetf, container, false);
        /*In this function we are setting up all the button that can we perform all the jobs*/
        fId(v);
        return v;
    }



    private void fId(View v) {
        DeleteButton = v.findViewById(R.id.deleteBtnId);
        EditButton = v.findViewById(R.id.editButtonId);
    }

    // Doing operation to delete item from recycler view


}