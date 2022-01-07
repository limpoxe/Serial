package com.limpoxe.app;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.limpoxe.serial.SerialPort;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    SerialPort serialPort = new SerialPort("/dev/ttyS0", 115200, 1, 8, 0, 0, 0x2);
                    serialPort.getInputStream();
                    serialPort.getOutputStream();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
