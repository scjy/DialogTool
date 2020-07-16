package com.dlc.dlccommonviewslibrary.view;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.dlc.dlccommonviewslibrary.R;


/**
 * Created by Administrator on 2018/8/29/029.
 */

public class AddDeviceDialog extends Dialog implements View.OnClickListener {
    TextView tvSerial;
    TextView tvAndroidId;
    TextView tvImei;
    EditText etNumber;
    TextView tvCancel;
    TextView tvSure;
    private boolean isGetImei;
    private String serial;
    private String androidId;
    private String imei;

    public AddDeviceDialog(Context context) {
        super(context, R.style.my_style_dialog);
        setContentView(R.layout.dialog_add_device);
        tvSerial = findViewById(R.id.tv_serial);
        tvAndroidId = findViewById(R.id.tv_android_id);
        tvImei = findViewById(R.id.tv_imei);
        etNumber = findViewById(R.id.et_number);
        tvCancel = findViewById(R.id.tv_cancel);
        tvSure = findViewById(R.id.tv_sure);
        serial = android.os.Build.SERIAL;
        androidId = Settings.System.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        tvSerial.setText("设备序列号：" + serial);
        tvAndroidId.setText("Android ID：" + androidId);
        tvSerial.setOnClickListener(this);
        tvAndroidId.setOnClickListener(this);
        tvImei.setOnClickListener(this);
        getImeiFromPhone();
    }

    private void getImeiFromPhone() {
        TelephonyManager telephonyManager = (TelephonyManager) getContext().getSystemService(Context.TELEPHONY_SERVICE);
        try {
            @SuppressLint("MissingPermission") String imei = telephonyManager.getDeviceId();
            this.imei = imei;
            if (imei != null) {
                tvImei.setText("IMEI：" + imei);
            } else {
                if (!isGetImei) {
                    return;
                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getImeiFromPhone();
                    }
                }, 5000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setGetImei(boolean getImei) {
        isGetImei = getImei;
    }

    public String getSerial() {
        if (serial == null) {
            serial = "";
        }
        return serial;
    }

    public String getAndroidId() {
        if (androidId == null) {
            androidId = "";
        }
        return androidId;
    }

    public String getImei() {
        if (imei == null) {
            imei = "";
        }
        return imei;
    }

    public String getNumber() {
        return etNumber.getText().toString().trim();
    }

    public AddDeviceDialog setCancelClickListener(View.OnClickListener listener) {
        tvCancel.setOnClickListener(listener);
        return this;
    }

    public AddDeviceDialog setConfirmClickListener(View.OnClickListener listener) {
        tvSure.setOnClickListener(listener);
        return this;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tv_serial) {
            etNumber.setText(getSerial());
        } else if (view.getId() == R.id.tv_android_id) {
            etNumber.setText(getAndroidId());
        } else if (view.getId() == R.id.tv_imei) {
            etNumber.setText(getImei());
        }
    }
}
