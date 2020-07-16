package com.dlc.dialogtooldemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dlc.dlccommonviewslibrary.util.DeviceNumSaver;
import com.dlc.dlccommonviewslibrary.view.AddDeviceDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AddDeviceDialog addDeviceDialog = new AddDeviceDialog(this);
        addDeviceDialog.show();
    }
}
