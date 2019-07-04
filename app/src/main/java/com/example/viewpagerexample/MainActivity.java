package com.example.viewpagerexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import com.example.viewpagerexample.databinding.ActivityMainBinding;
import com.example.viewpagerexample.viewmodel.MainActivityVM;


public class MainActivity extends AppCompatActivity {
    private   ActivityMainBinding mainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        MainActivityVM mainActivityVM = new MainActivityVM(this, mainBinding);
        mainBinding.setViewModel(mainActivityVM);

    }


}