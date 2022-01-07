package com.limpoxe.serial;

import java.io.FileDescriptor;

public class SerialPort {
    public static native FileDescriptor open(String path, int baudrate, int flags);

    public static native void close();

    static {
        System.loadLibrary("serialport");
    }
}
