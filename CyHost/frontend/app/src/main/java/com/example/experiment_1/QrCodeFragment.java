package com.example.experiment_1;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.squareup.picasso.Picasso;

import java.io.IOException;

public class QrCodeFragment extends Fragment {
    private ImageView imageView;
    private String userName;
    private String url = "https://api.qrserver.com/v1/create-qr-code/?size=150x150&data=";
    private TextView userNameText;
    private SurfaceView surfaceView;
    private CameraSource cameraSource;
    private TextView infoText;
    private BarcodeDetector barcodeDetector;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_qr_code, container,
                false);

        if(getActivity().getIntent().getExtras() != null){
            userName = getActivity().getIntent().getStringExtra("userName");
            userNameText = rootView.findViewById((R.id.qrUserName));
            userNameText.setText("@" + userName);
            url = "https://api.qrserver.com/v1/create-qr-code/?size=150x150&data=" + userName;
        }
        imageView = (ImageView) rootView.findViewById(R.id.qrCode);
        surfaceView = (SurfaceView)rootView.findViewById(R.id.camerapreview);
        infoText = (TextView)rootView.findViewById(R.id.infoText);
        barcodeDetector = new BarcodeDetector.Builder(getActivity().getApplicationContext()).setBarcodeFormats(Barcode.QR_CODE).build();

        cameraSource = new CameraSource.Builder(getActivity().getApplicationContext(), barcodeDetector).setRequestedPreviewSize(640,480).build();

        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                if(ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                    return;
                }
                try {
                    cameraSource.start(surfaceHolder);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                cameraSource.stop();
            }
        });

        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> qrCodes = detections.getDetectedItems();
                Toast.makeText(getContext(),  String.valueOf(qrCodes.size()), Toast.LENGTH_LONG).show();

                if(qrCodes.size() !=0){
                    infoText.post(new Runnable() {
                        @Override
                        public void run() {
                            Vibrator vibrator = (Vibrator)getActivity().getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                            vibrator.vibrate(1000);

                            infoText.setText(qrCodes.valueAt(0).displayValue);
                            Toast.makeText(getContext(),  qrCodes.valueAt(0).displayValue, Toast.LENGTH_LONG).show();

                            Log.d("QR CODE VALUE", qrCodes.valueAt(0).displayValue);
                        }
                    });
                }
            }
        });
        loadImageFromUrl(url);



        return rootView;
    }



    private void loadImageFromUrl(String url) {
        Picasso.with(getActivity())
                .load(url).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(imageView, new com.squareup.picasso.Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError() {

            }
        });
    }
    public String qrCode(String userName, QRCodeHandler handler){
        if(handler.getResponse(userName).equals("true")){
            return userName;
        }
        else{
            return "Error";
        }
    }
}

