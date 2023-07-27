package com.sharebysocial.com.Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;
import com.sharebysocial.com.Activities.FriendViewActivity;
import com.sharebysocial.com.Helper.CaptureAct;
import com.sharebysocial.com.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QrFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QrFragment extends BottomSheetfFragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private ImageView qrCode;
    private CardView QRButton;

    public QrFragment() {
        // Required empty public constructor
    }

    public static QrFragment newInstance(String param1, String param2) {
        QrFragment fragment = new QrFragment();
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
            String mParam1 = getArguments().getString(ARG_PARAM1);
            String mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_qr, container, false);
        findIds(view);
        generateQr(); // Generating qr code
        CLickScanBtn(view);

        return view;
    }

    private void CLickScanBtn(View view) {
        QRButton = view.findViewById(R.id.scanBtn);
        QRButton.setOnClickListener(v -> {
            scanQr(); // This function for scan QR code
        });
    }

    private void scanQr() {
        ScanOptions options = new ScanOptions();
        options.setDesiredBarcodeFormats(String.valueOf(BarcodeFormat.QR_CODE));
        options.setPrompt("Scan a QR");
        options.setCameraId(0);  // Use a specific camera of the device
        options.setBeepEnabled(false);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class);
        options.setBarcodeImageEnabled(true);
        barcodeLauncher.launch(options);
    }

    private final ActivityResultLauncher<ScanOptions> barcodeLauncher = registerForActivityResult(new ScanContract(),
            result -> {
                if (result.getContents() == null) {
                    Toast.makeText(requireActivity().getApplication(), "Cancelled", Toast.LENGTH_LONG).show();
                } else {

                    if (isValidQR(result.getContents())) {
                        Intent intent = new Intent(requireContext(), FriendViewActivity.class);
                        intent.putExtra("profileId", result.getContents());
                        startActivity(intent);
                        Log.d("CheckingBug", "bug: Fixing bug");
                    } else {
                        System.out.println("This qr code is invalid for this application");
                    }
                }
            });

    private boolean isValidQR(String str) {
        return !str.contains("@") || !str.contains(".") || !str.contains("#") || !str.contains("$") || !str.contains("[") || !str.contains("]") || !str.contains("/") || !str.contains("\\");
    }

    private void generateQr() {
        String userId = FirebaseAuth.getInstance().getUid();
        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
        try {
            Bitmap bitmap = barcodeEncoder.encodeBitmap(userId, BarcodeFormat.QR_CODE, 400, 400);
            qrCode.setImageBitmap(bitmap);
        } catch (WriterException e) {
            throw new RuntimeException(e);
        }
    }


    private void findIds(View view) {
        qrCode = view.findViewById(R.id.qrCodeId);
    }
}