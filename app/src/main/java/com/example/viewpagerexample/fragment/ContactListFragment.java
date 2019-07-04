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
import com.example.viewpagerexample.R;
import com.example.viewpagerexample.databinding.FragmentContactListBinding;;
import com.example.viewpagerexample.viewmodel.ContactVM;
import com.google.android.material.snackbar.Snackbar;

public class ContactListFragment extends BaseFragment {
    FragmentContactListBinding binding;
    private static final int PERMISSIONS_REQUEST_CONTACTS = 123;


    public ContactListFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            View  view = inflater.inflate(R.layout.fragment_contact_list,container,false);
            binding = DataBindingUtil.bind(view);
            ContactVM contactVM = new ContactVM(this,binding);
            binding.setContactView(contactVM);
            getContactPermission();
        return view;

    }

    private boolean getContactPermission(){

        if(getActivity().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)+getActivity().checkSelfPermission( Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission
                    .READ_EXTERNAL_STORAGE, Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_CONTACTS);
        }

        return true;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_CONTACTS:
                if (grantResults.length > 0) {
                    boolean readExternalFile = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean readContacts = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    if ( readExternalFile && readContacts ) {
                        // write your logic here
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
                                                        .READ_EXTERNAL_STORAGE, Manifest.permission.READ_CONTACTS},
                                                PERMISSIONS_REQUEST_CONTACTS);
                                    }
                                }).show();
                    }
                }
                break;
        }
    }


        @Override
       protected void doWorkOnVisible() {
        super.doWorkOnVisible();

    }
}
