package com.example.viewpagerexample.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import com.example.viewpagerexample.R;
import com.example.viewpagerexample.databinding.FragmentCameraBinding;
import com.example.viewpagerexample.viewmodel.CameraVM;
import com.google.android.material.snackbar.Snackbar;

public class CamerFragment extends Fragment {

    private FragmentCameraBinding binding;
    private CameraVM cameraVM;

    private static final int PERMISSIONS_REQUEST_CAMERA = 123;

    public CamerFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_camera, container, false);
        this.binding = DataBindingUtil.bind(view);
        this.cameraVM = new CameraVM(this, binding);
        binding.setCameraView(cameraVM);
        getCameraPermission();
        return view;
    }

    private boolean getCameraPermission() {
        if(getActivity().checkSelfPermission(Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission
                    .CAMERA}, PERMISSIONS_REQUEST_CAMERA);
        }else {
            cameraVM.startCamera();
        }

        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_CAMERA:
                if (grantResults.length > 0) {
                    boolean readCamera = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if ( readCamera) {
                        // write your logic here
                        cameraVM.startCamera();
                        return;
                    } else {
                        Snackbar.make(this.getActivity().findViewById(android.R.id.content),
                                "Please Grant Permissions",
                                Snackbar.LENGTH_INDEFINITE).setAction("ENABLE",
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        requestPermissions(
                                                new String[]{Manifest.permission
                                                        .CAMERA},
                                                PERMISSIONS_REQUEST_CAMERA);
                                    }
                                }).show();
                    }
                }
                break;
        }}


}
