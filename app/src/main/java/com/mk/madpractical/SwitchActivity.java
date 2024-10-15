package com.mk.madpractical;


import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothSocket;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

public class SwitchActivity extends AppCompatActivity {

    private static final int REQUEST_BLUETOOTH_CONNECT_PERMISSION = 3;
    private BluetoothAdapter bluetoothAdapter;
    private BluetoothManager bluetoothManager;
    private BluetoothSocket bluetoothSocket;
    private OutputStream outputStream;
    private Switch switch1, switch2, switch3, switch4;
    private TextView text;
    private Button connect_btn;

    private static final UUID UUID_HC05 = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_switch);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        switch1 = findViewById(R.id.switch5);
        switch2 = findViewById(R.id.switch6);
        switch3 = findViewById(R.id.switch7);
        switch4 = findViewById(R.id.switch8);

        text = findViewById(R.id.text);
        connect_btn = findViewById(R.id.connect);

        bluetoothManager = getSystemService(BluetoothManager.class);
        bluetoothAdapter = bluetoothManager.getAdapter();
        String deviceMac = getIntent().getStringExtra("device_mac");
        String deviceName = getIntent().getStringExtra("device_name");
        text.setText("Device: " + deviceName);
        connect_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connectToBluetoothDevice(deviceMac);
            }
        });


        // Toggle switch listener
        switch1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                sendBluetoothMessage("1");
            } else {
                sendBluetoothMessage("2");
            }
        });
        switch2.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                sendBluetoothMessage("3");
            } else {
                sendBluetoothMessage("4");
            }
        });
        switch3.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                sendBluetoothMessage("5");
            } else {
                sendBluetoothMessage("6");
            }
        });
        switch4.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                sendBluetoothMessage("7");
            } else {
                sendBluetoothMessage("8");
            }
        });
    }

    // Function to check and request Bluetooth connect permission
    private void checkBluetoothConnectPermission(String macAddress) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.BLUETOOTH_CONNECT
                }, REQUEST_BLUETOOTH_CONNECT_PERMISSION);
            } else {
                connectToBluetoothDevice(macAddress);
            }
        } else {
            connectToBluetoothDevice(macAddress);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_BLUETOOTH_CONNECT_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                String deviceMac = getIntent().getStringExtra("device_mac");
                connectToBluetoothDevice(deviceMac);
            } else {
                Toast.makeText(this, "Bluetooth connect permission is required", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Function to connect to the Bluetooth device using its MAC address
    private void connectToBluetoothDevice(String macAddress) {
        BluetoothDevice device = bluetoothAdapter.getRemoteDevice(macAddress);

        try {
            if (bluetoothSocket != null) {
                bluetoothSocket.close();
                bluetoothSocket.connect();
            } else {
                bluetoothSocket = device.createRfcommSocketToServiceRecord(UUID_HC05);
                bluetoothSocket.connect();
            }
            outputStream = bluetoothSocket.getOutputStream();
            showToast("Connected to " + macAddress);
            sendBluetoothMessage("2");
            sendBluetoothMessage("4");
            sendBluetoothMessage("6");
            sendBluetoothMessage("8 ");
        } catch (Exception e) {
            bluetoothSocket = null;
            showToast("Connection Failed");
        }
    }

    // Function to send a message via Bluetooth
    private void sendBluetoothMessage(String message) {
        try {
            if (outputStream != null) {
                outputStream.write(message.getBytes());
            }
        } catch (IOException e) {
            showToast("Failed to send message");
        }
    }

    // Show a toast message
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        try {
            bluetoothSocket.close();
        } catch (IOException e) {
        }
        super.onDestroy();
    }
}

